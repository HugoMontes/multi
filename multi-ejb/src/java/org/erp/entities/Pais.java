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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="pais")
/*     */ public class Pais
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
/*     */   @Column(name="codigo", length=255)
/*     */   private String codigo;
/*     */   private String estado;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idPais")
/*     */   private List<TransporteExterno> transporteExternoList;
/*     */   @OneToMany(mappedBy="expIdPaisDestino")
/*     */   private List<Regalia> regaliaList;
/*     */   
/*     */   public Pais() {}
/*     */   
/*     */   public Pais(Integer id)
/*     */   {
/*  56 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  60 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  64 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  68 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  72 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getCodigo() {
/*  76 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public void setCodigo(String codigo) {
/*  80 */     this.codigo = codigo;
/*     */   }
/*     */   
/*     */   public List<TransporteExterno> getTransporteExternoList() {
/*  84 */     return this.transporteExternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteExternoList(List<TransporteExterno> transporteExternoList) {
/*  88 */     this.transporteExternoList = transporteExternoList;
/*     */   }
/*     */   
/*     */   public List<Regalia> getRegaliaList() {
/*  92 */     return this.regaliaList;
/*     */   }
/*     */   
/*     */   public void setRegaliaList(List<Regalia> regaliaList) {
/*  96 */     this.regaliaList = regaliaList;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 100 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 104 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 109 */     int hash = 0;
/* 110 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 111 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 117 */     if (!(object instanceof Pais)) {
/* 118 */       return false;
/*     */     }
/* 120 */     Pais other = (Pais)object;
/* 121 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 122 */       return false;
/*     */     }
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 129 */     return "org.erp.entities.Pais[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Pais.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */