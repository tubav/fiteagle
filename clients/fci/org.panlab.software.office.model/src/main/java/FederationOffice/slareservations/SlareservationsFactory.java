/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.slareservations;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.slareservations.SlareservationsPackage
 * @generated
 */
public interface SlareservationsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SlareservationsFactory eINSTANCE = FederationOffice.slareservations.impl.SlareservationsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>SLA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SLA</em>'.
	 * @generated
	 */
	SLA createSLA();

	/**
	 * Returns a new object of class '<em>Reserved Resource Contract</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reserved Resource Contract</em>'.
	 * @generated
	 */
	ReservedResourceContract createReservedResourceContract();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SlareservationsPackage getSlareservationsPackage();

} //SlareservationsFactory
