/*    */ package org.erp.util;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.Date;
/*    */ import java.util.Properties;
/*    */ import javax.inject.Inject;
import javax.mail.Message;
/*    */ import javax.mail.Message.RecipientType;
/*    */ import javax.mail.MessagingException;
/*    */ import javax.mail.Session;
/*    */ import javax.mail.Transport;
/*    */ import javax.mail.internet.InternetAddress;
/*    */ import javax.mail.internet.MimeMessage;
/*    */ import org.jboss.solder.resourceLoader.Resource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnvioMail
/*    */ {
/*    */   @Inject
/*    */   @Resource("AppConfiguration.properties")
/*    */   private Properties messageConfiguration;
/* 31 */   String servidorSMTP = "smtp.gmail.com";
/* 32 */   String puerto = "587";
/* 33 */   String usuario = "sireminlp101@gmail.com";
/* 34 */   String password = "itm4891253016";
/*    */   
/*    */   public void envioCorreo(String destino, String asunto, String mensaje) {
/* 37 */     Properties props = new Properties();
/* 38 */     props.put("mail.debug", "true");
/* 39 */     props.put("mail.smtp.auth", Boolean.valueOf(true));
/* 40 */     props.put("mail.smtp.starttls.enable", Boolean.valueOf(true));
/* 41 */     props.put("mail.smtp.host", this.servidorSMTP);
/* 42 */     props.put("mail.smtp.port", this.puerto);
/*    */     
/* 44 */     Session session = Session.getInstance(props, null);
/*    */     try
/*    */     {
/* 47 */       MimeMessage email = new MimeMessage(session);
/* 48 */       email.setFrom(new InternetAddress("imultisystem@gmail.com", "iMultiSystem"));
/* 49 */       email.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
/* 50 */       email.setSubject(asunto);
/* 51 */       email.setSentDate(new Date());
/*    */       
/* 53 */       StringBuilder message = new StringBuilder();
/* 54 */       message.append("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
/* 55 */       message.append(mensaje);
/* 56 */       message.append("<br><br><br><b>Nota:</b>");
/* 57 */       message.append(" Este mensaje fue enviado por un sistema automatico de mensajeria, FAVOR NO RESPONDER. ");
/* 58 */       message.append("<font color=#0B2161>");
/* 59 */       message.append("<br><br><br><b>______________________________</b><br>");
/* 60 */       message.append("<b>iMulti</b><br>");
/* 61 */       message.append("Sistema de Informacion Empresarial<br>");
/* 62 */       message.append("www.codebolivia.com");
/* 63 */       message.append("</font>");
/* 64 */       message.append("<br>");
/* 65 */       message.append("</html>");
/*    */       
/* 67 */       email.setText(message.toString(), "utf-8", "html");
/* 68 */       Transport tr = session.getTransport("smtp");
/* 69 */       tr.connect(this.servidorSMTP, this.usuario, this.password);
/* 70 */       email.saveChanges();
/* 71 */       tr.sendMessage(email, email.getAllRecipients());
/* 72 */       tr.close();
/*    */     }
/*    */     catch (MessagingException e) {
/* 75 */       e.printStackTrace();
/*    */     } catch (UnsupportedEncodingException e) {
/* 77 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }
