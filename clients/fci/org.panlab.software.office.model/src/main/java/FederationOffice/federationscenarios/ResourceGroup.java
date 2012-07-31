/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.ResourceGroup#getGroupedResources <em>Grouped Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getResourceGroup()
 * @model
 * @generated
 */
public interface ResourceGroup extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Grouped Resources</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.ResourceRequest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grouped Resources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grouped Resources</em>' reference list.
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getResourceGroup_GroupedResources()
	 * @model
	 * @generated
	 */
	EList<ResourceRequest> getGroupedResources();

} // ResourceGroup
