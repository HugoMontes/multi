/*    */ package org.erp.util;
/*    */ 
/*    */ import javax.ejb.ApplicationException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ApplicationException(rollback=true)
/*    */ public class AdminException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Object objecto;
/*    */   
/*    */   public AdminException(String mensaje)
/*    */   {
/* 17 */     super(mensaje);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AdminException(Throwable causa)
/*    */     throws AdminException
/*    */   {
/* 29 */     super(causa);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AdminException(String mensaje, Throwable causa, Object object)
/*    */   {
/* 41 */     super(mensaje, causa);
/* 42 */     this.objecto = object;
/*    */   }
/*    */   
/*    */   public Object getObjeto() {
/* 46 */     return this.objecto;
/*    */   }
/*    */ }