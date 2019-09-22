/*     */ package org.erp.seguridad;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Logger;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.faces.bean.SessionScoped;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.GenKey;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.util.AESEncryptUtils;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.GenericBaseBean;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MasterTableType;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.jboss.solder.resourceLoader.Resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="keyBean")
/*     */ @SessionScoped
/*     */ public class KeyBean
/*     */   extends GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   @Resource("AppConfiguration.properties")
/*     */   private Properties messageConfiguration;
/*     */   @Inject
/*     */   private Logger logger;
/*     */   private SegUsuario segUsuarioSession;
/*     */   private String systemOwnModule;
/*     */   private GenKey genKey;
/*  63 */   private Map<Integer, String> mapTipoLicencia = null;
/*     */   
/*     */ 
/*     */   private List<ParameterTable> parameterTable;
/*     */   
/*     */   private String strTitulo;
/*     */   
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */ 
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  76 */     this.accesoBean = accesoBean;
/*  77 */     this.segUsuarioSession = accesoBean.getSegUsuarioSesion();
/*     */   }
/*     */   
/*     */   public AccesoBean getAccesoBean() {
/*  81 */     return this.accesoBean;
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/*     */     try {
/*     */       
/*     */     } catch (IOException e) {
/*  89 */       e.printStackTrace();
/*  90 */       this.logger.info("Error en la validación de sesión");
/*     */     }
/*  92 */     this.systemOwnModule = this.messageConfiguration.getProperty("system.own.module");
/*  93 */     cargarParametros();
/*  94 */     recuperarDatos();
/*  95 */     this.strTitulo = "";
/*     */   }
/*     */   
/*     */   public void recuperarDatos()
/*     */   {
/*     */     try {
/* 101 */       this.genKey = new GenKey();
/* 102 */       this.genKey = this.serviciosUtilBeanLocal.licenceKey();
/*     */       
/* 104 */       if (this.genKey.getId() != null) {
/* 105 */         this.genKey.setTipoLicenciaMostrar(new Integer(AESEncryptUtils.decrypt(this.systemOwnModule, this.genKey.getTipoLicencia())));
/* 106 */         this.genKey.setDesdeMostrar(convertStrigToDate(AESEncryptUtils.decrypt(this.systemOwnModule, this.genKey.getDesde())));
/* 107 */         this.genKey.setHastaMostrar(convertStrigToDate(AESEncryptUtils.decrypt(this.systemOwnModule, this.genKey.getHasta())));
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 111 */       e.printStackTrace();
/*     */     }
/*     */     catch (Exception e) {
/* 114 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void cargarParametros()
/*     */   {
/*     */     try {
/* 121 */       this.mapTipoLicencia = new HashMap();
/* 122 */       this.parameterTable = new ArrayList();
/* 123 */       this.parameterTable = this.serviciosUtilBeanLocal.listaParametricas(MasterTableType.TIPO_DE_LICENCIA.getCode());
/* 124 */       for (ParameterTable param : this.parameterTable) {
/* 125 */         this.mapTipoLicencia.put(param.getId(), param.getNombre());
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 130 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registrarLicencia() {
/* 135 */     this.strTitulo = "Modificar datos de Licencia";
/*     */   }
/*     */   
/*     */   public void guardarLicencia(ActionEvent event)
/*     */   {
/* 140 */     String serial = "";
/*     */     try {
/* 142 */       this.genKey.setUsuarioReg(this.segUsuarioSession.getUsuario());
/*     */       try
/*     */       {
/* 145 */         this.genKey.setTipoLicencia(AESEncryptUtils.encrypt(this.systemOwnModule, this.genKey.getTipoLicenciaMostrar().toString()));
/* 146 */         this.genKey.setDesde(AESEncryptUtils.encrypt(this.systemOwnModule, formatoFecha(this.genKey.getDesdeMostrar())));
/* 147 */         this.genKey.setHasta(AESEncryptUtils.encrypt(this.systemOwnModule, formatoFecha(this.genKey.getHastaMostrar())));
/*     */         
/* 149 */         serial = AESEncryptUtils.encrypt(this.systemOwnModule, this.genKey.getTipoLicenciaMostrar().toString() + formatoFecha(this.genKey.getDesdeMostrar()) + formatoFecha(this.genKey.getHastaMostrar()));
/*     */       }
/*     */       catch (Exception e) {
/* 152 */         e.printStackTrace();
/*     */       }
/*     */       
/* 155 */       if (serial.equals(this.genKey.getSerial())) {
/* 156 */         this.serviciosUtilBeanLocal.guardarDatosLicencia(this.genKey);
/* 157 */         Util.CrearMsgGuardado();
/*     */       } else {
/* 159 */         Util.CrearMsgERROR("Licencia no Valida", "Error", false);
/*     */       }
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 164 */       Util.CrearMsgErrorRegistro();
/* 165 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapTipoLicencia()
/*     */   {
/* 171 */     return this.mapTipoLicencia;
/*     */   }
/*     */   
/*     */   public void setMapTipoLicencia(Map<Integer, String> mapTipoLicencia) {
/* 175 */     this.mapTipoLicencia = mapTipoLicencia;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 179 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 183 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public GenKey getGenKey() {
/* 187 */     return this.genKey;
/*     */   }
/*     */   
/*     */   public void setGenKey(GenKey genKey) {
/* 191 */     this.genKey = genKey;
/*     */   }
/*     */   
/*     */   public String getSystemOwnModule() {
/* 195 */     return this.systemOwnModule;
/*     */   }
/*     */   
/*     */   public void setSystemOwnModule(String systemOwnModule) {
/* 199 */     this.systemOwnModule = systemOwnModule;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\KeyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */