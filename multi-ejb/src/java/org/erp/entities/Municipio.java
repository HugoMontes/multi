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
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="municipio")
/*     */ public class Municipio
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
/*     */   @Size(max=255)
/*     */   @Column(name="codigo", length=255)
/*     */   private String codigo;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idMunicipio")
/*     */   private List<Ciudad> ciudadList;
/*     */   @JoinColumn(name="id_departamento", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Departamento idDepartamento;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idMunicipio")
/*     */   private List<TransporteExterno> transporteExternoList;
/*     */   @OneToMany(mappedBy="idMunicipio")
/*     */   private List<DerechoMinero> derechoMineroList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idMunicipio")
/*     */   private List<Sucursal> sucursalList;
/*     */   @Size(max=255)
/*     */   @Column(name="municipio", length=255)
/*     */   private String municipio;
/*     */   @OneToMany(mappedBy="minIdMunicipio")
/*     */   private List<Liquidacion> liquidacionList;
/*     */   @OneToMany(mappedBy="ageIdMunicipio")
/*     */   private List<Regalia> regaliaList;
/*     */   
/*     */   public Municipio() {}
/*     */   
/*     */   public Municipio(Integer id)
/*     */   {
/*  65 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  69 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  73 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  77 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  81 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/*  85 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/*  89 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public String getCodigo() {
/*  93 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public void setCodigo(String codigo) {
/*  97 */     this.codigo = codigo;
/*     */   }
/*     */   
/*     */   public List<Ciudad> getCiudadList() {
/* 101 */     return this.ciudadList;
/*     */   }
/*     */   
/*     */   public void setCiudadList(List<Ciudad> ciudadList) {
/* 105 */     this.ciudadList = ciudadList;
/*     */   }
/*     */   
/*     */   public Departamento getIdDepartamento() {
/* 109 */     return this.idDepartamento;
/*     */   }
/*     */   
/*     */   public void setIdDepartamento(Departamento idDepartamento) {
/* 113 */     this.idDepartamento = idDepartamento;
/*     */   }
/*     */   
/*     */   public List<TransporteExterno> getTransporteExternoList() {
/* 117 */     return this.transporteExternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteExternoList(List<TransporteExterno> transporteExternoList) {
/* 121 */     this.transporteExternoList = transporteExternoList;
/*     */   }
/*     */   
/*     */   public List<DerechoMinero> getDerechoMineroList() {
/* 125 */     return this.derechoMineroList;
/*     */   }
/*     */   
/*     */   public void setDerechoMineroList(List<DerechoMinero> derechoMineroList) {
/* 129 */     this.derechoMineroList = derechoMineroList;
/*     */   }
/*     */   
/*     */   public List<Sucursal> getSucursalList() {
/* 133 */     return this.sucursalList;
/*     */   }
/*     */   
/*     */   public void setSucursalList(List<Sucursal> sucursalList) {
/* 137 */     this.sucursalList = sucursalList;
/*     */   }
/*     */   
/*     */   public String getMunicipio() {
/* 141 */     return this.municipio;
/*     */   }
/*     */   
/*     */   public void setMunicipio(String municipio) {
/* 145 */     this.municipio = municipio;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> getLiquidacionList() {
/* 149 */     return this.liquidacionList;
/*     */   }
/*     */   
/*     */   public void setLiquidacionList(List<Liquidacion> liquidacionList) {
/* 153 */     this.liquidacionList = liquidacionList;
/*     */   }
/*     */   
/*     */   public List<Regalia> getRegaliaList()
/*     */   {
/* 158 */     return this.regaliaList;
/*     */   }
/*     */   
/*     */   public void setRegaliaList(List<Regalia> regaliaList) {
/* 162 */     this.regaliaList = regaliaList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 167 */     int hash = 0;
/* 168 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 169 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 175 */     if (!(object instanceof Municipio)) {
/* 176 */       return false;
/*     */     }
/* 178 */     Municipio other = (Municipio)object;
/* 179 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 180 */       return false;
/*     */     }
/* 182 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 187 */     return "org.erp.entities.Municipio[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Municipio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */