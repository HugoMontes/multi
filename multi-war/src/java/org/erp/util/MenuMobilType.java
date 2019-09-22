/*    */ package org.erp.util;
/*    */ 
/*    */ public enum MenuMobilType
/*    */ {
/*  5 */   HOME(
/*    */   
/*    */ 
/*  8 */     Integer.valueOf(20), "Home"), 
/*  9 */   REGISTRO_COMANDAS(Integer.valueOf(21), "registro Comandas"), 
/* 10 */   LISTADO_COMANDAS(Integer.valueOf(22), "Listado de Comandas"), 
/* 11 */   REGISTRO_COVER(Integer.valueOf(23), "Registro de cover"), 
/* 12 */   LISTADO_COVER(Integer.valueOf(24), "Listado de cover"), 
/* 13 */   MENU_BAR(Integer.valueOf(25), "menu de bar");
/*    */   
/*    */   private Integer code;
/*    */   
/* 17 */   private MenuMobilType(Integer code, String value) { this.code = code;
/* 18 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private String value;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Integer getCode()
/*    */   {
/* 33 */     return this.code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setCode(Integer code)
/*    */   {
/* 42 */     this.code = code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 51 */     return this.value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setValue(String value)
/*    */   {
/* 60 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\MenuMobilType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */