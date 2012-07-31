/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.ResourceGroup;
import FederationOffice.federationscenarios.ResourceRequest;
import FederationOffice.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ResourceGroupImpl#getGroupedResources <em>Grouped Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceGroupImpl extends NamedElementImpl implements ResourceGroup {
	/**
	 * The cached value of the '{@link #getGroupedResources() <em>Grouped Resources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupedResources()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceRequest> groupedResources;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.RESOURCE_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceRequest> getGroupedResources() {
		if (groupedResources == null) {
			groupedResources = new EObjectResolvingEList<ResourceRequest>(ResourceRequest.class, this, FederationscenariosPackage.RESOURCE_GROUP__GROUPED_RESOURCES);
		}
		return groupedResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FederationscenariosPackage.RESOURCE_GROUP__GROUPED_RESOURCES:
				return getGroupedResources();
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
			case FederationscenariosPackage.RESOURCE_GROUP__GROUPED_RESOURCES:
				getGroupedResources().clear();
				getGroupedResources().addAll((Collection<? extends ResourceRequest>)newValue);
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
			case FederationscenariosPackage.RESOURCE_GROUP__GROUPED_RESOURCES:
				getGroupedResources().clear();
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
			case FederationscenariosPackage.RESOURCE_GROUP__GROUPED_RESOURCES:
				return groupedResources != null && !groupedResources.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResourceGroupImpl
