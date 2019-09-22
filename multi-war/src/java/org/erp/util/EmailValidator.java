/*    */ package org.erp.util;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import javax.faces.application.FacesMessage;
/*    */ import javax.faces.component.UIComponent;
/*    */ import javax.faces.context.FacesContext;
/*    */ import javax.faces.validator.FacesValidator;
/*    */ import javax.faces.validator.Validator;
/*    */ import javax.faces.validator.ValidatorException;
/*    */ import org.primefaces.validate.ClientValidator;
/*    */ 
/*    */ 
/*    */ 
/*    */ @FacesValidator("custom.emailValidator")
/*    */ public class EmailValidator
/*    */   implements Validator, ClientValidator
/*    */ {
/*    */   private Pattern pattern;
/*    */   private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
/*    */   
/*    */   public EmailValidator()
/*    */   {
/* 25 */     this.pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
/*    */   }
/*    */   
/*    */   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
/* 29 */     if (value == null) {
/* 30 */       return;
/*    */     }
/*    */     
/* 33 */     if (!this.pattern.matcher(value.toString()).matches()) {
/* 34 */       throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
/* 35 */         value + " No es un correo Válido;"));
/*    */     }
/*    */   }
/*    */   
/*    */   public Map<String, Object> getMetadata() {
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public String getValidatorId() {
/* 44 */     return "custom.emailValidator";
/*    */   }
/*    */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\util\EmailValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */