/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.uiObjects.UiObjectsPackage
 * @generated
 */
public interface UiObjectsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UiObjectsFactory eINSTANCE = FederationOffice.uiObjects.impl.UiObjectsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>ui Taxonomy Tree Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ui Taxonomy Tree Item</em>'.
	 * @generated
	 */
	ui_TaxonomyTreeItem createui_TaxonomyTreeItem();

	/**
	 * Returns a new object of class '<em>Office Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Office Manager</em>'.
	 * @generated
	 */
	OfficeManager createOfficeManager();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UiObjectsPackage getUiObjectsPackage();

} //UiObjectsFactory
