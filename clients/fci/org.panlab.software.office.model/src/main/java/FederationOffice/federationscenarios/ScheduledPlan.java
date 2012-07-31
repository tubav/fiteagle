/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scheduled Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.ScheduledPlan#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.ScheduledPlan#getValidUntil <em>Valid Until</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getScheduledPlan()
 * @model
 * @generated
 */
public interface ScheduledPlan extends EObject {
	/**
	 * Returns the value of the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid From</em>' attribute.
	 * @see #setValidFrom(Date)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getScheduledPlan_ValidFrom()
	 * @model
	 * @generated
	 */
	Date getValidFrom();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.ScheduledPlan#getValidFrom <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid From</em>' attribute.
	 * @see #getValidFrom()
	 * @generated
	 */
	void setValidFrom(Date value);

	/**
	 * Returns the value of the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid Until</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid Until</em>' attribute.
	 * @see #setValidUntil(Date)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getScheduledPlan_ValidUntil()
	 * @model
	 * @generated
	 */
	Date getValidUntil();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.ScheduledPlan#getValidUntil <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid Until</em>' attribute.
	 * @see #getValidUntil()
	 * @generated
	 */
	void setValidUntil(Date value);

} // ScheduledPlan
