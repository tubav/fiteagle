/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.resources.ResourceCategory#getResourcelist <em>Resourcelist</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.resources.ResourcesPackage#getResourceCategory()
 * @model
 * @generated
 */
public interface ResourceCategory extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Resourcelist</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.resources.Resource}.
	 * It is bidirectional and its opposite is '{@link FederationOffice.resources.Resource#getContributesToCategories <em>Contributes To Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourcelist</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourcelist</em>' reference list.
	 * @see FederationOffice.resources.ResourcesPackage#getResourceCategory_Resourcelist()
	 * @see FederationOffice.resources.Resource#getContributesToCategories
	 * @model opposite="contributesToCategories"
	 * @generated
	 */
	EList<Resource> getResourcelist();

} // ResourceCategory
