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
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.SegRol;
/*     */ import org.erp.seg.ML.seg.SegRolMLBeanLocal;
/*     */ import org.erp.seg.QL.seg.SegRolQLBeanLocal;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.Util;
/*     */ import org.primefaces.event.SelectEvent;
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
/*     */ @ManagedBean(name="rolesBean")
/*     */ @ViewScoped
/*     */ public class RolBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   SegRolQLBeanLocal segRolQLBeanLocal;
/*     */   @Inject
/*     */   SegRolMLBeanLocal segRolMLBeanLocal;
/*     */   private List<SegRol> listaSegRol;
/*     */   private List<SegRol> listaSegRolFiltro;
/*     */   private SegRol segRol;
/*     */   private String strTitulo;
/*     */   private boolean blnBloquearBoton;
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
///*     */     try
///*     */     {
///*     */       
///*     */     }
///*     */     catch (IOException e)
///*     */     {
///*  65 */       e.printStackTrace();
///*     */     }
/*     */     
/*  68 */     listarRolesTabla();
/*  69 */     this.segRol = new SegRol();
/*  70 */     this.strTitulo = "";
/*  71 */     this.blnBloquearBoton = true;
/*     */   }
/*     */   
/*     */   public void listarRolesTabla()
/*     */   {
/*  76 */     this.listaSegRol = new ArrayList();
/*     */     try {
/*  78 */       this.listaSegRol = this.segRolQLBeanLocal.listaRoles();
/*  79 */       this.blnBloquearBoton = true;
/*     */     } catch (AdminException e) {
/*  81 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void onRowSelect(SelectEvent event) {
/*  86 */     this.blnBloquearBoton = false;
/*     */   }
/*     */   
/*     */   public void registrarRol() {
/*  90 */     this.strTitulo = "Registrar";
/*  91 */     this.segRol = new SegRol();
/*     */   }
/*     */   
/*     */   public void modificarRol() {
/*  95 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void guardarRol(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 102 */       this.segRolMLBeanLocal.guardarRol(this.segRol);
/* 103 */       listarRolesTabla();
/* 104 */       Util.CrearMsgGuardado();
/* 105 */       this.blnBloquearBoton = true;
/*     */ 
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 110 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarRol(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 118 */       this.segRolMLBeanLocal.eliminarRol(this.segRol);
/* 119 */       listarRolesTabla();
/* 120 */       Util.CrearMsgEliminado();
/* 121 */       this.blnBloquearBoton = true;
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 125 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public List<SegRol> getListaSegRol() {
/* 130 */     return this.listaSegRol;
/*     */   }
/*     */   
/*     */   public void setListaSegRol(List<SegRol> listaSegRol) {
/* 134 */     this.listaSegRol = listaSegRol;
/*     */   }
/*     */   
/*     */   public List<SegRol> getListaSegRolFiltro() {
/* 138 */     return this.listaSegRolFiltro;
/*     */   }
/*     */   
/*     */   public void setListaSegRolFiltro(List<SegRol> listaSegRolFiltro) {
/* 142 */     this.listaSegRolFiltro = listaSegRolFiltro;
/*     */   }
/*     */   
/*     */   public SegRol getSegRol() {
/* 146 */     return this.segRol;
/*     */   }
/*     */   
/*     */   public void setSegRol(SegRol segRol) {
/* 150 */     this.segRol = segRol;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 154 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 158 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public boolean isBlnBloquearBoton() {
/* 162 */     return this.blnBloquearBoton;
/*     */   }
/*     */   
/*     */   public void setBlnBloquearBoton(boolean blnBloquearBoton) {
/* 166 */     this.blnBloquearBoton = blnBloquearBoton;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\RolBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */