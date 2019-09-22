/*    */ package org.erp.to;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DocumentProcessFileTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String fileName;
/*    */   private File tempDocumentFile;
/*    */   private byte[] documentFile;
/*    */   private String streamFileDir;
/*    */   
/*    */   public String getFileName()
/*    */   {
/* 19 */     return this.fileName;
/*    */   }
/*    */   
/*    */   public void setFileName(String fileName) {
/* 23 */     this.fileName = fileName;
/*    */   }
/*    */   
/*    */   public File getTempDocumentFile() {
/* 27 */     return this.tempDocumentFile;
/*    */   }
/*    */   
/*    */   public void setTempDocumentFile(File tempDocumentFile) {
/* 31 */     this.tempDocumentFile = tempDocumentFile;
/*    */   }
/*    */   
/*    */   public byte[] getDocumentFile() {
/* 35 */     return this.documentFile;
/*    */   }
/*    */   
/*    */   public void setDocumentFile(byte[] documentFile) {
/* 39 */     this.documentFile = documentFile;
/*    */   }
/*    */   
/*    */   public String getStreamFileDir() {
/* 43 */     return this.streamFileDir;
/*    */   }
/*    */   
/*    */   public void setStreamFileDir(String streamFileDir) {
/* 47 */     this.streamFileDir = streamFileDir;
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\to\DocumentProcessFileTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */