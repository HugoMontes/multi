/*     */ package org.erp.seg.QL.seg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.ejb.SessionContext;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ejb.TransactionManagement;
/*     */ import javax.ejb.TransactionManagementType;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.PersistenceContext;
/*     */ import javax.persistence.Query;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Stateless
/*     */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*     */ public class SegUsuarioQLBean
/*     */   implements SegUsuarioQLBeanLocal
/*     */ {
/*     */   @PersistenceContext(unitName="admindbDS")
/*     */   EntityManager em;
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public List<SegUsuario> listaUsuarios()
/*     */     throws AdminException
/*     */   {
/*  52 */     List<SegUsuario> lista = new ArrayList();
/*     */     try {
/*  54 */       StringBuilder query = new StringBuilder();
/*     */       
/*  56 */       query.append(" select segUser ");
/*  57 */       query.append(" from SegUsuario segUser ");
/*     */       
/*  59 */       query.append(" where segUser.estado = :estado ");
/*  60 */       query.append(" order by segUser.id ");
/*     */       
/*  62 */       Query consulta = this.em.createQuery(query.toString());
/*  63 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  65 */       lista = consulta.getResultList();
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/*     */ 
/*  73 */       e.printStackTrace();
/*  74 */       this.context.setRollbackOnly();
/*  75 */       throw new AdminException(e);
/*     */     }
/*     */     
/*  78 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegRol> listarRoles()
/*     */     throws AdminException
/*     */   {
/*  86 */     List<SegRol> lista = new ArrayList();
/*     */     try {
/*  88 */       StringBuilder query = new StringBuilder();
/*     */       
/*  90 */       query.append(" select segRol ");
/*  91 */       query.append(" from SegRol segRol ");
/*  92 */       query.append(" where segRol.estado = :estado");
/*     */       
/*  94 */       Query consulta = this.em.createQuery(query.toString());
/*  95 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  97 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 101 */       e.printStackTrace();
/* 102 */       this.context.setRollbackOnly();
/* 103 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 106 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegPermiso> listarPermisos(Integer idGenPersonal)
/*     */     throws AdminException
/*     */   {
/* 114 */     List<SegPermiso> lista = new ArrayList();
/*     */     try {
/* 116 */       StringBuilder query = new StringBuilder();
/*     */       
/* 118 */       query.append(" select segPer,user,rol ");
/* 119 */       query.append(" from SegPermiso segPer ");
/* 120 */       query.append(" join segPer.idUsuario user ");
/* 121 */       query.append(" join segPer.idRol rol ");
/* 122 */       query.append(" where segPer.estado = :estado ");
/* 123 */       query.append(" and user.estado = :estado ");
/* 124 */       query.append(" and rol.estado = :estado ");
/* 125 */       query.append(" and user.id = :idUsuario ");
/*     */       
/* 127 */       Query consulta = this.em.createQuery(query.toString());
/* 128 */       consulta.setParameter("estado", "HAB");
/* 129 */       consulta.setParameter("idUsuario", idGenPersonal);
/*     */       
/* 131 */       List<Object[]> listaObjetos = consulta.getResultList();
/*     */       
/* 133 */       for (Object[] obj : listaObjetos) {
/* 134 */         lista.add((SegPermiso)obj[0]);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 140 */       e.printStackTrace();
/* 141 */       this.context.setRollbackOnly();
/* 142 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 145 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegUsuario> verificarSiUsuario(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/* 153 */     List<SegUsuario> lista = new ArrayList();
/*     */     try {
/* 155 */       StringBuilder query = new StringBuilder();
/*     */       
/* 157 */       query.append(" select segUser ");
/* 158 */       query.append(" from SegUsuario segUser ");
/* 159 */       query.append(" where segUser.estado = :estado");
/* 160 */       query.append(" and segUser.usuario = :usuario");
/*     */       
/* 162 */       Query consulta = this.em.createQuery(query.toString());
/* 163 */       consulta.setParameter("estado", "HAB");
/* 164 */       consulta.setParameter("usuario", segUsuario.getUsuario());
/*     */       
/* 166 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 170 */       e.printStackTrace();
/* 171 */       this.context.setRollbackOnly();
/* 172 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 175 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegUsuario> verificarSiExisteSigla(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/* 183 */     List<SegUsuario> lista = new ArrayList();
/*     */     try {
/* 185 */       StringBuilder query = new StringBuilder();
/*     */       
/* 187 */       query.append(" select segUser ");
/* 188 */       query.append(" from SegUsuario segUser ");
/* 189 */       query.append(" where segUser.estado = :estado");
/* 190 */       query.append(" and segUser.sigla = :sigla");
/*     */       
/* 192 */       Query consulta = this.em.createQuery(query.toString());
/* 193 */       consulta.setParameter("estado", "HAB");
/* 194 */       consulta.setParameter("sigla", segUsuario.getSigla());
/*     */       
/* 196 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 200 */       e.printStackTrace();
/* 201 */       this.context.setRollbackOnly();
/* 202 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 205 */     return lista;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\SegUsuarioQLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */