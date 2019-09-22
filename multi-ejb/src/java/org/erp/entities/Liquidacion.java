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
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="liquidacion")
/*     */ public class Liquidacion
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Column(name="numero_orden", length=255)
/*     */   private String numeroOrden;
/*     */   @Column(name="min_ind_presentacion")
/*     */   private Integer minIndPresentacion;
/*     */   @Column(name="min_pns_kg", precision=20, scale=5)
/*     */   private BigDecimal minPnsKg;
/*     */   @Column(name="min_ley", precision=20, scale=5)
/*     */   private BigDecimal minLey;
/*     */   @Column(name="min_ind_unidad")
/*     */   private Integer minIndUnidad;
/*     */   @Column(name="min_cotizacion", precision=20, scale=4)
/*     */   private BigDecimal minCotizacion;
/*     */   @Column(name="min_ind_unidad_med_coti")
/*     */   private Integer minIndUnidadMedCoti;
/*     */   @Column(name="min_alicuota", precision=20, scale=4)
/*     */   private BigDecimal minAlicuota;
/*     */   @Column(name="min_pf_kg", precision=20, scale=5)
/*     */   private BigDecimal minPfKg;
/*     */   @Column(name="min_pf_um", precision=20, scale=2)
/*     */   private BigDecimal minPfUm;
/*     */   @Column(name="min_vbv_usd", precision=20, scale=2)
/*     */   private BigDecimal minVbvUsd;
/*     */   @Column(name="min_vbv_bs", precision=20, scale=2)
/*     */   private BigDecimal minVbvBs;
/*     */   @Column(name="min_rm_bs", precision=20, scale=2)
/*     */   private BigDecimal minRmBs;
/*     */   @Column(name="min_distribucion_gobernacion", precision=20, scale=4)
/*     */   private BigDecimal minDistribucionGobernacion;
/*     */   @Column(name="min_distribucion_municipio", precision=20, scale=4)
/*     */   private BigDecimal minDistribucionMunicipio;
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
/*     */   @JoinColumn(name="min_id_cotizacion_mineral", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private CotizacionMineral minIdCotizacionMineral;
/*     */   @JoinColumn(name="min_id_cotizacion_mineral_desc", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private CotizacionMineralDesc minIdCotizacionMineralDesc;
/*     */   @JoinColumn(name="min_id_municipio", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private Municipio minIdMunicipio;
/*     */   @JoinColumn(name="id_regalia", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Regalia idRegalia;
/*     */   
/*     */   public Liquidacion() {}
/*     */   
/*     */   public Liquidacion(Integer id)
/*     */   {
/* 106 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Liquidacion(Integer id, String usuarioReg, Date fechaReg, String estado) {
/* 110 */     this.id = id;
/* 111 */     this.usuarioReg = usuarioReg;
/* 112 */     this.fechaReg = fechaReg;
/* 113 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 117 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 121 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNumeroOrden() {
/* 125 */     return this.numeroOrden;
/*     */   }
/*     */   
/*     */   public void setNumeroOrden(String numeroOrden) {
/* 129 */     this.numeroOrden = numeroOrden;
/*     */   }
/*     */   
/*     */   public Integer getMinIndPresentacion() {
/* 133 */     return this.minIndPresentacion;
/*     */   }
/*     */   
/*     */   public void setMinIndPresentacion(Integer minIndPresentacion) {
/* 137 */     this.minIndPresentacion = minIndPresentacion;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinPnsKg() {
/* 141 */     return this.minPnsKg;
/*     */   }
/*     */   
/*     */   public void setMinPnsKg(BigDecimal minPnsKg) {
/* 145 */     this.minPnsKg = minPnsKg;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinLey() {
/* 149 */     return this.minLey;
/*     */   }
/*     */   
/*     */   public void setMinLey(BigDecimal minLey) {
/* 153 */     this.minLey = minLey;
/*     */   }
/*     */   
/*     */   public Integer getMinIndUnidad() {
/* 157 */     return this.minIndUnidad;
/*     */   }
/*     */   
/*     */   public void setMinIndUnidad(Integer minIndUnidad) {
/* 161 */     this.minIndUnidad = minIndUnidad;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinCotizacion() {
/* 165 */     return this.minCotizacion;
/*     */   }
/*     */   
/*     */   public void setMinCotizacion(BigDecimal minCotizacion) {
/* 169 */     this.minCotizacion = minCotizacion;
/*     */   }
/*     */   
/*     */   public Integer getMinIndUnidadMedCoti() {
/* 173 */     return this.minIndUnidadMedCoti;
/*     */   }
/*     */   
/*     */   public void setMinIndUnidadMedCoti(Integer minIndUnidadMedCoti) {
/* 177 */     this.minIndUnidadMedCoti = minIndUnidadMedCoti;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinAlicuota() {
/* 181 */     return this.minAlicuota;
/*     */   }
/*     */   
/*     */   public void setMinAlicuota(BigDecimal minAlicuota) {
/* 185 */     this.minAlicuota = minAlicuota;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinPfKg() {
/* 189 */     return this.minPfKg;
/*     */   }
/*     */   
/*     */   public void setMinPfKg(BigDecimal minPfKg) {
/* 193 */     this.minPfKg = minPfKg;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinPfUm() {
/* 197 */     return this.minPfUm;
/*     */   }
/*     */   
/*     */   public void setMinPfUm(BigDecimal minPfUm) {
/* 201 */     this.minPfUm = minPfUm;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinVbvUsd() {
/* 205 */     return this.minVbvUsd;
/*     */   }
/*     */   
/*     */   public void setMinVbvUsd(BigDecimal minVbvUsd) {
/* 209 */     this.minVbvUsd = minVbvUsd;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinVbvBs() {
/* 213 */     return this.minVbvBs;
/*     */   }
/*     */   
/*     */   public void setMinVbvBs(BigDecimal minVbvBs) {
/* 217 */     this.minVbvBs = minVbvBs;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinRmBs() {
/* 221 */     return this.minRmBs;
/*     */   }
/*     */   
/*     */   public void setMinRmBs(BigDecimal minRmBs) {
/* 225 */     this.minRmBs = minRmBs;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinDistribucionGobernacion() {
/* 229 */     return this.minDistribucionGobernacion;
/*     */   }
/*     */   
/*     */   public void setMinDistribucionGobernacion(BigDecimal minDistribucionGobernacion) {
/* 233 */     this.minDistribucionGobernacion = minDistribucionGobernacion;
/*     */   }
/*     */   
/*     */   public BigDecimal getMinDistribucionMunicipio() {
/* 237 */     return this.minDistribucionMunicipio;
/*     */   }
/*     */   
/*     */   public void setMinDistribucionMunicipio(BigDecimal minDistribucionMunicipio) {
/* 241 */     this.minDistribucionMunicipio = minDistribucionMunicipio;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 245 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 249 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 253 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 257 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 261 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 265 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 269 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 273 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public CotizacionMineral getMinIdCotizacionMineral() {
/* 277 */     return this.minIdCotizacionMineral;
/*     */   }
/*     */   
/*     */   public void setMinIdCotizacionMineral(CotizacionMineral minIdCotizacionMineral) {
/* 281 */     this.minIdCotizacionMineral = minIdCotizacionMineral;
/*     */   }
/*     */   
/*     */   public CotizacionMineralDesc getMinIdCotizacionMineralDesc() {
/* 285 */     return this.minIdCotizacionMineralDesc;
/*     */   }
/*     */   
/*     */   public void setMinIdCotizacionMineralDesc(CotizacionMineralDesc minIdCotizacionMineralDesc) {
/* 289 */     this.minIdCotizacionMineralDesc = minIdCotizacionMineralDesc;
/*     */   }
/*     */   
/*     */   public Municipio getMinIdMunicipio() {
/* 293 */     return this.minIdMunicipio;
/*     */   }
/*     */   
/*     */   public void setMinIdMunicipio(Municipio minIdMunicipio) {
/* 297 */     this.minIdMunicipio = minIdMunicipio;
/*     */   }
/*     */   
/*     */   public Regalia getIdRegalia() {
/* 301 */     return this.idRegalia;
/*     */   }
/*     */   
/*     */   public void setIdRegalia(Regalia idRegalia) {
/* 305 */     this.idRegalia = idRegalia;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 310 */     int hash = 0;
/* 311 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 312 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 318 */     if (!(object instanceof Liquidacion)) {
/* 319 */       return false;
/*     */     }
/* 321 */     Liquidacion other = (Liquidacion)object;
/* 322 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 323 */       return false;
/*     */     }
/* 325 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 330 */     return "org.erp.entities.Liquidacion[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Liquidacion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */