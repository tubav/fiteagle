/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Repatability Scheme</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see FederationOffice.availabilityContract.AvailabilityContractPackage#getRepatabilityScheme()
 * @model
 * @generated
 */
public enum RepatabilityScheme implements Enumerator {
	/**
	 * The '<em><b>DAILY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DAILY_VALUE
	 * @generated
	 * @ordered
	 */
	DAILY(0, "DAILY", "DAILY"),

	/**
	 * The '<em><b>WEEKLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEEKLY_VALUE
	 * @generated
	 * @ordered
	 */
	WEEKLY(1, "WEEKLY", "WEEKLY"),

	/**
	 * The '<em><b>MONTHLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTHLY_VALUE
	 * @generated
	 * @ordered
	 */
	MONTHLY(2, "MONTHLY", "MONTHLY"),

	/**
	 * The '<em><b>YEARLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YEARLY_VALUE
	 * @generated
	 * @ordered
	 */
	YEARLY(3, "YEARLY", "YEARLY");

	/**
	 * The '<em><b>DAILY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DAILY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DAILY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DAILY_VALUE = 0;

	/**
	 * The '<em><b>WEEKLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WEEKLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEEKLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WEEKLY_VALUE = 1;

	/**
	 * The '<em><b>MONTHLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MONTHLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONTHLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MONTHLY_VALUE = 2;

	/**
	 * The '<em><b>YEARLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YEARLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YEARLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int YEARLY_VALUE = 3;

	/**
	 * An array of all the '<em><b>Repatability Scheme</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RepatabilityScheme[] VALUES_ARRAY =
		new RepatabilityScheme[] {
			DAILY,
			WEEKLY,
			MONTHLY,
			YEARLY,
		};

	/**
	 * A public read-only list of all the '<em><b>Repatability Scheme</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RepatabilityScheme> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Repatability Scheme</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RepatabilityScheme get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RepatabilityScheme result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Repatability Scheme</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RepatabilityScheme getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RepatabilityScheme result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Repatability Scheme</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RepatabilityScheme get(int value) {
		switch (value) {
			case DAILY_VALUE: return DAILY;
			case WEEKLY_VALUE: return WEEKLY;
			case MONTHLY_VALUE: return MONTHLY;
			case YEARLY_VALUE: return YEARLY;
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
	private RepatabilityScheme(int value, String name, String literal) {
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
	
} //RepatabilityScheme
