/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects.impl;

import FederationOffice.uiObjects.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.uiObjects.OfficeManager;
import FederationOffice.uiObjects.UiObjectsFactory;
import FederationOffice.uiObjects.UiObjectsPackage;
import FederationOffice.uiObjects.ui_TaxonomyTreeItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UiObjectsFactoryImpl extends EFactoryImpl implements UiObjectsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UiObjectsFactory init() {
		try {
			UiObjectsFactory theUiObjectsFactory = (UiObjectsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/uiObjects"); 
			if (theUiObjectsFactory != null) {
				return theUiObjectsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UiObjectsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UiObjectsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UiObjectsPackage.UI_TAXONOMY_TREE_ITEM: return createui_TaxonomyTreeItem();
			case UiObjectsPackage.OFFICE_MANAGER: return createOfficeManager();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ui_TaxonomyTreeItem createui_TaxonomyTreeItem() {
		ui_TaxonomyTreeItemImpl ui_TaxonomyTreeItem = new ui_TaxonomyTreeItemImpl();
		return ui_TaxonomyTreeItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfficeManager createOfficeManager() {
		OfficeManagerImpl officeManager = new OfficeManagerImpl();
		return officeManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UiObjectsPackage getUiObjectsPackage() {
		return (UiObjectsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UiObjectsPackage getPackage() {
		return UiObjectsPackage.eINSTANCE;
	}

} //UiObjectsFactoryImpl
