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
/*     */ @ManagedBean(name="regaliaExportacionBean")
/*     */ @ViewScoped
/*     */ public class RegaliaExportacionBean
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
/*     */   ParametrosMLBeanLocal parametrosMLBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
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
/*     */   private ParameterTable parameter;
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
/*     */   public void inicio() {
/* 139 */     this.departamento = new Departamento();
/* 140 */     this.departamento.setId(Integer.valueOf(1));
/*     */     try
/*     */     {
/* 143 */       JSFUtilities.verificarSession();
/*     */     } catch (IOException e) {
/* 145 */       e.printStackTrace();
/* 146 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*     */     try
/*     */     {
/* 150 */       this.regalia = ((Regalia)JSFUtilities.getHttpSessionAttributeObject("RegaliaExternoId"));
/*     */     }
/*     */     catch (Exception e) {
/* 153 */       this.regalia = new Regalia();
/* 154 */       this.regalia.setIdDepartamento(this.departamento);
/* 155 */       this.regalia.setIdCooperativa(this.cooperativa);
/* 156 */       this.regalia.setExpIdPaisDestino(new Pais());
/* 157 */       this.regalia.setExpFechaDeclaracion(new Date());
/*     */     }
/*     */     
/*     */ 
/* 161 */     cargarParametros();
/* 162 */     this.strTitulo = "";
/* 163 */     this.pagina = "/pages/regalias/listExportacion.jsf";
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 168 */       Date fecha = new Date();
/* 169 */       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
/* 170 */       String dateString = format.format(fecha);
/* 171 */       Date date = format.parse(dateString);
/*     */       
/* 173 */       this.tipoCambio = this.liquidacionEjbBeanLocal.obtenerTipoCambio(date, ParameterTableType.TIPO_MONEDA_DOLAR.getCode());
/* 174 */       if ((this.tipoCambio != null) && (this.tipoCambio.getId() != null)) {
/* 175 */         this.regalia.setTc(this.tipoCambio.getCotizacionOficial());
/*     */       } else {
/* 177 */         this.tipoCambio = new TipoCambio();
/* 178 */         throw new AdminException("No Existe Tipo de Cambio a la Fecha");
/*     */       }
/*     */       
/* 181 */       this.liquidacion = new Liquidacion();
/* 182 */       if (this.regalia.getId() != null)
/*     */       {
/* 184 */         System.out.println("EXISTE INFO:::::::::");
/* 185 */         listarLiquidacion(this.regalia.getId());
/*     */       }
/*     */       else {
/* 188 */         this.departamento = new Departamento();
/* 189 */         this.departamento = this.cooperativaEJBBeanLocal.departamentoById(new Integer(1));
/*     */         
/*     */ 
/* 192 */         this.liquidacion.setIdRegalia(this.regalia);
/* 193 */         this.liquidacion.setMinIdCotizacionMineral(new CotizacionMineral());
/* 194 */         this.liquidacion.setMinIdCotizacionMineralDesc(new CotizacionMineralDesc());
/* 195 */         this.liquidacion.setMinIdMunicipio(new Municipio());
/*     */         
/* 197 */         this.totalRM = new BigDecimal(0);
/* 198 */         this.totalDistriGobernacion = new BigDecimal(0);
/* 199 */         this.totalDistriMunicipio = new BigDecimal(0);
/*     */       }
/*     */     } catch (AdminException e) {
/* 202 */       Util.CrearMsgErrorFatalGenerico(e.getMessage());
/*     */     } catch (ParseException e){
                Util.CrearMsgErrorFatalGenerico(e.getMessage());
              }
/*     */   }
/*     */   
/*     */   public void eliminarMineral(Liquidacion liquidacionParam)
/*     */   {
/*     */     try {
/* 209 */       this.liquidacion = liquidacionParam;
/* 210 */       this.liquidacionEjbBeanLocal.eliminarMineral(this.liquidacion);
/* 211 */       listarLiquidacion(this.regalia.getId());
/* 212 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 215 */       Util.CrearMsgErrorRegistro();
/* 216 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void guardarExportacion()
/*     */   {
/* 223 */     String nroTramite = "";
/*     */     
/*     */     try
/*     */     {
/* 227 */       this.regalia.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 228 */       this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 229 */       this.regalia.setIndEstadoPago(ParameterTableType.ESTADO_PAGO_PENDIENTE.getCode());
/* 230 */       this.regalia.setIndTipoFormulario(ParameterTableType.IND_FORM_EXPORTACION.getCode());
/* 231 */       this.liquidacion.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */       
/*     */ 
/* 234 */       DateTime fecha = new DateTime();
/* 235 */       Integer anio = Integer.valueOf(fecha.getYear());
/* 236 */       Integer mes = Integer.valueOf(fecha.getMonthOfYear());
/* 237 */       this.parameter = this.serviciosUtilBeanLocal.ParameterById(ParameterTableType.COORELATIVO_FORMULARIOS.getCode());
/* 238 */       Integer correlativo = Integer.valueOf(this.parameter.getNumero1().intValue() + 1);
/* 239 */       this.parameter.setNumero1(correlativo);
/* 240 */       this.parametrosMLBeanLocal.guardarParameter(this.parameter);
/*     */       
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
/*     */   public StreamedContent getReporteBoletaPago()
/*     */     throws SQLException
/*     */   {
/* 259 */     Locale espanol = new Locale("es", "ES");
/* 260 */     SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", espanol);
/* 261 */     String fecha = formateador.format(this.regalia.getFechaEmision());
/* 262 */     String fechaText = "La Paz, " + fecha;
/*     */     
/* 264 */     Map<String, Object> parametros = new HashMap();
/* 265 */     parametros.put("idRegalia", this.regalia.getId());
/* 266 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 267 */     parametros.put("fechaText", fechaText);
/*     */     
/* 269 */     return this.mr.runReporte("form", "rmBoletaPagoExp", parametros, "pdf", "rmBoletaPagoExp", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteFormEmision()
/*     */     throws SQLException
/*     */   {
/* 275 */     Map<String, Object> parametros = new HashMap();
/* 276 */     parametros.put("idRegalia", this.regalia.getId());
/* 277 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/* 278 */     return this.mr.runReporte("form", "rmExportacion", parametros, "pdf", "rmExportacion", Integer.valueOf(3));
/*     */   }
/*     */   
/*     */ 
/*     */   public void adicionarMineral()
/*     */   {
/* 284 */     if ((this.liquidacion.getMinIdMunicipio().getId() != null) && 
/* 285 */       (this.liquidacion.getMinIdCotizacionMineral().getId() != null) && 
/* 286 */       (this.liquidacion.getMinIdCotizacionMineralDesc().getId() != null) && 
/* 287 */       (this.liquidacion.getMinIndPresentacion() != null) && 
/* 288 */       (this.liquidacion.getMinPnsKg() != null) && 
/* 289 */       (this.liquidacion.getMinLey() != null) && 
/* 290 */       (this.liquidacion.getMinIndUnidad() != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 296 */       BigDecimal factor = new BigDecimal(0);
/* 297 */       if (this.liquidacion.getMinIndUnidad().intValue() == ParameterTableType.IND_UNIDAD_MEDIDA_GTM.getCode().intValue()) {
/* 298 */         factor = new BigDecimal(1000000);
/* 299 */       } else if (this.liquidacion.getMinIndUnidad().intValue() == ParameterTableType.IND_UNIDAD_MEDIDA_PORCENTAJE.getCode().intValue()) {
/* 300 */         factor = new BigDecimal(100);
/*     */       }
/*     */       
/* 303 */       this.liquidacion.setMinIdCotizacionMineral((CotizacionMineral)this.mapSelectMineral.get(this.liquidacion.getMinIdCotizacionMineral().getId()));
/*     */       
/*     */ 
/* 306 */       this.liquidacion.setMinPfKg(this.liquidacion.getMinPnsKg().multiply(this.liquidacion.getMinLey().divide(factor)));
/*     */       
/*     */ 
/* 309 */       String idFactor = (String)this.mapCotizacionUnidadMedida.get(this.liquidacion.getMinIndUnidadMedCoti());
/* 310 */       BigDecimal factorConversion = new BigDecimal(((String)this.mapCotizacionFactorConversion.get(idFactor)).toString());
/* 311 */       this.liquidacion.setMinPfUm(this.liquidacion.getMinPfKg().multiply(factorConversion).setScale(4, 4));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 316 */       this.liquidacion.setMinVbvUsd(this.liquidacion.getMinPfUm().multiply(this.liquidacion.getMinCotizacion()).setScale(4, 4));
/*     */       
/*     */ 
/* 319 */       this.liquidacion.setMinVbvBs(this.liquidacion.getMinVbvUsd().multiply(this.regalia.getTc()).setScale(4, 4));
/*     */       
/*     */ 
/* 322 */       BigDecimal factoAlicuota = new BigDecimal(100);
/*     */       
/* 324 */       this.liquidacion.setMinRmBs(this.liquidacion.getMinVbvBs().multiply(this.liquidacion.getMinAlicuota().divide(factoAlicuota)).setScale(2, 4));
/*     */       
/*     */ 
/* 327 */       BigDecimal gobernacion = this.liquidacion.getMinRmBs().multiply(new BigDecimal(0.85D));
/* 328 */       BigDecimal municipio = this.liquidacion.getMinRmBs().multiply(new BigDecimal(0.15D));
/*     */       
/* 330 */       this.liquidacion.setMinDistribucionGobernacion(gobernacion.setScale(0, 4));
/* 331 */       this.liquidacion.setMinDistribucionMunicipio(municipio.setScale(0, 4));
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 336 */         this.regalia.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 337 */         this.regalia.setIndEstadoFormulario(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 338 */         this.regalia.setIndEstadoPago(ParameterTableType.ESTADO_PAGO_PENDIENTE.getCode());
/* 339 */         this.regalia.setIndTipoFormulario(ParameterTableType.IND_FORM_EXPORTACION.getCode());
/*     */         
/* 341 */         this.liquidacion.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */         
/* 343 */         this.liquidacionEjbBeanLocal.guardarLiquidacionExportacion(this.regalia, this.liquidacion);
/*     */         
/* 345 */         Util.CrearMsgGuardado();
/*     */       }
/*     */       catch (AdminException e) {
/* 348 */         Util.CrearMsgErrorRegistro();
/* 349 */         e.printStackTrace();
/*     */       }
/*     */       
/* 352 */       listarLiquidacion(this.regalia.getId());
/*     */     }
/*     */     else {
/* 355 */       Util.CrearMsgErrorFatalGenerico("Faltan Datos que completar");
/*     */     }
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
/* 386 */       this.totalRM = this.totalRM.setScale(0, 4);
/*     */     }
/*     */     catch (AdminException e) {
/* 389 */       Util.CrearMsgErrorObtenerInfo();
/* 390 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void guardarFormulario()
/*     */   {
/* 400 */     System.out.println("VALOR::::::: " + this.liquidacion.getId());
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
/* 419 */       this.selectPais = new ArrayList();
/* 420 */       this.selectPais.add(new SelectItem(null, "Seleccione..."));
/* 421 */       List<Pais> listPais = new ArrayList();
/* 422 */       listPais = this.cooperativaEJBBeanLocal.listadoPais();
/* 423 */       for (Pais pais : listPais) {
/* 424 */         this.selectPais.add(new SelectItem(pais.getId(), pais.getNombre()));
/*     */       }
/*     */       
/* 427 */       this.selectAduanaSalida = new ArrayList();
/* 428 */       this.selectAduanaSalida.add(new SelectItem(null, "Seleccionar..."));
/* 429 */       this.parameterTable = new ArrayList();
/* 430 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_ADUANA_SALIDA.getCode());
/* 431 */       for (ParameterTable param : this.parameterTable) {
/* 432 */         this.selectAduanaSalida.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 435 */       this.selectLaboratorio = new ArrayList();
/* 436 */       this.selectLaboratorio.add(new SelectItem(null, "Seleccionar..."));
/* 437 */       this.parameterTable = new ArrayList();
/* 438 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_TIPO_LABORATORIO.getCode());
/* 439 */       for (ParameterTable param : this.parameterTable) {
/* 440 */         this.selectLaboratorio.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 443 */       this.mapMunicipio = new HashMap();
/* 444 */       this.selectMunicipio = new ArrayList();
/* 445 */       this.selectMunicipio.add(new SelectItem(null, "Seleccione..."));
/* 446 */       List<Municipio> listMun = new ArrayList();
/* 447 */       listMun = this.cooperativaEJBBeanLocal.listadoMunicipio(Constantes.ID_MUNICIPIO_LA_PAZ);
/* 448 */       for (Municipio mun : listMun) {
/* 449 */         this.selectMunicipio.add(new SelectItem(mun.getId(), mun.getNombre()));
/* 450 */         this.mapMunicipio.put(mun.getId(), mun.getNombre());
/*     */       }
/*     */       
/* 453 */       this.selectMineral = new ArrayList();
/* 454 */       this.mapSelectMineral = new HashMap();
/* 455 */       this.selectMineral.add(new SelectItem(null, "Seleccione..."));
/* 456 */       // Object cotMineral = new ArrayList();
/* 457 */       List<CotizacionMineral> cotMineral = this.liquidacionEjbBeanLocal.listCotizacionMineral();
/* 458 */       for (CotizacionMineral list : cotMineral) {
/* 459 */         this.selectMineral.add(new SelectItem(list.getId(), list.getNombre()));
/* 460 */         this.mapSelectMineral.put(list.getId(), list);
/*     */       }
/*     */       
/* 463 */       this.selectPresentacion = new ArrayList();
/* 464 */       this.selectPresentacion.add(new SelectItem(null, "Seleccione..."));
/* 465 */       this.parameterTable = new ArrayList();
/* 466 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 467 */       for (ParameterTable param : this.parameterTable) {
/* 468 */         this.selectPresentacion.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 471 */       this.selectOpcionMineral = new ArrayList();
/* 472 */       this.selectOpcionMineral.add(new SelectItem(null, ""));
/* 473 */       this.parameterTable = new ArrayList();
/* 474 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.SELECT_TIPO_MINERAL.getCode());
/* 475 */       for (ParameterTable param : this.parameterTable) {
/* 476 */         this.selectOpcionMineral.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/* 479 */       this.mapCotizacionFactorConversion = new HashMap();
/* 480 */       this.mapCotizacionUnidadMedida = new HashMap();
/* 481 */       this.parameterTable = new ArrayList();
/* 482 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_COTIZACION_UNIDAD_MEDIDA.getCode());
/* 483 */       for (ParameterTable param : this.parameterTable) {
/* 484 */         this.mapCotizacionUnidadMedida.put(param.getId(), param.getNombre());
/* 485 */         this.mapCotizacionFactorConversion.put(param.getNombre(), param.getTexto1());
/*     */       }
/*     */       
/* 488 */       this.mapDepartamento = new HashMap();
/* 489 */       // Object listDepar = new ArrayList();
/* 490 */       List<Departamento> listDepar = this.cooperativaEJBBeanLocal.listadoDepartamento();
/* 491 */       for (Departamento dep : listDepar) {
/* 492 */         this.mapDepartamento.put(dep.getId(), dep.getNombre());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 578 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void buscarDescripcionMineral()
/*     */   {
/* 584 */     this.selectMineralDescripcion = new ArrayList();
/* 585 */     this.selectMineralDescripcion.add(new SelectItem(null, "Seleccione..."));
/* 586 */     List<CotizacionMineralDesc> minDesc = new ArrayList();
/*     */     try
/*     */     {
/* 589 */       minDesc = this.liquidacionEjbBeanLocal.listCotizacionMineralDesc(this.liquidacion.getMinIdCotizacionMineral().getId());
/* 590 */       for (CotizacionMineralDesc desc : minDesc) {
/* 591 */         this.selectMineralDescripcion.add(new SelectItem(desc.getId(), desc.getNombre()));
/*     */       }
/*     */       
/* 594 */       CotizacionMineral cotizacionMineral = new CotizacionMineral();
/* 595 */       cotizacionMineral = (CotizacionMineral)this.mapSelectMineral.get(this.liquidacion.getMinIdCotizacionMineral().getId());
/*     */       
/* 597 */       this.liquidacion.setMinCotizacion(cotizacionMineral.getCotizacionOficial());
/* 598 */       this.liquidacion.setMinIndUnidadMedCoti(cotizacionMineral.getIndUnidadMedida());
/* 599 */       this.liquidacion.setMinAlicuota(cotizacionMineral.getAlicuotaExportaciones());
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 603 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void seleccionMinerales()
/*     */   {
/* 610 */     this.strTitulo = "Seleccionar minerales";
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
/*     */   private String generacionCorrelativo(Integer cor)
/*     */   {
/* 656 */     String compuesto = "";
/* 657 */     if (cor.toString().length() == 1) {
/* 658 */       compuesto = "0000" + cor;
/* 659 */     } else if (cor.toString().length() == 2) {
/* 660 */       compuesto = "000" + cor;
/* 661 */     } else if (cor.toString().length() == 3) {
/* 662 */       compuesto = "00" + cor;
/* 663 */     } else if (cor.toString().length() == 4) {
/* 664 */       compuesto = "0" + cor;
/*     */     }
/*     */     
/* 667 */     return compuesto;
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 671 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 675 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPresentacion() {
/* 679 */     return this.selectPresentacion;
/*     */   }
/*     */   
/*     */   public void setSelectPresentacion(List<SelectItem> selectPresentacion) {
/* 683 */     this.selectPresentacion = selectPresentacion;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMunicipio() {
/* 687 */     return this.selectMunicipio;
/*     */   }
/*     */   
/*     */   public void setSelectMunicipio(List<SelectItem> selectMunicipio) {
/* 691 */     this.selectMunicipio = selectMunicipio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPais() {
/* 695 */     return this.selectPais;
/*     */   }
/*     */   
/*     */   public void setSelectPais(List<SelectItem> selectPais) {
/* 699 */     this.selectPais = selectPais;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 703 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 707 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectOpcionMineral() {
/* 711 */     return this.selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public void setSelectOpcionMineral(List<SelectItem> selectOpcionMineral) {
/* 715 */     this.selectOpcionMineral = selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectLaboratorio() {
/* 719 */     return this.selectLaboratorio;
/*     */   }
/*     */   
/*     */   public void setSelectLaboratorio(List<SelectItem> selectLaboratorio) {
/* 723 */     this.selectLaboratorio = selectLaboratorio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectAduanaSalida() {
/* 727 */     return this.selectAduanaSalida;
/*     */   }
/*     */   
/*     */   public void setSelectAduanaSalida(List<SelectItem> selectAduanaSalida) {
/* 731 */     this.selectAduanaSalida = selectAduanaSalida;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMineral() {
/* 735 */     return this.selectMineral;
/*     */   }
/*     */   
/*     */   public void setSelectMineral(List<SelectItem> selectMineral) {
/* 739 */     this.selectMineral = selectMineral;
/*     */   }
/*     */   
/*     */   public Map<Integer, CotizacionMineral> getMapSelectMineral() {
/* 743 */     return this.mapSelectMineral;
/*     */   }
/*     */   
/*     */   public void setMapSelectMineral(Map<Integer, CotizacionMineral> mapSelectMineral) {
/* 747 */     this.mapSelectMineral = mapSelectMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMineralDescripcion() {
/* 751 */     return this.selectMineralDescripcion;
/*     */   }
/*     */   
/*     */   public void setSelectMineralDescripcion(List<SelectItem> selectMineralDescripcion)
/*     */   {
/* 756 */     this.selectMineralDescripcion = selectMineralDescripcion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapCotizacionUnidadMedida() {
/* 760 */     return this.mapCotizacionUnidadMedida;
/*     */   }
/*     */   
/*     */   public void setMapCotizacionUnidadMedida(Map<Integer, String> mapCotizacionUnidadMedida)
/*     */   {
/* 765 */     this.mapCotizacionUnidadMedida = mapCotizacionUnidadMedida;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapDepartamento() {
/* 769 */     return this.mapDepartamento;
/*     */   }
/*     */   
/*     */   public void setMapDepartamento(Map<Integer, String> mapDepartamento) {
/* 773 */     this.mapDepartamento = mapDepartamento;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapMunicipio() {
/* 777 */     return this.mapMunicipio;
/*     */   }
/*     */   
/*     */   public void setMapMunicipio(Map<Integer, String> mapMunicipio) {
/* 781 */     this.mapMunicipio = mapMunicipio;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalRM() {
/* 785 */     return this.totalRM;
/*     */   }
/*     */   
/*     */   public void setTotalRM(BigDecimal totalRM) {
/* 789 */     this.totalRM = totalRM;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalDistriGobernacion() {
/* 793 */     return this.totalDistriGobernacion;
/*     */   }
/*     */   
/*     */   public void setTotalDistriGobernacion(BigDecimal totalDistriGobernacion) {
/* 797 */     this.totalDistriGobernacion = totalDistriGobernacion;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalDistriMunicipio() {
/* 801 */     return this.totalDistriMunicipio;
/*     */   }
/*     */   
/*     */   public void setTotalDistriMunicipio(BigDecimal totalDistriMunicipio) {
/* 805 */     this.totalDistriMunicipio = totalDistriMunicipio;
/*     */   }
/*     */   
/*     */   public Regalia getRegalia() {
/* 809 */     return this.regalia;
/*     */   }
/*     */   
/*     */   public void setRegalia(Regalia regalia) {
/* 813 */     this.regalia = regalia;
/*     */   }
/*     */   
/*     */   public Liquidacion getLiquidacion() {
/* 817 */     return this.liquidacion;
/*     */   }
/*     */   
/*     */   public void setLiquidacion(Liquidacion liquidacion) {
/* 821 */     this.liquidacion = liquidacion;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> getListLiquidacion() {
/* 825 */     return this.listLiquidacion;
/*     */   }
/*     */   
/*     */   public void setListLiquidacion(List<Liquidacion> listLiquidacion) {
/* 829 */     this.listLiquidacion = listLiquidacion;
/*     */   }
/*     */   
/*     */   public Liquidacion getLiquidacionSelect() {
/* 833 */     return this.liquidacionSelect;
/*     */   }
/*     */   
/*     */   public void setLiquidacionSelect(Liquidacion liquidacionSelect) {
/* 837 */     this.liquidacionSelect = liquidacionSelect;
/*     */   }
/*     */   
/*     */   public TipoCambio getTipoCambio() {
/* 841 */     return this.tipoCambio;
/*     */   }
/*     */   
/*     */   public void setTipoCambio(TipoCambio tipoCambio) {
/* 845 */     this.tipoCambio = tipoCambio;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\RegaliaExportacionBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */