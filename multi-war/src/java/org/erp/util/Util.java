/*     */ package org.erp.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.model.SelectItem;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.primefaces.context.RequestContext;
/*     */ 
/*     */ public class Util
/*     */ {
/*     */   public static final boolean showException = true;
/*     */   
/*     */   public static void limpiarCache(HttpSession session)
/*     */   {
/*  27 */     session.removeAttribute("usuarioBean");
/*  28 */     session.removeAttribute("menuRolBean");
/*  29 */     session.removeAttribute("rolesBean");
/*  30 */     session.removeAttribute("personalBean");
/*  31 */     session.removeAttribute("personalCrudBean");
/*  32 */     session.removeAttribute("datosLaboralesBean");
/*  33 */     session.removeAttribute("clientesBean");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void limpiarCacheSession(HttpSession session)
/*     */   {
/*  41 */     session.removeAttribute("usuarioBean");
/*  42 */     session.removeAttribute("menuRolBean");
/*  43 */     session.removeAttribute("rolesBean");
/*     */     
/*  45 */     session.removeAttribute("accesoBean");
/*  46 */     session.removeAttribute("personalBean");
/*  47 */     session.removeAttribute("personalCrudBean");
/*  48 */     session.removeAttribute("datosLaboralesBean");
/*  49 */     session.removeAttribute("clientesBean");
/*     */   }
/*     */   
/*     */   public static void limpiarCacheSessionMobil(HttpSession session)
/*     */   {
/*  54 */     session.removeAttribute("mobAccesoBean");
/*     */   }
/*     */   
/*     */   public static void limpiarCache(HttpSession session, String nombreBean) {
/*  58 */     session.removeAttribute(nombreBean);
/*     */   }
/*     */   
/*     */   public static void limpiarCachePersonalCrud(HttpSession session, String nombreBean) {
/*  62 */     session.removeAttribute("personalBean");
/*  63 */     session.removeAttribute(nombreBean);
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
/*     */   public static void CrearVariableJavascript(String nomVariable, Object Valor)
/*     */   {
/*  77 */     RequestContext context = RequestContext.getCurrentInstance();
/*  78 */     context.addCallbackParam(nomVariable, Valor);
/*     */   }
/*     */   
/*     */   public static void CrearMsgWARN(String strMsg, String srtTitle, boolean sinErrores)
/*     */   {
/*  83 */     FacesMessage message = null;
/*  84 */     message = new FacesMessage(FacesMessage.SEVERITY_WARN, srtTitle, strMsg);
/*  85 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*  86 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(sinErrores));
/*     */   }
/*     */   
/*     */   public static void CrearMsgERROR(String strMsg, String srtTitle, boolean sinErrores)
/*     */   {
/*  91 */     FacesMessage message = null;
/*  92 */     message = new FacesMessage(FacesMessage.SEVERITY_ERROR, srtTitle, 
/*  93 */       strMsg);
/*  94 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*  95 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(sinErrores));
/*     */   }
/*     */   
/*     */   public static void CrearMsgGuardado() {
/*  99 */     FacesMessage message = null;
/*     */     
/* 101 */     message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", 
/* 102 */       "El registro se guardó correctamente");
/* 103 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 105 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static void CrearMsgGuardadoPersonalizado(String mensaje) {
/* 109 */     FacesMessage message = null;
/*     */     
/* 111 */     message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", 
/* 112 */       mensaje);
/* 113 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 115 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static void CrearMsgProcesoCancelado() {
/* 119 */     FacesMessage message = null;
/*     */     
/* 121 */     message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", 
/* 122 */       "El proceso fue cancelado exitosamente");
/* 123 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 125 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static void CrearMsgErrorRegistro() {
/* 129 */     FacesMessage message = null;
/*     */     
/* 131 */     message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mensaje", 
/* 132 */       "Ocurrieron errores en el registro");
/* 133 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 135 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static void CrearMsgErrorStock(String mensaje) {
/* 139 */     FacesMessage message = null;
/*     */     
/* 141 */     message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mensaje", 
/* 142 */       mensaje);
/* 143 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 145 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static void CrearMsgErrorFatalGenerico(String mensaje) {
/* 149 */     FacesMessage message = null;
/*     */     
/* 151 */     message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mensaje", 
/* 152 */       mensaje);
/* 153 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 155 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(false));
/*     */   }
/*     */   
/* 158 */   public static void CrearMsgErrorObtenerInfo() { FacesMessage message = null;
/*     */     
/* 160 */     message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", 
/* 161 */       "Ocurrieron errores al momento de obtener información");
/* 162 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 164 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static void CrearMsgCompletarInfo() {
/* 168 */     FacesMessage message = null;
/*     */     
/* 170 */     message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", 
/* 171 */       "Debe completar información faltante");
/* 172 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 174 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static void CrearMsgRestauracionPass() {
/* 178 */     FacesMessage message = null;
/*     */     
/* 180 */     message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La nueva contraseña fue enviado a su correo", 
/* 181 */       "");
/* 182 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 184 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static void CrearMsgModificado() {
/* 188 */     FacesMessage message = null;
/*     */     
/* 190 */     message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", 
/* 191 */       "El registro se modificó correctamente");
/* 192 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 194 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static void CrearMsgEliminado() {
/* 198 */     FacesMessage message = null;
/*     */     
/* 200 */     message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", 
/* 201 */       "El registro se eliminó correctamente");
/* 202 */     FacesContext.getCurrentInstance().addMessage(null, message);
/*     */     
/* 204 */     CrearVariableJavascript("sinErrores", Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String formatoFecha(Date fecha)
/*     */   {
/* 215 */     if (fecha != null) {
/* 216 */       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
/* 217 */       return formateador.format(fecha);
/*     */     }
/* 219 */     return "";
/*     */   }
/*     */   
/*     */   public static List<SelectItem> pantallaInicial()
/*     */   {
/* 224 */     List<SelectItem> selectPantallas = new ArrayList();
/* 225 */     selectPantallas.add(new SelectItem(null, "Seleccione..."));
/* 226 */     selectPantallas.add(new SelectItem("gerenciaGeneral.xhtml", "Gerencia General"));
/* 227 */     selectPantallas.add(new SelectItem("jefaturaContabilidad.xhtml", "Jefatura Contabilidad"));
/* 228 */     selectPantallas.add(new SelectItem("jefaturaRrhh.xhtml", "Jefatura RRHH"));
/* 229 */     selectPantallas.add(new SelectItem("pantallaVacia.xhtml", "Pantalla Vacia"));
/* 230 */     selectPantallas.add(new SelectItem("pantallaAdmin.xhtml", "Pantalla Admin"));
/* 231 */     return selectPantallas;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static List<SelectItem> accesoSistema()
/*     */   {
/* 243 */     List<SelectItem> selectAcceso = new ArrayList();
/* 244 */     selectAcceso.add(new SelectItem(null, "Seleccione..."));
/* 245 */     selectAcceso.add(new SelectItem("1", "Autorizado"));
/* 246 */     selectAcceso.add(new SelectItem("0", "Denegado"));
/* 247 */     return selectAcceso;
/*     */   }
/*     */   
/*     */   public static void putSessionMap(String name, Object obj)
/*     */   {
/* 252 */     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String descripcionPeriodo(String periodo)
/*     */   {
/* 259 */     String mes = "";
/* 260 */     if (periodo.equals("1")) {
/* 261 */       mes = "Enero";
/* 262 */     } else if (periodo.equals("2")) {
/* 263 */       mes = "Febrero";
/* 264 */     } else if (periodo.equals("3")) {
/* 265 */       mes = "Marzo";
/* 266 */     } else if (periodo.equals("4")) {
/* 267 */       mes = "Abril";
/* 268 */     } else if (periodo.equals("5")) {
/* 269 */       mes = "Mayo";
/* 270 */     } else if (periodo.equals("6")) {
/* 271 */       mes = "Junio";
/* 272 */     } else if (periodo.equals("7")) {
/* 273 */       mes = "Julio";
/* 274 */     } else if (periodo.equals("8")) {
/* 275 */       mes = "Agosto";
/* 276 */     } else if (periodo.equals("9")) {
/* 277 */       mes = "Septiembre";
/* 278 */     } else if (periodo.equals("10")) {
/* 279 */       mes = "Octubre";
/* 280 */     } else if (periodo.equals("11")) {
/* 281 */       mes = "Noviembre";
/* 282 */     } else if (periodo.equals("12")) {
/* 283 */       mes = "Dociembre";
/*     */     }
/*     */     
/* 286 */     return mes;
/*     */   }
/*     */   
/*     */   public static List<SelectItem> gestion() {
/* 290 */     List<SelectItem> selectAcceso = new ArrayList();
/* 291 */     selectAcceso.add(new SelectItem(null, "Seleccione..."));
/* 292 */     selectAcceso.add(new SelectItem("2014", "2014"));
/* 293 */     selectAcceso.add(new SelectItem("2015", "2015"));
/* 294 */     selectAcceso.add(new SelectItem("2016", "2016"));
/* 295 */     return selectAcceso;
/*     */   }
/*     */   
/*     */   public static Object Clonar(Object objInput)
/*     */   {
/*     */     try
/*     */     {
/* 302 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*     */       
/*     */ 
/* 305 */       ObjectOutputStream oos = new ObjectOutputStream(bos);
/*     */       
/* 307 */       oos.writeObject(objInput);
/*     */       
/* 309 */       oos.flush();
/*     */       
/* 311 */       ByteArrayInputStream bin = new ByteArrayInputStream(
/* 312 */         bos.toByteArray());
/*     */       
/*     */ 
/* 315 */       ObjectInputStream ois = new ObjectInputStream(bin);
/*     */       
/*     */ 
/* 318 */       return ois.readObject();
/*     */     }
/*     */     catch (IOException e) {
/* 321 */       e.printStackTrace();
/*     */     } catch (ClassNotFoundException e) {
/* 323 */       e.printStackTrace();
/*     */     }
/*     */     
/* 326 */     return null;
/*     */   }
/*     */   
/*     */   public static String getCadenaAlfanumAleatoria(int longitud) {
/* 330 */     String cadenaAleatoria = "";
/* 331 */     long milis = new GregorianCalendar().getTimeInMillis();
/* 332 */     Random r = new Random(milis);
/* 333 */     int i = 0;
/* 334 */     while (i < longitud) {
/* 335 */       char c = (char)r.nextInt(255);
/* 336 */       if (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z'))) {
/* 337 */         cadenaAleatoria = cadenaAleatoria + c;
/* 338 */         i++;
/*     */       }
/*     */     }
/* 341 */     return cadenaAleatoria;
/*     */   }
/*     */   
/*     */   public static String makePrintString(int lineChars, String producto, String cantidad, String costo, String subTotal) {
/* 345 */     int spaces = 0;
/* 346 */     String tab = "";
/*     */     try {
/* 348 */       spaces = lineChars - (producto.length() + cantidad.length() + costo.length() + subTotal.length() + 2);
/* 349 */       for (int j = 0; j < spaces; j++) {
/* 350 */         tab = tab + " ";
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {}
/*     */     
/* 355 */     return producto + tab + cantidad + " " + costo + " " + subTotal;
/*     */   }
/*     */   
/*     */   public static String makePrintCenter(int lineChars, String titulo, int inicio) {
/* 359 */     int spaces = 0;
/* 360 */     String tab = "";
/*     */     try {
/* 362 */       spaces = inicio;
/* 363 */       for (int j = 0; j < spaces; j++) {
/* 364 */         tab = tab + " ";
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {}
/*     */     
/* 369 */     return tab + titulo;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */