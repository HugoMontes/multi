/*     */ package org.erp.form;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.ejb.LocalBean;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ejb.TransactionAttribute;
/*     */ import javax.ejb.TransactionAttributeType;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.Query;
/*     */ import org.erp.entities.TransporteExterno;
/*     */ import org.erp.entities.TransporteInterno;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.CrudDao;
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
/*     */ public class InternoEjbBean
/*     */   extends CrudDao
/*     */   implements InternoEjbBeanLocal
/*     */ {
/*     */   public List<TransporteInterno> listadoTransInterno(Integer idCooperativa)
/*     */     throws AdminException
/*     */   {
/*  42 */     List<TransporteInterno> lista = new ArrayList();
/*     */     try {
/*  44 */       StringBuilder query = new StringBuilder();
/*  45 */       query.append(" select inte ");
/*  46 */       query.append(" from TransporteInterno inte ");
/*  47 */       query.append(" where inte.estado = :estado ");
/*  48 */       if (idCooperativa != null) {
/*  49 */         query.append(" and inte.idCooperativa.id = :idCooperativa ");
/*     */       }
/*  51 */       if (idCooperativa == null) {
/*  52 */         query.append(" and inte.indEstadoRegistro = :estadoRegistro ");
/*     */       }
/*  54 */       query.append(" order by inte.id DESC ");
/*     */       
/*  56 */       Query consulta = this.em.createQuery(query.toString());
/*  57 */       consulta.setParameter("estado", "HAB");
/*  58 */       if (idCooperativa != null) {
/*  59 */         consulta.setParameter("idCooperativa", idCooperativa);
/*     */       }
/*  61 */       if (idCooperativa == null) {
/*  62 */         consulta.setParameter("estadoRegistro", new Integer(130));
/*     */       }
/*     */       
/*  65 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/*  68 */       e.printStackTrace();
/*     */     }
/*  70 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarTransporteInterno(TransporteInterno transporteInterno) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  78 */       if ((transporteInterno.getId() != null) && (transporteInterno.getId().intValue() > 0)) {
/*  79 */         transporteInterno.setFechaMod(new Date());
/*  80 */         this.em.merge(transporteInterno);
/*     */       } else {
/*  82 */         transporteInterno.setFechaReg(new Date());
/*  83 */         transporteInterno.setEstado("HAB");
/*  84 */         this.em.persist(transporteInterno);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  88 */       e.printStackTrace();
/*  89 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarTransporteExterno(TransporteExterno transporteExterno) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  98 */       if ((transporteExterno.getId() != null) && (transporteExterno.getId().intValue() > 0)) {
/*  99 */         transporteExterno.setFechaMod(new Date());
/* 100 */         this.em.merge(transporteExterno);
/*     */       } else {
/* 102 */         transporteExterno.setFechaReg(new Date());
/* 103 */         transporteExterno.setEstado("HAB");
/* 104 */         this.em.persist(transporteExterno);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 108 */       e.printStackTrace();
/* 109 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\form\InternoEjbBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */