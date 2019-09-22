/*    */ package org.erp.to;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ 
/*    */ public class OrdenCompraTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer idProducto;
/*    */   private BigDecimal inventarioAlmacen;
/*    */   private BigDecimal InventarioBarras;
/*    */   private BigDecimal cantidadPedido;
/*    */   private BigDecimal ultimoCostoCompra;
/*    */   
/*    */   public Integer getIdProducto()
/*    */   {
/* 19 */     return this.idProducto;
/*    */   }
/*    */   
/* 22 */   public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
/*    */   
/*    */   public BigDecimal getInventarioAlmacen() {
/* 25 */     return this.inventarioAlmacen;
/*    */   }
/*    */   
/* 28 */   public void setInventarioAlmacen(BigDecimal inventarioAlmacen) { this.inventarioAlmacen = inventarioAlmacen; }
/*    */   
/*    */   public BigDecimal getInventarioBarras() {
/* 31 */     return this.InventarioBarras;
/*    */   }
/*    */   
/* 34 */   public void setInventarioBarras(BigDecimal inventarioBarras) { this.InventarioBarras = inventarioBarras; }
/*    */   
/*    */   public BigDecimal getCantidadPedido() {
/* 37 */     return this.cantidadPedido;
/*    */   }
/*    */   
/* 40 */   public void setCantidadPedido(BigDecimal cantidadPedido) { this.cantidadPedido = cantidadPedido; }
/*    */   
/*    */   public BigDecimal getUltimoCostoCompra() {
/* 43 */     return this.ultimoCostoCompra;
/*    */   }
/*    */   
/* 46 */   public void setUltimoCostoCompra(BigDecimal ultimoCostoCompra) { this.ultimoCostoCompra = ultimoCostoCompra; }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\to\OrdenCompraTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */