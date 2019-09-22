/*     */ package org.erp.param;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.inject.Inject;
/*     */ import org.erp.entities.MasterTable;
/*     */ import org.erp.entities.ParameterTable;
/*     */ import org.erp.param.ML.ParametrosMLBeanLocal;
/*     */ import org.erp.param.QL.ParametrosQLBeanLocal;
/*     */ import org.erp.util.AdminException;
/*     */ import org.erp.util.JSFUtilities;
/*     */ import org.erp.util.ServiciosUtilBeanLocal;
/*     */ import org.erp.util.Util;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.Days;
/*     */ import org.primefaces.event.SelectEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name="parametrosBean")
/*     */ @ViewScoped
/*     */ public class ParametroBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Inject
/*     */   ServiciosUtilBeanLocal serviciosUtilBeanLocal;
/*     */   @Inject
/*     */   ParametrosMLBeanLocal parametrosMLBeanLocal;
/*     */   @Inject
/*     */   ParametrosQLBeanLocal parametrosQLBeanLocal;
/*     */   private String strTitulo;
/*     */   private boolean blnHabModificarMaster;
/*     */   private boolean blnHabEliminarMaster;
/*     */   private List<MasterTable> listaMasterTable;
/*     */   private MasterTable masterTable;
/*     */   private List<MasterTable> filtroListaMasterTable;
/*     */   private List<ParameterTable> listaParameterTable;
/*     */   private ParameterTable parameterTable;
/*     */   private boolean blnHabModificarParameter;
/*     */   private boolean blnHabEliminarParameter;
/*     */   private boolean blnHabRegistrarParameter;
/*     */   
/*     */   @PostConstruct
/*     */   public void inicio()
/*     */   {
/*     */     try
/*     */     {
/*     */       
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  85 */       e.printStackTrace();
/*     */     }
/*     */     
/*  88 */     this.listaMasterTable = new ArrayList();
/*  89 */     listarMaster();
/*  90 */     this.strTitulo = "";
/*  91 */     this.blnHabModificarMaster = true;
/*  92 */     this.blnHabEliminarMaster = true;
/*     */     
/*  94 */     this.listaParameterTable = new ArrayList();
/*  95 */     this.parameterTable = new ParameterTable();
/*     */     
/*  97 */     this.blnHabModificarParameter = true;
/*  98 */     this.blnHabEliminarParameter = true;
/*  99 */     this.blnHabRegistrarParameter = true;
/*     */   }
/*     */   
/*     */   public void listarMaster() {
/* 103 */     this.listaMasterTable = new ArrayList();
/* 104 */     this.listaParameterTable = new ArrayList();
/*     */     try {
/* 106 */       this.listaMasterTable = this.parametrosQLBeanLocal.listaMaster();
/* 107 */       this.blnHabModificarMaster = true;
/* 108 */       this.blnHabEliminarMaster = true;
/*     */       
/* 110 */       this.blnHabModificarParameter = true;
/* 111 */       this.blnHabEliminarParameter = true;
/* 112 */       this.blnHabRegistrarParameter = true;
/*     */     }
/*     */     catch (AdminException e)
/*     */     {
/* 116 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void listarParameters() {
/* 121 */     this.listaParameterTable = new ArrayList();
/*     */     try {
/* 123 */       if (this.masterTable != null) {
/* 124 */         this.listaParameterTable = this.parametrosQLBeanLocal.listaParameter(this.masterTable.getId());
/* 125 */         this.blnHabModificarParameter = true;
/* 126 */         this.blnHabEliminarParameter = true;
/*     */       }
/*     */     }
/*     */     catch (AdminException e) {
/* 130 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registrarMaster()
/*     */   {
/* 136 */     this.strTitulo = "Registrar";
/* 137 */     this.masterTable = new MasterTable();
/*     */   }
/*     */   
/*     */   public void registrarParameter() {
/* 141 */     this.strTitulo = "Registrar";
/* 142 */     this.parameterTable = new ParameterTable();
/*     */   }
/*     */   
/*     */   public String diasPlazo(Date fecha, String estadoPago) {
/* 146 */     String dias = "";
/* 147 */     if (estadoPago.equals("7"))
/*     */     {
/*     */ 
/* 150 */       DateTime fechaActual = new DateTime(new Date());
/* 151 */       DateTime fechaFactura = new DateTime(fecha);
/*     */       
/* 153 */       if (fechaActual.isBefore(fechaFactura)) {
/* 154 */         Days days = Days.daysBetween(fechaActual, fechaFactura);
/* 155 */         dias = new Integer(days.getDays() + 1).toString();
/*     */       } else {
/* 157 */         dias = "VENCIDO";
/*     */       }
/*     */     } else {
/* 160 */       dias = "";
/*     */     }
/*     */     
/* 163 */     return dias;
/*     */   }
/*     */   
/*     */   public void guardarMaster(ActionEvent event)
/*     */   {
/*     */     try {
/* 169 */       this.parametrosMLBeanLocal.guardarMaster(this.masterTable);
/* 170 */       listarMaster();
/* 171 */       if (this.strTitulo.equals("Registrar")) {
/* 172 */         Util.CrearMsgGuardado();
/*     */       } else {
/* 174 */         Util.CrearMsgModificado();
/*     */       }
/*     */       
/* 177 */       this.blnHabModificarMaster = true;
/* 178 */       this.blnHabEliminarMaster = true;
/*     */     }
/*     */     catch (AdminException e) {
/* 181 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void guardarParameter(ActionEvent event)
/*     */   {
/*     */     try {
/* 188 */       this.parameterTable.setIdMaster(this.masterTable);
/* 189 */       this.parametrosMLBeanLocal.guardarParameter(this.parameterTable);
/* 190 */       listarParameters();
/* 191 */       if (this.strTitulo.equals("Registrar")) {
/* 192 */         Util.CrearMsgGuardado();
/*     */       } else {
/* 194 */         Util.CrearMsgModificado();
/*     */       }
/*     */       
/* 197 */       this.blnHabModificarMaster = true;
/* 198 */       this.blnHabEliminarMaster = true;
/*     */     }
/*     */     catch (AdminException e) {
/* 201 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void modificarMaster() {
/* 206 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void modificarParameter() {
/* 210 */     this.strTitulo = "Modificar";
/*     */   }
/*     */   
/*     */   public void eliminarMaster(ActionEvent event)
/*     */   {
/*     */     try {
/* 216 */       this.parametrosMLBeanLocal.eliminarMaster(this.masterTable);
/* 217 */       listarMaster();
/* 218 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 221 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminarParameter(ActionEvent event)
/*     */   {
/*     */     try {
/* 228 */       this.parametrosMLBeanLocal.eliminarParameter(this.parameterTable);
/* 229 */       listarParameters();
/* 230 */       Util.CrearMsgEliminado();
/*     */     }
/*     */     catch (AdminException e) {
/* 233 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String descripcionPeriodo(String mes)
/*     */   {
/* 240 */     return Util.descripcionPeriodo(mes);
/*     */   }
/*     */   
/*     */   public void onRowSelect(SelectEvent event)
/*     */   {
/* 245 */     this.blnHabModificarMaster = false;
/* 246 */     this.blnHabEliminarMaster = false;
/*     */     
/* 248 */     listarParameters();
/*     */     
/* 250 */     this.blnHabModificarParameter = true;
/* 251 */     this.blnHabEliminarParameter = true;
/* 252 */     this.blnHabRegistrarParameter = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onRowSelectParameter(SelectEvent event)
/*     */   {
/* 258 */     this.blnHabModificarParameter = false;
/* 259 */     this.blnHabEliminarParameter = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBlnHabModificarMaster()
/*     */   {
/* 265 */     return this.blnHabModificarMaster;
/*     */   }
/*     */   
/*     */   public void setBlnHabModificarMaster(boolean blnHabModificarMaster) {
/* 269 */     this.blnHabModificarMaster = blnHabModificarMaster;
/*     */   }
/*     */   
/*     */   public boolean isBlnHabEliminarMaster() {
/* 273 */     return this.blnHabEliminarMaster;
/*     */   }
/*     */   
/*     */   public void setBlnHabEliminarMaster(boolean blnHabEliminarMaster) {
/* 277 */     this.blnHabEliminarMaster = blnHabEliminarMaster;
/*     */   }
/*     */   
/*     */   public String getStrTitulo() {
/* 281 */     return this.strTitulo;
/*     */   }
/*     */   
/*     */   public void setStrTitulo(String strTitulo) {
/* 285 */     this.strTitulo = strTitulo;
/*     */   }
/*     */   
/*     */   public List<MasterTable> getListaMasterTable() {
/* 289 */     return this.listaMasterTable;
/*     */   }
/*     */   
/*     */   public void setListaMasterTable(List<MasterTable> listaMasterTable) {
/* 293 */     this.listaMasterTable = listaMasterTable;
/*     */   }
/*     */   
/*     */   public MasterTable getMasterTable() {
/* 297 */     return this.masterTable;
/*     */   }
/*     */   
/*     */   public void setMasterTable(MasterTable masterTable) {
/* 301 */     this.masterTable = masterTable;
/*     */   }
/*     */   
/*     */   public List<MasterTable> getFiltroListaMasterTable() {
/* 305 */     return this.filtroListaMasterTable;
/*     */   }
/*     */   
/*     */   public void setFiltroListaMasterTable(List<MasterTable> filtroListaMasterTable) {
/* 309 */     this.filtroListaMasterTable = filtroListaMasterTable;
/*     */   }
/*     */   
/*     */   public List<ParameterTable> getListaParameterTable() {
/* 313 */     return this.listaParameterTable;
/*     */   }
/*     */   
/*     */   public void setListaParameterTable(List<ParameterTable> listaParameterTable) {
/* 317 */     this.listaParameterTable = listaParameterTable;
/*     */   }
/*     */   
/*     */   public ParameterTable getParameterTable() {
/* 321 */     return this.parameterTable;
/*     */   }
/*     */   
/*     */   public void setParameterTable(ParameterTable parameterTable) {
/* 325 */     this.parameterTable = parameterTable;
/*     */   }
/*     */   
/*     */   public boolean isBlnHabModificarParameter() {
/* 329 */     return this.blnHabModificarParameter;
/*     */   }
/*     */   
/*     */   public void setBlnHabModificarParameter(boolean blnHabModificarParameter) {
/* 333 */     this.blnHabModificarParameter = blnHabModificarParameter;
/*     */   }
/*     */   
/*     */   public boolean isBlnHabEliminarParameter() {
/* 337 */     return this.blnHabEliminarParameter;
/*     */   }
/*     */   
/*     */   public void setBlnHabEliminarParameter(boolean blnHabEliminarParameter) {
/* 341 */     this.blnHabEliminarParameter = blnHabEliminarParameter;
/*     */   }
/*     */   
/*     */   public boolean isBlnHabRegistrarParameter() {
/* 345 */     return this.blnHabRegistrarParameter;
/*     */   }
/*     */   
/*     */   public void setBlnHabRegistrarParameter(boolean blnHabRegistrarParameter) {
/* 349 */     this.blnHabRegistrarParameter = blnHabRegistrarParameter;
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\param\ParametroBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */