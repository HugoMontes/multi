/*     */ package org.erp.adm;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
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
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.Actividad;
/*     */ import org.erp.entities.Ciudad;
/*     */ import org.erp.entities.ContratoMinero;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.Departamento;
/*     */ import org.erp.entities.DerechoMinero;
/*     */ import org.erp.entities.Mineral;
/*     */ import org.erp.entities.Municipio;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.entities.Sucursal;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.ParameterTableType;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.joda.time.DateTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="registerCooperativeBean")
/*     */ @ViewScoped
/*     */ public class RegisterCooperativeBean
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
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String strTitulo;
/*     */   private String pagina;
/*     */   private List<ParameterTable> parameterTable;
/*     */   private Cooperativa cooperativa;
/*     */   private List<SelectItem> selectActorProductivo;
/*  77 */   private Map<Integer, String> mapActorProductivo = null;
/*  78 */   private Map<Integer, String> mapRegionProMunicipio = null;
/*     */   
/*     */   private List<SelectItem> selectCiExpedido;
/*     */   private List<SelectItem> selectDepartamento;
/*     */   private List<SelectItem> selectMunicipio;
/*     */   private List<SelectItem> selectCiudad;
/*     */   private List<Sucursal> listSucursal;
/*     */   private Sucursal sucursal;
/*     */   private List<SelectItem> selectTipoOficina;
/*  87 */   private Map<Integer, String> mapTipoOficina = null;
/*     */   
/*     */   private List<Actividad> listTodasActividades;
/*     */   private List<Actividad> listActividadesSeleccionadas;
/*  91 */   private Map<Integer, String> mapActividad = null;
/*     */   
/*     */   private List<Mineral> listMineralTodoMetal;
/*     */   private List<Mineral> listMineralMetalSelect;
/*  95 */   private Map<Integer, String> mapMineralMetal = null;
/*     */   
/*     */   private List<Mineral> listMineralTodoNoMetal;
/*     */   private List<Mineral> listMineralNoMetalSelect;
/*  99 */   private Map<Integer, String> mapMineralNoMetal = null;
/*     */   
/* 101 */   private Map<Integer, String> mapLeyMineral = null;
/*     */   
/*     */   private List<DerechoMinero> listDerechoMinero;
/*     */   
/*     */   private DerechoMinero derechoMinero;
/*     */   private List<SelectItem> selectUnidadMedida;
/* 107 */   private Map<Integer, String> mapUnidadMedida = null;
/*     */   
/*     */   private List<ContratoMinero> listContratoMinero;
/*     */   
/*     */   private ContratoMinero contratoMinero;
/* 112 */   private Map<Integer, String> mapAtes = null;
/*     */   private List<SelectItem> selectMunicipioDerMin;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/* 119 */     this.accesoBean = accesoBean;
/* 120 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/* 124 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
///*     */     try {
///*     */       
///*     */     }
///*     */     catch (IOException e) {
///* 134 */       e.printStackTrace();
///* 135 */       this.logger.info("Error en la validación de sesión");
///*     */     }
/*     */     try
/*     */     {
/* 139 */       this.cooperativa = ((Cooperativa)JSFUtilities.getHttpSessionAttributeObject("cooperativaId"));
/*     */     } catch (Exception e) {
/* 141 */       this.cooperativa = new Cooperativa();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 146 */     cargarParametros();
/*     */     
/* 148 */     this.strTitulo = "";
/* 149 */     this.pagina = "/pages/adm/listCooperativa.jsf";
/*     */     
/* 151 */     if ((this.cooperativa != null) && (this.cooperativa.getId() != null)) {
/* 152 */       listarSucursales();
/* 153 */       listarActividadesSeleccionadas();
/* 154 */       listarMineralesSeleccionados();
/* 155 */       listarDerechoMinero();
/* 156 */       listarContratoMinero();
/*     */     }
/*     */     else {
/* 159 */       this.cooperativa = new Cooperativa();
/* 160 */       this.cooperativa.setFechaRegistro(new Date());
/*     */       
/* 162 */       DateTime fechaActual = new DateTime(new Date());
/* 163 */       fechaActual = fechaActual.plusYears(2);
/* 164 */       this.cooperativa.setFechaExpiracion(fechaActual.toDate());
/*     */       
/* 166 */       this.listSucursal = new ArrayList();
/* 167 */       this.listActividadesSeleccionadas = new ArrayList();
/*     */       
/* 169 */       this.listMineralMetalSelect = new ArrayList();
/* 170 */       this.listMineralNoMetalSelect = new ArrayList();
/*     */       
/* 172 */       this.listDerechoMinero = new ArrayList();
/* 173 */       this.derechoMinero = new DerechoMinero();
/* 174 */       this.derechoMinero.setIdMunicipio(new Municipio());
/*     */       
/* 176 */       this.listContratoMinero = new ArrayList();
/* 177 */       this.contratoMinero = new ContratoMinero();
/*     */     }
/* 179 */     this.sucursal = new Sucursal();
/* 180 */     this.sucursal.setIdDepartamento(new Departamento());
/* 181 */     this.sucursal.setIdMunicipio(new Municipio());
/* 182 */     this.sucursal.setIdCiudad(new Ciudad());
/*     */   }
/*     */   
/*     */   public void cargarMunicipio()
/*     */   {
/*     */     try
/*     */     {
/* 189 */       this.selectMunicipio = new ArrayList();
/* 190 */       this.selectMunicipio.add(new SelectItem(null, "Seleccione..."));
/* 191 */       List<Municipio> listMun = new ArrayList();
/* 192 */       listMun = this.cooperativaEJBBeanLocal.listadoMunicipio(this.sucursal.getIdDepartamento().getId());
/*     */       
/* 194 */       this.mapRegionProMunicipio = new HashMap();
/* 195 */       for (Municipio mun : listMun) {
/* 196 */         this.selectMunicipio.add(new SelectItem(mun.getId(), mun.getNombre()));
/* 197 */         this.mapRegionProMunicipio.put(mun.getId(), mun.getDescripcion());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 202 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadMunicipio() {}
/*     */   
/*     */   public void cargarCiudad()
/*     */   {
/*     */     try {
/* 211 */       this.selectCiudad = new ArrayList();
/* 212 */       this.selectCiudad.add(new SelectItem(null, "Seleccione..."));
/* 213 */       List<Ciudad> listCiu = new ArrayList();
/* 214 */       listCiu = this.cooperativaEJBBeanLocal.listadoCiudad(this.sucursal.getIdMunicipio().getId());
/* 215 */       for (Ciudad c : listCiu) {
/* 216 */         this.selectCiudad.add(new SelectItem(c.getId(), c.getNombre()));
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 221 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registrarSucursal() {
/* 226 */     this.strTitulo = "Registrar";
/*     */     
/* 228 */     this.sucursal = new Sucursal();
/* 229 */     this.sucursal.setIdDepartamento(new Departamento());
/* 230 */     this.sucursal.setIdMunicipio(new Municipio());
/* 231 */     this.sucursal.setIdCiudad(new Ciudad());
/*     */   }
/*     */   
/*     */   public void registrarDerechosMineros()
/*     */   {
/* 236 */     this.strTitulo = "Registrar";
/* 237 */     this.derechoMinero = new DerechoMinero();
/* 238 */     this.derechoMinero.setIdMunicipio(new Municipio());
/*     */   }
/*     */   
/*     */   public void registrarContratoMinero() {
/* 242 */     this.strTitulo = "Registrar";
/* 243 */     this.contratoMinero = new ContratoMinero();
/*     */   }
/*     */   
/*     */   public void modificarSucursal()
/*     */   {
/* 248 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarDerechosMineros()
/*     */   {
/* 253 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarContratoMineros()
/*     */   {
/* 258 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void guardarOperacion()
/*     */   {
/*     */     try
/*     */     {
/* 265 */       this.cooperativa.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 266 */       this.cooperativaEJBBeanLocal.guardarCooperativa(this.cooperativa);
/*     */       
/* 268 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 271 */       Util.CrearMsgErrorRegistro();
/* 272 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarSucursal()
/*     */   {
/*     */     try
/*     */     {
/* 280 */       if (this.cooperativa.getId() != null) {
/* 281 */         this.sucursal.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 282 */         this.sucursal.setIdCiudad(null);
/* 283 */         this.sucursal.setIdCooperativa(this.cooperativa);
/* 284 */         this.cooperativaEJBBeanLocal.guardarSucursal(this.sucursal);
/* 285 */         listarSucursales();
/* 286 */         Util.CrearMsgGuardado();
/*     */       }
/*     */       else {
/* 289 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 295 */       Util.CrearMsgErrorRegistro();
/* 296 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarSucursal()
/*     */   {
/*     */     try {
/* 303 */       if (this.cooperativa.getId() != null) {
/* 304 */         this.sucursal.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 305 */         this.cooperativaEJBBeanLocal.eliminarSucursal(this.sucursal);
/* 306 */         listarSucursales();
/* 307 */         Util.CrearMsgEliminado();
/*     */       }
/*     */       else {
/* 310 */         Util.CrearMsgERROR("Error en la eliminación de sucrusal", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 316 */       Util.CrearMsgErrorRegistro();
/* 317 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarDerechoMinero()
/*     */   {
/*     */     try {
/* 324 */       if (this.cooperativa.getId() != null) {
/* 325 */         this.derechoMinero.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 326 */         this.derechoMinero.setIdCooperativa(this.cooperativa);
/* 327 */         this.cooperativaEJBBeanLocal.guardarDerechoMinero(this.derechoMinero);
/* 328 */         listarDerechoMinero();
/* 329 */         Util.CrearMsgGuardado();
/*     */       }
/*     */       else {
/* 332 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 338 */       Util.CrearMsgErrorRegistro();
/* 339 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarDerechoMinero()
/*     */   {
/*     */     try {
/* 346 */       if (this.cooperativa.getId() != null) {
/* 347 */         this.derechoMinero.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 348 */         this.cooperativaEJBBeanLocal.eliminarDerechoMinero(this.derechoMinero);
/* 349 */         listarDerechoMinero();
/* 350 */         Util.CrearMsgEliminado();
/*     */       }
/*     */       else {
/* 353 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 359 */       Util.CrearMsgErrorRegistro();
/* 360 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarContratoMinero()
/*     */   {
/*     */     try {
/* 367 */       if (this.cooperativa.getId() != null) {
/* 368 */         this.contratoMinero.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 369 */         this.contratoMinero.setIdCooperativa(this.cooperativa);
/* 370 */         this.cooperativaEJBBeanLocal.guardarContratoMinero(this.contratoMinero);
/* 371 */         listarContratoMinero();
/* 372 */         Util.CrearMsgGuardado();
/*     */       }
/*     */       else {
/* 375 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 381 */       Util.CrearMsgErrorRegistro();
/* 382 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarContratoMinero()
/*     */   {
/*     */     try {
/* 389 */       if (this.cooperativa.getId() != null) {
/* 390 */         this.contratoMinero.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 391 */         this.cooperativaEJBBeanLocal.eliminarContratoMinero(this.contratoMinero);
/* 392 */         listarContratoMinero();
/* 393 */         Util.CrearMsgEliminado();
/*     */       }
/*     */       else {
/* 396 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 402 */       Util.CrearMsgErrorRegistro();
/* 403 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarActividad()
/*     */   {
/*     */     try {
/* 410 */       if (this.cooperativa.getId() != null) {
/* 411 */         if (this.listActividadesSeleccionadas.size() > 0)
/*     */         {
/* 413 */           this.cooperativaEJBBeanLocal.guardarEliminarActividades(this.cooperativa.getId());
/* 414 */           for (Actividad act : this.listActividadesSeleccionadas) {
/* 415 */             Actividad actividad = new Actividad();
/* 416 */             actividad.setIndActividad(act.getId());
/* 417 */             actividad.setIdCooperativa(this.cooperativa);
/* 418 */             actividad.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */             
/* 420 */             this.cooperativaEJBBeanLocal.guardarActividadCooperativa(actividad);
/*     */           }
/* 422 */           Util.CrearMsgGuardado();
/*     */         }
/*     */         else {
/* 425 */           Util.CrearMsgERROR("Tiene que seleccionar al menos una Actividad", null, false);
/*     */         }
/*     */       } else {
/* 428 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 434 */       Util.CrearMsgErrorRegistro();
/* 435 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarMineral()
/*     */   {
/*     */     try {
/* 442 */       if (this.cooperativa.getId() != null) {
/* 443 */         if ((this.listMineralMetalSelect.size() > 0) || (this.listMineralNoMetalSelect.size() > 0))
/*     */         {
/*     */ 
/*     */ 
/* 447 */           this.cooperativaEJBBeanLocal.guardarEliminarMinerales(this.cooperativa.getId());
/* 448 */           for (Mineral met : this.listMineralMetalSelect) {
/* 449 */             Mineral metalico = new Mineral();
/* 450 */             metalico.setIndMineral(met.getId());
/* 451 */             metalico.setIndMetalNoMetal(ParameterTableType.MINERAL_METALICOS.getCode());
/* 452 */             metalico.setIdCooperativa(this.cooperativa);
/* 453 */             metalico.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */             
/* 455 */             this.cooperativaEJBBeanLocal.guardarMinerales(metalico);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 460 */           for (Mineral noMet : this.listMineralNoMetalSelect) {
/* 461 */             Mineral noMetalico = new Mineral();
/* 462 */             noMetalico.setIndMineral(noMet.getId());
/* 463 */             noMetalico.setIndMetalNoMetal(ParameterTableType.MINERAL_NO_METALICOS.getCode());
/* 464 */             noMetalico.setIdCooperativa(this.cooperativa);
/* 465 */             noMetalico.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */             
/* 467 */             this.cooperativaEJBBeanLocal.guardarMinerales(noMetalico);
/*     */           }
/* 469 */           Util.CrearMsgGuardado();
/*     */         }
/*     */         else {
/* 472 */           Util.CrearMsgERROR("Tiene que seleccionar al menos un Mineral", null, false);
/*     */         }
/*     */       } else {
/* 475 */         Util.CrearMsgERROR("Debe completar la pestaña Información Genreral", null, false);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 481 */       Util.CrearMsgErrorRegistro();
/* 482 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarActividadesSeleccionadas()
/*     */   {
/* 488 */     this.listActividadesSeleccionadas = new ArrayList();
/*     */     try
/*     */     {
/* 491 */       List<Actividad> listaAct = this.cooperativaEJBBeanLocal.listadoActividadesRegistradas(this.cooperativa.getId());
/*     */       
/* 493 */       for (Actividad list : listaAct) {
/* 494 */         Actividad actividad = new Actividad();
/* 495 */         actividad.setId(list.getIndActividad());
/* 496 */         this.listActividadesSeleccionadas.add(actividad);
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 500 */       Util.CrearMsgErrorObtenerInfo();
/* 501 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarMineralesSeleccionados()
/*     */   {
/* 507 */     this.listMineralMetalSelect = new ArrayList();
/* 508 */     this.listMineralNoMetalSelect = new ArrayList();
/*     */     try {
/* 510 */       List<Mineral> listaMet = this.cooperativaEJBBeanLocal.listadoMinerales(this.cooperativa.getId(), ParameterTableType.MINERAL_METALICOS.getCode());
/*     */       
/* 512 */       for (Mineral lm : listaMet) {
/* 513 */         Mineral mineral = new Mineral();
/* 514 */         mineral.setId(lm.getIndMineral());
/* 515 */         this.listMineralMetalSelect.add(mineral);
/*     */       }
/*     */       
/* 518 */       List<Mineral> listaNoMet = this.cooperativaEJBBeanLocal.listadoMinerales(this.cooperativa.getId(), ParameterTableType.MINERAL_NO_METALICOS.getCode());
/*     */       
/* 520 */       for (Mineral nm : listaNoMet) {
/* 521 */         Mineral noMetal = new Mineral();
/* 522 */         noMetal.setId(nm.getIndMineral());
/* 523 */         this.listMineralNoMetalSelect.add(noMetal);
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 527 */       Util.CrearMsgErrorObtenerInfo();
/* 528 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarSucursales()
/*     */   {
/* 534 */     this.sucursal = new Sucursal();
/* 535 */     this.sucursal.setIdDepartamento(new Departamento());
/* 536 */     this.sucursal.setIdMunicipio(new Municipio());
/* 537 */     this.sucursal.setIdCiudad(new Ciudad());
/*     */     
/* 539 */     this.listSucursal = new ArrayList();
/*     */     try
/*     */     {
/* 542 */       this.listSucursal = this.cooperativaEJBBeanLocal.listadoSucursal(this.cooperativa.getId());
/*     */     } catch (AdminException e) {
/* 544 */       Util.CrearMsgErrorObtenerInfo();
/* 545 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarDerechoMinero()
/*     */   {
/* 551 */     this.derechoMinero = new DerechoMinero();
/* 552 */     this.derechoMinero.setIdMunicipio(new Municipio());
/*     */     
/* 554 */     this.listDerechoMinero = new ArrayList();
/*     */     try
/*     */     {
/* 557 */       this.listDerechoMinero = this.cooperativaEJBBeanLocal.listadoDerechoMinero(this.cooperativa.getId());
/*     */     } catch (AdminException e) {
/* 559 */       Util.CrearMsgErrorObtenerInfo();
/* 560 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarContratoMinero()
/*     */   {
/* 566 */     this.contratoMinero = new ContratoMinero();
/* 567 */     this.listContratoMinero = new ArrayList();
/*     */     try
/*     */     {
/* 570 */       this.listContratoMinero = this.cooperativaEJBBeanLocal.listadoContratoMinero(this.cooperativa.getId());
/*     */     } catch (AdminException e) {
/* 572 */       Util.CrearMsgErrorObtenerInfo();
/* 573 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 581 */       this.selectActorProductivo = new ArrayList();
/* 582 */       this.selectActorProductivo.add(new SelectItem(null, "Seleccione..."));
/* 583 */       this.mapActorProductivo = new HashMap();
/* 584 */       this.parameterTable = new ArrayList();
/* 585 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ACTOR_PRODUCTIVO.getCode());
/* 586 */       for (ParameterTable param : this.parameterTable) {
/* 587 */         this.selectActorProductivo.add(new SelectItem(param.getId(), param.getNombre()));
/* 588 */         this.mapActorProductivo.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 593 */       this.selectCiExpedido = new ArrayList();
/* 594 */       this.selectCiExpedido.add(new SelectItem(null, "Seleccione..."));
/* 595 */       this.parameterTable = new ArrayList();
/* 596 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CI_EXPEDIDO.getCode());
/* 597 */       for (ParameterTable param : this.parameterTable) {
/* 598 */         this.selectCiExpedido.add(new SelectItem(param.getId(), param.getDescripcion()));
/*     */       }
/*     */       
/*     */ 
/* 602 */       this.selectDepartamento = new ArrayList();
/* 603 */       this.selectDepartamento.add(new SelectItem(null, "Seleccione..."));
/* 604 */       List<Departamento> listDepar = new ArrayList();
/* 605 */       listDepar = this.cooperativaEJBBeanLocal.listadoDepartamento();
/* 606 */       for (Departamento dep : listDepar) {
/* 607 */         this.selectDepartamento.add(new SelectItem(dep.getId(), dep.getNombre()));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 612 */       this.selectTipoOficina = new ArrayList();
/* 613 */       this.selectTipoOficina.add(new SelectItem(null, "Seleccione..."));
/* 614 */       this.mapTipoOficina = new HashMap();
/* 615 */       this.parameterTable = new ArrayList();
/* 616 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.TIPO_SUCURSAL.getCode());
/* 617 */       for (ParameterTable param : this.parameterTable) {
/* 618 */         this.selectTipoOficina.add(new SelectItem(param.getId(), param.getNombre()));
/* 619 */         this.mapTipoOficina.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 623 */       this.mapActividad = new HashMap();
/* 624 */       this.listTodasActividades = new ArrayList();
/* 625 */       this.parameterTable = new ArrayList();
/* 626 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ACTIVIDAD_MINERA.getCode());
/*     */       
/* 628 */       for (ParameterTable param : this.parameterTable) {
/* 629 */         Actividad actividad = new Actividad();
/* 630 */         actividad.setId(param.getId());
/* 631 */         this.listTodasActividades.add(actividad);
/* 632 */         this.mapActividad.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/* 635 */       this.mapLeyMineral = new HashMap();
/*     */       
/* 637 */       this.mapMineralMetal = new HashMap();
/* 638 */       this.listMineralTodoMetal = new ArrayList();
/* 639 */       this.parameterTable = new ArrayList();
/* 640 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.MINERAL_METALICO.getCode());
/*     */       
/* 642 */       for (ParameterTable param : this.parameterTable) {
/* 643 */         Mineral mineral = new Mineral();
/* 644 */         mineral.setId(param.getId());
/* 645 */         this.listMineralTodoMetal.add(mineral);
/* 646 */         this.mapMineralMetal.put(param.getId(), param.getNombre());
/* 647 */         this.mapLeyMineral.put(param.getId(), param.getTexto1());
/*     */       }
/*     */       
/*     */ 
/* 651 */       this.mapMineralNoMetal = new HashMap();
/* 652 */       this.listMineralTodoNoMetal = new ArrayList();
/* 653 */       this.parameterTable = new ArrayList();
/* 654 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.MINERAL_NO_METALICO.getCode());
/*     */       
/* 656 */       for (ParameterTable param : this.parameterTable) {
/* 657 */         Mineral mineralNoMetal = new Mineral();
/* 658 */         mineralNoMetal.setId(param.getId());
/* 659 */         this.listMineralTodoNoMetal.add(mineralNoMetal);
/* 660 */         this.mapMineralNoMetal.put(param.getId(), param.getNombre());
/* 661 */         this.mapLeyMineral.put(param.getId(), param.getTexto1());
/*     */       }
/*     */       
/*     */ 
/* 665 */       this.selectUnidadMedida = new ArrayList();
/* 666 */       this.selectUnidadMedida.add(new SelectItem(null, "Seleccione..."));
/* 667 */       this.mapUnidadMedida = new HashMap();
/* 668 */       this.parameterTable = new ArrayList();
/* 669 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.UNIDAD_MEDIDA.getCode());
/* 670 */       for (ParameterTable param : this.parameterTable) {
/* 671 */         this.selectUnidadMedida.add(new SelectItem(param.getId(), param.getNombre()));
/* 672 */         this.mapUnidadMedida.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 676 */       this.mapAtes = new HashMap();
/* 677 */       this.parameterTable = new ArrayList();
/* 678 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ATES.getCode());
/* 679 */       for (ParameterTable param : this.parameterTable) {
/* 680 */         this.mapAtes.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 684 */       this.selectMunicipioDerMin = new ArrayList();
/* 685 */       this.selectMunicipioDerMin.add(new SelectItem(null, "Seleccione..."));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 690 */       // Object listMunDerMin = new ArrayList();
/* 691 */       List<Municipio> listMunDerMin = this.cooperativaEJBBeanLocal.listadoMunicipioDerMin();
/*     */       
/* 693 */       // for (Municipio mun : (List)listMunDerMin) {
                for (Municipio mun : listMunDerMin) {
/* 694 */         this.selectMunicipioDerMin.add(new SelectItem(mun.getId(), mun.getMunicipio()));
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 701 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa()
/*     */   {
/* 707 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 711 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectActorProductivo() {
/* 715 */     return this.selectActorProductivo;
/*     */   }
/*     */   
/*     */   public void setSelectActorProductivo(List<SelectItem> selectActorProductivo) {
/* 719 */     this.selectActorProductivo = selectActorProductivo;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapActorProductivo() {
/* 723 */     return this.mapActorProductivo;
/*     */   }
/*     */   
/*     */   public void setMapActorProductivo(Map<Integer, String> mapActorProductivo) {
/* 727 */     this.mapActorProductivo = mapActorProductivo;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 731 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 735 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCiExpedido() {
/* 739 */     return this.selectCiExpedido;
/*     */   }
/*     */   
/*     */   public void setSelectCiExpedido(List<SelectItem> selectCiExpedido) {
/* 743 */     this.selectCiExpedido = selectCiExpedido;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectDepartamento() {
/* 747 */     return this.selectDepartamento;
/*     */   }
/*     */   
/*     */   public void setSelectDepartamento(List<SelectItem> selectDepartamento) {
/* 751 */     this.selectDepartamento = selectDepartamento;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMunicipio() {
/* 755 */     return this.selectMunicipio;
/*     */   }
/*     */   
/*     */   public void setSelectMunicipio(List<SelectItem> selectMunicipio) {
/* 759 */     this.selectMunicipio = selectMunicipio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCiudad() {
/* 763 */     return this.selectCiudad;
/*     */   }
/*     */   
/*     */   public void setSelectCiudad(List<SelectItem> selectCiudad) {
/* 767 */     this.selectCiudad = selectCiudad;
/*     */   }
/*     */   
/*     */   public Sucursal getSucursal() {
/* 771 */     return this.sucursal;
/*     */   }
/*     */   
/*     */   public void setSucursal(Sucursal sucursal) {
/* 775 */     this.sucursal = sucursal;
/*     */   }
/*     */   
/*     */   public List<Sucursal> getListSucursal() {
/* 779 */     return this.listSucursal;
/*     */   }
/*     */   
/*     */   public void setListSucursal(List<Sucursal> listSucursal) {
/* 783 */     this.listSucursal = listSucursal;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectTipoOficina() {
/* 787 */     return this.selectTipoOficina;
/*     */   }
/*     */   
/*     */   public void setSelectTipoOficina(List<SelectItem> selectTipoOficina) {
/* 791 */     this.selectTipoOficina = selectTipoOficina;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoOficina() {
/* 795 */     return this.mapTipoOficina;
/*     */   }
/*     */   
/*     */   public void setMapTipoOficina(Map<Integer, String> mapTipoOficina) {
/* 799 */     this.mapTipoOficina = mapTipoOficina;
/*     */   }
/*     */   
/*     */   public List<Actividad> getListTodasActividades() {
/* 803 */     return this.listTodasActividades;
/*     */   }
/*     */   
/*     */   public void setListTodasActividades(List<Actividad> listTodasActividades) {
/* 807 */     this.listTodasActividades = listTodasActividades;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapActividad() {
/* 811 */     return this.mapActividad;
/*     */   }
/*     */   
/*     */   public void setMapActividad(Map<Integer, String> mapActividad) {
/* 815 */     this.mapActividad = mapActividad;
/*     */   }
/*     */   
/*     */   public List<Actividad> getListActividadesSeleccionadas() {
/* 819 */     return this.listActividadesSeleccionadas;
/*     */   }
/*     */   
/*     */   public void setListActividadesSeleccionadas(List<Actividad> listActividadesSeleccionadas)
/*     */   {
/* 824 */     this.listActividadesSeleccionadas = listActividadesSeleccionadas;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralTodoMetal() {
/* 828 */     return this.listMineralTodoMetal;
/*     */   }
/*     */   
/*     */   public void setListMineralTodoMetal(List<Mineral> listMineralTodoMetal) {
/* 832 */     this.listMineralTodoMetal = listMineralTodoMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralMetalSelect() {
/* 836 */     return this.listMineralMetalSelect;
/*     */   }
/*     */   
/*     */   public void setListMineralMetalSelect(List<Mineral> listMineralMetalSelect) {
/* 840 */     this.listMineralMetalSelect = listMineralMetalSelect;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapMineralMetal() {
/* 844 */     return this.mapMineralMetal;
/*     */   }
/*     */   
/*     */   public void setMapMineralMetal(Map<Integer, String> mapMineralMetal) {
/* 848 */     this.mapMineralMetal = mapMineralMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralTodoNoMetal() {
/* 852 */     return this.listMineralTodoNoMetal;
/*     */   }
/*     */   
/*     */   public void setListMineralTodoNoMetal(List<Mineral> listMineralTodoNoMetal) {
/* 856 */     this.listMineralTodoNoMetal = listMineralTodoNoMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralNoMetalSelect() {
/* 860 */     return this.listMineralNoMetalSelect;
/*     */   }
/*     */   
/*     */   public void setListMineralNoMetalSelect(List<Mineral> listMineralNoMetalSelect) {
/* 864 */     this.listMineralNoMetalSelect = listMineralNoMetalSelect;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapMineralNoMetal() {
/* 868 */     return this.mapMineralNoMetal;
/*     */   }
/*     */   
/*     */   public void setMapMineralNoMetal(Map<Integer, String> mapMineralNoMetal) {
/* 872 */     this.mapMineralNoMetal = mapMineralNoMetal;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapLeyMineral() {
/* 876 */     return this.mapLeyMineral;
/*     */   }
/*     */   
/*     */   public void setMapLeyMineral(Map<Integer, String> mapLeyMineral) {
/* 880 */     this.mapLeyMineral = mapLeyMineral;
/*     */   }
/*     */   
/*     */   public List<DerechoMinero> getListDerechoMinero() {
/* 884 */     return this.listDerechoMinero;
/*     */   }
/*     */   
/*     */   public void setListDerechoMinero(List<DerechoMinero> listDerechoMinero) {
/* 888 */     this.listDerechoMinero = listDerechoMinero;
/*     */   }
/*     */   
/*     */   public DerechoMinero getDerechoMinero() {
/* 892 */     return this.derechoMinero;
/*     */   }
/*     */   
/*     */   public void setDerechoMinero(DerechoMinero derechoMinero) {
/* 896 */     this.derechoMinero = derechoMinero;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectUnidadMedida() {
/* 900 */     return this.selectUnidadMedida;
/*     */   }
/*     */   
/*     */   public void setSelectUnidadMedida(List<SelectItem> selectUnidadMedida) {
/* 904 */     this.selectUnidadMedida = selectUnidadMedida;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapUnidadMedida() {
/* 908 */     return this.mapUnidadMedida;
/*     */   }
/*     */   
/*     */   public void setMapUnidadMedida(Map<Integer, String> mapUnidadMedida) {
/* 912 */     this.mapUnidadMedida = mapUnidadMedida;
/*     */   }
/*     */   
/*     */   public List<ContratoMinero> getListContratoMinero() {
/* 916 */     return this.listContratoMinero;
/*     */   }
/*     */   
/*     */   public void setListContratoMinero(List<ContratoMinero> listContratoMinero) {
/* 920 */     this.listContratoMinero = listContratoMinero;
/*     */   }
/*     */   
/*     */   public ContratoMinero getContratoMinero() {
/* 924 */     return this.contratoMinero;
/*     */   }
/*     */   
/*     */   public void setContratoMinero(ContratoMinero contratoMinero) {
/* 928 */     this.contratoMinero = contratoMinero;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapAtes() {
/* 932 */     return this.mapAtes;
/*     */   }
/*     */   
/*     */   public void setMapAtes(Map<Integer, String> mapAtes) {
/* 936 */     this.mapAtes = mapAtes;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapRegionProMunicipio() {
/* 940 */     return this.mapRegionProMunicipio;
/*     */   }
/*     */   
/*     */   public void setMapRegionProMunicipio(Map<Integer, String> mapRegionProMunicipio) {
/* 944 */     this.mapRegionProMunicipio = mapRegionProMunicipio;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMunicipioDerMin() {
/* 948 */     return this.selectMunicipioDerMin;
/*     */   }
/*     */   
/*     */   public void setSelectMunicipioDerMin(List<SelectItem> selectMunicipioDerMin) {
/* 952 */     this.selectMunicipioDerMin = selectMunicipioDerMin;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\adm\RegisterCooperativeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */