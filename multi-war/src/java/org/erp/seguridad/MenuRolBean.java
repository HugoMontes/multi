/*     */ package org.erp.seguridad;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.faces.event.AjaxBehaviorEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.SegMenu;
/*     */ import org.erp.entities.SegMenurol;
/*     */ import org.erp.entities.SegRol;
/*     */ import org.erp.seg.ML.seg.SegMenuRolMLBeanLocal;
/*     */ import org.erp.seg.QL.seg.SegMenuRolQLBeanLocal;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.Util;
/*     */ import org.primefaces.component.selectonemenu.SelectOneMenu;
/*     */ import org.primefaces.event.NodeSelectEvent;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ import org.primefaces.model.DefaultTreeNode;
/*     */ import org.primefaces.model.TreeNode;
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
/*     */ @ManagedBean(name="menuRolBean")
/*     */ @ViewScoped
/*     */ public class MenuRolBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   SegMenuRolQLBeanLocal segMenuRolQLBeanLocal;
/*     */   @Inject
/*     */   SegMenuRolMLBeanLocal segMenuRolMLBeanLocal;
/*     */   private TreeNode root;
/*     */   private TreeNode selectedNode;
/*     */   private List<SegMenu> listaSegMenu;
/*     */   private List<SegMenurol> listaSegMenuRol;
/*     */   private SegMenurol segMenuRol;
/*     */   private String strTitulo;
/*     */   private List<SelectItem> selectMenuRol;
/*     */   private String menuSeleccionado;
/*     */   private SegRol segRol;
/*     */   private SegMenu segMenu;
/*     */   private SegRol segRoles;
/*     */   private boolean blnBtnEliminar;
/*     */   private boolean blnBtnRegistrar;
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
/*     */     try
/*     */     {
/*     */       
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  82 */       e.printStackTrace();
/*     */     }
/*     */     
/*  85 */     this.listaSegMenu = new ArrayList();
/*  86 */     this.listaSegMenuRol = new ArrayList();
/*  87 */     this.segMenuRol = new SegMenurol();
/*  88 */     this.strTitulo = "";
/*  89 */     this.selectMenuRol = new ArrayList();
/*  90 */     this.menuSeleccionado = "";
/*     */     
/*  92 */     this.segRol = new SegRol();
/*  93 */     this.segMenu = new SegMenu();
/*  94 */     this.segRoles = new SegRol();
/*  95 */     cargarMenu();
/*     */     
/*  97 */     this.segMenuRol.setIdMenu(this.segMenu);
/*  98 */     this.segMenuRol.setIdRol(this.segRol);
/*     */     
/* 100 */     this.blnBtnEliminar = true;
/* 101 */     this.blnBtnRegistrar = true;
/*     */   }
/*     */   
/*     */   public void registrarMenuRol()
/*     */   {
/* 106 */     this.strTitulo = "Registrar";
/* 107 */     this.segMenuRol = new SegMenurol();
/* 108 */     this.segRol = new SegRol();
/*     */     
/* 110 */     this.segMenuRol.setIdMenu(this.segMenu);
/* 111 */     this.segMenuRol.setIdRol(this.segRol);
/* 112 */     this.segRoles = new SegRol();
/*     */     
/* 114 */     cargarListaMenuRol();
/*     */     
/* 116 */     this.blnBtnEliminar = true;
/*     */   }
/*     */   
/*     */   public void cargarMenu()
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.listaSegMenu = new ArrayList();
/* 124 */       this.listaSegMenu = this.segMenuRolQLBeanLocal.listaMenu();
/*     */     }
/*     */     catch (AdminException e) {
/* 127 */       e.printStackTrace();
/*     */     }
/*     */     
/* 130 */     this.root = new DefaultTreeNode("Root", null);
/* 131 */     for (SegMenu menu : this.listaSegMenu) {
/* 132 */       if (menu.getIdMenu() == null) {
/* 133 */         TreeNode node0 = new DefaultTreeNode(menu.getNombre(), this.root);
/*     */         
/* 135 */         for (SegMenu subMenu : this.listaSegMenu) {
/* 136 */           if ((subMenu.getIdMenu() != null) && 
/* 137 */             (menu.getId() == subMenu.getIdMenu().getId())) {
/* 138 */             DefaultTreeNode localDefaultTreeNode = new DefaultTreeNode(
/* 139 */               subMenu.getNombre(), node0);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onNodeSelect(NodeSelectEvent event)
/*     */   {
/* 148 */     listarRolesDelMenu(event.getTreeNode().getData().toString());
/* 149 */     if (!event.getTreeNode().getParent().getData().equals("Root"))
/*     */     {
/* 151 */       this.menuSeleccionado = event.getTreeNode().getData().toString();
/*     */       
/* 153 */       this.blnBtnRegistrar = false;
/* 154 */       this.blnBtnEliminar = true;
/*     */       try
/*     */       {
/* 157 */         this.segMenu = this.segMenuRolQLBeanLocal.datosMenu(this.menuSeleccionado);
/*     */       }
/*     */       catch (AdminException e)
/*     */       {
/* 161 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 166 */       this.blnBtnRegistrar = true;
/* 167 */       this.blnBtnEliminar = true;
/* 168 */       this.menuSeleccionado = "";
/*     */     }
/*     */   }
/*     */   
/*     */   public void onRowSelect(SelectEvent event)
/*     */   {
/* 174 */     this.blnBtnEliminar = false;
/*     */   }
/*     */   
/*     */   public void listarRolesDelMenu(String menu) {
/* 178 */     this.listaSegMenuRol = new ArrayList();
/*     */     try {
/* 180 */       this.listaSegMenuRol = this.segMenuRolQLBeanLocal.listaMenuRol(menu);
/*     */     } catch (AdminException e) {
/* 182 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void cargarListaMenuRol() {
/*     */     try {
/* 188 */       this.selectMenuRol.clear();
/* 189 */       List<SegRol> listaRoles = this.segMenuRolQLBeanLocal
/* 190 */         .listaRol(this.menuSeleccionado);
/* 191 */       this.selectMenuRol.add(new SelectItem(null, "Seleccione .."));
/* 192 */       for (SegRol lista : listaRoles) {
/* 193 */         this.selectMenuRol.add(new SelectItem(lista.getId(), lista
/* 194 */           .getNombre()));
/*     */       }
/*     */     } catch (AdminException e) {
/* 197 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void guardarMenuRol(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 206 */       this.segMenuRol.setIdRol(this.segRol);
/* 207 */       this.segMenuRol.setIdMenu(this.segMenu);
/*     */       
/* 209 */       this.segMenuRolMLBeanLocal.guardarMenuRol(this.segMenuRol);
/* 210 */       listarRolesDelMenu(this.menuSeleccionado);
/* 211 */       Util.CrearMsgGuardado();
/*     */       
/* 213 */       this.blnBtnEliminar = true;
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 217 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarMenuRol(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 225 */       this.segMenuRolMLBeanLocal.eliminarMenuRol(this.segMenuRol);
/* 226 */       listarRolesDelMenu(this.menuSeleccionado);
/* 227 */       Util.CrearMsgEliminado();
/*     */       
/* 229 */       this.blnBtnEliminar = true;
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 233 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eventChangeSelect(AjaxBehaviorEvent event)
/*     */   {
/* 239 */     SelectOneMenu sel = (SelectOneMenu)event.getComponent();
/* 240 */     Integer selected = (Integer)sel.getValue();
/* 241 */     this.segRoles = new SegRol();
/*     */     try {
/* 243 */       this.segRoles = this.segMenuRolQLBeanLocal.datosRol(selected);
/*     */     }
/*     */     catch (AdminException e) {
/* 246 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public TreeNode getRoot() {
/* 251 */     return this.root;
/*     */   }
/*     */   
/*     */   public void setRoot(TreeNode root) {
/* 255 */     this.root = root;
/*     */   }
/*     */   
/*     */   public TreeNode getSelectedNode() {
/* 259 */     return this.selectedNode;
/*     */   }
/*     */   
/*     */   public void setSelectedNode(TreeNode selectedNode) {
/* 263 */     this.selectedNode = selectedNode;
/*     */   }
/*     */   
/*     */   public List<SegMenurol> getListaSegMenuRol() {
/* 267 */     return this.listaSegMenuRol;
/*     */   }
/*     */   
/*     */   public void setListaSegMenuRol(List<SegMenurol> listaSegMenuRol) {
/* 271 */     this.listaSegMenuRol = listaSegMenuRol;
/*     */   }
/*     */   
/*     */   public SegMenurol getSegMenuRol() {
/* 275 */     return this.segMenuRol;
/*     */   }
/*     */   
/*     */   public void setSegMenuRol(SegMenurol segMenuRol) {
/* 279 */     this.segMenuRol = segMenuRol;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 283 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 287 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMenuRol() {
/* 291 */     return this.selectMenuRol;
/*     */   }
/*     */   
/*     */   public void setSelectMenuRol(List<SelectItem> selectMenuRol) {
/* 295 */     this.selectMenuRol = selectMenuRol;
/*     */   }
/*     */   
/*     */   public SegRol getSegRoles() {
/* 299 */     return this.segRoles;
/*     */   }
/*     */   
/*     */   public void setSegRoles(SegRol segRoles) {
/* 303 */     this.segRoles = segRoles;
/*     */   }
/*     */   
/*     */   public boolean isBlnBtnEliminar() {
/* 307 */     return this.blnBtnEliminar;
/*     */   }
/*     */   
/*     */   public void setBlnBtnEliminar(boolean blnBtnEliminar) {
/* 311 */     this.blnBtnEliminar = blnBtnEliminar;
/*     */   }
/*     */   
/*     */   public boolean isBlnBtnRegistrar() {
/* 315 */     return this.blnBtnRegistrar;
/*     */   }
/*     */   
/*     */   public void setBlnBtnRegistrar(boolean blnBtnRegistrar) {
/* 319 */     this.blnBtnRegistrar = blnBtnRegistrar;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\MenuRolBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */