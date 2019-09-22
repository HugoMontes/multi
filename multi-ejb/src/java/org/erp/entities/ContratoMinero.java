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
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ @Entity
/*     */ @javax.persistence.Table(name="contrato_minero")
/*     */ public class ContratoMinero implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Size(max=255)
/*     */   @Column(name="titular", length=255)
/*     */   private String titular;
/*     */   @Column(name="fecha_contrato")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaContrato;
/*     */   @Size(max=255)
/*     */   @Column(name="numero_testimonio", length=255)
/*     */   private String numeroTestimonio;
/*     */   @Size(max=255)
/*     */   @Column(name="plazo_anios", length=255)
/*     */   private String plazoAnios;
/*     */   @Column(name="ind_ates")
/*     */   private Integer indAtes;
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
/*     */   @javax.persistence.JoinColumn(name="id_cooperativa", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Cooperativa idCooperativa;
/*     */   
/*     */   public ContratoMinero() {}
/*     */   
/*     */   public ContratoMinero(Integer id)
/*     */   {
/*  65 */     this.id = id;
/*     */   }
/*     */   
/*     */   public ContratoMinero(Integer id, String usuarioReg, String estado) {
/*  69 */     this.id = id;
/*  70 */     this.usuarioReg = usuarioReg;
/*  71 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  75 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  79 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getTitular() {
/*  83 */     return this.titular;
/*     */   }
/*     */   
/*     */   public void setTitular(String titular) {
/*  87 */     this.titular = titular;
/*     */   }
/*     */   
/*     */   public Date getFechaContrato() {
/*  91 */     return this.fechaContrato;
/*     */   }
/*     */   
/*     */   public void setFechaContrato(Date fechaContrato) {
/*  95 */     this.fechaContrato = fechaContrato;
/*     */   }
/*     */   
/*     */   public String getNumeroTestimonio() {
/*  99 */     return this.numeroTestimonio;
/*     */   }
/*     */   
/*     */   public void setNumeroTestimonio(String numeroTestimonio) {
/* 103 */     this.numeroTestimonio = numeroTestimonio;
/*     */   }
/*     */   
/*     */   public String getPlazoAnios() {
/* 107 */     return this.plazoAnios;
/*     */   }
/*     */   
/*     */   public void setPlazoAnios(String plazoAnios) {
/* 111 */     this.plazoAnios = plazoAnios;
/*     */   }
/*     */   
/*     */   public Integer getIndAtes() {
/* 115 */     return this.indAtes;
/*     */   }
/*     */   
/*     */   public void setIndAtes(Integer indAtes) {
/* 119 */     this.indAtes = indAtes;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 123 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 127 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 131 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 135 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 139 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 143 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 147 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 151 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 155 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 159 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 164 */     int hash = 0;
/* 165 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 166 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 172 */     if (!(object instanceof ContratoMinero)) {
/* 173 */       return false;
/*     */     }
/* 175 */     ContratoMinero other = (ContratoMinero)object;
/* 176 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 177 */       return false;
/*     */     }
/* 179 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 184 */     return "org.erp.entities.ContratoMinero[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\ContratoMinero.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */