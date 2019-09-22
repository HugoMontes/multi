/*    */ package org.erp.seg.ML.seg;
/*    */ 
/*    */ import javax.annotation.Resource;
/*    */ import javax.ejb.LocalBean;
/*    */ import javax.ejb.SessionContext;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.ejb.TransactionAttribute;
/*    */ import javax.ejb.TransactionAttributeType;
/*    */ import javax.ejb.TransactionManagement;
/*    */ import javax.ejb.TransactionManagementType;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
/*    */ import org.erp.entities.SegMenurol;
/*    */ import org.erp.util.AdminException;
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
/*    */ @Stateless
/*    */ @LocalBean
/*    */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*    */ public class SegMenuRolMLBean
/*    */   implements SegMenuRolMLBeanLocal
/*    */ {
/*    */   @PersistenceContext(unitName="admindbDS")
/*    */   EntityManager em;
/*    */   @Resource
/*    */   private SessionContext context;
/*    */   
/*    */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*    */   public void guardarMenuRol(SegMenurol segMenurol)
/*    */     throws AdminException
/*    */   {
/*    */     try
/*    */     {
/* 51 */       if ((segMenurol.getId() != null) && (segMenurol.getId().intValue() > 0)) {
/* 52 */         this.em.merge(segMenurol);
/*    */       } else {
/* 54 */         segMenurol.setEstado("HAB");
/* 55 */         this.em.persist(segMenurol);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 59 */       e.printStackTrace();
/* 60 */       this.context.setRollbackOnly();
/* 61 */       throw new AdminException(e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*    */   public void eliminarMenuRol(SegMenurol segMenurol)
/*    */     throws AdminException
/*    */   {
/*    */     try
/*    */     {
/* 72 */       if ((segMenurol.getId() != null) && (segMenurol.getId().intValue() > 0)) {
/* 73 */         segMenurol.setEstado("DES");
/* 74 */         this.em.merge(segMenurol);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 78 */       e.printStackTrace();
/* 79 */       this.context.setRollbackOnly();
/* 80 */       throw new AdminException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\ML\seg\SegMenuRolMLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */