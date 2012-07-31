/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite.impl;

import FederationOffice.providersite.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.providersite.IGW;
import FederationOffice.providersite.PTM;
import FederationOffice.providersite.ProvidersiteFactory;
import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.Site;
import FederationOffice.providersite.SiteLocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProvidersiteFactoryImpl extends EFactoryImpl implements ProvidersiteFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ProvidersiteFactory init() {
		try {
			ProvidersiteFactory theProvidersiteFactory = (ProvidersiteFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/providersite"); 
			if (theProvidersiteFactory != null) {
				return theProvidersiteFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ProvidersiteFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidersiteFactoryImpl() {
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
			case ProvidersitePackage.PTM: return createPTM();
			case ProvidersitePackage.IGW: return createIGW();
			case ProvidersitePackage.SITE: return createSite();
			case ProvidersitePackage.SITE_LOCATION: return createSiteLocation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTM createPTM() {
		PTMImpl ptm = new PTMImpl();
		return ptm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IGW createIGW() {
		IGWImpl igw = new IGWImpl();
		return igw;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site createSite() {
		SiteImpl site = new SiteImpl();
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiteLocation createSiteLocation() {
		SiteLocationImpl siteLocation = new SiteLocationImpl();
		return siteLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidersitePackage getProvidersitePackage() {
		return (ProvidersitePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ProvidersitePackage getPackage() {
		return ProvidersitePackage.eINSTANCE;
	}

} //ProvidersiteFactoryImpl
