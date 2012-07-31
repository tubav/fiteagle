/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.users.OfficeCustomer;
import FederationOffice.users.TestbedDesigner;
import FederationOffice.users.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Office Customer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.users.impl.OfficeCustomerImpl#getUtilizesVirtualTestbed <em>Utilizes Virtual Testbed</em>}</li>
 *   <li>{@link FederationOffice.users.impl.OfficeCustomerImpl#getHasTestbedDesigner <em>Has Testbed Designer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OfficeCustomerImpl extends OfficeUserImpl implements OfficeCustomer {
	/**
	 * The cached value of the '{@link #getUtilizesVirtualTestbed() <em>Utilizes Virtual Testbed</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtilizesVirtualTestbed()
	 * @generated
	 * @ordered
	 */
	protected EList<RequestedFederationScenario> utilizesVirtualTestbed;

	/**
	 * The cached value of the '{@link #getHasTestbedDesigner() <em>Has Testbed Designer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasTestbedDesigner()
	 * @generated
	 * @ordered
	 */
	protected TestbedDesigner hasTestbedDesigner;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OfficeCustomerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.Literals.OFFICE_CUSTOMER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequestedFederationScenario> getUtilizesVirtualTestbed() {
		if (utilizesVirtualTestbed == null) {
			utilizesVirtualTestbed = new EObjectResolvingEList<RequestedFederationScenario>(RequestedFederationScenario.class, this, UsersPackage.OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED);
		}
		return utilizesVirtualTestbed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestbedDesigner getHasTestbedDesigner() {
		if (hasTestbedDesigner != null && hasTestbedDesigner.eIsProxy()) {
			InternalEObject oldHasTestbedDesigner = (InternalEObject)hasTestbedDesigner;
			hasTestbedDesigner = (TestbedDesigner)eResolveProxy(oldHasTestbedDesigner);
			if (hasTestbedDesigner != oldHasTestbedDesigner) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsersPackage.OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER, oldHasTestbedDesigner, hasTestbedDesigner));
			}
		}
		return hasTestbedDesigner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestbedDesigner basicGetHasTestbedDesigner() {
		return hasTestbedDesigner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasTestbedDesigner(TestbedDesigner newHasTestbedDesigner) {
		TestbedDesigner oldHasTestbedDesigner = hasTestbedDesigner;
		hasTestbedDesigner = newHasTestbedDesigner;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER, oldHasTestbedDesigner, hasTestbedDesigner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsersPackage.OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED:
				return getUtilizesVirtualTestbed();
			case UsersPackage.OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER:
				if (resolve) return getHasTestbedDesigner();
				return basicGetHasTestbedDesigner();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UsersPackage.OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED:
				getUtilizesVirtualTestbed().clear();
				getUtilizesVirtualTestbed().addAll((Collection<? extends RequestedFederationScenario>)newValue);
				return;
			case UsersPackage.OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER:
				setHasTestbedDesigner((TestbedDesigner)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UsersPackage.OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED:
				getUtilizesVirtualTestbed().clear();
				return;
			case UsersPackage.OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER:
				setHasTestbedDesigner((TestbedDesigner)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UsersPackage.OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED:
				return utilizesVirtualTestbed != null && !utilizesVirtualTestbed.isEmpty();
			case UsersPackage.OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER:
				return hasTestbedDesigner != null;
		}
		return super.eIsSet(featureID);
	}

} //OfficeCustomerImpl
