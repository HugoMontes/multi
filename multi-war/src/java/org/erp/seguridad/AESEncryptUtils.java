/*     */ package org.erp.seguridad;
/*     */ 
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.SecretKeySpec;
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
/*     */ public class AESEncryptUtils
/*     */ {
/*     */   private static final String HEX = "0123456789ABCDEF";
/*     */   
/*     */   public static String encrypt(String seed, String cleartext)
/*     */     throws Exception
/*     */   {
/*  30 */     byte[] rawKey = getRawKey(seed.getBytes());
/*  31 */     byte[] result = encrypt(rawKey, cleartext.getBytes());
/*  32 */     return toHex(result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String decrypt(String seed, String encrypted)
/*     */     throws Exception
/*     */   {
/*  44 */     byte[] rawKey = getRawKey(seed.getBytes());
/*  45 */     byte[] enc = toByte(encrypted);
/*  46 */     byte[] result = decrypt(rawKey, enc);
/*  47 */     return new String(result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static byte[] getRawKey(byte[] seed)
/*     */     throws Exception
/*     */   {
/*  58 */     KeyGenerator kgen = KeyGenerator.getInstance("AES");
/*  59 */     SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
/*  60 */     sr.setSeed(seed);
/*  61 */     kgen.init(128, sr);
/*  62 */     SecretKey skey = kgen.generateKey();
/*  63 */     byte[] raw = skey.getEncoded();
/*  64 */     return raw;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static byte[] encrypt(byte[] raw, byte[] clear)
/*     */     throws Exception
/*     */   {
/*  77 */     SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
/*  78 */     Cipher cipher = Cipher.getInstance("AES");
/*  79 */     cipher.init(1, skeySpec);
/*  80 */     byte[] encrypted = cipher.doFinal(clear);
/*  81 */     return encrypted;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static byte[] decrypt(byte[] raw, byte[] encrypted)
/*     */     throws Exception
/*     */   {
/*  93 */     SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
/*  94 */     Cipher cipher = Cipher.getInstance("AES");
/*  95 */     cipher.init(2, skeySpec);
/*  96 */     byte[] decrypted = cipher.doFinal(encrypted);
/*  97 */     return decrypted;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toHex(String txt)
/*     */   {
/* 107 */     return toHex(txt.getBytes());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String fromHex(String hex)
/*     */   {
/* 117 */     return new String(toByte(hex));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] toByte(String hexString)
/*     */   {
/* 127 */     int len = hexString.length() / 2;
/* 128 */     byte[] result = new byte[len];
/* 129 */     for (int i = 0; i < len; i++)
/* 130 */       result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
/* 131 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toHex(byte[] buf)
/*     */   {
/* 141 */     if (buf == null)
/* 142 */       return "";
/* 143 */     StringBuffer result = new StringBuffer(2 * buf.length);
/* 144 */     for (int i = 0; i < buf.length; i++) {
/* 145 */       appendHex(result, buf[i]);
/*     */     }
/* 147 */     return result.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void appendHex(StringBuffer sb, byte b)
/*     */   {
/* 160 */     sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF)).append("0123456789ABCDEF".charAt(b & 0xF));
/*     */   }
/*     */ }


/* Location:              F:\win\multi-ear.ear!\multi-web.war!\WEB-INF\classes\org\erp\seguridad\AESEncryptUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */