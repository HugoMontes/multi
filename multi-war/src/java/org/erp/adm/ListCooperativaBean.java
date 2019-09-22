/*     */ package org.erp.adm;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MostrarReporte;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
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
/*     */ @ManagedBean(name="listCooperativaBean")
/*     */ @ViewScoped
/*     */ public class ListCooperativaBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   @Inject
/*     */   CooperativaEJBBeanLocal cooperativaEJBBeanLocal;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   private String strTitulo;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String pagina;
/*     */   private List<Cooperativa> listCooperativa;
/*     */   private List<Cooperativa> listCooperativaFilter;
/*     */   private Cooperativa cooperativa;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  89 */     this.accesoBean = accesoBean;
/*  90 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  94 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
///*     */     try {
///*     */       
///*     */     }
///*     */     catch (IOException e) {
///* 103 */       e.printStackTrace();
///* 104 */       this.logger.info("Error en la validación de sesión");
///*     */     }
/*     */     
/* 107 */     listarCooperativa();
/*     */     
/*     */ 
/* 110 */     this.strTitulo = "";
/* 111 */     this.pagina = "/pages/adm/registerCooperative.jsf";
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarCooperativa()
/*     */   {
/* 117 */     this.cooperativa = new Cooperativa();
/* 118 */     this.listCooperativa = new ArrayList();
/*     */     try
/*     */     {
/* 121 */       this.listCooperativa = this.cooperativaEJBBeanLocal.listadoCooperativa();
/*     */     } catch (AdminException e) {
/* 123 */       Util.CrearMsgErrorObtenerInfo();
/* 124 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void renovarCooperativa()
/*     */   {
/* 130 */     this.strTitulo = "Ampliar fecha de validez";
/*     */   }
/*     */   
/*     */   public void registerCooperativa() {
/* 134 */     JSFUtilities.setHttpSessionAttribute("cooperativaId", Integer.valueOf(0));
/* 135 */     JSFUtilities.direccionarPagina(this.pagina);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void guardarValidez()
/*     */   {
/*     */     try
/*     */     {
/* 146 */       this.cooperativa.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 147 */       this.cooperativaEJBBeanLocal.guardarCooperativa(this.cooperativa);
/*     */       
/* 149 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 152 */       Util.CrearMsgErrorRegistro();
/* 153 */       e.printStackTrace();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StreamedContent getReporteFormCien()
/*     */     throws SQLException
/*     */   {
/* 254 */     Map<String, Object> parametros = new HashMap();
/* 255 */     parametros.put("idCooperativa", this.cooperativa.getId());
/* 256 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*     */     
/* 258 */     return this.mr.runReporte("admin", "comFormulario", parametros, "pdf", "comFormulario", Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteCertificado() throws SQLException
/*     */   {
/* 263 */     Map<String, Object> parametros = new HashMap();
/* 264 */     parametros.put("idCooperativa", this.cooperativa.getId());
/* 265 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*     */     
/* 267 */     return this.mr.runReporte("admin", "comCertificado", parametros, "pdf", "comCertificado", Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void modifyCooperative() {
/* 271 */     JSFUtilities.setHttpSessionAttribute("cooperativaId", this.cooperativa);
/* 272 */     JSFUtilities.direccionarPagina(this.pagina);
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
/*     */   public void onRowSelectDetalle(SelectEvent event) {}
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
/*     */   public String getStrTitulo()
/*     */   {
/* 312 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 316 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<Cooperativa> getListCooperativa() {
/* 320 */     return this.listCooperativa;
/*     */   }
/*     */   
/*     */   public void setListCooperativa(List<Cooperativa> listCooperativa) {
/* 324 */     this.listCooperativa = listCooperativa;
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 328 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 332 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public List<Cooperativa> getListCooperativaFilter() {
/* 336 */     return this.listCooperativaFilter;
/*     */   }
/*     */   
/*     */   public void setListCooperativaFilter(List<Cooperativa> listCooperativaFilter) {
/* 340 */     this.listCooperativaFilter = listCooperativaFilter;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\adm\ListCooperativaBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */