/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite;

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
 * @see FederationOffice.providersite.ProvidersiteFactory
 * @model kind="package"
 * @generated
 */
public interface ProvidersitePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "providersite";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/providersite";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.providersite";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProvidersitePackage eINSTANCE = FederationOffice.providersite.impl.ProvidersitePackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.providersite.impl.PTMImpl <em>PTM</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.providersite.impl.PTMImpl
	 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getPTM()
	 * @generated
	 */
	int PTM = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>IP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM__IP = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Belongs To Site</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM__BELONGS_TO_SITE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>PTM</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTM_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.providersite.impl.IGWImpl <em>IGW</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.providersite.impl.IGWImpl
	 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getIGW()
	 * @generated
	 */
	int IGW = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Belongs To Site</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW__BELONGS_TO_SITE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>IP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW__IP = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IGW</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGW_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link FederationOffice.providersite.impl.SiteImpl <em>Site</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.providersite.impl.SiteImpl
	 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getSite()
	 * @generated
	 */
	int SITE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Ptm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__PTM = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Igwlist</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__IGWLIST = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Located At</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__LOCATED_AT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Offered Resources List</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__OFFERED_RESOURCES_LIST = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Belongs To Provider</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__BELONGS_TO_PROVIDER = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Site</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link FederationOffice.providersite.impl.SiteLocationImpl <em>Site Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.providersite.impl.SiteLocationImpl
	 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getSiteLocation()
	 * @generated
	 */
	int SITE_LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION__ADDRESS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Geocoords</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION__GEOCOORDS = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Site Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_LOCATION_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link FederationOffice.providersite.PTM <em>PTM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PTM</em>'.
	 * @see FederationOffice.providersite.PTM
	 * @generated
	 */
	EClass getPTM();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.providersite.PTM#getIP <em>IP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IP</em>'.
	 * @see FederationOffice.providersite.PTM#getIP()
	 * @see #getPTM()
	 * @generated
	 */
	EAttribute getPTM_IP();

	/**
	 * Returns the meta object for the container reference '{@link FederationOffice.providersite.PTM#getBelongsToSite <em>Belongs To Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Belongs To Site</em>'.
	 * @see FederationOffice.providersite.PTM#getBelongsToSite()
	 * @see #getPTM()
	 * @generated
	 */
	EReference getPTM_BelongsToSite();

	/**
	 * Returns the meta object for class '{@link FederationOffice.providersite.IGW <em>IGW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IGW</em>'.
	 * @see FederationOffice.providersite.IGW
	 * @generated
	 */
	EClass getIGW();

	/**
	 * Returns the meta object for the container reference '{@link FederationOffice.providersite.IGW#getBelongsToSite <em>Belongs To Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Belongs To Site</em>'.
	 * @see FederationOffice.providersite.IGW#getBelongsToSite()
	 * @see #getIGW()
	 * @generated
	 */
	EReference getIGW_BelongsToSite();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.providersite.IGW#getIP <em>IP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IP</em>'.
	 * @see FederationOffice.providersite.IGW#getIP()
	 * @see #getIGW()
	 * @generated
	 */
	EAttribute getIGW_IP();

	/**
	 * Returns the meta object for class '{@link FederationOffice.providersite.Site <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site</em>'.
	 * @see FederationOffice.providersite.Site
	 * @generated
	 */
	EClass getSite();

	/**
	 * Returns the meta object for the containment reference '{@link FederationOffice.providersite.Site#getPtm <em>Ptm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ptm</em>'.
	 * @see FederationOffice.providersite.Site#getPtm()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_Ptm();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.providersite.Site#getIgwlist <em>Igwlist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Igwlist</em>'.
	 * @see FederationOffice.providersite.Site#getIgwlist()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_Igwlist();

	/**
	 * Returns the meta object for the containment reference '{@link FederationOffice.providersite.Site#getLocatedAt <em>Located At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Located At</em>'.
	 * @see FederationOffice.providersite.Site#getLocatedAt()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_LocatedAt();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.providersite.Site#getOfferedResourcesList <em>Offered Resources List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Offered Resources List</em>'.
	 * @see FederationOffice.providersite.Site#getOfferedResourcesList()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_OfferedResourcesList();

	/**
	 * Returns the meta object for the container reference '{@link FederationOffice.providersite.Site#getBelongsToProvider <em>Belongs To Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Belongs To Provider</em>'.
	 * @see FederationOffice.providersite.Site#getBelongsToProvider()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_BelongsToProvider();

	/**
	 * Returns the meta object for class '{@link FederationOffice.providersite.SiteLocation <em>Site Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site Location</em>'.
	 * @see FederationOffice.providersite.SiteLocation
	 * @generated
	 */
	EClass getSiteLocation();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.providersite.SiteLocation#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see FederationOffice.providersite.SiteLocation#getAddress()
	 * @see #getSiteLocation()
	 * @generated
	 */
	EAttribute getSiteLocation_Address();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.providersite.SiteLocation#getGeocoords <em>Geocoords</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Geocoords</em>'.
	 * @see FederationOffice.providersite.SiteLocation#getGeocoords()
	 * @see #getSiteLocation()
	 * @generated
	 */
	EAttribute getSiteLocation_Geocoords();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProvidersiteFactory getProvidersiteFactory();

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
		 * The meta object literal for the '{@link FederationOffice.providersite.impl.PTMImpl <em>PTM</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.providersite.impl.PTMImpl
		 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getPTM()
		 * @generated
		 */
		EClass PTM = eINSTANCE.getPTM();

		/**
		 * The meta object literal for the '<em><b>IP</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PTM__IP = eINSTANCE.getPTM_IP();

		/**
		 * The meta object literal for the '<em><b>Belongs To Site</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PTM__BELONGS_TO_SITE = eINSTANCE.getPTM_BelongsToSite();

		/**
		 * The meta object literal for the '{@link FederationOffice.providersite.impl.IGWImpl <em>IGW</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.providersite.impl.IGWImpl
		 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getIGW()
		 * @generated
		 */
		EClass IGW = eINSTANCE.getIGW();

		/**
		 * The meta object literal for the '<em><b>Belongs To Site</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IGW__BELONGS_TO_SITE = eINSTANCE.getIGW_BelongsToSite();

		/**
		 * The meta object literal for the '<em><b>IP</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IGW__IP = eINSTANCE.getIGW_IP();

		/**
		 * The meta object literal for the '{@link FederationOffice.providersite.impl.SiteImpl <em>Site</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.providersite.impl.SiteImpl
		 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getSite()
		 * @generated
		 */
		EClass SITE = eINSTANCE.getSite();

		/**
		 * The meta object literal for the '<em><b>Ptm</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__PTM = eINSTANCE.getSite_Ptm();

		/**
		 * The meta object literal for the '<em><b>Igwlist</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__IGWLIST = eINSTANCE.getSite_Igwlist();

		/**
		 * The meta object literal for the '<em><b>Located At</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__LOCATED_AT = eINSTANCE.getSite_LocatedAt();

		/**
		 * The meta object literal for the '<em><b>Offered Resources List</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__OFFERED_RESOURCES_LIST = eINSTANCE.getSite_OfferedResourcesList();

		/**
		 * The meta object literal for the '<em><b>Belongs To Provider</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__BELONGS_TO_PROVIDER = eINSTANCE.getSite_BelongsToProvider();

		/**
		 * The meta object literal for the '{@link FederationOffice.providersite.impl.SiteLocationImpl <em>Site Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.providersite.impl.SiteLocationImpl
		 * @see FederationOffice.providersite.impl.ProvidersitePackageImpl#getSiteLocation()
		 * @generated
		 */
		EClass SITE_LOCATION = eINSTANCE.getSiteLocation();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_LOCATION__ADDRESS = eINSTANCE.getSiteLocation_Address();

		/**
		 * The meta object literal for the '<em><b>Geocoords</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITE_LOCATION__GEOCOORDS = eINSTANCE.getSiteLocation_Geocoords();

	}

} //ProvidersitePackage
