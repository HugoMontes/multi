/*    */ package org.erp.util;
/*    */ 
/*    */ import java.io.PrintStream;
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
/*    */ 
/*    */ public class GeneradorClaves
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*    */     try
/*    */     {
/* 23 */       String llave = "Gobierno Autonomo de La Paz";
/* 24 */       String tipoLicencia = "66";
/* 25 */       String desde = "16/08/2017";
/* 26 */       String hasta = "21/05/2019";
/*    */       
/* 28 */       System.out.println("TIPO DE LICENCIA: " + AESEncryptUtils.encrypt(llave, tipoLicencia));
/* 29 */       System.out.println("DESDE: " + AESEncryptUtils.encrypt(llave, desde));
/* 30 */       System.out.println("DESDE: " + AESEncryptUtils.encrypt(llave, hasta));
/*    */       
/* 32 */       String serie = AESEncryptUtils.encrypt(llave, tipoLicencia + desde + hasta);
/* 33 */       System.out.println("SERIE: " + serie);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 37 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\GeneradorClaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */