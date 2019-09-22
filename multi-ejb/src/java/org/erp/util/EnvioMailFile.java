/*     */ package org.erp.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import javax.activation.DataHandler;
/*     */ import javax.activation.FileDataSource;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.BodyPart;
import javax.mail.Message;
/*     */ import javax.mail.Message.RecipientType;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.InternetAddress;
/*     */ import javax.mail.internet.MimeBodyPart;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import javax.mail.internet.MimeMultipart;
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
/*     */ public class EnvioMailFile
/*     */ {
/*  38 */   String servidorSMTP = "smtp.gmail.com";
/*  39 */   String puerto = "587";
/*  40 */   String usuario = "ejmploEmisor@gmail.com";
/*  41 */   String password = "passEmisor";
/*     */   
/*     */   public void envioCorreo(Address[] destinos, String asunto, String mensaje, String fileName, String filePath) {
/*  44 */     Properties props = new Properties();
/*  45 */     props.put("mail.debug", "true");
/*  46 */     props.put("mail.smtp.auth", Boolean.valueOf(true));
/*  47 */     props.put("mail.smtp.starttls.enable", Boolean.valueOf(true));
/*  48 */     props.put("mail.smtp.host", this.servidorSMTP);
/*  49 */     props.put("mail.smtp.port", this.puerto);
/*     */     
/*  51 */     Session session = Session.getInstance(props, null);
/*     */     
/*     */     try
/*     */     {
/*  55 */       BodyPart texto = new MimeBodyPart();
/*  56 */       StringBuilder message = new StringBuilder();
/*  57 */       message.append("Estimados: ");
/*  58 */       message.append(mensaje);
/*  59 */       texto.setText(message.toString());
/*     */       
/*     */ 
/*  62 */       BodyPart adjunto = new MimeBodyPart();
/*  63 */       adjunto.setDataHandler(new DataHandler(new FileDataSource(filePath)));
/*  64 */       adjunto.setFileName(fileName);
/*     */       
/*     */ 
/*  67 */       MimeMultipart multiParte = new MimeMultipart();
/*  68 */       multiParte.addBodyPart(texto);
/*  69 */       multiParte.addBodyPart(adjunto);
/*     */       
/*  71 */       MimeMessage email = new MimeMessage(session);
/*  72 */       email.setFrom(new InternetAddress("ejmploEmisor@gmail.com", "iMultiSystem"));
/*     */       
/*  74 */       email.setRecipients(Message.RecipientType.TO, destinos);
/*  75 */       email.setSubject(asunto);
/*  76 */       email.setSentDate(new Date());
/*  77 */       email.setContent(multiParte);
/*     */       
/*     */ 
/*     */ 
/*  81 */       Transport tr = session.getTransport("smtp");
/*  82 */       tr.connect(this.servidorSMTP, this.usuario, this.password);
/*  83 */       email.saveChanges();
/*  84 */       tr.sendMessage(email, email.getAllRecipients());
/*  85 */       tr.close();
/*     */     }
/*     */     catch (MessagingException e) {
/*  88 */       e.printStackTrace();
/*     */     } catch (UnsupportedEncodingException e) {
/*  90 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void envioCorreoSoloMensaje(Address[] destinos, String asunto, String mensaje) {
/*  95 */     Properties props = new Properties();
/*  96 */     props.put("mail.debug", "true");
/*  97 */     props.put("mail.smtp.auth", Boolean.valueOf(true));
/*  98 */     props.put("mail.smtp.starttls.enable", Boolean.valueOf(true));
/*  99 */     props.put("mail.smtp.host", this.servidorSMTP);
/* 100 */     props.put("mail.smtp.port", this.puerto);
/*     */     
/* 102 */     Session session = Session.getInstance(props, null);
/*     */     try
/*     */     {
/* 105 */       MimeMessage email = new MimeMessage(session);
/* 106 */       email.setFrom(new InternetAddress("ejmploEmisor@gmail.com", "iMultiSystem"));
/*     */       
/*     */ 
/* 109 */       email.setRecipients(Message.RecipientType.TO, destinos);
/*     */       
/* 111 */       email.setSubject(asunto);
/* 112 */       email.setSentDate(new Date());
/*     */       
/* 114 */       email.setText(mensaje);
/* 115 */       Transport tr = session.getTransport("smtp");
/* 116 */       tr.connect(this.servidorSMTP, this.usuario, this.password);
/* 117 */       email.saveChanges();
/* 118 */       tr.sendMessage(email, email.getAllRecipients());
/* 119 */       tr.close();
/*     */     }
/*     */     catch (MessagingException e) {
/* 122 */       e.printStackTrace();
/*     */     } catch (UnsupportedEncodingException e) {
/* 124 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ }
