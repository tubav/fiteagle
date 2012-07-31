/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see FederationOffice.uiObjects.UiObjectsFactory
 * @model kind="package"
 * @generated
 */
public interface UiObjectsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "uiObjects";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/uiObjects";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.uiObjects";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UiObjectsPackage eINSTANCE = FederationOffice.uiObjects.impl.UiObjectsPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl <em>ui Taxonomy Tree Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl
	 * @see FederationOffice.uiObjects.impl.UiObjectsPackageImpl#getui_TaxonomyTreeItem()
	 * @generated
	 */
	int UI_TAXONOMY_TREE_ITEM = 0;

	/**
	 * The feature id for the '<em><b>Parent Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_TAXONOMY_TREE_ITEM__PARENT_NODE = 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_TAXONOMY_TREE_ITEM__CHILD_NODES = 2;

	/**
	 * The feature id for the '<em><b>Office Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT = 3;

	/**
	 * The number of structural features of the '<em>ui Taxonomy Tree Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_TAXONOMY_TREE_ITEM_FEATURE_COUNT = 4;


	/**
	 * The meta object id for the '{@link FederationOffice.uiObjects.impl.OfficeManagerImpl <em>Office Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.uiObjects.impl.OfficeManagerImpl
	 * @see FederationOffice.uiObjects.impl.UiObjectsPackageImpl#getOfficeManager()
	 * @generated
	 */
	int OFFICE_MANAGER = 1;

	/**
	 * The feature id for the '<em><b>Offices Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_MANAGER__OFFICES_REF = 0;

	/**
	 * The number of structural features of the '<em>Office Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_MANAGER_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem <em>ui Taxonomy Tree Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ui Taxonomy Tree Item</em>'.
	 * @see FederationOffice.uiObjects.ui_TaxonomyTreeItem
	 * @generated
	 */
	EClass getui_TaxonomyTreeItem();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getParentNode <em>Parent Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Node</em>'.
	 * @see FederationOffice.uiObjects.ui_TaxonomyTreeItem#getParentNode()
	 * @see #getui_TaxonomyTreeItem()
	 * @generated
	 */
	EReference getui_TaxonomyTreeItem_ParentNode();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see FederationOffice.uiObjects.ui_TaxonomyTreeItem#getDisplayName()
	 * @see #getui_TaxonomyTreeItem()
	 * @generated
	 */
	EAttribute getui_TaxonomyTreeItem_DisplayName();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getChildNodes <em>Child Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Nodes</em>'.
	 * @see FederationOffice.uiObjects.ui_TaxonomyTreeItem#getChildNodes()
	 * @see #getui_TaxonomyTreeItem()
	 * @generated
	 */
	EReference getui_TaxonomyTreeItem_ChildNodes();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getOfficeElement <em>Office Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Office Element</em>'.
	 * @see FederationOffice.uiObjects.ui_TaxonomyTreeItem#getOfficeElement()
	 * @see #getui_TaxonomyTreeItem()
	 * @generated
	 */
	EReference getui_TaxonomyTreeItem_OfficeElement();

	/**
	 * Returns the meta object for class '{@link FederationOffice.uiObjects.OfficeManager <em>Office Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Office Manager</em>'.
	 * @see FederationOffice.uiObjects.OfficeManager
	 * @generated
	 */
	EClass getOfficeManager();

	/**
	 * Returns the meta object for the reference list '{@link FederationOffice.uiObjects.OfficeManager#getOfficesRef <em>Offices Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Offices Ref</em>'.
	 * @see FederationOffice.uiObjects.OfficeManager#getOfficesRef()
	 * @see #getOfficeManager()
	 * @generated
	 */
	EReference getOfficeManager_OfficesRef();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UiObjectsFactory getUiObjectsFactory();

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
		 * The meta object literal for the '{@link FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl <em>ui Taxonomy Tree Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl
		 * @see FederationOffice.uiObjects.impl.UiObjectsPackageImpl#getui_TaxonomyTreeItem()
		 * @generated
		 */
		EClass UI_TAXONOMY_TREE_ITEM = eINSTANCE.getui_TaxonomyTreeItem();

		/**
		 * The meta object literal for the '<em><b>Parent Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UI_TAXONOMY_TREE_ITEM__PARENT_NODE = eINSTANCE.getui_TaxonomyTreeItem_ParentNode();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME = eINSTANCE.getui_TaxonomyTreeItem_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Child Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UI_TAXONOMY_TREE_ITEM__CHILD_NODES = eINSTANCE.getui_TaxonomyTreeItem_ChildNodes();

		/**
		 * The meta object literal for the '<em><b>Office Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT = eINSTANCE.getui_TaxonomyTreeItem_OfficeElement();

		/**
		 * The meta object literal for the '{@link FederationOffice.uiObjects.impl.OfficeManagerImpl <em>Office Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.uiObjects.impl.OfficeManagerImpl
		 * @see FederationOffice.uiObjects.impl.UiObjectsPackageImpl#getOfficeManager()
		 * @generated
		 */
		EClass OFFICE_MANAGER = eINSTANCE.getOfficeManager();

		/**
		 * The meta object literal for the '<em><b>Offices Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE_MANAGER__OFFICES_REF = eINSTANCE.getOfficeManager_OfficesRef();

	}

} //UiObjectsPackage
