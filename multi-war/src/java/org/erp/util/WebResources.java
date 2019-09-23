/*    */ package org.erp.util;
/*    */ 
/*    */ import javax.enterprise.context.RequestScoped;
/*    */ import javax.enterprise.inject.Produces;
/*    */ import javax.faces.context.FacesContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WebResources
/*    */ {
/*    */   @Produces
/*    */   @RequestScoped
/*    */   public FacesContext produceFacesContext()
/*    */   {
/* 41 */     return FacesContext.getCurrentInstance();
/*    */   }
/*    */ }
