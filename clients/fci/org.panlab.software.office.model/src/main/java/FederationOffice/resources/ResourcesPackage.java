/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import FederationOffice.FederationOfficePackage;
import FederationOffice.services.ServicesPackage;

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
 * @see FederationOffice.resources.ResourcesFactory
 * @model kind="package"
 * @generated
 */
public interface ResourcesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "resources";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/resources";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.resources";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ResourcesPackage eINSTANCE = FederationOffice.resources.impl.ResourcesPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.resources.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.resources.impl.ResourceImpl
	 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Resource Settings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__RESOURCE_SETTINGS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contributes To Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CONTRIBUTES_TO_CATEGORIES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requires Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REQUIRES_RESOURCES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link FederationOffice.resources.impl.OfferedResourceImpl <em>Offered Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.resources.impl.OfferedResourceImpl
	 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getOfferedResource()
	 * @generated
	 */
	int OFFERED_RESOURCE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__NAME = RESOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__ID = RESOURCE__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__DESCRIPTION = RESOURCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__UNIQUE_ID = RESOURCE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Resource Settings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__RESOURCE_SETTINGS = RESOURCE__RESOURCE_SETTINGS;

	/**
	 * The feature id for the '<em><b>Contributes To Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__CONTRIBUTES_TO_CATEGORIES = RESOURCE__CONTRIBUTES_TO_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Requires Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__REQUIRES_RESOURCES = RESOURCE__REQUIRES_RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__RESOURCE_TYPE = RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Belongs To Site</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__BELONGS_TO_SITE = RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Impl Offered Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__IMPL_OFFERED_SERVICE = RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Multiton Max Occur</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE__MULTITON_MAX_OCCUR = RESOURCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Offered Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERED_RESOURCE_FEATURE_COUNT = RESOURCE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link FederationOffice.resources.impl.ResourceSettingImpl <em>Resource Setting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.resources.impl.ResourceSettingImpl
	 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResourceSetting()
	 * @generated
	 */
	int RESOURCE_SETTING = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__NAME = ServicesPackage.ABSTRACT_SETTING__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__ID = ServicesPackage.ABSTRACT_SETTING__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__DESCRIPTION = ServicesPackage.ABSTRACT_SETTING__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__UNIQUE_ID = ServicesPackage.ABSTRACT_SETTING__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Setting Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__SETTING_TYPE = ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE;

	/**
	 * The feature id for the '<em><b>User Exposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__USER_EXPOSED = ServicesPackage.ABSTRACT_SETTING__USER_EXPOSED;

	/**
	 * The feature id for the '<em><b>User Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__USER_EDITABLE = ServicesPackage.ABSTRACT_SETTING__USER_EDITABLE;

	/**
	 * The feature id for the '<em><b>Can Be Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__CAN_BE_PUBLISHED = ServicesPackage.ABSTRACT_SETTING__CAN_BE_PUBLISHED;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__READABLE = ServicesPackage.ABSTRACT_SETTING__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__WRITABLE = ServicesPackage.ABSTRACT_SETTING__WRITABLE;

	/**
	 * The feature id for the '<em><b>Setting Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__SETTING_CONSTRAINTS = ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Requires Params</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__REQUIRES_PARAMS = ServicesPackage.ABSTRACT_SETTING__REQUIRES_PARAMS;

	/**
	 * The feature id for the '<em><b>Only Configured By Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES = ServicesPackage.ABSTRACT_SETTING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Impl Service Setting</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING__IMPL_SERVICE_SETTING = ServicesPackage.ABSTRACT_SETTING_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource Setting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SETTING_FEATURE_COUNT = ServicesPackage.ABSTRACT_SETTING_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.resources.impl.ResourceCategoryImpl <em>Resource Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.resources.impl.ResourceCategoryImpl
	 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResourceCategory()
	 * @generated
	 */
	int RESOURCE_CATEGORY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CATEGORY__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CATEGORY__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CATEGORY__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CATEGORY__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Resourcelist</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CATEGORY__RESOURCELIST = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CATEGORY_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.resources.ResourceType <em>Resource Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.resources.ResourceType
	 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResourceType()
	 * @generated
	 */
	int RESOURCE_TYPE = 4;


	/**
	 * Returns the meta object for class '{@link FederationOffice.resources.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see FederationOffice.resources.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.resources.Resource#getResourceSettings <em>Resource Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Settings</em>'.
	 * @see FederationOffice.resources.Resource#getResourceSettings()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_ResourceSettings();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.resources.Resource#getContributesToCategories <em>Contributes To Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributes To Categories</em>'.
	 * @see FederationOffice.resources.Resource#getContributesToCategories()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_ContributesToCategories();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.resources.Resource#getRequiresResources <em>Requires Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires Resources</em>'.
	 * @see FederationOffice.resources.Resource#getRequiresResources()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_RequiresResources();

	/**
	 * Returns the meta object for class '{@link FederationOffice.resources.OfferedResource <em>Offered Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Offered Resource</em>'.
	 * @see FederationOffice.resources.OfferedResource
	 * @generated
	 */
	EClass getOfferedResource();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.resources.OfferedResource#getResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Type</em>'.
	 * @see FederationOffice.resources.OfferedResource#getResourceType()
	 * @see #getOfferedResource()
	 * @generated
	 */
	EAttribute getOfferedResource_ResourceType();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.resources.OfferedResource#getMultitonMaxOccur <em>Multiton Max Occur</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiton Max Occur</em>'.
	 * @see FederationOffice.resources.OfferedResource#getMultitonMaxOccur()
	 * @see #getOfferedResource()
	 * @generated
	 */
	EAttribute getOfferedResource_MultitonMaxOccur();

	/**
	 * Returns the meta object for the container reference '{@link FederationOffice.resources.OfferedResource#getBelongsToSite <em>Belongs To Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Belongs To Site</em>'.
	 * @see FederationOffice.resources.OfferedResource#getBelongsToSite()
	 * @see #getOfferedResource()
	 * @generated
	 */
	EReference getOfferedResource_BelongsToSite();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.resources.OfferedResource#getImplOfferedService <em>Impl Offered Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Impl Offered Service</em>'.
	 * @see FederationOffice.resources.OfferedResource#getImplOfferedService()
	 * @see #getOfferedResource()
	 * @generated
	 */
	EReference getOfferedResource_ImplOfferedService();

	/**
	 * Returns the meta object for class '{@link FederationOffice.resources.ResourceSetting <em>Resource Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Setting</em>'.
	 * @see FederationOffice.resources.ResourceSetting
	 * @generated
	 */
	EClass getResourceSetting();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.resources.ResourceSetting#getOnlyConfiguredByResources <em>Only Configured By Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Only Configured By Resources</em>'.
	 * @see FederationOffice.resources.ResourceSetting#getOnlyConfiguredByResources()
	 * @see #getResourceSetting()
	 * @generated
	 */
	EReference getResourceSetting_OnlyConfiguredByResources();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.resources.ResourceSetting#getImplServiceSetting <em>Impl Service Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Impl Service Setting</em>'.
	 * @see FederationOffice.resources.ResourceSetting#getImplServiceSetting()
	 * @see #getResourceSetting()
	 * @generated
	 */
	EReference getResourceSetting_ImplServiceSetting();

	/**
	 * Returns the meta object for class '{@link FederationOffice.resources.ResourceCategory <em>Resource Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Category</em>'.
	 * @see FederationOffice.resources.ResourceCategory
	 * @generated
	 */
	EClass getResourceCategory();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.resources.ResourceCategory#getResourcelist <em>Resourcelist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resourcelist</em>'.
	 * @see FederationOffice.resources.ResourceCategory#getResourcelist()
	 * @see #getResourceCategory()
	 * @generated
	 */
	EReference getResourceCategory_Resourcelist();

	/**
	 * Returns the meta object for enum '{@link FederationOffice.resources.ResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Resource Type</em>'.
	 * @see FederationOffice.resources.ResourceType
	 * @generated
	 */
	EEnum getResourceType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ResourcesFactory getResourcesFactory();

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
		 * The meta object literal for the '{@link FederationOffice.resources.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.resources.impl.ResourceImpl
		 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Resource Settings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__RESOURCE_SETTINGS = eINSTANCE.getResource_ResourceSettings();

		/**
		 * The meta object literal for the '<em><b>Contributes To Categories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__CONTRIBUTES_TO_CATEGORIES = eINSTANCE.getResource_ContributesToCategories();

		/**
		 * The meta object literal for the '<em><b>Requires Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__REQUIRES_RESOURCES = eINSTANCE.getResource_RequiresResources();

		/**
		 * The meta object literal for the '{@link FederationOffice.resources.impl.OfferedResourceImpl <em>Offered Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.resources.impl.OfferedResourceImpl
		 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getOfferedResource()
		 * @generated
		 */
		EClass OFFERED_RESOURCE = eINSTANCE.getOfferedResource();

		/**
		 * The meta object literal for the '<em><b>Resource Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFERED_RESOURCE__RESOURCE_TYPE = eINSTANCE.getOfferedResource_ResourceType();

		/**
		 * The meta object literal for the '<em><b>Multiton Max Occur</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFERED_RESOURCE__MULTITON_MAX_OCCUR = eINSTANCE.getOfferedResource_MultitonMaxOccur();

		/**
		 * The meta object literal for the '<em><b>Belongs To Site</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFERED_RESOURCE__BELONGS_TO_SITE = eINSTANCE.getOfferedResource_BelongsToSite();

		/**
		 * The meta object literal for the '<em><b>Impl Offered Service</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFERED_RESOURCE__IMPL_OFFERED_SERVICE = eINSTANCE.getOfferedResource_ImplOfferedService();

		/**
		 * The meta object literal for the '{@link FederationOffice.resources.impl.ResourceSettingImpl <em>Resource Setting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.resources.impl.ResourceSettingImpl
		 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResourceSetting()
		 * @generated
		 */
		EClass RESOURCE_SETTING = eINSTANCE.getResourceSetting();

		/**
		 * The meta object literal for the '<em><b>Only Configured By Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES = eINSTANCE.getResourceSetting_OnlyConfiguredByResources();

		/**
		 * The meta object literal for the '<em><b>Impl Service Setting</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SETTING__IMPL_SERVICE_SETTING = eINSTANCE.getResourceSetting_ImplServiceSetting();

		/**
		 * The meta object literal for the '{@link FederationOffice.resources.impl.ResourceCategoryImpl <em>Resource Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.resources.impl.ResourceCategoryImpl
		 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResourceCategory()
		 * @generated
		 */
		EClass RESOURCE_CATEGORY = eINSTANCE.getResourceCategory();

		/**
		 * The meta object literal for the '<em><b>Resourcelist</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_CATEGORY__RESOURCELIST = eINSTANCE.getResourceCategory_Resourcelist();

		/**
		 * The meta object literal for the '{@link FederationOffice.resources.ResourceType <em>Resource Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.resources.ResourceType
		 * @see FederationOffice.resources.impl.ResourcesPackageImpl#getResourceType()
		 * @generated
		 */
		EEnum RESOURCE_TYPE = eINSTANCE.getResourceType();

	}

} //ResourcesPackage
