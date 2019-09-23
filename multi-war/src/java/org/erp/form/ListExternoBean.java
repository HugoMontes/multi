/*     */ package org.erp.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
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
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.entities.TransporteExterno;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.MostrarReporte;
/*     */ import org.erp.util.ParameterTableType;
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
/*     */ @ManagedBean(name="listExternoBean")
/*     */ @ViewScoped
/*     */ public class ListExternoBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   ExternoEjbBeanLocal externoEjbBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   private String strTitulo;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String pagina;
/*  74 */   private Map<Integer, String> mapCiExtendido = null;
/*  75 */   private Map<Integer, String> mapPresentacion = null;
/*     */   private List<ParameterTable> parameterTable;
/*  77 */   private Map<Integer, String> mapEstadoRegistroForm = null;
/*     */   
/*     */   private List<TransporteExterno> listTransExterno;
/*     */   private List<TransporteExterno> listTransExternoFilter;
/*     */   private TransporteExterno transporteExterno;
/*     */   private Cooperativa cooperativa;
/*  83 */   private Map<Integer, String> mapCargo = null;
/*     */   
/*  85 */   private Map<Integer, String> mapTipoExportacion = null;
/*     */   
/*     */ 
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  95 */     this.accesoBean = accesoBean;
/*  96 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*  97 */     if (this.segUsuarioSession.getIdCooperativa() != null) {
/*  98 */       this.cooperativa = this.segUsuarioSession.getIdCooperativa();
/*     */     } else {
/* 100 */       this.cooperativa = new Cooperativa();
/*     */     }
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/* 105 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
///*     */     try {
///*     */       
///*     */     }
///*     */     catch (IOException e) {
///* 114 */       e.printStackTrace();
///* 115 */       this.logger.info("Error en la validación de sesión");
///*     */     }
/*     */     
/* 118 */     listarTransporteExterno();
/* 119 */     cargarParametros();
/*     */     
/* 121 */     this.strTitulo = "";
/* 122 */     this.pagina = "/pages/form/registerExterno.jsf";
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarTransporteExterno()
/*     */   {
/* 128 */     this.transporteExterno = new TransporteExterno();
/* 129 */     this.listTransExterno = new ArrayList();
/*     */     try
/*     */     {
/* 132 */       this.listTransExterno = this.externoEjbBeanLocal.listadoTransExterno(this.cooperativa.getId());
/*     */     } catch (AdminException e) {
/* 134 */       Util.CrearMsgErrorObtenerInfo();
/* 135 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerExterno()
/*     */   {
/* 142 */     JSFUtilities.setHttpSessionAttribute("externoId", Integer.valueOf(0));
/* 143 */     JSFUtilities.direccionarPagina(this.pagina);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void guardarEmision()
/*     */   {
/*     */     try
/*     */     {
/* 155 */       this.transporteExterno.setEmitidoPor(this.cooperativa.getNombreCompleto());
/* 156 */       this.transporteExterno.setEmitidoCi(this.cooperativa.getRepreCi() + " " + (String)this.mapCiExtendido.get(this.cooperativa.getIndRepreExpendido()));
/* 157 */       this.transporteExterno.setEmitidoCargo(this.cooperativa.getRepreCargo());
/*     */       
/* 159 */       this.transporteExterno.setIndEstadoRegistro(ParameterTableType.ESTADO_EN_EMITIDO_FORM.getCode());
/* 160 */       this.transporteExterno.setFechaEmision(new Date());
/* 161 */       this.transporteExterno.setQr(this.transporteExterno.getId() + "|" + 
/* 162 */         formatoFecha(this.transporteExterno.getFechaEmision()) + "|" + 
/* 163 */         this.transporteExterno.getSolRazonSocial() + "|" + 
/* 164 */         this.transporteExterno.getCarLote() + "|" + 
/* 165 */         this.transporteExterno.getCondNombreConductor());
/* 166 */       this.transporteExterno.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 167 */       this.externoEjbBeanLocal.guardarTransporteExterno(this.transporteExterno);
/* 168 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 171 */       Util.CrearMsgErrorRegistro();
/* 172 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void devolverHaciaOperador()
/*     */   {
/*     */     try
/*     */     {
/* 180 */       this.transporteExterno.setIndEstadoRegistro(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 181 */       this.transporteExterno.setFechaEmision(null);
/* 182 */       this.transporteExterno.setQr(null);
/*     */       
/* 184 */       this.transporteExterno.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 185 */       this.externoEjbBeanLocal.guardarTransporteExterno(this.transporteExterno);
/* 186 */       listarTransporteExterno();
/*     */       
/*     */ 
/* 189 */       Util.CrearMsgGuardadoPersonalizado("El registro fue enviado al Operador");
/*     */     }
/*     */     catch (AdminException e) {
/* 192 */       Util.CrearMsgErrorRegistro();
/* 193 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 203 */       this.mapPresentacion = new HashMap();
/* 204 */       this.parameterTable = new ArrayList();
/* 205 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 206 */       for (ParameterTable param : this.parameterTable) {
/* 207 */         this.mapPresentacion.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 212 */       this.mapEstadoRegistroForm = new HashMap();
/* 213 */       this.parameterTable = new ArrayList();
/* 214 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ESTADO_REGISTRO_FORM.getCode());
/* 215 */       for (ParameterTable param : this.parameterTable) {
/* 216 */         this.mapEstadoRegistroForm.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 220 */       this.mapTipoExportacion = new HashMap();
/* 221 */       this.parameterTable = new ArrayList();
/* 222 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.EXPORTACION_TOTAL_PARCIAL.getCode());
/* 223 */       for (ParameterTable param : this.parameterTable) {
/* 224 */         this.mapTipoExportacion.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 228 */       this.mapCargo = new HashMap();
/* 229 */       this.parameterTable = new ArrayList();
/* 230 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CARGO.getCode());
/* 231 */       for (ParameterTable param : this.parameterTable) {
/* 232 */         this.mapCargo.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 236 */       this.mapCiExtendido = new HashMap();
/* 237 */       this.parameterTable = new ArrayList();
/* 238 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CI_EXPEDIDO.getCode());
/* 239 */       for (ParameterTable param : this.parameterTable) {
/* 240 */         this.mapCiExtendido.put(param.getId(), param.getDescripcion());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 245 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteFormEmision()
/*     */     throws SQLException
/*     */   {
/* 252 */     Map<String, Object> parametros = new HashMap();
/* 253 */     parametros.put("idTransporte", this.transporteExterno.getId());
/* 254 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*     */     
/* 256 */     return this.mr.runReporte("form", "comFormExterno", parametros, "pdf", "comFormExterno", Integer.valueOf(2));
/*     */   }
/*     */   
/*     */   public void modifyTransporte()
/*     */   {
/* 261 */     JSFUtilities.setHttpSessionAttribute("externoId", this.transporteExterno);
/* 262 */     JSFUtilities.direccionarPagina(this.pagina);
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
/* 302 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 306 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 310 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 314 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapEstadoRegistroForm() {
/* 318 */     return this.mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public void setMapEstadoRegistroForm(Map<Integer, String> mapEstadoRegistroForm) {
/* 322 */     this.mapEstadoRegistroForm = mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public List<TransporteExterno> getListTransExterno() {
/* 326 */     return this.listTransExterno;
/*     */   }
/*     */   
/*     */   public void setListTransExterno(List<TransporteExterno> listTransExterno) {
/* 330 */     this.listTransExterno = listTransExterno;
/*     */   }
/*     */   
/*     */   public List<TransporteExterno> getListTransExternoFilter() {
/* 334 */     return this.listTransExternoFilter;
/*     */   }
/*     */   
/*     */   public void setListTransExternoFilter(List<TransporteExterno> listTransExternoFilter)
/*     */   {
/* 339 */     this.listTransExternoFilter = listTransExternoFilter;
/*     */   }
/*     */   
/*     */   public TransporteExterno getTransporteExterno() {
/* 343 */     return this.transporteExterno;
/*     */   }
/*     */   
/*     */   public void setTransporteExterno(TransporteExterno transporteExterno) {
/* 347 */     this.transporteExterno = transporteExterno;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapPresentacion() {
/* 351 */     return this.mapPresentacion;
/*     */   }
/*     */   
/*     */   public void setMapPresentacion(Map<Integer, String> mapPresentacion) {
/* 355 */     this.mapPresentacion = mapPresentacion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoExportacion() {
/* 359 */     return this.mapTipoExportacion;
/*     */   }
/*     */   
/*     */   public void setMapTipoExportacion(Map<Integer, String> mapTipoExportacion) {
/* 363 */     this.mapTipoExportacion = mapTipoExportacion;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\ListExternoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */