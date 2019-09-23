/*     */ package org.erp.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
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
/*     */ import org.erp.entities.Ciudad;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.Departamento;
/*     */ import org.erp.entities.Mineral;
/*     */ import org.erp.entities.Municipio;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.entities.TransporteInterno;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="registerInternoBean")
/*     */ @ViewScoped
/*     */ public class RegisterInternoBean
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
/*     */   private TransporteInterno transporteInterno;
/*     */   private List<SelectItem> selectTrancaSalida;
/*  86 */   private Map<Integer, String> mapTrancaSalida = null;
/*     */   
/*     */   private List<SelectItem> selectPresentacion;
/*  89 */   private Map<Integer, String> mapPresentacion = null;
/*     */   
/*     */   private List<SelectItem> selectDepartamento;
/*     */   
/*     */   private List<SelectItem> selectCiudad;
/*     */   
/*     */   private List<SelectItem> selectCiExpedido;
/*     */   
/*     */   private List<Mineral> listMineralTodoMetal;
/*     */   
/*     */   private List<Mineral> listMineralTodoNoMetal;
/*     */   private List<Mineral> listMineralMetalSelect;
/*     */   private List<Mineral> listMineralNoMetalSelect;
/*     */   private List<SelectItem> selectOpcionMineral;
/*     */   private List<SelectItem> selectMunicipioDerMin;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/* 109 */     this.accesoBean = accesoBean;
/* 110 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/* 111 */     this.cooperativa = this.segUsuarioSession.getIdCooperativa();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/* 115 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
///*     */     try {
///*     */       
///*     */     }
///*     */     catch (IOException e) {
///* 125 */       e.printStackTrace();
///* 126 */       this.logger.info("Error en la validación de sesión");
///*     */     }
/*     */     try
/*     */     {
/* 130 */       this.transporteInterno = ((TransporteInterno)JSFUtilities.getHttpSessionAttributeObject("internoId"));
/*     */     } catch (Exception e) {
/* 132 */       this.transporteInterno = new TransporteInterno();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 137 */     cargarParametros();
/*     */     
/* 139 */     this.strTitulo = "";
/* 140 */     this.pagina = "/pages/adm/listFormInterno.jsf";
/*     */     
/* 142 */     if (this.transporteInterno.getId() != null)
/*     */     {
/* 144 */       cargarCiudad();
/*     */     }
/*     */     else {
/* 147 */       this.transporteInterno = new TransporteInterno();
/* 148 */       this.transporteInterno.setIdCiudad(new Ciudad());
/* 149 */       this.transporteInterno.setIdDepartamento(new Departamento());
/* 150 */       this.transporteInterno.setIdCooperativa(this.cooperativa);
/*     */     }
/*     */     
/*     */ 
/* 154 */     this.listMineralMetalSelect = new ArrayList();
/* 155 */     this.listMineralNoMetalSelect = new ArrayList();
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
/*     */   public void modificarSucursal()
/*     */   {
/* 178 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void seleccionMinerales()
/*     */   {
/* 183 */     this.strTitulo = "Seleccionar minerales";
/*     */   }
/*     */   
/*     */   public void modificarDerechosMineros()
/*     */   {
/* 188 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarContratoMineros()
/*     */   {
/* 193 */     this.strTitulo = "Modificar";
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
/*     */   public void guardarFormulario()
/*     */   {
/*     */     try
/*     */     {
/* 213 */       if (((this.transporteInterno.getCarMineralUno() != null) && (!this.transporteInterno.getCarMineralUno().isEmpty())) || (
/* 214 */         (this.transporteInterno.getCarMineralDos() != null) && (!this.transporteInterno.getCarMineralDos().isEmpty()))) {
/* 215 */         this.transporteInterno.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 216 */         this.transporteInterno.setIndEstadoRegistro(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 217 */         this.internoEjbBeanLocal.guardarTransporteInterno(this.transporteInterno);
/*     */         
/* 219 */         Util.CrearMsgGuardado();
/*     */       } else {
/* 221 */         Util.CrearMsgERROR("Debe seleccionar al menos un mineral", null, false);
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 226 */       Util.CrearMsgErrorRegistro();
/* 227 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void mineralesSeleccionados()
/*     */   {
/* 233 */     String compuesto = "";
/* 234 */     if (this.listMineralMetalSelect.size() > 0) {
/* 235 */       int t = this.listMineralMetalSelect.size() - 1;
/* 236 */       for (int i = 0; i < this.listMineralMetalSelect.size() - 1; i++)
/*     */       {
/* 238 */         compuesto = compuesto + ((Mineral)this.listMineralMetalSelect.get(i)).getOtroUno().trim() + " " + (((Mineral)this.listMineralMetalSelect.get(i)).getOtroDos() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(i)).getOtroDos()) + (((Mineral)this.listMineralMetalSelect.get(i)).getOtroTres() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(i)).getOtroTres()) + ",";
/*     */       }
/*     */       
/* 241 */       compuesto = compuesto + ((Mineral)this.listMineralMetalSelect.get(t)).getOtroUno().trim() + " " + (((Mineral)this.listMineralMetalSelect.get(t)).getOtroDos() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(t)).getOtroDos()) + (((Mineral)this.listMineralMetalSelect.get(t)).getOtroTres() == null ? "" : ((Mineral)this.listMineralMetalSelect.get(t)).getOtroTres());
/*     */     }
/*     */     
/* 244 */     this.transporteInterno.setCarMineralUno(compuesto);
/*     */     
/* 246 */     compuesto = "";
/* 247 */     if (this.listMineralNoMetalSelect.size() > 0)
/*     */     {
/* 249 */       int te = this.listMineralNoMetalSelect.size() - 1;
/* 250 */       for (int i = 0; i < this.listMineralNoMetalSelect.size() - 1; i++)
/*     */       {
/* 252 */         compuesto = compuesto + ((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroUno().trim() + " " + (((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroDos() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroDos()) + (((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroTres() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(i)).getOtroTres()) + ",";
/*     */       }
/* 254 */       compuesto = compuesto + ((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroUno().trim() + " " + (((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroDos() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroDos()) + (((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroTres() == null ? "" : ((Mineral)this.listMineralNoMetalSelect.get(te)).getOtroTres());
/*     */     }
/* 256 */     this.transporteInterno.setCarMineralDos(compuesto);
/*     */   }
/*     */   
/*     */ 
/*     */   public void cargarCiudad()
/*     */   {
/*     */     try
/*     */     {
/* 264 */       this.selectCiudad = new ArrayList();
/* 265 */       this.selectCiudad.add(new SelectItem(null, "Seleccione..."));
/* 266 */       List<Ciudad> listCiu = new ArrayList();
/* 267 */       listCiu = this.cooperativaEJBBeanLocal.listadoCiudadByDpto(this.transporteInterno.getIdDepartamento().getId());
/* 268 */       for (Ciudad ciu : listCiu) {
/* 269 */         this.selectCiudad.add(new SelectItem(ciu.getId(), ciu.getNombre()));
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 274 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 283 */       this.selectTrancaSalida = new ArrayList();
/* 284 */       this.selectTrancaSalida.add(new SelectItem(null, "Seleccione..."));
/* 285 */       this.mapTrancaSalida = new HashMap();
/* 286 */       this.parameterTable = new ArrayList();
/* 287 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.TRANCA_DE_SALIDA.getCode());
/* 288 */       for (ParameterTable param : this.parameterTable) {
/* 289 */         this.selectTrancaSalida.add(new SelectItem(param.getId(), param.getNombre()));
/* 290 */         this.mapTrancaSalida.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 294 */       this.selectPresentacion = new ArrayList();
/* 295 */       this.selectPresentacion.add(new SelectItem(null, "Seleccione..."));
/* 296 */       this.mapPresentacion = new HashMap();
/* 297 */       this.parameterTable = new ArrayList();
/* 298 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 299 */       for (ParameterTable param : this.parameterTable) {
/* 300 */         this.mapPresentacion.put(param.getId(), param.getNombre());
/* 301 */         this.selectPresentacion.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 305 */       this.selectDepartamento = new ArrayList();
/* 306 */       this.selectDepartamento.add(new SelectItem(null, "Seleccione..."));
/* 307 */       List<Departamento> listDepar = new ArrayList();
/* 308 */       listDepar = this.cooperativaEJBBeanLocal.listadoDepartamento();
/* 309 */       for (Departamento dep : listDepar) {
/* 310 */         this.selectDepartamento.add(new SelectItem(dep.getId(), dep.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 314 */       this.listMineralTodoMetal = new ArrayList();
/* 315 */       this.parameterTable = new ArrayList();
/* 316 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.MINERAL_METALICO.getCode());
/*     */       
/* 318 */       for (ParameterTable param : this.parameterTable) {
/* 319 */         Mineral mineral = new Mineral();
/* 320 */         mineral.setId(param.getId());
/* 321 */         mineral.setOtroUno(param.getNombre());
/*     */         
/* 323 */         this.listMineralTodoMetal.add(mineral);
/*     */       }
/*     */       
/*     */ 
/* 327 */       this.listMineralTodoNoMetal = new ArrayList();
/* 328 */       this.parameterTable = new ArrayList();
/* 329 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.MINERAL_NO_METALICO.getCode());
/*     */       
/* 331 */       for (ParameterTable param : this.parameterTable) {
/* 332 */         Mineral mineralNoMetal = new Mineral();
/* 333 */         mineralNoMetal.setId(param.getId());
/* 334 */         mineralNoMetal.setOtroUno(param.getNombre());
/*     */         
/* 336 */         this.listMineralTodoNoMetal.add(mineralNoMetal);
/*     */       }
/*     */       
/*     */ 
/* 340 */       this.selectOpcionMineral = new ArrayList();
/* 341 */       this.selectOpcionMineral.add(new SelectItem(null, ""));
/* 342 */       this.parameterTable = new ArrayList();
/* 343 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.SELECT_TIPO_MINERAL.getCode());
/* 344 */       for (ParameterTable param : this.parameterTable) {
/* 345 */         this.selectOpcionMineral.add(new SelectItem(param.getNombre(), param.getNombre()));
/*     */       }
/*     */       
/*     */ 
/* 349 */       this.selectMunicipioDerMin = new ArrayList();
/* 350 */       this.selectMunicipioDerMin.add(new SelectItem(null, "Seleccione..."));
/* 351 */       // Object listMunDerMin = new ArrayList();
/* 352 */       List<Municipio> listMunDerMin = this.cooperativaEJBBeanLocal.listadoMunicipioDerMin();
/* 353 */       for (Municipio mun : listMunDerMin) {
/* 354 */         if (mun.getMunicipio() != null) {
/* 355 */           this.selectMunicipioDerMin.add(new SelectItem(mun.getMunicipio(), mun.getMunicipio()));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 362 */       this.selectCiExpedido = new ArrayList();
/* 363 */       this.selectCiExpedido.add(new SelectItem(null, "Seleccione..."));
/* 364 */       this.parameterTable = new ArrayList();
/* 365 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CI_EXPEDIDO.getCode());
/* 366 */       for (ParameterTable param : this.parameterTable) {
/* 367 */         this.selectCiExpedido.add(new SelectItem(param.getId(), param.getNombre()));
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 373 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 378 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 382 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public TransporteInterno getTransporteInterno() {
/* 386 */     return this.transporteInterno;
/*     */   }
/*     */   
/*     */   public void setTransporteInterno(TransporteInterno transporteInterno) {
/* 390 */     this.transporteInterno = transporteInterno;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectTrancaSalida() {
/* 394 */     return this.selectTrancaSalida;
/*     */   }
/*     */   
/*     */   public void setSelectTrancaSalida(List<SelectItem> selectTrancaSalida) {
/* 398 */     this.selectTrancaSalida = selectTrancaSalida;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTrancaSalida() {
/* 402 */     return this.mapTrancaSalida;
/*     */   }
/*     */   
/*     */   public void setMapTrancaSalida(Map<Integer, String> mapTrancaSalida) {
/* 406 */     this.mapTrancaSalida = mapTrancaSalida;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectPresentacion() {
/* 410 */     return this.selectPresentacion;
/*     */   }
/*     */   
/*     */   public void setSelectPresentacion(List<SelectItem> selectPresentacion) {
/* 414 */     this.selectPresentacion = selectPresentacion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapPresentacion() {
/* 418 */     return this.mapPresentacion;
/*     */   }
/*     */   
/*     */   public void setMapPresentacion(Map<Integer, String> mapPresentacion) {
/* 422 */     this.mapPresentacion = mapPresentacion;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectDepartamento() {
/* 426 */     return this.selectDepartamento;
/*     */   }
/*     */   
/*     */   public void setSelectDepartamento(List<SelectItem> selectDepartamento) {
/* 430 */     this.selectDepartamento = selectDepartamento;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCiudad() {
/* 434 */     return this.selectCiudad;
/*     */   }
/*     */   
/*     */   public void setSelectCiudad(List<SelectItem> selectCiudad) {
/* 438 */     this.selectCiudad = selectCiudad;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 442 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 446 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralTodoMetal() {
/* 450 */     return this.listMineralTodoMetal;
/*     */   }
/*     */   
/*     */   public void setListMineralTodoMetal(List<Mineral> listMineralTodoMetal) {
/* 454 */     this.listMineralTodoMetal = listMineralTodoMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralTodoNoMetal() {
/* 458 */     return this.listMineralTodoNoMetal;
/*     */   }
/*     */   
/*     */   public void setListMineralTodoNoMetal(List<Mineral> listMineralTodoNoMetal) {
/* 462 */     this.listMineralTodoNoMetal = listMineralTodoNoMetal;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralMetalSelect() {
/* 466 */     return this.listMineralMetalSelect;
/*     */   }
/*     */   
/*     */   public void setListMineralMetalSelect(List<Mineral> listMineralMetalSelect) {
/* 470 */     this.listMineralMetalSelect = listMineralMetalSelect;
/*     */   }
/*     */   
/*     */   public List<Mineral> getListMineralNoMetalSelect() {
/* 474 */     return this.listMineralNoMetalSelect;
/*     */   }
/*     */   
/*     */   public void setListMineralNoMetalSelect(List<Mineral> listMineralNoMetalSelect) {
/* 478 */     this.listMineralNoMetalSelect = listMineralNoMetalSelect;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectOpcionMineral() {
/* 482 */     return this.selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public void setSelectOpcionMineral(List<SelectItem> selectOpcionMineral) {
/* 486 */     this.selectOpcionMineral = selectOpcionMineral;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMunicipioDerMin() {
/* 490 */     return this.selectMunicipioDerMin;
/*     */   }
/*     */   
/*     */   public void setSelectMunicipioDerMin(List<SelectItem> selectMunicipioDerMin) {
/* 494 */     this.selectMunicipioDerMin = selectMunicipioDerMin;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectCiExpedido() {
/* 498 */     return this.selectCiExpedido;
/*     */   }
/*     */   
/*     */   public void setSelectCiExpedido(List<SelectItem> selectCiExpedido) {
/* 502 */     this.selectCiExpedido = selectCiExpedido;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\RegisterInternoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */