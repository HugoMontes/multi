/*     */ package org.erp.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.SQLException;
/*     */ import java.text.ParseException;
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
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.adm.CooperativaEJBBeanLocal;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.CotizacionMineral;
/*     */ import org.erp.entities.CotizacionMineralDesc;
/*     */ import org.erp.entities.Departamento;
/*     */ import org.erp.entities.Liquidacion;
/*     */ import org.erp.entities.Municipio;
/*     */ import org.erp.entities.Pais;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.Regalia;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.entities.TipoCambio;
/*     */ import org.erp.param.ML.ParametrosMLBeanLocal;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.Constantes;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="regaliaAgenteBean")
/*     */ @ViewScoped
/*     */ public class RegaliaAgenteBean
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
/*     */   LiquidacionEjbBeanLocal liquidacionEjbBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   @Inject
/*     */   ParametrosMLBeanLocal parametrosMLBeanLocal;
/*     */   private ParameterTable parameter;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String strTitulo;
/*     */   private String pagina;
/*     */   private List<ParameterTable> parameterTable;
/*     */   private Cooperativa cooperativa;
/*     */   private Liquidacion liquidacion;
/*     */   private List<Liquidacion> listLiquidacion;
/*     */   private Liquidacion liquidacionSelect;
/*     */   private Regalia regalia;
/*     */   private List<SelectItem> selectPais;
/*     */   private List<SelectItem> selectAduanaSalida;
/*     */   private List<SelectItem> selectLaboratorio;
/*     */   private List<SelectItem> selectMunicipio;
/*     */   private List<SelectItem> selectMineral;
/*     */   private Map<Integer, CotizacionMineral> mapSelectMineral;
/*     */   private List<SelectItem> selectMineralDescripcion;
/*     */   private List<SelectItem> selectPresentacion;
/*     */   private List<SelectItem> selectOpcionMineral;
/*     */   private Map<Integer, String> mapCotizacionUnidadMedida;
/*     */   private Map<String, String> mapCotizacionFactorConversion;
/*     */   private Map<Integer, String> mapDepartamento;
/*     */   private Map<Integer, String> mapMunicipio;
/*     */   private BigDecimal totalRM;
/*     */   private BigDecimal totalDistriGobernacion;
/*     */   private BigDecimal totalDistriMunicipio;
/*     */   private Departamento departamento;
/*     */   private TipoCambio tipoCambio;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/* 127 */     this.accesoBean = accesoBean;
/* 128 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/* 129 */     this.cooperativa = this.segUsuarioSession.getIdCooperativa();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/* 133 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() throws AdminException, ParseException {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/* 142 */       e.printStackTrace();
/* 143 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*     */     try
/*     */     {
/* 147 */       this.regalia = ((Regalia)JSFUtilities.getHttpSessionAttributeObject("RegaliaAgenteId"));
/*     */     }
/*     */     catch (Exception e) {
/* 150 */       this.regalia = new Regalia();
/* 151 */       this.departamento = new Departamento();
/* 152 */       this.departamento = this.cooperativaEJBBeanLocal.departamentoById(new Integer(1));
/*     */       
/* 154 */       this.regalia.setIdDepartamento(this.departamento);
/* 155 */       this.regalia.setIdCooperativa(this.cooperativa);
/*     */       
/* 157 */       this.regalia.setAgeIdMunicipio(new Municipio());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 162 */     cargarParametros();
/* 163 */     this.strTitulo = "";
/* 164 */     this.pagina = "/pages/regalias/listExportacion.jsf";
/*     */     
/* 166 */     this.liquidacion = new Liquidacion();
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 171 */       Date fecha = new Date();
/* 172 */       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
/* 173 */       String dateString = format.format(fecha);
/* 174 */       Date date = format.parse(dateString);
/*     */       
/* 176 */       this.tipoCambio = this.liquidacionEjbBeanLocal.obtenerTipoCambio(date, ParameterTableType.TIPO_MONEDA_DOLAR.getCode());
/* 177 */       if ((this.tipoCambio != null) && (this.tipoCambio.getId() != null)) {
/* 178 */         this.regalia.setTc(this.tipoCambio.getCotizacionOficial());
/*     */       } else {
/* 180 */         this.tipoCambio = new TipoCambio();
/* 181 */         throw new AdminException("No Existe Tipo de Cambio a la Fecha");
/*     */       }
/*     */       
/*     */ 
/* 185 */       if (this.regalia.getId() != null)
/*     */       {
/* 187 */         System.out.println("EXISTE INFO:::::::::");
/* 188 */         listarLiquidacion(this.regalia.getId());
/*     */       }
/*     */       else
/*     */       {
/* 192 */         this.liquidacion.setIdRegalia(this.regalia);
/* 193 */         this.liquidacion.setMinIdCotizacionMineral(new CotizacionMineral());
/* 194 */         this.liquidacion.setMinIdCotizacionMineralDesc(new CotizacionMineralDesc());
/* 195 */         this.liquidacion.setMinIdMunicipio(new Municipio());
/*     */         
/* 197 */         this.totalRM = new BigDecimal(0);
/* 198 */         this.totalDistriGobernacion = new BigDecimal(0);
/* 199 */         this.totalDistriMunicipio = new BigDecimal(0);
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 203 */       Util.CrearMsgErrorFatalGenerico(e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarMineral(Liquidacion liquidacionParam)
/*     */   {
/*     */     try {
/* 210 */       this.liquidacion = liquidacionParam;
/* 211 */       this.liquidacionEjbBeanLocal.eliminarMineral(this.liquidacion);
/* 212 */       listarLiquidacion(this.regalia.getId());
/* 213 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 216 */       Util.CrearMsgErrorRegistro();
/* 217 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void guardarExportacion()
/*     */   {
/* 224 */     String nroTramite = "";
/*     */     
/*     */     try
/*     */     {
/* 228 */       this.regalia.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 229 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 230 */       this.regalia.setIndEstadoPago(ParameterTableType.ESTADO_PAGO_PENDIENTE.getCode());
/* 231 */       this.regalia.setIndTipoFormulario(ParameterTableType.IND_FORM_AGENTE_RETENCION.getCode());
/* 232 */       this.liquidacion.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */       
/*     */ 
/* 235 */       DateTime fecha = new DateTime();
/* 236 */       Integer anio = Integer.valueOf(fecha.getYear());
/* 237 */       Integer mes = Integer.valueOf(fecha.getMonthOfYear());
/* 238 */       this.parameter = this.serviciosUtilBeanLocal.ParameterById(ParameterTableType.COORELATIVO_FORMULARIOS.getCode());
/* 239 */       Integer correlativo = Integer.valueOf(this.parameter.getNumero1().intValue() + 1);
/* 240 */       this.parameter.setNumero1(correlativo);
/* 241 */       this.parametrosMLBeanLocal.guardarParameter(this.parameter);
/* 242 */       nroTramite = "2" + anio.toString() + mes.toString() + generacionCorrelativo(correlativo);
/* 243 */       this.regalia.setNumeroOrden(nroTramite);
/*     */       
/* 245 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_EMITIDO_FORM.getCode());
/* 246 */       this.regalia.setFechaEmision(new Date());
/* 247 */       this.liquidacionEjbBeanLocal.guardarRegalia(this.regalia);
/* 248 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 251 */       Util.CrearMsgErrorRegistro();
/* 252 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void adicionarMineral() {
/* 257 */     if ((this.liquidacion.getMinIdMunicipio().getId() != null) && 
/* 258 */       (this.liquidacion.getMinIdCotizacionMineral().getId() != null) && 
/* 259 */       (this.liquidacion.getMinIdCotizacionMineralDesc().getId() != null) && 
/* 260 */       (this.liquidacion.getMinIndPresentacion() != null) && 
/* 261 */       (this.liquidacion.getMinPnsKg() != null) && 
/* 262 */       (this.liquidacion.getMinLey() != null) && 
/* 263 */       (this.liquidacion.getMinIndUnidad() != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 268 */       BigDecimal factor = new BigDecimal(0);
/* 269 */       if (this.liquidacion.getMinIndUnidad().intValue() == ParameterTableType.IND_UNIDAD_MEDIDA_GTM.getCode().intValue()) {
/* 270 */         factor = new BigDecimal(1000000);
/* 271 */       } else if (this.liquidacion.getMinIndUnidad().intValue() == ParameterTableType.IND_UNIDAD_MEDIDA_PORCENTAJE.getCode().intValue()) {
/* 272 */         factor = new BigDecimal(100);
/*     */       }
/*     */       
/* 275 */       this.liquidacion.setMinIdCotizacionMineral((CotizacionMineral)this.mapSelectMineral.get(this.liquidacion.getMinIdCotizacionMineral().getId()));
/*     */       
/*     */ 
/*     */ 
/* 279 */       this.liquidacion.setMinPfKg(this.liquidacion.getMinPnsKg().multiply(this.liquidacion.getMinLey().divide(factor)));
/*     */       
/*     */ 
/* 282 */       String idFactor = (String)this.mapCotizacionUnidadMedida.get(this.liquidacion.getMinIndUnidadMedCoti());
/* 283 */       BigDecimal factorConversion = new BigDecimal(((String)this.mapCotizacionFactorConversion.get(idFactor)).toString());
/* 284 */       this.liquidacion.setMinPfUm(this.liquidacion.getMinPfKg().multiply(factorConversion).setScale(4, 4));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 289 */       this.liquidacion.setMinVbvUsd(this.liquidacion.getMinPfUm().multiply(this.liquidacion.getMinCotizacion()).setScale(4, 4));
/*     */       
/*     */ 
/* 292 */       this.liquidacion.setMinVbvBs(this.liquidacion.getMinVbvUsd().multiply(this.regalia.getTc()).setScale(4, 4));
/*     */       
/*     */ 
/* 295 */       BigDecimal factoAlicuota = new BigDecimal(100);
/*     */       
/* 297 */       this.liquidacion.setMinRmBs(this.liquidacion.getMinVbvBs().multiply(this.liquidacion.getMinAlicuota().divide(factoAlicuota)).setScale(2, 4));
/*     */       
/*     */ 
/* 300 */       BigDecimal gobernacion = this.liquidacion.getMinRmBs().multiply(new BigDecimal(0.85D));
/* 301 */       BigDecimal municipio = this.liquidacion.getMinRmBs().multiply(new BigDecimal(0.15D));
/* 302 */       this.liquidacion.setMinDistribucionGobernacion(gobernacion.setScale(0, 4));
/* 303 */       this.liquidacion.setMinDistribucionMunicipio(municipio.setScale(0, 4));
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 308 */         this.regalia.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 309 */         this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 310 */         this.regalia.setIndEstadoPago(ParameterTableType.ESTADO_PAGO_PENDIENTE.getCode());
/* 311 */         this.regalia.setIndTipoFormulario(ParameterTableType.IND_FORM_AGENTE_RETENCION.getCode());
/*     */         
/* 313 */         this.liquidacion.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */         
/* 315 */         this.liquidacionEjbBeanLocal.guardarLiquidacionExportacion(this.regalia, this.liquidacion);
/*     */         
/* 317 */         Util.CrearMsgGuardado();
/*     */       }
/*     */       catch (AdminException e) {
/* 320 */         Util.CrearMsgErrorRegistro();
/* 321 */         e.printStackTrace();
/*     */       }
/*     */       
/* 324 */       listarLiquidacion(this.regalia.getId());
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 329 */       Util.CrearMsgErrorFatalGenerico("Faltan Datos que completar");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StreamedContent getReporteBoletaPago()
/*     */     throws SQLException
/*     */   {
/* 338 */     Locale espanol = new Locale("es", "ES");
/* 339 */     SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", espanol);
/* 340 */     String fecha = formateador.format(this.regalia.getFechaEmision());
/* 341 */     String fechaText = "La Paz, " + fecha;
/*     */     
/* 343 */     Map<String, Object> parametros = new HashMap();
/* 344 */     parametros.put("idRegalia", this.regalia.getId());
/* 345 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 346 */     parametros.put("fechaText", fechaText);
/* 347 */     return this.mr.runReporte("form", "rmBoletaPagoAgente", parametros, "pdf", "rmBoletaPagoAgente", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteFormEmision()
/*     */     throws SQLException
/*     */   {
/* 353 */     Map<String, Object> parametros = new HashMap();
/* 354 */     parametros.put("idRegalia", this.regalia.getId());
/* 355 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 356 */     return this.mr.runReporte("form", "rmAgente", parametros, "pdf", "rmAgente", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarLiquidacion(Integer idRegalia)
/*     */   {
/* 362 */     this.listLiquidacion = new ArrayList();
/*     */     
/* 364 */     this.liquidacion = new Liquidacion();
/* 365 */     this.liquidacion.setIdRegalia(this.regalia);
/* 366 */     this.liquidacion.setMinIdCotizacionMineral(new CotizacionMineral());
/* 367 */     this.liquidacion.setMinIdCotizacionMineralDesc(new CotizacionMineralDesc());
/* 368 */     this.liquidacion.setMinIdMunicipio(new Municipio());
/*     */     try
/*     */     {
/* 371 */       this.listLiquidacion = this.liquidacionEjbBeanLocal.listadoliquidacionByRegalia(idRegalia);
/*     */       
/* 373 */       this.totalRM = new BigDecimal(0);
/* 374 */       this.totalDistriGobernacion = new BigDecimal(0);
/* 375 */       this.totalDistriMunicipio = new BigDecimal(0);
/* 376 */       for (Liquidacion det : this.listLiquidacion) {
/* 377 */         this.totalRM = this.totalRM.add(det.getMinRmBs());
/*     */         
/* 379 */         this.totalDistriGobernacion = this.totalDistriGobernacion.add(det.getMinDistribucionGobernacion());
/* 380 */         this.totalDistriGobernacion = this.totalDistriGobernacion.setScale(0, 4);
/*     */         
/* 382 */         this.totalDistriMunicipio = this.totalDistriMunicipio.add(det.getMinDistribucionMunicipio());
/* 383 */         this.totalDistriMunicipio = this.totalDistriMunicipio.setScale(0, 4);
/*     */       }
/*     */       
/*     */ 
/* 387 */       this.totalRM = this.totalRM.setScale(0, 4);
/*     */     }
/*     */     catch (AdminException e) {
/* 390 */       Util.CrearMsgErrorObtenerInfo();
/* 391 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void guardarFormulario()
/*     */   {
/* 401 */     System.out.println("VALOR::::::: " + this.liquidacion.getId());
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
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 420 */       this.selectPais = new ArrayList();
/* 421 */       this.selectPais.add(new SelectItem(null, "Seleccione..."));
/* 422 */       List<Pais> listPais = new ArrayList();
/* 423 */       listPais = this.cooperativaEJBBeanLocal.listadoPais();
/* 424 */       for (Pais pais : listPais) {
/* 425 */         this.selectPais.add(new SelectItem(pais.getId(), pais.getNombre()));
/*     */       }
/*     */       
/* 428 */       this.selectAduanaSalida = new ArrayList();
/* 429 */       this.selectAduanaSalida.add(new SelectItem(null, "Seleccionar..."));
/* 430 */       this.parameterTable = new ArrayList();
/* 431 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_ADUANA_SALIDA.getCode());
/* 432 */       for (ParameterTable param : this.parameterTable) {
/* 433 */         this.selectAduanaSalida.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 436 */       this.selectLaboratorio = new ArrayList();
/* 437 */       this.selectLaboratorio.add(new SelectItem(null, "Seleccionar..."));
/* 438 */       this.parameterTable = new ArrayList();
/* 439 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_TIPO_LABORATORIO.getCode());
/* 440 */       for (ParameterTable param : this.parameterTable) {
/* 441 */         this.selectLaboratorio.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 444 */       this.mapMunicipio = new HashMap();
/* 445 */       this.selectMunicipio = new ArrayList();
/* 446 */       this.selectMunicipio.add(new SelectItem(null, "Seleccione..."));
/* 447 */       List<Municipio> listMun = new ArrayList();
/* 448 */       listMun = this.cooperativaEJBBeanLocal.listadoMunicipio(Constantes.ID_MUNICIPIO_LA_PAZ);
/* 449 */       for (Municipio mun : listMun) {
/* 450 */         this.selectMunicipio.add(new SelectItem(mun.getId(), mun.getNombre()));
/* 451 */         this.mapMunicipio.put(mun.getId(), mun.getNombre());
/*     */       }
/*     */       
/* 454 */       this.selectMineral = new ArrayList();
/* 455 */       this.mapSelectMineral = new HashMap();
/* 456 */       this.selectMineral.add(new SelectItem(null, "Seleccione..."));
/* 457 */       Object cotMineral = new ArrayList();
/* 458 */       cotMineral = this.liquidacionEjbBeanLocal.listCotizacionMineral();
/* 459 */       for (CotizacionMineral list : (List)cotMineral) {
/* 460 */         this.selectMineral.add(new SelectItem(list.getId(), list.getNombre()));
/* 461 */         this.mapSelectMineral.put(list.getId(), list);
/*     */       }
/*     */       
/* 464 */       this.selectPresentacion = new ArrayList();
/* 465 */       this.selectPresentacion.add(new SelectItem(null, "Seleccione..."));
/* 466 */       this.parameterTable = new ArrayList();
/* 467 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 468 */       for (ParameterTable param : this.parameterTable) {
/* 469 */         this.selectPresentacion.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 472 */       this.selectOpcionMineral = new ArrayList();
/* 473 */       this.selectOpcionMineral.add(new SelectItem(null, ""));
/* 474 */       this.parameterTable = new ArrayList();
/* 475 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.SELECT_TIPO_MINERAL.getCode());
/* 476 */       for (ParameterTable param : this.parameterTable) {
/* 477 */         this.selectOpcionMineral.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 480 */       this.mapCotizacionFactorConversion = new HashMap();
/* 481 */       this.mapCotizacionUnidadMedida = new HashMap();
/* 482 */       this.parameterTable = new ArrayList();
/* 483 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_COTIZACION_UNIDAD_MEDIDA.getCode());
/* 484 */       for (ParameterTable param : this.parameterTable) {
/* 485 */         this.mapCotizacionUnidadMedida.put(param.getId(), param.getNombre());
/* 486 */         this.mapCotizacionFactorConversion.put(param.getNombre(), param.getTexto1());
/*     */       }
/*     */       
/* 489 */       this.mapDepartamento = new HashMap();
/* 490 */       Object listDepar = new ArrayList();
/* 491 */       listDepar = this.cooperativaEJBBeanLocal.listadoDepartamento();
/* 492 */       for (Departamento dep : (List)listDepar) {
/* 493 */         this.mapDepartamento.put(dep.getId(), dep.getNombre());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 579 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void buscarDescripcionMineral()
/*     */   {
/* 585 */     this.selectMineralDescripcion = new ArrayList();
/* 586 */     this.selectMineralDescripcion.add(new SelectItem(null, "Seleccione..."));
/* 587 */     List<CotizacionMineralDesc> minDesc = new ArrayList();
/*     */     try
/*     */     {
/* 590 */       minDesc = this.liquidacionEjbBeanLocal.listCotizacionMineralDesc(this.liquidacion.getMinIdCotizacionMineral().getId());
/* 591 */       for (CotizacionMineralDesc desc : minDesc) {
/* 592 */         this.selectMineralDescripcion.add(new SelectItem(desc.getId(), desc.getNombre()));
/*     */       }
/*     */       
/* 595 */       CotizacionMineral cotizacionMineral = new CotizacionMineral();
/* 596 */       cotizacionMineral = (CotizacionMineral)this.mapSelectMineral.get(this.liquidacion.getMinIdCotizacionMineral().getId());
/*     */       
/* 598 */       this.liquidacion.setMinCotizacion(cotizacionMineral.getCotizacionOficial());
/* 599 */       this.liquidacion.setMinIndUnidadMedCoti(cotizacionMineral.getIndUnidadMedida());
/* 600 */       this.liquidacion.setMinAlicuota(cotizacionMineral.getAlicuotaInternas());
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 604 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void seleccionMinerales()
/*     */   {
/* 611 */     this.strTitulo = "Seleccionar minerales";
/*     */   }
/*     */   
/*     */ 
/*     */   private String generacionCorrelativo(Integer cor)
/*     */   {
/* 617 */     String compuesto = "";
/* 618 */     if (cor.toString().length() == 1) {
/* 619 */       compuesto = "0000" + cor;
/* 620 */     } else if (cor.toString().length() == 2) {
/* 621 */       compuesto = "000" + cor;
/* 622 */     } else if (cor.toString().length() == 3) {
/* 623 */       compuesto = "00" + cor;
/* 624 */     } else if (cor.toString().length() == 4) {
/* 625 */       compuesto = "0" + cor;
/*     */     }
/*     */     
/* 628 */     return compuesto;
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
/*     */   public Cooperativa getCooperativa()
/*     */   {
/* 672 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 676 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPresentacion() {
/* 680 */     return this.selectPresentacion;
/*     */   }
/*     */   
/*     */   public void setSelectPresentacion(List<SelectItem> selectPresentacion) {
/* 684 */     this.selectPresentacion = selectPresentacion;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMunicipio() {
/* 688 */     return this.selectMunicipio;
/*     */   }
/*     */   
/*     */   public void setSelectMunicipio(List<SelectItem> selectMunicipio) {
/* 692 */     this.selectMunicipio = selectMunicipio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPais() {
/* 696 */     return this.selectPais;
/*     */   }
/*     */   
/*     */   public void setSelectPais(List<SelectItem> selectPais) {
/* 700 */     this.selectPais = selectPais;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 704 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 708 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectOpcionMineral() {
/* 712 */     return this.selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public void setSelectOpcionMineral(List<SelectItem> selectOpcionMineral) {
/* 716 */     this.selectOpcionMineral = selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectLaboratorio() {
/* 720 */     return this.selectLaboratorio;
/*     */   }
/*     */   
/*     */   public void setSelectLaboratorio(List<SelectItem> selectLaboratorio) {
/* 724 */     this.selectLaboratorio = selectLaboratorio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectAduanaSalida() {
/* 728 */     return this.selectAduanaSalida;
/*     */   }
/*     */   
/*     */   public void setSelectAduanaSalida(List<SelectItem> selectAduanaSalida) {
/* 732 */     this.selectAduanaSalida = selectAduanaSalida;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMineral() {
/* 736 */     return this.selectMineral;
/*     */   }
/*     */   
/*     */   public void setSelectMineral(List<SelectItem> selectMineral) {
/* 740 */     this.selectMineral = selectMineral;
/*     */   }
/*     */   
/*     */   public Map<Integer, CotizacionMineral> getMapSelectMineral() {
/* 744 */     return this.mapSelectMineral;
/*     */   }
/*     */   
/*     */   public void setMapSelectMineral(Map<Integer, CotizacionMineral> mapSelectMineral) {
/* 748 */     this.mapSelectMineral = mapSelectMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMineralDescripcion() {
/* 752 */     return this.selectMineralDescripcion;
/*     */   }
/*     */   
/*     */   public void setSelectMineralDescripcion(List<SelectItem> selectMineralDescripcion)
/*     */   {
/* 757 */     this.selectMineralDescripcion = selectMineralDescripcion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapCotizacionUnidadMedida() {
/* 761 */     return this.mapCotizacionUnidadMedida;
/*     */   }
/*     */   
/*     */   public void setMapCotizacionUnidadMedida(Map<Integer, String> mapCotizacionUnidadMedida)
/*     */   {
/* 766 */     this.mapCotizacionUnidadMedida = mapCotizacionUnidadMedida;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapDepartamento() {
/* 770 */     return this.mapDepartamento;
/*     */   }
/*     */   
/*     */   public void setMapDepartamento(Map<Integer, String> mapDepartamento) {
/* 774 */     this.mapDepartamento = mapDepartamento;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapMunicipio() {
/* 778 */     return this.mapMunicipio;
/*     */   }
/*     */   
/*     */   public void setMapMunicipio(Map<Integer, String> mapMunicipio) {
/* 782 */     this.mapMunicipio = mapMunicipio;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalRM() {
/* 786 */     return this.totalRM;
/*     */   }
/*     */   
/*     */   public void setTotalRM(BigDecimal totalRM) {
/* 790 */     this.totalRM = totalRM;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalDistriGobernacion() {
/* 794 */     return this.totalDistriGobernacion;
/*     */   }
/*     */   
/*     */   public void setTotalDistriGobernacion(BigDecimal totalDistriGobernacion) {
/* 798 */     this.totalDistriGobernacion = totalDistriGobernacion;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalDistriMunicipio() {
/* 802 */     return this.totalDistriMunicipio;
/*     */   }
/*     */   
/*     */   public void setTotalDistriMunicipio(BigDecimal totalDistriMunicipio) {
/* 806 */     this.totalDistriMunicipio = totalDistriMunicipio;
/*     */   }
/*     */   
/*     */   public Regalia getRegalia() {
/* 810 */     return this.regalia;
/*     */   }
/*     */   
/*     */   public void setRegalia(Regalia regalia) {
/* 814 */     this.regalia = regalia;
/*     */   }
/*     */   
/*     */   public Liquidacion getLiquidacion() {
/* 818 */     return this.liquidacion;
/*     */   }
/*     */   
/*     */   public void setLiquidacion(Liquidacion liquidacion) {
/* 822 */     this.liquidacion = liquidacion;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> getListLiquidacion() {
/* 826 */     return this.listLiquidacion;
/*     */   }
/*     */   
/*     */   public void setListLiquidacion(List<Liquidacion> listLiquidacion) {
/* 830 */     this.listLiquidacion = listLiquidacion;
/*     */   }
/*     */   
/*     */   public Liquidacion getLiquidacionSelect() {
/* 834 */     return this.liquidacionSelect;
/*     */   }
/*     */   
/*     */   public void setLiquidacionSelect(Liquidacion liquidacionSelect) {
/* 838 */     this.liquidacionSelect = liquidacionSelect;
/*     */   }
/*     */   
/*     */   public TipoCambio getTipoCambio() {
/* 842 */     return this.tipoCambio;
/*     */   }
/*     */   
/*     */   public void setTipoCambio(TipoCambio tipoCambio) {
/* 846 */     this.tipoCambio = tipoCambio;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\RegaliaAgenteBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */