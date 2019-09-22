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
/*     */ import org.erp.entities.SegMenu;
/*     */ import org.erp.entities.SegMenurol;
/*     */ import org.erp.entities.SegRol;
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
/*     */ 
/*     */ @Stateless
/*     */ @TransactionManagement(TransactionManagementType.CONTAINER)
/*     */ public class SegMenuRolQLBean
/*     */   implements SegMenuRolQLBeanLocal
/*     */ {
/*     */   @PersistenceContext(unitName="admindbDS")
/*     */   EntityManager em;
/*     */   @Resource
/*     */   private SessionContext context;
/*     */   
/*     */   public List<SegMenu> listaMenu()
/*     */     throws AdminException
/*     */   {
/*  53 */     List<SegMenu> lista = new ArrayList();
/*     */     try {
/*  55 */       StringBuilder query = new StringBuilder();
/*     */       
/*  57 */       query.append(" select segMenu ");
/*  58 */       query.append(" from SegMenu segMenu ");
/*  59 */       query.append(" where segMenu.estado = :estado ");
/*     */       
/*  61 */       Query consulta = this.em.createQuery(query.toString());
/*  62 */       consulta.setParameter("estado", "HAB");
/*     */       
/*  64 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  68 */       e.printStackTrace();
/*  69 */       this.context.setRollbackOnly();
/*  70 */       throw new AdminException(e);
/*     */     }
/*     */     
/*  73 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegMenurol> listaMenuRol(String menu)
/*     */     throws AdminException
/*     */   {
/*  81 */     List<SegMenurol> lista = new ArrayList();
/*     */     try {
/*  83 */       StringBuilder query = new StringBuilder();
/*     */       
/*  85 */       query.append(" select menuRol,rol,menu ");
/*  86 */       query.append(" from SegMenurol menuRol ");
/*  87 */       query.append(" join menuRol.idRol rol ");
/*  88 */       query.append(" join menuRol.idMenu menu ");
/*  89 */       query.append(" where menuRol.estado = :estado ");
/*  90 */       query.append(" and rol.estado = :estado ");
/*  91 */       query.append(" and menu.estado = :estado ");
/*  92 */       query.append(" and menu.nombre = :nombre ");
/*     */       
/*  94 */       Query consulta = this.em.createQuery(query.toString());
/*  95 */       consulta.setParameter("estado", "HAB");
/*  96 */       consulta.setParameter("nombre", menu);
/*     */       
/*  98 */       List<Object[]> listaObjetos = consulta.getResultList();
/*     */       
/* 100 */       for (Object[] obj : listaObjetos) {
/* 101 */         lista.add((SegMenurol)obj[0]);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 106 */       e.printStackTrace();
/* 107 */       this.context.setRollbackOnly();
/* 108 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 111 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<SegRol> listaRol(String menuSeleccionado)
/*     */     throws AdminException
/*     */   {
/* 119 */     List<SegRol> lista = new ArrayList();
/*     */     try {
/* 121 */       StringBuilder query = new StringBuilder();
/*     */       
/* 123 */       query.append(" select rol ");
/* 124 */       query.append(" from SegRol rol ");
/* 125 */       query.append(" where rol.estado = :estado ");
/* 126 */       query.append(" and rol.estado = :estado ");
/* 127 */       query.append(" and rol.id not in ( ");
/*     */       
/* 129 */       query.append(" select ro.id from SegMenurol mr ");
/* 130 */       query.append(" join mr.idMenu menu ");
/* 131 */       query.append(" join mr.idRol ro ");
/* 132 */       query.append(" where mr.estado = :estado ");
/* 133 */       query.append(" and menu.estado = :estado ");
/* 134 */       query.append(" and ro.estado = :estado ");
/* 135 */       query.append(" and menu.nombre = :nombreMenu )");
/*     */       
/* 137 */       Query consulta = this.em.createQuery(query.toString());
/* 138 */       consulta.setParameter("estado", "HAB");
/* 139 */       consulta.setParameter("nombreMenu", menuSeleccionado);
/*     */       
/* 141 */       lista = consulta.getResultList();
/*     */     }
/*     */     catch (Exception e) {
/* 144 */       e.printStackTrace();
/* 145 */       this.context.setRollbackOnly();
/* 146 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 149 */     return lista;
/*     */   }
/*     */   
/*     */ 
/*     */   public SegRol datosRol(Integer idRol)
/*     */     throws AdminException
/*     */   {
/* 156 */     SegRol segRol = new SegRol();
/*     */     try {
/* 158 */       StringBuilder query = new StringBuilder();
/*     */       
/* 160 */       query.append(" select segRol ");
/* 161 */       query.append(" from SegRol segRol ");
/* 162 */       query.append(" where segRol.estado = :estado ");
/* 163 */       query.append(" and segRol.id = :idRol ");
/*     */       
/* 165 */       Query consulta = this.em.createQuery(query.toString());
/* 166 */       consulta.setParameter("estado", "HAB");
/* 167 */       consulta.setParameter("idRol", idRol);
/*     */       
/* 169 */       segRol = (SegRol)consulta.getSingleResult();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 173 */       e.printStackTrace();
/* 174 */       this.context.setRollbackOnly();
/* 175 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 178 */     return segRol;
/*     */   }
/*     */   
/*     */ 
/*     */   public SegMenu datosMenu(String menu)
/*     */     throws AdminException
/*     */   {
/* 185 */     SegMenu segMenu = new SegMenu();
/*     */     try {
/* 187 */       StringBuilder query = new StringBuilder();
/*     */       
/* 189 */       query.append(" select menu ");
/* 190 */       query.append(" from SegMenu menu ");
/* 191 */       query.append(" where menu.estado = :estado ");
/* 192 */       query.append(" and menu.nombre = :nombre ");
/*     */       
/* 194 */       Query consulta = this.em.createQuery(query.toString());
/* 195 */       consulta.setParameter("estado", "HAB");
/* 196 */       consulta.setParameter("nombre", menu);
/*     */       
/* 198 */       segMenu = (SegMenu)consulta.getSingleResult();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 202 */       e.printStackTrace();
/* 203 */       this.context.setRollbackOnly();
/* 204 */       throw new AdminException(e);
/*     */     }
/*     */     
/* 207 */     return segMenu;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\SegMenuRolQLBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */