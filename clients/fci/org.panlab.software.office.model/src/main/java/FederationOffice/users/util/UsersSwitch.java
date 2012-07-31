/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import FederationOffice.NamedElement;
import FederationOffice.users.*;
import FederationOffice.users.Account;
import FederationOffice.users.Admin;
import FederationOffice.users.OfficeCustomer;
import FederationOffice.users.OfficePersonel;
import FederationOffice.users.OfficeUser;
import FederationOffice.users.ResourcesProvider;
import FederationOffice.users.TestbedDesigner;
import FederationOffice.users.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see FederationOffice.users.UsersPackage
 * @generated
 */
public class UsersSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UsersPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsersSwitch() {
		if (modelPackage == null) {
			modelPackage = UsersPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case UsersPackage.ACCOUNT: {
				Account account = (Account)theEObject;
				T result = caseAccount(account);
				if (result == null) result = caseNamedElement(account);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsersPackage.RESOURCES_PROVIDER: {
				ResourcesProvider resourcesProvider = (ResourcesProvider)theEObject;
				T result = caseResourcesProvider(resourcesProvider);
				if (result == null) result = caseOfficeUser(resourcesProvider);
				if (result == null) result = caseNamedElement(resourcesProvider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsersPackage.TESTBED_DESIGNER: {
				TestbedDesigner testbedDesigner = (TestbedDesigner)theEObject;
				T result = caseTestbedDesigner(testbedDesigner);
				if (result == null) result = caseOfficeUser(testbedDesigner);
				if (result == null) result = caseNamedElement(testbedDesigner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsersPackage.ADMIN: {
				Admin admin = (Admin)theEObject;
				T result = caseAdmin(admin);
				if (result == null) result = caseOfficePersonel(admin);
				if (result == null) result = caseOfficeUser(admin);
				if (result == null) result = caseNamedElement(admin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsersPackage.OFFICE_CUSTOMER: {
				OfficeCustomer officeCustomer = (OfficeCustomer)theEObject;
				T result = caseOfficeCustomer(officeCustomer);
				if (result == null) result = caseOfficeUser(officeCustomer);
				if (result == null) result = caseNamedElement(officeCustomer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsersPackage.OFFICE_USER: {
				OfficeUser officeUser = (OfficeUser)theEObject;
				T result = caseOfficeUser(officeUser);
				if (result == null) result = caseNamedElement(officeUser);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsersPackage.OFFICE_PERSONEL: {
				OfficePersonel officePersonel = (OfficePersonel)theEObject;
				T result = caseOfficePersonel(officePersonel);
				if (result == null) result = caseOfficeUser(officePersonel);
				if (result == null) result = caseNamedElement(officePersonel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Account</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Account</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccount(Account object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resources Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resources Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourcesProvider(ResourcesProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Testbed Designer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Testbed Designer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestbedDesigner(TestbedDesigner object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Admin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Admin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdmin(Admin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Office Customer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Office Customer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOfficeCustomer(OfficeCustomer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Office User</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Office User</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOfficeUser(OfficeUser object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Office Personel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Office Personel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOfficePersonel(OfficePersonel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //UsersSwitch
