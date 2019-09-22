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
/*     */ @ManagedBean(name="listadoOperador")
/*     */ @ViewScoped
/*     */ public class ListadoOperador
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
/*     */   private List<SelectItem> selectActorProductivo;
/*     */   private SegUsuario segUsuarioSession;
/*  58 */   private Map<Integer, String> mapActorProductivo = null;
/*     */   
/*     */   private List<ParameterTable> parameterTable;
/*     */   
/*     */   private OperadoresTO operadoresTO;
/*     */   
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */ 
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  70 */     this.accesoBean = accesoBean;
/*  71 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  75 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/*  84 */       e.printStackTrace();
/*  85 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*  87 */     cargarParametros();
/*  88 */     this.operadoresTO = new OperadoresTO();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/*  97 */       this.selectActorProductivo = new ArrayList();
/*  98 */       this.selectActorProductivo.add(new SelectItem(Integer.valueOf(0), "Todos..."));
/*  99 */       this.mapActorProductivo = new HashMap();
/* 100 */       this.parameterTable = new ArrayList();
/* 101 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ACTOR_PRODUCTIVO.getCode());
/* 102 */       for (ParameterTable param : this.parameterTable) {
/* 103 */         this.selectActorProductivo.add(new SelectItem(param.getId(), param.getNombre()));
/* 104 */         this.mapActorProductivo.put(param.getId(), param.getNombre());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 109 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public StreamedContent getReporte() throws SQLException
/*     */   {
/* 115 */     Map<String, Object> parametros = new HashMap();
/* 116 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*     */     
/* 118 */     String query = "";
/* 119 */     if (!this.operadoresTO.getIndActorProductivo().equals(Integer.valueOf(0))) {
/* 120 */       query = "and c.ind_actor_productivo = " + this.operadoresTO.getIndActorProductivo();
/*     */     } else {
/* 122 */       query = "";
/*     */     }
/* 124 */     parametros.put("query", query);
/* 125 */     return this.mr.runReporte("admin", "listadoOperadores", parametros, "pdf", "listadoOperadores", Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectActorProductivo()
/*     */   {
/* 130 */     return this.selectActorProductivo;
/*     */   }
/*     */   
/*     */   public void setSelectActorProductivo(List<SelectItem> selectActorProductivo) {
/* 134 */     this.selectActorProductivo = selectActorProductivo;
/*     */   }
/*     */   
/*     */   public OperadoresTO getOperadoresTO() {
/* 138 */     return this.operadoresTO;
/*     */   }
/*     */   
/*     */   public void setOperadoresTO(OperadoresTO operadoresTO) {
/* 142 */     this.operadoresTO = operadoresTO;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\reporte\ListadoOperador.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */