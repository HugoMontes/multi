/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
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
/*     */ @Entity
/*     */ @Table(name="cotizacion_mineral_desc")
/*     */ public class CotizacionMineralDesc
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
/*     */   @JoinColumn(name="id_cotizacion_mineral", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private CotizacionMineral idCotizacionMineral;
/*     */   @OneToMany(mappedBy="minIdCotizacionMineralDesc")
/*     */   private List<Liquidacion> liquidacionList;
/*     */   
/*     */   public CotizacionMineralDesc() {}
/*     */   
/*     */   public CotizacionMineralDesc(Integer id)
/*     */   {
/*  69 */     this.id = id;
/*     */   }
/*     */   
/*     */   public CotizacionMineralDesc(Integer id, String usuarioReg, Date fechaReg, String estado) {
/*  73 */     this.id = id;
/*  74 */     this.usuarioReg = usuarioReg;
/*  75 */     this.fechaReg = fechaReg;
/*  76 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  80 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  84 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  88 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  92 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/*  96 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 100 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 104 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 108 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 112 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 116 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 120 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 124 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public CotizacionMineral getIdCotizacionMineral() {
/* 128 */     return this.idCotizacionMineral;
/*     */   }
/*     */   
/*     */   public void setIdCotizacionMineral(CotizacionMineral idCotizacionMineral) {
/* 132 */     this.idCotizacionMineral = idCotizacionMineral;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> getLiquidacionList() {
/* 136 */     return this.liquidacionList;
/*     */   }
/*     */   
/*     */   public void setLiquidacionList(List<Liquidacion> liquidacionList) {
/* 140 */     this.liquidacionList = liquidacionList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 145 */     int hash = 0;
/* 146 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 147 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 153 */     if (!(object instanceof CotizacionMineralDesc)) {
/* 154 */       return false;
/*     */     }
/* 156 */     CotizacionMineralDesc other = (CotizacionMineralDesc)object;
/* 157 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 158 */       return false;
/*     */     }
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 165 */     return "org.erp.entities.CotizacionMineralDesc[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\CotizacionMineralDesc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */