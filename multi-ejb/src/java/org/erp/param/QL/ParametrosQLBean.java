/*    */ package org.erp.param.QL;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import javax.ejb.LocalBean;
/*    */ import javax.ejb.SessionContext;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.Query;
/*    */ import org.erp.entities.MasterTable;
/*    */ import org.erp.entities.ParameterTable;
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
/*    */ 
/*    */ 
/*    */ @Stateless
/*    */ @LocalBean
/*    */ public class ParametrosQLBean
/*    */   extends CrudDao
/*    */   implements ParametrosQLBeanLocal
/*    */ {
/*    */   @Resource
/*    */   private SessionContext context;
/*    */   
/*    */   public List<MasterTable> listaMaster()
/*    */     throws AdminException
/*    */   {
/* 46 */     List<MasterTable> lista = new ArrayList();
/*    */     try {
/* 48 */       StringBuilder query = new StringBuilder();
/*    */       
/* 50 */       query.append(" select mas ");
/* 51 */       query.append(" from MasterTable mas ");
/* 52 */       query.append(" where mas.estado = :estado ");
/* 53 */       query.append(" order by mas.nombre ASC ");
/*    */       
/* 55 */       Query consulta = this.em.createQuery(query.toString());
/* 56 */       consulta.setParameter("estado", "HAB");
/*    */       
/* 58 */       lista = consulta.getResultList();
/*    */     } catch (Exception e) {
/* 60 */       e.printStackTrace();
/* 61 */       this.context.setRollbackOnly();
/* 62 */       throw new AdminException(e);
/*    */     }
/* 64 */     return lista;
/*    */   }
/*    */   
/*    */ 
/*    */   public List<ParameterTable> listaParameter(Integer idMaster)
/*    */     throws AdminException
/*    */   {
/* 71 */     List<ParameterTable> lista = new ArrayList();
/*    */     try {
/* 73 */       StringBuilder query = new StringBuilder();
/*    */       
/* 75 */       query.append(" select par ");
/* 76 */       query.append(" from ParameterTable par ");
/* 77 */       query.append(" where par.idMaster.id = :idMaster ");
/* 78 */       query.append(" and par.estado = :estado ");
/* 79 */       query.append(" order by par.orden ASC ");
/*    */       
/* 81 */       Query consulta = this.em.createQuery(query.toString());
/* 82 */       consulta.setParameter("estado", "HAB");
/* 83 */       consulta.setParameter("idMaster", idMaster);
/*    */       
/* 85 */       lista = consulta.getResultList();
/*    */     } catch (Exception e) {
/* 87 */       e.printStackTrace();
/* 88 */       this.context.setRollbackOnly();
/* 89 */       throw new AdminException(e);
/*    */     }
/* 91 */     return lista;
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\QL\ParametrosQLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */