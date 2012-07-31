/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.providersite.ProvidersitePackage
 * @generated
 */
public interface ProvidersiteFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProvidersiteFactory eINSTANCE = FederationOffice.providersite.impl.ProvidersiteFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>PTM</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PTM</em>'.
	 * @generated
	 */
	PTM createPTM();

	/**
	 * Returns a new object of class '<em>IGW</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IGW</em>'.
	 * @generated
	 */
	IGW createIGW();

	/**
	 * Returns a new object of class '<em>Site</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Site</em>'.
	 * @generated
	 */
	Site createSite();

	/**
	 * Returns a new object of class '<em>Site Location</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Site Location</em>'.
	 * @generated
	 */
	SiteLocation createSiteLocation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ProvidersitePackage getProvidersitePackage();

} //ProvidersiteFactory
