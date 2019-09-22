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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="transporte_externo")
/*     */ public class TransporteExterno
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
/*     */   @Column(name="ind_emitido_ci")
/*     */   private Integer indEmitidoCi;
/*     */   @Column(name="ind_tipo_laboratorio")
/*     */   private Integer indTipoLaboratorio;
/*     */   @Column(name="ind_aduana_salida")
/*     */   private Integer indAduanaSalida;
/*     */   @Column(name="ind_emitido_licencia")
/*     */   private Integer indEmitidoLicencia;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Column(name="fecha_exportacion", nullable=false)
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date fechaExportacion;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_razon_social", length=255)
/*     */   private String solRazonSocial;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_nit", length=255)
/*     */   private String solNit;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_nim", length=255)
/*     */   private String solNim;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_nombre_apellido", length=255)
/*     */   private String solNombreApellido;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_ci", length=255)
/*     */   private String solCi;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_laboratorio", length=255)
/*     */   private String solLaboratorio;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_nro_analisis", length=255)
/*     */   private String solNroAnalisis;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_factura_comercial", length=255)
/*     */   private String solFacturaComercial;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_id_m", length=255)
/*     */   private String solIdM;
/*     */   @Size(max=255)
/*     */   @Column(name="sol_nro_acta_verificacion", length=255)
/*     */   private String solNroActaVerificacion;
/*     */   @Column(name="car_ind_mineral_metal")
/*     */   private Integer carIndMineralMetal;
/*     */   @Column(name="car_ind_presentacion_prod")
/*     */   private Integer carIndPresentacionProd;
/*     */   @Size(max=255)
/*     */   @Column(name="car_lote", length=255)
/*     */   private String carLote;
/*     */   @Column(name="car_ind_export_total_parcial")
/*     */   private Integer carIndExportTotalParcial;
/*     */   @Size(max=255)
/*     */   @Column(name="car_cantidad_bultos", length=255)
/*     */   private String carCantidadBultos;
/*     */   @Column(name="car_ind_descripcion_mineral")
/*     */   private Integer carIndDescripcionMineral;
/*     */   @Size(max=255)
/*     */   @Column(name="car_peso_bruto_humedo", length=255)
/*     */   private String carPesoBrutoHumedo;
/*     */   @Size(max=255)
/*     */   @Column(name="car_lara", length=255)
/*     */   private String carLara;
/*     */   @Size(max=255)
/*     */   @Column(name="car_peso_neto_humedo", length=255)
/*     */   private String carPesoNetoHumedo;
/*     */   @Size(max=255)
/*     */   @Column(name="car_humedad", length=255)
/*     */   private String carHumedad;
/*     */   @Size(max=255)
/*     */   @Column(name="car_merma", length=255)
/*     */   private String carMerma;
/*     */   @Size(max=255)
/*     */   @Column(name="car_peso_neto_seco", length=255)
/*     */   private String carPesoNetoSeco;
/*     */   @Column(name="car_ind_ley_mineral")
/*     */   private Integer carIndLeyMineral;
/*     */   @Size(max=255)
/*     */   @Column(name="car_ley_campo_uno", length=255)
/*     */   private String carLeyCampoUno;
/*     */   @Size(max=255)
/*     */   @Column(name="car_ley_campo_dos", length=255)
/*     */   private String carLeyCampoDos;
/*     */   @Size(max=255)
/*     */   @Column(name="car_ley_campo_tres", length=255)
/*     */   private String carLeyCampoTres;
/*     */   @Size(max=255)
/*     */   @Column(name="org_codigo_prodcutor", length=255)
/*     */   private String orgCodigoProdcutor;
/*     */   @Size(max=255)
/*     */   @Column(name="dest_razon_social_comprador", length=255)
/*     */   private String destRazonSocialComprador;
/*     */   @Size(max=255)
/*     */   @Column(name="dest_aduana_salida", length=255)
/*     */   private String destAduanaSalida;
/*     */   @Size(max=255)
/*     */   @Column(name="dest_cod_aduana", length=255)
/*     */   private String destCodAduana;
/*     */   @Column(name="dest_ind_tipo_transporte")
/*     */   private Integer destIndTipoTransporte;
/*     */   @Size(max=255)
/*     */   @Column(name="cond_nombre_conductor", length=255)
/*     */   private String condNombreConductor;
/*     */   @Size(max=255)
/*     */   @Column(name="cond_licencia_conductor", length=255)
/*     */   private String condLicenciaConductor;
/*     */   @Size(max=255)
/*     */   @Column(name="cond_placa_control", length=255)
/*     */   private String condPlacaControl;
/*     */   @Size(max=255)
/*     */   @Column(name="cond_caracteristica_vehiculo", length=255)
/*     */   private String condCaracteristicaVehiculo;
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
/*     */   @JoinColumn(name="id_pais", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Pais idPais;
/*     */   @JoinColumn(name="id_municipio", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Municipio idMunicipio;
/*     */   @JoinColumn(name="id_departamento", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Departamento idDepartamento;
/*     */   @JoinColumn(name="id_cooperativa", referencedColumnName="id", nullable=false)
/*     */   @ManyToOne(optional=false)
/*     */   private Cooperativa idCooperativa;
/*     */   @Size(max=255)
/*     */   @Column(name="qr", length=255)
/*     */   private String qr;
/*     */   @Size(max=255)
/*     */   @Column(name="car_cantidad_total_parcial", length=255)
/*     */   private String carCantidadTotalParcial;
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
/*     */   public TransporteExterno() {}
/*     */   
/*     */   public TransporteExterno(Integer id)
/*     */   {
/* 205 */     this.id = id;
/*     */   }
/*     */   
/*     */   public TransporteExterno(Integer id, Date fechaEmision, Date fechaExportacion, String usuarioReg, String estado) {
/* 209 */     this.id = id;
/* 210 */     this.fechaEmision = fechaEmision;
/* 211 */     this.fechaExportacion = fechaExportacion;
/* 212 */     this.usuarioReg = usuarioReg;
/* 213 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 217 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 221 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Date getFechaEmision() {
/* 225 */     return this.fechaEmision;
/*     */   }
/*     */   
/*     */   public void setFechaEmision(Date fechaEmision) {
/* 229 */     this.fechaEmision = fechaEmision;
/*     */   }
/*     */   
/*     */   public Date getFechaExportacion() {
/* 233 */     return this.fechaExportacion;
/*     */   }
/*     */   
/*     */   public void setFechaExportacion(Date fechaExportacion) {
/* 237 */     this.fechaExportacion = fechaExportacion;
/*     */   }
/*     */   
/*     */   public String getSolRazonSocial() {
/* 241 */     return this.solRazonSocial;
/*     */   }
/*     */   
/*     */   public void setSolRazonSocial(String solRazonSocial) {
/* 245 */     this.solRazonSocial = solRazonSocial;
/*     */   }
/*     */   
/*     */   public String getSolNit() {
/* 249 */     return this.solNit;
/*     */   }
/*     */   
/*     */   public void setSolNit(String solNit) {
/* 253 */     this.solNit = solNit;
/*     */   }
/*     */   
/*     */   public String getSolNim() {
/* 257 */     return this.solNim;
/*     */   }
/*     */   
/*     */   public void setSolNim(String solNim) {
/* 261 */     this.solNim = solNim;
/*     */   }
/*     */   
/*     */   public String getSolNombreApellido() {
/* 265 */     return this.solNombreApellido;
/*     */   }
/*     */   
/*     */   public void setSolNombreApellido(String solNombreApellido) {
/* 269 */     this.solNombreApellido = solNombreApellido;
/*     */   }
/*     */   
/*     */   public String getSolCi() {
/* 273 */     return this.solCi;
/*     */   }
/*     */   
/*     */   public void setSolCi(String solCi) {
/* 277 */     this.solCi = solCi;
/*     */   }
/*     */   
/*     */   public String getSolLaboratorio() {
/* 281 */     return this.solLaboratorio;
/*     */   }
/*     */   
/*     */   public void setSolLaboratorio(String solLaboratorio) {
/* 285 */     this.solLaboratorio = solLaboratorio;
/*     */   }
/*     */   
/*     */   public String getSolNroAnalisis() {
/* 289 */     return this.solNroAnalisis;
/*     */   }
/*     */   
/*     */   public void setSolNroAnalisis(String solNroAnalisis) {
/* 293 */     this.solNroAnalisis = solNroAnalisis;
/*     */   }
/*     */   
/*     */   public String getSolFacturaComercial() {
/* 297 */     return this.solFacturaComercial;
/*     */   }
/*     */   
/*     */   public void setSolFacturaComercial(String solFacturaComercial) {
/* 301 */     this.solFacturaComercial = solFacturaComercial;
/*     */   }
/*     */   
/*     */   public String getSolIdM() {
/* 305 */     return this.solIdM;
/*     */   }
/*     */   
/*     */   public void setSolIdM(String solIdM) {
/* 309 */     this.solIdM = solIdM;
/*     */   }
/*     */   
/*     */   public String getSolNroActaVerificacion() {
/* 313 */     return this.solNroActaVerificacion;
/*     */   }
/*     */   
/*     */   public void setSolNroActaVerificacion(String solNroActaVerificacion) {
/* 317 */     this.solNroActaVerificacion = solNroActaVerificacion;
/*     */   }
/*     */   
/*     */   public Integer getCarIndMineralMetal() {
/* 321 */     return this.carIndMineralMetal;
/*     */   }
/*     */   
/*     */   public void setCarIndMineralMetal(Integer carIndMineralMetal) {
/* 325 */     this.carIndMineralMetal = carIndMineralMetal;
/*     */   }
/*     */   
/*     */   public Integer getCarIndPresentacionProd() {
/* 329 */     return this.carIndPresentacionProd;
/*     */   }
/*     */   
/*     */   public void setCarIndPresentacionProd(Integer carIndPresentacionProd) {
/* 333 */     this.carIndPresentacionProd = carIndPresentacionProd;
/*     */   }
/*     */   
/*     */   public String getCarLote() {
/* 337 */     return this.carLote;
/*     */   }
/*     */   
/*     */   public void setCarLote(String carLote) {
/* 341 */     this.carLote = carLote;
/*     */   }
/*     */   
/*     */   public Integer getCarIndExportTotalParcial() {
/* 345 */     return this.carIndExportTotalParcial;
/*     */   }
/*     */   
/*     */   public void setCarIndExportTotalParcial(Integer carIndExportTotalParcial) {
/* 349 */     this.carIndExportTotalParcial = carIndExportTotalParcial;
/*     */   }
/*     */   
/*     */   public String getCarCantidadBultos() {
/* 353 */     return this.carCantidadBultos;
/*     */   }
/*     */   
/*     */   public void setCarCantidadBultos(String carCantidadBultos) {
/* 357 */     this.carCantidadBultos = carCantidadBultos;
/*     */   }
/*     */   
/*     */   public Integer getCarIndDescripcionMineral() {
/* 361 */     return this.carIndDescripcionMineral;
/*     */   }
/*     */   
/*     */   public void setCarIndDescripcionMineral(Integer carIndDescripcionMineral) {
/* 365 */     this.carIndDescripcionMineral = carIndDescripcionMineral;
/*     */   }
/*     */   
/*     */   public String getCarPesoBrutoHumedo() {
/* 369 */     return this.carPesoBrutoHumedo;
/*     */   }
/*     */   
/*     */   public void setCarPesoBrutoHumedo(String carPesoBrutoHumedo) {
/* 373 */     this.carPesoBrutoHumedo = carPesoBrutoHumedo;
/*     */   }
/*     */   
/*     */   public String getCarLara() {
/* 377 */     return this.carLara;
/*     */   }
/*     */   
/*     */   public void setCarLara(String carLara) {
/* 381 */     this.carLara = carLara;
/*     */   }
/*     */   
/*     */   public String getCarPesoNetoHumedo() {
/* 385 */     return this.carPesoNetoHumedo;
/*     */   }
/*     */   
/*     */   public void setCarPesoNetoHumedo(String carPesoNetoHumedo) {
/* 389 */     this.carPesoNetoHumedo = carPesoNetoHumedo;
/*     */   }
/*     */   
/*     */   public String getCarHumedad() {
/* 393 */     return this.carHumedad;
/*     */   }
/*     */   
/*     */   public void setCarHumedad(String carHumedad) {
/* 397 */     this.carHumedad = carHumedad;
/*     */   }
/*     */   
/*     */   public String getCarMerma() {
/* 401 */     return this.carMerma;
/*     */   }
/*     */   
/*     */   public void setCarMerma(String carMerma) {
/* 405 */     this.carMerma = carMerma;
/*     */   }
/*     */   
/*     */   public String getCarPesoNetoSeco() {
/* 409 */     return this.carPesoNetoSeco;
/*     */   }
/*     */   
/*     */   public void setCarPesoNetoSeco(String carPesoNetoSeco) {
/* 413 */     this.carPesoNetoSeco = carPesoNetoSeco;
/*     */   }
/*     */   
/*     */   public Integer getCarIndLeyMineral() {
/* 417 */     return this.carIndLeyMineral;
/*     */   }
/*     */   
/*     */   public void setCarIndLeyMineral(Integer carIndLeyMineral) {
/* 421 */     this.carIndLeyMineral = carIndLeyMineral;
/*     */   }
/*     */   
/*     */   public String getCarLeyCampoUno() {
/* 425 */     return this.carLeyCampoUno;
/*     */   }
/*     */   
/*     */   public void setCarLeyCampoUno(String carLeyCampoUno) {
/* 429 */     this.carLeyCampoUno = carLeyCampoUno;
/*     */   }
/*     */   
/*     */   public String getCarLeyCampoDos() {
/* 433 */     return this.carLeyCampoDos;
/*     */   }
/*     */   
/*     */   public void setCarLeyCampoDos(String carLeyCampoDos) {
/* 437 */     this.carLeyCampoDos = carLeyCampoDos;
/*     */   }
/*     */   
/*     */   public String getCarLeyCampoTres() {
/* 441 */     return this.carLeyCampoTres;
/*     */   }
/*     */   
/*     */   public void setCarLeyCampoTres(String carLeyCampoTres) {
/* 445 */     this.carLeyCampoTres = carLeyCampoTres;
/*     */   }
/*     */   
/*     */   public String getOrgCodigoProdcutor() {
/* 449 */     return this.orgCodigoProdcutor;
/*     */   }
/*     */   
/*     */   public void setOrgCodigoProdcutor(String orgCodigoProdcutor) {
/* 453 */     this.orgCodigoProdcutor = orgCodigoProdcutor;
/*     */   }
/*     */   
/*     */   public String getDestRazonSocialComprador() {
/* 457 */     return this.destRazonSocialComprador;
/*     */   }
/*     */   
/*     */   public void setDestRazonSocialComprador(String destRazonSocialComprador) {
/* 461 */     this.destRazonSocialComprador = destRazonSocialComprador;
/*     */   }
/*     */   
/*     */   public String getDestAduanaSalida() {
/* 465 */     return this.destAduanaSalida;
/*     */   }
/*     */   
/*     */   public void setDestAduanaSalida(String destAduanaSalida) {
/* 469 */     this.destAduanaSalida = destAduanaSalida;
/*     */   }
/*     */   
/*     */   public String getDestCodAduana() {
/* 473 */     return this.destCodAduana;
/*     */   }
/*     */   
/*     */   public void setDestCodAduana(String destCodAduana) {
/* 477 */     this.destCodAduana = destCodAduana;
/*     */   }
/*     */   
/*     */   public Integer getDestIndTipoTransporte() {
/* 481 */     return this.destIndTipoTransporte;
/*     */   }
/*     */   
/*     */   public void setDestIndTipoTransporte(Integer destIndTipoTransporte) {
/* 485 */     this.destIndTipoTransporte = destIndTipoTransporte;
/*     */   }
/*     */   
/*     */   public String getCondNombreConductor() {
/* 489 */     return this.condNombreConductor;
/*     */   }
/*     */   
/*     */   public void setCondNombreConductor(String condNombreConductor) {
/* 493 */     this.condNombreConductor = condNombreConductor;
/*     */   }
/*     */   
/*     */   public String getCondLicenciaConductor() {
/* 497 */     return this.condLicenciaConductor;
/*     */   }
/*     */   
/*     */   public void setCondLicenciaConductor(String condLicenciaConductor) {
/* 501 */     this.condLicenciaConductor = condLicenciaConductor;
/*     */   }
/*     */   
/*     */   public String getCondPlacaControl() {
/* 505 */     return this.condPlacaControl;
/*     */   }
/*     */   
/*     */   public void setCondPlacaControl(String condPlacaControl) {
/* 509 */     this.condPlacaControl = condPlacaControl;
/*     */   }
/*     */   
/*     */   public String getCondCaracteristicaVehiculo() {
/* 513 */     return this.condCaracteristicaVehiculo;
/*     */   }
/*     */   
/*     */   public void setCondCaracteristicaVehiculo(String condCaracteristicaVehiculo) {
/* 517 */     this.condCaracteristicaVehiculo = condCaracteristicaVehiculo;
/*     */   }
/*     */   
/*     */   public Integer getIndEstadoRegistro() {
/* 521 */     return this.indEstadoRegistro;
/*     */   }
/*     */   
/*     */   public void setIndEstadoRegistro(Integer indEstadoRegistro) {
/* 525 */     this.indEstadoRegistro = indEstadoRegistro;
/*     */   }
/*     */   
/*     */   public String getUsuarioReg() {
/* 529 */     return this.usuarioReg;
/*     */   }
/*     */   
/*     */   public void setUsuarioReg(String usuarioReg) {
/* 533 */     this.usuarioReg = usuarioReg;
/*     */   }
/*     */   
/*     */   public Date getFechaReg() {
/* 537 */     return this.fechaReg;
/*     */   }
/*     */   
/*     */   public void setFechaReg(Date fechaReg) {
/* 541 */     this.fechaReg = fechaReg;
/*     */   }
/*     */   
/*     */   public Date getFechaMod() {
/* 545 */     return this.fechaMod;
/*     */   }
/*     */   
/*     */   public void setFechaMod(Date fechaMod) {
/* 549 */     this.fechaMod = fechaMod;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 553 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 557 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Pais getIdPais() {
/* 561 */     return this.idPais;
/*     */   }
/*     */   
/*     */   public void setIdPais(Pais idPais) {
/* 565 */     this.idPais = idPais;
/*     */   }
/*     */   
/*     */   public Municipio getIdMunicipio() {
/* 569 */     return this.idMunicipio;
/*     */   }
/*     */   
/*     */   public void setIdMunicipio(Municipio idMunicipio) {
/* 573 */     this.idMunicipio = idMunicipio;
/*     */   }
/*     */   
/*     */   public Departamento getIdDepartamento() {
/* 577 */     return this.idDepartamento;
/*     */   }
/*     */   
/*     */   public void setIdDepartamento(Departamento idDepartamento) {
/* 581 */     this.idDepartamento = idDepartamento;
/*     */   }
/*     */   
/*     */   public Cooperativa getIdCooperativa() {
/* 585 */     return this.idCooperativa;
/*     */   }
/*     */   
/*     */   public void setIdCooperativa(Cooperativa idCooperativa) {
/* 589 */     this.idCooperativa = idCooperativa;
/*     */   }
/*     */   
/*     */   public String getQr() {
/* 593 */     return this.qr;
/*     */   }
/*     */   
/*     */   public void setQr(String qr) {
/* 597 */     this.qr = qr;
/*     */   }
/*     */   
/*     */   public String getCarCantidadTotalParcial() {
/* 601 */     return this.carCantidadTotalParcial;
/*     */   }
/*     */   
/*     */   public void setCarCantidadTotalParcial(String carCantidadTotalParcial) {
/* 605 */     this.carCantidadTotalParcial = carCantidadTotalParcial;
/*     */   }
/*     */   
/*     */   public String getEmitidoPor()
/*     */   {
/* 610 */     return this.emitidoPor;
/*     */   }
/*     */   
/*     */   public void setEmitidoPor(String emitidoPor) {
/* 614 */     this.emitidoPor = emitidoPor;
/*     */   }
/*     */   
/*     */   public String getEmitidoCargo() {
/* 618 */     return this.emitidoCargo;
/*     */   }
/*     */   
/*     */   public void setEmitidoCargo(String emitidoCargo) {
/* 622 */     this.emitidoCargo = emitidoCargo;
/*     */   }
/*     */   
/*     */   public String getEmitidoCi() {
/* 626 */     return this.emitidoCi;
/*     */   }
/*     */   
/*     */   public void setEmitidoCi(String emitidoCi) {
/* 630 */     this.emitidoCi = emitidoCi;
/*     */   }
/*     */   
/*     */   public Integer getIndEmitidoCi() {
/* 634 */     return this.indEmitidoCi;
/*     */   }
/*     */   
/*     */   public void setIndEmitidoCi(Integer indEmitidoCi) {
/* 638 */     this.indEmitidoCi = indEmitidoCi;
/*     */   }
/*     */   
/*     */   public Integer getIndTipoLaboratorio() {
/* 642 */     return this.indTipoLaboratorio;
/*     */   }
/*     */   
/*     */   public void setIndTipoLaboratorio(Integer indTipoLaboratorio) {
/* 646 */     this.indTipoLaboratorio = indTipoLaboratorio;
/*     */   }
/*     */   
/*     */   public Integer getIndAduanaSalida() {
/* 650 */     return this.indAduanaSalida;
/*     */   }
/*     */   
/*     */   public void setIndAduanaSalida(Integer indAduanaSalida) {
/* 654 */     this.indAduanaSalida = indAduanaSalida;
/*     */   }
/*     */   
/*     */   public Integer getIndEmitidoLicencia() {
/* 658 */     return this.indEmitidoLicencia;
/*     */   }
/*     */   
/*     */   public void setIndEmitidoLicencia(Integer indEmitidoLicencia) {
/* 662 */     this.indEmitidoLicencia = indEmitidoLicencia;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 667 */     int hash = 0;
/* 668 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 669 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 675 */     if (!(object instanceof TransporteExterno)) {
/* 676 */       return false;
/*     */     }
/* 678 */     TransporteExterno other = (TransporteExterno)object;
/* 679 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 680 */       return false;
/*     */     }
/* 682 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 687 */     return "org.erp.entities.TransporteExterno[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\TransporteExterno.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */