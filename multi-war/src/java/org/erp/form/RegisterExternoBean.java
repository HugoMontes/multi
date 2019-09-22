/*     */ package org.erp.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ import org.erp.entities.Departamento;
/*     */ import org.erp.entities.Mineral;
/*     */ import org.erp.entities.Municipio;
/*     */ import org.erp.entities.Pais;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.entities.TransporteExterno;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.ParameterTableType;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="registerExternoBean")
/*     */ @ViewScoped
/*     */ public class RegisterExternoBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   InternoEjbBeanLocal internoEjbBeanLocal;
/*     */   @Inject
/*     */   CooperativaEJBBeanLocal cooperativaEJBBeanLocal;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String strTitulo;
/*     */   private String pagina;
/*     */   private List<ParameterTable> parameterTable;
/*     */   private Cooperativa cooperativa;
/*     */   private TransporteExterno transporteExterno;
/*     */   private List<SelectItem> selectTrancaSalida;
/*  77 */   private Map<Integer, String> mapTrancaSalida = null;
/*     */   
/*     */   private List<SelectItem> selectPresentacion;
/*  80 */   private Map<Integer, String> mapPresentacion = null;
/*     */   
/*     */   private List<SelectItem> selectDepartamento;
/*     */   
/*     */   private List<SelectItem> selectCiudad;
/*     */   private List<SelectItem> selectMineralMetal;
/*  86 */   private Map<Integer, String> mapMineralMetal = null;
/*     */   
/*     */   private List<SelectItem> selectExpTotalParcial;
/*  89 */   private Map<Integer, String> mapExpTotalParcial = null;
/*     */   
/*     */   private List<SelectItem> selectDescripcionMineral;
/*  92 */   private Map<Integer, String> mapDescripcionMineral = null;
/*     */   
/*     */   private List<SelectItem> selectMunicipio;
/*  95 */   private Map<Integer, String> mapCodigoMunicipio = null;
/*     */   
/*     */   private List<SelectItem> selectPais;
/*     */   
/*     */   private List<SelectItem> selectTipoTransporte;
/*     */   
/*     */   private List<Mineral> listMineralTodoMetal;
/*     */   
/*     */   private List<Mineral> listMineralTodoNoMetal;
/*     */   private List<Mineral> listMineralMetalSelect;
/*     */   private List<Mineral> listMineralNoMetalSelect;
/*     */   private List<SelectItem> selectOpcionMineral;
/*     */   private List<SelectItem> selectCiExpedido;
/*     */   private List<SelectItem> selectLaboratorio;
/*     */   private List<SelectItem> selectAduanaSalida;
/* 110 */   private Map<Integer, String> mapCodigoAduana = null;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean) {
/* 115 */     this.accesoBean = accesoBean;
/* 116 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/* 117 */     this.cooperativa = this.segUsuarioSession.getIdCooperativa();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/* 121 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/* 131 */       e.printStackTrace();
/* 132 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*     */     try
/*     */     {
/* 136 */       this.transporteExterno = ((TransporteExterno)JSFUtilities.getHttpSessionAttributeObject("externoId"));
/*     */     } catch (Exception e) {
/* 138 */       this.transporteExterno = new TransporteExterno();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 143 */     cargarParametros();
/*     */     
/* 145 */     this.strTitulo = "";
/* 146 */     this.pagina = "/pages/adm/listFormExterno.jsf";
/*     */     
/* 148 */     if (this.transporteExterno.getId() != null)
/*     */     {
/* 150 */       cargarMunicipio();
/*     */     }
/*     */     else {
/* 153 */       this.transporteExterno = new TransporteExterno();
/* 154 */       this.transporteExterno.setIdDepartamento(new Departamento());
/* 155 */       this.transporteExterno.setIdCooperativa(this.cooperativa);
/* 156 */       this.transporteExterno.setIdPais(new Pais());
/* 157 */       this.transporteExterno.setIdMunicipio(new Municipio());
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarFormulario()
/*     */   {
/*     */     try
/*     */     {
/* 165 */       this.transporteExterno.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 166 */       this.transporteExterno.setIndEstadoRegistro(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 167 */       this.internoEjbBeanLocal.guardarTransporteExterno(this.transporteExterno);
/*     */       
/* 169 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 172 */       Util.CrearMsgErrorRegistro();
/* 173 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 182 */       this.selectTrancaSalida = new ArrayList();
/* 183 */       this.selectTrancaSalida.add(new SelectItem(null, "Seleccione..."));
/* 184 */       this.mapTrancaSalida = new HashMap();
/* 185 */       this.parameterTable = new ArrayList();
/* 186 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.TRANCA_DE_SALIDA.getCode());
/* 187 */       for (ParameterTable param : this.parameterTable) {
/* 188 */         this.selectTrancaSalida.add(new SelectItem(param.getId(), param.getNombre()));
/* 189 */         this.mapTrancaSalida.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 193 */       this.selectPresentacion = new ArrayList();
/* 194 */       this.selectPresentacion.add(new SelectItem(null, "Seleccione..."));
/* 195 */       this.mapPresentacion = new HashMap();
/* 196 */       this.parameterTable = new ArrayList();
/* 197 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 198 */       for (ParameterTable param : this.parameterTable) {
/* 199 */         this.mapPresentacion.put(param.getId(), param.getNombre());
/* 200 */         this.selectPresentacion.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 204 */       this.selectDepartamento = new ArrayList();
/* 205 */       this.selectDepartamento.add(new SelectItem(null, "Seleccione..."));
/* 206 */       List<Departamento> listDepar = new ArrayList();
/* 207 */       listDepar = this.cooperativaEJBBeanLocal.listadoDepartamento();
/* 208 */       for (Departamento dep : listDepar) {
/* 209 */         this.selectDepartamento.add(new SelectItem(dep.getId(), dep.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 213 */       this.selectMineralMetal = new ArrayList();
/* 214 */       this.selectMineralMetal.add(new SelectItem(null, "Seleccione..."));
/* 215 */       this.mapMineralMetal = new HashMap();
/* 216 */       this.parameterTable = new ArrayList();
/* 217 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CARACTERISTICAS_MINERAL_METAL.getCode());
/* 218 */       for (ParameterTable param : this.parameterTable) {
/* 219 */         this.mapMineralMetal.put(param.getId(), param.getNombre());
/* 220 */         this.selectMineralMetal.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 224 */       this.selectExpTotalParcial = new ArrayList();
/* 225 */       this.selectExpTotalParcial.add(new SelectItem(null, "Seleccione..."));
/* 226 */       this.mapExpTotalParcial = new HashMap();
/* 227 */       this.parameterTable = new ArrayList();
/* 228 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.EXPORTACION_TOTAL_PARCIAL.getCode());
/* 229 */       for (ParameterTable param : this.parameterTable) {
/* 230 */         this.mapExpTotalParcial.put(param.getId(), param.getNombre());
/* 231 */         this.selectExpTotalParcial.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 235 */       this.selectDescripcionMineral = new ArrayList();
/* 236 */       this.selectDescripcionMineral.add(new SelectItem(null, "Seleccione..."));
/* 237 */       this.mapDescripcionMineral = new HashMap();
/* 238 */       this.parameterTable = new ArrayList();
/* 239 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.DESCRIPCION_DEL_MINERAL_EXPORTACION.getCode());
/* 240 */       for (ParameterTable param : this.parameterTable) {
/* 241 */         this.mapDescripcionMineral.put(param.getId(), param.getNombre());
/* 242 */         this.selectDescripcionMineral.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 247 */       this.selectPais = new ArrayList();
/* 248 */       this.selectPais.add(new SelectItem(null, "Seleccione..."));
/* 249 */       Object listPais = new ArrayList();
/* 250 */       listPais = this.cooperativaEJBBeanLocal.listadoPais();
/* 251 */       for (Pais pais : (List)listPais) {
/* 252 */         this.selectPais.add(new SelectItem(pais.getId(), pais.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 256 */       this.selectTipoTransporte = new ArrayList();
/* 257 */       this.selectTipoTransporte.add(new SelectItem(null, "Seleccione..."));
/* 258 */       this.parameterTable = new ArrayList();
/* 259 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.TIPO_TRANSPORTE_EXPORTACION.getCode());
/* 260 */       for (ParameterTable param : this.parameterTable) {
/* 261 */         this.selectTipoTransporte.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 265 */       this.listMineralTodoMetal = new ArrayList();
/* 266 */       this.parameterTable = new ArrayList();
/* 267 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.MINERAL_METALICO.getCode());
/*     */       
/* 269 */       for (ParameterTable param : this.parameterTable) {
/* 270 */         Mineral mineral = new Mineral();
/* 271 */         mineral.setId(param.getId());
/* 272 */         mineral.setOtroUno(param.getNombre());
/*     */         
/* 274 */         this.listMineralTodoMetal.add(mineral);
/*     */       }
/*     */       
/*     */ 
/* 278 */       this.listMineralTodoNoMetal = new ArrayList();
/* 279 */       this.parameterTable = new ArrayList();
/* 280 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.MINERAL_NO_METALICO.getCode());
/*     */       
/* 282 */       for (ParameterTable param : this.parameterTable) {
/* 283 */         Mineral mineralNoMetal = new Mineral();
/* 284 */         mineralNoMetal.setId(param.getId());
/* 285 */         mineralNoMetal.setOtroUno(param.getNombre());
/*     */         
/* 287 */         this.listMineralTodoNoMetal.add(mineralNoMetal);
/*     */       }
/*     */       
/*     */ 
/* 291 */       this.selectOpcionMineral = new ArrayList();
/* 292 */       this.selectOpcionMineral.add(new SelectItem(null, ""));
/* 293 */       this.parameterTable = new ArrayList();
/* 294 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.SELECT_TIPO_MINERAL.getCode());
/* 295 */       for (ParameterTable param : this.parameterTable) {
/* 296 */         this.selectOpcionMineral.add(new SelectItem(param.getNombre(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 300 */       this.selectCiExpedido = new ArrayList();
/* 301 */       this.selectCiExpedido.add(new SelectItem(null, "Selec."));
/* 302 */       this.parameterTable = new ArrayList();
/* 303 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CI_EXPEDIDO.getCode());
/* 304 */       for (ParameterTable param : this.parameterTable) {
/* 305 */         this.selectCiExpedido.add(new SelectItem(param.getId(), param.getDescripcion()));
/*     */       }
/*     */       
/*     */ 
/* 309 */       this.selectLaboratorio = new ArrayList();
/* 310 */       this.selectLaboratorio.add(new SelectItem(null, "Seleccionar..."));
/* 311 */       this.parameterTable = new ArrayList();
/* 312 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_TIPO_LABORATORIO.getCode());
/* 313 */       for (ParameterTable param : this.parameterTable) {
/* 314 */         this.selectLaboratorio.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 319 */       this.mapCodigoAduana = new HashMap();
/*     */       
/* 321 */       this.selectAduanaSalida = new ArrayList();
/* 322 */       this.selectAduanaSalida.add(new SelectItem(null, "Seleccionar..."));
/* 323 */       this.parameterTable = new ArrayList();
/* 324 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_ADUANA_SALIDA.getCode());
/* 325 */       for (ParameterTable param : this.parameterTable) {
/* 326 */         this.selectAduanaSalida.add(new SelectItem(param.getId(), param.getNombre()));
/* 327 */         this.mapCodigoAduana.put(param.getId(), param.getTexto1());
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/*     */ 
/* 335 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void seleccionMinerales() {
/* 340 */     this.strTitulo = "Seleccionar minerales";
/*     */   }
/*     */   
/*     */ 
/*     */   public void seleccionCodigoAduana()
/*     */   {
/* 346 */     this.transporteExterno.setDestCodAduana(((String)this.mapCodigoAduana.get(this.transporteExterno.getIndAduanaSalida())).toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calculoFinal()
/*     */   {
/* 354 */     BigDecimal pesoNetoBrupo = null;
/* 355 */     BigDecimal tara = null;
/* 356 */     if ((this.transporteExterno.getCarPesoBrutoHumedo() != null) && (!this.transporteExterno.getCarPesoBrutoHumedo().isEmpty())) {
/* 357 */       pesoNetoBrupo = new BigDecimal(this.transporteExterno.getCarPesoBrutoHumedo());
/*     */     } else {
/* 359 */       pesoNetoBrupo = new BigDecimal(0);
/*     */     }
/*     */     
/* 362 */     if ((this.transporteExterno.getCarLara() != null) && (!this.transporteExterno.getCarLara().isEmpty())) {
/* 363 */       tara = new BigDecimal(this.transporteExterno.getCarLara());
/*     */     } else {
/* 365 */       tara = new BigDecimal(0);
/*     */     }
/*     */     
/* 368 */     BigDecimal resultado = pesoNetoBrupo.subtract(tara).setScale(5, 4);
/*     */     
/* 370 */     this.transporteExterno.setCarPesoNetoHumedo(resultado.toString());
/*     */     
/*     */ 
/*     */ 
/* 374 */     Float humedad = Float.valueOf(0.0F);
/* 375 */     Float merma = Float.valueOf(0.0F);
/* 376 */     Float pesoNetoSeco = Float.valueOf(0.0F);
/* 377 */     Float pesoNetoHumedo = Float.valueOf(0.0F);
/*     */     
/* 379 */     if ((this.transporteExterno.getCarHumedad() != null) && (!this.transporteExterno.getCarHumedad().isEmpty())) {
/* 380 */       humedad = new Float(this.transporteExterno.getCarHumedad());
/*     */     } else {
/* 382 */       humedad = Float.valueOf(0.0F);
/*     */     }
/*     */     
/* 385 */     if ((this.transporteExterno.getCarMerma() != null) && (!this.transporteExterno.getCarMerma().isEmpty())) {
/* 386 */       merma = new Float(this.transporteExterno.getCarMerma());
/*     */     } else {
/* 388 */       merma = Float.valueOf(0.0F);
/*     */     }
/*     */     
/*     */ 
/* 392 */     if ((this.transporteExterno.getCarPesoNetoHumedo() != null) && (!this.transporteExterno.getCarPesoNetoHumedo().isEmpty())) {
/* 393 */       pesoNetoHumedo = new Float(this.transporteExterno.getCarPesoNetoHumedo());
/*     */     } else {
/* 395 */       pesoNetoHumedo = Float.valueOf(1.0F);
/*     */     }
/*     */     
/* 398 */     pesoNetoSeco = Float.valueOf(pesoNetoHumedo.floatValue() - humedad.floatValue() / 100.0F * pesoNetoHumedo.floatValue() - merma.floatValue() / 100.0F * pesoNetoHumedo.floatValue());
/*     */     
/* 400 */     this.transporteExterno.setCarPesoNetoSeco(pesoNetoSeco.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void cargarTotalParcial()
/*     */   {
/* 407 */     if (this.transporteExterno.getCarIndExportTotalParcial().equals(ParameterTableType.TIPO_EXPORTACION_PARCIAL.getCode())) {
/* 408 */       this.transporteExterno.setCarCantidadTotalParcial("");
/*     */     }
/*     */   }
/*     */   
/*     */   public void mineralesSeleccionados()
/*     */   {
/* 414 */     String compuesto = "";
/* 415 */     if (this.listMineralMetalSelect.size() > 0) {
/* 416 */       int t = this.listMineralMetalSelect.size() - 1;
/* 417 */       for (int i = 0; i < this.listMineralMetalSelect.size() - 1; i++)
/*     */       {
/* 419 */         compuesto = compuesto + ((Mineral)this.listMineralMetalSelect.get(i)).getOtroUno().trim() + " " + (((Mineral)this.listMineralMetalSelect.get(i)).getOtroDos() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(i)).getOtroDos()) + (((Mineral)this.listMineralMetalSelect.get(i)).getOtroTres() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(i)).getOtroTres()) + ",";
/*     */       }
/*     */       
/* 422 */       compuesto = compuesto + ((Mineral)this.listMineralMetalSelect.get(t)).getOtroUno().trim() + " " + (((Mineral)this.listMineralMetalSelect.get(t)).getOtroDos() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(t)).getOtroDos()) + (((Mineral)this.listMineralMetalSelect.get(t)).getOtroTres() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(t)).getOtroTres());
/*     */     }
/*     */     
/* 425 */     this.transporteExterno.setCarLeyCampoUno(compuesto);
/*     */     
/* 427 */     compuesto = "";
/* 428 */     if (this.listMineralNoMetalSelect.size() > 0)
/*     */     {
/* 430 */       int te = this.listMineralNoMetalSelect.size() - 1;
/* 431 */       for (int i = 0; i < this.listMineralNoMetalSelect.size() - 1; i++)
/*     */       {
/* 433 */         compuesto = compuesto + ((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroUno().trim() + " " + (((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroDos() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroDos()) + (((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroTres() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroTres()) + ",";
/*     */       }
/*     */       
/*     */ 
/* 437 */       compuesto = compuesto + ((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroUno().trim() + " " + (((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroDos() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroDos()) + (((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroTres() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroTres());
/*     */     }
/* 439 */     this.transporteExterno.setCarLeyCampoDos(compuesto);
/*     */   }
/*     */   
/*     */   public void cargarMunicipio()
/*     */   {
/*     */     try {
/* 445 */       this.selectMunicipio = new ArrayList();
/* 446 */       this.selectMunicipio.add(new SelectItem(null, "Seleccione..."));
/* 447 */       this.mapCodigoMunicipio = new HashMap();
/* 448 */       List<Municipio> listMun = new ArrayList();
/* 449 */       listMun = this.cooperativaEJBBeanLocal.listadoMunicipio(this.transporteExterno.getIdDepartamento().getId());
/* 450 */       for (Municipio mun : listMun) {
/* 451 */         this.selectMunicipio.add(new SelectItem(mun.getId(), mun.getNombre()));
/* 452 */         this.mapCodigoMunicipio.put(mun.getId(), mun.getCodigo());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 457 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 462 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 466 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectTrancaSalida() {
/* 470 */     return this.selectTrancaSalida;
/*     */   }
/*     */   
/*     */   public void setSelectTrancaSalida(List<SelectItem> selectTrancaSalida) {
/* 474 */     this.selectTrancaSalida = selectTrancaSalida;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTrancaSalida() {
/* 478 */     return this.mapTrancaSalida;
/*     */   }
/*     */   
/*     */   public void setMapTrancaSalida(Map<Integer, String> mapTrancaSalida) {
/* 482 */     this.mapTrancaSalida = mapTrancaSalida;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPresentacion() {
/* 486 */     return this.selectPresentacion;
/*     */   }
/*     */   
/*     */   public void setSelectPresentacion(List<SelectItem> selectPresentacion) {
/* 490 */     this.selectPresentacion = selectPresentacion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapPresentacion() {
/* 494 */     return this.mapPresentacion;
/*     */   }
/*     */   
/*     */   public void setMapPresentacion(Map<Integer, String> mapPresentacion) {
/* 498 */     this.mapPresentacion = mapPresentacion;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectDepartamento() {
/* 502 */     return this.selectDepartamento;
/*     */   }
/*     */   
/*     */   public void setSelectDepartamento(List<SelectItem> selectDepartamento) {
/* 506 */     this.selectDepartamento = selectDepartamento;
/*     */   }
/*     */   
/*     */   public TransporteExterno getTransporteExterno() {
/* 510 */     return this.transporteExterno;
/*     */   }
/*     */   
/*     */   public void setTransporteExterno(TransporteExterno transporteExterno) {
/* 514 */     this.transporteExterno = transporteExterno;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMineralMetal() {
/* 518 */     return this.selectMineralMetal;
/*     */   }
/*     */   
/*     */   public void setSelectMineralMetal(List<SelectItem> selectMineralMetal) {
/* 522 */     this.selectMineralMetal = selectMineralMetal;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapMineralMetal() {
/* 526 */     return this.mapMineralMetal;
/*     */   }
/*     */   
/*     */   public void setMapMineralMetal(Map<Integer, String> mapMineralMetal) {
/* 530 */     this.mapMineralMetal = mapMineralMetal;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectExpTotalParcial() {
/* 534 */     return this.selectExpTotalParcial;
/*     */   }
/*     */   
/*     */   public void setSelectExpTotalParcial(List<SelectItem> selectExpTotalParcial) {
/* 538 */     this.selectExpTotalParcial = selectExpTotalParcial;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapExpTotalParcial() {
/* 542 */     return this.mapExpTotalParcial;
/*     */   }
/*     */   
/*     */   public void setMapExpTotalParcial(Map<Integer, String> mapExpTotalParcial) {
/* 546 */     this.mapExpTotalParcial = mapExpTotalParcial;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectDescripcionMineral() {
/* 550 */     return this.selectDescripcionMineral;
/*     */   }
/*     */   
/*     */   public void setSelectDescripcionMineral(List<SelectItem> selectDescripcionMineral)
/*     */   {
/* 555 */     this.selectDescripcionMineral = selectDescripcionMineral;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapDescripcionMineral() {
/* 559 */     return this.mapDescripcionMineral;
/*     */   }
/*     */   
/*     */   public void setMapDescripcionMineral(Map<Integer, String> mapDescripcionMineral) {
/* 563 */     this.mapDescripcionMineral = mapDescripcionMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMunicipio() {
/* 567 */     return this.selectMunicipio;
/*     */   }
/*     */   
/*     */   public void setSelectMunicipio(List<SelectItem> selectMunicipio) {
/* 571 */     this.selectMunicipio = selectMunicipio;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapCodigoMunicipio() {
/* 575 */     return this.mapCodigoMunicipio;
/*     */   }
/*     */   
/*     */   public void setMapCodigoMunicipio(Map<Integer, String> mapCodigoMunicipio) {
/* 579 */     this.mapCodigoMunicipio = mapCodigoMunicipio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPais() {
/* 583 */     return this.selectPais;
/*     */   }
/*     */   
/*     */   public void setSelectPais(List<SelectItem> selectPais) {
/* 587 */     this.selectPais = selectPais;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectTipoTransporte() {
/* 591 */     return this.selectTipoTransporte;
/*     */   }
/*     */   
/*     */   public void setSelectTipoTransporte(List<SelectItem> selectTipoTransporte) {
/* 595 */     this.selectTipoTransporte = selectTipoTransporte;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 599 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 603 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralTodoMetal() {
/* 607 */     return this.listMineralTodoMetal;
/*     */   }
/*     */   
/*     */   public void setListMineralTodoMetal(List<Mineral> listMineralTodoMetal) {
/* 611 */     this.listMineralTodoMetal = listMineralTodoMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralTodoNoMetal() {
/* 615 */     return this.listMineralTodoNoMetal;
/*     */   }
/*     */   
/*     */   public void setListMineralTodoNoMetal(List<Mineral> listMineralTodoNoMetal) {
/* 619 */     this.listMineralTodoNoMetal = listMineralTodoNoMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralMetalSelect() {
/* 623 */     return this.listMineralMetalSelect;
/*     */   }
/*     */   
/*     */   public void setListMineralMetalSelect(List<Mineral> listMineralMetalSelect) {
/* 627 */     this.listMineralMetalSelect = listMineralMetalSelect;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralNoMetalSelect() {
/* 631 */     return this.listMineralNoMetalSelect;
/*     */   }
/*     */   
/*     */   public void setListMineralNoMetalSelect(List<Mineral> listMineralNoMetalSelect) {
/* 635 */     this.listMineralNoMetalSelect = listMineralNoMetalSelect;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectOpcionMineral() {
/* 639 */     return this.selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public void setSelectOpcionMineral(List<SelectItem> selectOpcionMineral) {
/* 643 */     this.selectOpcionMineral = selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCiExpedido() {
/* 647 */     return this.selectCiExpedido;
/*     */   }
/*     */   
/*     */   public void setSelectCiExpedido(List<SelectItem> selectCiExpedido) {
/* 651 */     this.selectCiExpedido = selectCiExpedido;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectLaboratorio() {
/* 655 */     return this.selectLaboratorio;
/*     */   }
/*     */   
/*     */   public void setSelectLaboratorio(List<SelectItem> selectLaboratorio) {
/* 659 */     this.selectLaboratorio = selectLaboratorio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectAduanaSalida() {
/* 663 */     return this.selectAduanaSalida;
/*     */   }
/*     */   
/*     */   public void setSelectAduanaSalida(List<SelectItem> selectAduanaSalida) {
/* 667 */     this.selectAduanaSalida = selectAduanaSalida;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapCodigoAduana() {
/* 671 */     return this.mapCodigoAduana;
/*     */   }
/*     */   
/*     */   public void setMapCodigoAduana(Map<Integer, String> mapCodigoAduana) {
/* 675 */     this.mapCodigoAduana = mapCodigoAduana;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\RegisterExternoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */