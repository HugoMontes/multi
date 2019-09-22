/*    */ package org.erp.util;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.ejb.LocalBean;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.internal.SessionFactoryImpl;
/*    */ import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
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
/*    */ @Stateless
/*    */ @LocalBean
/*    */ public class ConexionReport
/*    */   implements ConeccionReportLocal
/*    */ {
/*    */   @PersistenceContext(unitName="admindbDS")
/*    */   EntityManager em;
/*    */   
/*    */   public Connection getSesionSistema()
/*    */   {
/* 52 */     Session session = (Session)this.em.getDelegate();
/* 53 */     SessionFactoryImpl sessionFactory = (SessionFactoryImpl)session.getSessionFactory();
/*    */     
/*    */     try
/*    */     {
/* 57 */       return sessionFactory.getConnectionProvider().getConnection();
/*    */     }
/*    */     catch (SQLException e)
/*    */     {
/* 61 */       e.printStackTrace(); }
/* 62 */     return null;
/*    */   }
/*    */ }