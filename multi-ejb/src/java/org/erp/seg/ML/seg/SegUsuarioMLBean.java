/*     */ package org.erp.seg.ML.seg;
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
/*     */ import javax.persistence.Query;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.SegPermiso;
/*     */ import org.erp.entities.SegRol;
/*     */ import org.erp.entities.SegUsuario;
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
/*     */ @Stateless
/*     */ @LocalBean
/*     */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*     */ public class SegUsuarioMLBean
/*     */   implements SegUsuarioMLBeanLocal
/*     */ {
/*     */   @PersistenceContext(unitName="admindbDS")
/*     */   EntityManager em;
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarUsuario(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  52 */       if ((segUsuario.getIdCooperativa() == null) || (segUsuario.getIdCooperativa().getId() == null)) {
/*  53 */         segUsuario.setIdCooperativa(null);
/*     */       }
/*     */       
/*  56 */       if ((segUsuario.getId() != null) && (segUsuario.getId().intValue() > 0))
/*     */       {
/*  58 */         this.em.merge(segUsuario);
/*     */       }
/*     */       else {
/*  61 */         segUsuario.setEstado("HAB");
/*  62 */         this.em.persist(segUsuario);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       this.context.setRollbackOnly();
/*  68 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarCambioPass(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  79 */       if ((segUsuario.getId() != null) && (segUsuario.getId().intValue() > 0)) {
/*  80 */         this.em.merge(segUsuario);
/*     */       }
/*     */     } catch (Exception e) {
/*  83 */       e.printStackTrace();
/*  84 */       this.context.setRollbackOnly();
/*  85 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarUsuario(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  98 */       if ((segUsuario.getId() != null) && (segUsuario.getId().intValue() > 0)) {
/*  99 */         segUsuario.setEstado("DES");
/* 100 */         this.em.merge(segUsuario);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */       this.context.setRollbackOnly();
/* 106 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void saveOrUpdateRolesPorUsuario(SegUsuario segUsuario, SegRol[] selectRoles)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 120 */       if (segUsuario.getId() != null) {
/* 121 */         StringBuilder query = new StringBuilder();
/* 122 */         query.append(" delete from SegPermiso segPer where segPer.idUsuario.id=:idUsuario ");
/* 123 */         Query consulta = this.em.createQuery(query.toString());
/* 124 */         consulta.setParameter("idUsuario", segUsuario.getId());
/* 125 */         consulta.executeUpdate();
/*     */         
/*     */ 
/* 128 */         for (int i = 0; i < selectRoles.length; i++)
/*     */         {
/* 130 */           SegPermiso segPermiso = new SegPermiso();
/* 131 */           segPermiso.setIdRol(selectRoles[i]);
/* 132 */           segPermiso.setIdUsuario(segUsuario);
/* 133 */           segPermiso.setEstado("HAB");
/* 134 */           this.em.persist(segPermiso);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 140 */       e.printStackTrace();
/* 141 */       this.context.setRollbackOnly();
/* 142 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\ML\seg\SegUsuarioMLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */