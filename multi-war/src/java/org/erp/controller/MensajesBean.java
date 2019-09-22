/*    */ package org.erp.controller;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.annotation.PostConstruct;
/*    */ import javax.faces.bean.ManagedBean;
/*    */ import javax.faces.bean.SessionScoped;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ManagedBean(name="mB")
/*    */ @SessionScoped
/*    */ public class MensajesBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Map<String, String> get;
/*    */   
/*    */   @PostConstruct
/*    */   public void inicio()
/*    */   {
/* 38 */     cargarDatos();
/*    */   }
/*    */   
/*    */   private void cargarDatos()
/*    */   {
/* 43 */     this.get = new HashMap();
/*    */     
/* 45 */     this.get.put("MSG_ELIMINACION", "¿Esta seguro de eliminar el registro seleccionado?");
/*    */   }
/*    */   
/*    */   public Map<String, String> getGet() {
/* 49 */     return this.get;
/*    */   }
/*    */   
/*    */   public void setGet(Map<String, String> get) {
/* 53 */     this.get = get;
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\controller\MensajesBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */