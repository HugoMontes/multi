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
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.CotizacionMineral;
/*     */ import org.erp.entities.CotizacionMineralDesc;
/*     */ import org.erp.entities.Liquidacion;
/*     */ import org.erp.entities.Municipio;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.Regalia;
/*     */ import org.erp.entities.SegUsuario;
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
/*     */ @ManagedBean(name="cajasBean")
/*     */ @ViewScoped
/*     */ public class CajasBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   LiquidacionEjbBeanLocal liquidacionEjbBeanLocal;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String numeroTramite;
/*     */   private Regalia regalia;
/*     */   private List<ParameterTable> parameterTable;
/*  76 */   private Map<Integer, String> mapTipoFormulario = null;
/*     */   
/*     */   private Liquidacion liquidacion;
/*     */   private List<Liquidacion> listLiquidacion;
/*     */   private BigDecimal totalRM;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  86 */     this.accesoBean = accesoBean;
/*  87 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  91 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() throws AdminException {
/*  96 */     this.numeroTramite = "";
/*     */     try {
/*  98 */       JSFUtilities.verificarSession();
/*     */     } catch (IOException e) {
/* 100 */       e.printStackTrace();
/* 101 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/* 103 */     this.regalia = new Regalia();
/* 104 */     cargarParametros();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void listarLiquidacion(Integer idRegalia)
/*     */   {
/* 111 */     this.listLiquidacion = new ArrayList();
/*     */     
/* 113 */     this.liquidacion = new Liquidacion();
/* 114 */     this.liquidacion.setIdRegalia(this.regalia);
/* 115 */     this.liquidacion.setMinIdCotizacionMineral(new CotizacionMineral());
/* 116 */     this.liquidacion.setMinIdCotizacionMineralDesc(new CotizacionMineralDesc());
/* 117 */     this.liquidacion.setMinIdMunicipio(new Municipio());
/*     */     try
/*     */     {
/* 120 */       this.listLiquidacion = this.liquidacionEjbBeanLocal.listadoliquidacionByRegalia(idRegalia);
/*     */       
/* 122 */       this.totalRM = new BigDecimal(0);
/* 123 */       for (Liquidacion det : this.listLiquidacion) {
/* 124 */         this.totalRM = this.totalRM.add(det.getMinRmBs());
/*     */       }
/* 126 */       this.totalRM = this.totalRM.setScale(0, 0);
/*     */     } catch (AdminException e) {
/* 128 */       Util.CrearMsgErrorObtenerInfo();
/* 129 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registrarPago()
/*     */   {
/*     */     try {
/* 136 */       this.regalia.setIndEstadoPago(ParameterTableType.ESTADO_PAGO_PAGADO.getCode());
/* 137 */       this.liquidacionEjbBeanLocal.guardarRegalia(this.regalia);
/*     */       
/*     */ 
/* 140 */       this.numeroTramite = "";
/* 141 */       this.regalia = new Regalia();
/* 142 */       this.totalRM = new BigDecimal(0);
/* 143 */       Util.CrearMsgGuardado();
/*     */     }
/*     */     catch (AdminException e) {
/* 146 */       Util.CrearMsgErrorRegistro();
/* 147 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void buscarByNroTRamite()
/*     */   {
/*     */     try {
/* 154 */       this.regalia = new Regalia();
/* 155 */       this.regalia = this.liquidacionEjbBeanLocal.obtenerRegaliaByTramite(this.numeroTramite);
/*     */       
/* 157 */       if ((this.regalia != null) && (this.regalia.getId() != null)) {
/* 158 */         listarLiquidacion(this.regalia.getId());
/*     */         
/* 160 */         if (this.regalia.getIndEstadoPago().intValue() == ParameterTableType.ESTADO_PAGO_PAGADO.getCode().intValue())
/*     */         {
/* 162 */           this.regalia = new Regalia();
/* 163 */           this.totalRM = new BigDecimal(0);
/* 164 */           Util.CrearMsgGuardadoPersonalizado("El formulario NO tiene deudas");
/*     */         }
/*     */         else {
/* 167 */           Util.CrearMsgGuardadoPersonalizado("Resultados Encontrados");
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 172 */         this.regalia = new Regalia();
/* 173 */         this.totalRM = new BigDecimal(0);
/* 174 */         Util.CrearMsgErrorFatalGenerico("No existen resultados");
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 178 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void cargarParametros()
/*     */   {
/*     */     try
/*     */     {
/* 187 */       this.mapTipoFormulario = new HashMap();
/* 188 */       this.parameterTable = new ArrayList();
/* 189 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.IND_TIPO_FORMULARIO.getCode());
/* 190 */       for (ParameterTable param : this.parameterTable) {
/* 191 */         this.mapTipoFormulario.put(param.getId(), param.getNombre());
/*     */       }
/*     */       
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 197 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/* 201 */   public String getNumeroTramite() { return this.numeroTramite; }
/*     */   
/*     */   public void setNumeroTramite(String numeroTramite)
/*     */   {
/* 205 */     this.numeroTramite = numeroTramite;
/*     */   }
/*     */   
/*     */   public Regalia getRegalia() {
/* 209 */     return this.regalia;
/*     */   }
/*     */   
/*     */   public void setRegalia(Regalia regalia) {
/* 213 */     this.regalia = regalia;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoFormulario() {
/* 217 */     return this.mapTipoFormulario;
/*     */   }
/*     */   
/*     */   public void setMapTipoFormulario(Map<Integer, String> mapTipoFormulario) {
/* 221 */     this.mapTipoFormulario = mapTipoFormulario;
/*     */   }
/*     */   
/*     */   public BigDecimal getTotalRM() {
/* 225 */     return this.totalRM;
/*     */   }
/*     */   
/*     */   public void setTotalRM(BigDecimal totalRM) {
/* 229 */     this.totalRM = totalRM;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\CajasBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */