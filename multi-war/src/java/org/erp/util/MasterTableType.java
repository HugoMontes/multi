/*    */ package org.erp.util;
/*    */ 
/*    */ 
/*    */ public enum MasterTableType
/*    */ {
/*  6 */   CARGO(Integer.valueOf(1), "CARGOS"), 
/*  7 */   TIPO_DE_LICENCIA(Integer.valueOf(2), "TIPO DE LICENCIA"), 
/*  8 */   ACTOR_PRODUCTIVO(Integer.valueOf(3), "ACTOR PRODUCTIVO"), 
/*  9 */   CI_EXPEDIDO(Integer.valueOf(4), "CI EXPEDIDO"), 
/* 10 */   TIPO_SUCURSAL(Integer.valueOf(5), "TIPO DE SUCURSAL"), 
/* 11 */   ACTIVIDAD_MINERA(Integer.valueOf(6), "ACTIVIDAD MINERA"), 
/* 12 */   MINERAL_METALICO(Integer.valueOf(7), "MINERAL METALICO"), 
/* 13 */   MINERAL_NO_METALICO(Integer.valueOf(8), "MINERAL No METAL"), 
/* 14 */   UNIDAD_MEDIDA(Integer.valueOf(10), "UNIDAD DE MEDIDA"), 
/* 15 */   ATES(Integer.valueOf(11), "ATES"), 
/* 16 */   PRESENTACION_PRODUCTOS(Integer.valueOf(12), "PRESENTACION DE PRODCUTOS"), 
/* 17 */   ESTADO_REGISTRO_FORM(Integer.valueOf(13), "ESTADO REGISTRO FORMULARIO"), 
/* 18 */   TRANCA_DE_SALIDA(Integer.valueOf(14), "TRANCA DE SALIDA"), 
/* 19 */   TIPO_DE_EXPORTACION(Integer.valueOf(15), "TIPO DE EXPORTACION"), 
/* 20 */   CARACTERISTICAS_MINERAL_METAL(Integer.valueOf(16), "CARACTERISTICAS MINERAL O METAL"), 
/* 21 */   EXPORTACION_TOTAL_PARCIAL(Integer.valueOf(17), "EXPORTACION TOTAL O PARCIAL"), 
/* 22 */   DESCRIPCION_DEL_MINERAL_EXPORTACION(Integer.valueOf(18), "EXPORTACION TOTAL O PARCIAL EXPORTACION"), 
/* 23 */   TIPO_TRANSPORTE_EXPORTACION(Integer.valueOf(19), "TIPO DE TRANSPORTE EXPORTACION"), 
/* 24 */   SELECT_TIPO_MINERAL(Integer.valueOf(20), "SELECT TIPO DE MINERAL"), 
/* 25 */   IND_TIPO_LABORATORIO(Integer.valueOf(21), "LABORATORIO"), 
/* 26 */   IND_ADUANA_SALIDA(Integer.valueOf(22), "ADUANA DE SALIDA"), 
/* 27 */   IND_TIPO_FORMULARIO(Integer.valueOf(23), "TIPO DE FORMULARIO"), 
/* 28 */   IND_COTIZACION_UNIDAD_MEDIDA(Integer.valueOf(24), "COTIZACION UNIDAD DE MEDIDA"), 
/* 29 */   IND_TIPO_MONEDA(Integer.valueOf(25), "TIPO DE MONEDA"), 
/* 30 */   IND_ESTADO_PAGO_FORM(Integer.valueOf(26), "ESTADO DE PAGO DE FORMULARIO");
/*    */   
/*    */   private Integer code;
/*    */   
/* 34 */   private MasterTableType(Integer code, String value) { this.code = code;
/* 35 */     this.value = value;
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
/* 50 */     return this.code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setCode(Integer code)
/*    */   {
/* 59 */     this.code = code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 68 */     return this.value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setValue(String value)
/*    */   {
/* 77 */     this.value = value;
/*    */   }
/*    */ }