/*     */ package org.erp.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.faces.component.UIViewRoot;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSFUtilities
/*     */ {
/*     */   public static void verificarSession()
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/*  27 */       String miSession = getHttpSessionAttribute("ticketId");
/*  28 */       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/*  29 */       Map params = ec.getRequestParameterMap();
/*  30 */       String ticketId = (String)params.get("ticketId");
/*  31 */       if ((miSession != null) && (ticketId != null) && (miSession.equals(ticketId))) {
/*  32 */         System.out.println("CONTINUA CONECTADO AL SISTEMA la SESSION: " + miSession);
/*     */       } else {
/*  34 */         ec.redirect(ec.getRequestContextPath() + "/index.jsf");
/*     */       }
/*     */     } catch (Exception e) {
/*  37 */       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/*  38 */       ec.redirect(ec.getRequestContextPath() + "/index.jsf");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void verificarSessionMobile() throws IOException
/*     */   {
/*     */     try
/*     */     {
/*  46 */       String miSession = getHttpSessionAttribute("ticketId");
/*  47 */       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/*  48 */       Map params = ec.getRequestParameterMap();
/*  49 */       String ticketId = (String)params.get("ticketId");
/*  50 */       if ((miSession != null) && (ticketId != null) && (miSession.equals(ticketId))) {
/*  51 */         System.out.println("CONTINUA CONECTADO AL SISTEMA la SESSION: " + miSession);
/*     */       } else {
/*  53 */         ec.redirect(ec.getRequestContextPath() + "/mobile.jsf");
/*     */       }
/*     */     } catch (Exception e) {
/*  56 */       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
/*  57 */       ec.redirect(ec.getRequestContextPath() + "/mobile.jsf");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void direccionarPagina(String pagina)
/*     */   {
/*  63 */     String miSession = getHttpSessionAttribute("ticketId");
/*  64 */     FacesContext fc = FacesContext.getCurrentInstance();
/*  65 */     ExternalContext ec = fc.getExternalContext();
/*  66 */     ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
/*     */     try {
/*  68 */       ec.redirect(servletContext.getContextPath() + pagina + "?ticketId=" + miSession);
/*     */     }
/*     */     catch (IOException e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void putRequestMap(String name, Object obj) {
/*  76 */     FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(name, obj);
/*     */   }
/*     */   
/*     */   public static Object getRequestMap(String name)
/*     */   {
/*  81 */     return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(name);
/*     */   }
/*     */   
/*     */   public static void removeRequestMap(String name)
/*     */   {
/*  86 */     FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(name);
/*     */   }
/*     */   
/*     */   public static void putViewMap(String name, Object obj)
/*     */   {
/*  91 */     FacesContext.getCurrentInstance().getViewRoot().getViewMap().put(name, obj);
/*     */   }
/*     */   
/*     */   public static Object getViewMap(String name)
/*     */   {
/*  96 */     return FacesContext.getCurrentInstance().getViewRoot().getViewMap().get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void removeViewMap(String name)
/*     */   {
/* 105 */     FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void putSessionMap(String name, Object obj)
/*     */   {
/* 115 */     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object getSessionMap(String name)
/*     */   {
/* 127 */     return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void removeSessionMap(String name)
/*     */   {
/* 136 */     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object findViewComponent(String key)
/*     */   {
/* 146 */     return FacesContext.getCurrentInstance().getViewRoot().findComponent(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getRequestParameterMap(String key)
/*     */   {
/* 156 */     return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
/*     */   }
/*     */   
/*     */   public static String getHttpSessionAttribute(String name) {
/* 160 */     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/* 161 */     return request.getSession().getAttribute(name).toString();
/*     */   }
/*     */   
/*     */   public static Object getHttpSessionAttributeObject(String name) {
/* 165 */     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/* 166 */     return request.getSession().getAttribute(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getRemoteIpAddress()
/*     */   {
/* 176 */     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/* 177 */     String strIp = request.getHeader("ClientIP");
/* 178 */     if (strIp == null) {
/* 179 */       strIp = request.getHeader("x-forwarded-for");
/* 180 */       if (strIp == null) {
/* 181 */         strIp = request.getRemoteAddr();
/*     */       }
/*     */     }
/* 184 */     return strIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void killSession()
/*     */   {
/* 192 */     HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
/* 193 */     if (httpSession != null) {
/* 194 */       httpSession.invalidate();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Locale getCurrentLocale()
/*     */   {
/* 203 */     return FacesContext.getCurrentInstance().getViewRoot().getLocale();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getCurrentViewId()
/*     */   {
/* 212 */     return FacesContext.getCurrentInstance().getViewRoot().getViewId();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setHttpSessionAttribute(String name, Object obj)
/*     */   {
/* 222 */     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/* 223 */     request.getSession().setAttribute(name, obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void removeHttpSessionAttribute(String name)
/*     */   {
/* 233 */     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
/* 234 */     request.getSession().removeAttribute(name);
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\JSFUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */