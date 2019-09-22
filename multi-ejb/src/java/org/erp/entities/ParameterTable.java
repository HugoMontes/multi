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
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="parameter_table")
/*     */ public class ParameterTable
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
/*     */   @Column(name="descripcion", length=255)
/*     */   private String descripcion;
/*     */   @Size(max=255)
/*     */   @Column(name="texto1", length=255)
/*     */   private String texto1;
/*     */   @Size(max=255)
/*     */   @Column(name="texto2", length=255)
/*     */   private String texto2;
/*     */   @Column(name="fechaInicio")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaInicio;
/*     */   @Column(name="fechaFin")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaFin;
/*     */   @Column(name="numero1")
/*     */   private Integer numero1;
/*     */   @Column(name="numero2")
/*     */   private Integer numero2;
/*     */   @Size(max=1)
/*     */   @Column(name="requerido", length=1)
/*     */   private String requerido;
/*     */   @Column(name="orden")
/*     */   private Integer orden;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @JoinColumn(name="id_master", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private MasterTable idMaster;
/*     */   
/*     */   public ParameterTable() {}
/*     */   
/*     */   public ParameterTable(Integer id)
/*     */   {
/*  71 */     this.id = id;
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
/*     */   public String getNombre() {
/*  83 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  87 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/*  91 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/*  95 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public String getTexto1() {
/*  99 */     return this.texto1;
/*     */   }
/*     */   
/*     */   public void setTexto1(String texto1) {
/* 103 */     this.texto1 = texto1;
/*     */   }
/*     */   
/*     */   public String getTexto2() {
/* 107 */     return this.texto2;
/*     */   }
/*     */   
/*     */   public void setTexto2(String texto2) {
/* 111 */     this.texto2 = texto2;
/*     */   }
/*     */   
/*     */   public Date getFechaInicio() {
/* 115 */     return this.fechaInicio;
/*     */   }
/*     */   
/*     */   public void setFechaInicio(Date fechaInicio) {
/* 119 */     this.fechaInicio = fechaInicio;
/*     */   }
/*     */   
/*     */   public Date getFechaFin() {
/* 123 */     return this.fechaFin;
/*     */   }
/*     */   
/*     */   public void setFechaFin(Date fechaFin) {
/* 127 */     this.fechaFin = fechaFin;
/*     */   }
/*     */   
/*     */   public Integer getNumero1() {
/* 131 */     return this.numero1;
/*     */   }
/*     */   
/*     */   public void setNumero1(Integer numero1) {
/* 135 */     this.numero1 = numero1;
/*     */   }
/*     */   
/*     */   public Integer getNumero2() {
/* 139 */     return this.numero2;
/*     */   }
/*     */   
/*     */   public void setNumero2(Integer numero2) {
/* 143 */     this.numero2 = numero2;
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
/*     */   public MasterTable getIdMaster() {
/* 155 */     return this.idMaster;
/*     */   }
/*     */   
/*     */   public void setIdMaster(MasterTable idMaster) {
/* 159 */     this.idMaster = idMaster;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getRequerido()
/*     */   {
/* 165 */     return this.requerido;
/*     */   }
/*     */   
/*     */   public void setRequerido(String requerido) {
/* 169 */     this.requerido = requerido;
/*     */   }
/*     */   
/*     */   public Integer getOrden() {
/* 173 */     return this.orden;
/*     */   }
/*     */   
/*     */   public void setOrden(Integer orden) {
/* 177 */     this.orden = orden;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 182 */     int hash = 0;
/* 183 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 184 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 190 */     if (!(object instanceof ParameterTable)) {
/* 191 */       return false;
/*     */     }
/* 193 */     ParameterTable other = (ParameterTable)object;
/* 194 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 195 */       return false;
/*     */     }
/* 197 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 202 */     return "org.erp.entities.ParameterTable[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\ParameterTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */