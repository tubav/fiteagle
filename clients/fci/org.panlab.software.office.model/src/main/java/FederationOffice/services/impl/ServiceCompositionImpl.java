/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.services.Service;
import FederationOffice.services.ServiceComposition;
import FederationOffice.services.ServicesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.ServiceCompositionImpl#getHasServices <em>Has Services</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceCompositionImpl extends NamedElementImpl implements ServiceComposition {
	/**
	 * The cached value of the '{@link #getHasServices() <em>Has Services</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasServices()
	 * @generated
	 * @ordered
	 */
	protected EList<Service> hasServices;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceCompositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.SERVICE_COMPOSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Service> getHasServices() {
		if (hasServices == null) {
			hasServices = new EObjectResolvingEList<Service>(Service.class, this, ServicesPackage.SERVICE_COMPOSITION__HAS_SERVICES);
		}
		return hasServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServicesPackage.SERVICE_COMPOSITION__HAS_SERVICES:
				return getHasServices();
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
			case ServicesPackage.SERVICE_COMPOSITION__HAS_SERVICES:
				getHasServices().clear();
				getHasServices().addAll((Collection<? extends Service>)newValue);
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
			case ServicesPackage.SERVICE_COMPOSITION__HAS_SERVICES:
				getHasServices().clear();
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
			case ServicesPackage.SERVICE_COMPOSITION__HAS_SERVICES:
				return hasServices != null && !hasServices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ServiceCompositionImpl
