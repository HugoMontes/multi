/*    */ package org.erp.util;
/*    */ 
/*    */ import jpos.JposException;
/*    */ import jpos.POSPrinter;
/*    */ import jpos.POSPrinterControl113;
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
/*    */ public class ImprimirComanda
/*    */ {
/*    */   POSPrinterControl113 ptr;
/*    */   
/*    */   public ImprimirComanda()
/*    */   {
/* 24 */     this.ptr = new POSPrinter();
/*    */   }
/*    */   
/*    */   private void iniciarImpresora() throws AdminException
/*    */   {
/*    */     try {
/* 30 */       this.ptr.open("POSPrinter");
/* 31 */       this.ptr.claim(1000);
/* 32 */       this.ptr.setDeviceEnabled(true);
/*    */     } catch (JposException e) {
/* 34 */       e.printStackTrace();
/* 35 */       throw new AdminException("Error al cerrar conexion con la impresora");
/*    */     }
/*    */   }
/*    */   
/*    */   private void cerrarImpresora() throws AdminException
/*    */   {
/*    */     try
/*    */     {
/* 43 */       this.ptr.setDeviceEnabled(false);
/*    */       
/*    */ 
/* 46 */       this.ptr.release();
/*    */       
/*    */ 
/* 49 */       this.ptr.close();
/*    */     }
/*    */     catch (JposException e)
/*    */     {
/* 53 */       e.printStackTrace();
/* 54 */       throw new AdminException("Error al cerrar conexion con la impresora");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public static String makePrintString(int lineChars, String producto, String cantidad, String costo, String subTotal)
/*    */   {
/* 61 */     int spaces = 0;
/* 62 */     String tab = "";
/*    */     try {
/* 64 */       spaces = lineChars - (producto.length() + cantidad.length() + costo.length() + subTotal.length() + 2);
/* 65 */       for (int j = 0; j < spaces; j++) {
/* 66 */         tab = tab + " ";
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 71 */     return producto + tab + cantidad + " " + costo + " " + subTotal;
/*    */   }
/*    */   
/*    */   public static String makePrintCenter(int lineChars, String titulo, int inicio) {
/* 75 */     int spaces = 0;
/* 76 */     String tab = "";
/*    */     try {
/* 78 */       spaces = inicio;
/* 79 */       for (int j = 0; j < spaces; j++) {
/* 80 */         tab = tab + " ";
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 85 */     return tab + titulo;
/*    */   }
/*    */ }
