/*    */ package org.erp.entities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.NamedQuery;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Temporal;
/*    */ import javax.persistence.TemporalType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name="seg_acceso")
/*    */ @NamedQuery(name="SegAcceso.findAll", query="SELECT s FROM SegAcceso s")
/*    */ public class SegAcceso
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*    */   @Basic(optional=false)
/*    */   @Column(name="id", nullable=false)
/*    */   private Integer id;
/*    */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="fecha")
/*    */   private Date fecha;
/*    */   @Column(name="ip")
/*    */   private String ip;
/*    */   @Column(name="usuario")
/*    */   private String usuario;
/*    */   
/*    */   public int getId()
/*    */   {
/* 41 */     return this.id.intValue();
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 45 */     this.id = Integer.valueOf(id);
/*    */   }
/*    */   
/*    */   public Date getFecha() {
/* 49 */     return this.fecha;
/*    */   }
/*    */   
/*    */   public void setFecha(Date fecha) {
/* 53 */     this.fecha = fecha;
/*    */   }
/*    */   
/*    */   public String getIp() {
/* 57 */     return this.ip;
/*    */   }
/*    */   
/*    */   public void setIp(String ip) {
/* 61 */     this.ip = ip;
/*    */   }
/*    */   
/*    */   public String getUsuario() {
/* 65 */     return this.usuario;
/*    */   }
/*    */   
/*    */   public void setUsuario(String usuario) {
/* 69 */     this.usuario = usuario;
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\entities\SegAcceso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */