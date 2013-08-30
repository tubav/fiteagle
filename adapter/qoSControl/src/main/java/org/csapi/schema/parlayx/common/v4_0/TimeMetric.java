/*    */ package org.csapi.schema.parlayx.common.v4_0;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="TimeMetric", propOrder={"metric", "units"})
/*    */ public class TimeMetric
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 12343L;
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected TimeMetrics metric;
/*    */   protected int units;
/*    */ 
/*    */   public TimeMetrics getMetric()
/*    */   {
/* 54 */     return this.metric;
/*    */   }
/*    */ 
/*    */   public void setMetric(TimeMetrics value)
/*    */   {
/* 66 */     this.metric = value;
/*    */   }
/*    */ 
/*    */   public int getUnits()
/*    */   {
/* 74 */     return this.units;
/*    */   }
/*    */ 
/*    */   public void setUnits(int value)
/*    */   {
/* 82 */     this.units = value;
/*    */   }
/*    */ }

/* Location:           /Users/dne/.m2/repository/de/fhg/fokus/ngni/opense/parlayx_4_0_jaxws/0.0.1/parlayx_4_0_jaxws-0.0.1.jar
 * Qualified Name:     org.csapi.schema.parlayx.common.v4_0.TimeMetric
 * JD-Core Version:    0.6.0
 */