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
/*     */ @ManagedBean(name="listExportacionBean")
/*     */ @ViewScoped
/*     */ public class ListExportacionBean
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
/* 122 */     this.pagina = "/pages/regalias/registerRegaliaExterno.jsf";
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarRegaliaExterno()
/*     */   {
/* 128 */     this.regalia = new Regalia();
/* 129 */     this.listRegalia = new ArrayList();
/*     */     try
/*     */     {
/* 132 */       this.listRegalia = this.liquidacionEjbBeanLocal.listadoRegalia(this.cooperativa.getId(), ParameterTableType.REGALIA_EXPORTACION.getCode());
/*     */     } catch (AdminException e) {
/* 134 */       Util.CrearMsgErrorObtenerInfo();
/* 135 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registerRegaliaExterno()
/*     */   {
/* 141 */     JSFUtilities.setHttpSessionAttribute("RegaliaExternoId", Integer.valueOf(0));
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
/*     */ 
/*     */ 
/*     */ 
/* 173 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_EMITIDO_FORM.getCode());
/* 174 */       this.regalia.setFechaEmision(new Date());
/* 175 */       this.liquidacionEjbBeanLocal.guardarRegalia(this.regalia);
/* 176 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 179 */       Util.CrearMsgErrorRegistro();
/* 180 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void devolverHaciaOperador()
/*     */   {
/*     */     try
/*     */     {
/* 188 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 189 */       this.regalia.setFechaEmision(null);
/* 190 */       this.regalia.setNumeroOrden(null);
/* 191 */       this.regalia.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 192 */       this.liquidacionEjbBeanLocal.guardarRegalia(this.regalia);
/* 193 */       listarRegaliaExterno();
/* 194 */       Util.CrearMsgGuardadoPersonalizado("El registro fue enviado al Operador");
/*     */     } catch (AdminException e) {
/* 196 */       Util.CrearMsgErrorRegistro();
/* 197 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 207 */       this.mapPresentacion = new HashMap();
/* 208 */       this.parameterTable = new ArrayList();
/* 209 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 210 */       for (ParameterTable param : this.parameterTable) {
/* 211 */         this.mapPresentacion.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 216 */       this.mapEstadoRegistroForm = new HashMap();
/* 217 */       this.parameterTable = new ArrayList();
/* 218 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ESTADO_REGISTRO_FORM.getCode());
/* 219 */       for (ParameterTable param : this.parameterTable) {
/* 220 */         this.mapEstadoRegistroForm.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 224 */       this.mapEstadoPagoForm = new HashMap();
/* 225 */       this.parameterTable = new ArrayList();
/* 226 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_ESTADO_PAGO_FORM.getCode());
/* 227 */       for (ParameterTable param : this.parameterTable) {
/* 228 */         this.mapEstadoPagoForm.put(param.getId(), param.getNombre());
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
/* 241 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteBoletaPago()
/*     */     throws SQLException
/*     */   {
/* 248 */     Locale espanol = new Locale("es", "ES");
/* 249 */     SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", espanol);
/* 250 */     String fecha = formateador.format(this.regalia.getFechaEmision());
/* 251 */     String fechaText = "La Paz, " + fecha;
/*     */     
/* 253 */     Map<String, Object> parametros = new HashMap();
/* 254 */     parametros.put("idRegalia", this.regalia.getId());
/* 255 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 256 */     parametros.put("fechaText", fechaText);
/*     */     
/* 258 */     return this.mr.runReporte("form", "rmBoletaPagoExp", parametros, "pdf", "rmBoletaPagoExp", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteFormEmision()
/*     */     throws SQLException
/*     */   {
/* 264 */     Map<String, Object> parametros = new HashMap();
/* 265 */     parametros.put("idRegalia", this.regalia.getId());
/* 266 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 267 */     return this.mr.runReporte("form", "rmExportacion", parametros, "pdf", "rmExportacion", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */ 
/*     */   public void modifyRegaliaExterno()
/*     */   {
/* 273 */     JSFUtilities.setHttpSessionAttribute("RegaliaExternoId", this.regalia);
/* 274 */     JSFUtilities.direccionarPagina(this.pagina);
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
/* 313 */     String compuesto = "";
/* 314 */     if (cor.toString().length() == 1) {
/* 315 */       compuesto = "0000" + cor;
/* 316 */     } else if (cor.toString().length() == 2) {
/* 317 */       compuesto = "000" + cor;
/* 318 */     } else if (cor.toString().length() == 3) {
/* 319 */       compuesto = "00" + cor;
/* 320 */     } else if (cor.toString().length() == 4) {
/* 321 */       compuesto = "0" + cor;
/*     */     }
/*     */     
/* 324 */     return compuesto;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 328 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 332 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 336 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 340 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapEstadoRegistroForm() {
/* 344 */     return this.mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public void setMapEstadoRegistroForm(Map<Integer, String> mapEstadoRegistroForm) {
/* 348 */     this.mapEstadoRegistroForm = mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapPresentacion() {
/* 352 */     return this.mapPresentacion;
/*     */   }
/*     */   
/*     */   public void setMapPresentacion(Map<Integer, String> mapPresentacion) {
/* 356 */     this.mapPresentacion = mapPresentacion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoFormulario() {
/* 360 */     return this.mapTipoFormulario;
/*     */   }
/*     */   
/*     */   public void setMapTipoFormulario(Map<Integer, String> mapTipoFormulario) {
/* 364 */     this.mapTipoFormulario = mapTipoFormulario;
/*     */   }
/*     */   
/*     */   public List<Regalia> getListRegalia() {
/* 368 */     return this.listRegalia;
/*     */   }
/*     */   
/*     */   public void setListRegalia(List<Regalia> listRegalia) {
/* 372 */     this.listRegalia = listRegalia;
/*     */   }
/*     */   
/*     */   public List<Regalia> getListRegaliaFilter() {
/* 376 */     return this.listRegaliaFilter;
/*     */   }
/*     */   
/*     */   public void setListRegaliaFilter(List<Regalia> listRegaliaFilter) {
/* 380 */     this.listRegaliaFilter = listRegaliaFilter;
/*     */   }
/*     */   
/*     */   public Regalia getRegalia() {
/* 384 */     return this.regalia;
/*     */   }
/*     */   
/*     */   public void setRegalia(Regalia regalia) {
/* 388 */     this.regalia = regalia;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapEstadoPagoForm() {
/* 392 */     return this.mapEstadoPagoForm;
/*     */   }
/*     */   
/*     */   public void setMapEstadoPagoForm(Map<Integer, String> mapEstadoPagoForm) {
/* 396 */     this.mapEstadoPagoForm = mapEstadoPagoForm;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\ListExportacionBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */