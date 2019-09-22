/*     */ package org.erp.seguridad;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.SessionScoped;
/*     */ import javax.faces.component.UIComponent;
/*     */ import javax.faces.component.UIInput;
/*     */ import javax.faces.component.UIViewRoot;
/*     */ import javax.faces.component.html.HtmlInputText;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.faces.event.AjaxBehaviorEvent;
/*     */ import javax.faces.validator.ValidatorException;
/*     */ import javax.inject.Inject;
/*     */ import javax.script.Invocable;
/*     */ import javax.script.ScriptEngine;
/*     */ import javax.script.ScriptEngineManager;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import nl.captcha.Captcha;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.entities.SegAcceso;
/*     */ import org.erp.entities.SegUsuario;
/*     */ import org.erp.seg.ML.seg.SegUsuarioMLBeanLocal;
/*     */ import org.erp.seg.QL.seg.AccesoQLBeanLocal;
/*     */ import org.erp.util.AESEncryptUtils;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.EnvioMail;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.MessagesUtilities;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.jboss.solder.resourceLoader.Resource;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="accesoBean")
/*     */ @SessionScoped
/*     */ public class AccesoBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   private SegUsuarioMLBeanLocal segUsuarioMLBeanLocal;
/*     */   @Inject
/*     */   AccesoQLBeanLocal accesoQLBeanLocal;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   @Resource("AppConfiguration.properties")
/*     */   private Properties messageConfiguration;
/*     */   private SegUsuario segUsuario;
/*     */   private String paginaInicio;
/*     */   private SegUsuario segUsuarioSesion;
/*     */   private Date fechaActual;
/*     */   private String stageApplication;
/*     */   private String versionApplication;
/*     */   private String dateApplication;
/*     */   private String nameSystem;
/*     */   private String systemOwn;
/*     */   private String systemOwnModule;
/*     */   private String systemNameProvider;
/*     */   private String usuarioActual;
/*     */   private String tipoUsuario;
/*     */   private String currentNav;
/*     */   private String userKey;
/*     */   private String userPass;
/*     */   private String correo;
/*     */   private boolean useCaptcha;
/*     */   private String useCaptchaText;
/*     */   private String captchaUrl;
/*     */   private static final String CAPTCHA_SERVLET = "/captcha.png";
/*     */   private boolean showOneTime;
/*     */   private String captchaAnswer;
/*     */   private Captcha simpleCaptcha;
/*     */   
/*     */   public AccesoBean()
/*     */   {
/* 123 */     this.captchaUrl = "/captcha.png";
/* 124 */     setShowOneTime(true);
/*     */   }
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio() {
/* 129 */     HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
/* 130 */     if (httpSession != null) {
/* 131 */       httpSession.invalidate();
/*     */     }
/* 133 */     this.currentNav = new String();
/*     */     
/* 135 */     this.usuarioActual = "";
/* 136 */     this.tipoUsuario = "";
/* 137 */     this.segUsuario = new SegUsuario();
/* 138 */     this.segUsuarioSesion = new SegUsuario();
/* 139 */     this.paginaInicio = "";
/* 140 */     this.correo = "";
/* 141 */     this.fechaActual = new Date();
/* 142 */     this.stageApplication = this.messageConfiguration.getProperty("system.stage");
/* 143 */     this.versionApplication = this.messageConfiguration.getProperty("system.version");
/* 144 */     this.dateApplication = this.messageConfiguration.getProperty("system.date");
/* 145 */     this.nameSystem = this.messageConfiguration.getProperty("system.name");
/* 146 */     this.systemOwn = this.messageConfiguration.getProperty("system.own");
/* 147 */     this.systemOwnModule = this.messageConfiguration.getProperty("system.own.module");
/* 148 */     this.systemNameProvider = this.messageConfiguration.getProperty("system.name.provider");
/* 149 */     this.useCaptchaText = this.messageConfiguration.getProperty("system.use.captcha");
/* 150 */     if (this.useCaptchaText.equals("1")) {
/* 151 */       this.useCaptcha = true;
/*     */     } else {
/* 153 */       this.useCaptcha = false;
/*     */     }
/*     */     
/*     */ 
/* 157 */     if (!FacesContext.getCurrentInstance().isPostback()) {
/* 158 */       FacesContext context = FacesContext.getCurrentInstance();
/* 159 */       context.getViewRoot().setLocale(new Locale("es"));
/* 160 */       Util.putSessionMap("locale", context.getViewRoot().getLocale().toString());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateNav()
/*     */   {
/* 173 */     FacesContext context = FacesContext.getCurrentInstance();
/* 174 */     Map map = context.getExternalContext().getRequestParameterMap();
/* 175 */     this.currentNav = ((String)map.get("currentNav"));
/*     */   }
/*     */   
/*     */   public void verificarUsuario() throws AdminException
/*     */   {
/* 180 */     String desencript = decryptPassword(this.userKey, this.userPass);
/* 181 */     SegUsuario segUsuarioLogin = this.accesoQLBeanLocal.verificarAccesoUsuario(this.segUsuario.getUsuario(), DigestUtils.sha256Hex(desencript));
/* 182 */     this.segUsuarioSesion = segUsuarioLogin;
/*     */     
/* 184 */     if (segUsuarioLogin != null)
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
/* 195 */       if (segUsuarioLogin.getHabilitado().equals("1")) {
/* 196 */         DateTime fechaIni = new DateTime(segUsuarioLogin.getFechainiciovigencia());
/* 197 */         DateTime fechaFin = new DateTime(segUsuarioLogin.getFechafinvigencia());
/* 198 */         DateTime fechaActual = new DateTime(new Date());
/*     */         
/* 200 */         if ((fechaIni.isBefore(fechaActual)) && (fechaActual.isBefore(fechaFin))) {
/*     */           try {
/* 202 */             if (segUsuarioLogin.getPantallaprincipal() != null) {
/* 203 */               if (segUsuarioLogin.getPantallaprincipal() != "") {
/* 204 */                 this.paginaInicio = segUsuarioLogin.getPantallaprincipal();
/*     */               } else {
/* 206 */                 this.paginaInicio = "pantallaVacia.xhtml";
/*     */               }
/*     */             } else {
/* 209 */               this.paginaInicio = "pantallaVacia.xhtml";
/*     */             }
/*     */             
/* 212 */             ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/* 213 */             String ticketId = (String)ec.getRequestParameterMap().get("frmLogin:userPass");
/* 214 */             JSFUtilities.setHttpSessionAttribute("ticketId", AESEncryptUtils.encrypt("iCamel", ticketId));
/* 215 */             JSFUtilities.setHttpSessionAttribute("usuarioId", segUsuarioLogin.getId());
/*     */             
/* 217 */             this.simpleCaptcha = null;
/*     */             
/* 219 */             SegAcceso segAcceso = new SegAcceso();
/* 220 */             segAcceso.setUsuario(segUsuarioLogin.getUsuario());
/* 221 */             segAcceso.setFecha(new Date());
/* 222 */             segAcceso.setIp(JSFUtilities.getRemoteIpAddress());
/* 223 */             this.accesoQLBeanLocal.guardarAcceso(segAcceso);
/* 224 */             ec.redirect(ec.getRequestContextPath() + "/inicio.jsf");
/*     */           }
/*     */           catch (IOException e1)
/*     */           {
/* 228 */             e1.printStackTrace();
/*     */           } catch (Exception e) {
/* 230 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 235 */           Util.CrearMsgWARN("", MessagesUtilities.getMessage("login.error.schedule.invalid"), false);
/* 236 */           resetCaptchaUrl();
/*     */         }
/*     */       } else {
/* 239 */         Util.CrearMsgWARN("", MessagesUtilities.getMessage("login.error.holiday"), false);
/* 240 */         resetCaptchaUrl();
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 246 */       Util.CrearMsgWARN("", MessagesUtilities.getMessage("login.error.user.invalid"), false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 260 */       reloadCaptcha();
/*     */     }
/*     */   }
/*     */   
/*     */   public void verificarCorreo(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 268 */       SegUsuario segUsuarioLogin = this.accesoQLBeanLocal.verificarCorreo(this.correo);
/* 269 */       if (segUsuarioLogin != null) {
/* 270 */         String pass = Util.getCadenaAlfanumAleatoria(8);
/* 271 */         segUsuarioLogin.setContrasena(DigestUtils.sha256Hex(pass));
/* 272 */         this.segUsuarioMLBeanLocal.guardarUsuario(segUsuarioLogin);
/*     */         
/* 274 */         String mensaje = "Estimad@: " + segUsuarioLogin.getNombres() + " su contraseña restaurada es: <b>" + pass + 
/* 275 */           "</b> le recomendamos que debe cambiar una vez ingresado al sistema.";
/* 276 */         EnvioMail envioMail = new EnvioMail();
/* 277 */         envioMail.envioCorreo(segUsuarioLogin.getEmail(), "Restauracion contraseña", mensaje);
/*     */         
/* 279 */         Util.CrearMsgRestauracionPass();
/*     */       } else {
/* 281 */         Util.CrearMsgWARN("", MessagesUtilities.getMessage("error.reset.password"), false);
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 285 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public String descripcionParametro(Integer idParameter) throws AdminException {
/* 290 */     if ((idParameter != null) && (idParameter.intValue() != 0)) {
/* 291 */       ParameterTable parameterTable = this.serviciosUtilBeanLocal.ParameterById(idParameter);
/* 292 */       return parameterTable.getNombre();
/*     */     }
/* 294 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */   public void localeCodeChanged(AjaxBehaviorEvent e)
/*     */   {
/* 300 */     String newLocaleValue = ((UIInput)e.getComponent()).getValue()
/* 301 */       .toString();
/* 302 */     FacesContext.getCurrentInstance().getViewRoot()
/* 303 */       .setLocale(new Locale(newLocaleValue));
/* 304 */     Util.putSessionMap("locale", new Locale(newLocaleValue));
/*     */   }
/*     */   
/*     */   public String decryptPassword(String encryptedPassword, String keyPass) {
/* 308 */     String pass = "";
/*     */     try {
/* 310 */       ScriptEngineManager manager = new ScriptEngineManager();
/* 311 */       ScriptEngine engine = manager.getEngineByName("javascript");
/* 312 */       InputStreamReader isr = new InputStreamReader(AccesoBean.class.getResourceAsStream("/META-INF/resources/js/des.js"));
/* 313 */       Reader reader = isr;
/* 314 */       engine.eval(reader);
/* 315 */       Invocable inv = (Invocable)engine;
/* 316 */       String key = "";String vector = null;
/* 317 */       if ((keyPass != null) && (keyPass.length() >= 48)) {
/* 318 */         key = keyPass.substring(0, 48);
/* 319 */         if (keyPass.length() >= 64) {
/* 320 */           vector = keyPass.substring(48, 64);
/*     */         }
/*     */       }
/* 323 */       Object[] args = { key, encryptedPassword, Integer.valueOf(0), Integer.valueOf(vector != null ? 1 : 0), vector, "" };
/* 324 */       pass = (String)inv.invokeFunction("des1", args);
/*     */     } catch (Exception e) {
/* 326 */       e.printStackTrace();
/*     */     }
/* 328 */     return pass;
/*     */   }
/*     */   
/*     */   public void reloadCaptcha()
/*     */   {
/* 333 */     this.captchaUrl = "/captcha.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetCaptchaUrl()
/*     */   {
/* 340 */     this.captchaUrl = "/captcha.png";
/*     */   }
/*     */   
/*     */   public boolean isShowOneTime() {
/* 344 */     return this.showOneTime;
/*     */   }
/*     */   
/*     */   public void setShowOneTime(boolean showOneTime) {
/* 348 */     this.showOneTime = showOneTime;
/*     */   }
/*     */   
/*     */   public void validateCaptcha(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException, IOException {
/* 352 */     this.simpleCaptcha = ((Captcha)JSFUtilities.getHttpSessionAttributeObject("captcha"));
/*     */     
/* 354 */     if ((value != null) && (this.simpleCaptcha.isCorrect(value.toString()))) {
/* 355 */       return;
/*     */     }
/*     */     
/*     */ 
/* 359 */     ((HtmlInputText)componentToValidate).setSubmittedValue("");
/* 360 */     throw new ValidatorException(new FacesMessage("Captcha does not match"));
/*     */   }
/*     */   
/*     */   public void limpiarUsuario() throws AdminException {
/* 364 */     this.segUsuario = new SegUsuario();
/*     */   }
/*     */   
/*     */   public SegUsuario getSegUsuario()
/*     */   {
/* 369 */     return this.segUsuario;
/*     */   }
/*     */   
/*     */   public void setSegUsuario(SegUsuario segUsuario) {
/* 373 */     this.segUsuario = segUsuario;
/*     */   }
/*     */   
/*     */   public String getPaginaInicio() {
/* 377 */     return this.paginaInicio;
/*     */   }
/*     */   
/*     */   public void setPaginaInicio(String paginaInicio) {
/* 381 */     this.paginaInicio = paginaInicio;
/*     */   }
/*     */   
/*     */   public SegUsuario getSegUsuarioSesion() {
/* 385 */     return this.segUsuarioSesion;
/*     */   }
/*     */   
/*     */   public void setSegUsuarioSesion(SegUsuario segUsuarioSesion) {
/* 389 */     this.segUsuarioSesion = segUsuarioSesion;
/*     */   }
/*     */   
/*     */   public Date getFechaActual() {
/* 393 */     return this.fechaActual;
/*     */   }
/*     */   
/*     */   public void setFechaActual(Date fechaActual) {
/* 397 */     this.fechaActual = fechaActual;
/*     */   }
/*     */   
/*     */   public String getStageApplication() {
/* 401 */     return this.stageApplication;
/*     */   }
/*     */   
/*     */   public void setStageApplication(String stageApplication) {
/* 405 */     this.stageApplication = stageApplication;
/*     */   }
/*     */   
/*     */   public String getVersionApplication() {
/* 409 */     return this.versionApplication;
/*     */   }
/*     */   
/*     */   public void setVersionApplication(String versionApplication) {
/* 413 */     this.versionApplication = versionApplication;
/*     */   }
/*     */   
/*     */   public String getDateApplication() {
/* 417 */     return this.dateApplication;
/*     */   }
/*     */   
/*     */   public void setDateApplication(String dateApplication) {
/* 421 */     this.dateApplication = dateApplication;
/*     */   }
/*     */   
/*     */   public String getNameSystem() {
/* 425 */     return this.nameSystem;
/*     */   }
/*     */   
/*     */   public void setNameSystem(String nameSystem) {
/* 429 */     this.nameSystem = nameSystem;
/*     */   }
/*     */   
/*     */   public String getSystemOwn() {
/* 433 */     return this.systemOwn;
/*     */   }
/*     */   
/*     */   public void setSystemOwn(String systemOwn) {
/* 437 */     this.systemOwn = systemOwn;
/*     */   }
/*     */   
/*     */   public String getSystemOwnModule() {
/* 441 */     return this.systemOwnModule;
/*     */   }
/*     */   
/*     */   public void setSystemOwnModule(String systemOwnModule) {
/* 445 */     this.systemOwnModule = systemOwnModule;
/*     */   }
/*     */   
/*     */   public String getUsuarioActual() {
/* 449 */     return this.usuarioActual;
/*     */   }
/*     */   
/*     */   public void setUsuarioActual(String usuarioActual) {
/* 453 */     this.usuarioActual = usuarioActual;
/*     */   }
/*     */   
/*     */   public String getTipoUsuario() {
/* 457 */     return this.tipoUsuario;
/*     */   }
/*     */   
/*     */   public void setTipoUsuario(String tipoUsuario) {
/* 461 */     this.tipoUsuario = tipoUsuario;
/*     */   }
/*     */   
/*     */   public String getCurrentNav() {
/* 465 */     return this.currentNav;
/*     */   }
/*     */   
/*     */   public void setCurrentNav(String currentNav) {
/* 469 */     this.currentNav = currentNav;
/*     */   }
/*     */   
/*     */   public String getUserKey() {
/* 473 */     return this.userKey;
/*     */   }
/*     */   
/*     */   public void setUserKey(String userKey) {
/* 477 */     this.userKey = userKey;
/*     */   }
/*     */   
/*     */   public String getUserPass() {
/* 481 */     return this.userPass;
/*     */   }
/*     */   
/*     */   public void setUserPass(String userPass) {
/* 485 */     this.userPass = userPass;
/*     */   }
/*     */   
/*     */   public String getCorreo() {
/* 489 */     return this.correo;
/*     */   }
/*     */   
/*     */   public void setCorreo(String correo) {
/* 493 */     this.correo = correo;
/*     */   }
/*     */   
/*     */   public String getSystemNameProvider() {
/* 497 */     return this.systemNameProvider;
/*     */   }
/*     */   
/*     */   public void setSystemNameProvider(String systemNameProvider) {
/* 501 */     this.systemNameProvider = systemNameProvider;
/*     */   }
/*     */   
/*     */   public String getCaptchaUrl() {
/* 505 */     return this.captchaUrl;
/*     */   }
/*     */   
/*     */   public void setCaptchaUrl(String captchaUrl) {
/* 509 */     this.captchaUrl = captchaUrl;
/*     */   }
/*     */   
/*     */   public String getCaptchaAnswer() {
/* 513 */     return this.captchaAnswer;
/*     */   }
/*     */   
/*     */   public void setCaptchaAnswer(String captchaAnswer) {
/* 517 */     this.captchaAnswer = captchaAnswer;
/*     */   }
/*     */   
/*     */   public Captcha getSimpleCaptcha() {
/* 521 */     return this.simpleCaptcha;
/*     */   }
/*     */   
/*     */   public void setSimpleCaptcha(Captcha simpleCaptcha) {
/* 525 */     this.simpleCaptcha = simpleCaptcha;
/*     */   }
/*     */   
/*     */   public boolean isUseCaptcha() {
/* 529 */     return this.useCaptcha;
/*     */   }
/*     */   
/*     */   public void setUseCaptcha(boolean useCaptcha) {
/* 533 */     this.useCaptcha = useCaptcha;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\AccesoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */