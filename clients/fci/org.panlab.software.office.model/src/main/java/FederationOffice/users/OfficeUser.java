/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Office User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.users.OfficeUser#getHasAccount <em>Has Account</em>}</li>
 *   <li>{@link FederationOffice.users.OfficeUser#getOrganization <em>Organization</em>}</li>
 *   <li>{@link FederationOffice.users.OfficeUser#getAddress <em>Address</em>}</li>
 *   <li>{@link FederationOffice.users.OfficeUser#getTelephone <em>Telephone</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.users.UsersPackage#getOfficeUser()
 * @model abstract="true"
 * @generated
 */
public interface OfficeUser extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Account</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Account</em>' containment reference.
	 * @see #setHasAccount(Account)
	 * @see FederationOffice.users.UsersPackage#getOfficeUser_HasAccount()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	Account getHasAccount();

	/**
	 * Sets the value of the '{@link FederationOffice.users.OfficeUser#getHasAccount <em>Has Account</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Account</em>' containment reference.
	 * @see #getHasAccount()
	 * @generated
	 */
	void setHasAccount(Account value);

	/**
	 * Returns the value of the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Organization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Organization</em>' attribute.
	 * @see #setOrganization(String)
	 * @see FederationOffice.users.UsersPackage#getOfficeUser_Organization()
	 * @model
	 * @generated
	 */
	String getOrganization();

	/**
	 * Sets the value of the '{@link FederationOffice.users.OfficeUser#getOrganization <em>Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Organization</em>' attribute.
	 * @see #getOrganization()
	 * @generated
	 */
	void setOrganization(String value);

	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see FederationOffice.users.UsersPackage#getOfficeUser_Address()
	 * @model
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link FederationOffice.users.OfficeUser#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Returns the value of the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Telephone</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Telephone</em>' attribute.
	 * @see #setTelephone(String)
	 * @see FederationOffice.users.UsersPackage#getOfficeUser_Telephone()
	 * @model
	 * @generated
	 */
	String getTelephone();

	/**
	 * Sets the value of the '{@link FederationOffice.users.OfficeUser#getTelephone <em>Telephone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Telephone</em>' attribute.
	 * @see #getTelephone()
	 * @generated
	 */
	void setTelephone(String value);

} // OfficeUser
