/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.services.Service;
import FederationOffice.services.ServiceSetting;
import FederationOffice.services.ServicesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.ServiceImpl#getServiceSettings <em>Service Settings</em>}</li>
 *   <li>{@link FederationOffice.services.impl.ServiceImpl#getRequiresServices <em>Requires Services</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceImpl extends NamedElementImpl implements Service {
	/**
	 * The cached value of the '{@link #getServiceSettings() <em>Service Settings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceSettings()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceSetting> serviceSettings;

	/**
	 * The cached value of the '{@link #getRequiresServices() <em>Requires Services</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiresServices()
	 * @generated
	 * @ordered
	 */
	protected EList<Service> requiresServices;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ServiceSetting> getServiceSettings() {
		if (serviceSettings == null) {
			serviceSettings = new EObjectContainmentEList.Resolving<ServiceSetting>(ServiceSetting.class, this, ServicesPackage.SERVICE__SERVICE_SETTINGS);
		}
		return serviceSettings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Service> getRequiresServices() {
		if (requiresServices == null) {
			requiresServices = new EObjectResolvingEList<Service>(Service.class, this, ServicesPackage.SERVICE__REQUIRES_SERVICES);
		}
		return requiresServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServicesPackage.SERVICE__SERVICE_SETTINGS:
				return ((InternalEList<?>)getServiceSettings()).basicRemove(otherEnd, msgs);
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
			case ServicesPackage.SERVICE__SERVICE_SETTINGS:
				return getServiceSettings();
			case ServicesPackage.SERVICE__REQUIRES_SERVICES:
				return getRequiresServices();
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
			case ServicesPackage.SERVICE__SERVICE_SETTINGS:
				getServiceSettings().clear();
				getServiceSettings().addAll((Collection<? extends ServiceSetting>)newValue);
				return;
			case ServicesPackage.SERVICE__REQUIRES_SERVICES:
				getRequiresServices().clear();
				getRequiresServices().addAll((Collection<? extends Service>)newValue);
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
			case ServicesPackage.SERVICE__SERVICE_SETTINGS:
				getServiceSettings().clear();
				return;
			case ServicesPackage.SERVICE__REQUIRES_SERVICES:
				getRequiresServices().clear();
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
			case ServicesPackage.SERVICE__SERVICE_SETTINGS:
				return serviceSettings != null && !serviceSettings.isEmpty();
			case ServicesPackage.SERVICE__REQUIRES_SERVICES:
				return requiresServices != null && !requiresServices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ServiceImpl
