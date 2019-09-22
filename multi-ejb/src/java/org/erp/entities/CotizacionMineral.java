/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cotizacion_mineral")
/*     */ public class CotizacionMineral
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Column(length=255)
/*     */   private String nombre;
/*     */   @Column(name="unidad_medida", length=255)
/*     */   private String unidadMedida;
/*     */   @Basic(optional=false)
/*     */   @Column(name="ind_unidad_medida", nullable=false)
/*     */   private Integer indUnidadMedida;
/*     */   @Column(name="cotizacion_oficial", precision=10, scale=4)
/*     */   private BigDecimal cotizacionOficial;
/*     */   @Column(name="alicuota_exportaciones", precision=10, scale=4)
/*     */   private BigDecimal alicuotaExportaciones;
/*     */   @Column(name="alicuota_internas", precision=10, scale=4)
/*     */   private BigDecimal alicuotaInternas;
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
/*     */   @OneToMany(mappedBy="idCotizacionMineral")
/*     */   private List<CotizacionMineralDesc> cotizacionMineralDescList;
/*     */   @OneToMany(mappedBy="minIdCotizacionMineral")
/*     */   private List<Liquidacion> liquidacionList;
/*     */   
/*     */   public CotizacionMineral() {}
/*     */   
/*     */   public CotizacionMineral(Integer id)
/*     */   {
/*  80 */     this.id = id;
/*     */   }
/*     */   
/*     */   public CotizacionMineral(Integer id, Integer indUnidadMedida, String usuarioReg, Date fechaReg, String estado) {
/*  84 */     this.id = id;
/*  85 */     this.indUnidadMedida = indUnidadMedida;
/*  86 */     this.usuarioReg = usuarioReg;
/*  87 */     this.fechaReg = fechaReg;
/*  88 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  92 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  96 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/* 100 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/* 104 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public Integer getIndUnidadMedida() {
/* 108 */     return this.indUnidadMedida;
/*     */   }
/*     */   
/*     */   public void setIndUnidadMedida(Integer indUnidadMedida) {
/* 112 */     this.indUnidadMedida = indUnidadMedida;
/*     */   }
/*     */   
/*     */   public BigDecimal getCotizacionOficial() {
/* 116 */     return this.cotizacionOficial;
/*     */   }
/*     */   
/*     */   public void setCotizacionOficial(BigDecimal cotizacionOficial) {
/* 120 */     this.cotizacionOficial = cotizacionOficial;
/*     */   }
/*     */   
/*     */   public BigDecimal getAlicuotaExportaciones() {
/* 124 */     return this.alicuotaExportaciones;
/*     */   }
/*     */   
/*     */   public void setAlicuotaExportaciones(BigDecimal alicuotaExportaciones) {
/* 128 */     this.alicuotaExportaciones = alicuotaExportaciones;
/*     */   }
/*     */   
/*     */   public BigDecimal getAlicuotaInternas() {
/* 132 */     return this.alicuotaInternas;
/*     */   }
/*     */   
/*     */   public void setAlicuotaInternas(BigDecimal alicuotaInternas) {
/* 136 */     this.alicuotaInternas = alicuotaInternas;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 140 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 144 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 148 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 152 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 156 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 160 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 164 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 168 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public List<CotizacionMineralDesc> getCotizacionMineralDescList() {
/* 172 */     return this.cotizacionMineralDescList;
/*     */   }
/*     */   
/*     */   public void setCotizacionMineralDescList(List<CotizacionMineralDesc> cotizacionMineralDescList) {
/* 176 */     this.cotizacionMineralDescList = cotizacionMineralDescList;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> getLiquidacionList() {
/* 180 */     return this.liquidacionList;
/*     */   }
/*     */   
/*     */   public void setLiquidacionList(List<Liquidacion> liquidacionList) {
/* 184 */     this.liquidacionList = liquidacionList;
/*     */   }
/*     */   
/*     */   public String getUnidadMedida() {
/* 188 */     return this.unidadMedida;
/*     */   }
/*     */   
/*     */   public void setUnidadMedida(String unidadMedida) {
/* 192 */     this.unidadMedida = unidadMedida;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 197 */     int hash = 0;
/* 198 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 199 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 205 */     if (!(object instanceof CotizacionMineral)) {
/* 206 */       return false;
/*     */     }
/* 208 */     CotizacionMineral other = (CotizacionMineral)object;
/* 209 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 210 */       return false;
/*     */     }
/* 212 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 217 */     return "org.erp.entities.CotizacionMineral[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\CotizacionMineral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */