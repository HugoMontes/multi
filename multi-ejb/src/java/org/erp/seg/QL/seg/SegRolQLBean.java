/*    */ package org.erp.seg.QL.seg;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import javax.ejb.SessionContext;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.ejb.TransactionManagement;
/*    */ import javax.ejb.TransactionManagementType;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
/*    */ import javax.persistence.Query;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Stateless
/*    */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*    */ public class SegRolQLBean
/*    */   implements SegRolQLBeanLocal
/*    */ {
/*    */   @PersistenceContext(unitName="admindbDS")
/*    */   EntityManager em;
/*    */   @Resource
/*    */   private SessionContext context;
/*    */   
/*    */   public List<SegRol> listaRoles()
/*    */     throws AdminException
/*    */   {
/* 52 */     List<SegRol> lista = new ArrayList();
/*    */     try {
/* 54 */       StringBuilder query = new StringBuilder();
/*    */       
/* 56 */       query.append(" select segRol ");
/* 57 */       query.append(" from SegRol segRol ");
/* 58 */       query.append(" where segRol.estado = :estado ");
/*    */       
/* 60 */       Query consulta = this.em.createQuery(query.toString());
/* 61 */       consulta.setParameter("estado", "HAB");
/*    */       
/* 63 */       lista = consulta.getResultList();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 67 */       e.printStackTrace();
/* 68 */       this.context.setRollbackOnly();
/* 69 */       throw new AdminException(e);
/*    */     }
/*    */     
/* 72 */     return lista;
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\SegRolQLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */