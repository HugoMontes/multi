/*     */ package org.erp.param.ML;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.ejb.LocalBean;
/*     */ import javax.ejb.SessionContext;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ejb.TransactionAttribute;
/*     */ import javax.ejb.TransactionAttributeType;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.Query;
/*     */ import org.erp.entities.Pais;
/*     */ import org.erp.entities.TipoCambio;
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
/*     */ @Stateless
/*     */ @LocalBean
/*     */ public class TipoCambioEjbBean
/*     */   extends CrudDao
/*     */   implements TipoCambioEjbBeanLocal
/*     */ {
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public List<TipoCambio> listaTipoCambio()
/*     */     throws AdminException
/*     */   {
/*  44 */     List<TipoCambio> lista = new ArrayList();
/*     */     try {
/*  46 */       StringBuilder query = new StringBuilder();
/*     */       
/*  48 */       query.append(" select coti ");
/*  49 */       query.append(" from TipoCambio coti ");
/*  50 */       query.append(" where coti.estado = :estado ");
/*  51 */       query.append(" order by coti.id DESC ");
/*     */       
/*  53 */       Query consulta = this.em.createQuery(query.toString());
/*  54 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  56 */       lista = consulta.getResultList();
/*     */     } catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       this.context.setRollbackOnly();
/*  60 */       throw new AdminException(e);
/*     */     }
/*  62 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarTipoCambio(TipoCambio tipoCambio)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  71 */       if ((tipoCambio.getId() != null) && (tipoCambio.getId().intValue() > 0)) {
/*  72 */         tipoCambio.setFechaMod(new Date());
/*  73 */         this.em.merge(tipoCambio);
/*     */       } else {
/*  75 */         tipoCambio.setFechaReg(new Date());
/*  76 */         tipoCambio.setEstado("HAB");
/*  77 */         this.em.persist(tipoCambio);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  81 */       e.printStackTrace();
/*  82 */       this.context.setRollbackOnly();
/*  83 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarPais(Pais pais)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  93 */       if ((pais.getId() != null) && (pais.getId().intValue() > 0)) {
/*  94 */         this.em.merge(pais);
/*     */       }
/*     */       else {
/*  97 */         pais.setEstado("HAB");
/*  98 */         this.em.persist(pais);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 103 */       e.printStackTrace();
/* 104 */       this.context.setRollbackOnly();
/* 105 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarTipoCambio(TipoCambio tipoCambio) throws AdminException
/*     */   {
/*     */     try {
/* 113 */       if ((tipoCambio.getId() != null) && (tipoCambio.getId().intValue() > 0)) {
/* 114 */         tipoCambio.setFechaMod(new Date());
/* 115 */         tipoCambio.setEstado("DES");
/* 116 */         this.em.merge(tipoCambio);
/*     */       }
/*     */     } catch (Exception e) {
/* 119 */       e.printStackTrace();
/* 120 */       this.context.setRollbackOnly();
/* 121 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<Pais> listaPais()
/*     */     throws AdminException
/*     */   {
/* 130 */     List<Pais> lista = new ArrayList();
/*     */     try {
/* 132 */       StringBuilder query = new StringBuilder();
/*     */       
/* 134 */       query.append(" select coti ");
/* 135 */       query.append(" from Pais coti ");
/* 136 */       query.append(" where coti.estado = :estado ");
/* 137 */       query.append(" order by coti.nombre ASC ");
/*     */       
/* 139 */       Query consulta = this.em.createQuery(query.toString());
/* 140 */       consulta.setParameter("estado", "HAB");
/*     */       
/* 142 */       lista = consulta.getResultList();
/*     */     } catch (Exception e) {
/* 144 */       e.printStackTrace();
/* 145 */       this.context.setRollbackOnly();
/* 146 */       throw new AdminException(e);
/*     */     }
/* 148 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarPais(Pais pais) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 156 */       if ((pais.getId() != null) && (pais.getId().intValue() > 0)) {
/* 157 */         pais.setEstado("DES");
/* 158 */         this.em.merge(pais);
/*     */       }
/*     */     } catch (Exception e) {
/* 161 */       e.printStackTrace();
/* 162 */       this.context.setRollbackOnly();
/* 163 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\ML\TipoCambioEjbBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */