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

import FederationOffice.resources.Resource;
import FederationOffice.resources.ResourceSetting;
import FederationOffice.services.ServiceSetting;
import FederationOffice.services.ServicesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Setting</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.ServiceSettingImpl#getMappedToResourceSettings <em>Mapped To Resource Settings</em>}</li>
 *   <li>{@link FederationOffice.services.impl.ServiceSettingImpl#getProvidedByResources <em>Provided By Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceSettingImpl extends AbstractSettingImpl implements ServiceSetting {
	/**
	 * The cached value of the '{@link #getMappedToResourceSettings() <em>Mapped To Resource Settings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedToResourceSettings()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceSetting> mappedToResourceSettings;

	/**
	 * The cached value of the '{@link #getProvidedByResources() <em>Provided By Resources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidedByResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> providedByResources;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceSettingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.SERVICE_SETTING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceSetting> getMappedToResourceSettings() {
		if (mappedToResourceSettings == null) {
			mappedToResourceSettings = new EObjectResolvingEList<ResourceSetting>(ResourceSetting.class, this, ServicesPackage.SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS);
		}
		return mappedToResourceSettings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getProvidedByResources() {
		if (providedByResources == null) {
			providedByResources = new EObjectResolvingEList<Resource>(Resource.class, this, ServicesPackage.SERVICE_SETTING__PROVIDED_BY_RESOURCES);
		}
		return providedByResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServicesPackage.SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS:
				return getMappedToResourceSettings();
			case ServicesPackage.SERVICE_SETTING__PROVIDED_BY_RESOURCES:
				return getProvidedByResources();
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
			case ServicesPackage.SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS:
				getMappedToResourceSettings().clear();
				getMappedToResourceSettings().addAll((Collection<? extends ResourceSetting>)newValue);
				return;
			case ServicesPackage.SERVICE_SETTING__PROVIDED_BY_RESOURCES:
				getProvidedByResources().clear();
				getProvidedByResources().addAll((Collection<? extends Resource>)newValue);
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
			case ServicesPackage.SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS:
				getMappedToResourceSettings().clear();
				return;
			case ServicesPackage.SERVICE_SETTING__PROVIDED_BY_RESOURCES:
				getProvidedByResources().clear();
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
			case ServicesPackage.SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS:
				return mappedToResourceSettings != null && !mappedToResourceSettings.isEmpty();
			case ServicesPackage.SERVICE_SETTING__PROVIDED_BY_RESOURCES:
				return providedByResources != null && !providedByResources.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ServiceSettingImpl
