/*     */ package org.erp.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.SessionScoped;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.inject.Inject;
/*     */ import javax.servlet.ServletContext;
/*     */ import net.sf.jasperreports.engine.JasperCompileManager;
/*     */ import net.sf.jasperreports.engine.JasperExportManager;
/*     */ import net.sf.jasperreports.engine.JasperFillManager;
/*     */ import net.sf.jasperreports.engine.JasperPrint;
/*     */ import net.sf.jasperreports.engine.JasperReport;
/*     */ import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
/*     */ import net.sf.jasperreports.engine.util.SimpleFileResolver;
/*     */ import org.primefaces.model.DefaultStreamedContent;
/*     */ import org.primefaces.model.StreamedContent;
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
/*     */ @ManagedBean(name="mostrarReporte")
/*     */ @SessionScoped
/*     */ public class MostrarReporte
/*     */ {
/*     */   @Inject
/*     */   ConeccionReportLocal coneccionLocal;
/*     */   
/*     */   public StreamedContent runReporte(String carpeta, String nombre, Map<String, Object> parametros, Integer tipoTransporte)
/*     */     throws SQLException
/*     */   {
/*  57 */     return runReporte(carpeta, nombre, parametros, "pdf", null, tipoTransporte);
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
/*     */   public StreamedContent runReporte(String carpeta, String nombre, Map<String, Object> parametros, String tipo, String nombreArchivo, boolean sw_img, Integer tipoTransporte)
/*     */   {
/*  72 */     if (parametros == null) {
/*  73 */       parametros = new HashMap();
/*     */     }
/*  75 */     Connection conn = null;
/*     */     try {
/*  77 */       conn = this.coneccionLocal.getSesionSistema();
/*     */     } catch (Exception e) {
/*  79 */       System.out.println("ERROR: " + this.coneccionLocal);
/*  80 */       e.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  86 */     byte[] fbytes = null;
/*  87 */     StreamedContent file = null;
/*     */     
/*  89 */     String reporte = "reportes/" + carpeta + "/" + nombre + ".jrxml";
/*     */     
/*  91 */     FacesContext fContext = FacesContext.getCurrentInstance();
/*  92 */     ServletContext contexts = (ServletContext)fContext.getExternalContext().getContext();
/*     */     
/*  94 */     File f = new File(contexts.getRealPath(reporte));
/*     */     
/*     */ 
/*     */     String imagen;
/*     */     
/*     */     // String imagen;
/*     */     
/* 101 */     if (sw_img) {
/* 102 */       imagen = contexts.getRealPath("resources").concat("/img");
/*     */     }
/*     */     else {
/* 105 */       imagen = contexts.getRealPath("resources").concat("/img/logo/logolp.jpg");
/*     */     }
/* 107 */     String imagenTwo = contexts.getRealPath("resources").concat("/img/logo/logo_2.jpg");
/* 108 */     String imagenThree = contexts.getRealPath("resources").concat("/img/logo/logo_marca_agua_dos.jpg");
/*     */     
/* 110 */     String imagenForm100 = contexts.getRealPath("resources").concat("/img/logo/form100.jpg");
/*     */     String imagenForm101;
/* 112 */     // String imagenForm101; 
              if (tipoTransporte.intValue() == 1) {
/* 113 */       imagenForm101 = contexts.getRealPath("resources").concat("/img/logo/form101i.jpg"); 
              } else { 
                // String imagenForm101;
/* 114 */       if (tipoTransporte.intValue() == 2) {
/* 115 */         imagenForm101 = contexts.getRealPath("resources").concat("/img/logo/form101e.jpg"); 
                } else { 
                  // String imagenForm101;
/* 116 */         if (tipoTransporte.intValue() == 3) {
/* 117 */           imagenForm101 = contexts.getRealPath("resources").concat("/img/logo/logo_rm.jpg");
/*     */         } else {
/* 119 */           imagenForm101 = contexts.getRealPath("resources").concat("");
/*     */         }
/*     */       }
/*     */     }
/* 123 */     String subreporte = contexts.getRealPath("reportes").concat("\\" + carpeta + "\\");
/*     */     
/*     */     try
/*     */     {
/* 127 */       Locale locale = new Locale("es", "ES");
/* 128 */       parametros.put("REPORT_FILE_RESOLVER", new SimpleFileResolver(new File(contexts.getRealPath("/"))));
/* 129 */       parametros.put("REPORT_VIRTUALIZER", new JRFileVirtualizer(10));
/* 130 */       parametros.put("IMAGE_DIR", imagen);
/* 131 */       parametros.put("IMAGE_DIR_TWO", imagenTwo);
/* 132 */       parametros.put("SUBREPORT_DIR", subreporte);
/* 133 */       parametros.put("REPORT_LOCALE", Locale.US);
/* 134 */       parametros.put("IMAGE_DIR_THREE", imagenThree);
/* 135 */       parametros.put("IMAGE_DIR_FORM100", imagenForm100);
/* 136 */       parametros.put("IMAGE_DIR_FORM101", imagenForm101);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */       JasperReport jasperReport = JasperCompileManager.compileReport(f.getPath());
/* 143 */       JasperPrint imp = JasperFillManager.fillReport(jasperReport, parametros, conn);
/*     */       
/* 145 */       if (tipo.equals("pdf"))
/*     */       {
/* 147 */         fbytes = JasperExportManager.exportReportToPdf(imp);
/* 148 */         if (fbytes == null) {
/* 149 */           fbytes = new byte[0];
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 154 */         String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
/*     */         
/* 156 */         ByteArrayInputStream stream = new ByteArrayInputStream(fbytes);
/* 157 */         file = new DefaultStreamedContent(stream, "application/pdf", nombre + timeStamp + ".pdf");
/*     */       }
/*     */       
/* 160 */       conn.close();
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 165 */       System.out.println("ERROR: runReporte " + e);
/* 166 */       e.printStackTrace();
/*     */     }
/*     */     
/* 169 */     return file;
/*     */   }
/*     */   
/*     */   public StreamedContent runReporte(String carpeta, String nombre, Map<String, Object> parametros, String tipo, String nombreArchivo, Integer tipoTransporte)
/*     */   {
/* 174 */     return runReporte(carpeta, nombre, parametros, tipo, nombreArchivo, false, tipoTransporte);
/*     */   }
/*     */ }