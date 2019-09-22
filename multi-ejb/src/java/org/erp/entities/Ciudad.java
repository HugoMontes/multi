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
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="ciudad")
/*     */ public class Ciudad implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Size(max=255)
/*     */   @Column(name="nombre", length=255)
/*     */   private String nombre;
/*     */   @Size(max=255)
/*     */   @Column(name="descripcion", length=255)
/*     */   private String descripcion;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCiudad")
/*     */   private List<Sucursal> sucursalList;
/*     */   @javax.persistence.JoinColumn(name="id_municipio", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Municipio idMunicipio;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCiudad")
/*     */   private List<TransporteInterno> transporteInternoList;
/*     */   
/*     */   public Ciudad() {}
/*     */   
/*     */   public Ciudad(Integer id)
/*     */   {
/*  44 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  52 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  56 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  60 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/*  64 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/*  68 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public List<Sucursal> getSucursalList() {
/*  72 */     return this.sucursalList;
/*     */   }
/*     */   
/*     */   public void setSucursalList(List<Sucursal> sucursalList) {
/*  76 */     this.sucursalList = sucursalList;
/*     */   }
/*     */   
/*     */   public Municipio getIdMunicipio() {
/*  80 */     return this.idMunicipio;
/*     */   }
/*     */   
/*     */   public void setIdMunicipio(Municipio idMunicipio) {
/*  84 */     this.idMunicipio = idMunicipio;
/*     */   }
/*     */   
/*     */   public List<TransporteInterno> getTransporteInternoList() {
/*  88 */     return this.transporteInternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteInternoList(List<TransporteInterno> transporteInternoList) {
/*  92 */     this.transporteInternoList = transporteInternoList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  97 */     int hash = 0;
/*  98 */     hash += (this.id != null ? this.id.hashCode() : 0);
/*  99 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 105 */     if (!(object instanceof Ciudad)) {
/* 106 */       return false;
/*     */     }
/* 108 */     Ciudad other = (Ciudad)object;
/* 109 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 117 */     return "org.erp.entities.Ciudad[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Ciudad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */