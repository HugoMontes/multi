/*     */ package org.erp.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Random;
/*     */ import java.util.regex.Matcher;
/*     */ import org.joda.time.DateTime;
/*     */ import org.primefaces.model.DefaultStreamedContent;
/*     */ import org.primefaces.model.StreamedContent;
/*     */ import org.primefaces.model.UploadedFile;
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
/*     */ public abstract class GenericBaseBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  39 */   private Date fechaActual = new Date();
/*  40 */   private String fUploadFileSize = "1048576";
/*     */   
/*  42 */   private String fUploadFileTypesValidate = "xls|xlsx|doc|docx|pdf|jpg|jpeg";
/*     */   
/*  44 */   private String fUploadFileSizeDocumentsDisplay = "1.0 Mb";
/*  45 */   private String fUploadFileTypesDocumentsDisplay = "*.jpg, *.jpeg";
/*  46 */   public Float equivalenteUnMlaOnza = Float.valueOf(0.033814F);
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
/*     */   public String fUploadValidateFile(UploadedFile fileUploaded, Matcher matcher, String maxSize, String fUploadFileTypes)
/*     */   {
/*  81 */     String displayName = null;
/*  82 */     String extensionFile = fileUploaded.getFileName().toLowerCase().substring(fileUploaded.getFileName().lastIndexOf(".") + 1, fileUploaded.getFileName().length());
/*  83 */     Matcher matcherToUser = null;
/*  84 */     String maxSizeToUse = getfUploadFileSize();
/*     */     
/*  86 */     if (matcher != null) {
/*  87 */       matcherToUser = matcher;
/*     */     }
/*  89 */     if (maxSize != null) {
/*  90 */       maxSizeToUse = maxSize;
/*     */     }
/*     */     
/*  93 */     if ((matcherToUser != null) && (!matcherToUser.matches())) {
/*  94 */       Util.CrearMsgWARN("Alerta", "Archivo no valido", false);
/*     */     }
/*     */     
/*     */ 
/*  98 */     if (fUploadFileTypes == null) {
/*  99 */       fUploadFileTypes = this.fUploadFileTypesValidate;
/*     */     }
/* 101 */     if ((!fUploadFileTypes.contains(extensionFile)) && (matcherToUser == null)) {
/* 102 */       Util.CrearMsgWARN("Archivo no valido", "", false);
/* 103 */       return null;
/*     */     }
/*     */     
/* 106 */     if (fileUploaded.getSize() > Integer.valueOf(maxSizeToUse).intValue()) {
/* 107 */       Util.CrearMsgWARN("Tamaño del archivo supera lo esperado", "", false);
/* 108 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 116 */     displayName = fileUploaded.getFileName();
/*     */     
/* 118 */     return displayName;
/*     */   }
/*     */   
/*     */   public StreamedContent getStreamedContentFromFile(byte[] file, String fileType, String fileName) throws IOException
/*     */   {
/* 123 */     if (file != null) {
/* 124 */       InputStream inputStream = new ByteArrayInputStream(file);
/* 125 */       StreamedContent streamedContentFile = new DefaultStreamedContent(inputStream, fileType, fileName);
/* 126 */       inputStream.close();
/* 127 */       return streamedContentFile;
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public String formatoFecha(Date fecha)
/*     */   {
/* 135 */     if (fecha != null) {
/* 136 */       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
/* 137 */       return formateador.format(fecha);
/*     */     }
/* 139 */     return "";
/*     */   }
/*     */   
/*     */   public Date convertStrigToDate(String fecha) throws ParseException
/*     */   {
/* 144 */     if (fecha != null) {
/* 145 */       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
/* 146 */       Date date = formateador.parse(fecha);
/* 147 */       return date;
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */   
/*     */   public String formatoFechaBaseDatos(Date fecha)
/*     */   {
/* 154 */     if (fecha != null) {
/* 155 */       SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
/* 156 */       return formateador.format(fecha);
/*     */     }
/* 158 */     return "";
/*     */   }
/*     */   
/*     */   public Integer calcularEdadPersonal(Date fechaInicial, Date fechaActual)
/*     */   {
/* 163 */     DateTime dateCumpleanios = new DateTime(fechaInicial);
/* 164 */     DateTime fechaHoy = new DateTime(fechaActual);
/* 165 */     return Integer.valueOf(fechaHoy.getYear() - dateCumpleanios.getYear());
/*     */   }
/*     */   
/*     */   public String getCadenaAlfanumAleatoria(int longitud)
/*     */   {
/* 170 */     String cadenaAleatoria = "";
/* 171 */     long milis = new GregorianCalendar().getTimeInMillis();
/* 172 */     Random r = new Random(milis);
/* 173 */     int i = 0;
/* 174 */     while (i < longitud) {
/* 175 */       char c = (char)r.nextInt(255);
/* 176 */       if (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z'))) {
/* 177 */         cadenaAleatoria = cadenaAleatoria + c;
/* 178 */         i++;
/*     */       }
/*     */     }
/* 181 */     return cadenaAleatoria;
/*     */   }
/*     */   
/*     */   public String getfUploadFileSize() {
/* 185 */     return this.fUploadFileSize;
/*     */   }
/*     */   
/*     */   public void setfUploadFileSize(String fUploadFileSize) {
/* 189 */     this.fUploadFileSize = fUploadFileSize;
/*     */   }
/*     */   
/*     */   public String getfUploadFileSizeDocumentsDisplay() {
/* 193 */     return this.fUploadFileSizeDocumentsDisplay;
/*     */   }
/*     */   
/*     */   public void setfUploadFileSizeDocumentsDisplay(String fUploadFileSizeDocumentsDisplay)
/*     */   {
/* 198 */     this.fUploadFileSizeDocumentsDisplay = fUploadFileSizeDocumentsDisplay;
/*     */   }
/*     */   
/*     */   public String getfUploadFileTypesDocumentsDisplay() {
/* 202 */     return this.fUploadFileTypesDocumentsDisplay;
/*     */   }
/*     */   
/*     */   public void setfUploadFileTypesDocumentsDisplay(String fUploadFileTypesDocumentsDisplay)
/*     */   {
/* 207 */     this.fUploadFileTypesDocumentsDisplay = fUploadFileTypesDocumentsDisplay;
/*     */   }
/*     */   
/*     */   public Date getFechaActual() {
/* 211 */     return this.fechaActual;
/*     */   }
/*     */   
/*     */   public void setFechaActual(Date fechaActual) {
/* 215 */     this.fechaActual = fechaActual;
/*     */   }
/*     */ }
