/*     */ package org.csapi.schema.parlayx.adq.v4_0;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import org.csapi.schema.parlayx.common.v4_0.NameValuePair;
import org.csapi.schema.parlayx.common.v4_0.TimeMetric;
/*     */ @XmlRootElement
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="QoSFeatureProperties", propOrder={"duration", "upStreamSpeedRate", "downStreamSpeedRate", "otherProperties"})
/*     */ public class QoSFeatureProperties
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 12343L;
/*     */   protected TimeMetric duration;
/*     */   protected String upStreamSpeedRate;
/*     */   protected String downStreamSpeedRate;
/*     */   protected List<NameValuePair> otherProperties;
/*     */ 
/*     */   public TimeMetric getDuration()
/*     */   {
/*  62 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public void setDuration(TimeMetric value)
/*     */   {
/*  74 */     this.duration = value;
/*     */   }
/*     */ 
/*     */   public String getUpStreamSpeedRate()
/*     */   {
/*  86 */     return this.upStreamSpeedRate;
/*     */   }
/*     */ 
/*     */   public void setUpStreamSpeedRate(String value)
/*     */   {
/*  98 */     this.upStreamSpeedRate = value;
/*     */   }
/*     */ 
/*     */   public String getDownStreamSpeedRate()
/*     */   {
/* 110 */     return this.downStreamSpeedRate;
/*     */   }
/*     */ 
/*     */   public void setDownStreamSpeedRate(String value)
/*     */   {
/* 122 */     this.downStreamSpeedRate = value;
/*     */   }
/*     */ 
/*     */   public List<NameValuePair> getOtherProperties()
/*     */   {
/* 148 */     if (this.otherProperties == null) {
/* 149 */       this.otherProperties = new ArrayList();
/*     */     }
/* 151 */     return this.otherProperties;
/*     */   }
/*     */ }

/* Location:           /Users/dne/.m2/repository/de/fhg/fokus/ngni/opense/parlayx_4_0_jaxws/0.0.1/parlayx_4_0_jaxws-0.0.1.jar
 * Qualified Name:     org.csapi.schema.parlayx.adq.v4_0.QoSFeatureProperties
 * JD-Core Version:    0.6.0
 */