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
/*    */ import org.erp.entities.SegRol;
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
/*    */ public class SegRolMLBean
/*    */   implements SegRolMLBeanLocal
/*    */ {
/*    */   @PersistenceContext(unitName="admindbDS")
/*    */   EntityManager em;
/*    */   @Resource
/*    */   private SessionContext context;
/*    */   
/*    */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*    */   public void guardarRol(SegRol segRol)
/*    */     throws AdminException
/*    */   {
/*    */     try
/*    */     {
/* 51 */       if ((segRol.getId() != null) && (segRol.getId().intValue() > 0)) {
/* 52 */         this.em.merge(segRol);
/*    */       } else {
/* 54 */         segRol.setEstado("HAB");
/* 55 */         this.em.persist(segRol);
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
/*    */ 
/*    */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*    */   public void eliminarRol(SegRol segRol)
/*    */     throws AdminException
/*    */   {
/*    */     try
/*    */     {
/* 73 */       if ((segRol.getId() != null) && (segRol.getId().intValue() > 0)) {
/* 74 */         segRol.setEstado("DES");
/* 75 */         this.em.merge(segRol);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 79 */       e.printStackTrace();
/* 80 */       this.context.setRollbackOnly();
/* 81 */       throw new AdminException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\ML\seg\SegRolMLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */