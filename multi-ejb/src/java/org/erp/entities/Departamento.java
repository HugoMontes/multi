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
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.validation.constraints.Size;
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
/*     */ @Entity
/*     */ @Table(name="departamento")
/*     */ public class Departamento
/*     */   implements Serializable
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
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idDepartamento")
/*     */   private List<TransporteInterno> transporteInternoList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idDepartamento")
/*     */   private List<Municipio> municipioList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idDepartamento")
/*     */   private List<TransporteExterno> transporteExternoList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idDepartamento")
/*     */   private List<Sucursal> sucursalList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idDepartamento")
/*     */   private List<Regalia> regaliaList;
/*     */   
/*     */   public Departamento() {}
/*     */   
/*     */   public Departamento(Integer id)
/*     */   {
/*  58 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  62 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  66 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  70 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  74 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/*  78 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/*  82 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public List<TransporteInterno> getTransporteInternoList() {
/*  86 */     return this.transporteInternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteInternoList(List<TransporteInterno> transporteInternoList) {
/*  90 */     this.transporteInternoList = transporteInternoList;
/*     */   }
/*     */   
/*     */   public List<Municipio> getMunicipioList() {
/*  94 */     return this.municipioList;
/*     */   }
/*     */   
/*     */   public void setMunicipioList(List<Municipio> municipioList) {
/*  98 */     this.municipioList = municipioList;
/*     */   }
/*     */   
/*     */   public List<TransporteExterno> getTransporteExternoList() {
/* 102 */     return this.transporteExternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteExternoList(List<TransporteExterno> transporteExternoList) {
/* 106 */     this.transporteExternoList = transporteExternoList;
/*     */   }
/*     */   
/*     */   public List<Sucursal> getSucursalList() {
/* 110 */     return this.sucursalList;
/*     */   }
/*     */   
/*     */   public void setSucursalList(List<Sucursal> sucursalList) {
/* 114 */     this.sucursalList = sucursalList;
/*     */   }
/*     */   
/*     */   public List<Regalia> getRegaliaList() {
/* 118 */     return this.regaliaList;
/*     */   }
/*     */   
/*     */   public void setRegaliaList(List<Regalia> regaliaList) {
/* 122 */     this.regaliaList = regaliaList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 127 */     int hash = 0;
/* 128 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 129 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 135 */     if (!(object instanceof Departamento)) {
/* 136 */       return false;
/*     */     }
/* 138 */     Departamento other = (Departamento)object;
/* 139 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 140 */       return false;
/*     */     }
/* 142 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 147 */     return "org.erp.entities.Departamento[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Departamento.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */