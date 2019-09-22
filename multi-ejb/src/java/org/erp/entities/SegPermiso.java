/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.Table;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="seg_permiso")
/*     */ @NamedQueries({@javax.persistence.NamedQuery(name="SegPermiso.findAll", query="SELECT s FROM SegPermiso s"), @javax.persistence.NamedQuery(name="SegPermiso.findById", query="SELECT s FROM SegPermiso s WHERE s.id = :id"), @javax.persistence.NamedQuery(name="SegPermiso.findByEstado", query="SELECT s FROM SegPermiso s WHERE s.estado = :estado")})
/*     */ public class SegPermiso
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @JoinColumn(name="id_usuario", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private SegUsuario idUsuario;
/*     */   @JoinColumn(name="id_rol", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private SegRol idRol;
/*     */   
/*     */   public SegPermiso() {}
/*     */   
/*     */   public SegPermiso(Integer id)
/*     */   {
/*  46 */     this.id = id;
/*     */   }
/*     */   
/*     */   public SegPermiso(Integer id, String estado) {
/*  50 */     this.id = id;
/*  51 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  55 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  59 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/*  63 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/*  67 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public SegUsuario getIdUsuario() {
/*  71 */     return this.idUsuario;
/*     */   }
/*     */   
/*     */   public void setIdUsuario(SegUsuario idUsuario) {
/*  75 */     this.idUsuario = idUsuario;
/*     */   }
/*     */   
/*     */   public SegRol getIdRol() {
/*  79 */     return this.idRol;
/*     */   }
/*     */   
/*     */   public void setIdRol(SegRol idRol) {
/*  83 */     this.idRol = idRol;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  88 */     int hash = 0;
/*  89 */     hash += (this.id != null ? this.id.hashCode() : 0);
/*  90 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/*  96 */     if (!(object instanceof SegPermiso)) {
/*  97 */       return false;
/*     */     }
/*  99 */     SegPermiso other = (SegPermiso)object;
/* 100 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 101 */       return false;
/*     */     }
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 108 */     return "org.erp.entities.SegPermiso[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\SegPermiso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */