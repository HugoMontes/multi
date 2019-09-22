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
/*     */ @javax.persistence.Table(name="actividad")
/*     */ public class Actividad implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Column(name="ind_actividad")
/*     */   private Integer indActividad;
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
/*     */   
/*     */   public Actividad() {}
/*     */   
/*     */   public Actividad(Integer id)
/*     */   {
/*  59 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Actividad(Integer id, String usuarioReg, String estado) {
/*  63 */     this.id = id;
/*  64 */     this.usuarioReg = usuarioReg;
/*  65 */     this.estado = estado;
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
/*     */   public Integer getIndActividad() {
/*  77 */     return this.indActividad;
/*     */   }
/*     */   
/*     */   public void setIndActividad(Integer indActividad) {
/*  81 */     this.indActividad = indActividad;
/*     */   }
/*     */   
/*     */   public String getOtroUno() {
/*  85 */     return this.otroUno;
/*     */   }
/*     */   
/*     */   public void setOtroUno(String otroUno) {
/*  89 */     this.otroUno = otroUno;
/*     */   }
/*     */   
/*     */   public String getOtroDos() {
/*  93 */     return this.otroDos;
/*     */   }
/*     */   
/*     */   public void setOtroDos(String otroDos) {
/*  97 */     this.otroDos = otroDos;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 101 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 105 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 109 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 113 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 117 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 121 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 125 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 129 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 133 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 137 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 142 */     int hash = 0;
/* 143 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 144 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 150 */     if (!(object instanceof Actividad)) {
/* 151 */       return false;
/*     */     }
/* 153 */     Actividad other = (Actividad)object;
/* 154 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 162 */     return "org.erp.entities.Actividad[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Actividad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */