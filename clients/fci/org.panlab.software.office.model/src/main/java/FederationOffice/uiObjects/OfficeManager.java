/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import FederationOffice.Office;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Office Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.uiObjects.OfficeManager#getOfficesRef <em>Offices Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.uiObjects.UiObjectsPackage#getOfficeManager()
 * @model
 * @generated
 */
public interface OfficeManager extends EObject {
	/**
	 * Returns the value of the '<em><b>Offices Ref</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.Office}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offices Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offices Ref</em>' reference list.
	 * @see FederationOffice.uiObjects.UiObjectsPackage#getOfficeManager_OfficesRef()
	 * @model
	 * @generated
	 */
	EList<Office> getOfficesRef();

} // OfficeManager
