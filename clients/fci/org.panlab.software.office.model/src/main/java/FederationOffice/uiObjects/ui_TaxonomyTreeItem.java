/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ui Taxonomy Tree Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getParentNode <em>Parent Node</em>}</li>
 *   <li>{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getOfficeElement <em>Office Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.uiObjects.UiObjectsPackage#getui_TaxonomyTreeItem()
 * @model
 * @generated
 */
public interface ui_TaxonomyTreeItem extends EObject {
	/**
	 * Returns the value of the '<em><b>Parent Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Node</em>' reference.
	 * @see #setParentNode(ui_TaxonomyTreeItem)
	 * @see FederationOffice.uiObjects.UiObjectsPackage#getui_TaxonomyTreeItem_ParentNode()
	 * @model
	 * @generated
	 */
	ui_TaxonomyTreeItem getParentNode();

	/**
	 * Sets the value of the '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getParentNode <em>Parent Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Node</em>' reference.
	 * @see #getParentNode()
	 * @generated
	 */
	void setParentNode(ui_TaxonomyTreeItem value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see FederationOffice.uiObjects.UiObjectsPackage#getui_TaxonomyTreeItem_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Child Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.uiObjects.ui_TaxonomyTreeItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Nodes</em>' containment reference list.
	 * @see FederationOffice.uiObjects.UiObjectsPackage#getui_TaxonomyTreeItem_ChildNodes()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ui_TaxonomyTreeItem> getChildNodes();

	/**
	 * Returns the value of the '<em><b>Office Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Office Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Office Element</em>' reference.
	 * @see #setOfficeElement(NamedElement)
	 * @see FederationOffice.uiObjects.UiObjectsPackage#getui_TaxonomyTreeItem_OfficeElement()
	 * @model
	 * @generated
	 */
	NamedElement getOfficeElement();

	/**
	 * Sets the value of the '{@link FederationOffice.uiObjects.ui_TaxonomyTreeItem#getOfficeElement <em>Office Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Office Element</em>' reference.
	 * @see #getOfficeElement()
	 * @generated
	 */
	void setOfficeElement(NamedElement value);

} // ui_TaxonomyTreeItem
