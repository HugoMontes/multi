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
/*     */ @Table(name="seg_menurol")
/*     */ @NamedQueries({@javax.persistence.NamedQuery(name="SegMenurol.findAll", query="SELECT s FROM SegMenurol s"), @javax.persistence.NamedQuery(name="SegMenurol.findById", query="SELECT s FROM SegMenurol s WHERE s.id = :id"), @javax.persistence.NamedQuery(name="SegMenurol.findByEstado", query="SELECT s FROM SegMenurol s WHERE s.estado = :estado")})
/*     */ public class SegMenurol
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
/*     */   @JoinColumn(name="id_rol", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private SegRol idRol;
/*     */   @JoinColumn(name="id_menu", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private SegMenu idMenu;
/*     */   
/*     */   public SegMenurol() {}
/*     */   
/*     */   public SegMenurol(Integer id)
/*     */   {
/*  46 */     this.id = id;
/*     */   }
/*     */   
/*     */   public SegMenurol(Integer id, String estado) {
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
/*     */   public SegRol getIdRol() {
/*  71 */     return this.idRol;
/*     */   }
/*     */   
/*     */   public void setIdRol(SegRol idRol) {
/*  75 */     this.idRol = idRol;
/*     */   }
/*     */   
/*     */   public SegMenu getIdMenu() {
/*  79 */     return this.idMenu;
/*     */   }
/*     */   
/*     */   public void setIdMenu(SegMenu idMenu) {
/*  83 */     this.idMenu = idMenu;
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
/*  96 */     if (!(object instanceof SegMenurol)) {
/*  97 */       return false;
/*     */     }
/*  99 */     SegMenurol other = (SegMenurol)object;
/* 100 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 101 */       return false;
/*     */     }
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 108 */     return "org.erp.entities.SegMenurol[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\SegMenurol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */