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
import FederationOffice.federationscenarios.InfrastructureRequest;
import FederationOffice.federationscenarios.ResourceGroup;
import FederationOffice.federationscenarios.ResourceRequest;
import FederationOffice.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Infrastructure Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.InfrastructureRequestImpl#getReqOfferedResources <em>Req Offered Resources</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.InfrastructureRequestImpl#getResourceGroups <em>Resource Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfrastructureRequestImpl extends NamedElementImpl implements InfrastructureRequest {
	/**
	 * The cached value of the '{@link #getReqOfferedResources() <em>Req Offered Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReqOfferedResources()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceRequest> reqOfferedResources;

	/**
	 * The cached value of the '{@link #getResourceGroups() <em>Resource Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceGroup> resourceGroups;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfrastructureRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.INFRASTRUCTURE_REQUEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceRequest> getReqOfferedResources() {
		if (reqOfferedResources == null) {
			reqOfferedResources = new EObjectContainmentEList.Resolving<ResourceRequest>(ResourceRequest.class, this, FederationscenariosPackage.INFRASTRUCTURE_REQUEST__REQ_OFFERED_RESOURCES);
		}
		return reqOfferedResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceGroup> getResourceGroups() {
		if (resourceGroups == null) {
			resourceGroups = new EObjectContainmentEList.Resolving<ResourceGroup>(ResourceGroup.class, this, FederationscenariosPackage.INFRASTRUCTURE_REQUEST__RESOURCE_GROUPS);
		}
		return resourceGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__REQ_OFFERED_RESOURCES:
				return ((InternalEList<?>)getReqOfferedResources()).basicRemove(otherEnd, msgs);
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__RESOURCE_GROUPS:
				return ((InternalEList<?>)getResourceGroups()).basicRemove(otherEnd, msgs);
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
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__REQ_OFFERED_RESOURCES:
				return getReqOfferedResources();
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__RESOURCE_GROUPS:
				return getResourceGroups();
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
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__REQ_OFFERED_RESOURCES:
				getReqOfferedResources().clear();
				getReqOfferedResources().addAll((Collection<? extends ResourceRequest>)newValue);
				return;
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__RESOURCE_GROUPS:
				getResourceGroups().clear();
				getResourceGroups().addAll((Collection<? extends ResourceGroup>)newValue);
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
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__REQ_OFFERED_RESOURCES:
				getReqOfferedResources().clear();
				return;
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__RESOURCE_GROUPS:
				getResourceGroups().clear();
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
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__REQ_OFFERED_RESOURCES:
				return reqOfferedResources != null && !reqOfferedResources.isEmpty();
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST__RESOURCE_GROUPS:
				return resourceGroups != null && !resourceGroups.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InfrastructureRequestImpl
