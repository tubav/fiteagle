/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.services.ServicesPackage
 * @generated
 */
public interface ServicesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ServicesFactory eINSTANCE = FederationOffice.services.impl.ServicesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service</em>'.
	 * @generated
	 */
	Service createService();

	/**
	 * Returns a new object of class '<em>Service Setting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Setting</em>'.
	 * @generated
	 */
	ServiceSetting createServiceSetting();

	/**
	 * Returns a new object of class '<em>Offered Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Offered Service</em>'.
	 * @generated
	 */
	OfferedService createOfferedService();

	/**
	 * Returns a new object of class '<em>Service Composition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Composition</em>'.
	 * @generated
	 */
	ServiceComposition createServiceComposition();

	/**
	 * Returns a new object of class '<em>tide Type String</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>tide Type String</em>'.
	 * @generated
	 */
	tideTypeString createtideTypeString();

	/**
	 * Returns a new object of class '<em>tide Type Enum</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>tide Type Enum</em>'.
	 * @generated
	 */
	tideTypeEnum createtideTypeEnum();

	/**
	 * Returns a new object of class '<em>tide Type List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>tide Type List</em>'.
	 * @generated
	 */
	tideTypeList createtideTypeList();

	/**
	 * Returns a new object of class '<em>tide Type Enum Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>tide Type Enum Item</em>'.
	 * @generated
	 */
	tideTypeEnumItem createtideTypeEnumItem();

	/**
	 * Returns a new object of class '<em>tide Type Tide Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>tide Type Tide Element</em>'.
	 * @generated
	 */
	tideTypeTideElement createtideTypeTideElement();

	/**
	 * Returns a new object of class '<em>Taxonomy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Taxonomy</em>'.
	 * @generated
	 */
	Taxonomy createTaxonomy();

	/**
	 * Returns a new object of class '<em>Abstract Setting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Setting</em>'.
	 * @generated
	 */
	AbstractSetting createAbstractSetting();

	/**
	 * Returns a new object of class '<em>Setting Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Setting Constraint</em>'.
	 * @generated
	 */
	SettingConstraint createSettingConstraint();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ServicesPackage getServicesPackage();

} //ServicesFactory
