/*    */ package org.csapi.schema.parlayx.common.v4_0;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="TimeMetrics")
/*    */ @XmlEnum
/*    */ public enum TimeMetrics
/*    */ {
/* 35 */   MILLISECOND("Millisecond"), 
/*    */ 
/* 37 */   SECOND("Second"), 
/*    */ 
/* 39 */   MINUTE("Minute"), 
/*    */ 
/* 41 */   HOUR("Hour"), 
/*    */ 
/* 43 */   DAY("Day"), 
/*    */ 
/* 45 */   WEEK("Week"), 
/*    */ 
/* 47 */   MONTH("Month"), 
/*    */ 
/* 49 */   YEAR("Year");
/*    */ 
/*    */   private final String value;
/*    */ 
/* 53 */   private TimeMetrics(String v) { this.value = v; }
/*    */ 
/*    */   public String value()
/*    */   {
/* 57 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static TimeMetrics fromValue(String v) {
/* 61 */     for (TimeMetrics c : values()) {
/* 62 */       if (c.value.equals(v)) {
/* 63 */         return c;
/*    */       }
/*    */     }
/* 66 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           /Users/dne/.m2/repository/de/fhg/fokus/ngni/opense/parlayx_4_0_jaxws/0.0.1/parlayx_4_0_jaxws-0.0.1.jar
 * Qualified Name:     org.csapi.schema.parlayx.common.v4_0.TimeMetrics
 * JD-Core Version:    0.6.0
 */