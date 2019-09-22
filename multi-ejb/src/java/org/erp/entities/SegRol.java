/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="seg_rol")
/*     */ @NamedQueries({@javax.persistence.NamedQuery(name="SegRol.findAll", query="SELECT s FROM SegRol s"), @javax.persistence.NamedQuery(name="SegRol.findById", query="SELECT s FROM SegRol s WHERE s.id = :id"), @javax.persistence.NamedQuery(name="SegRol.findByNombre", query="SELECT s FROM SegRol s WHERE s.nombre = :nombre"), @javax.persistence.NamedQuery(name="SegRol.findByCodigo", query="SELECT s FROM SegRol s WHERE s.codigo = :codigo"), @javax.persistence.NamedQuery(name="SegRol.findByPantallaprincipal", query="SELECT s FROM SegRol s WHERE s.pantallaprincipal = :pantallaprincipal"), @javax.persistence.NamedQuery(name="SegRol.findByObservaciones", query="SELECT s FROM SegRol s WHERE s.observaciones = :observaciones"), @javax.persistence.NamedQuery(name="SegRol.findByEstado", query="SELECT s FROM SegRol s WHERE s.estado = :estado")})
/*     */ public class SegRol
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
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="nombre", nullable=false, length=255)
/*     */   private String nombre;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="codigo", nullable=false, length=255)
/*     */   private String codigo;
/*     */   @Size(max=255)
/*     */   @Column(name="pantallaprincipal", length=255)
/*     */   private String pantallaprincipal;
/*     */   @Size(max=255)
/*     */   @Column(name="observaciones", length=255)
/*     */   private String observaciones;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @OneToMany(mappedBy="idRol")
/*     */   private List<SegMenurol> segMenurolList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idRol")
/*     */   private List<SegPermiso> segPermisoList;
/*     */   
/*     */   public SegRol() {}
/*     */   
/*     */   public SegRol(Integer id)
/*     */   {
/*  65 */     this.id = id;
/*     */   }
/*     */   
/*     */   public SegRol(Integer id, String nombre, String codigo, String estado) {
/*  69 */     this.id = id;
/*  70 */     this.nombre = nombre;
/*  71 */     this.codigo = codigo;
/*  72 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  76 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  80 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  84 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  88 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getCodigo() {
/*  92 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public void setCodigo(String codigo) {
/*  96 */     this.codigo = codigo;
/*     */   }
/*     */   
/*     */   public String getPantallaprincipal() {
/* 100 */     return this.pantallaprincipal;
/*     */   }
/*     */   
/*     */   public void setPantallaprincipal(String pantallaprincipal) {
/* 104 */     this.pantallaprincipal = pantallaprincipal;
/*     */   }
/*     */   
/*     */   public String getObservaciones() {
/* 108 */     return this.observaciones;
/*     */   }
/*     */   
/*     */   public void setObservaciones(String observaciones) {
/* 112 */     this.observaciones = observaciones;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 116 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 120 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public List<SegMenurol> getSegMenurolList() {
/* 124 */     return this.segMenurolList;
/*     */   }
/*     */   
/*     */   public void setSegMenurolList(List<SegMenurol> segMenurolList) {
/* 128 */     this.segMenurolList = segMenurolList;
/*     */   }
/*     */   
/*     */   public List<SegPermiso> getSegPermisoList() {
/* 132 */     return this.segPermisoList;
/*     */   }
/*     */   
/*     */   public void setSegPermisoList(List<SegPermiso> segPermisoList) {
/* 136 */     this.segPermisoList = segPermisoList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 141 */     int hash = 0;
/* 142 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 143 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 149 */     if (!(object instanceof SegRol)) {
/* 150 */       return false;
/*     */     }
/* 152 */     SegRol other = (SegRol)object;
/* 153 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 154 */       return false;
/*     */     }
/* 156 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 161 */     return "org.erp.entities.SegRol[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\SegRol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */