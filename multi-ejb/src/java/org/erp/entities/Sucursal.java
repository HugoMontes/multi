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
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="sucursal")
/*     */ @NamedQueries({@javax.persistence.NamedQuery(name="Sucursal.findAll", query="SELECT s FROM Sucursal s")})
/*     */ public class Sucursal
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Size(max=255)
/*     */   @Column(name="zona", length=255)
/*     */   private String zona;
/*     */   @Size(max=255)
/*     */   @Column(name="calle_avenida", length=255)
/*     */   private String calleAvenida;
/*     */   @Size(max=255)
/*     */   @Column(name="numero", length=255)
/*     */   private String numero;
/*     */   @Size(max=255)
/*     */   @Column(name="telefono", length=255)
/*     */   private String telefono;
/*     */   @Size(max=255)
/*     */   @Column(name="fax", length=255)
/*     */   private String fax;
/*     */   @Column(name="ind_tipo_sucursal")
/*     */   private Integer indTipoSucursal;
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
/*     */   @JoinColumn(name="id_cooperativa", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Cooperativa idCooperativa;
/*     */   @JoinColumn(name="id_ciudad", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private Ciudad idCiudad;
/*     */   @JoinColumn(name="id_departamento", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Departamento idDepartamento;
/*     */   @JoinColumn(name="id_municipio", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Municipio idMunicipio;
/*     */   
/*     */   public Sucursal() {}
/*     */   
/*     */   public Sucursal(Integer id)
/*     */   {
/*  83 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Sucursal(Integer id, String usuarioReg, String estado) {
/*  87 */     this.id = id;
/*  88 */     this.usuarioReg = usuarioReg;
/*  89 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  93 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  97 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getZona() {
/* 101 */     return this.zona;
/*     */   }
/*     */   
/*     */   public void setZona(String zona) {
/* 105 */     this.zona = zona;
/*     */   }
/*     */   
/*     */   public String getCalleAvenida() {
/* 109 */     return this.calleAvenida;
/*     */   }
/*     */   
/*     */   public void setCalleAvenida(String calleAvenida) {
/* 113 */     this.calleAvenida = calleAvenida;
/*     */   }
/*     */   
/*     */   public String getNumero() {
/* 117 */     return this.numero;
/*     */   }
/*     */   
/*     */   public void setNumero(String numero) {
/* 121 */     this.numero = numero;
/*     */   }
/*     */   
/*     */   public String getTelefono() {
/* 125 */     return this.telefono;
/*     */   }
/*     */   
/*     */   public void setTelefono(String telefono) {
/* 129 */     this.telefono = telefono;
/*     */   }
/*     */   
/*     */   public String getFax() {
/* 133 */     return this.fax;
/*     */   }
/*     */   
/*     */   public void setFax(String fax) {
/* 137 */     this.fax = fax;
/*     */   }
/*     */   
/*     */   public Integer getIndTipoSucursal() {
/* 141 */     return this.indTipoSucursal;
/*     */   }
/*     */   
/*     */   public void setIndTipoSucursal(Integer indTipoSucursal) {
/* 145 */     this.indTipoSucursal = indTipoSucursal;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 149 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 153 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 157 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 161 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 165 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 169 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 173 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 177 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 181 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 185 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public Ciudad getIdCiudad() {
/* 189 */     return this.idCiudad;
/*     */   }
/*     */   
/*     */   public void setIdCiudad(Ciudad idCiudad) {
/* 193 */     this.idCiudad = idCiudad;
/*     */   }
/*     */   
/*     */   public Departamento getIdDepartamento() {
/* 197 */     return this.idDepartamento;
/*     */   }
/*     */   
/*     */   public void setIdDepartamento(Departamento idDepartamento) {
/* 201 */     this.idDepartamento = idDepartamento;
/*     */   }
/*     */   
/*     */   public Municipio getIdMunicipio() {
/* 205 */     return this.idMunicipio;
/*     */   }
/*     */   
/*     */   public void setIdMunicipio(Municipio idMunicipio) {
/* 209 */     this.idMunicipio = idMunicipio;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 214 */     int hash = 0;
/* 215 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 216 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 222 */     if (!(object instanceof Sucursal)) {
/* 223 */       return false;
/*     */     }
/* 225 */     Sucursal other = (Sucursal)object;
/* 226 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 227 */       return false;
/*     */     }
/* 229 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 234 */     return "org.erp.entities.Sucursal[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Sucursal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */