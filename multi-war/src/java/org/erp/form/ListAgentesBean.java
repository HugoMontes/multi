/*     */ package org.erp.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.Regalia;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.param.ML.ParametrosMLBeanLocal;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.MostrarReporte;
/*     */ import org.erp.util.ParameterTableType;
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
/*     */ @ManagedBean(name="listAgentesBean")
/*     */ @ViewScoped
/*     */ public class ListAgentesBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   LiquidacionEjbBeanLocal liquidacionEjbBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   @Inject
/*     */   ParametrosMLBeanLocal parametrosMLBeanLocal;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   private String strTitulo;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String pagina;
/*  75 */   private Map<Integer, String> mapPresentacion = null;
/*     */   private List<ParameterTable> parameterTable;
/*  77 */   private Map<Integer, String> mapEstadoRegistroForm = null;
/*  78 */   private Map<Integer, String> mapTipoFormulario = null;
/*  79 */   private Map<Integer, String> mapEstadoPagoForm = null;
/*     */   
/*     */   private ParameterTable parameter;
/*     */   
/*     */   private List<Regalia> listRegalia;
/*     */   
/*     */   private List<Regalia> listRegaliaFilter;
/*     */   
/*     */   private Regalia regalia;
/*     */   
/*     */   private Cooperativa cooperativa;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
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
/* 118 */     listarRegaliaExterno();
/* 119 */     cargarParametros();
/*     */     
/* 121 */     this.strTitulo = "";
/* 122 */     this.pagina = "/pages/regalias/registerRegaliaAgente.jsf";
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarRegaliaExterno()
/*     */   {
/* 128 */     this.regalia = new Regalia();
/* 129 */     this.listRegalia = new ArrayList();
/*     */     try
/*     */     {
/* 132 */       this.listRegalia = this.liquidacionEjbBeanLocal.listadoRegalia(this.cooperativa.getId(), ParameterTableType.REGALIA_AGENTE_RETENCION.getCode());
/*     */     } catch (AdminException e) {
/* 134 */       Util.CrearMsgErrorObtenerInfo();
/* 135 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registerRegaliaExterno()
/*     */   {
/* 141 */     JSFUtilities.setHttpSessionAttribute("RegaliaAgenteId", Integer.valueOf(0));
/* 142 */     JSFUtilities.direccionarPagina(this.pagina);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void guardarEmision()
/*     */   {
/* 151 */     String nroTramite = "";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 160 */       DateTime fecha = new DateTime();
/* 161 */       Integer anio = Integer.valueOf(fecha.getYear());
/* 162 */       Integer mes = Integer.valueOf(fecha.getMonthOfYear());
/* 163 */       this.parameter = this.serviciosUtilBeanLocal.ParameterById(ParameterTableType.COORELATIVO_FORMULARIOS.getCode());
/* 164 */       Integer correlativo = Integer.valueOf(this.parameter.getNumero1().intValue() + 1);
/* 165 */       this.parameter.setNumero1(correlativo);
/* 166 */       this.parametrosMLBeanLocal.guardarParameter(this.parameter);
/* 167 */       nroTramite = "2" + anio.toString() + mes.toString() + generacionCorrelativo(correlativo);
/* 168 */       this.regalia.setNumeroOrden(nroTramite);
/*     */       
/* 170 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_EMITIDO_FORM.getCode());
/* 171 */       this.regalia.setFechaEmision(new Date());
/* 172 */       this.liquidacionEjbBeanLocal.guardarRegalia(this.regalia);
/* 173 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 176 */       Util.CrearMsgErrorRegistro();
/* 177 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void devolverHaciaOperador()
/*     */   {
/*     */     try
/*     */     {
/* 185 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 186 */       this.regalia.setFechaEmision(null);
/* 187 */       this.regalia.setNumeroOrden(null);
/* 188 */       this.regalia.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 189 */       this.liquidacionEjbBeanLocal.guardarRegalia(this.regalia);
/* 190 */       listarRegaliaExterno();
/* 191 */       Util.CrearMsgGuardadoPersonalizado("El registro fue enviado al Operador");
/*     */     } catch (AdminException e) {
/* 193 */       Util.CrearMsgErrorRegistro();
/* 194 */       e.printStackTrace();
/*     */     }
/*     */   }
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
/* 220 */       this.mapEstadoPagoForm = new HashMap();
/* 221 */       this.parameterTable = new ArrayList();
/* 222 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_ESTADO_PAGO_FORM.getCode());
/* 223 */       for (ParameterTable param : this.parameterTable) {
/* 224 */         this.mapEstadoPagoForm.put(param.getId(), param.getNombre());
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/*     */ 
/*     */ 
/* 237 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteBoletaPago()
/*     */     throws SQLException
/*     */   {
/* 244 */     Locale espanol = new Locale("es", "ES");
/* 245 */     SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", espanol);
/* 246 */     String fecha = formateador.format(this.regalia.getFechaEmision());
/* 247 */     String fechaText = "La Paz, " + fecha;
/*     */     
/* 249 */     Map<String, Object> parametros = new HashMap();
/* 250 */     parametros.put("idRegalia", this.regalia.getId());
/* 251 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 252 */     parametros.put("fechaText", fechaText);
/* 253 */     return this.mr.runReporte("form", "rmBoletaPagoAgente", parametros, "pdf", "rmBoletaPagoAgente", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteFormEmision()
/*     */     throws SQLException
/*     */   {
/* 259 */     Map<String, Object> parametros = new HashMap();
/* 260 */     parametros.put("idRegalia", this.regalia.getId());
/* 261 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 262 */     return this.mr.runReporte("form", "rmAgente", parametros, "pdf", "rmAgente", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */ 
/*     */   public void modifyRegaliaExterno()
/*     */   {
/* 268 */     JSFUtilities.setHttpSessionAttribute("RegaliaAgenteId", this.regalia);
/* 269 */     JSFUtilities.direccionarPagina(this.pagina);
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
/*     */   private String generacionCorrelativo(Integer cor)
/*     */   {
/* 308 */     String compuesto = "";
/* 309 */     if (cor.toString().length() == 1) {
/* 310 */       compuesto = "0000" + cor;
/* 311 */     } else if (cor.toString().length() == 2) {
/* 312 */       compuesto = "000" + cor;
/* 313 */     } else if (cor.toString().length() == 3) {
/* 314 */       compuesto = "00" + cor;
/* 315 */     } else if (cor.toString().length() == 4) {
/* 316 */       compuesto = "0" + cor;
/*     */     }
/*     */     
/* 319 */     return compuesto;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 323 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 327 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 331 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 335 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapEstadoRegistroForm() {
/* 339 */     return this.mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public void setMapEstadoRegistroForm(Map<Integer, String> mapEstadoRegistroForm) {
/* 343 */     this.mapEstadoRegistroForm = mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapPresentacion() {
/* 347 */     return this.mapPresentacion;
/*     */   }
/*     */   
/*     */   public void setMapPresentacion(Map<Integer, String> mapPresentacion) {
/* 351 */     this.mapPresentacion = mapPresentacion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoFormulario() {
/* 355 */     return this.mapTipoFormulario;
/*     */   }
/*     */   
/*     */   public void setMapTipoFormulario(Map<Integer, String> mapTipoFormulario) {
/* 359 */     this.mapTipoFormulario = mapTipoFormulario;
/*     */   }
/*     */   
/*     */   public List<Regalia> getListRegalia() {
/* 363 */     return this.listRegalia;
/*     */   }
/*     */   
/*     */   public void setListRegalia(List<Regalia> listRegalia) {
/* 367 */     this.listRegalia = listRegalia;
/*     */   }
/*     */   
/*     */   public List<Regalia> getListRegaliaFilter() {
/* 371 */     return this.listRegaliaFilter;
/*     */   }
/*     */   
/*     */   public void setListRegaliaFilter(List<Regalia> listRegaliaFilter) {
/* 375 */     this.listRegaliaFilter = listRegaliaFilter;
/*     */   }
/*     */   
/*     */   public Regalia getRegalia() {
/* 379 */     return this.regalia;
/*     */   }
/*     */   
/*     */   public void setRegalia(Regalia regalia) {
/* 383 */     this.regalia = regalia;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapEstadoPagoForm() {
/* 387 */     return this.mapEstadoPagoForm;
/*     */   }
/*     */   
/*     */   public void setMapEstadoPagoForm(Map<Integer, String> mapEstadoPagoForm) {
/* 391 */     this.mapEstadoPagoForm = mapEstadoPagoForm;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\ListAgentesBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */