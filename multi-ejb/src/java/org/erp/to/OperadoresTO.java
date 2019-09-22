/*    */ package org.erp.to;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OperadoresTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Date desde;
/*    */   private Date hasta;
/*    */   private Integer indActorProductivo;
/*    */   private Integer indTipoTransporte;
/*    */   private Integer idCooperativa;
/*    */   
/*    */   public Date getDesde()
/*    */   {
/* 20 */     return this.desde;
/*    */   }
/*    */   
/* 23 */   public void setDesde(Date desde) { this.desde = desde; }
/*    */   
/*    */   public Date getHasta() {
/* 26 */     return this.hasta;
/*    */   }
/*    */   
/* 29 */   public void setHasta(Date hasta) { this.hasta = hasta; }
/*    */   
/*    */   public Integer getIndActorProductivo() {
/* 32 */     return this.indActorProductivo;
/*    */   }
/*    */   
/* 35 */   public void setIndActorProductivo(Integer indActorProductivo) { this.indActorProductivo = indActorProductivo; }
/*    */   
/*    */   public Integer getIndTipoTransporte() {
/* 38 */     return this.indTipoTransporte;
/*    */   }
/*    */   
/* 41 */   public void setIndTipoTransporte(Integer indTipoTransporte) { this.indTipoTransporte = indTipoTransporte; }
/*    */   
/*    */   public Integer getIdCooperativa() {
/* 44 */     return this.idCooperativa;
/*    */   }
/*    */   
/* 47 */   public void setIdCooperativa(Integer idCooperativa) { this.idCooperativa = idCooperativa; }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\to\OperadoresTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */