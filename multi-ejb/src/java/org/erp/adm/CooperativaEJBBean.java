/*     */ package org.erp.adm;
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
/*     */ import org.erp.entities.Actividad;
/*     */ import org.erp.entities.Ciudad;
/*     */ import org.erp.entities.ContratoMinero;
/*     */ import org.erp.entities.Cooperativa;
/*     */ import org.erp.entities.Departamento;
/*     */ import org.erp.entities.DerechoMinero;
/*     */ import org.erp.entities.Mineral;
/*     */ import org.erp.entities.Municipio;
/*     */ import org.erp.entities.Pais;
/*     */ import org.erp.entities.Sucursal;
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
/*     */ public class CooperativaEJBBean
/*     */   extends CrudDao
/*     */   implements CooperativaEJBBeanLocal
/*     */ {
/*     */   public List<Cooperativa> listadoCooperativa()
/*     */     throws AdminException
/*     */   {
/*  50 */     List<Cooperativa> lista = new ArrayList();
/*     */     try {
/*  52 */       StringBuilder query = new StringBuilder();
/*  53 */       query.append(" select cop ");
/*  54 */       query.append(" from Cooperativa cop ");
/*  55 */       query.append(" where cop.estado = :estado ");
/*     */       
/*     */ 
/*  58 */       Query consulta = this.em.createQuery(query.toString());
/*  59 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  61 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/*  64 */       e.printStackTrace();
/*     */     }
/*  66 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Sucursal> listadoSucursal(Integer idCooperativa)
/*     */     throws AdminException
/*     */   {
/*  72 */     List<Sucursal> lista = new ArrayList();
/*     */     try {
/*  74 */       StringBuilder query = new StringBuilder();
/*  75 */       query.append(" select suc ");
/*  76 */       query.append(" from Sucursal suc ");
/*  77 */       query.append(" where suc.estado = :estado ");
/*  78 */       query.append(" and suc.idCooperativa.id = :idCooperativa ");
/*     */       
/*  80 */       Query consulta = this.em.createQuery(query.toString());
/*  81 */       consulta.setParameter("estado", "HAB");
/*  82 */       consulta.setParameter("idCooperativa", idCooperativa);
/*     */       
/*  84 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/*  87 */       e.printStackTrace();
/*     */     }
/*  89 */     return lista;
/*     */   }
/*     */   
/*     */   public List<DerechoMinero> listadoDerechoMinero(Integer idCooperativa)
/*     */     throws AdminException
/*     */   {
/*  95 */     List<DerechoMinero> lista = new ArrayList();
/*     */     try {
/*  97 */       StringBuilder query = new StringBuilder();
/*  98 */       query.append(" select dm ");
/*  99 */       query.append(" from DerechoMinero dm ");
/* 100 */       query.append(" where dm.estado = :estado ");
/* 101 */       query.append(" and dm.idCooperativa.id = :idCooperativa ");
/*     */       
/* 103 */       Query consulta = this.em.createQuery(query.toString());
/* 104 */       consulta.setParameter("estado", "HAB");
/* 105 */       consulta.setParameter("idCooperativa", idCooperativa);
/*     */       
/* 107 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 110 */       e.printStackTrace();
/*     */     }
/* 112 */     return lista;
/*     */   }
/*     */   
/*     */   public List<ContratoMinero> listadoContratoMinero(Integer idCooperativa)
/*     */     throws AdminException
/*     */   {
/* 118 */     List<ContratoMinero> lista = new ArrayList();
/*     */     try {
/* 120 */       StringBuilder query = new StringBuilder();
/* 121 */       query.append(" select cm ");
/* 122 */       query.append(" from ContratoMinero cm ");
/* 123 */       query.append(" where cm.estado = :estado ");
/* 124 */       query.append(" and cm.idCooperativa.id = :idCooperativa ");
/*     */       
/* 126 */       Query consulta = this.em.createQuery(query.toString());
/* 127 */       consulta.setParameter("estado", "HAB");
/* 128 */       consulta.setParameter("idCooperativa", idCooperativa);
/*     */       
/* 130 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 133 */       e.printStackTrace();
/*     */     }
/* 135 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Actividad> listadoActividadesRegistradas(Integer idCooperativa)
/*     */     throws AdminException
/*     */   {
/* 141 */     List<Actividad> lista = new ArrayList();
/*     */     try {
/* 143 */       StringBuilder query = new StringBuilder();
/* 144 */       query.append(" select act ");
/* 145 */       query.append(" from Actividad act ");
/* 146 */       query.append(" where act.estado = :estado ");
/* 147 */       query.append(" and act.idCooperativa.id = :idCooperativa ");
/*     */       
/* 149 */       Query consulta = this.em.createQuery(query.toString());
/* 150 */       consulta.setParameter("estado", "HAB");
/* 151 */       consulta.setParameter("idCooperativa", idCooperativa);
/*     */       
/* 153 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 156 */       e.printStackTrace();
/*     */     }
/* 158 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Mineral> listadoMinerales(Integer idCooperativa, Integer tipoMineral)
/*     */     throws AdminException
/*     */   {
/* 164 */     List<Mineral> lista = new ArrayList();
/*     */     try {
/* 166 */       StringBuilder query = new StringBuilder();
/* 167 */       query.append(" select mine ");
/* 168 */       query.append(" from Mineral mine ");
/* 169 */       query.append(" where mine.estado = :estado ");
/* 170 */       query.append(" and mine.idCooperativa.id = :idCooperativa ");
/* 171 */       query.append(" and mine.indMetalNoMetal = :tipoMineral ");
/*     */       
/* 173 */       Query consulta = this.em.createQuery(query.toString());
/* 174 */       consulta.setParameter("estado", "HAB");
/* 175 */       consulta.setParameter("idCooperativa", idCooperativa);
/* 176 */       consulta.setParameter("tipoMineral", tipoMineral);
/*     */       
/* 178 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 181 */       e.printStackTrace();
/*     */     }
/* 183 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Departamento> listadoDepartamento()
/*     */     throws AdminException
/*     */   {
/* 189 */     List<Departamento> lista = new ArrayList();
/*     */     try {
/* 191 */       StringBuilder query = new StringBuilder();
/* 192 */       query.append(" select dep ");
/* 193 */       query.append(" from Departamento dep ");
/*     */       
/*     */ 
/*     */ 
/* 197 */       Query consulta = this.em.createQuery(query.toString());
/*     */       
/*     */ 
/* 200 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 203 */       e.printStackTrace();
/*     */     }
/* 205 */     return lista;
/*     */   }
/*     */   
/*     */   public Departamento departamentoById(Integer idDepartamento) throws AdminException
/*     */   {
/* 210 */     Departamento departamento = new Departamento();
/*     */     try {
/* 212 */       StringBuilder query = new StringBuilder();
/* 213 */       query.append(" select dep ");
/* 214 */       query.append(" from Departamento dep ");
/* 215 */       query.append(" where dep.id = :idDepartamento ");
/*     */       
/* 217 */       Query consulta = this.em.createQuery(query.toString());
/* 218 */       consulta.setParameter("idDepartamento", idDepartamento);
/*     */       
/* 220 */       departamento = (Departamento)consulta.getSingleResult();
/*     */     }
/*     */     catch (Exception e) {
/* 223 */       e.printStackTrace();
/*     */     }
/* 225 */     return departamento;
/*     */   }
/*     */   
/*     */   public List<Pais> listadoPais()
/*     */     throws AdminException
/*     */   {
/* 231 */     List<Pais> lista = new ArrayList();
/*     */     try {
/* 233 */       StringBuilder query = new StringBuilder();
/* 234 */       query.append(" select p ");
/* 235 */       query.append(" from Pais p ");
/* 236 */       query.append(" where p.estado = :estado ");
/* 237 */       query.append(" order by p.id ASC ");
/*     */       
/* 239 */       Query consulta = this.em.createQuery(query.toString());
/* 240 */       consulta.setParameter("estado", "HAB");
/*     */       
/* 242 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/*     */     }
/* 247 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Municipio> listadoMunicipio(Integer idDepartamento)
/*     */     throws AdminException
/*     */   {
/* 253 */     List<Municipio> lista = new ArrayList();
/*     */     try {
/* 255 */       StringBuilder query = new StringBuilder();
/* 256 */       query.append(" select mun ");
/* 257 */       query.append(" from Municipio mun ");
/* 258 */       query.append(" where mun.idDepartamento.id = :idDepartamento ");
/*     */       
/* 260 */       Query consulta = this.em.createQuery(query.toString());
/*     */       
/* 262 */       consulta.setParameter("idDepartamento", idDepartamento);
/*     */       
/* 264 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 267 */       e.printStackTrace();
/*     */     }
/* 269 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Ciudad> listadoCiudad(Integer idMunicipio)
/*     */     throws AdminException
/*     */   {
/* 275 */     List<Ciudad> lista = new ArrayList();
/*     */     try {
/* 277 */       StringBuilder query = new StringBuilder();
/* 278 */       query.append(" select ciu ");
/* 279 */       query.append(" from Ciudad ciu ");
/* 280 */       query.append(" where ciu.idMunicipio.id = :idMunicipio ");
/*     */       
/* 282 */       Query consulta = this.em.createQuery(query.toString());
/*     */       
/* 284 */       consulta.setParameter("idMunicipio", idMunicipio);
/*     */       
/* 286 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 289 */       e.printStackTrace();
/*     */     }
/* 291 */     return lista;
/*     */   }
/*     */   
/*     */   public List<Ciudad> listadoCiudadByDpto(Integer idDepartamento)
/*     */     throws AdminException
/*     */   {
/* 297 */     List<Ciudad> lista = new ArrayList();
/*     */     try {
/* 299 */       StringBuilder query = new StringBuilder();
/* 300 */       query.append(" select ciu ");
/* 301 */       query.append(" from Ciudad ciu ");
/* 302 */       query.append(" where ciu.idMunicipio.idDepartamento.id = :idDepartamento ");
/*     */       
/* 304 */       Query consulta = this.em.createQuery(query.toString());
/*     */       
/* 306 */       consulta.setParameter("idDepartamento", idDepartamento);
/*     */       
/* 308 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 311 */       e.printStackTrace();
/*     */     }
/* 313 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarCooperativa(Cooperativa cooperativa) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 321 */       if ((cooperativa.getId() != null) && (cooperativa.getId().intValue() > 0)) {
/* 322 */         cooperativa.setFechaMod(new Date());
/* 323 */         this.em.merge(cooperativa);
/*     */       } else {
/* 325 */         cooperativa.setFechaReg(new Date());
/* 326 */         cooperativa.setEstado("HAB");
/* 327 */         this.em.persist(cooperativa);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 331 */       e.printStackTrace();
/*     */       
/* 333 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarSucursal(Sucursal sucursal) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 342 */       if ((sucursal.getId() != null) && (sucursal.getId().intValue() > 0)) {
/* 343 */         sucursal.setFechaMod(new Date());
/* 344 */         this.em.merge(sucursal);
/*     */       } else {
/* 346 */         sucursal.setFechaReg(new Date());
/* 347 */         sucursal.setEstado("HAB");
/* 348 */         this.em.persist(sucursal);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 352 */       e.printStackTrace();
/* 353 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarSucursal(Sucursal sucursal) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 362 */       if ((sucursal.getId() != null) && (sucursal.getId().intValue() > 0)) {
/* 363 */         sucursal.setFechaReg(new Date());
/* 364 */         sucursal.setEstado("DES");
/* 365 */         this.em.merge(sucursal);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 369 */       e.printStackTrace();
/* 370 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarDerechoMinero(DerechoMinero derechoMinero) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 379 */       if (derechoMinero.getIdMunicipio().getId() == null) {
/* 380 */         derechoMinero.setIdMunicipio(null);
/*     */       }
/*     */       
/* 383 */       if ((derechoMinero.getId() != null) && (derechoMinero.getId().intValue() > 0)) {
/* 384 */         derechoMinero.setFechaMod(new Date());
/* 385 */         this.em.merge(derechoMinero);
/*     */       } else {
/* 387 */         derechoMinero.setFechaReg(new Date());
/* 388 */         derechoMinero.setEstado("HAB");
/* 389 */         this.em.persist(derechoMinero);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 393 */       e.printStackTrace();
/* 394 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarDerechoMinero(DerechoMinero derechoMinero) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 403 */       if ((derechoMinero.getId() != null) && (derechoMinero.getId().intValue() > 0)) {
/* 404 */         derechoMinero.setFechaReg(new Date());
/* 405 */         derechoMinero.setEstado("DES");
/* 406 */         this.em.merge(derechoMinero);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 410 */       e.printStackTrace();
/* 411 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarContratoMinero(ContratoMinero contratoMinero)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 421 */       if ((contratoMinero.getId() != null) && (contratoMinero.getId().intValue() > 0)) {
/* 422 */         contratoMinero.setFechaMod(new Date());
/* 423 */         this.em.merge(contratoMinero);
/*     */       } else {
/* 425 */         contratoMinero.setFechaReg(new Date());
/* 426 */         contratoMinero.setEstado("HAB");
/* 427 */         this.em.persist(contratoMinero);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 431 */       e.printStackTrace();
/* 432 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void eliminarContratoMinero(ContratoMinero contratoMinero)
/*     */     throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 442 */       if ((contratoMinero.getId() != null) && (contratoMinero.getId().intValue() > 0)) {
/* 443 */         contratoMinero.setFechaReg(new Date());
/* 444 */         contratoMinero.setEstado("DES");
/* 445 */         this.em.merge(contratoMinero);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 449 */       e.printStackTrace();
/* 450 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarEliminarActividades(Integer idCooperativa) throws AdminException
/*     */   {
/*     */     try {
/* 458 */       StringBuilder query = new StringBuilder();
/* 459 */       query.append(" delete  ");
/* 460 */       query.append(" from Actividad act ");
/* 461 */       query.append(" where act.idCooperativa.id = :idCooperativa ");
/*     */       
/* 463 */       Query consulta = this.em.createQuery(query.toString());
/* 464 */       consulta.setParameter("idCooperativa", idCooperativa);
/*     */       
/* 466 */       consulta.executeUpdate();
/*     */     }
/*     */     catch (Exception e) {
/* 469 */       e.printStackTrace();
/* 470 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarEliminarMinerales(Integer idCooperativa) throws AdminException
/*     */   {
/*     */     try {
/* 478 */       StringBuilder query = new StringBuilder();
/* 479 */       query.append(" delete  ");
/* 480 */       query.append(" from Mineral mine ");
/* 481 */       query.append(" where mine.idCooperativa.id = :idCooperativa ");
/*     */       
/* 483 */       Query consulta = this.em.createQuery(query.toString());
/* 484 */       consulta.setParameter("idCooperativa", idCooperativa);
/*     */       
/* 486 */       consulta.executeUpdate();
/*     */     }
/*     */     catch (Exception e) {
/* 489 */       e.printStackTrace();
/* 490 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarActividadCooperativa(Actividad actividad) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 499 */       if ((actividad.getId() != null) && (actividad.getId().intValue() > 0)) {
/* 500 */         actividad.setFechaMod(new Date());
/* 501 */         this.em.merge(actividad);
/*     */       } else {
/* 503 */         actividad.setFechaReg(new Date());
/* 504 */         actividad.setEstado("HAB");
/* 505 */         this.em.persist(actividad);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 509 */       e.printStackTrace();
/* 510 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarMinerales(Mineral mineral) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 519 */       if ((mineral.getId() != null) && (mineral.getId().intValue() > 0)) {
/* 520 */         mineral.setFechaMod(new Date());
/* 521 */         this.em.merge(mineral);
/*     */       } else {
/* 523 */         mineral.setFechaReg(new Date());
/* 524 */         mineral.setEstado("HAB");
/* 525 */         this.em.persist(mineral);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 529 */       e.printStackTrace();
/* 530 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Municipio> listadoMunicipioDerMin()
/*     */     throws AdminException
/*     */   {
/* 537 */     List<Municipio> lista = new ArrayList();
/*     */     try {
/* 539 */       StringBuilder query = new StringBuilder();
/* 540 */       query.append(" select mun ");
/* 541 */       query.append(" from Municipio mun ");
/*     */       
/*     */ 
/* 544 */       Query consulta = this.em.createQuery(query.toString());
/*     */       
/*     */ 
/* 547 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 550 */       e.printStackTrace();
/*     */     }
/* 552 */     return lista;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\adm\CooperativaEJBBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */