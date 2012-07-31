/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.FederationOfficePackage
 * @generated
 */
public interface FederationOfficeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FederationOfficeFactory eINSTANCE = FederationOffice.impl.FederationOfficeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Office</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Office</em>'.
	 * @generated
	 */
	Office createOffice();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FederationOfficePackage getFederationOfficePackage();

} //FederationOfficeFactory
