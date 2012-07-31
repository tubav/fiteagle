/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.federationscenarios.FederationscenariosPackage
 * @generated
 */
public interface FederationscenariosFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FederationscenariosFactory eINSTANCE = FederationOffice.federationscenarios.impl.FederationscenariosFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Requested Federation Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requested Federation Scenario</em>'.
	 * @generated
	 */
	RequestedFederationScenario createRequestedFederationScenario();

	/**
	 * Returns a new object of class '<em>Service Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Request</em>'.
	 * @generated
	 */
	ServiceRequest createServiceRequest();

	/**
	 * Returns a new object of class '<em>Resource Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Request</em>'.
	 * @generated
	 */
	ResourceRequest createResourceRequest();

	/**
	 * Returns a new object of class '<em>Setting Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Setting Instance</em>'.
	 * @generated
	 */
	SettingInstance createSettingInstance();

	/**
	 * Returns a new object of class '<em>Service Setting Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Setting Instance</em>'.
	 * @generated
	 */
	ServiceSettingInstance createServiceSettingInstance();

	/**
	 * Returns a new object of class '<em>Resource Setting Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Setting Instance</em>'.
	 * @generated
	 */
	ResourceSettingInstance createResourceSettingInstance();

	/**
	 * Returns a new object of class '<em>Credentials</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Credentials</em>'.
	 * @generated
	 */
	Credentials createCredentials();

	/**
	 * Returns a new object of class '<em>Services Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Services Request</em>'.
	 * @generated
	 */
	ServicesRequest createServicesRequest();

	/**
	 * Returns a new object of class '<em>Infrastructure Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Infrastructure Request</em>'.
	 * @generated
	 */
	InfrastructureRequest createInfrastructureRequest();

	/**
	 * Returns a new object of class '<em>Scheduled Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scheduled Plan</em>'.
	 * @generated
	 */
	ScheduledPlan createScheduledPlan();

	/**
	 * Returns a new object of class '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import</em>'.
	 * @generated
	 */
	Import createImport();

	/**
	 * Returns a new object of class '<em>Resource Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Group</em>'.
	 * @generated
	 */
	ResourceGroup createResourceGroup();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FederationscenariosPackage getFederationscenariosPackage();

} //FederationscenariosFactory
