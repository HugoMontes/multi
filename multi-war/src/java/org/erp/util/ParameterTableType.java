/*    */ package org.erp.util;
/*    */ 
/*    */ 
/*    */ public enum ParameterTableType
/*    */ {
/*  6 */   MINERAL_METALICOS(Integer.valueOf(119), "MINERAL METALICOS"), 
/*  7 */   MINERAL_NO_METALICOS(Integer.valueOf(120), "MINERAL NO METALICOS"), 
/*    */   
/*  9 */   ESTADO_EN_PROCESO_FORM(Integer.valueOf(129), "ESTADO EN PROCESO FORM"), 
/* 10 */   ESTADO_EN_EMITIDO_FORM(Integer.valueOf(130), "ESTADO EN EMISION FORM"), 
/*    */   
/* 12 */   TIPO_EXPORTACION_PARCIAL(Integer.valueOf(142), "TIPO DE EXPORTACION PARCIAL"), 
/*    */   
/* 14 */   REGALIA_EXPORTACION(Integer.valueOf(243), "REGALIA MINERA DE EXPORTACION"), 
/* 15 */   REGALIA_AGENTE_RETENCION(Integer.valueOf(244), "REGALIA AGENTE DE RETENCION"), 
/*    */   
/* 17 */   TIPO_MONEDA_DOLAR(Integer.valueOf(249), "TIPO DE MONEDA DOLAR"), 
/*    */   
/* 19 */   ESTADO_PAGO_PAGADO(Integer.valueOf(251), "ESTADO DE PAGO PAGADO"), 
/* 20 */   ESTADO_PAGO_PENDIENTE(Integer.valueOf(252), "ESTADO DE PAGO PENDIENTE"), 
/*    */   
/* 22 */   IND_FORM_EXPORTACION(Integer.valueOf(243), "FORMULARIO DE EXPORTACION"), 
/* 23 */   IND_FORM_AGENTE_RETENCION(Integer.valueOf(244), "FORMULARIO AGENTES DE RETENCION"), 
/*    */   
/* 25 */   COORELATIVO_FORMULARIOS(Integer.valueOf(255), "CORRELATIVO DE FORMULARIO"), 
/*    */   
/* 27 */   IND_UNIDAD_MEDIDA_PORCENTAJE(Integer.valueOf(217), "PORCENTAJE"), 
/* 28 */   IND_UNIDAD_MEDIDA_GTM(Integer.valueOf(218), "GTM");
/*    */   
/*    */   private ParameterTableType(Integer code, String value) {
/* 31 */     this.code = code;
/* 32 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private Integer code;
/*    */   
/*    */ 
/*    */ 
/*    */   private String value;
/*    */   
/*    */ 
/*    */   public Integer getCode()
/*    */   {
/* 47 */     return this.code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setCode(Integer code)
/*    */   {
/* 56 */     this.code = code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 65 */     return this.value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setValue(String value)
/*    */   {
/* 74 */     this.value = value;
/*    */   }
/*    */ }