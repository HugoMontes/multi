/*     */ package org.erp.seg.QL.seg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.ejb.SessionContext;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ejb.TransactionAttribute;
/*     */ import javax.ejb.TransactionAttributeType;
/*     */ import javax.ejb.TransactionManagement;
/*     */ import javax.ejb.TransactionManagementType;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.PersistenceContext;
/*     */ import javax.persistence.Query;
/*     */ import org.erp.entities.SegAcceso;
/*     */ import org.erp.entities.SegMenu;
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
/*     */ public class AccesoQLBean
/*     */   implements AccesoQLBeanLocal
/*     */ {
/*     */   @PersistenceContext(unitName="admindbDS")
/*     */   EntityManager em;
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public SegUsuario verificarAccesoUsuario(String usuario, String contrasena)
/*     */     throws AdminException
/*     */   {
/*  55 */     SegUsuario segUsuario = new SegUsuario();
/*     */     try {
/*  57 */       StringBuilder query = new StringBuilder();
/*     */       
/*  59 */       query.append(" select segUser ");
/*  60 */       query.append(" from SegUsuario segUser ");
/*  61 */       query.append(" where segUser.usuario = :usuario ");
/*  62 */       query.append(" and segUser.contrasena = :contrasena ");
/*  63 */       query.append(" and segUser.estado = :estado ");
/*     */       
/*  65 */       Query consulta = this.em.createQuery(query.toString());
/*  66 */       consulta.setParameter("usuario", usuario.toUpperCase());
/*  67 */       consulta.setParameter("contrasena", contrasena);
/*  68 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  70 */       segUsuario = (SegUsuario)consulta.getSingleResult();
/*     */     }
/*     */     catch (Exception e) {
/*  73 */       e.printStackTrace();
/*  74 */       segUsuario = null;
/*     */     }
/*  76 */     return segUsuario;
/*     */   }
/*     */   
/*     */ 
/*     */   public SegUsuario verificarCorreo(String email)
/*     */     throws AdminException
/*     */   {
/*  83 */     SegUsuario segUsuario = new SegUsuario();
/*     */     try {
/*  85 */       StringBuilder query = new StringBuilder();
/*     */       
/*  87 */       query.append(" select segUser ");
/*  88 */       query.append(" from SegUsuario segUser ");
/*  89 */       query.append(" where segUser.email = :email ");
/*  90 */       query.append(" and segUser.estado = :estado ");
/*     */       
/*  92 */       Query consulta = this.em.createQuery(query.toString());
/*  93 */       consulta.setParameter("email", email.toLowerCase());
/*  94 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  96 */       segUsuario = (SegUsuario)consulta.getSingleResult();
/*     */     }
/*     */     catch (Exception e) {
/*  99 */       segUsuario = null;
/*     */     }
/* 101 */     return segUsuario;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<SegRol> obtenerListaRoles(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/* 111 */     List<SegRol> lista = new ArrayList();
/*     */     try {
/* 113 */       StringBuilder query = new StringBuilder();
/*     */       
/* 115 */       query.append(" select segPer,segRol ");
/* 116 */       query.append(" from SegPermiso segPer ");
/* 117 */       query.append(" join segPer.idRol segRol ");
/* 118 */       query.append(" where segPer.idUsuario.id = :idUsuario");
/* 119 */       query.append(" and segPer.estado = :estado ");
/* 120 */       query.append(" and segRol.estado = :estado ");
/*     */       
/* 122 */       Query consulta = this.em.createQuery(query.toString());
/* 123 */       consulta.setParameter("idUsuario", segUsuario.getId());
/* 124 */       consulta.setParameter("estado", "HAB");
/*     */       
/* 126 */       List<Object[]> listaObjetos = consulta.getResultList();
/* 127 */       for (Object[] objeto : listaObjetos) {
/* 128 */         lista.add((SegRol)objeto[1]);
/*     */       }
/*     */     } catch (Exception e) {
/* 131 */       e.printStackTrace();
/* 132 */       this.context.setRollbackOnly();
/* 133 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 136 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegRol> obtenerListaRolesMenu(Integer identificador)
/*     */     throws AdminException
/*     */   {
/* 144 */     List<SegRol> lista = new ArrayList();
/*     */     try {
/* 146 */       StringBuilder query = new StringBuilder();
/*     */       
/* 148 */       query.append(" select segRol ");
/* 149 */       query.append(" from SegMenurol segMenRol ");
/* 150 */       query.append(" join segMenRol.idRol segRol ");
/* 151 */       query.append(" join segMenRol.idMenu segMenu ");
/*     */       
/* 153 */       query.append(" where segMenu.id = :identificador ");
/* 154 */       query.append(" and segMenu.nivel = :nivel ");
/* 155 */       query.append(" and segMenRol.estado = :estado ");
/* 156 */       query.append(" and segRol.estado = :estado ");
/* 157 */       query.append(" and segMenu.estado = :estado ");
/* 158 */       query.append(" and segMenu.tipo = :tipo ");
/* 159 */       Query consulta = this.em.createQuery(query.toString());
/*     */       
/* 161 */       consulta.setParameter("identificador", identificador);
/* 162 */       consulta.setParameter("nivel", new Integer(2));
/* 163 */       consulta.setParameter("estado", "HAB");
/* 164 */       consulta.setParameter("tipo", Integer.valueOf(Integer.parseInt("1")));
/*     */       
/* 166 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 169 */       e.printStackTrace();
/* 170 */       this.context.setRollbackOnly();
/* 171 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 174 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<SegMenu> obtenerListaMenu(SegUsuario segUsuario)
/*     */     throws AdminException
/*     */   {
/* 183 */     List<SegMenu> lista = new ArrayList();
/*     */     try {
/* 185 */       StringBuilder query = new StringBuilder();
/*     */       
/* 187 */       query.append(" select a ");
/* 188 */       query.append(" from SegMenu a ");
/* 189 */       query.append(" where a.estado = :estado");
/* 190 */       query.append(" and a.idMenu is null ");
/* 191 */       query.append(" and a.tipo = :tipo ");
/* 192 */       query.append(" order by a.id ");
/* 193 */       Query consulta = this.em.createQuery(query.toString());
/* 194 */       consulta.setParameter("estado", "HAB");
/* 195 */       consulta.setParameter("tipo", Integer.valueOf(Integer.parseInt("1")));
/* 196 */       lista = consulta.getResultList();
/*     */     } catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       this.context.setRollbackOnly();
/* 200 */       throw new AdminException(e);
/*     */     }
/* 202 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegMenu> obtenerListaMenuItem(SegMenu segMenu)
/*     */     throws AdminException
/*     */   {
/* 210 */     List<SegMenu> lista = new ArrayList();
/*     */     try {
/* 212 */       StringBuilder query = new StringBuilder();
/*     */       
/* 214 */       query.append(" select a ");
/* 215 */       query.append(" from SegMenu a ");
/* 216 */       query.append(" where a.estado = :estado ");
/* 217 */       query.append(" and a.idMenu.id = :idMenu ");
/* 218 */       query.append(" and a.tipo = :tipo ");
/* 219 */       query.append(" order by a.id asc ");
/* 220 */       Query consulta = this.em.createQuery(query.toString());
/* 221 */       consulta.setParameter("estado", "HAB");
/* 222 */       consulta.setParameter("idMenu", segMenu.getId());
/* 223 */       consulta.setParameter("tipo", Integer.valueOf(Integer.parseInt("1")));
/* 224 */       lista = consulta.getResultList();
/*     */     } catch (Exception e) {
/* 226 */       e.printStackTrace();
/* 227 */       throw new AdminException(e);
/*     */     }
/* 229 */     return lista;
/*     */   }
/*     */   
/*     */   @TransactionAttribute(TransactionAttributeType.REQUIRED)
/*     */   public void guardarAcceso(SegAcceso segAcceso) throws AdminException
/*     */   {
/*     */     try
/*     */     {
/* 237 */       this.em.persist(segAcceso);
/*     */     }
/*     */     catch (Exception e) {
/* 240 */       e.printStackTrace();
/* 241 */       this.context.setRollbackOnly();
/* 242 */       throw new AdminException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean obtenerListaMenuPorRol(List<Integer> listaRol, Integer idMenu)
/*     */     throws AdminException
/*     */   {
/* 251 */     boolean habilitado = false;
/* 252 */     List<SegMenu> lista = new ArrayList();
/*     */     try {
/* 254 */       StringBuilder query = new StringBuilder();
/*     */       
/* 256 */       query.append(" select mr ");
/* 257 */       query.append(" from SegMenurol mr ");
/* 258 */       query.append(" where mr.estado = :estado ");
/*     */       
/* 260 */       query.append(" and mr.idMenu.id = :idMenu ");
/* 261 */       query.append(" and mr.idRol.id in :listaRoles ");
/*     */       
/* 263 */       Query consulta = this.em.createQuery(query.toString());
/* 264 */       consulta.setParameter("estado", "HAB");
/* 265 */       consulta.setParameter("idMenu", idMenu);
/* 266 */       consulta.setParameter("listaRoles", listaRol);
/* 267 */       lista = consulta.getResultList();
/* 268 */       if (lista.size() > 0) {
/* 269 */         habilitado = true;
/*     */       }
/*     */     } catch (Exception e) {
/* 272 */       e.printStackTrace();
/* 273 */       throw new AdminException(e);
/*     */     }
/* 275 */     return habilitado;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\AccesoQLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */