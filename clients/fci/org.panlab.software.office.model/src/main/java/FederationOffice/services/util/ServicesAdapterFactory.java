/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import FederationOffice.NamedElement;
import FederationOffice.services.*;
import FederationOffice.services.AbstractSetting;
import FederationOffice.services.OfferedService;
import FederationOffice.services.Service;
import FederationOffice.services.ServiceComposition;
import FederationOffice.services.ServiceSetting;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.SettingConstraint;
import FederationOffice.services.SettingType;
import FederationOffice.services.Taxonomy;
import FederationOffice.services.tideTypeEnum;
import FederationOffice.services.tideTypeEnumItem;
import FederationOffice.services.tideTypeList;
import FederationOffice.services.tideTypeString;
import FederationOffice.services.tideTypeTideElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.services.ServicesPackage
 * @generated
 */
public class ServicesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ServicesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ServicesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServicesSwitch<Adapter> modelSwitch =
		new ServicesSwitch<Adapter>() {
			@Override
			public Adapter caseService(Service object) {
				return createServiceAdapter();
			}
			@Override
			public Adapter caseServiceSetting(ServiceSetting object) {
				return createServiceSettingAdapter();
			}
			@Override
			public Adapter caseOfferedService(OfferedService object) {
				return createOfferedServiceAdapter();
			}
			@Override
			public Adapter caseServiceComposition(ServiceComposition object) {
				return createServiceCompositionAdapter();
			}
			@Override
			public Adapter caseSettingType(SettingType object) {
				return createSettingTypeAdapter();
			}
			@Override
			public Adapter casetideTypeString(tideTypeString object) {
				return createtideTypeStringAdapter();
			}
			@Override
			public Adapter casetideTypeEnum(tideTypeEnum object) {
				return createtideTypeEnumAdapter();
			}
			@Override
			public Adapter casetideTypeList(tideTypeList object) {
				return createtideTypeListAdapter();
			}
			@Override
			public Adapter casetideTypeEnumItem(tideTypeEnumItem object) {
				return createtideTypeEnumItemAdapter();
			}
			@Override
			public Adapter casetideTypeTideElement(tideTypeTideElement object) {
				return createtideTypeTideElementAdapter();
			}
			@Override
			public Adapter caseTaxonomy(Taxonomy object) {
				return createTaxonomyAdapter();
			}
			@Override
			public Adapter caseAbstractSetting(AbstractSetting object) {
				return createAbstractSettingAdapter();
			}
			@Override
			public Adapter caseSettingConstraint(SettingConstraint object) {
				return createSettingConstraintAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.Service
	 * @generated
	 */
	public Adapter createServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.ServiceSetting <em>Service Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.ServiceSetting
	 * @generated
	 */
	public Adapter createServiceSettingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.OfferedService <em>Offered Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.OfferedService
	 * @generated
	 */
	public Adapter createOfferedServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.ServiceComposition <em>Service Composition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.ServiceComposition
	 * @generated
	 */
	public Adapter createServiceCompositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.SettingType <em>Setting Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.SettingType
	 * @generated
	 */
	public Adapter createSettingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.tideTypeString <em>tide Type String</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.tideTypeString
	 * @generated
	 */
	public Adapter createtideTypeStringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.tideTypeEnum <em>tide Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.tideTypeEnum
	 * @generated
	 */
	public Adapter createtideTypeEnumAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.tideTypeList <em>tide Type List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.tideTypeList
	 * @generated
	 */
	public Adapter createtideTypeListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.tideTypeEnumItem <em>tide Type Enum Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.tideTypeEnumItem
	 * @generated
	 */
	public Adapter createtideTypeEnumItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.tideTypeTideElement <em>tide Type Tide Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.tideTypeTideElement
	 * @generated
	 */
	public Adapter createtideTypeTideElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.Taxonomy <em>Taxonomy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.Taxonomy
	 * @generated
	 */
	public Adapter createTaxonomyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.AbstractSetting <em>Abstract Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.AbstractSetting
	 * @generated
	 */
	public Adapter createAbstractSettingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.services.SettingConstraint <em>Setting Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.services.SettingConstraint
	 * @generated
	 */
	public Adapter createSettingConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ServicesAdapterFactory
