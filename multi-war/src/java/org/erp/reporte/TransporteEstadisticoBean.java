/*     */ package org.erp.reporte;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.to.OperadoresTO;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MostrarReporte;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
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
/*     */ @ManagedBean(name="transporteEstadisticoBean")
/*     */ @ViewScoped
/*     */ public class TransporteEstadisticoBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private List<ParameterTable> parameterTable;
/*     */   private OperadoresTO operadoresTO;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  69 */     this.accesoBean = accesoBean;
/*  70 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  74 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/*  83 */       e.printStackTrace();
/*  84 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*  86 */     this.operadoresTO = new OperadoresTO();
/*  87 */     this.operadoresTO.setDesde(new Date());
/*  88 */     this.operadoresTO.setHasta(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */   public StreamedContent getReporte()
/*     */     throws SQLException
/*     */   {
/*  95 */     Map<String, Object> parametros = new HashMap();
/*  96 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*  97 */     parametros.put("desde", this.operadoresTO.getDesde());
/*  98 */     parametros.put("hasta", this.operadoresTO.getHasta());
/*     */     
/* 100 */     if (this.operadoresTO.getIndTipoTransporte().equals(Integer.valueOf(1)))
/* 101 */       return this.mr.runReporte("admin", "estadisticoInterno", parametros, "pdf", "estadisticoInterno", Integer.valueOf(0));
/* 102 */     if (this.operadoresTO.getIndTipoTransporte().equals(Integer.valueOf(2))) {
/* 103 */       return this.mr.runReporte("admin", "estadisticoExterno", parametros, "pdf", "estadisticoExterno", Integer.valueOf(0));
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */   
/*     */   public OperadoresTO getOperadoresTO()
/*     */   {
/* 110 */     return this.operadoresTO;
/*     */   }
/*     */   
/*     */   public void setOperadoresTO(OperadoresTO operadoresTO) {
/* 114 */     this.operadoresTO = operadoresTO;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\reporte\TransporteEstadisticoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */