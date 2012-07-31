/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.Office;
import FederationOffice.uiObjects.OfficeManager;
import FederationOffice.uiObjects.UiObjectsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Office Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.uiObjects.impl.OfficeManagerImpl#getOfficesRef <em>Offices Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OfficeManagerImpl extends EObjectImpl implements OfficeManager {
	/**
	 * The cached value of the '{@link #getOfficesRef() <em>Offices Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfficesRef()
	 * @generated
	 * @ordered
	 */
	protected EList<Office> officesRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OfficeManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiObjectsPackage.Literals.OFFICE_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Office> getOfficesRef() {
		if (officesRef == null) {
			officesRef = new EObjectResolvingEList<Office>(Office.class, this, UiObjectsPackage.OFFICE_MANAGER__OFFICES_REF);
		}
		return officesRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UiObjectsPackage.OFFICE_MANAGER__OFFICES_REF:
				return getOfficesRef();
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
			case UiObjectsPackage.OFFICE_MANAGER__OFFICES_REF:
				getOfficesRef().clear();
				getOfficesRef().addAll((Collection<? extends Office>)newValue);
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
			case UiObjectsPackage.OFFICE_MANAGER__OFFICES_REF:
				getOfficesRef().clear();
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
			case UiObjectsPackage.OFFICE_MANAGER__OFFICES_REF:
				return officesRef != null && !officesRef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OfficeManagerImpl
