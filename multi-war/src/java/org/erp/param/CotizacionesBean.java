/*     */ package org.erp.param;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.CotizacionMineral;
/*     */ import org.erp.entities.CotizacionMineralDesc;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.param.ML.CotizacionesEjbBeanLocal;
/*     */ import org.erp.param.ML.ParametrosMLBeanLocal;
/*     */ import org.erp.param.QL.ParametrosQLBeanLocal;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.Days;
/*     */ import org.primefaces.event.SelectEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="cotizacionesBean")
/*     */ @ViewScoped
/*     */ public class CotizacionesBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   CotizacionesEjbBeanLocal cotizacionesEjbBeanLocal;
/*     */   @Inject
/*     */   ParametrosMLBeanLocal parametrosMLBeanLocal;
/*     */   @Inject
/*     */   ParametrosQLBeanLocal parametrosQLBeanLocal;
/*     */   private String strTitulo;
/*     */   private CotizacionMineral cotizacionMineral;
/*     */   private List<CotizacionMineral> listCotizacionMineral;
/*     */   private List<CotizacionMineral> listCotizacionMineralFilter;
/*     */   private CotizacionMineralDesc cotizacionMineralDesc;
/*     */   private List<CotizacionMineralDesc> listCotizacionMineralDesc;
/*     */   private List<CotizacionMineralDesc> listCotizacionMineralDescFilter;
/*     */   private List<SelectItem> selectMedidaMineral;
/*     */   private List<ParameterTable> parameterTable;
/*     */   private Map<Integer, String> mapMedidaMineral;
/*     */   private SegUsuario segUsuarioSession;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  94 */     this.accesoBean = accesoBean;
/*  95 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  99 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/* 108 */       e.printStackTrace();
/*     */     }
/*     */     
/* 111 */     cargarParametros();
/* 112 */     listarMinerales();
/*     */   }
/*     */   
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 120 */       this.mapMedidaMineral = new HashMap();
/* 121 */       this.selectMedidaMineral = new ArrayList();
/* 122 */       this.selectMedidaMineral.add(new SelectItem(null, ""));
/* 123 */       this.parameterTable = new ArrayList();
/* 124 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_COTIZACION_UNIDAD_MEDIDA.getCode());
/* 125 */       for (ParameterTable param : this.parameterTable) {
/* 126 */         this.selectMedidaMineral.add(new SelectItem(param.getId(), param.getNombre()));
/* 127 */         this.mapMedidaMineral.put(param.getId(), param.getNombre());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 132 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarMinerales()
/*     */   {
/* 138 */     this.cotizacionMineral = new CotizacionMineral();
/* 139 */     this.listCotizacionMineral = new ArrayList();
/*     */     
/* 141 */     this.cotizacionMineralDesc = new CotizacionMineralDesc();
/* 142 */     this.cotizacionMineralDesc.setIdCotizacionMineral(this.cotizacionMineral);
/* 143 */     this.listCotizacionMineralDesc = new ArrayList();
/*     */     try
/*     */     {
/* 146 */       this.listCotizacionMineral = this.cotizacionesEjbBeanLocal.listaCotizaciones();
/*     */     } catch (AdminException e) {
/* 148 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarDescripcion()
/*     */   {
/* 154 */     this.cotizacionMineralDesc = new CotizacionMineralDesc();
/* 155 */     this.cotizacionMineralDesc.setIdCotizacionMineral(this.cotizacionMineral);
/* 156 */     this.listCotizacionMineralDesc = new ArrayList();
/*     */     try
/*     */     {
/* 159 */       this.listCotizacionMineralDesc = this.cotizacionesEjbBeanLocal.listaDescripcion(this.cotizacionMineral.getId());
/*     */     }
/*     */     catch (AdminException e) {
/* 162 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registrarMaster()
/*     */   {
/* 168 */     this.strTitulo = "Registrar Cotización";
/* 169 */     this.cotizacionMineral = new CotizacionMineral();
/*     */   }
/*     */   
/*     */   public void registrarDescripcion() {
/* 173 */     this.strTitulo = "Registrar Descripción Mineral";
/*     */     
/* 175 */     this.cotizacionMineralDesc = new CotizacionMineralDesc();
/* 176 */     this.cotizacionMineralDesc.setIdCotizacionMineral(this.cotizacionMineral);
/*     */   }
/*     */   
/*     */   public String diasPlazo(Date fecha, String estadoPago)
/*     */   {
/* 181 */     String dias = "";
/* 182 */     if (estadoPago.equals("7"))
/*     */     {
/*     */ 
/* 185 */       DateTime fechaActual = new DateTime(new Date());
/* 186 */       DateTime fechaFactura = new DateTime(fecha);
/*     */       
/* 188 */       if (fechaActual.isBefore(fechaFactura)) {
/* 189 */         Days days = Days.daysBetween(fechaActual, fechaFactura);
/* 190 */         dias = new Integer(days.getDays() + 1).toString();
/*     */       } else {
/* 192 */         dias = "VENCIDO";
/*     */       }
/*     */     } else {
/* 195 */       dias = "";
/*     */     }
/*     */     
/* 198 */     return dias;
/*     */   }
/*     */   
/*     */   public void guardarMaster(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 205 */       this.cotizacionMineral.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 206 */       this.cotizacionesEjbBeanLocal.guardarCotizacionMineral(this.cotizacionMineral);
/*     */       
/* 208 */       Util.CrearMsgGuardado();
/*     */       
/* 210 */       listarMinerales();
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 214 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarCotizacionDescripcion(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 222 */       this.cotizacionMineralDesc.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 223 */       this.cotizacionesEjbBeanLocal.guardarCotizacionDescripcion(this.cotizacionMineralDesc);
/* 224 */       listarDescripcion();
/* 225 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 229 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void modificarMaster() {
/* 234 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarDescripcion() {
/* 238 */     this.strTitulo = "Modificar Descripcion de Mineral";
/*     */   }
/*     */   
/*     */   public void eliminarMaster(ActionEvent event)
/*     */   {
/*     */     try {
/* 244 */       this.cotizacionesEjbBeanLocal.eliminarCotizacion(this.cotizacionMineral);
/* 245 */       listarMinerales();
/* 246 */       listarDescripcion();
/* 247 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 250 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarCotizacionDescripcion(ActionEvent event)
/*     */   {
/*     */     try {
/* 257 */       this.cotizacionesEjbBeanLocal.eliminarCotizacionDescripcion(this.cotizacionMineralDesc);
/* 258 */       listarDescripcion();
/* 259 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 262 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String descripcionPeriodo(String mes)
/*     */   {
/* 269 */     return Util.descripcionPeriodo(mes);
/*     */   }
/*     */   
/*     */   public void onRowSelect(SelectEvent event)
/*     */   {
/* 274 */     listarDescripcion();
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelectDescripcion(SelectEvent event) {}
/*     */   
/*     */   public String getStrTitulo()
/*     */   {
/* 282 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 286 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public CotizacionMineral getCotizacionMineral() {
/* 290 */     return this.cotizacionMineral;
/*     */   }
/*     */   
/*     */   public void setCotizacionMineral(CotizacionMineral cotizacionMineral) {
/* 294 */     this.cotizacionMineral = cotizacionMineral;
/*     */   }
/*     */   
/*     */   public List<CotizacionMineral> getListCotizacionMineral() {
/* 298 */     return this.listCotizacionMineral;
/*     */   }
/*     */   
/*     */   public void setListCotizacionMineral(List<CotizacionMineral> listCotizacionMineral)
/*     */   {
/* 303 */     this.listCotizacionMineral = listCotizacionMineral;
/*     */   }
/*     */   
/*     */   public CotizacionMineralDesc getCotizacionMineralDesc() {
/* 307 */     return this.cotizacionMineralDesc;
/*     */   }
/*     */   
/*     */   public void setCotizacionMineralDesc(CotizacionMineralDesc cotizacionMineralDesc) {
/* 311 */     this.cotizacionMineralDesc = cotizacionMineralDesc;
/*     */   }
/*     */   
/*     */   public List<CotizacionMineralDesc> getListCotizacionMineralDesc() {
/* 315 */     return this.listCotizacionMineralDesc;
/*     */   }
/*     */   
/*     */   public void setListCotizacionMineralDesc(List<CotizacionMineralDesc> listCotizacionMineralDesc)
/*     */   {
/* 320 */     this.listCotizacionMineralDesc = listCotizacionMineralDesc;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMedidaMineral() {
/* 324 */     return this.selectMedidaMineral;
/*     */   }
/*     */   
/*     */   public void setSelectMedidaMineral(List<SelectItem> selectMedidaMineral) {
/* 328 */     this.selectMedidaMineral = selectMedidaMineral;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapMedidaMineral() {
/* 332 */     return this.mapMedidaMineral;
/*     */   }
/*     */   
/*     */   public void setMapMedidaMineral(Map<Integer, String> mapMedidaMineral) {
/* 336 */     this.mapMedidaMineral = mapMedidaMineral;
/*     */   }
/*     */   
/*     */   public List<CotizacionMineral> getListCotizacionMineralFilter() {
/* 340 */     return this.listCotizacionMineralFilter;
/*     */   }
/*     */   
/*     */   public void setListCotizacionMineralFilter(List<CotizacionMineral> listCotizacionMineralFilter)
/*     */   {
/* 345 */     this.listCotizacionMineralFilter = listCotizacionMineralFilter;
/*     */   }
/*     */   
/*     */   public List<CotizacionMineralDesc> getListCotizacionMineralDescFilter() {
/* 349 */     return this.listCotizacionMineralDescFilter;
/*     */   }
/*     */   
/*     */   public void setListCotizacionMineralDescFilter(List<CotizacionMineralDesc> listCotizacionMineralDescFilter)
/*     */   {
/* 354 */     this.listCotizacionMineralDescFilter = listCotizacionMineralDescFilter;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\param\CotizacionesBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */