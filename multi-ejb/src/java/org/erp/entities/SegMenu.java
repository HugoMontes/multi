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
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
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
/*     */ @Entity
/*     */ @Table(name="seg_menu")
/*     */ @NamedQueries({@javax.persistence.NamedQuery(name="SegMenu.findAll", query="SELECT s FROM SegMenu s"), @javax.persistence.NamedQuery(name="SegMenu.findById", query="SELECT s FROM SegMenu s WHERE s.id = :id"), @javax.persistence.NamedQuery(name="SegMenu.findByNombre", query="SELECT s FROM SegMenu s WHERE s.nombre = :nombre"), @javax.persistence.NamedQuery(name="SegMenu.findByDescripcion", query="SELECT s FROM SegMenu s WHERE s.descripcion = :descripcion"), @javax.persistence.NamedQuery(name="SegMenu.findByEstado", query="SELECT s FROM SegMenu s WHERE s.estado = :estado")})
/*     */ public class SegMenu
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
/*     */   @Column(name="identificador", length=255)
/*     */   private String identificador;
/*     */   @Column(name="nivel")
/*     */   private Integer nivel;
/*     */   @Size(max=255)
/*     */   @Column(name="url", length=255)
/*     */   private String url;
/*     */   @Basic(optional=false)
/*     */   @NotNull
/*     */   @Size(min=1, max=3)
/*     */   @Column(name="estado", nullable=false, length=3)
/*     */   private String estado;
/*     */   @Column(name="tipo")
/*     */   private Integer tipo;
/*     */   @OneToMany(mappedBy="idMenu")
/*     */   private List<SegMenurol> segMenurolList;
/*     */   @OneToMany(mappedBy="idMenu")
/*     */   private List<SegMenu> segMenuList;
/*     */   @JoinColumn(name="id_menu", referencedColumnName="id")
/*     */   @ManyToOne
/*     */   private SegMenu idMenu;
/*     */   
/*     */   public SegMenu() {}
/*     */   
/*     */   public SegMenu(Integer id)
/*     */   {
/*  72 */     this.id = id;
/*     */   }
/*     */   
/*     */   public SegMenu(Integer id, String estado) {
/*  76 */     this.id = id;
/*  77 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  81 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  85 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/*  89 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/*  93 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/*  97 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/* 101 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/* 105 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/* 109 */     this.url = url;
/*     */   }
/*     */   
/*     */   public String getIdentificador() {
/* 113 */     return this.identificador;
/*     */   }
/*     */   
/*     */   public void setIdentificador(String identificador) {
/* 117 */     this.identificador = identificador;
/*     */   }
/*     */   
/*     */   public Integer getNivel() {
/* 121 */     return this.nivel;
/*     */   }
/*     */   
/*     */   public void setNivel(Integer nivel) {
/* 125 */     this.nivel = nivel;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 129 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 133 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public Integer getTipo() {
/* 137 */     return this.tipo;
/*     */   }
/*     */   
/*     */   public void setTipo(Integer tipo) {
/* 141 */     this.tipo = tipo;
/*     */   }
/*     */   
/*     */   public List<SegMenurol> getSegMenurolList() {
/* 145 */     return this.segMenurolList;
/*     */   }
/*     */   
/*     */   public void setSegMenurolList(List<SegMenurol> segMenurolList) {
/* 149 */     this.segMenurolList = segMenurolList;
/*     */   }
/*     */   
/*     */   public List<SegMenu> getSegMenuList() {
/* 153 */     return this.segMenuList;
/*     */   }
/*     */   
/*     */   public void setSegMenuList(List<SegMenu> segMenuList) {
/* 157 */     this.segMenuList = segMenuList;
/*     */   }
/*     */   
/*     */   public SegMenu getIdMenu() {
/* 161 */     return this.idMenu;
/*     */   }
/*     */   
/*     */   public void setIdMenu(SegMenu idMenu) {
/* 165 */     this.idMenu = idMenu;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 170 */     int hash = 0;
/* 171 */     hash += (this.id != null ? this.id.hashCode() : 0);
/* 172 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 178 */     if (!(object instanceof SegMenu)) {
/* 179 */       return false;
/*     */     }
/* 181 */     SegMenu other = (SegMenu)object;
/* 182 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 183 */       return false;
/*     */     }
/* 185 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 190 */     return "org.erp.entities.SegMenu[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\SegMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */