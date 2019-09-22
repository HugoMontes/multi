/*     */ package org.erp.form;
/*     */ 
/*     */ import java.math.BigDecimal;
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
/*     */ import org.erp.entities.Liquidacion;
/*     */ import org.erp.entities.Regalia;
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
/*     */ 
/*     */ 
/*     */ @Stateless
/*     */ @LocalBean
/*     */ public class LiquidacionEjbBean
/*     */   extends CrudDao
/*     */   implements LiquidacionEjbBeanLocal
/*     */ {
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public List<Regalia> listadoRegalia(Integer idCooperativa, Integer indTipoFormulario)
/*     */     throws AdminException
/*     */   {
/*  50 */     List<Regalia> lista = new ArrayList();
/*     */     try {
/*  52 */       StringBuilder query = new StringBuilder();
/*  53 */       query.append(" select reg ");
/*  54 */       query.append(" from Regalia reg ");
/*  55 */       query.append(" where reg.estado = :estado ");
/*     */       
/*  57 */       if (idCooperativa != null) {
/*  58 */         query.append(" and reg.idCooperativa.id = :idCooperativa ");
/*     */       }
/*  60 */       if (idCooperativa == null) {
/*  61 */         query.append(" and reg.indEstadoFormulario = :indEstadoFormulario ");
/*     */       }
/*  63 */       query.append(" and reg.indTipoFormulario = :indTipoFormulario ");
/*  64 */       query.append(" order by reg.id DESC ");
/*     */       
/*     */ 
/*     */ 
/*  68 */       Query consulta = this.em.createQuery(query.toString());
/*  69 */       consulta.setParameter("estado", "HAB");
/*  70 */       consulta.setParameter("indTipoFormulario", indTipoFormulario);
/*     */       
/*  72 */       if (idCooperativa != null) {
/*  73 */         consulta.setParameter("idCooperativa", idCooperativa);
/*     */       }
/*  75 */       if (idCooperativa == null) {
/*  76 */         consulta.setParameter("indEstadoFormulario", new Integer(130));
/*     */       }
/*     */       
/*  79 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*     */     }
/*  84 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> listadoliquidacionByRegalia(Integer idRegalia)
/*     */     throws AdminException
/*     */   {
/*  90 */     List<Liquidacion> lista = new ArrayList();
/*     */     try {
/*  92 */       StringBuilder query = new StringBuilder();
/*  93 */       query.append(" select liq ");
/*  94 */       query.append(" from Liquidacion liq ");
/*  95 */       query.append(" where liq.estado = :estado ");
/*  96 */       query.append(" and liq.idRegalia.id = :idRegalia ");
/*  97 */       query.append(" order by liq.id ASC ");
/*     */       
/*     */ 
/* 100 */       Query consulta = this.em.createQuery(query.toString());
/* 101 */       consulta.setParameter("estado", "HAB");
/* 102 */       consulta.setParameter("idRegalia", idRegalia);
/*     */       
/* 104 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 107 */       e.printStackTrace();
/*     */     }
/* 109 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarLiquidacionExportacion(Regalia regalia, Liquidacion liquidacion)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 118 */       if ((regalia.getId() != null) && (regalia.getId().intValue() > 0)) {
/* 119 */         regalia.setFechaMod(new Date());
/* 120 */         this.em.merge(regalia);
/*     */       } else {
/* 122 */         regalia.setFechaReg(new Date());
/* 123 */         regalia.setEstado("HAB");
/* 124 */         this.em.persist(regalia);
/*     */       }
/*     */       
/*     */ 
/* 128 */       if ((liquidacion.getId() != null) && (liquidacion.getId().intValue() > 0)) {
/* 129 */         liquidacion.setFechaMod(new Date());
/* 130 */         this.em.merge(liquidacion);
/*     */       } else {
/* 132 */         liquidacion.setFechaReg(new Date());
/* 133 */         liquidacion.setEstado("HAB");
/* 134 */         this.em.persist(liquidacion);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 138 */       e.printStackTrace();
/* 139 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarRegalia(Regalia regalia)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 149 */       if ((regalia.getId() != null) && (regalia.getId().intValue() > 0)) {
/* 150 */         regalia.setFechaMod(new Date());
/* 151 */         this.em.merge(regalia);
/*     */       } else {
/* 153 */         regalia.setFechaReg(new Date());
/* 154 */         regalia.setEstado("HAB");
/* 155 */         this.em.persist(regalia);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public List<CotizacionMineral> listCotizacionMineral()
/*     */     throws AdminException
/*     */   {
/* 168 */     List<CotizacionMineral> lista = new ArrayList();
/*     */     try {
/* 170 */       StringBuilder query = new StringBuilder();
/* 171 */       query.append(" select cot ");
/* 172 */       query.append(" from CotizacionMineral cot ");
/* 173 */       query.append(" where cot.estado = :estado ");
/*     */       
/* 175 */       Query consulta = this.em.createQuery(query.toString());
/* 176 */       consulta.setParameter("estado", "HAB");
/*     */       
/* 178 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 181 */       e.printStackTrace();
/*     */     }
/* 183 */     return lista;
/*     */   }
/*     */   
/*     */   public List<CotizacionMineralDesc> listCotizacionMineralDesc(Integer idCotizacionMineral)
/*     */     throws AdminException
/*     */   {
/* 189 */     List<CotizacionMineralDesc> lista = new ArrayList();
/*     */     try {
/* 191 */       StringBuilder query = new StringBuilder();
/* 192 */       query.append(" select cotDesc ");
/* 193 */       query.append(" from CotizacionMineralDesc cotDesc ");
/* 194 */       query.append(" where cotDesc.estado = :estado ");
/* 195 */       query.append(" and cotDesc.idCotizacionMineral.id = :idCotizacionMineral ");
/*     */       
/* 197 */       Query consulta = this.em.createQuery(query.toString());
/* 198 */       consulta.setParameter("estado", "HAB");
/* 199 */       consulta.setParameter("idCotizacionMineral", idCotizacionMineral);
/*     */       
/* 201 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 204 */       e.printStackTrace();
/*     */     }
/* 206 */     return lista;
/*     */   }
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
/*     */   public TipoCambio obtenerTipoCambio(Date fecha, Integer indTipoMoneda)
/*     */     throws AdminException
/*     */   {
/* 223 */     TipoCambio tipoCambio = new TipoCambio();
/*     */     try
/*     */     {
/* 226 */       StringBuilder query = new StringBuilder();
/* 227 */       query.append(" select tc ");
/* 228 */       query.append(" from TipoCambio tc ");
/* 229 */       query.append(" where tc.estado = :estado ");
/* 230 */       query.append(" and tc.indTipoMoneda = :indTipoMoneda ");
/*     */       
/*     */ 
/* 233 */       query.append(" and tc.fecha = :fecha ");
/*     */       
/* 235 */       Query consulta = this.em.createQuery(query.toString());
/* 236 */       consulta.setParameter("estado", "HAB");
/* 237 */       consulta.setParameter("fecha", fecha);
/* 238 */       consulta.setParameter("indTipoMoneda", indTipoMoneda);
/*     */       try
/*     */       {
/* 241 */         tipoCambio = (TipoCambio)consulta.getSingleResult();
/*     */       } catch (Exception e) {
/* 243 */         tipoCambio = null;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 250 */       
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 248 */       e.printStackTrace();
/*     */     }
              return tipoCambio;
/*     */   }
/*     */   
/*     */ 
/*     */   public BigDecimal obtenerTipoCambioEjemplo(Integer indTipoMoneda)
/*     */     throws AdminException
/*     */   {
/* 256 */     BigDecimal tipoCambio = null;
/*     */     try {
/* 258 */       StringBuilder query = new StringBuilder();
/* 259 */       query.append(" select tc.cotizacionOficial ");
/* 260 */       query.append(" from TipoCambio tc ");
/* 261 */       query.append(" where tc.estado = :estado ");
/* 262 */       query.append(" and tc.indTipoMoneda = :indTipoMoneda ");
/*     */       
/* 264 */       Query consulta = this.em.createQuery(query.toString());
/* 265 */       consulta.setParameter("estado", "HAB");
/* 266 */       consulta.setParameter("indTipoMoneda", indTipoMoneda);
/*     */       try
/*     */       {
/* 269 */         tipoCambio = (BigDecimal)consulta.getSingleResult();
/*     */       } catch (Exception e) {
/* 271 */         tipoCambio = null;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 278 */      
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 276 */       e.printStackTrace();
/*     */     }
              return tipoCambio;
/*     */   }
/*     */   
/*     */ 
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarMineral(Liquidacion liquidacion)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 287 */       if ((liquidacion.getId() != null) && (liquidacion.getId().intValue() > 0)) {
/* 288 */         liquidacion.setEstado("DES");
/* 289 */         this.em.merge(liquidacion);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 293 */       e.printStackTrace();
/* 294 */       this.context.setRollbackOnly();
/* 295 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Regalia obtenerRegaliaByTramite(String numeroTramite)
/*     */     throws AdminException
/*     */   {
/* 304 */     Regalia regalia = new Regalia();
/*     */     try
/*     */     {
/* 307 */       StringBuilder query = new StringBuilder();
/* 308 */       query.append(" select reg ");
/* 309 */       query.append(" from Regalia reg ");
/* 310 */       query.append(" where reg.estado = :estado ");
/* 311 */       query.append(" and reg.numeroOrden = :numeroOrden ");
/*     */       
/* 313 */       Query consulta = this.em.createQuery(query.toString());
/* 314 */       consulta.setParameter("estado", "HAB");
/* 315 */       consulta.setParameter("numeroOrden", numeroTramite);
/*     */       try
/*     */       {
/* 318 */         regalia = (Regalia)consulta.getSingleResult();
/*     */       } catch (Exception e) {
/* 320 */         regalia = new Regalia();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 327 */       
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 325 */       e.printStackTrace();
/*     */     }
              return regalia;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\form\LiquidacionEjbBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */