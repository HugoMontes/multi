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
/*     */ import javax.persistence.Transient;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ @Entity
/*     */ @javax.persistence.Table(name="mineral")
/*     */ public class Mineral implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Column(name="ind_metal_no_metal")
/*     */   private Integer indMetalNoMetal;
/*     */   @Column(name="ind_mineral")
/*     */   private Integer indMineral;
/*     */   @Size(max=255)
/*     */   @Column(name="otro_uno", length=255)
/*     */   private String otroUno;
/*     */   @Size(max=255)
/*     */   @Column(name="otro_dos", length=255)
/*     */   private String otroDos;
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
/*     */   @Transient
/*     */   private String otroTres;
/*     */   
/*     */   public Mineral() {}
/*     */   
/*     */   public Mineral(Integer id)
/*     */   {
/*  64 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Mineral(Integer id, String usuarioReg, String estado) {
/*  68 */     this.id = id;
/*  69 */     this.usuarioReg = usuarioReg;
/*  70 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  74 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  78 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getIndMetalNoMetal() {
/*  82 */     return this.indMetalNoMetal;
/*     */   }
/*     */   
/*     */   public void setIndMetalNoMetal(Integer indMetalNoMetal) {
/*  86 */     this.indMetalNoMetal = indMetalNoMetal;
/*     */   }
/*     */   
/*     */   public Integer getIndMineral() {
/*  90 */     return this.indMineral;
/*     */   }
/*     */   
/*     */   public void setIndMineral(Integer indMineral) {
/*  94 */     this.indMineral = indMineral;
/*     */   }
/*     */   
/*     */   public String getOtroUno() {
/*  98 */     return this.otroUno;
/*     */   }
/*     */   
/*     */   public void setOtroUno(String otroUno) {
/* 102 */     this.otroUno = otroUno;
/*     */   }
/*     */   
/*     */   public String getOtroDos() {
/* 106 */     return this.otroDos;
/*     */   }
/*     */   
/*     */   public void setOtroDos(String otroDos) {
/* 110 */     this.otroDos = otroDos;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 114 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 118 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 122 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 126 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 130 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 134 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 138 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 142 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 146 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 150 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public String getOtroTres() {
/* 154 */     return this.otroTres;
/*     */   }
/*     */   
/*     */   public void setOtroTres(String otroTres) {
/* 158 */     this.otroTres = otroTres;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 163 */     int hash = 0;
/* 164 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 165 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 171 */     if (!(object instanceof Mineral)) {
/* 172 */       return false;
/*     */     }
/* 174 */     Mineral other = (Mineral)object;
/* 175 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 176 */       return false;
/*     */     }
/* 178 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 183 */     return "org.erp.entities.Mineral[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Mineral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */