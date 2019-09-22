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


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\WebResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */