/*    */ package org.erp.form;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.math.BigDecimal;
/*    */ import java.text.DecimalFormat;
/*    */ 
/*    */ 
/*    */ public class Numero
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 12 */     String nume = "1312321.09";
/* 13 */     BigDecimal num = new BigDecimal(nume);
/* 14 */     DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
/* 15 */     String numeroFormatedo = decimalFormat.format(num);
/* 16 */     String uno = numeroFormatedo.replace(".", ",");
/* 17 */     String primera = uno.substring(0, uno.length() - 3);
/* 18 */     String segunda = uno.substring(uno.length() - 3, uno.length());
/* 19 */     String segunda_2 = segunda.replace(",", ".");
/* 20 */     System.out.println("FINAL: " + primera + segunda_2);
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\form\Numero.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */