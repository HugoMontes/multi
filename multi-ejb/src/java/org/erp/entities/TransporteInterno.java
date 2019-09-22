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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="transporte_interno")
/*     */ public class TransporteInterno
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Basic(optional=false)
/*     */   @Column(name="id", nullable=false)
/*     */   private Integer id;
/*     */   @Column(name="fecha_emision")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaEmision;
/*     */   @Column(name="fecha_vencimiento")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaVencimiento;
/*     */   @Size(max=255)
/*     */   @Column(name="org_municipio_productor", length=255)
/*     */   private String orgMunicipioProductor;
/*     */   @Size(max=255)
/*     */   @Column(name="org_centro_minero_localidad", length=255)
/*     */   private String orgCentroMineroLocalidad;
/*     */   @Size(max=255)
/*     */   @Column(name="org_denominacion_area", length=255)
/*     */   private String orgDenominacionArea;
/*     */   @Column(name="ind_emitido_licencia")
/*     */   private Integer indEmitidoLicencia;
/*     */   @Column(name="org_ind_tranca_salida")
/*     */   private Integer orgIndTrancaSalida;
/*     */   @Column(name="car_ind_presentacion")
/*     */   private Integer carIndPresentacion;
/*     */   @Size(max=255)
/*     */   @Column(name="car_cantidad_embalaje", length=255)
/*     */   private String carCantidadEmbalaje;
/*     */   @Size(max=255)
/*     */   @Column(name="car_lotes", length=255)
/*     */   private String carLotes;
/*     */   @Size(max=255)
/*     */   @Column(name="car_peso_bruto_kg", length=255)
/*     */   private String carPesoBrutoKg;
/*     */   @Size(max=255)
/*     */   @Column(name="car_peso_neto_kg", length=255)
/*     */   private String carPesoNetoKg;
/*     */   @Size(max=255)
/*     */   @Column(name="car_mineral_uno", length=255)
/*     */   private String carMineralUno;
/*     */   @Size(max=255)
/*     */   @Column(name="car_mineral_dos", length=255)
/*     */   private String carMineralDos;
/*     */   @Size(max=255)
/*     */   @Column(name="car_mineral_tres", length=255)
/*     */   private String carMineralTres;
/*     */   @Column(name="car_ind_ley")
/*     */   private Integer carIndLey;
/*     */   @Size(max=255)
/*     */   @Column(name="car_ley_dm_uno", length=255)
/*     */   private String carLeyDmUno;
/*     */   @Size(max=255)
/*     */   @Column(name="car_ley_dm_dos", length=255)
/*     */   private String carLeyDmDos;
/*     */   @Size(max=255)
/*     */   @Column(name="comercializadora", length=255)
/*     */   private String comercializadora;
/*     */   @Size(max=255)
/*     */   @Column(name="con_nombre_conductor", length=255)
/*     */   private String conNombreConductor;
/*     */   @Size(max=255)
/*     */   @Column(name="con_licencia_conductor", length=255)
/*     */   private String conLicenciaConductor;
/*     */   @Size(max=255)
/*     */   @Column(name="con_placa_control", length=255)
/*     */   private String conPlacaControl;
/*     */   @Size(max=255)
/*     */   @Column(name="con_caracteristica_vehiculo", length=255)
/*     */   private String conCaracteristicaVehiculo;
/*     */   @Column(name="ind_estado_registro")
/*     */   private Integer indEstadoRegistro;
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
/*     */   @JoinColumn(name="id_departamento", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Departamento idDepartamento;
/*     */   @JoinColumn(name="id_cooperativa", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Cooperativa idCooperativa;
/*     */   @JoinColumn(name="id_ciudad", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Ciudad idCiudad;
/*     */   @Size(max=255)
/*     */   @Column(name="qr", length=255)
/*     */   private String qr;
/*     */   @Size(max=255)
/*     */   @Column(name="emitido_por", length=255)
/*     */   private String emitidoPor;
/*     */   @Size(max=255)
/*     */   @Column(name="emitido_cargo", length=255)
/*     */   private String emitidoCargo;
/*     */   @Size(max=255)
/*     */   @Column(name="emitido_ci", length=255)
/*     */   private String emitidoCi;
/*     */   
/*     */   public TransporteInterno() {}
/*     */   
/*     */   public TransporteInterno(Integer id)
/*     */   {
/* 148 */     this.id = id;
/*     */   }
/*     */   
/*     */   public TransporteInterno(Integer id, String usuarioReg, String estado) {
/* 152 */     this.id = id;
/* 153 */     this.usuarioReg = usuarioReg;
/* 154 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 158 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 162 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Date getFechaEmision() {
/* 166 */     return this.fechaEmision;
/*     */   }
/*     */   
/*     */   public void setFechaEmision(Date fechaEmision) {
/* 170 */     this.fechaEmision = fechaEmision;
/*     */   }
/*     */   
/*     */   public String getOrgMunicipioProductor() {
/* 174 */     return this.orgMunicipioProductor;
/*     */   }
/*     */   
/*     */   public void setOrgMunicipioProductor(String orgMunicipioProductor) {
/* 178 */     this.orgMunicipioProductor = orgMunicipioProductor;
/*     */   }
/*     */   
/*     */   public String getOrgCentroMineroLocalidad() {
/* 182 */     return this.orgCentroMineroLocalidad;
/*     */   }
/*     */   
/*     */   public void setOrgCentroMineroLocalidad(String orgCentroMineroLocalidad) {
/* 186 */     this.orgCentroMineroLocalidad = orgCentroMineroLocalidad;
/*     */   }
/*     */   
/*     */   public String getOrgDenominacionArea() {
/* 190 */     return this.orgDenominacionArea;
/*     */   }
/*     */   
/*     */   public void setOrgDenominacionArea(String orgDenominacionArea) {
/* 194 */     this.orgDenominacionArea = orgDenominacionArea;
/*     */   }
/*     */   
/*     */   public Integer getOrgIndTrancaSalida() {
/* 198 */     return this.orgIndTrancaSalida;
/*     */   }
/*     */   
/*     */   public void setOrgIndTrancaSalida(Integer orgIndTrancaSalida) {
/* 202 */     this.orgIndTrancaSalida = orgIndTrancaSalida;
/*     */   }
/*     */   
/*     */   public Integer getCarIndPresentacion() {
/* 206 */     return this.carIndPresentacion;
/*     */   }
/*     */   
/*     */   public void setCarIndPresentacion(Integer carIndPresentacion) {
/* 210 */     this.carIndPresentacion = carIndPresentacion;
/*     */   }
/*     */   
/*     */   public String getCarCantidadEmbalaje() {
/* 214 */     return this.carCantidadEmbalaje;
/*     */   }
/*     */   
/*     */   public void setCarCantidadEmbalaje(String carCantidadEmbalaje) {
/* 218 */     this.carCantidadEmbalaje = carCantidadEmbalaje;
/*     */   }
/*     */   
/*     */   public String getCarLotes() {
/* 222 */     return this.carLotes;
/*     */   }
/*     */   
/*     */   public void setCarLotes(String carLotes) {
/* 226 */     this.carLotes = carLotes;
/*     */   }
/*     */   
/*     */   public String getCarPesoBrutoKg() {
/* 230 */     return this.carPesoBrutoKg;
/*     */   }
/*     */   
/*     */   public void setCarPesoBrutoKg(String carPesoBrutoKg) {
/* 234 */     this.carPesoBrutoKg = carPesoBrutoKg;
/*     */   }
/*     */   
/*     */   public String getCarPesoNetoKg() {
/* 238 */     return this.carPesoNetoKg;
/*     */   }
/*     */   
/*     */   public void setCarPesoNetoKg(String carPesoNetoKg) {
/* 242 */     this.carPesoNetoKg = carPesoNetoKg;
/*     */   }
/*     */   
/*     */   public String getCarMineralUno() {
/* 246 */     return this.carMineralUno;
/*     */   }
/*     */   
/*     */   public void setCarMineralUno(String carMineralUno) {
/* 250 */     this.carMineralUno = carMineralUno;
/*     */   }
/*     */   
/*     */   public String getCarMineralDos() {
/* 254 */     return this.carMineralDos;
/*     */   }
/*     */   
/*     */   public void setCarMineralDos(String carMineralDos) {
/* 258 */     this.carMineralDos = carMineralDos;
/*     */   }
/*     */   
/*     */   public String getCarMineralTres() {
/* 262 */     return this.carMineralTres;
/*     */   }
/*     */   
/*     */   public void setCarMineralTres(String carMineralTres) {
/* 266 */     this.carMineralTres = carMineralTres;
/*     */   }
/*     */   
/*     */   public Integer getCarIndLey() {
/* 270 */     return this.carIndLey;
/*     */   }
/*     */   
/*     */   public void setCarIndLey(Integer carIndLey) {
/* 274 */     this.carIndLey = carIndLey;
/*     */   }
/*     */   
/*     */   public String getCarLeyDmUno() {
/* 278 */     return this.carLeyDmUno;
/*     */   }
/*     */   
/*     */   public void setCarLeyDmUno(String carLeyDmUno) {
/* 282 */     this.carLeyDmUno = carLeyDmUno;
/*     */   }
/*     */   
/*     */   public String getCarLeyDmDos() {
/* 286 */     return this.carLeyDmDos;
/*     */   }
/*     */   
/*     */   public void setCarLeyDmDos(String carLeyDmDos) {
/* 290 */     this.carLeyDmDos = carLeyDmDos;
/*     */   }
/*     */   
/*     */   public String getConNombreConductor() {
/* 294 */     return this.conNombreConductor;
/*     */   }
/*     */   
/*     */   public void setConNombreConductor(String conNombreConductor) {
/* 298 */     this.conNombreConductor = conNombreConductor;
/*     */   }
/*     */   
/*     */   public String getConLicenciaConductor() {
/* 302 */     return this.conLicenciaConductor;
/*     */   }
/*     */   
/*     */   public void setConLicenciaConductor(String conLicenciaConductor) {
/* 306 */     this.conLicenciaConductor = conLicenciaConductor;
/*     */   }
/*     */   
/*     */   public String getConPlacaControl() {
/* 310 */     return this.conPlacaControl;
/*     */   }
/*     */   
/*     */   public void setConPlacaControl(String conPlacaControl) {
/* 314 */     this.conPlacaControl = conPlacaControl;
/*     */   }
/*     */   
/*     */   public String getConCaracteristicaVehiculo() {
/* 318 */     return this.conCaracteristicaVehiculo;
/*     */   }
/*     */   
/*     */   public void setConCaracteristicaVehiculo(String conCaracteristicaVehiculo) {
/* 322 */     this.conCaracteristicaVehiculo = conCaracteristicaVehiculo;
/*     */   }
/*     */   
/*     */   public Integer getIndEstadoRegistro() {
/* 326 */     return this.indEstadoRegistro;
/*     */   }
/*     */   
/*     */   public void setIndEstadoRegistro(Integer indEstadoRegistro) {
/* 330 */     this.indEstadoRegistro = indEstadoRegistro;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 334 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 338 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 342 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 346 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 350 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 354 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 358 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 362 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Departamento getIdDepartamento() {
/* 366 */     return this.idDepartamento;
/*     */   }
/*     */   
/*     */   public void setIdDepartamento(Departamento idDepartamento) {
/* 370 */     this.idDepartamento = idDepartamento;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 374 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 378 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public Ciudad getIdCiudad() {
/* 382 */     return this.idCiudad;
/*     */   }
/*     */   
/*     */   public void setIdCiudad(Ciudad idCiudad) {
/* 386 */     this.idCiudad = idCiudad;
/*     */   }
/*     */   
/*     */   public String getQr()
/*     */   {
/* 391 */     return this.qr;
/*     */   }
/*     */   
/*     */   public void setQr(String qr) {
/* 395 */     this.qr = qr;
/*     */   }
/*     */   
/*     */   public String getComercializadora()
/*     */   {
/* 400 */     return this.comercializadora;
/*     */   }
/*     */   
/*     */   public void setComercializadora(String comercializadora) {
/* 404 */     this.comercializadora = comercializadora;
/*     */   }
/*     */   
/*     */   public String getEmitidoPor()
/*     */   {
/* 409 */     return this.emitidoPor;
/*     */   }
/*     */   
/*     */   public void setEmitidoPor(String emitidoPor) {
/* 413 */     this.emitidoPor = emitidoPor;
/*     */   }
/*     */   
/*     */   public String getEmitidoCargo() {
/* 417 */     return this.emitidoCargo;
/*     */   }
/*     */   
/*     */   public void setEmitidoCargo(String emitidoCargo) {
/* 421 */     this.emitidoCargo = emitidoCargo;
/*     */   }
/*     */   
/*     */   public String getEmitidoCi() {
/* 425 */     return this.emitidoCi;
/*     */   }
/*     */   
/*     */   public void setEmitidoCi(String emitidoCi) {
/* 429 */     this.emitidoCi = emitidoCi;
/*     */   }
/*     */   
/*     */   public Date getFechaVencimiento() {
/* 433 */     return this.fechaVencimiento;
/*     */   }
/*     */   
/*     */   public void setFechaVencimiento(Date fechaVencimiento) {
/* 437 */     this.fechaVencimiento = fechaVencimiento;
/*     */   }
/*     */   
/*     */   public Integer getIndEmitidoLicencia() {
/* 441 */     return this.indEmitidoLicencia;
/*     */   }
/*     */   
/*     */   public void setIndEmitidoLicencia(Integer indEmitidoLicencia) {
/* 445 */     this.indEmitidoLicencia = indEmitidoLicencia;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 450 */     int hash = 0;
/* 451 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 452 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 458 */     if (!(object instanceof TransporteInterno)) {
/* 459 */       return false;
/*     */     }
/* 461 */     TransporteInterno other = (TransporteInterno)object;
/* 462 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 463 */       return false;
/*     */     }
/* 465 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 470 */     return "org.erp.entities.TransporteInterno[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\TransporteInterno.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */