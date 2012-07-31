/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import FederationOffice.FederationOfficePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see FederationOffice.services.ServicesFactory
 * @model kind="package"
 * @generated
 */
public interface ServicesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "services";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/services";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.services";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ServicesPackage eINSTANCE = FederationOffice.services.impl.ServicesPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.ServiceImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Service Settings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__SERVICE_SETTINGS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Requires Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__REQUIRES_SERVICES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.AbstractSettingImpl <em>Abstract Setting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.AbstractSettingImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getAbstractSetting()
	 * @generated
	 */
	int ABSTRACT_SETTING = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Setting Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__SETTING_TYPE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User Exposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__USER_EXPOSED = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>User Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__USER_EDITABLE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Can Be Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__CAN_BE_PUBLISHED = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__READABLE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__WRITABLE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Setting Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__SETTING_CONSTRAINTS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Requires Params</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING__REQUIRES_PARAMS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Abstract Setting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SETTING_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.ServiceSettingImpl <em>Service Setting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.ServiceSettingImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getServiceSetting()
	 * @generated
	 */
	int SERVICE_SETTING = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__NAME = ABSTRACT_SETTING__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__ID = ABSTRACT_SETTING__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__DESCRIPTION = ABSTRACT_SETTING__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__UNIQUE_ID = ABSTRACT_SETTING__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Setting Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__SETTING_TYPE = ABSTRACT_SETTING__SETTING_TYPE;

	/**
	 * The feature id for the '<em><b>User Exposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__USER_EXPOSED = ABSTRACT_SETTING__USER_EXPOSED;

	/**
	 * The feature id for the '<em><b>User Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__USER_EDITABLE = ABSTRACT_SETTING__USER_EDITABLE;

	/**
	 * The feature id for the '<em><b>Can Be Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__CAN_BE_PUBLISHED = ABSTRACT_SETTING__CAN_BE_PUBLISHED;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__READABLE = ABSTRACT_SETTING__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__WRITABLE = ABSTRACT_SETTING__WRITABLE;

	/**
	 * The feature id for the '<em><b>Setting Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__SETTING_CONSTRAINTS = ABSTRACT_SETTING__SETTING_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Requires Params</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__REQUIRES_PARAMS = ABSTRACT_SETTING__REQUIRES_PARAMS;

	/**
	 * The feature id for the '<em><b>Mapped To Resource Settings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS = ABSTRACT_SETTING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Provided By Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING__PROVIDED_BY_RESOURCES = ABSTRACT_SETTING_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service Setting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SETTING_FEATURE_COUNT = ABSTRACT_SETTING_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.OfferedServiceImpl <em>Offered Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.OfferedServiceImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getOfferedService()
	 * @generated
	 */
	int OFFERED_SERVICE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE__UNIQUE_ID = SERVICE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Service Settings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE__SERVICE_SETTINGS = SERVICE__SERVICE_SETTINGS;

	/**
	 * The feature id for the '<em><b>Requires Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE__REQUIRES_SERVICES = SERVICE__REQUIRES_SERVICES;

	/**
	 * The number of structural features of the '<em>Offered Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.ServiceCompositionImpl <em>Service Composition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.ServiceCompositionImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getServiceComposition()
	 * @generated
	 */
	int SERVICE_COMPOSITION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPOSITION__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPOSITION__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPOSITION__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPOSITION__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPOSITION__HAS_SERVICES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service Composition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPOSITION_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.SettingTypeImpl <em>Setting Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.SettingTypeImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getSettingType()
	 * @generated
	 */
	int SETTING_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_TYPE__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_TYPE__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_TYPE__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_TYPE__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The number of structural features of the '<em>Setting Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_TYPE_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.tideTypeStringImpl <em>tide Type String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.tideTypeStringImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeString()
	 * @generated
	 */
	int TIDE_TYPE_STRING = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_STRING__NAME = SETTING_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_STRING__ID = SETTING_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_STRING__DESCRIPTION = SETTING_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_STRING__UNIQUE_ID = SETTING_TYPE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_STRING__DEFAULT_VALUE = SETTING_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>tide Type String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_STRING_FEATURE_COUNT = SETTING_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.tideTypeEnumImpl <em>tide Type Enum</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.tideTypeEnumImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeEnum()
	 * @generated
	 */
	int TIDE_TYPE_ENUM = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM__NAME = SETTING_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM__ID = SETTING_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM__DESCRIPTION = SETTING_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM__UNIQUE_ID = SETTING_TYPE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Tide Enumlist</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM__TIDE_ENUMLIST = SETTING_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM__DEFAULT_VALUE = SETTING_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>tide Type Enum</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM_FEATURE_COUNT = SETTING_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.tideTypeListImpl <em>tide Type List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.tideTypeListImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeList()
	 * @generated
	 */
	int TIDE_TYPE_LIST = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_LIST__NAME = SETTING_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_LIST__ID = SETTING_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_LIST__DESCRIPTION = SETTING_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_LIST__UNIQUE_ID = SETTING_TYPE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Contains Elements Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_LIST__CONTAINS_ELEMENTS_OF = SETTING_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>tide Type List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_LIST_FEATURE_COUNT = SETTING_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.tideTypeEnumItemImpl <em>tide Type Enum Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.tideTypeEnumItemImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeEnumItem()
	 * @generated
	 */
	int TIDE_TYPE_ENUM_ITEM = 8;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM_ITEM__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM_ITEM__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>tide Type Enum Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_ENUM_ITEM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.tideTypeTideElementImpl <em>tide Type Tide Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.tideTypeTideElementImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeTideElement()
	 * @generated
	 */
	int TIDE_TYPE_TIDE_ELEMENT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_TIDE_ELEMENT__NAME = SETTING_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_TIDE_ELEMENT__ID = SETTING_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_TIDE_ELEMENT__DESCRIPTION = SETTING_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_TIDE_ELEMENT__UNIQUE_ID = SETTING_TYPE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Of Tide Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_TIDE_ELEMENT__OF_TIDE_ELEMENT = SETTING_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>tide Type Tide Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIDE_TYPE_TIDE_ELEMENT_FEATURE_COUNT = SETTING_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.TaxonomyImpl <em>Taxonomy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.TaxonomyImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getTaxonomy()
	 * @generated
	 */
	int TAXONOMY = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Taxonomies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__TAXONOMIES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__CATEGORIES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Has Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__HAS_SCENARIOS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Has Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY__HAS_SERVICES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Taxonomy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAXONOMY_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link FederationOffice.services.impl.SettingConstraintImpl <em>Setting Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.impl.SettingConstraintImpl
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getSettingConstraint()
	 * @generated
	 */
	int SETTING_CONSTRAINT = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>For Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__FOR_OPERATION = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Available After Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Required Before Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Setting Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTING_CONSTRAINT_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link FederationOffice.services.ServiceResourceOperation <em>Service Resource Operation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.services.ServiceResourceOperation
	 * @see FederationOffice.services.impl.ServicesPackageImpl#getServiceResourceOperation()
	 * @generated
	 */
	int SERVICE_RESOURCE_OPERATION = 13;

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see FederationOffice.services.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.services.Service#getServiceSettings <em>Service Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Settings</em>'.
	 * @see FederationOffice.services.Service#getServiceSettings()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_ServiceSettings();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.Service#getRequiresServices <em>Requires Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires Services</em>'.
	 * @see FederationOffice.services.Service#getRequiresServices()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_RequiresServices();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.ServiceSetting <em>Service Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Setting</em>'.
	 * @see FederationOffice.services.ServiceSetting
	 * @generated
	 */
	EClass getServiceSetting();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.ServiceSetting#getMappedToResourceSettings <em>Mapped To Resource Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mapped To Resource Settings</em>'.
	 * @see FederationOffice.services.ServiceSetting#getMappedToResourceSettings()
	 * @see #getServiceSetting()
	 * @generated
	 */
	EReference getServiceSetting_MappedToResourceSettings();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.ServiceSetting#getProvidedByResources <em>Provided By Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Provided By Resources</em>'.
	 * @see FederationOffice.services.ServiceSetting#getProvidedByResources()
	 * @see #getServiceSetting()
	 * @generated
	 */
	EReference getServiceSetting_ProvidedByResources();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.OfferedService <em>Offered Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Offered Service</em>'.
	 * @see FederationOffice.services.OfferedService
	 * @generated
	 */
	EClass getOfferedService();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.ServiceComposition <em>Service Composition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Composition</em>'.
	 * @see FederationOffice.services.ServiceComposition
	 * @generated
	 */
	EClass getServiceComposition();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.ServiceComposition#getHasServices <em>Has Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Has Services</em>'.
	 * @see FederationOffice.services.ServiceComposition#getHasServices()
	 * @see #getServiceComposition()
	 * @generated
	 */
	EReference getServiceComposition_HasServices();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.SettingType <em>Setting Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Setting Type</em>'.
	 * @see FederationOffice.services.SettingType
	 * @generated
	 */
	EClass getSettingType();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.tideTypeString <em>tide Type String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>tide Type String</em>'.
	 * @see FederationOffice.services.tideTypeString
	 * @generated
	 */
	EClass gettideTypeString();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.tideTypeString#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see FederationOffice.services.tideTypeString#getDefaultValue()
	 * @see #gettideTypeString()
	 * @generated
	 */
	EAttribute gettideTypeString_DefaultValue();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.tideTypeEnum <em>tide Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>tide Type Enum</em>'.
	 * @see FederationOffice.services.tideTypeEnum
	 * @generated
	 */
	EClass gettideTypeEnum();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.services.tideTypeEnum#getTideEnumlist <em>Tide Enumlist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tide Enumlist</em>'.
	 * @see FederationOffice.services.tideTypeEnum#getTideEnumlist()
	 * @see #gettideTypeEnum()
	 * @generated
	 */
	EReference gettideTypeEnum_TideEnumlist();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.services.tideTypeEnum#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Value</em>'.
	 * @see FederationOffice.services.tideTypeEnum#getDefaultValue()
	 * @see #gettideTypeEnum()
	 * @generated
	 */
	EReference gettideTypeEnum_DefaultValue();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.tideTypeList <em>tide Type List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>tide Type List</em>'.
	 * @see FederationOffice.services.tideTypeList
	 * @generated
	 */
	EClass gettideTypeList();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.services.tideTypeList#getContainsElementsOf <em>Contains Elements Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contains Elements Of</em>'.
	 * @see FederationOffice.services.tideTypeList#getContainsElementsOf()
	 * @see #gettideTypeList()
	 * @generated
	 */
	EReference gettideTypeList_ContainsElementsOf();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.tideTypeEnumItem <em>tide Type Enum Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>tide Type Enum Item</em>'.
	 * @see FederationOffice.services.tideTypeEnumItem
	 * @generated
	 */
	EClass gettideTypeEnumItem();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.tideTypeEnumItem#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see FederationOffice.services.tideTypeEnumItem#getValue()
	 * @see #gettideTypeEnumItem()
	 * @generated
	 */
	EAttribute gettideTypeEnumItem_Value();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.tideTypeEnumItem#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see FederationOffice.services.tideTypeEnumItem#getDescription()
	 * @see #gettideTypeEnumItem()
	 * @generated
	 */
	EAttribute gettideTypeEnumItem_Description();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.tideTypeTideElement <em>tide Type Tide Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>tide Type Tide Element</em>'.
	 * @see FederationOffice.services.tideTypeTideElement
	 * @generated
	 */
	EClass gettideTypeTideElement();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.services.tideTypeTideElement#getOfTideElement <em>Of Tide Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Of Tide Element</em>'.
	 * @see FederationOffice.services.tideTypeTideElement#getOfTideElement()
	 * @see #gettideTypeTideElement()
	 * @generated
	 */
	EReference gettideTypeTideElement_OfTideElement();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.Taxonomy <em>Taxonomy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Taxonomy</em>'.
	 * @see FederationOffice.services.Taxonomy
	 * @generated
	 */
	EClass getTaxonomy();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.services.Taxonomy#getTaxonomies <em>Taxonomies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Taxonomies</em>'.
	 * @see FederationOffice.services.Taxonomy#getTaxonomies()
	 * @see #getTaxonomy()
	 * @generated
	 */
	EReference getTaxonomy_Taxonomies();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.services.Taxonomy#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see FederationOffice.services.Taxonomy#getCategories()
	 * @see #getTaxonomy()
	 * @generated
	 */
	EReference getTaxonomy_Categories();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.Taxonomy#getHasScenarios <em>Has Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Has Scenarios</em>'.
	 * @see FederationOffice.services.Taxonomy#getHasScenarios()
	 * @see #getTaxonomy()
	 * @generated
	 */
	EReference getTaxonomy_HasScenarios();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.Taxonomy#getHasServices <em>Has Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Has Services</em>'.
	 * @see FederationOffice.services.Taxonomy#getHasServices()
	 * @see #getTaxonomy()
	 * @generated
	 */
	EReference getTaxonomy_HasServices();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.AbstractSetting <em>Abstract Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Setting</em>'.
	 * @see FederationOffice.services.AbstractSetting
	 * @generated
	 */
	EClass getAbstractSetting();

	/**
	 * Returns the meta object for the containment reference '{@link FederationOffice.services.AbstractSetting#getSettingType <em>Setting Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Setting Type</em>'.
	 * @see FederationOffice.services.AbstractSetting#getSettingType()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EReference getAbstractSetting_SettingType();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.AbstractSetting#isUserExposed <em>User Exposed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Exposed</em>'.
	 * @see FederationOffice.services.AbstractSetting#isUserExposed()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EAttribute getAbstractSetting_UserExposed();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.AbstractSetting#isUserEditable <em>User Editable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Editable</em>'.
	 * @see FederationOffice.services.AbstractSetting#isUserEditable()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EAttribute getAbstractSetting_UserEditable();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.AbstractSetting#isCanBePublished <em>Can Be Published</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Be Published</em>'.
	 * @see FederationOffice.services.AbstractSetting#isCanBePublished()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EAttribute getAbstractSetting_CanBePublished();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.AbstractSetting#isReadable <em>Readable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Readable</em>'.
	 * @see FederationOffice.services.AbstractSetting#isReadable()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EAttribute getAbstractSetting_Readable();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.AbstractSetting#isWritable <em>Writable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Writable</em>'.
	 * @see FederationOffice.services.AbstractSetting#isWritable()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EAttribute getAbstractSetting_Writable();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.services.AbstractSetting#getSettingConstraints <em>Setting Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Setting Constraints</em>'.
	 * @see FederationOffice.services.AbstractSetting#getSettingConstraints()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EReference getAbstractSetting_SettingConstraints();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.services.AbstractSetting#getRequiresParams <em>Requires Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires Params</em>'.
	 * @see FederationOffice.services.AbstractSetting#getRequiresParams()
	 * @see #getAbstractSetting()
	 * @generated
	 */
	EReference getAbstractSetting_RequiresParams();

	/**
	 * Returns the meta object for class '{@link FederationOffice.services.SettingConstraint <em>Setting Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Setting Constraint</em>'.
	 * @see FederationOffice.services.SettingConstraint
	 * @generated
	 */
	EClass getSettingConstraint();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.SettingConstraint#getForOperation <em>For Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>For Operation</em>'.
	 * @see FederationOffice.services.SettingConstraint#getForOperation()
	 * @see #getSettingConstraint()
	 * @generated
	 */
	EAttribute getSettingConstraint_ForOperation();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.SettingConstraint#isAvailableAfterOperation <em>Available After Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Available After Operation</em>'.
	 * @see FederationOffice.services.SettingConstraint#isAvailableAfterOperation()
	 * @see #getSettingConstraint()
	 * @generated
	 */
	EAttribute getSettingConstraint_AvailableAfterOperation();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.services.SettingConstraint#isRequiredBeforeOperation <em>Required Before Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required Before Operation</em>'.
	 * @see FederationOffice.services.SettingConstraint#isRequiredBeforeOperation()
	 * @see #getSettingConstraint()
	 * @generated
	 */
	EAttribute getSettingConstraint_RequiredBeforeOperation();

	/**
	 * Returns the meta object for enum '{@link FederationOffice.services.ServiceResourceOperation <em>Service Resource Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Service Resource Operation</em>'.
	 * @see FederationOffice.services.ServiceResourceOperation
	 * @generated
	 */
	EEnum getServiceResourceOperation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ServicesFactory getServicesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.ServiceImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Service Settings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__SERVICE_SETTINGS = eINSTANCE.getService_ServiceSettings();

		/**
		 * The meta object literal for the '<em><b>Requires Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__REQUIRES_SERVICES = eINSTANCE.getService_RequiresServices();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.ServiceSettingImpl <em>Service Setting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.ServiceSettingImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getServiceSetting()
		 * @generated
		 */
		EClass SERVICE_SETTING = eINSTANCE.getServiceSetting();

		/**
		 * The meta object literal for the '<em><b>Mapped To Resource Settings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_SETTING__MAPPED_TO_RESOURCE_SETTINGS = eINSTANCE.getServiceSetting_MappedToResourceSettings();

		/**
		 * The meta object literal for the '<em><b>Provided By Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_SETTING__PROVIDED_BY_RESOURCES = eINSTANCE.getServiceSetting_ProvidedByResources();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.OfferedServiceImpl <em>Offered Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.OfferedServiceImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getOfferedService()
		 * @generated
		 */
		EClass OFFERED_SERVICE = eINSTANCE.getOfferedService();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.ServiceCompositionImpl <em>Service Composition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.ServiceCompositionImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getServiceComposition()
		 * @generated
		 */
		EClass SERVICE_COMPOSITION = eINSTANCE.getServiceComposition();

		/**
		 * The meta object literal for the '<em><b>Has Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_COMPOSITION__HAS_SERVICES = eINSTANCE.getServiceComposition_HasServices();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.SettingTypeImpl <em>Setting Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.SettingTypeImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getSettingType()
		 * @generated
		 */
		EClass SETTING_TYPE = eINSTANCE.getSettingType();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.tideTypeStringImpl <em>tide Type String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.tideTypeStringImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeString()
		 * @generated
		 */
		EClass TIDE_TYPE_STRING = eINSTANCE.gettideTypeString();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIDE_TYPE_STRING__DEFAULT_VALUE = eINSTANCE.gettideTypeString_DefaultValue();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.tideTypeEnumImpl <em>tide Type Enum</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.tideTypeEnumImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeEnum()
		 * @generated
		 */
		EClass TIDE_TYPE_ENUM = eINSTANCE.gettideTypeEnum();

		/**
		 * The meta object literal for the '<em><b>Tide Enumlist</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIDE_TYPE_ENUM__TIDE_ENUMLIST = eINSTANCE.gettideTypeEnum_TideEnumlist();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIDE_TYPE_ENUM__DEFAULT_VALUE = eINSTANCE.gettideTypeEnum_DefaultValue();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.tideTypeListImpl <em>tide Type List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.tideTypeListImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeList()
		 * @generated
		 */
		EClass TIDE_TYPE_LIST = eINSTANCE.gettideTypeList();

		/**
		 * The meta object literal for the '<em><b>Contains Elements Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIDE_TYPE_LIST__CONTAINS_ELEMENTS_OF = eINSTANCE.gettideTypeList_ContainsElementsOf();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.tideTypeEnumItemImpl <em>tide Type Enum Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.tideTypeEnumItemImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeEnumItem()
		 * @generated
		 */
		EClass TIDE_TYPE_ENUM_ITEM = eINSTANCE.gettideTypeEnumItem();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIDE_TYPE_ENUM_ITEM__VALUE = eINSTANCE.gettideTypeEnumItem_Value();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIDE_TYPE_ENUM_ITEM__DESCRIPTION = eINSTANCE.gettideTypeEnumItem_Description();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.tideTypeTideElementImpl <em>tide Type Tide Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.tideTypeTideElementImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#gettideTypeTideElement()
		 * @generated
		 */
		EClass TIDE_TYPE_TIDE_ELEMENT = eINSTANCE.gettideTypeTideElement();

		/**
		 * The meta object literal for the '<em><b>Of Tide Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIDE_TYPE_TIDE_ELEMENT__OF_TIDE_ELEMENT = eINSTANCE.gettideTypeTideElement_OfTideElement();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.TaxonomyImpl <em>Taxonomy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.TaxonomyImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getTaxonomy()
		 * @generated
		 */
		EClass TAXONOMY = eINSTANCE.getTaxonomy();

		/**
		 * The meta object literal for the '<em><b>Taxonomies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAXONOMY__TAXONOMIES = eINSTANCE.getTaxonomy_Taxonomies();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAXONOMY__CATEGORIES = eINSTANCE.getTaxonomy_Categories();

		/**
		 * The meta object literal for the '<em><b>Has Scenarios</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAXONOMY__HAS_SCENARIOS = eINSTANCE.getTaxonomy_HasScenarios();

		/**
		 * The meta object literal for the '<em><b>Has Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAXONOMY__HAS_SERVICES = eINSTANCE.getTaxonomy_HasServices();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.AbstractSettingImpl <em>Abstract Setting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.AbstractSettingImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getAbstractSetting()
		 * @generated
		 */
		EClass ABSTRACT_SETTING = eINSTANCE.getAbstractSetting();

		/**
		 * The meta object literal for the '<em><b>Setting Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_SETTING__SETTING_TYPE = eINSTANCE.getAbstractSetting_SettingType();

		/**
		 * The meta object literal for the '<em><b>User Exposed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_SETTING__USER_EXPOSED = eINSTANCE.getAbstractSetting_UserExposed();

		/**
		 * The meta object literal for the '<em><b>User Editable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_SETTING__USER_EDITABLE = eINSTANCE.getAbstractSetting_UserEditable();

		/**
		 * The meta object literal for the '<em><b>Can Be Published</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_SETTING__CAN_BE_PUBLISHED = eINSTANCE.getAbstractSetting_CanBePublished();

		/**
		 * The meta object literal for the '<em><b>Readable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_SETTING__READABLE = eINSTANCE.getAbstractSetting_Readable();

		/**
		 * The meta object literal for the '<em><b>Writable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_SETTING__WRITABLE = eINSTANCE.getAbstractSetting_Writable();

		/**
		 * The meta object literal for the '<em><b>Setting Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_SETTING__SETTING_CONSTRAINTS = eINSTANCE.getAbstractSetting_SettingConstraints();

		/**
		 * The meta object literal for the '<em><b>Requires Params</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_SETTING__REQUIRES_PARAMS = eINSTANCE.getAbstractSetting_RequiresParams();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.impl.SettingConstraintImpl <em>Setting Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.impl.SettingConstraintImpl
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getSettingConstraint()
		 * @generated
		 */
		EClass SETTING_CONSTRAINT = eINSTANCE.getSettingConstraint();

		/**
		 * The meta object literal for the '<em><b>For Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SETTING_CONSTRAINT__FOR_OPERATION = eINSTANCE.getSettingConstraint_ForOperation();

		/**
		 * The meta object literal for the '<em><b>Available After Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION = eINSTANCE.getSettingConstraint_AvailableAfterOperation();

		/**
		 * The meta object literal for the '<em><b>Required Before Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION = eINSTANCE.getSettingConstraint_RequiredBeforeOperation();

		/**
		 * The meta object literal for the '{@link FederationOffice.services.ServiceResourceOperation <em>Service Resource Operation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.services.ServiceResourceOperation
		 * @see FederationOffice.services.impl.ServicesPackageImpl#getServiceResourceOperation()
		 * @generated
		 */
		EEnum SERVICE_RESOURCE_OPERATION = eINSTANCE.getServiceResourceOperation();

	}

} //ServicesPackage
