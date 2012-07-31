/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.ServiceRequest;
import FederationOffice.federationscenarios.ServicesRequest;
import FederationOffice.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Services Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ServicesRequestImpl#getServiceRequestList <em>Service Request List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServicesRequestImpl extends NamedElementImpl implements ServicesRequest {
	/**
	 * The cached value of the '{@link #getServiceRequestList() <em>Service Request List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRequestList()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceRequest> serviceRequestList;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServicesRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.SERVICES_REQUEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ServiceRequest> getServiceRequestList() {
		if (serviceRequestList == null) {
			serviceRequestList = new EObjectContainmentEList.Resolving<ServiceRequest>(ServiceRequest.class, this, FederationscenariosPackage.SERVICES_REQUEST__SERVICE_REQUEST_LIST);
		}
		return serviceRequestList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FederationscenariosPackage.SERVICES_REQUEST__SERVICE_REQUEST_LIST:
				return ((InternalEList<?>)getServiceRequestList()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FederationscenariosPackage.SERVICES_REQUEST__SERVICE_REQUEST_LIST:
				return getServiceRequestList();
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
			case FederationscenariosPackage.SERVICES_REQUEST__SERVICE_REQUEST_LIST:
				getServiceRequestList().clear();
				getServiceRequestList().addAll((Collection<? extends ServiceRequest>)newValue);
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
			case FederationscenariosPackage.SERVICES_REQUEST__SERVICE_REQUEST_LIST:
				getServiceRequestList().clear();
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
			case FederationscenariosPackage.SERVICES_REQUEST__SERVICE_REQUEST_LIST:
				return serviceRequestList != null && !serviceRequestList.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ServicesRequestImpl
