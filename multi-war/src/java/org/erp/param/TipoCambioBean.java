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
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.entities.TipoCambio;
/*     */ import org.erp.param.ML.CotizacionesEjbBeanLocal;
/*     */ import org.erp.param.ML.TipoCambioEjbBeanLocal;
/*     */ import org.erp.seguridad.AccesoBean;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
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
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="tipoCambioBean")
/*     */ @ViewScoped
/*     */ public class TipoCambioBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   CotizacionesEjbBeanLocal cotizacionesEjbBeanLocal;
/*     */   @Inject
/*     */   TipoCambioEjbBeanLocal tipoCambioEjbBeanLocal;
/*     */   private String strTitulo;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private TipoCambio tipoCambio;
/*     */   private List<TipoCambio> listTipoCambio;
/*     */   private List<TipoCambio> listTipoCambioFilter;
/*     */   private List<SelectItem> selectMoneda;
/*     */   private Map<Integer, String> mapTipoMoneda;
/*     */   private List<ParameterTable> parameterTable;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  91 */     this.accesoBean = accesoBean;
/*  92 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  96 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     }
/*     */     catch (IOException e) {
/* 105 */       e.printStackTrace();
/*     */     }
/*     */     
/* 108 */     cargarParametros();
/* 109 */     listarTipoCambio();
/*     */   }
/*     */   
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       this.mapTipoMoneda = new HashMap();
/* 118 */       this.selectMoneda = new ArrayList();
/* 119 */       this.selectMoneda.add(new SelectItem(null, ""));
/* 120 */       this.parameterTable = new ArrayList();
/* 121 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_TIPO_MONEDA.getCode());
/* 122 */       for (ParameterTable param : this.parameterTable) {
/* 123 */         this.selectMoneda.add(new SelectItem(param.getId(), param.getNombre()));
/* 124 */         this.mapTipoMoneda.put(param.getId(), param.getNombre());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 129 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarTipoCambio()
/*     */   {
/* 136 */     this.tipoCambio = new TipoCambio();
/* 137 */     this.listTipoCambio = new ArrayList();
/*     */     try
/*     */     {
/* 140 */       this.listTipoCambio = this.tipoCambioEjbBeanLocal.listaTipoCambio();
/*     */     } catch (AdminException e) {
/* 142 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void registrarTipoCambio()
/*     */   {
/* 149 */     this.strTitulo = "Registrar Tipo Cambio";
/* 150 */     this.tipoCambio = new TipoCambio();
/*     */   }
/*     */   
/*     */   public String diasPlazo(Date fecha, String estadoPago)
/*     */   {
/* 155 */     String dias = "";
/* 156 */     if (estadoPago.equals("7"))
/*     */     {
/*     */ 
/* 159 */       DateTime fechaActual = new DateTime(new Date());
/* 160 */       DateTime fechaFactura = new DateTime(fecha);
/*     */       
/* 162 */       if (fechaActual.isBefore(fechaFactura)) {
/* 163 */         Days days = Days.daysBetween(fechaActual, fechaFactura);
/* 164 */         dias = new Integer(days.getDays() + 1).toString();
/*     */       } else {
/* 166 */         dias = "VENCIDO";
/*     */       }
/*     */     } else {
/* 169 */       dias = "";
/*     */     }
/*     */     
/* 172 */     return dias;
/*     */   }
/*     */   
/*     */   public void guardarMaster(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 179 */       this.tipoCambio.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 180 */       this.tipoCambioEjbBeanLocal.guardarTipoCambio(this.tipoCambio);
/*     */       
/* 182 */       Util.CrearMsgGuardado();
/*     */       
/* 184 */       listarTipoCambio();
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 188 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void modificarTC()
/*     */   {
/* 194 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarDescripcion() {
/* 198 */     this.strTitulo = "Modificar Descripcion de Mineral";
/*     */   }
/*     */   
/*     */   public void eliminarMaster(ActionEvent event)
/*     */   {
/*     */     try {
/* 204 */       this.tipoCambioEjbBeanLocal.eliminarTipoCambio(this.tipoCambio);
/* 205 */       listarTipoCambio();
/* 206 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 209 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public String descripcionPeriodo(String mes)
/*     */   {
/* 215 */     return Util.descripcionPeriodo(mes);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void onRowSelectDescripcion(SelectEvent event) {}
/*     */   
/*     */   public String getStrTitulo()
/*     */   {
/* 226 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 230 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public TipoCambio getTipoCambio() {
/* 234 */     return this.tipoCambio;
/*     */   }
/*     */   
/*     */   public void setTipoCambio(TipoCambio tipoCambio) {
/* 238 */     this.tipoCambio = tipoCambio;
/*     */   }
/*     */   
/*     */   public List<TipoCambio> getListTipoCambio() {
/* 242 */     return this.listTipoCambio;
/*     */   }
/*     */   
/*     */   public void setListTipoCambio(List<TipoCambio> listTipoCambio) {
/* 246 */     this.listTipoCambio = listTipoCambio;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoMoneda() {
/* 250 */     return this.mapTipoMoneda;
/*     */   }
/*     */   
/*     */   public void setMapTipoMoneda(Map<Integer, String> mapTipoMoneda) {
/* 254 */     this.mapTipoMoneda = mapTipoMoneda;
/*     */   }
/*     */   
/*     */   public List<TipoCambio> getListTipoCambioFilter() {
/* 258 */     return this.listTipoCambioFilter;
/*     */   }
/*     */   
/*     */   public void setListTipoCambioFilter(List<TipoCambio> listTipoCambioFilter) {
/* 262 */     this.listTipoCambioFilter = listTipoCambioFilter;
/*     */   }
/*     */   
/*     */   public List<SelectItem> getSelectMoneda() {
/* 266 */     return this.selectMoneda;
/*     */   }
/*     */   
/*     */   public void setSelectMoneda(List<SelectItem> selectMoneda) {
/* 270 */     this.selectMoneda = selectMoneda;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\param\TipoCambioBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */