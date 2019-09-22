/*    */ package org.erp.to;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class RecepcionTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer idAlmacen;
/*    */   private Integer idBarra;
/*    */   private Date desde;
/*    */   private Date hasta;
/*    */   
/*    */   public Integer getIdAlmacen()
/*    */   {
/* 18 */     return this.idAlmacen;
/*    */   }
/*    */   
/* 21 */   public void setIdAlmacen(Integer idAlmacen) { this.idAlmacen = idAlmacen; }
/*    */   
/*    */   public Date getDesde() {
/* 24 */     return this.desde;
/*    */   }
/*    */   
/* 27 */   public void setDesde(Date desde) { this.desde = desde; }
/*    */   
/*    */   public Date getHasta() {
/* 30 */     return this.hasta;
/*    */   }
/*    */   
/* 33 */   public void setHasta(Date hasta) { this.hasta = hasta; }
/*    */   
/*    */   public Integer getIdBarra() {
/* 36 */     return this.idBarra;
/*    */   }
/*    */   
/* 39 */   public void setIdBarra(Integer idBarra) { this.idBarra = idBarra; }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\to\RecepcionTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */