/*     */ package org.erp.seguridad;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import javax.faces.bean.ManagedProperty;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import nl.captcha.Captcha;
/*     */ import nl.captcha.Captcha.Builder;
/*     */ import nl.captcha.backgrounds.GradiatedBackgroundProducer;
/*     */ import nl.captcha.servlet.CaptchaServletUtil;
/*     */ import nl.captcha.text.producer.DefaultTextProducer;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ @WebServlet(name="/GenerateImageCaptchaServlet", urlPatterns={"/captcha.png"})
/*     */ public class GenerateImageCaptchaServlet
/*     */   extends HttpServlet
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @ManagedProperty("#{accesoBean}")
/*     */   private AccesoBean accesoBean;
/*     */   
/*     */   public void setAccesoBean(AccesoBean accesoBean)
/*     */   {
/*  49 */     this.accesoBean = accesoBean;
/*  50 */     System.out.println("PASO POR AQUI");
/*     */   }
/*     */   
/*  53 */   public AccesoBean getAccesoBean() { return this.accesoBean; }
/*     */   
/*     */ 
/*     */ 
/*  57 */   private static final char[] DEFAULT_CHARS = { 'a', 'b', 'c', 'd', 
/*  58 */     'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 'r', 'w', 'x', 'y', 
/*  59 */     '2', '3', '4', '5', '6', '7', '8' };
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
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  82 */     String reload = "true";
/*     */     
/*     */ 
/*  85 */     if (StringUtils.isNotBlank(reload)) {
/*  86 */       Captcha captcha = new Captcha.Builder(190, 40)
/*  87 */         .addBackground(new GradiatedBackgroundProducer())
/*  88 */         .addText(new DefaultTextProducer(5, DEFAULT_CHARS))
/*     */         
/*     */ 
/*  91 */         .addNoise()
/*  92 */         .addNoise()
/*  93 */         .addBorder()
/*  94 */         .build();
/*     */       
/*  96 */       request.getSession().setAttribute("captcha", captcha);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 101 */       CaptchaServletUtil.writeImage(response, captcha.getImage());
/* 102 */       return;
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
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 117 */     doGet(request, response);
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\GenerateImageCaptchaServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */