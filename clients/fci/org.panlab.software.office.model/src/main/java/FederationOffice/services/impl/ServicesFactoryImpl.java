/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.impl;

import FederationOffice.services.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.services.AbstractSetting;
import FederationOffice.services.OfferedService;
import FederationOffice.services.Service;
import FederationOffice.services.ServiceComposition;
import FederationOffice.services.ServiceResourceOperation;
import FederationOffice.services.ServiceSetting;
import FederationOffice.services.ServicesFactory;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.SettingConstraint;
import FederationOffice.services.Taxonomy;
import FederationOffice.services.tideTypeEnum;
import FederationOffice.services.tideTypeEnumItem;
import FederationOffice.services.tideTypeList;
import FederationOffice.services.tideTypeString;
import FederationOffice.services.tideTypeTideElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServicesFactoryImpl extends EFactoryImpl implements ServicesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ServicesFactory init() {
		try {
			ServicesFactory theServicesFactory = (ServicesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/services"); 
			if (theServicesFactory != null) {
				return theServicesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ServicesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesFactoryImpl() {
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
			case ServicesPackage.SERVICE: return createService();
			case ServicesPackage.SERVICE_SETTING: return createServiceSetting();
			case ServicesPackage.OFFERED_SERVICE: return createOfferedService();
			case ServicesPackage.SERVICE_COMPOSITION: return createServiceComposition();
			case ServicesPackage.TIDE_TYPE_STRING: return createtideTypeString();
			case ServicesPackage.TIDE_TYPE_ENUM: return createtideTypeEnum();
			case ServicesPackage.TIDE_TYPE_LIST: return createtideTypeList();
			case ServicesPackage.TIDE_TYPE_ENUM_ITEM: return createtideTypeEnumItem();
			case ServicesPackage.TIDE_TYPE_TIDE_ELEMENT: return createtideTypeTideElement();
			case ServicesPackage.TAXONOMY: return createTaxonomy();
			case ServicesPackage.ABSTRACT_SETTING: return createAbstractSetting();
			case ServicesPackage.SETTING_CONSTRAINT: return createSettingConstraint();
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
			case ServicesPackage.SERVICE_RESOURCE_OPERATION:
				return createServiceResourceOperationFromString(eDataType, initialValue);
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
			case ServicesPackage.SERVICE_RESOURCE_OPERATION:
				return convertServiceResourceOperationToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service createService() {
		ServiceImpl service = new ServiceImpl();
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSetting createServiceSetting() {
		ServiceSettingImpl serviceSetting = new ServiceSettingImpl();
		return serviceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfferedService createOfferedService() {
		OfferedServiceImpl offeredService = new OfferedServiceImpl();
		return offeredService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceComposition createServiceComposition() {
		ServiceCompositionImpl serviceComposition = new ServiceCompositionImpl();
		return serviceComposition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeString createtideTypeString() {
		tideTypeStringImpl tideTypeString = new tideTypeStringImpl();
		return tideTypeString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeEnum createtideTypeEnum() {
		tideTypeEnumImpl tideTypeEnum = new tideTypeEnumImpl();
		return tideTypeEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeList createtideTypeList() {
		tideTypeListImpl tideTypeList = new tideTypeListImpl();
		return tideTypeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeEnumItem createtideTypeEnumItem() {
		tideTypeEnumItemImpl tideTypeEnumItem = new tideTypeEnumItemImpl();
		return tideTypeEnumItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeTideElement createtideTypeTideElement() {
		tideTypeTideElementImpl tideTypeTideElement = new tideTypeTideElementImpl();
		return tideTypeTideElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Taxonomy createTaxonomy() {
		TaxonomyImpl taxonomy = new TaxonomyImpl();
		return taxonomy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractSetting createAbstractSetting() {
		AbstractSettingImpl abstractSetting = new AbstractSettingImpl();
		return abstractSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingConstraint createSettingConstraint() {
		SettingConstraintImpl settingConstraint = new SettingConstraintImpl();
		return settingConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceResourceOperation createServiceResourceOperationFromString(EDataType eDataType, String initialValue) {
		ServiceResourceOperation result = ServiceResourceOperation.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertServiceResourceOperationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesPackage getServicesPackage() {
		return (ServicesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ServicesPackage getPackage() {
		return ServicesPackage.eINSTANCE;
	}

} //ServicesFactoryImpl
