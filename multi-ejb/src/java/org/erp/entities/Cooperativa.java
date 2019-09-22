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
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cooperativa")
/*     */ public class Cooperativa
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
/*     */   @Column(name="nro_registro", nullable=false)
/*     */   private int nroRegistro;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="fecha_registro", nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaRegistro;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="fecha_expiracion", nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaExpiracion;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=255)
/*     */   @Column(name="razon_social", nullable=false, length=255)
/*     */   private String razonSocial;
/*     */   @Size(max=255)
/*     */   @Column(name="nit", length=255)
/*     */   private String nit;
/*     */   @Size(max=255)
/*     */   @Column(name="nim", length=255)
/*     */   private String nim;
/*     */   @Size(max=255)
/*     */   @Column(name="niar", length=255)
/*     */   private String niar;
/*     */   @Size(max=255)
/*     */   @Column(name="ruex", length=255)
/*     */   private String ruex;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="ind_actor_productivo", nullable=false)
/*     */   private int indActorProductivo;
/*     */   @Size(max=255)
/*     */   @Column(name="resolucion_fundeempresa", length=255)
/*     */   private String resolucionFundeempresa;
/*     */   @Size(max=255)
/*     */   @Column(name="resolucion_adm", length=255)
/*     */   private String resolucionAdm;
/*     */   @Column(name="fecha_admin")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaAdmin;
/*     */   @Size(max=255)
/*     */   @Column(name="nro_socios", length=255)
/*     */   private String nroSocios;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_paterno", length=255)
/*     */   private String reprePaterno;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_materno", length=255)
/*     */   private String repreMaterno;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_nombres", length=255)
/*     */   private String repreNombres;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_cargo", length=255)
/*     */   private String repreCargo;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_ci", length=255)
/*     */   private String repreCi;
/*     */   @Column(name="ind_repre_expendido")
/*     */   private Integer indRepreExpendido;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_email", length=255)
/*     */   private String repreEmail;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_telefono", length=255)
/*     */   private String repreTelefono;
/*     */   @Size(max=255)
/*     */   @Column(name="repre_celular", length=255)
/*     */   private String repreCelular;
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
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<Sucursal> sucursalList;
/*     */   @OneToMany(mappedBy="idCooperativa")
/*     */   private List<SegUsuario> segUsuarioList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<ContratoMinero> contratoMineroList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<Actividad> actividadList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<TransporteInterno> transporteInternoList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<Mineral> mineralList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<TransporteExterno> transporteExternoList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<DerechoMinero> derechoMineroList;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idCooperativa")
/*     */   private List<Regalia> regaliaList;
/*     */   @Transient
/*     */   private String nombreCompleto;
/*     */   
/*     */   public Cooperativa() {}
/*     */   
/*     */   public Cooperativa(Integer id)
/*     */   {
/* 151 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Cooperativa(Integer id, int nroRegistro, Date fechaRegistro, Date fechaExpiracion, String razonSocial, int indActorProductivo, String usuarioReg, String estado) {
/* 155 */     this.id = id;
/* 156 */     this.nroRegistro = nroRegistro;
/* 157 */     this.fechaRegistro = fechaRegistro;
/* 158 */     this.fechaExpiracion = fechaExpiracion;
/* 159 */     this.razonSocial = razonSocial;
/* 160 */     this.indActorProductivo = indActorProductivo;
/* 161 */     this.usuarioReg = usuarioReg;
/* 162 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 166 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 170 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getNroRegistro() {
/* 174 */     return this.nroRegistro;
/*     */   }
/*     */   
/*     */   public void setNroRegistro(int nroRegistro) {
/* 178 */     this.nroRegistro = nroRegistro;
/*     */   }
/*     */   
/*     */   public Date getFechaRegistro() {
/* 182 */     return this.fechaRegistro;
/*     */   }
/*     */   
/*     */   public void setFechaRegistro(Date fechaRegistro) {
/* 186 */     this.fechaRegistro = fechaRegistro;
/*     */   }
/*     */   
/*     */   public Date getFechaExpiracion() {
/* 190 */     return this.fechaExpiracion;
/*     */   }
/*     */   
/*     */   public void setFechaExpiracion(Date fechaExpiracion) {
/* 194 */     this.fechaExpiracion = fechaExpiracion;
/*     */   }
/*     */   
/*     */   public String getRazonSocial() {
/* 198 */     return this.razonSocial;
/*     */   }
/*     */   
/*     */   public void setRazonSocial(String razonSocial) {
/* 202 */     this.razonSocial = razonSocial;
/*     */   }
/*     */   
/*     */   public String getNit() {
/* 206 */     return this.nit;
/*     */   }
/*     */   
/*     */   public void setNit(String nit) {
/* 210 */     this.nit = nit;
/*     */   }
/*     */   
/*     */   public String getNim() {
/* 214 */     return this.nim;
/*     */   }
/*     */   
/*     */   public void setNim(String nim) {
/* 218 */     this.nim = nim;
/*     */   }
/*     */   
/*     */   public String getNiar() {
/* 222 */     return this.niar;
/*     */   }
/*     */   
/*     */   public void setNiar(String niar) {
/* 226 */     this.niar = niar;
/*     */   }
/*     */   
/*     */   public String getRuex() {
/* 230 */     return this.ruex;
/*     */   }
/*     */   
/*     */   public void setRuex(String ruex) {
/* 234 */     this.ruex = ruex;
/*     */   }
/*     */   
/*     */   public int getIndActorProductivo() {
/* 238 */     return this.indActorProductivo;
/*     */   }
/*     */   
/*     */   public void setIndActorProductivo(int indActorProductivo) {
/* 242 */     this.indActorProductivo = indActorProductivo;
/*     */   }
/*     */   
/*     */   public String getResolucionFundeempresa() {
/* 246 */     return this.resolucionFundeempresa;
/*     */   }
/*     */   
/*     */   public void setResolucionFundeempresa(String resolucionFundeempresa) {
/* 250 */     this.resolucionFundeempresa = resolucionFundeempresa;
/*     */   }
/*     */   
/*     */   public String getResolucionAdm() {
/* 254 */     return this.resolucionAdm;
/*     */   }
/*     */   
/*     */   public void setResolucionAdm(String resolucionAdm) {
/* 258 */     this.resolucionAdm = resolucionAdm;
/*     */   }
/*     */   
/*     */   public Date getFechaAdmin() {
/* 262 */     return this.fechaAdmin;
/*     */   }
/*     */   
/*     */   public void setFechaAdmin(Date fechaAdmin) {
/* 266 */     this.fechaAdmin = fechaAdmin;
/*     */   }
/*     */   
/*     */   public String getNroSocios() {
/* 270 */     return this.nroSocios;
/*     */   }
/*     */   
/*     */   public void setNroSocios(String nroSocios) {
/* 274 */     this.nroSocios = nroSocios;
/*     */   }
/*     */   
/*     */   public String getReprePaterno() {
/* 278 */     return this.reprePaterno;
/*     */   }
/*     */   
/*     */   public void setReprePaterno(String reprePaterno) {
/* 282 */     this.reprePaterno = reprePaterno;
/*     */   }
/*     */   
/*     */   public String getRepreMaterno() {
/* 286 */     return this.repreMaterno;
/*     */   }
/*     */   
/*     */   public void setRepreMaterno(String repreMaterno) {
/* 290 */     this.repreMaterno = repreMaterno;
/*     */   }
/*     */   
/*     */   public String getRepreNombres() {
/* 294 */     return this.repreNombres;
/*     */   }
/*     */   
/*     */   public void setRepreNombres(String repreNombres) {
/* 298 */     this.repreNombres = repreNombres;
/*     */   }
/*     */   
/*     */   public String getRepreCargo() {
/* 302 */     return this.repreCargo;
/*     */   }
/*     */   
/*     */   public void setRepreCargo(String repreCargo) {
/* 306 */     this.repreCargo = repreCargo;
/*     */   }
/*     */   
/*     */   public String getRepreCi() {
/* 310 */     return this.repreCi;
/*     */   }
/*     */   
/*     */   public void setRepreCi(String repreCi) {
/* 314 */     this.repreCi = repreCi;
/*     */   }
/*     */   
/*     */   public Integer getIndRepreExpendido() {
/* 318 */     return this.indRepreExpendido;
/*     */   }
/*     */   
/*     */   public void setIndRepreExpendido(Integer indRepreExpendido) {
/* 322 */     this.indRepreExpendido = indRepreExpendido;
/*     */   }
/*     */   
/*     */   public String getRepreEmail() {
/* 326 */     return this.repreEmail;
/*     */   }
/*     */   
/*     */   public void setRepreEmail(String repreEmail) {
/* 330 */     this.repreEmail = repreEmail;
/*     */   }
/*     */   
/*     */   public String getRepreTelefono() {
/* 334 */     return this.repreTelefono;
/*     */   }
/*     */   
/*     */   public void setRepreTelefono(String repreTelefono) {
/* 338 */     this.repreTelefono = repreTelefono;
/*     */   }
/*     */   
/*     */   public String getRepreCelular() {
/* 342 */     return this.repreCelular;
/*     */   }
/*     */   
/*     */   public void setRepreCelular(String repreCelular) {
/* 346 */     this.repreCelular = repreCelular;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 350 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 354 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 358 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 362 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 366 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 370 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 374 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 378 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public List<Sucursal> getSucursalList() {
/* 382 */     return this.sucursalList;
/*     */   }
/*     */   
/*     */   public void setSucursalList(List<Sucursal> sucursalList) {
/* 386 */     this.sucursalList = sucursalList;
/*     */   }
/*     */   
/*     */   public List<SegUsuario> getSegUsuarioList() {
/* 390 */     return this.segUsuarioList;
/*     */   }
/*     */   
/*     */   public void setSegUsuarioList(List<SegUsuario> segUsuarioList) {
/* 394 */     this.segUsuarioList = segUsuarioList;
/*     */   }
/*     */   
/*     */   public List<ContratoMinero> getContratoMineroList() {
/* 398 */     return this.contratoMineroList;
/*     */   }
/*     */   
/*     */   public void setContratoMineroList(List<ContratoMinero> contratoMineroList) {
/* 402 */     this.contratoMineroList = contratoMineroList;
/*     */   }
/*     */   
/*     */   public List<Actividad> getActividadList() {
/* 406 */     return this.actividadList;
/*     */   }
/*     */   
/*     */   public void setActividadList(List<Actividad> actividadList) {
/* 410 */     this.actividadList = actividadList;
/*     */   }
/*     */   
/*     */   public List<TransporteInterno> getTransporteInternoList() {
/* 414 */     return this.transporteInternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteInternoList(List<TransporteInterno> transporteInternoList) {
/* 418 */     this.transporteInternoList = transporteInternoList;
/*     */   }
/*     */   
/*     */   public List<Mineral> getMineralList() {
/* 422 */     return this.mineralList;
/*     */   }
/*     */   
/*     */   public void setMineralList(List<Mineral> mineralList) {
/* 426 */     this.mineralList = mineralList;
/*     */   }
/*     */   
/*     */   public List<TransporteExterno> getTransporteExternoList() {
/* 430 */     return this.transporteExternoList;
/*     */   }
/*     */   
/*     */   public void setTransporteExternoList(List<TransporteExterno> transporteExternoList) {
/* 434 */     this.transporteExternoList = transporteExternoList;
/*     */   }
/*     */   
/*     */   public List<DerechoMinero> getDerechoMineroList() {
/* 438 */     return this.derechoMineroList;
/*     */   }
/*     */   
/*     */   public void setDerechoMineroList(List<DerechoMinero> derechoMineroList) {
/* 442 */     this.derechoMineroList = derechoMineroList;
/*     */   }
/*     */   
/*     */   public String getNombreCompleto()
/*     */   {
/* 447 */     if ((this.repreNombres != null) && (this.reprePaterno != null) && (this.repreMaterno != null)) {
/* 448 */       return this.repreNombres + " " + this.reprePaterno + " " + this.repreMaterno;
/*     */     }
/* 450 */     return "";
/*     */   }
/*     */   
/*     */   public void setNombreCompleto(String nombreCompleto)
/*     */   {
/* 455 */     this.nombreCompleto = nombreCompleto;
/*     */   }
/*     */   
/*     */   public List<Regalia> getRegaliaList() {
/* 459 */     return this.regaliaList;
/*     */   }
/*     */   
/*     */   public void setRegaliaList(List<Regalia> regaliaList) {
/* 463 */     this.regaliaList = regaliaList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 468 */     int hash = 0;
/* 469 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 470 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 476 */     if (!(object instanceof Cooperativa)) {
/* 477 */       return false;
/*     */     }
/* 479 */     Cooperativa other = (Cooperativa)object;
/* 480 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 481 */       return false;
/*     */     }
/* 483 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 488 */     return "org.erp.entities.Cooperativa[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Cooperativa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */