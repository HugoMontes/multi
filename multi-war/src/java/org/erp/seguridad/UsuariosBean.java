/*     */ package org.erp.seguridad;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.inject.Inject;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.erp.adm.CooperativaEJBBeanLocal;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegPermiso;
/*     */ import org.erp.entities.SegRol;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.seg.ML.seg.SegUsuarioMLBeanLocal;
/*     */ import org.erp.seg.QL.seg.SegUsuarioQLBeanLocal;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.MostrarReporte;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.joda.time.DateTime;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import org.primefaces.model.StreamedContent;
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
/*     */ @ManagedBean(name="usuarioBean")
/*     */ @ViewScoped
/*     */ public class UsuariosBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   private SegUsuarioQLBeanLocal segUsuarioQLBeanLocal;
/*     */   @Inject
/*     */   private SegUsuarioMLBeanLocal segUsuarioMLBeanLocal;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   CooperativaEJBBeanLocal cooperativaEJBBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   private List<SegUsuario> listaSegUsuario;
/*     */   private List<SegUsuario> listaSegUsuarioFiltro;
/*     */   private SegUsuario segUsuario;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String strTitulo;
/*     */   private List<SelectItem> selectPersonas;
/*     */   private List<SelectItem> selectPantallas;
/*     */   private List<SelectItem> selectAcceso;
/*     */   private boolean blnBloquearBoton;
/*     */   private List<SegPermiso> listaPermiso;
/*     */   private UsuariosBean.SegRolDataModel segRolData;
/*     */   private List<SegRol> listaSegRol;
/*     */   private SegRol[] selectRol;
/*     */   private boolean blnDesplegarRoles;
/*     */   private boolean blnCamposEditables;
/*     */   private Date fechaActual;
/*     */   private String contrasenaActual;
/*     */   private String nuevoPass;
/*     */   private List<SelectItem> selectCargo;
/*     */   private List<ParameterTable> parameterTable;
/*     */   private List<Cooperativa> listCooperativa;
/*     */   private List<SelectItem> selectCooperativa;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/* 119 */     this.accesoBean = accesoBean;
/* 120 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public SegUsuario getSegUsuarioSession() {
/* 124 */     return this.segUsuarioSession;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
///*     */     try {
///*     */       
///*     */     }
///*     */     catch (IOException e) {
///* 134 */       e.printStackTrace();
///*     */     }
/*     */     
/* 137 */     System.out.println("Nombre: " + this.segUsuarioSession.getNombres());
/*     */     
/* 139 */     listarUsuariosTabla();
/* 140 */     this.selectCargo = new ArrayList();
/*     */     
/* 142 */     this.strTitulo = "";
/* 143 */     this.selectPersonas = new ArrayList();
/* 144 */     this.selectPantallas = new ArrayList();
/* 145 */     this.selectAcceso = new ArrayList();
/* 146 */     this.blnBloquearBoton = true;
/* 147 */     this.listaSegRol = new ArrayList();
/* 148 */     this.listaPermiso = new ArrayList();
/* 149 */     this.blnDesplegarRoles = false;
/* 150 */     this.fechaActual = new Date();
/* 151 */     this.contrasenaActual = "";
/* 152 */     this.nuevoPass = "";
/* 153 */     cargarParametros();
/*     */   }
/*     */   
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 160 */       this.parameterTable = new ArrayList();
/* 161 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CARGO.getCode());
/* 162 */       this.selectCargo = new ArrayList();
/* 163 */       this.selectCargo.add(new SelectItem(null, "Seleccione..."));
/* 164 */       for (ParameterTable lista : this.parameterTable) {
/* 165 */         this.selectCargo.add(new SelectItem(lista.getId(), lista.getNombre()));
/*     */       }
/*     */       
/* 168 */       this.listCooperativa = new ArrayList();
/* 169 */       this.listCooperativa = this.cooperativaEJBBeanLocal.listadoCooperativa();
/* 170 */       this.selectCooperativa = new ArrayList();
/* 171 */       this.selectCooperativa.add(new SelectItem(null, "Seleccione..."));
/* 172 */       for (Cooperativa lista : this.listCooperativa) {
/* 173 */         this.selectCooperativa.add(new SelectItem(lista.getId(), lista.getRazonSocial()));
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 178 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarUsuariosTabla()
/*     */   {
/* 185 */     this.segUsuario = new SegUsuario();
/* 186 */     this.segUsuario.setIdCooperativa(new Cooperativa());
/* 187 */     this.listaSegUsuario = new ArrayList();
/*     */     try {
/* 189 */       this.listaSegUsuario = this.segUsuarioQLBeanLocal.listaUsuarios();
/* 190 */       this.blnBloquearBoton = true;
/* 191 */       this.blnDesplegarRoles = false;
/*     */     } catch (AdminException e) {
/* 193 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/* 198 */     this.blnBloquearBoton = false;
/* 199 */     this.blnDesplegarRoles = false;
/* 200 */     this.selectRol = null;
/* 201 */     if (this.segUsuario.getIdCooperativa() == null)
/* 202 */       this.segUsuario.setIdCooperativa(new Cooperativa());
/*     */   }
/*     */   
/*     */   public void guardarRolesPorUsuario() {
/*     */     try {
/* 207 */       this.segUsuarioMLBeanLocal.saveOrUpdateRolesPorUsuario(this.segUsuario, this.selectRol);
/* 208 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 211 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void verificarSiExisteUsuario() throws AdminException {
/* 216 */     List<SegUsuario> lista = this.segUsuarioQLBeanLocal.verificarSiUsuario(this.segUsuario);
/* 217 */     if (lista.size() > 0) {
/* 218 */       Util.CrearMsgERROR("El usuario ingresado ya fue registrado, debe ingresar nuevamente", "ERROR", false);
/* 219 */       this.segUsuario.setUsuario(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void verificarSiExisteSigla() throws AdminException {
/* 224 */     List<SegUsuario> lista = this.segUsuarioQLBeanLocal.verificarSiExisteSigla(this.segUsuario);
/* 225 */     if (lista.size() > 0) {
/* 226 */       Util.CrearMsgERROR("La sigla ingresado ya fue registrado, debe ingresar nuevamente", "ERROR", false);
/* 227 */       this.segUsuario.setSigla(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void datosParaCambiarPass() {
/* 232 */     this.strTitulo = "Cambiar Contraseña";
/* 233 */     this.blnDesplegarRoles = false;
/*     */     
/* 235 */     if (this.segUsuario.getIdCooperativa().getId() == null) {
/* 236 */       this.segUsuario.setIdCooperativa(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void desplegarRolesPorUsuario()
/*     */   {
/* 242 */     this.blnDesplegarRoles = true;
/*     */     try
/*     */     {
/* 245 */       this.listaSegRol = this.segUsuarioQLBeanLocal.listarRoles();
/* 246 */       this.listaPermiso = this.segUsuarioQLBeanLocal.listarPermisos(this.segUsuario.getId());
/*     */     }
/*     */     catch (AdminException e) {
/* 249 */       e.printStackTrace();
/*     */     }
/* 251 */     if (this.listaPermiso.size() > 0) {
/* 252 */       SegRol[] selectTemasAux = new SegRol[this.listaPermiso.size()];
/* 253 */       int i = 0;
/* 254 */       for (SegPermiso aux : this.listaPermiso) {
/* 255 */         selectTemasAux[i] = aux.getIdRol();
/* 256 */         i++;
/*     */       }
/* 258 */       this.selectRol = selectTemasAux;
/*     */     }
/* 260 */     this.segRolData = new UsuariosBean.SegRolDataModel(this, this.listaSegRol);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void registrarUsuario()
/*     */   {
/* 267 */     this.strTitulo = "Registrar";
/* 268 */     this.segUsuario = new SegUsuario();
/* 269 */     this.segUsuario.setIdCooperativa(new Cooperativa());
/*     */     
/* 271 */     this.selectPantallas = Util.pantallaInicial();
/* 272 */     this.selectAcceso = Util.accesoSistema();
/* 273 */     this.blnDesplegarRoles = false;
/*     */     
/* 275 */     this.blnCamposEditables = false;
/*     */   }
/*     */   
/*     */   public void modificarUsuario() {
/* 279 */     this.strTitulo = "Modificar";
/*     */     
/* 281 */     this.selectPantallas.clear();
/* 282 */     this.selectAcceso.clear();
/* 283 */     this.selectPantallas = Util.pantallaInicial();
/* 284 */     this.selectAcceso = Util.accesoSistema();
/* 285 */     this.blnDesplegarRoles = false;
/* 286 */     this.blnCamposEditables = true;
/*     */   }
/*     */   
/*     */   public void eliminarUsuarioSeleccionado() {
/* 290 */     this.blnDesplegarRoles = false;
/*     */   }
/*     */   
/*     */   public void guardarUsuario(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 297 */       DateTime fechaIni = new DateTime(this.segUsuario.getFechainiciovigencia());
/* 298 */       DateTime fechaFin = new DateTime(this.segUsuario.getFechafinvigencia());
/*     */       
/* 300 */       if (fechaIni.isBefore(fechaFin))
/*     */       {
/* 302 */         if ((this.segUsuario.getId() != null) && (this.segUsuario.getId().intValue() > 0)) {
/* 303 */           this.segUsuarioMLBeanLocal.guardarUsuario(this.segUsuario);
/*     */         } else {
/* 305 */           this.segUsuario.setFechacreacion(new Date());
/* 306 */           this.segUsuario.setConformidad("0");
/* 307 */           this.segUsuario.setTipousuario("2");
/* 308 */           this.segUsuario.setContrasena(DigestUtils.sha256Hex(this.segUsuario.getContrasena()));
/* 309 */           this.segUsuarioMLBeanLocal.guardarUsuario(this.segUsuario);
/*     */         }
/* 311 */         Util.CrearMsgGuardado();
/* 312 */         listarUsuariosTabla();
/* 313 */         this.blnBloquearBoton = true;
/*     */       } else {
/* 315 */         Util.CrearMsgERROR("Inicio de vigencia debe ser menor a fecha fin ", "ERROR", false);
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 320 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void guardarCambioPass(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 329 */       this.segUsuario.setContrasena(DigestUtils.sha256Hex(this.segUsuario.getContrasena()));
/* 330 */       this.segUsuarioMLBeanLocal.guardarCambioPass(this.segUsuario);
/* 331 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 335 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void cambioPass(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 343 */       if (this.segUsuarioSession.getContrasena().equals(DigestUtils.sha256Hex(this.contrasenaActual))) {
/* 344 */         this.segUsuarioSession.setContrasena(DigestUtils.sha256Hex(this.nuevoPass));
/* 345 */         this.segUsuarioMLBeanLocal.guardarCambioPass(this.segUsuarioSession);
/* 346 */         Util.CrearMsgGuardado();
/*     */       } else {
/* 348 */         Util.CrearMsgERROR("La contraseña actual no es válida", "ERROR", false);
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 352 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void eliminarUsuario(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 362 */       this.segUsuarioMLBeanLocal.eliminarUsuario(this.segUsuario);
/* 363 */       listarUsuariosTabla();
/* 364 */       Util.CrearMsgEliminado();
/* 365 */       this.blnBloquearBoton = true;
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 369 */       e.printStackTrace();
/*     */     }
/*     */   }
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
/*     */   public StreamedContent getReporte()
/*     */     throws SQLException
/*     */   {
/* 389 */     Map<String, Object> parametros = new HashMap();
/* 390 */     parametros.put("P_TITLE_REPORT", "ADMINISTRACION DE USUARIOS");
/* 391 */     return this.mr.runReporte("usuarios", "usuarios", parametros, "pdf", "usuarios", Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public List<SegUsuario> getListaSegUsuario()
/*     */   {
/* 396 */     return this.listaSegUsuario;
/*     */   }
/*     */   
/*     */   public void setListaSegUsuario(List<SegUsuario> listaSegUsuario) {
/* 400 */     this.listaSegUsuario = listaSegUsuario;
/*     */   }
/*     */   
/*     */   public SegUsuario getSegUsuario() {
/* 404 */     return this.segUsuario;
/*     */   }
/*     */   
/*     */   public void setSegUsuario(SegUsuario segUsuario) {
/* 408 */     this.segUsuario = segUsuario;
/*     */   }
/*     */   
/*     */   public List<SegUsuario> getListaSegUsuarioFiltro() {
/* 412 */     return this.listaSegUsuarioFiltro;
/*     */   }
/*     */   
/*     */   public void setListaSegUsuarioFiltro(List<SegUsuario> listaSegUsuarioFiltro) {
/* 416 */     this.listaSegUsuarioFiltro = listaSegUsuarioFiltro;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 420 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 424 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPersonas() {
/* 428 */     return this.selectPersonas;
/*     */   }
/*     */   
/*     */   public void setSelectPersonas(List<SelectItem> selectPersonas) {
/* 432 */     this.selectPersonas = selectPersonas;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPantallas() {
/* 436 */     return this.selectPantallas;
/*     */   }
/*     */   
/*     */   public void setSelectPantallas(List<SelectItem> selectPantallas) {
/* 440 */     this.selectPantallas = selectPantallas;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectAcceso() {
/* 444 */     return this.selectAcceso;
/*     */   }
/*     */   
/*     */   public void setSelectAcceso(List<SelectItem> selectAcceso) {
/* 448 */     this.selectAcceso = selectAcceso;
/*     */   }
/*     */   
/*     */   public boolean isBlnBloquearBoton() {
/* 452 */     return this.blnBloquearBoton;
/*     */   }
/*     */   
/*     */   public void setBlnBloquearBoton(boolean blnBloquearBoton) {
/* 456 */     this.blnBloquearBoton = blnBloquearBoton;
/*     */   }
/*     */   
/*     */   public UsuariosBean.SegRolDataModel getSegRolData() {
/* 460 */     return this.segRolData;
/*     */   }
/*     */   
/*     */   public void setSegRolData(UsuariosBean.SegRolDataModel segRolData) {
/* 464 */     this.segRolData = segRolData;
/*     */   }
/*     */   
/*     */   public SegRol[] getSelectRol() {
/* 468 */     return this.selectRol;
/*     */   }
/*     */   
/*     */   public void setSelectRol(SegRol[] selectRol) {
/* 472 */     this.selectRol = selectRol;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBlnDesplegarRoles()
/*     */   {
/* 504 */     return this.blnDesplegarRoles;
/*     */   }
/*     */   
/*     */   public void setBlnDesplegarRoles(boolean blnDesplegarRoles) {
/* 508 */     this.blnDesplegarRoles = blnDesplegarRoles;
/*     */   }
/*     */   
/*     */   public Date getFechaActual() {
/* 512 */     return this.fechaActual;
/*     */   }
/*     */   
/*     */   public void setFechaActual(Date fechaActual) {
/* 516 */     this.fechaActual = fechaActual;
/*     */   }
/*     */   
/*     */   public String getContrasenaActual() {
/* 520 */     return this.contrasenaActual;
/*     */   }
/*     */   
/*     */   public void setContrasenaActual(String contrasenaActual) {
/* 524 */     this.contrasenaActual = contrasenaActual;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNuevoPass()
/*     */   {
/* 536 */     return this.nuevoPass;
/*     */   }
/*     */   
/*     */   public void setNuevoPass(String nuevoPass) {
/* 540 */     this.nuevoPass = nuevoPass;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCargo() {
/* 544 */     return this.selectCargo;
/*     */   }
/*     */   
/*     */   public void setSelectCargo(List<SelectItem> selectCargo) {
/* 548 */     this.selectCargo = selectCargo;
/*     */   }
/*     */   
/*     */   public boolean isBlnCamposEditables() {
/* 552 */     return this.blnCamposEditables;
/*     */   }
/*     */   
/*     */   public void setBlnCamposEditables(boolean blnCamposEditables) {
/* 556 */     this.blnCamposEditables = blnCamposEditables;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCooperativa() {
/* 560 */     return this.selectCooperativa;
/*     */   }
/*     */   
/*     */   public void setSelectCooperativa(List<SelectItem> selectCooperativa) {
/* 564 */     this.selectCooperativa = selectCooperativa;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\UsuariosBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */