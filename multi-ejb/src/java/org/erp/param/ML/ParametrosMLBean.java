/*     */ package org.erp.param.ML;
/*     */ 
/*     */ import javax.annotation.Resource;
/*     */ import javax.ejb.LocalBean;
/*     */ import javax.ejb.SessionContext;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ejb.TransactionAttribute;
/*     */ import javax.ejb.TransactionAttributeType;
/*     */ import javax.ejb.TransactionManagement;
/*     */ import javax.ejb.TransactionManagementType;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.PersistenceContext;
/*     */ import org.erp.entities.MasterTable;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.util.AdminException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Stateless
/*     */ @LocalBean
/*     */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*     */ public class ParametrosMLBean
/*     */   implements ParametrosMLBeanLocal
/*     */ {
/*     */   @PersistenceContext(unitName="admindbDS")
/*     */   EntityManager em;
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarMaster(MasterTable masterTable)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  53 */       if ((masterTable.getId() != null) && (masterTable.getId().intValue() > 0)) {
/*  54 */         this.em.merge(masterTable);
/*     */       } else {
/*  56 */         masterTable.setEstado("HAB");
/*  57 */         this.em.persist(masterTable);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  61 */       e.printStackTrace();
/*  62 */       this.context.setRollbackOnly();
/*  63 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarParameter(ParameterTable parameterTable)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  75 */       if ((parameterTable.getId() != null) && (parameterTable.getId().intValue() > 0)) {
/*  76 */         this.em.merge(parameterTable);
/*     */       } else {
/*  78 */         parameterTable.setEstado("HAB");
/*  79 */         this.em.persist(parameterTable);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  83 */       e.printStackTrace();
/*  84 */       this.context.setRollbackOnly();
/*  85 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarMaster(MasterTable masterTable)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  95 */       if ((masterTable.getId() != null) && (masterTable.getId().intValue() > 0)) {
/*  96 */         masterTable.setEstado("DES");
/*  97 */         this.em.merge(masterTable);
/*     */       }
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       this.context.setRollbackOnly();
/* 102 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarParameter(ParameterTable parameterTable) throws AdminException
/*     */   {
/*     */     try {
/* 110 */       if ((parameterTable.getId() != null) && (parameterTable.getId().intValue() > 0)) {
/* 111 */         parameterTable.setEstado("DES");
/* 112 */         this.em.merge(parameterTable);
/*     */       }
/*     */     } catch (Exception e) {
/* 115 */       e.printStackTrace();
/* 116 */       this.context.setRollbackOnly();
/* 117 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\ML\ParametrosMLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */