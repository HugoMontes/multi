/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="gen_key")
/*     */ public class GenKey
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
/*     */   @Column(name="tipo_licencia", nullable=false, length=255)
/*     */   private String tipoLicencia;
/*     */   @Size(max=255)
/*     */   @Column(name="desde", length=255)
/*     */   private String desde;
/*     */   @Size(max=255)
/*     */   @Column(name="hasta", length=255)
/*     */   private String hasta;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="serial", nullable=false, length=255)
/*     */   private String serial;
/*     */   @Size(max=255)
/*     */   @Column(name="observaciones", length=255)
/*     */   private String observaciones;
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
/*     */   @Transient
/*     */   private Integer tipoLicenciaMostrar;
/*     */   @Transient
/*     */   private Date DesdeMostrar;
/*     */   @Transient
/*     */   private Date HastaMostrar;
/*     */   @Transient
/*     */   private String serieGenerada;
/*     */   
/*     */   public GenKey() {}
/*     */   
/*     */   public GenKey(Integer id)
/*     */   {
/*  77 */     this.id = id;
/*     */   }
/*     */   
/*     */   public GenKey(Integer id, String tipoLicencia, String serial, String usuarioReg, String estado) {
/*  81 */     this.id = id;
/*  82 */     this.tipoLicencia = tipoLicencia;
/*  83 */     this.serial = serial;
/*  84 */     this.usuarioReg = usuarioReg;
/*  85 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  89 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  93 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getTipoLicencia() {
/*  97 */     return this.tipoLicencia;
/*     */   }
/*     */   
/*     */   public void setTipoLicencia(String tipoLicencia) {
/* 101 */     this.tipoLicencia = tipoLicencia;
/*     */   }
/*     */   
/*     */   public String getDesde() {
/* 105 */     return this.desde;
/*     */   }
/*     */   
/*     */   public void setDesde(String desde) {
/* 109 */     this.desde = desde;
/*     */   }
/*     */   
/*     */   public String getHasta() {
/* 113 */     return this.hasta;
/*     */   }
/*     */   
/*     */   public void setHasta(String hasta) {
/* 117 */     this.hasta = hasta;
/*     */   }
/*     */   
/*     */   public String getSerial() {
/* 121 */     return this.serial;
/*     */   }
/*     */   
/*     */   public void setSerial(String serial) {
/* 125 */     this.serial = serial;
/*     */   }
/*     */   
/*     */   public String getObservaciones() {
/* 129 */     return this.observaciones;
/*     */   }
/*     */   
/*     */   public void setObservaciones(String observaciones) {
/* 133 */     this.observaciones = observaciones;
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
/*     */ 
/*     */   public Integer getTipoLicenciaMostrar()
/*     */   {
/* 171 */     return this.tipoLicenciaMostrar;
/*     */   }
/*     */   
/*     */   public void setTipoLicenciaMostrar(Integer tipoLicenciaMostrar) {
/* 175 */     this.tipoLicenciaMostrar = tipoLicenciaMostrar;
/*     */   }
/*     */   
/*     */   public Date getDesdeMostrar() {
/* 179 */     return this.DesdeMostrar;
/*     */   }
/*     */   
/*     */   public void setDesdeMostrar(Date desdeMostrar) {
/* 183 */     this.DesdeMostrar = desdeMostrar;
/*     */   }
/*     */   
/*     */   public Date getHastaMostrar() {
/* 187 */     return this.HastaMostrar;
/*     */   }
/*     */   
/*     */   public void setHastaMostrar(Date hastaMostrar) {
/* 191 */     this.HastaMostrar = hastaMostrar;
/*     */   }
/*     */   
/*     */   public String getSerieGenerada()
/*     */   {
/* 196 */     return this.serieGenerada;
/*     */   }
/*     */   
/*     */   public void setSerieGenerada(String serieGenerada) {
/* 200 */     this.serieGenerada = serieGenerada;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 205 */     int hash = 0;
/* 206 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 207 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 213 */     if (!(object instanceof GenKey)) {
/* 214 */       return false;
/*     */     }
/* 216 */     GenKey other = (GenKey)object;
/* 217 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 218 */       return false;
/*     */     }
/* 220 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 225 */     return "org.erp.entities.GenKey[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\GenKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */