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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="regalia")
/*     */ public class Regalia
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
/*     */   @Column(name="ind_estado_formulario")
/*     */   private Integer indEstadoFormulario;
/*     */   @Column(name="ind_estado_pago")
/*     */   private Integer indEstadoPago;
/*     */   @Column(name="ind_tipo_formulario")
/*     */   private Integer indTipoFormulario;
/*     */   @Column(name="fecha_emision")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaEmision;
/*     */   @Column(precision=10, scale=2)
/*     */   private BigDecimal tc;
/*     */   @Column(name="age_fecha_transaccion")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date ageFechaTransaccion;
/*     */   @Column(name="age_razon_social", length=255)
/*     */   private String ageRazonSocial;
/*     */   @Column(name="age_nim_ci", length=255)
/*     */   private String ageNimCi;
/*     */   @Column(name="age_localidad", length=255)
/*     */   private String ageLocalidad;
/*     */   @Column(name="age_ind_laboratorio")
/*     */   private Integer ageIndLaboratorio;
/*     */   @Column(name="age_certificado_analisis", length=255)
/*     */   private String ageCertificadoAnalisis;
/*     */   @Column(name="exp_fecha_exportacion")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date expFechaExportacion;
/*     */   @Column(name="exp_razon_social_comprador", length=255)
/*     */   private String expRazonSocialComprador;
/*     */   @Column(name="exp_ind_aduana_salida")
/*     */   private Integer expIndAduanaSalida;
/*     */   @Column(name="exp_ind_laboratorio")
/*     */   private Integer expIndLaboratorio;
/*     */   @Column(name="exp_cetificado_analisis", length=255)
/*     */   private String expCetificadoAnalisis;
/*     */   @Column(name="exp_lote", length=255)
/*     */   private String expLote;
/*     */   @Column(name="exp_fecha_declaracion")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date expFechaDeclaracion;
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
/*     */   @JoinColumn(name="id_cooperativa", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Cooperativa idCooperativa;
/*     */   @JoinColumn(name="id_departamento", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Departamento idDepartamento;
/*     */   @JoinColumn(name="age_id_municipio", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private Municipio ageIdMunicipio;
/*     */   @JoinColumn(name="exp_id_pais_destino", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private Pais expIdPaisDestino;
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="idRegalia")
/*     */   private List<Liquidacion> liquidacionList;
/*     */   
/*     */   public Regalia() {}
/*     */   
/*     */   public Regalia(Integer id)
/*     */   {
/* 123 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Regalia(Integer id, String usuarioReg, Date fechaReg, String estado) {
/* 127 */     this.id = id;
/* 128 */     this.usuarioReg = usuarioReg;
/* 129 */     this.fechaReg = fechaReg;
/* 130 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 134 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 138 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNumeroOrden() {
/* 142 */     return this.numeroOrden;
/*     */   }
/*     */   
/*     */   public void setNumeroOrden(String numeroOrden) {
/* 146 */     this.numeroOrden = numeroOrden;
/*     */   }
/*     */   
/*     */   public Integer getIndEstadoFormulario() {
/* 150 */     return this.indEstadoFormulario;
/*     */   }
/*     */   
/*     */   public void setIndEstadoFormulario(Integer indEstadoFormulario) {
/* 154 */     this.indEstadoFormulario = indEstadoFormulario;
/*     */   }
/*     */   
/*     */   public Integer getIndEstadoPago() {
/* 158 */     return this.indEstadoPago;
/*     */   }
/*     */   
/*     */   public void setIndEstadoPago(Integer indEstadoPago) {
/* 162 */     this.indEstadoPago = indEstadoPago;
/*     */   }
/*     */   
/*     */   public Integer getIndTipoFormulario() {
/* 166 */     return this.indTipoFormulario;
/*     */   }
/*     */   
/*     */   public void setIndTipoFormulario(Integer indTipoFormulario) {
/* 170 */     this.indTipoFormulario = indTipoFormulario;
/*     */   }
/*     */   
/*     */   public Date getFechaEmision() {
/* 174 */     return this.fechaEmision;
/*     */   }
/*     */   
/*     */   public void setFechaEmision(Date fechaEmision) {
/* 178 */     this.fechaEmision = fechaEmision;
/*     */   }
/*     */   
/*     */   public BigDecimal getTc() {
/* 182 */     return this.tc;
/*     */   }
/*     */   
/*     */   public void setTc(BigDecimal tc) {
/* 186 */     this.tc = tc;
/*     */   }
/*     */   
/*     */   public Date getAgeFechaTransaccion() {
/* 190 */     return this.ageFechaTransaccion;
/*     */   }
/*     */   
/*     */   public void setAgeFechaTransaccion(Date ageFechaTransaccion) {
/* 194 */     this.ageFechaTransaccion = ageFechaTransaccion;
/*     */   }
/*     */   
/*     */   public String getAgeRazonSocial() {
/* 198 */     return this.ageRazonSocial;
/*     */   }
/*     */   
/*     */   public void setAgeRazonSocial(String ageRazonSocial) {
/* 202 */     this.ageRazonSocial = ageRazonSocial;
/*     */   }
/*     */   
/*     */   public String getAgeNimCi() {
/* 206 */     return this.ageNimCi;
/*     */   }
/*     */   
/*     */   public void setAgeNimCi(String ageNimCi) {
/* 210 */     this.ageNimCi = ageNimCi;
/*     */   }
/*     */   
/*     */   public String getAgeLocalidad() {
/* 214 */     return this.ageLocalidad;
/*     */   }
/*     */   
/*     */   public void setAgeLocalidad(String ageLocalidad) {
/* 218 */     this.ageLocalidad = ageLocalidad;
/*     */   }
/*     */   
/*     */   public Integer getAgeIndLaboratorio() {
/* 222 */     return this.ageIndLaboratorio;
/*     */   }
/*     */   
/*     */   public void setAgeIndLaboratorio(Integer ageIndLaboratorio) {
/* 226 */     this.ageIndLaboratorio = ageIndLaboratorio;
/*     */   }
/*     */   
/*     */   public String getAgeCertificadoAnalisis() {
/* 230 */     return this.ageCertificadoAnalisis;
/*     */   }
/*     */   
/*     */   public void setAgeCertificadoAnalisis(String ageCertificadoAnalisis) {
/* 234 */     this.ageCertificadoAnalisis = ageCertificadoAnalisis;
/*     */   }
/*     */   
/*     */   public Date getExpFechaExportacion() {
/* 238 */     return this.expFechaExportacion;
/*     */   }
/*     */   
/*     */   public void setExpFechaExportacion(Date expFechaExportacion) {
/* 242 */     this.expFechaExportacion = expFechaExportacion;
/*     */   }
/*     */   
/*     */   public String getExpRazonSocialComprador() {
/* 246 */     return this.expRazonSocialComprador;
/*     */   }
/*     */   
/*     */   public void setExpRazonSocialComprador(String expRazonSocialComprador) {
/* 250 */     this.expRazonSocialComprador = expRazonSocialComprador;
/*     */   }
/*     */   
/*     */   public Integer getExpIndAduanaSalida() {
/* 254 */     return this.expIndAduanaSalida;
/*     */   }
/*     */   
/*     */   public void setExpIndAduanaSalida(Integer expIndAduanaSalida) {
/* 258 */     this.expIndAduanaSalida = expIndAduanaSalida;
/*     */   }
/*     */   
/*     */   public Integer getExpIndLaboratorio() {
/* 262 */     return this.expIndLaboratorio;
/*     */   }
/*     */   
/*     */   public void setExpIndLaboratorio(Integer expIndLaboratorio) {
/* 266 */     this.expIndLaboratorio = expIndLaboratorio;
/*     */   }
/*     */   
/*     */   public String getExpCetificadoAnalisis() {
/* 270 */     return this.expCetificadoAnalisis;
/*     */   }
/*     */   
/*     */   public void setExpCetificadoAnalisis(String expCetificadoAnalisis) {
/* 274 */     this.expCetificadoAnalisis = expCetificadoAnalisis;
/*     */   }
/*     */   
/*     */   public String getExpLote() {
/* 278 */     return this.expLote;
/*     */   }
/*     */   
/*     */   public void setExpLote(String expLote) {
/* 282 */     this.expLote = expLote;
/*     */   }
/*     */   
/*     */   public Date getExpFechaDeclaracion() {
/* 286 */     return this.expFechaDeclaracion;
/*     */   }
/*     */   
/*     */   public void setExpFechaDeclaracion(Date expFechaDeclaracion) {
/* 290 */     this.expFechaDeclaracion = expFechaDeclaracion;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 294 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 298 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 302 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 306 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 310 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 314 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 318 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 322 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 326 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 330 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public Departamento getIdDepartamento() {
/* 334 */     return this.idDepartamento;
/*     */   }
/*     */   
/*     */   public void setIdDepartamento(Departamento idDepartamento) {
/* 338 */     this.idDepartamento = idDepartamento;
/*     */   }
/*     */   
/*     */   public Municipio getAgeIdMunicipio() {
/* 342 */     return this.ageIdMunicipio;
/*     */   }
/*     */   
/*     */   public void setAgeIdMunicipio(Municipio ageIdMunicipio) {
/* 346 */     this.ageIdMunicipio = ageIdMunicipio;
/*     */   }
/*     */   
/*     */   public Pais getExpIdPaisDestino() {
/* 350 */     return this.expIdPaisDestino;
/*     */   }
/*     */   
/*     */   public void setExpIdPaisDestino(Pais expIdPaisDestino) {
/* 354 */     this.expIdPaisDestino = expIdPaisDestino;
/*     */   }
/*     */   
/*     */   public List<Liquidacion> getLiquidacionList() {
/* 358 */     return this.liquidacionList;
/*     */   }
/*     */   
/*     */   public void setLiquidacionList(List<Liquidacion> liquidacionList) {
/* 362 */     this.liquidacionList = liquidacionList;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 367 */     int hash = 0;
/* 368 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 369 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 375 */     if (!(object instanceof Regalia)) {
/* 376 */       return false;
/*     */     }
/* 378 */     Regalia other = (Regalia)object;
/* 379 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 380 */       return false;
/*     */     }
/* 382 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 387 */     return "org.erp.entities.Regalia[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\Regalia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */