/*    */ package org.erp.form;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.ejb.LocalBean;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.ejb.TransactionAttribute;
/*    */ import javax.ejb.TransactionAttributeType;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.Query;
/*    */ import org.erp.entities.TransporteExterno;
/*    */ import org.erp.util.AdminException;
/*    */ import org.erp.util.CrudDao;
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
/*    */ public class ExternoEjbBean
/*    */   extends CrudDao
/*    */   implements ExternoEjbBeanLocal
/*    */ {
/*    */   public List<TransporteExterno> listadoTransExterno(Integer idCooperativa)
/*    */     throws AdminException
/*    */   {
/* 41 */     List<TransporteExterno> lista = new ArrayList();
/*    */     try {
/* 43 */       StringBuilder query = new StringBuilder();
/* 44 */       query.append(" select exte ");
/* 45 */       query.append(" from TransporteExterno exte ");
/* 46 */       query.append(" where exte.estado = :estado ");
/* 47 */       if (idCooperativa != null) {
/* 48 */         query.append(" and exte.idCooperativa.id = :idCooperativa ");
/*    */       }
/*    */       
/* 51 */       if (idCooperativa == null) {
/* 52 */         query.append(" and exte.indEstadoRegistro = :estadoRegistro ");
/*    */       }
/* 54 */       query.append(" order by exte.id DESC ");
/*    */       
/* 56 */       Query consulta = this.em.createQuery(query.toString());
/* 57 */       consulta.setParameter("estado", "HAB");
/* 58 */       if (idCooperativa != null) {
/* 59 */         consulta.setParameter("idCooperativa", idCooperativa);
/*    */       }
/* 61 */       if (idCooperativa == null) {
/* 62 */         consulta.setParameter("estadoRegistro", new Integer(130));
/*    */       }
/*    */       
/* 65 */       lista = consulta.getResultList();
/*    */     }
/*    */     catch (Exception e) {
/* 68 */       e.printStackTrace();
/*    */     }
/* 70 */     return lista;
/*    */   }
/*    */   
/*    */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*    */   public void guardarTransporteExterno(TransporteExterno transporteExterno) throws AdminException
/*    */   {
/*    */     try
/*    */     {
/* 78 */       if ((transporteExterno.getId() != null) && (transporteExterno.getId().intValue() > 0)) {
/* 79 */         transporteExterno.setFechaMod(new Date());
/* 80 */         this.em.merge(transporteExterno);
/*    */       } else {
/* 82 */         transporteExterno.setFechaReg(new Date());
/* 83 */         transporteExterno.setEstado("HAB");
/* 84 */         this.em.persist(transporteExterno);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 88 */       e.printStackTrace();
/* 89 */       throw new AdminException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\form\ExternoEjbBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */