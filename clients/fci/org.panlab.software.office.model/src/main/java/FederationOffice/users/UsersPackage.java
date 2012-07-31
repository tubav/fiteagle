/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see FederationOffice.users.UsersFactory
 * @model kind="package"
 * @generated
 */
public interface UsersPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "users";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/users";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.users";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UsersPackage eINSTANCE = FederationOffice.users.impl.UsersPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.AccountImpl <em>Account</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.AccountImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getAccount()
	 * @generated
	 */
	int ACCOUNT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__PASSWORD = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__USERNAME = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Account</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.OfficeUserImpl <em>Office User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.OfficeUserImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getOfficeUser()
	 * @generated
	 */
	int OFFICE_USER = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__HAS_ACCOUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__ORGANIZATION = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__ADDRESS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER__TELEPHONE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Office User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_USER_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.ResourcesProviderImpl <em>Resources Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.ResourcesProviderImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getResourcesProvider()
	 * @generated
	 */
	int RESOURCES_PROVIDER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__NAME = OFFICE_USER__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__ID = OFFICE_USER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__DESCRIPTION = OFFICE_USER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__UNIQUE_ID = OFFICE_USER__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__HAS_ACCOUNT = OFFICE_USER__HAS_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__ORGANIZATION = OFFICE_USER__ORGANIZATION;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__ADDRESS = OFFICE_USER__ADDRESS;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__TELEPHONE = OFFICE_USER__TELEPHONE;

	/**
	 * The feature id for the '<em><b>Offered Site List</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER__OFFERED_SITE_LIST = OFFICE_USER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resources Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_PROVIDER_FEATURE_COUNT = OFFICE_USER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.TestbedDesignerImpl <em>Testbed Designer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.TestbedDesignerImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getTestbedDesigner()
	 * @generated
	 */
	int TESTBED_DESIGNER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__NAME = OFFICE_USER__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__ID = OFFICE_USER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__DESCRIPTION = OFFICE_USER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__UNIQUE_ID = OFFICE_USER__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__HAS_ACCOUNT = OFFICE_USER__HAS_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__ORGANIZATION = OFFICE_USER__ORGANIZATION;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__ADDRESS = OFFICE_USER__ADDRESS;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__TELEPHONE = OFFICE_USER__TELEPHONE;

	/**
	 * The feature id for the '<em><b>Designs Virtual Testbeds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER__DESIGNS_VIRTUAL_TESTBEDS = OFFICE_USER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Testbed Designer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESTBED_DESIGNER_FEATURE_COUNT = OFFICE_USER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.OfficePersonelImpl <em>Office Personel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.OfficePersonelImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getOfficePersonel()
	 * @generated
	 */
	int OFFICE_PERSONEL = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__NAME = OFFICE_USER__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__ID = OFFICE_USER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__DESCRIPTION = OFFICE_USER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__UNIQUE_ID = OFFICE_USER__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__HAS_ACCOUNT = OFFICE_USER__HAS_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__ORGANIZATION = OFFICE_USER__ORGANIZATION;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__ADDRESS = OFFICE_USER__ADDRESS;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL__TELEPHONE = OFFICE_USER__TELEPHONE;

	/**
	 * The number of structural features of the '<em>Office Personel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_PERSONEL_FEATURE_COUNT = OFFICE_USER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.AdminImpl <em>Admin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.AdminImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getAdmin()
	 * @generated
	 */
	int ADMIN = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__NAME = OFFICE_PERSONEL__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__ID = OFFICE_PERSONEL__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__DESCRIPTION = OFFICE_PERSONEL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__UNIQUE_ID = OFFICE_PERSONEL__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__HAS_ACCOUNT = OFFICE_PERSONEL__HAS_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__ORGANIZATION = OFFICE_PERSONEL__ORGANIZATION;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__ADDRESS = OFFICE_PERSONEL__ADDRESS;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__TELEPHONE = OFFICE_PERSONEL__TELEPHONE;

	/**
	 * The number of structural features of the '<em>Admin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_FEATURE_COUNT = OFFICE_PERSONEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link FederationOffice.users.impl.OfficeCustomerImpl <em>Office Customer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.users.impl.OfficeCustomerImpl
	 * @see FederationOffice.users.impl.UsersPackageImpl#getOfficeCustomer()
	 * @generated
	 */
	int OFFICE_CUSTOMER = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__NAME = OFFICE_USER__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__ID = OFFICE_USER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__DESCRIPTION = OFFICE_USER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__UNIQUE_ID = OFFICE_USER__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Has Account</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__HAS_ACCOUNT = OFFICE_USER__HAS_ACCOUNT;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__ORGANIZATION = OFFICE_USER__ORGANIZATION;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__ADDRESS = OFFICE_USER__ADDRESS;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__TELEPHONE = OFFICE_USER__TELEPHONE;

	/**
	 * The feature id for the '<em><b>Utilizes Virtual Testbed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED = OFFICE_USER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Has Testbed Designer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER = OFFICE_USER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Office Customer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_CUSTOMER_FEATURE_COUNT = OFFICE_USER_FEATURE_COUNT + 2;

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.Account <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Account</em>'.
	 * @see FederationOffice.users.Account
	 * @generated
	 */
	EClass getAccount();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.users.Account#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see FederationOffice.users.Account#getPassword()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Password();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.users.Account#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Username</em>'.
	 * @see FederationOffice.users.Account#getUsername()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Username();

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.ResourcesProvider <em>Resources Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resources Provider</em>'.
	 * @see FederationOffice.users.ResourcesProvider
	 * @generated
	 */
	EClass getResourcesProvider();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.users.ResourcesProvider#getOfferedSiteList <em>Offered Site List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Offered Site List</em>'.
	 * @see FederationOffice.users.ResourcesProvider#getOfferedSiteList()
	 * @see #getResourcesProvider()
	 * @generated
	 */
	EReference getResourcesProvider_OfferedSiteList();

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.TestbedDesigner <em>Testbed Designer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Testbed Designer</em>'.
	 * @see FederationOffice.users.TestbedDesigner
	 * @generated
	 */
	EClass getTestbedDesigner();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.users.TestbedDesigner#getDesignsVirtualTestbeds <em>Designs Virtual Testbeds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Designs Virtual Testbeds</em>'.
	 * @see FederationOffice.users.TestbedDesigner#getDesignsVirtualTestbeds()
	 * @see #getTestbedDesigner()
	 * @generated
	 */
	EReference getTestbedDesigner_DesignsVirtualTestbeds();

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.Admin <em>Admin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Admin</em>'.
	 * @see FederationOffice.users.Admin
	 * @generated
	 */
	EClass getAdmin();

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.OfficeCustomer <em>Office Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Office Customer</em>'.
	 * @see FederationOffice.users.OfficeCustomer
	 * @generated
	 */
	EClass getOfficeCustomer();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.users.OfficeCustomer#getUtilizesVirtualTestbed <em>Utilizes Virtual Testbed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Utilizes Virtual Testbed</em>'.
	 * @see FederationOffice.users.OfficeCustomer#getUtilizesVirtualTestbed()
	 * @see #getOfficeCustomer()
	 * @generated
	 */
	EReference getOfficeCustomer_UtilizesVirtualTestbed();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.users.OfficeCustomer#getHasTestbedDesigner <em>Has Testbed Designer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Has Testbed Designer</em>'.
	 * @see FederationOffice.users.OfficeCustomer#getHasTestbedDesigner()
	 * @see #getOfficeCustomer()
	 * @generated
	 */
	EReference getOfficeCustomer_HasTestbedDesigner();

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.OfficeUser <em>Office User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Office User</em>'.
	 * @see FederationOffice.users.OfficeUser
	 * @generated
	 */
	EClass getOfficeUser();

	/**
	 * Returns the meta object for the containment reference '{@link FederationOffice.users.OfficeUser#getHasAccount <em>Has Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Has Account</em>'.
	 * @see FederationOffice.users.OfficeUser#getHasAccount()
	 * @see #getOfficeUser()
	 * @generated
	 */
	EReference getOfficeUser_HasAccount();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.users.OfficeUser#getOrganization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Organization</em>'.
	 * @see FederationOffice.users.OfficeUser#getOrganization()
	 * @see #getOfficeUser()
	 * @generated
	 */
	EAttribute getOfficeUser_Organization();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.users.OfficeUser#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see FederationOffice.users.OfficeUser#getAddress()
	 * @see #getOfficeUser()
	 * @generated
	 */
	EAttribute getOfficeUser_Address();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.users.OfficeUser#getTelephone <em>Telephone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Telephone</em>'.
	 * @see FederationOffice.users.OfficeUser#getTelephone()
	 * @see #getOfficeUser()
	 * @generated
	 */
	EAttribute getOfficeUser_Telephone();

	/**
	 * Returns the meta object for class '{@link FederationOffice.users.OfficePersonel <em>Office Personel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Office Personel</em>'.
	 * @see FederationOffice.users.OfficePersonel
	 * @generated
	 */
	EClass getOfficePersonel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UsersFactory getUsersFactory();

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
		 * The meta object literal for the '{@link FederationOffice.users.impl.AccountImpl <em>Account</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.AccountImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getAccount()
		 * @generated
		 */
		EClass ACCOUNT = eINSTANCE.getAccount();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__PASSWORD = eINSTANCE.getAccount_Password();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__USERNAME = eINSTANCE.getAccount_Username();

		/**
		 * The meta object literal for the '{@link FederationOffice.users.impl.ResourcesProviderImpl <em>Resources Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.ResourcesProviderImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getResourcesProvider()
		 * @generated
		 */
		EClass RESOURCES_PROVIDER = eINSTANCE.getResourcesProvider();

		/**
		 * The meta object literal for the '<em><b>Offered Site List</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES_PROVIDER__OFFERED_SITE_LIST = eINSTANCE.getResourcesProvider_OfferedSiteList();

		/**
		 * The meta object literal for the '{@link FederationOffice.users.impl.TestbedDesignerImpl <em>Testbed Designer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.TestbedDesignerImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getTestbedDesigner()
		 * @generated
		 */
		EClass TESTBED_DESIGNER = eINSTANCE.getTestbedDesigner();

		/**
		 * The meta object literal for the '<em><b>Designs Virtual Testbeds</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TESTBED_DESIGNER__DESIGNS_VIRTUAL_TESTBEDS = eINSTANCE.getTestbedDesigner_DesignsVirtualTestbeds();

		/**
		 * The meta object literal for the '{@link FederationOffice.users.impl.AdminImpl <em>Admin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.AdminImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getAdmin()
		 * @generated
		 */
		EClass ADMIN = eINSTANCE.getAdmin();

		/**
		 * The meta object literal for the '{@link FederationOffice.users.impl.OfficeCustomerImpl <em>Office Customer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.OfficeCustomerImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getOfficeCustomer()
		 * @generated
		 */
		EClass OFFICE_CUSTOMER = eINSTANCE.getOfficeCustomer();

		/**
		 * The meta object literal for the '<em><b>Utilizes Virtual Testbed</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE_CUSTOMER__UTILIZES_VIRTUAL_TESTBED = eINSTANCE.getOfficeCustomer_UtilizesVirtualTestbed();

		/**
		 * The meta object literal for the '<em><b>Has Testbed Designer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE_CUSTOMER__HAS_TESTBED_DESIGNER = eINSTANCE.getOfficeCustomer_HasTestbedDesigner();

		/**
		 * The meta object literal for the '{@link FederationOffice.users.impl.OfficeUserImpl <em>Office User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.OfficeUserImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getOfficeUser()
		 * @generated
		 */
		EClass OFFICE_USER = eINSTANCE.getOfficeUser();

		/**
		 * The meta object literal for the '<em><b>Has Account</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE_USER__HAS_ACCOUNT = eINSTANCE.getOfficeUser_HasAccount();

		/**
		 * The meta object literal for the '<em><b>Organization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFICE_USER__ORGANIZATION = eINSTANCE.getOfficeUser_Organization();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFICE_USER__ADDRESS = eINSTANCE.getOfficeUser_Address();

		/**
		 * The meta object literal for the '<em><b>Telephone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFICE_USER__TELEPHONE = eINSTANCE.getOfficeUser_Telephone();

		/**
		 * The meta object literal for the '{@link FederationOffice.users.impl.OfficePersonelImpl <em>Office Personel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.users.impl.OfficePersonelImpl
		 * @see FederationOffice.users.impl.UsersPackageImpl#getOfficePersonel()
		 * @generated
		 */
		EClass OFFICE_PERSONEL = eINSTANCE.getOfficePersonel();

	}

} //UsersPackage
