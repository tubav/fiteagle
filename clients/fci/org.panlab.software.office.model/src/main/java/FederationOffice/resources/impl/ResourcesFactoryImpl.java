/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources.impl;

import FederationOffice.resources.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.resources.OfferedResource;
import FederationOffice.resources.Resource;
import FederationOffice.resources.ResourceCategory;
import FederationOffice.resources.ResourceSetting;
import FederationOffice.resources.ResourceType;
import FederationOffice.resources.ResourcesFactory;
import FederationOffice.resources.ResourcesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourcesFactoryImpl extends EFactoryImpl implements ResourcesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResourcesFactory init() {
		try {
			ResourcesFactory theResourcesFactory = (ResourcesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/resources"); 
			if (theResourcesFactory != null) {
				return theResourcesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ResourcesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ResourcesPackage.RESOURCE: return createResource();
			case ResourcesPackage.OFFERED_RESOURCE: return createOfferedResource();
			case ResourcesPackage.RESOURCE_SETTING: return createResourceSetting();
			case ResourcesPackage.RESOURCE_CATEGORY: return createResourceCategory();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ResourcesPackage.RESOURCE_TYPE:
				return createResourceTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ResourcesPackage.RESOURCE_TYPE:
				return convertResourceTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfferedResource createOfferedResource() {
		OfferedResourceImpl offeredResource = new OfferedResourceImpl();
		return offeredResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSetting createResourceSetting() {
		ResourceSettingImpl resourceSetting = new ResourceSettingImpl();
		return resourceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceCategory createResourceCategory() {
		ResourceCategoryImpl resourceCategory = new ResourceCategoryImpl();
		return resourceCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType createResourceTypeFromString(EDataType eDataType, String initialValue) {
		ResourceType result = ResourceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResourceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcesPackage getResourcesPackage() {
		return (ResourcesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ResourcesPackage getPackage() {
		return ResourcesPackage.eINSTANCE;
	}

} //ResourcesFactoryImpl
