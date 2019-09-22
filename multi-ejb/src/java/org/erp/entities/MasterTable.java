/*     */ package org.erp.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="master_table")
/*     */ public class MasterTable
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
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @OneToMany(mappedBy="idMaster")
/*     */   private List<ParameterTable> parameterTableList;
/*     */   
/*     */   public MasterTable() {}
/*     */   
/*     */   public MasterTable(Integer id)
/*     */   {
/*  46 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  50 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  54 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  58 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  62 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/*  66 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/*  70 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public String getEstado()
/*     */   {
/*  75 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/*  79 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public List<ParameterTable> getParameterTableList() {
/*  83 */     return this.parameterTableList;
/*     */   }
/*     */   
/*     */   public void setParameterTableList(List<ParameterTable> parameterTableList) {
/*  87 */     this.parameterTableList = parameterTableList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  92 */     int hash = 0;
/*  93 */     hash += (this.id != null ? this.id.hashCode() : 0);
/*  94 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 100 */     if (!(object instanceof MasterTable)) {
/* 101 */       return false;
/*     */     }
/* 103 */     MasterTable other = (MasterTable)object;
/* 104 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 105 */       return false;
/*     */     }
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 112 */     return "org.erp.entities.MasterTable[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\MasterTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */