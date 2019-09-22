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
/*     */ import javax.persistence.Transient;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ import javax.xml.bind.annotation.XmlTransient;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="seg_usuario")
/*     */ public class SegUsuario
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="paterno", nullable=false, length=255)
/*     */   private String paterno;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="materno", nullable=false, length=255)
/*     */   private String materno;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="nombres", nullable=false, length=255)
/*     */   private String nombres;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="nro_documento", nullable=false, length=255)
/*     */   private String nroDocumento;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="email", nullable=false, length=255)
/*     */   private String email;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="p_cargo", nullable=false)
/*     */   private Integer pCargo;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="usuario", nullable=false, length=255)
/*     */   private String usuario;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="contrasena", nullable=false, length=255)
/*     */   private String contrasena;
/*     */   @Size(max=255)
/*     */   @Column(name="observaciones", length=255)
/*     */   private String observaciones;
/*     */   @Size(max=255)
/*     */   @Column(name="sigla", length=255)
/*     */   private String sigla;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="fechacreacion", nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechacreacion;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=1)
/*     */   @Column(name="tipousuario", nullable=false, length=1)
/*     */   private String tipousuario;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="fechainiciovigencia", nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechainiciovigencia;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="fechafinvigencia", nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechafinvigencia;
/*     */   @Size(max=255)
/*     */   @Column(name="pantallaprincipal", length=255)
/*     */   private String pantallaprincipal;
/*     */   @Size(max=1)
/*     */   @Column(name="conformidad", length=1)
/*     */   private String conformidad;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=1)
/*     */   @Column(name="habilitado", nullable=false, length=1)
/*     */   private String habilitado;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @JoinColumn(name="id_cooperativa", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private Cooperativa idCooperativa;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idUsuario")
/*     */   private List<SegPermiso> segPermisoList;
/*     */   @Transient
/*     */   private String nombreCompleto;
/*     */   
/*     */   public SegUsuario() {}
/*     */   
/*     */   public SegUsuario(Integer id)
/*     */   {
/* 147 */     this.id = id;
/*     */   }
/*     */   
/*     */   public SegUsuario(Integer id, String paterno, String materno, String nombres, String nroDocumento, String email, int pCargo, String usuario, String contrasena, Date fechacreacion, String tipousuario, Date fechainiciovigencia, Date fechafinvigencia, String habilitado, String estado) {
/* 151 */     this.id = id;
/* 152 */     this.paterno = paterno;
/* 153 */     this.materno = materno;
/* 154 */     this.nombres = nombres;
/* 155 */     this.nroDocumento = nroDocumento;
/* 156 */     this.email = email;
/* 157 */     this.pCargo = Integer.valueOf(pCargo);
/* 158 */     this.usuario = usuario;
/* 159 */     this.contrasena = contrasena;
/* 160 */     this.fechacreacion = fechacreacion;
/* 161 */     this.tipousuario = tipousuario;
/* 162 */     this.fechainiciovigencia = fechainiciovigencia;
/* 163 */     this.fechafinvigencia = fechafinvigencia;
/* 164 */     this.habilitado = habilitado;
/* 165 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 169 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 173 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getPaterno() {
/* 177 */     return this.paterno;
/*     */   }
/*     */   
/*     */   public void setPaterno(String paterno) {
/* 181 */     this.paterno = paterno;
/*     */   }
/*     */   
/*     */   public String getMaterno() {
/* 185 */     return this.materno;
/*     */   }
/*     */   
/*     */   public void setMaterno(String materno) {
/* 189 */     this.materno = materno;
/*     */   }
/*     */   
/*     */   public String getNombres() {
/* 193 */     return this.nombres;
/*     */   }
/*     */   
/*     */   public void setNombres(String nombres) {
/* 197 */     this.nombres = nombres;
/*     */   }
/*     */   
/*     */   public String getNroDocumento() {
/* 201 */     return this.nroDocumento;
/*     */   }
/*     */   
/*     */   public void setNroDocumento(String nroDocumento) {
/* 205 */     this.nroDocumento = nroDocumento;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 209 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 213 */     this.email = email;
/*     */   }
/*     */   
/*     */   public Integer getpCargo()
/*     */   {
/* 218 */     return this.pCargo;
/*     */   }
/*     */   
/*     */   public void setpCargo(Integer pCargo) {
/* 222 */     this.pCargo = pCargo;
/*     */   }
/*     */   
/*     */   public String getUsuario() {
/* 226 */     return this.usuario;
/*     */   }
/*     */   
/*     */   public void setUsuario(String usuario) {
/* 230 */     this.usuario = usuario;
/*     */   }
/*     */   
/*     */   public String getContrasena() {
/* 234 */     return this.contrasena;
/*     */   }
/*     */   
/*     */   public void setContrasena(String contrasena) {
/* 238 */     this.contrasena = contrasena;
/*     */   }
/*     */   
/*     */   public String getObservaciones() {
/* 242 */     return this.observaciones;
/*     */   }
/*     */   
/*     */   public void setObservaciones(String observaciones) {
/* 246 */     this.observaciones = observaciones;
/*     */   }
/*     */   
/*     */   public Date getFechacreacion() {
/* 250 */     return this.fechacreacion;
/*     */   }
/*     */   
/*     */   public void setFechacreacion(Date fechacreacion) {
/* 254 */     this.fechacreacion = fechacreacion;
/*     */   }
/*     */   
/*     */   public String getTipousuario() {
/* 258 */     return this.tipousuario;
/*     */   }
/*     */   
/*     */   public void setTipousuario(String tipousuario) {
/* 262 */     this.tipousuario = tipousuario;
/*     */   }
/*     */   
/*     */   public Date getFechainiciovigencia() {
/* 266 */     return this.fechainiciovigencia;
/*     */   }
/*     */   
/*     */   public void setFechainiciovigencia(Date fechainiciovigencia) {
/* 270 */     this.fechainiciovigencia = fechainiciovigencia;
/*     */   }
/*     */   
/*     */   public Date getFechafinvigencia() {
/* 274 */     return this.fechafinvigencia;
/*     */   }
/*     */   
/*     */   public void setFechafinvigencia(Date fechafinvigencia) {
/* 278 */     this.fechafinvigencia = fechafinvigencia;
/*     */   }
/*     */   
/*     */   public String getPantallaprincipal() {
/* 282 */     return this.pantallaprincipal;
/*     */   }
/*     */   
/*     */   public void setPantallaprincipal(String pantallaprincipal) {
/* 286 */     this.pantallaprincipal = pantallaprincipal;
/*     */   }
/*     */   
/*     */   public String getConformidad() {
/* 290 */     return this.conformidad;
/*     */   }
/*     */   
/*     */   public void setConformidad(String conformidad) {
/* 294 */     this.conformidad = conformidad;
/*     */   }
/*     */   
/*     */   public String getHabilitado() {
/* 298 */     return this.habilitado;
/*     */   }
/*     */   
/*     */   public void setHabilitado(String habilitado) {
/* 302 */     this.habilitado = habilitado;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 306 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 310 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public String getSigla() {
/* 314 */     return this.sigla;
/*     */   }
/*     */   
/*     */   public void setSigla(String sigla) {
/* 318 */     this.sigla = sigla;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<SegPermiso> getSegPermisoList() {
/* 323 */     return this.segPermisoList;
/*     */   }
/*     */   
/*     */   public void setSegPermisoList(List<SegPermiso> segPermisoList) {
/* 327 */     this.segPermisoList = segPermisoList;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 331 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 335 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 340 */     int hash = 0;
/* 341 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 342 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 348 */     if (!(object instanceof SegUsuario)) {
/* 349 */       return false;
/*     */     }
/* 351 */     SegUsuario other = (SegUsuario)object;
/* 352 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 353 */       return false;
/*     */     }
/* 355 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 360 */     return "org.erp.entities.SegUsuario[ id=" + this.id + " ]";
/*     */   }
/*     */   
/*     */   public String getNombreCompleto() {
/* 364 */     if ((this.paterno != null) && (this.materno != null) && (this.nombres != null)) {
/* 365 */       return this.paterno + " " + this.materno + ", " + this.nombres;
/*     */     }
/* 367 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */   public void setNombreCompleto(String nombreCompleto)
/*     */   {
/* 373 */     this.nombreCompleto = nombreCompleto;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\SegUsuario.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */