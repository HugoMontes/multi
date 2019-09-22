/*    */ package org.erp.util;
/*    */ 
/*    */ import java.lang.reflect.Member;
/*    */ import java.util.logging.Logger;
/*    */ import javax.enterprise.inject.Produces;
/*    */ import javax.enterprise.inject.spi.InjectionPoint;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
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
/*    */ public class Resources
/*    */ {
/*    */   @Produces
/*    */   @PersistenceContext
/*    */   private EntityManager em;
/*    */   
/*    */   @Produces
/*    */   public Logger produceLog(InjectionPoint injectionPoint)
/*    */   {
/* 43 */     return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
/*    */   }
/*    */ }
