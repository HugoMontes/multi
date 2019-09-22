/*     */ package org.erp.controller;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Logger;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.component.UIViewRoot;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.inject.Inject;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.erp.entities.GenKey;
/*     */ import org.erp.entities.SegMenu;
/*     */ import org.erp.entities.SegRol;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.seg.QL.seg.AccesoQLBeanLocal;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AESEncryptUtils;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.jboss.solder.resourceLoader.Resource;
/*     */ import org.primefaces.model.menu.DefaultMenuItem;
/*     */ import org.primefaces.model.menu.DefaultMenuModel;
/*     */ import org.primefaces.model.menu.DefaultSubMenu;
/*     */ import org.primefaces.model.menu.MenuModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="menuController")
/*     */ @ViewScoped
/*     */ public class MenuController
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   @Resource("AppConfiguration.properties")
/*     */   private Properties messageConfiguration;
/*     */   @Inject
/*     */   AccesoQLBeanLocal accesoQLBeanLocal;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   private Logger logger;
/*  79 */   private SegUsuario segUsuario = new SegUsuario();
/*  80 */   private String pagina = "";
/*     */   
/*     */   boolean usuarioBln;
/*     */   private Integer tiempoSession;
/*  84 */   private MenuModel menuModel = new DefaultMenuModel();
/*     */   
/*     */   public String sessionId;
/*     */   
/*     */   private List<SegRol> listaRolesUsuario;
/*     */   
/*     */   private GenKey genKey;
/*     */   
/*     */   private String systemOwnModule;
/*     */   
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public AccesoBean getAccesoBean()
/*     */   {
/*  99 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean) {
/* 103 */     this.accesoBean = accesoBean;
/* 104 */     this.segUsuario = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
/* 110 */     if (!FacesContext.getCurrentInstance().isPostback()) {
/* 111 */       FacesContext context = FacesContext.getCurrentInstance();
/* 112 */       context.getViewRoot().setLocale(new Locale("es"));
/* 113 */       Util.putSessionMap("locale", context.getViewRoot().getLocale().toString());
/*     */     }
/*     */     
/*     */ 
/* 117 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 118 */     HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
/* 119 */     this.logger.info("Session creda = " + session + "::::::::::::" + this.segUsuario.getNombreCompleto());
/*     */     
/* 121 */     this.systemOwnModule = this.messageConfiguration.getProperty("system.own.module");
/*     */     
/* 123 */     if (this.segUsuario.getUsuario() != null)
/*     */     {
/*     */ 
/* 126 */       this.tiempoSession = Integer.valueOf(900000);
/* 127 */       this.pagina = "/pages/inicio.xhtml";
/* 128 */       System.out.println("Ingresando por primera vez");
/*     */       
/* 130 */       this.genKey = new GenKey();
/*     */       try {
/* 132 */         this.genKey = this.serviciosUtilBeanLocal.licenceKey();
/*     */         
/* 134 */         if (this.genKey.getId() != null) {
/* 135 */           this.genKey.setTipoLicenciaMostrar(new Integer(AESEncryptUtils.decrypt(this.systemOwnModule, this.genKey.getTipoLicencia())));
/* 136 */           this.genKey.setDesdeMostrar(convertStrigToDate(AESEncryptUtils.decrypt(this.systemOwnModule, this.genKey.getDesde())));
/* 137 */           this.genKey.setHastaMostrar(convertStrigToDate(AESEncryptUtils.decrypt(this.systemOwnModule, this.genKey.getHasta())));
/* 138 */           this.genKey.setSerieGenerada(AESEncryptUtils.encrypt(this.systemOwnModule, this.genKey.getTipoLicenciaMostrar().toString() + formatoFecha(this.genKey.getDesdeMostrar()) + formatoFecha(this.genKey.getHastaMostrar())));
/*     */         }
/*     */         
/*     */       }
/*     */       catch (AdminException e1)
/*     */       {
/* 144 */         e1.printStackTrace();
/*     */       }
/*     */       catch (NumberFormatException e) {
/* 147 */         e.printStackTrace();
/*     */       }
/*     */       catch (Exception e) {
/* 150 */         e.printStackTrace();
/*     */       }
/*     */       try
/*     */       {
/* 154 */         Date fechaAct = new Date();
/* 155 */         if ((fechaAct.after(this.genKey.getDesdeMostrar())) && 
/* 156 */           (fechaAct.before(this.genKey.getHastaMostrar())) && 
/* 157 */           (this.genKey.getSerieGenerada().equals(this.genKey.getSerial()))) {
/* 158 */           definicionMenu();
/*     */         } else {
/* 160 */           definicionMenuLicencia();
/*     */         }
/*     */       } catch (IOException e) {
/* 163 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 167 */       cerrarSesion();
/*     */     }
/*     */   }
/*     */   
/*     */   public void definicionMenuLicencia() throws IOException {
/* 172 */     this.sessionId = JSFUtilities.getHttpSessionAttribute("ticketId");
/* 173 */     if (this.sessionId != null) {
/* 174 */       this.listaRolesUsuario = new ArrayList();
/*     */       try {
/* 176 */         this.listaRolesUsuario = this.accesoQLBeanLocal.obtenerListaRoles(this.segUsuario);
/* 177 */         DefaultSubMenu subMenu = new DefaultSubMenu("Error");
/* 178 */         subMenu.setIcon("ui-icon-grip-dotted-vertical");
/* 179 */         DefaultMenuItem item = new DefaultMenuItem("No existe licencia valida");
/* 180 */         item.setIcon("ui-icon-carat-1-e");
/* 181 */         subMenu.addElement(item);
/* 182 */         this.menuModel.addElement(subMenu);
/*     */       }
/*     */       catch (AdminException e) {
/* 185 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 188 */       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/* 189 */       ec.redirect(ec.getRequestContextPath());
/*     */     }
/*     */   }
/*     */   
/*     */   public void definicionMenu() throws IOException
/*     */   {
/* 195 */     List<SegMenu> lista = new ArrayList();
/*     */     try
/*     */     {
/* 198 */       this.listaRolesUsuario = new ArrayList();
/* 199 */       this.listaRolesUsuario = this.accesoQLBeanLocal.obtenerListaRoles(this.segUsuario);
/* 200 */       this.sessionId = JSFUtilities.getHttpSessionAttribute("ticketId");
/* 201 */       if (this.sessionId != null) {
/* 202 */         lista = this.accesoQLBeanLocal.obtenerListaMenu(this.segUsuario);
/* 203 */         for (SegMenu menu : lista) {
/* 204 */           DefaultSubMenu subMenu = new DefaultSubMenu(menu.getNombre());
/* 205 */           subMenu.setIcon("ui-icon-grip-dotted-vertical");
/* 206 */           List<SegMenu> listaItem = this.accesoQLBeanLocal.obtenerListaMenuItem(menu);
/* 207 */           for (SegMenu menuItem : listaItem) {
/* 208 */             List<SegRol> usuariosMnuRoles = this.accesoQLBeanLocal.obtenerListaRolesMenu(menuItem.getId());
/* 209 */             if (comparadorListaRoles(usuariosMnuRoles, this.listaRolesUsuario)) {
/* 210 */               DefaultMenuItem item = new DefaultMenuItem(menuItem.getNombre());
/* 211 */               if (menuItem.getUrl() != null) {
/* 212 */                 item.setOutcome(menuItem.getUrl() + "?ticketId=" + this.sessionId);
/*     */               }
/* 214 */               item.setIcon("ui-icon-carat-1-e");
/* 215 */               item.setProcess(eventoMenuItem());
/*     */               
/* 217 */               subMenu.addElement(item);
/*     */             }
/*     */           }
/* 220 */           if (subMenu.getElements().size() > 0) {
/* 221 */             this.menuModel.addElement(subMenu);
/*     */           }
/*     */         }
/*     */       } else {
/* 225 */         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/* 226 */         ec.redirect(ec.getRequestContextPath());
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 233 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean tieneRole(String role) {
/* 238 */     boolean tieneRol = false;
/* 239 */     if (this.listaRolesUsuario != null) {
/* 240 */       for (SegRol rol : this.listaRolesUsuario) {
/* 241 */         if (rol.getNombre().equals(role)) {
/* 242 */           tieneRol = true;
/* 243 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 247 */     return tieneRol;
/*     */   }
/*     */   
/*     */   public void home() {
/* 251 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 252 */     ExternalContext ec = fc.getExternalContext();
/* 253 */     ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/*     */     try {
/* 255 */       ec.redirect(servletContext.getContextPath() + "/inicio.jsf");
/*     */     }
/*     */     catch (IOException e) {
/* 258 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void cerrarSesion() {
/* 263 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 264 */     ExternalContext ec = fc.getExternalContext();
/* 265 */     ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/*     */     try {
/* 267 */       HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
/*     */       
/* 269 */       Util.limpiarCacheSession(session);
/* 270 */       ec.redirect(servletContext.getContextPath() + "/index.jsf");
/*     */     } catch (IOException e) {
/* 272 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public String eventoMenuItem()
/*     */   {
/* 278 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 279 */     HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
/*     */     
/* 281 */     Util.limpiarCache(session);
/* 282 */     return "";
/*     */   }
/*     */   
/*     */   public String eventoMenuItemVolver() {
/* 286 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 287 */     HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
/* 288 */     return "";
/*     */   }
/*     */   
/*     */   private boolean comparadorListaRoles(List<SegRol> rolesMenu, List<SegRol> rolesUsuario)
/*     */   {
/* 293 */     boolean resultado = false;
/* 294 */     Iterator localIterator2; for (Iterator localIterator1 = rolesMenu.iterator(); localIterator1.hasNext(); 
/* 295 */         localIterator2.hasNext())
/*     */     {
/* 294 */       SegRol menu = (SegRol)localIterator1.next();
/* 295 */       localIterator2 = rolesUsuario.iterator(); continue;SegRol roles = (SegRol)localIterator2.next();
/* 296 */       if (menu.equals(roles)) {
/* 297 */         resultado = true;
/*     */       }
/*     */     }
/*     */     
/* 301 */     return resultado;
/*     */   }
/*     */   
/*     */ 
/*     */   public void eventoMenu(ActionEvent event)
/*     */   {
/* 307 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 308 */     HttpSession session = (HttpSession)fc.getExternalContext().getSession(
/* 309 */       false);
/*     */     
/* 311 */     System.out.println("NUEVA SESION CREADA::::::::::::::: " + session);
/*     */     
/*     */ 
/* 314 */     Util.limpiarCache(session);
/*     */     
/* 316 */     Map<String, String> map = fc.getExternalContext().getRequestParameterMap();
/*     */     
/* 318 */     if (map != null)
/*     */     {
/*     */ 
/* 321 */       this.pagina = "/pages/inicio.xhtml";
/*     */     } else {
/* 323 */       System.out.println("NO EXISTE SESION");
/*     */     }
/*     */   }
/*     */   
/*     */   public void eventoVolver(String nombreBean)
/*     */   {
/* 329 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 330 */     HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
/*     */     
/* 332 */     Util.limpiarCachePersonalCrud(session, nombreBean);
/* 333 */     Map<String, String> map = fc.getExternalContext().getRequestParameterMap();
/* 334 */     if (map != null) {
/* 335 */       this.pagina = "/pages/principal.xhtml";
/*     */     } else {
/* 337 */       System.out.println("NULLLLLLLLLLLLLLLLLLL");
/*     */     }
/*     */   }
/*     */   
/*     */   public String getPagina() {
/* 342 */     return this.pagina;
/*     */   }
/*     */   
/*     */   public void setPagina(String pagina) {
/* 346 */     this.pagina = pagina;
/*     */   }
/*     */   
/*     */   public SegUsuario getSegUsuario() {
/* 350 */     return this.segUsuario;
/*     */   }
/*     */   
/*     */   public void setSegUsuario(SegUsuario segUsuario) {
/* 354 */     this.segUsuario = segUsuario;
/*     */   }
/*     */   
/*     */   public boolean isUsuarioBln()
/*     */   {
/* 359 */     return this.usuarioBln;
/*     */   }
/*     */   
/*     */   public void setUsuarioBln(boolean usuarioBln) {
/* 363 */     this.usuarioBln = usuarioBln;
/*     */   }
/*     */   
/*     */   public MenuModel getMenuModel() {
/* 367 */     return this.menuModel;
/*     */   }
/*     */   
/*     */   public void setMenuModel(MenuModel menuModel) {
/* 371 */     this.menuModel = menuModel;
/*     */   }
/*     */   
/*     */   public Integer getTiempoSession() {
/* 375 */     return this.tiempoSession;
/*     */   }
/*     */   
/*     */   public void setTiempoSession(Integer tiempoSession) {
/* 379 */     this.tiempoSession = tiempoSession;
/*     */   }
/*     */   
/*     */   public String getSessionId() {
/* 383 */     return this.sessionId;
/*     */   }
/*     */   
/*     */   public void setSessionId(String sessionId) {
/* 387 */     this.sessionId = sessionId;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\controller\MenuController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */