/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ @Table(name="tipo_cambio")
/*     */ public class TipoCambio
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Basic(optional=false)
/*     */   @Column(name="ind_tipo_moneda", nullable=false)
/*     */   private Integer indTipoMoneda;
/*     */   @Column(name="cotizacion_oficial", precision=10, scale=2)
/*     */   private BigDecimal cotizacionOficial;
/*     */   @Column(name="cotizacion_compra", precision=10, scale=2)
/*     */   private BigDecimal cotizacionCompra;
/*     */   @Basic(optional=false)
/*     */   @Column(nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fecha;
/*     */   @Basic(optional=false)
/*     */   @Column(name="usuario_reg", nullable=false, length=255)
/*     */   private String usuarioReg;
/*     */   @Basic(optional=false)
/*     */   @Column(name="fecha_reg", nullable=false)
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date fechaReg;
/*     */   @Column(name="fecha_mod")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date fechaMod;
/*     */   @Basic(optional=false)
/*     */   @Column(nullable=false, length=3)
/*     */   private String estado;
/*     */   
/*     */   public TipoCambio() {}
/*     */   
/*     */   public TipoCambio(Integer id)
/*     */   {
/*  69 */     this.id = id;
/*     */   }
/*     */   
/*     */   public TipoCambio(Integer id, Integer indTipoMoneda, Date fecha, String usuarioReg, Date fechaReg, String estado) {
/*  73 */     this.id = id;
/*  74 */     this.indTipoMoneda = indTipoMoneda;
/*  75 */     this.fecha = fecha;
/*  76 */     this.usuarioReg = usuarioReg;
/*  77 */     this.fechaReg = fechaReg;
/*  78 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  82 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  86 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getIndTipoMoneda() {
/*  90 */     return this.indTipoMoneda;
/*     */   }
/*     */   
/*     */   public void setIndTipoMoneda(Integer indTipoMoneda) {
/*  94 */     this.indTipoMoneda = indTipoMoneda;
/*     */   }
/*     */   
/*     */   public BigDecimal getCotizacionOficial() {
/*  98 */     return this.cotizacionOficial;
/*     */   }
/*     */   
/*     */   public void setCotizacionOficial(BigDecimal cotizacionOficial) {
/* 102 */     this.cotizacionOficial = cotizacionOficial;
/*     */   }
/*     */   
/*     */   public BigDecimal getCotizacionCompra() {
/* 106 */     return this.cotizacionCompra;
/*     */   }
/*     */   
/*     */   public void setCotizacionCompra(BigDecimal cotizacionCompra) {
/* 110 */     this.cotizacionCompra = cotizacionCompra;
/*     */   }
/*     */   
/*     */   public Date getFecha() {
/* 114 */     return this.fecha;
/*     */   }
/*     */   
/*     */   public void setFecha(Date fecha) {
/* 118 */     this.fecha = fecha;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 122 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 126 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 130 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 134 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 138 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 142 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 146 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 150 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 155 */     int hash = 0;
/* 156 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 157 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 163 */     if (!(object instanceof TipoCambio)) {
/* 164 */       return false;
/*     */     }
/* 166 */     TipoCambio other = (TipoCambio)object;
/* 167 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 168 */       return false;
/*     */     }
/* 170 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 175 */     return "org.erp.entities.TipoCambio[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\TipoCambio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */