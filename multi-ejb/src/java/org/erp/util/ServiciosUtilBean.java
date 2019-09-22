/*     */ package org.erp.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.ejb.SessionContext;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ejb.TransactionAttribute;
/*     */ import javax.ejb.TransactionAttributeType;
/*     */ import javax.ejb.TransactionManagement;
/*     */ import javax.ejb.TransactionManagementType;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.Query;
/*     */ import org.erp.entities.GenKey;
/*     */ import org.erp.entities.ParameterTable;
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
/*     */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*     */ public class ServiciosUtilBean
/*     */   extends CrudDao
/*     */   implements ServiciosUtilBeanLocal
/*     */ {
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public List<ParameterTable> listaParametricas(Integer idMaster)
/*     */     throws AdminException
/*     */   {
/*  48 */     List<ParameterTable> lista = new ArrayList();
/*     */     try {
/*  50 */       StringBuilder query = new StringBuilder();
/*     */       
/*  52 */       query.append(" select param ");
/*  53 */       query.append(" from ParameterTable param ");
/*  54 */       query.append(" where param.estado = :estado ");
/*  55 */       query.append(" and param.idMaster.id = :idMaster ");
/*  56 */       query.append(" order by param.orden asc ");
/*     */       
/*  58 */       Query consulta = this.em.createQuery(query.toString());
/*  59 */       consulta.setParameter("estado", "HAB");
/*  60 */       consulta.setParameter("idMaster", idMaster);
/*     */       
/*  62 */       lista = consulta.getResultList();
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/*  69 */       e.printStackTrace();
/*  70 */       this.context.setRollbackOnly();
/*  71 */       throw new AdminException(e);
/*     */     }
/*     */     
/*  74 */     return lista;
/*     */   }
/*     */   
/*     */   public ParameterTable ParameterById(Integer idParameter)
/*     */     throws AdminException
/*     */   {
/*  80 */     ParameterTable parameterTable = new ParameterTable();
/*     */     try {
/*  82 */       StringBuilder query = new StringBuilder();
/*     */       
/*  84 */       query.append(" select param ");
/*  85 */       query.append(" from ParameterTable param ");
/*  86 */       query.append(" where param.estado = :estado ");
/*  87 */       query.append(" and param.id = :idParameter ");
/*     */       
/*  89 */       Query consulta = this.em.createQuery(query.toString());
/*  90 */       consulta.setParameter("estado", "HAB");
/*  91 */       consulta.setParameter("idParameter", idParameter);
/*     */       
/*  93 */       parameterTable = (ParameterTable)consulta.getSingleResult();
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/* 100 */       e.printStackTrace();
/* 101 */       this.context.setRollbackOnly();
/* 102 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 105 */     return parameterTable;
/*     */   }
/*     */   
/*     */ 
/*     */   public GenKey licenceKey()
/*     */     throws AdminException
/*     */   {
/* 112 */     GenKey genKey = new GenKey();
/*     */     try {
/* 114 */       StringBuilder query = new StringBuilder();
/*     */       
/* 116 */       query.append(" select gen ");
/* 117 */       query.append(" from GenKey gen ");
/* 118 */       query.append(" where gen.estado = :estado ");
/*     */       
/* 120 */       Query consulta = this.em.createQuery(query.toString());
/* 121 */       consulta.setParameter("estado", "HAB");
/*     */       try
/*     */       {
/* 124 */         genKey = (GenKey)consulta.getSingleResult();
/*     */       } catch (Exception e) {
/* 126 */         genKey = new GenKey();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */       return genKey;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 131 */       e.printStackTrace();
/* 132 */       this.context.setRollbackOnly();
/* 133 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarDatosLicencia(GenKey genKey)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 145 */       if ((genKey.getId() != null) && (genKey.getId().intValue() > 0)) {
/* 146 */         genKey.setFechaMod(new Date());
/* 147 */         this.em.merge(genKey);
/*     */       } else {
/* 149 */         genKey.setFechaReg(new Date());
/* 150 */         genKey.setEstado("HAB");
/* 151 */         this.em.persist(genKey);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 155 */       e.printStackTrace();
/* 156 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */ }