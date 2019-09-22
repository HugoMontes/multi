/*     */ package org.erp.reporte;
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
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.adm.CooperativaEJBBeanLocal;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.to.OperadoresTO;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
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
/*     */ @ManagedBean(name="transporteBean")
/*     */ @ViewScoped
/*     */ public class TransporteBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   CooperativaEJBBeanLocal cooperativaEJBBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   private List<SelectItem> selectCooperativa;
/*     */   private SegUsuario segUsuarioSession;
/*  64 */   private Map<Integer, String> mapActorProductivo = null;
/*     */   
/*     */   private List<ParameterTable> parameterTable;
/*     */   
/*     */   private OperadoresTO operadoresTO;
/*     */   
/*     */   private List<SelectItem> selectActorProductivo;
/*     */   
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  77 */     this.accesoBean = accesoBean;
/*  78 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  82 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/*  91 */       e.printStackTrace();
/*  92 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*  94 */     cargarParametros();
/*  95 */     this.operadoresTO = new OperadoresTO();
/*     */   }
/*     */   
/*     */ 
/*     */   public void seleccionOperador()
/*     */   {
/* 101 */     if ((this.operadoresTO.getIdCooperativa().intValue() == 0) || (this.operadoresTO.getIdCooperativa() == null)) {
/* 102 */       this.operadoresTO.setIndActorProductivo(Integer.valueOf(0));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 113 */       List<Cooperativa> listCooperativa = new ArrayList();
/* 114 */       this.selectCooperativa = new ArrayList();
/* 115 */       this.selectCooperativa.add(new SelectItem(Integer.valueOf(0), "Todos..."));
/* 116 */       listCooperativa = this.cooperativaEJBBeanLocal.listadoCooperativa();
/* 117 */       for (Cooperativa cope : listCooperativa) {
/* 118 */         this.selectCooperativa.add(new SelectItem(cope.getId(), cope.getRazonSocial()));
/*     */       }
/*     */       
/*     */ 
/* 122 */       this.selectActorProductivo = new ArrayList();
/* 123 */       this.selectActorProductivo.add(new SelectItem(Integer.valueOf(0), "Todos..."));
/* 124 */       this.parameterTable = new ArrayList();
/* 125 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ACTOR_PRODUCTIVO.getCode());
/* 126 */       for (ParameterTable param : this.parameterTable) {
/* 127 */         this.selectActorProductivo.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 131 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public StreamedContent getReporte() throws SQLException
/*     */   {
/* 137 */     Map<String, Object> parametros = new HashMap();
/* 138 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*     */     
/* 140 */     if ((this.operadoresTO.getIdCooperativa() != null) && (!this.operadoresTO.getIdCooperativa().equals(Integer.valueOf(0)))) {
/* 141 */       parametros.put("query", "and c.id = " + this.operadoresTO.getIdCooperativa());
/*     */     } else {
/* 143 */       parametros.put("query", "");
/*     */     }
/*     */     
/* 146 */     if ((this.operadoresTO.getIndActorProductivo() != null) && (!this.operadoresTO.getIndActorProductivo().equals(Integer.valueOf(0)))) {
/* 147 */       parametros.put("queryActor", "and c.ind_actor_productivo = " + this.operadoresTO.getIndActorProductivo());
/*     */     } else {
/* 149 */       parametros.put("queryActor", "");
/*     */     }
/*     */     
/* 152 */     parametros.put("desde", this.operadoresTO.getDesde());
/* 153 */     parametros.put("hasta", this.operadoresTO.getHasta());
/*     */     
/* 155 */     if (this.operadoresTO.getIndTipoTransporte().equals(Integer.valueOf(1)))
/* 156 */       return this.mr.runReporte("admin", "listadoTransporteInterno", parametros, "pdf", "listadoTransporteInterno", Integer.valueOf(0));
/* 157 */     if (this.operadoresTO.getIndTipoTransporte().equals(Integer.valueOf(2))) {
/* 158 */       return this.mr.runReporte("admin", "listadoTransporteExterno", parametros, "pdf", "listadoTransporteExterno", Integer.valueOf(0));
/*     */     }
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public OperadoresTO getOperadoresTO()
/*     */   {
/* 165 */     return this.operadoresTO;
/*     */   }
/*     */   
/*     */   public void setOperadoresTO(OperadoresTO operadoresTO) {
/* 169 */     this.operadoresTO = operadoresTO;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCooperativa() {
/* 173 */     return this.selectCooperativa;
/*     */   }
/*     */   
/*     */   public void setSelectCooperativa(List<SelectItem> selectCooperativa) {
/* 177 */     this.selectCooperativa = selectCooperativa;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectActorProductivo() {
/* 181 */     return this.selectActorProductivo;
/*     */   }
/*     */   
/*     */   public void setSelectActorProductivo(List<SelectItem> selectActorProductivo) {
/* 185 */     this.selectActorProductivo = selectActorProductivo;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\reporte\TransporteBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */