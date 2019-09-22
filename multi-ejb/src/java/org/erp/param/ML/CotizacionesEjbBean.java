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
/*     */ import org.erp.entities.CotizacionMineral;
/*     */ import org.erp.entities.CotizacionMineralDesc;
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
/*     */ public class CotizacionesEjbBean
/*     */   extends CrudDao
/*     */   implements CotizacionesEjbBeanLocal
/*     */ {
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public List<CotizacionMineral> listaCotizaciones()
/*     */     throws AdminException
/*     */   {
/*  44 */     List<CotizacionMineral> lista = new ArrayList();
/*     */     try {
/*  46 */       StringBuilder query = new StringBuilder();
/*     */       
/*  48 */       query.append(" select coti ");
/*  49 */       query.append(" from CotizacionMineral coti ");
/*  50 */       query.append(" where coti.estado = :estado ");
/*  51 */       query.append(" order by coti.nombre ASC ");
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
/*     */ 
/*     */   public List<CotizacionMineralDesc> listaDescripcion(Integer idCotizacion)
/*     */     throws AdminException
/*     */   {
/*  69 */     List<CotizacionMineralDesc> lista = new ArrayList();
/*     */     try {
/*  71 */       StringBuilder query = new StringBuilder();
/*     */       
/*  73 */       query.append(" select par ");
/*  74 */       query.append(" from CotizacionMineralDesc par ");
/*  75 */       query.append(" where par.idCotizacionMineral.id = :idCotizacion ");
/*  76 */       query.append(" and par.estado = :estado ");
/*  77 */       query.append(" order by par.nombre ASC ");
/*     */       
/*  79 */       Query consulta = this.em.createQuery(query.toString());
/*  80 */       consulta.setParameter("estado", "HAB");
/*  81 */       consulta.setParameter("idCotizacion", idCotizacion);
/*     */       
/*  83 */       lista = consulta.getResultList();
/*     */     } catch (Exception e) {
/*  85 */       e.printStackTrace();
/*  86 */       this.context.setRollbackOnly();
/*  87 */       throw new AdminException(e);
/*     */     }
/*  89 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarCotizacionMineral(CotizacionMineral cotizacionMineral)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/*  98 */       if ((cotizacionMineral.getId() != null) && (cotizacionMineral.getId().intValue() > 0)) {
/*  99 */         cotizacionMineral.setFechaMod(new Date());
/* 100 */         this.em.merge(cotizacionMineral);
/*     */       } else {
/* 102 */         cotizacionMineral.setFechaReg(new Date());
/* 103 */         cotizacionMineral.setEstado("HAB");
/* 104 */         this.em.persist(cotizacionMineral);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 108 */       e.printStackTrace();
/* 109 */       this.context.setRollbackOnly();
/* 110 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarCotizacionDescripcion(CotizacionMineralDesc cotizacionMineralDesc)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 120 */       if ((cotizacionMineralDesc.getId() != null) && (cotizacionMineralDesc.getId().intValue() > 0)) {
/* 121 */         cotizacionMineralDesc.setEstado("DES");
/* 122 */         cotizacionMineralDesc.setFechaMod(new Date());
/* 123 */         this.em.merge(cotizacionMineralDesc);
/*     */       }
/*     */     } catch (Exception e) {
/* 126 */       e.printStackTrace();
/* 127 */       this.context.setRollbackOnly();
/* 128 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarCotizacion(CotizacionMineral cotizacionMineral) throws AdminException
/*     */   {
/*     */     try {
/* 136 */       if ((cotizacionMineral.getId() != null) && (cotizacionMineral.getId().intValue() > 0)) {
/* 137 */         cotizacionMineral.setFechaMod(new Date());
/* 138 */         cotizacionMineral.setEstado("DES");
/* 139 */         this.em.merge(cotizacionMineral);
/*     */       }
/*     */     } catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       this.context.setRollbackOnly();
/* 144 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarCotizacionDescripcion(CotizacionMineralDesc cotizacionMineralDesc)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 154 */       if ((cotizacionMineralDesc.getId() != null) && (cotizacionMineralDesc.getId().intValue() > 0)) {
/* 155 */         cotizacionMineralDesc.setFechaMod(new Date());
/* 156 */         this.em.merge(cotizacionMineralDesc);
/*     */       } else {
/* 158 */         cotizacionMineralDesc.setFechaReg(new Date());
/* 159 */         cotizacionMineralDesc.setEstado("HAB");
/* 160 */         this.em.persist(cotizacionMineralDesc);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 164 */       e.printStackTrace();
/* 165 */       this.context.setRollbackOnly();
/* 166 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\ML\CotizacionesEjbBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */