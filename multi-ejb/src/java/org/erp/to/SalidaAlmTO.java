/*    */ package org.erp.to;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class SalidaAlmTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer tipoSalida;
/*    */   private Integer idAlmacen;
/*    */   private Integer idBarra;
/*    */   private Integer idProveedor;
/*    */   private Integer idArea;
/*    */   private Date desde;
/*    */   private Date hasta;
/*    */   
/*    */   public Integer getTipoSalida()
/*    */   {
/* 21 */     return this.tipoSalida;
/*    */   }
/*    */   
/* 24 */   public void setTipoSalida(Integer tipoSalida) { this.tipoSalida = tipoSalida; }
/*    */   
/*    */   public Integer getIdBarra() {
/* 27 */     return this.idBarra;
/*    */   }
/*    */   
/* 30 */   public void setIdBarra(Integer idBarra) { this.idBarra = idBarra; }
/*    */   
/*    */   public Integer getIdProveedor() {
/* 33 */     return this.idProveedor;
/*    */   }
/*    */   
/* 36 */   public void setIdProveedor(Integer idProveedor) { this.idProveedor = idProveedor; }
/*    */   
/*    */   public Integer getIdArea() {
/* 39 */     return this.idArea;
/*    */   }
/*    */   
/* 42 */   public void setIdArea(Integer idArea) { this.idArea = idArea; }
/*    */   
/*    */   public Date getDesde() {
/* 45 */     return this.desde;
/*    */   }
/*    */   
/* 48 */   public void setDesde(Date desde) { this.desde = desde; }
/*    */   
/*    */   public Date getHasta() {
/* 51 */     return this.hasta;
/*    */   }
/*    */   
/* 54 */   public void setHasta(Date hasta) { this.hasta = hasta; }
/*    */   
/*    */   public Integer getIdAlmacen() {
/* 57 */     return this.idAlmacen;
/*    */   }
/*    */   
/* 60 */   public void setIdAlmacen(Integer idAlmacen) { this.idAlmacen = idAlmacen; }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\to\SalidaAlmTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */