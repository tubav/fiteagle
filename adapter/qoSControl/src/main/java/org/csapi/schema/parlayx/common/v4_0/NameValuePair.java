/*     */ package org.csapi.schema.parlayx.common.v4_0;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="NameValuePair", propOrder={"name", "value", "description"})
/*     */ public class NameValuePair
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 12343L;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected String name;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected String value;
/*     */   protected String description;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  58 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  70 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public String getValue()
/*     */   {
/*  82 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/*  94 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 106 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String value)
/*     */   {
/* 118 */     this.description = value;
/*     */   }
/*     */ }

/* Location:           /Users/dne/.m2/repository/de/fhg/fokus/ngni/opense/parlayx_4_0_jaxws/0.0.1/parlayx_4_0_jaxws-0.0.1.jar
 * Qualified Name:     org.csapi.schema.parlayx.common.v4_0.NameValuePair
 * JD-Core Version:    0.6.0
 */