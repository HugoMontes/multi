/*     */ package org.erp.util;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.faces.component.UIViewRoot;
/*     */ import javax.faces.context.FacesContext;
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
/*     */ public class MessagesUtilities
/*     */ {
/*     */   public static String getConstantsMessage(String key)
/*     */   {
/*  31 */     ResourceBundle bundle = ResourceBundle.getBundle("Messages");
/*  32 */     String value = bundle.getString(key);
/*  33 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getMessage(Locale locale, String key)
/*     */   {
/*  44 */     return getBundle(locale).getString(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getMessage(String key)
/*     */   {
/*  55 */     ResourceBundle bundle = ResourceBundle.getBundle("Messages", JSFUtilities.getCurrentLocale());
/*  56 */     String value = bundle.getString(key);
/*  57 */     return value;
/*     */   }
/*     */   
/*     */   public static String getMessage(String key, Object... params) {
/*  61 */     Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
/*  62 */     return MessageFormat.format(getBundle(locale).getString(key), params);
/*     */   }
/*     */   
/*     */   public static String getGenericMessage(String key) {
/*  66 */     ResourceBundle bundle = ResourceBundle.getBundle("Messages", JSFUtilities.getCurrentLocale());
/*  67 */     String value = bundle.getString(key);
/*  68 */     return value;
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
/*     */   public static ResourceBundle getBundle(Locale locale)
/*     */   {
/*  85 */     return ResourceBundle.getBundle("Messages", locale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ResourceBundle getBundle(String propertyFile, Locale locale)
/*     */   {
/*  96 */     return ResourceBundle.getBundle(propertyFile, locale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getMessage(String propertyFile, Locale locale, String key)
/*     */   {
/* 108 */     ResourceBundle bundle = ResourceBundle.getBundle(propertyFile, locale);
/* 109 */     return bundle.getString(key);
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
/*     */   public static String getMessage(String propertyFile, Locale locale, String key, Object... params)
/*     */   {
/* 122 */     return MessageFormat.format(getBundle(propertyFile, locale).getString(key), params);
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
/*     */   public static String getMessage(String propertyFile, String key, Object[] params)
/*     */   {
/* 135 */     return getMessage(propertyFile, JSFUtilities.getCurrentLocale(), key, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getMessage(Locale locale, String key, Object... params)
/*     */   {
/* 147 */     return MessageFormat.format(getBundle(locale).getString(key), params);
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\MessagesUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */