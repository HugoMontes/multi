/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ @Entity
/*     */ @javax.persistence.Table(name="derecho_minero")
/*     */ public class DerechoMinero implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @javax.persistence.Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Size(max=255)
/*     */   @Column(name="denominativo", length=255)
/*     */   private String denominativo;
/*     */   @Column(name="fecha_derechos_min")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaDerechosMin;
/*     */   @Size(max=255)
/*     */   @Column(name="numero_resolucion", length=255)
/*     */   private String numeroResolucion;
/*     */   @Size(max=255)
/*     */   @Column(name="cantidad_cuadricula", length=255)
/*     */   private String cantidadCuadricula;
/*     */   @Size(max=255)
/*     */   @Column(name="cantidad_pertenencia", length=255)
/*     */   private String cantidadPertenencia;
/*     */   @Column(name="ind_unidad_medida")
/*     */   private Integer indUnidadMedida;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="usuario_reg", nullable=false, length=255)
/*     */   private String usuarioReg;
/*     */   @Column(name="fecha_reg")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaReg;
/*     */   @Column(name="fecha_mod")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaMod;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @JoinColumn(name="id_municipio", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private Municipio idMunicipio;
/*     */   @JoinColumn(name="id_cooperativa", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Cooperativa idCooperativa;
/*     */   
/*     */   public DerechoMinero() {}
/*     */   
/*     */   public DerechoMinero(Integer id)
/*     */   {
/*  71 */     this.id = id;
/*     */   }
/*     */   
/*     */   public DerechoMinero(Integer id, String usuarioReg, String estado) {
/*  75 */     this.id = id;
/*  76 */     this.usuarioReg = usuarioReg;
/*  77 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  81 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  85 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getDenominativo() {
/*  89 */     return this.denominativo;
/*     */   }
/*     */   
/*     */   public void setDenominativo(String denominativo) {
/*  93 */     this.denominativo = denominativo;
/*     */   }
/*     */   
/*     */   public Date getFechaDerechosMin() {
/*  97 */     return this.fechaDerechosMin;
/*     */   }
/*     */   
/*     */   public void setFechaDerechosMin(Date fechaDerechosMin) {
/* 101 */     this.fechaDerechosMin = fechaDerechosMin;
/*     */   }
/*     */   
/*     */   public String getNumeroResolucion() {
/* 105 */     return this.numeroResolucion;
/*     */   }
/*     */   
/*     */   public void setNumeroResolucion(String numeroResolucion) {
/* 109 */     this.numeroResolucion = numeroResolucion;
/*     */   }
/*     */   
/*     */   public String getCantidadCuadricula() {
/* 113 */     return this.cantidadCuadricula;
/*     */   }
/*     */   
/*     */   public void setCantidadCuadricula(String cantidadCuadricula) {
/* 117 */     this.cantidadCuadricula = cantidadCuadricula;
/*     */   }
/*     */   
/*     */   public String getCantidadPertenencia() {
/* 121 */     return this.cantidadPertenencia;
/*     */   }
/*     */   
/*     */   public void setCantidadPertenencia(String cantidadPertenencia) {
/* 125 */     this.cantidadPertenencia = cantidadPertenencia;
/*     */   }
/*     */   
/*     */   public Integer getIndUnidadMedida() {
/* 129 */     return this.indUnidadMedida;
/*     */   }
/*     */   
/*     */   public void setIndUnidadMedida(Integer indUnidadMedida) {
/* 133 */     this.indUnidadMedida = indUnidadMedida;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 137 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 141 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 145 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 149 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 153 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 157 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 161 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 165 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Municipio getIdMunicipio() {
/* 169 */     return this.idMunicipio;
/*     */   }
/*     */   
/*     */   public void setIdMunicipio(Municipio idMunicipio) {
/* 173 */     this.idMunicipio = idMunicipio;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 177 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 181 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 186 */     int hash = 0;
/* 187 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 188 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 194 */     if (!(object instanceof DerechoMinero)) {
/* 195 */       return false;
/*     */     }
/* 197 */     DerechoMinero other = (DerechoMinero)object;
/* 198 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 199 */       return false;
/*     */     }
/* 201 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 206 */     return "org.erp.entities.DerechoMinero[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\DerechoMinero.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */