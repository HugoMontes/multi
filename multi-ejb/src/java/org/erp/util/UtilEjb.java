/*    */ package org.erp.util;
/*    */ 
/*    */ public class UtilEjb
/*    */ {
/*    */   public static String makePrintStringTitulo(int lineChars, String producto, String cantidad, String costo, String subTotal) {
/*  6 */     int spaces = 0;
/*  7 */     String tab = "";
/*    */     try {
/*  9 */       spaces = lineChars - (producto.length() + cantidad.length() + costo.length() + subTotal.length() + 2);
/* 10 */       for (int j = 0; j < spaces; j++) {
/* 11 */         tab = tab + " ";
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 16 */     return producto + tab + cantidad + " " + costo + " " + subTotal;
/*    */   }
/*    */   
/*    */   public static String makePrintStringProductos(int lineChars, String producto, String cantidad, String costo, String subTotal) {
/* 20 */     int spaces = 0;
/* 21 */     String tab = "";
/* 22 */     String recoSubTotal = "";
/* 23 */     String recoCosto = "";
/*    */     try {
/* 25 */       if (subTotal.length() >= 6) {
/* 26 */         recoSubTotal = " ";
/*    */       } else {
/* 28 */         recoSubTotal = "  ";
/* 29 */         recoCosto = " ";
/*    */       }
/*    */       
/* 32 */       spaces = lineChars - (producto.length() + cantidad.length() + costo.length() + subTotal.length() + (2 + recoSubTotal.length() + recoCosto.length()));
/* 33 */       for (int j = 0; j < spaces; j++) {
/* 34 */         tab = tab + " ";
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 39 */     return producto + tab + cantidad + " " + recoCosto + costo + " " + recoSubTotal + subTotal;
/*    */   }
/*    */   
/*    */   public static String makePrintStringCostoTotal(int lineChars, String producto, String subTotal) {
/* 43 */     int spaces = 0;
/* 44 */     String tab = "";
/*    */     try {
/* 46 */       spaces = lineChars - (producto.length() + subTotal.length() + 2);
/* 47 */       for (int j = 0; j < spaces; j++) {
/* 48 */         tab = tab + " ";
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 53 */     return producto + tab + subTotal;
/*    */   }
/*    */   
/*    */   public static String makePrintStringDetalle(int lineChars, String producto, String cantidad, String medida) {
/* 57 */     int spaces = 0;
/* 58 */     String tab = "";
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 67 */     return "     " + producto + "   " + cantidad + "  " + medida;
/*    */   }
/*    */   
/*    */   public static String makePrintCenter(int lineChars, String titulo, int inicio) {
/* 71 */     int spaces = 0;
/* 72 */     String tab = "";
/*    */     try {
/* 74 */       spaces = inicio;
/* 75 */       for (int j = 0; j < spaces; j++) {
/* 76 */         tab = tab + " ";
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 81 */     return tab + titulo;
/*    */   }
/*    */   
/*    */   public static String makeDibujarLinea(int lineChars, String tipoLinea) {
/* 85 */     int spaces = 0;
/* 86 */     String tab = "";
/*    */     try {
/* 88 */       spaces = lineChars;
/* 89 */       for (int j = 0; j < spaces; j++) {
/* 90 */         tab = tab + tipoLinea;
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/* 95 */     return tab;
/*    */   }
/*    */ }
