/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>VT Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see FederationOffice.FederationOfficePackage#getVTStatus()
 * @model
 * @generated
 */
public enum VTStatus implements Enumerator {
	/**
	 * The '<em><b>NEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEW_VALUE
	 * @generated
	 * @ordered
	 */
	NEW(0, "NEW", "NEW"), /**
	 * The '<em><b>REQUESTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUESTED_VALUE
	 * @generated
	 * @ordered
	 */
	REQUESTED(1, "REQUESTED", "REQUESTED"), /**
	 * The '<em><b>SCHEDULED PROVISIONING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCHEDULED_PROVISIONING_VALUE
	 * @generated
	 * @ordered
	 */
	SCHEDULED_PROVISIONING(2, "SCHEDULED_PROVISIONING", "SCHEDULED_PROVISIONING"), /**
	 * The '<em><b>MODIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MODIFIED_VALUE
	 * @generated
	 * @ordered
	 */
	MODIFIED(3, "MODIFIED", "MODIFIED"), /**
	 * The '<em><b>REJECTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REJECTED_VALUE
	 * @generated
	 * @ordered
	 */
	REJECTED(4, "REJECTED", "REJECTED"), /**
	 * The '<em><b>RUNNING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNING_VALUE
	 * @generated
	 * @ordered
	 */
	RUNNING(5, "RUNNING", "RUNNING"), /**
	 * The '<em><b>PROVISIONING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROVISIONING_VALUE
	 * @generated
	 * @ordered
	 */
	PROVISIONING(6, "PROVISIONING", "PROVISIONING"), /**
	 * The '<em><b>SHUT DOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHUT_DOWN_VALUE
	 * @generated
	 * @ordered
	 */
	SHUT_DOWN(7, "SHUT_DOWN", "SHUT_DOWN"), /**
	 * The '<em><b>DELETED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETED_VALUE
	 * @generated
	 * @ordered
	 */
	DELETED(8, "DELETED", "DELETED"), /**
	 * The '<em><b>SHUTTING DOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHUTTING_DOWN_VALUE
	 * @generated
	 * @ordered
	 */
	SHUTTING_DOWN(9, "SHUTTING_DOWN", "SHUTTING_DOWN"), /**
	 * The '<em><b>SCHEDULED SHUT DOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCHEDULED_SHUT_DOWN_VALUE
	 * @generated
	 * @ordered
	 */
	SCHEDULED_SHUT_DOWN(10, "SCHEDULED_SHUT_DOWN", "SCHEDULED_SHUT_DOWN");

	/**
	 * The '<em><b>NEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NEW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NEW_VALUE = 0;

	/**
	 * The '<em><b>REQUESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUESTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUESTED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REQUESTED_VALUE = 1;

	/**
	 * The '<em><b>SCHEDULED PROVISIONING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SCHEDULED PROVISIONING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCHEDULED_PROVISIONING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SCHEDULED_PROVISIONING_VALUE = 2;

	/**
	 * The '<em><b>MODIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MODIFIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MODIFIED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MODIFIED_VALUE = 3;

	/**
	 * The '<em><b>REJECTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REJECTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REJECTED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REJECTED_VALUE = 4;

	/**
	 * The '<em><b>RUNNING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RUNNING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RUNNING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RUNNING_VALUE = 5;

	/**
	 * The '<em><b>PROVISIONING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROVISIONING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROVISIONING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROVISIONING_VALUE = 6;

	/**
	 * The '<em><b>SHUT DOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SHUT DOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHUT_DOWN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHUT_DOWN_VALUE = 7;

	/**
	 * The '<em><b>DELETED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELETED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DELETED_VALUE = 8;

	/**
	 * The '<em><b>SHUTTING DOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SHUTTING DOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHUTTING_DOWN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHUTTING_DOWN_VALUE = 9;

	/**
	 * The '<em><b>SCHEDULED SHUT DOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SCHEDULED SHUT DOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCHEDULED_SHUT_DOWN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SCHEDULED_SHUT_DOWN_VALUE = 10;

	/**
	 * An array of all the '<em><b>VT Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final VTStatus[] VALUES_ARRAY =
		new VTStatus[] {
			NEW,
			REQUESTED,
			SCHEDULED_PROVISIONING,
			MODIFIED,
			REJECTED,
			RUNNING,
			PROVISIONING,
			SHUT_DOWN,
			DELETED,
			SHUTTING_DOWN,
			SCHEDULED_SHUT_DOWN,
		};

	/**
	 * A public read-only list of all the '<em><b>VT Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<VTStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>VT Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VTStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VTStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>VT Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VTStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VTStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>VT Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VTStatus get(int value) {
		switch (value) {
			case NEW_VALUE: return NEW;
			case REQUESTED_VALUE: return REQUESTED;
			case SCHEDULED_PROVISIONING_VALUE: return SCHEDULED_PROVISIONING;
			case MODIFIED_VALUE: return MODIFIED;
			case REJECTED_VALUE: return REJECTED;
			case RUNNING_VALUE: return RUNNING;
			case PROVISIONING_VALUE: return PROVISIONING;
			case SHUT_DOWN_VALUE: return SHUT_DOWN;
			case DELETED_VALUE: return DELETED;
			case SHUTTING_DOWN_VALUE: return SHUTTING_DOWN;
			case SCHEDULED_SHUT_DOWN_VALUE: return SCHEDULED_SHUT_DOWN;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private VTStatus(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //VTStatus
