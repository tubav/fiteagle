/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.slareservations;

import java.util.Date;

import FederationOffice.NamedElement;
import FederationOffice.resources.OfferedResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reserved Resource Contract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.slareservations.ReservedResourceContract#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link FederationOffice.slareservations.ReservedResourceContract#getForResource <em>For Resource</em>}</li>
 *   <li>{@link FederationOffice.slareservations.ReservedResourceContract#getValidUntil <em>Valid Until</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.slareservations.SlareservationsPackage#getReservedResourceContract()
 * @model
 * @generated
 */
public interface ReservedResourceContract extends NamedElement {
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
	 * @see FederationOffice.slareservations.SlareservationsPackage#getReservedResourceContract_ValidFrom()
	 * @model
	 * @generated
	 */
	Date getValidFrom();

	/**
	 * Sets the value of the '{@link FederationOffice.slareservations.ReservedResourceContract#getValidFrom <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid From</em>' attribute.
	 * @see #getValidFrom()
	 * @generated
	 */
	void setValidFrom(Date value);

	/**
	 * Returns the value of the '<em><b>For Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For Resource</em>' reference.
	 * @see #setForResource(OfferedResource)
	 * @see FederationOffice.slareservations.SlareservationsPackage#getReservedResourceContract_ForResource()
	 * @model
	 * @generated
	 */
	OfferedResource getForResource();

	/**
	 * Sets the value of the '{@link FederationOffice.slareservations.ReservedResourceContract#getForResource <em>For Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For Resource</em>' reference.
	 * @see #getForResource()
	 * @generated
	 */
	void setForResource(OfferedResource value);

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
	 * @see FederationOffice.slareservations.SlareservationsPackage#getReservedResourceContract_ValidUntil()
	 * @model
	 * @generated
	 */
	Date getValidUntil();

	/**
	 * Sets the value of the '{@link FederationOffice.slareservations.ReservedResourceContract#getValidUntil <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid Until</em>' attribute.
	 * @see #getValidUntil()
	 * @generated
	 */
	void setValidUntil(Date value);

} // ReservedResourceContract
