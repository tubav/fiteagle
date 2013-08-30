/*    */ package org.csapi.schema.parlayx.common.v4_0;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="Media")
/*    */ @XmlEnum
/*    */ public enum Media
/*    */ {
/* 31 */   AUDIO("Audio"), 
/*    */ 
/* 33 */   VIDEO("Video"), 
/*    */ 
/* 35 */   CHAT("Chat"), 
/*    */ 
/* 37 */   DATA("Data");
/*    */ 
/*    */   private final String value;
/*    */ 
/* 41 */   private Media(String v) { this.value = v; }
/*    */ 
/*    */   public String value()
/*    */   {
/* 45 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static Media fromValue(String v) {
/* 49 */     for (Media c : values()) {
/* 50 */       if (c.value.equals(v)) {
/* 51 */         return c;
/*    */       }
/*    */     }
/* 54 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           /Users/dne/.m2/repository/de/fhg/fokus/ngni/opense/parlayx_4_0_jaxws/0.0.1/parlayx_4_0_jaxws-0.0.1.jar
 * Qualified Name:     org.csapi.schema.parlayx.common.v4_0.Media
 * JD-Core Version:    0.6.0
 */