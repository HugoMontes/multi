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
/*     */ import org.erp.entities.TransporteInterno;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="listInternoBean")
/*     */ @ViewScoped
/*     */ public class ListInternoBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   InternoEjbBeanLocal internoEjbBeanLocal;
/*     */   @Inject
/*     */   MostrarReporte mr;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   private String strTitulo;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String pagina;
/*  74 */   private Map<Integer, String> mapPresentacion = null;
/*     */   
/*     */   private List<ParameterTable> parameterTable;
/*  77 */   private Map<Integer, String> mapEstadoRegistroForm = null;
/*  78 */   private Map<Integer, String> mapCargo = null;
/*  79 */   private Map<Integer, String> mapCiExtendido = null;
/*     */   
/*     */   private List<TransporteInterno> listTransInterno;
/*     */   
/*     */   private List<TransporteInterno> listTransInternoFilter;
/*     */   
/*     */   private TransporteInterno transporteInterno;
/*     */   
/*     */   private Cooperativa cooperativa;
/*     */   
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  94 */     this.accesoBean = accesoBean;
/*  95 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*  96 */     if (this.segUsuarioSession.getIdCooperativa() != null) {
/*  97 */       this.cooperativa = this.segUsuarioSession.getIdCooperativa();
/*     */     } else {
/*  99 */       this.cooperativa = new Cooperativa();
/*     */     }
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean()
/*     */   {
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
/* 118 */     listarTransporteInterno();
/* 119 */     cargarParametros();
/*     */     
/* 121 */     this.strTitulo = "";
/* 122 */     this.pagina = "/pages/form/registerInterno.jsf";
/*     */   }
/*     */   
/*     */ 
/*     */   public void listarTransporteInterno()
/*     */   {
/* 128 */     this.transporteInterno = new TransporteInterno();
/* 129 */     this.listTransInterno = new ArrayList();
/*     */     try
/*     */     {
/* 132 */       this.listTransInterno = this.internoEjbBeanLocal.listadoTransInterno(this.cooperativa.getId());
/*     */     } catch (AdminException e) {
/* 134 */       Util.CrearMsgErrorObtenerInfo();
/* 135 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void renovarCooperativa()
/*     */   {
/* 141 */     this.strTitulo = "Ampliar fecha de validez";
/*     */   }
/*     */   
/*     */   public void registerInterno()
/*     */   {
/* 146 */     JSFUtilities.setHttpSessionAttribute("internoId", Integer.valueOf(0));
/* 147 */     JSFUtilities.direccionarPagina(this.pagina);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelect(SelectEvent event) {}
/*     */   
/*     */ 
/*     */   public void guardarEmision()
/*     */   {
/*     */     try
/*     */     {
/* 158 */       this.transporteInterno.setEmitidoPor(this.cooperativa.getNombreCompleto());
/* 159 */       this.transporteInterno.setEmitidoCi(this.cooperativa.getRepreCi() + " " + (String)this.mapCiExtendido.get(this.cooperativa.getIndRepreExpendido()));
/* 160 */       this.transporteInterno.setEmitidoCargo(this.cooperativa.getRepreCargo());
/*     */       
/* 162 */       this.transporteInterno.setIndEstadoRegistro(ParameterTableType.ESTADO_EN_EMITIDO_FORM.getCode());
/* 163 */       this.transporteInterno.setFechaEmision(new Date());
/* 164 */       this.transporteInterno.setQr(this.transporteInterno.getId() + "|" + 
/* 165 */         formatoFecha(this.transporteInterno.getFechaEmision()) + "|" + 
/* 166 */         this.transporteInterno.getOrgMunicipioProductor() + "|" + 
/* 167 */         this.transporteInterno.getCarLotes() + "|" + 
/* 168 */         this.transporteInterno.getConNombreConductor());
/* 169 */       DateTime fechaVen = new DateTime(new Date());
/* 170 */       fechaVen = fechaVen.plusDays(4);
/* 171 */       this.transporteInterno.setFechaVencimiento(fechaVen.toDate());
/* 172 */       this.transporteInterno.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 173 */       this.internoEjbBeanLocal.guardarTransporteInterno(this.transporteInterno);
/* 174 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 177 */       Util.CrearMsgErrorRegistro();
/* 178 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void devolverHaciaOperador()
/*     */   {
/*     */     try
/*     */     {
/* 186 */       this.transporteInterno.setIndEstadoRegistro(ParameterTableType.ESTADO_EN_PROCESO_FORM.getCode());
/* 187 */       this.transporteInterno.setFechaEmision(null);
/* 188 */       this.transporteInterno.setQr(null);
/*     */       
/* 190 */       this.transporteInterno.setUsuarioReg(this.segUsuarioSession.getUsuario());
/* 191 */       this.internoEjbBeanLocal.guardarTransporteInterno(this.transporteInterno);
/* 192 */       listarTransporteInterno();
/*     */       
/*     */ 
/* 195 */       Util.CrearMsgGuardadoPersonalizado("El registro fue enviado al Operador");
/*     */     }
/*     */     catch (AdminException e) {
/* 198 */       Util.CrearMsgErrorRegistro();
/* 199 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 209 */       this.mapPresentacion = new HashMap();
/* 210 */       this.parameterTable = new ArrayList();
/* 211 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.PRESENTACION_PRODUCTOS.getCode());
/* 212 */       for (ParameterTable param : this.parameterTable) {
/* 213 */         this.mapPresentacion.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 218 */       this.mapEstadoRegistroForm = new HashMap();
/* 219 */       this.parameterTable = new ArrayList();
/* 220 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.ESTADO_REGISTRO_FORM.getCode());
/* 221 */       for (ParameterTable param : this.parameterTable) {
/* 222 */         this.mapEstadoRegistroForm.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 226 */       this.mapCargo = new HashMap();
/* 227 */       this.parameterTable = new ArrayList();
/* 228 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CARGO.getCode());
/* 229 */       for (ParameterTable param : this.parameterTable) {
/* 230 */         this.mapCargo.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */ 
/* 234 */       this.mapCiExtendido = new HashMap();
/* 235 */       this.parameterTable = new ArrayList();
/* 236 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.CI_EXPEDIDO.getCode());
/* 237 */       for (ParameterTable param : this.parameterTable) {
/* 238 */         this.mapCiExtendido.put(param.getId(), param.getDescripcion());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 243 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public StreamedContent getReporteFormEmision()
/*     */     throws SQLException
/*     */   {
/* 250 */     Map<String, Object> parametros = new HashMap();
/* 251 */     parametros.put("idTransporte", this.transporteInterno.getId());
/* 252 */     parametros.put("usuario", this.segUsuarioSession.getUsuario());
/*     */     
/* 254 */     parametros.put("nombreUsuario", null);
/* 255 */     parametros.put("nroCi", null);
/* 256 */     parametros.put("cargo", null);
/*     */     
/* 258 */     return this.mr.runReporte("form", "comFormInterno", parametros, "pdf", "comFormInterno", Integer.valueOf(1));
/*     */   }
/*     */   
/*     */   public void modifyTransporte()
/*     */   {
/* 263 */     JSFUtilities.setHttpSessionAttribute("internoId", this.transporteInterno);
/* 264 */     JSFUtilities.direccionarPagina(this.pagina);
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
/* 304 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 308 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public Cooperativa getCooperativa() {
/* 312 */     return this.cooperativa;
/*     */   }
/*     */   
/*     */   public void setCooperativa(Cooperativa cooperativa) {
/* 316 */     this.cooperativa = cooperativa;
/*     */   }
/*     */   
/*     */   public List<TransporteInterno> getListTransInterno() {
/* 320 */     return this.listTransInterno;
/*     */   }
/*     */   
/*     */   public void setListTransInterno(List<TransporteInterno> listTransInterno) {
/* 324 */     this.listTransInterno = listTransInterno;
/*     */   }
/*     */   
/*     */   public List<TransporteInterno> getListTransInternoFilter() {
/* 328 */     return this.listTransInternoFilter;
/*     */   }
/*     */   
/*     */   public void setListTransInternoFilter(List<TransporteInterno> listTransInternoFilter)
/*     */   {
/* 333 */     this.listTransInternoFilter = listTransInternoFilter;
/*     */   }
/*     */   
/*     */   public TransporteInterno getTransporteInterno() {
/* 337 */     return this.transporteInterno;
/*     */   }
/*     */   
/*     */   public void setTransporteInterno(TransporteInterno transporteInterno) {
/* 341 */     this.transporteInterno = transporteInterno;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapPresentacion() {
/* 345 */     return this.mapPresentacion;
/*     */   }
/*     */   
/*     */   public void setMapPresentacion(Map<Integer, String> mapPresentacion) {
/* 349 */     this.mapPresentacion = mapPresentacion;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapEstadoRegistroForm() {
/* 353 */     return this.mapEstadoRegistroForm;
/*     */   }
/*     */   
/*     */   public void setMapEstadoRegistroForm(Map<Integer, String> mapEstadoRegistroForm) {
/* 357 */     this.mapEstadoRegistroForm = mapEstadoRegistroForm;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\ListInternoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */