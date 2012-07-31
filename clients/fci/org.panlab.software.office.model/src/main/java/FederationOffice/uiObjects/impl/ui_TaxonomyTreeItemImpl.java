/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.NamedElement;
import FederationOffice.uiObjects.UiObjectsPackage;
import FederationOffice.uiObjects.ui_TaxonomyTreeItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ui Taxonomy Tree Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl#getParentNode <em>Parent Node</em>}</li>
 *   <li>{@link FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link FederationOffice.uiObjects.impl.ui_TaxonomyTreeItemImpl#getOfficeElement <em>Office Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ui_TaxonomyTreeItemImpl extends EObjectImpl implements ui_TaxonomyTreeItem {
	/**
	 * The cached value of the '{@link #getParentNode() <em>Parent Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentNode()
	 * @generated
	 * @ordered
	 */
	protected ui_TaxonomyTreeItem parentNode;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChildNodes() <em>Child Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<ui_TaxonomyTreeItem> childNodes;

	/**
	 * The cached value of the '{@link #getOfficeElement() <em>Office Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfficeElement()
	 * @generated
	 * @ordered
	 */
	protected NamedElement officeElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ui_TaxonomyTreeItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiObjectsPackage.Literals.UI_TAXONOMY_TREE_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ui_TaxonomyTreeItem getParentNode() {
		if (parentNode != null && parentNode.eIsProxy()) {
			InternalEObject oldParentNode = (InternalEObject)parentNode;
			parentNode = (ui_TaxonomyTreeItem)eResolveProxy(oldParentNode);
			if (parentNode != oldParentNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__PARENT_NODE, oldParentNode, parentNode));
			}
		}
		return parentNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ui_TaxonomyTreeItem basicGetParentNode() {
		return parentNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentNode(ui_TaxonomyTreeItem newParentNode) {
		ui_TaxonomyTreeItem oldParentNode = parentNode;
		parentNode = newParentNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__PARENT_NODE, oldParentNode, parentNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME, oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ui_TaxonomyTreeItem> getChildNodes() {
		if (childNodes == null) {
			childNodes = new EObjectContainmentEList.Resolving<ui_TaxonomyTreeItem>(ui_TaxonomyTreeItem.class, this, UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__CHILD_NODES);
		}
		return childNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement getOfficeElement() {
		if (officeElement != null && officeElement.eIsProxy()) {
			InternalEObject oldOfficeElement = (InternalEObject)officeElement;
			officeElement = (NamedElement)eResolveProxy(oldOfficeElement);
			if (officeElement != oldOfficeElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT, oldOfficeElement, officeElement));
			}
		}
		return officeElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement basicGetOfficeElement() {
		return officeElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOfficeElement(NamedElement newOfficeElement) {
		NamedElement oldOfficeElement = officeElement;
		officeElement = newOfficeElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT, oldOfficeElement, officeElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__CHILD_NODES:
				return ((InternalEList<?>)getChildNodes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__PARENT_NODE:
				if (resolve) return getParentNode();
				return basicGetParentNode();
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME:
				return getDisplayName();
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__CHILD_NODES:
				return getChildNodes();
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT:
				if (resolve) return getOfficeElement();
				return basicGetOfficeElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__PARENT_NODE:
				setParentNode((ui_TaxonomyTreeItem)newValue);
				return;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__CHILD_NODES:
				getChildNodes().clear();
				getChildNodes().addAll((Collection<? extends ui_TaxonomyTreeItem>)newValue);
				return;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT:
				setOfficeElement((NamedElement)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__PARENT_NODE:
				setParentNode((ui_TaxonomyTreeItem)null);
				return;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__CHILD_NODES:
				getChildNodes().clear();
				return;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT:
				setOfficeElement((NamedElement)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__PARENT_NODE:
				return parentNode != null;
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__CHILD_NODES:
				return childNodes != null && !childNodes.isEmpty();
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT:
				return officeElement != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (DisplayName: ");
		result.append(displayName);
		result.append(')');
		return result.toString();
	}

} //ui_TaxonomyTreeItemImpl
