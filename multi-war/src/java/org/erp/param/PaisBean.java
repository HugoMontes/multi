/*     */ package org.erp.param;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.Pais;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.param.ML.CotizacionesEjbBeanLocal;
/*     */ import org.erp.param.ML.TipoCambioEjbBeanLocal;
/*     */ import org.erp.seguridad.AccesoBean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="paisBean")
/*     */ @ViewScoped
/*     */ public class PaisBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   CotizacionesEjbBeanLocal cotizacionesEjbBeanLocal;
/*     */   @Inject
/*     */   TipoCambioEjbBeanLocal tipoCambioEjbBeanLocal;
/*     */   private String strTitulo;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private Pais pais;
/*     */   private List<Pais> listPais;
/*     */   private List<Pais> listPaisFilter;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  67 */     this.accesoBean = accesoBean;
/*  68 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  72 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/*  81 */       e.printStackTrace();
/*     */     }
/*     */     
/*  84 */     listarPais();
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarPais()
/*     */   {
/*  90 */     this.pais = new Pais();
/*  91 */     this.listPais = new ArrayList();
/*     */     try
/*     */     {
/*  94 */       this.listPais = this.tipoCambioEjbBeanLocal.listaPais();
/*     */     } catch (AdminException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void registrarPais()
/*     */   {
/* 103 */     this.strTitulo = "Registrar Tipo Cambio";
/* 104 */     this.pais = new Pais();
/*     */   }
/*     */   
/*     */   public void guardarMaster(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 111 */       this.tipoCambioEjbBeanLocal.guardarPais(this.pais);
/* 112 */       Util.CrearMsgGuardado();
/* 113 */       listarPais();
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 117 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void modificarPais()
/*     */   {
/* 123 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarDescripcion() {
/* 127 */     this.strTitulo = "Modificar Descripcion de Mineral";
/*     */   }
/*     */   
/*     */   public void eliminarMaster(ActionEvent event)
/*     */   {
/*     */     try {
/* 133 */       this.tipoCambioEjbBeanLocal.eliminarPais(this.pais);
/* 134 */       listarPais();
/* 135 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 138 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public String descripcionPeriodo(String mes)
/*     */   {
/* 144 */     return Util.descripcionPeriodo(mes);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void onRowSelectDescripcion(SelectEvent event) {}
/*     */   
/*     */   public String getStrTitulo()
/*     */   {
/* 155 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 159 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public Pais getPais() {
/* 163 */     return this.pais;
/*     */   }
/*     */   
/*     */   public void setPais(Pais pais) {
/* 167 */     this.pais = pais;
/*     */   }
/*     */   
/*     */   public List<Pais> getListPais() {
/* 171 */     return this.listPais;
/*     */   }
/*     */   
/*     */   public void setListPais(List<Pais> listPais) {
/* 175 */     this.listPais = listPais;
/*     */   }
/*     */   
/*     */   public List<Pais> getListPaisFilter() {
/* 179 */     return this.listPaisFilter;
/*     */   }
/*     */   
/*     */   public void setListPaisFilter(List<Pais> listPaisFilter) {
/* 183 */     this.listPaisFilter = listPaisFilter;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\param\PaisBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */