/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Operator Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.specification.SpecificationPackage#getOperatorType()
 * @model
 * @generated
 */
public enum OperatorType implements Enumerator
{
   /**
    * The '<em><b>LESS</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #LESS_VALUE
    * @generated
    * @ordered
    */
   LESS(0, "LESS", "LESS"),

   /**
    * The '<em><b>LESS OR EQUAL</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #LESS_OR_EQUAL_VALUE
    * @generated
    * @ordered
    */
   LESS_OR_EQUAL(1, "LESS_OR_EQUAL", "LESS_OR_EQUAL"),

   /**
    * The '<em><b>GREATER</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #GREATER_VALUE
    * @generated
    * @ordered
    */
   GREATER(2, "GREATER", "GREATER"),

   /**
    * The '<em><b>GREATER OR EQUAL</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #GREATER_OR_EQUAL_VALUE
    * @generated
    * @ordered
    */
   GREATER_OR_EQUAL(3, "GREATER_OR_EQUAL", "GREATER_OR_EQUAL"),

   /**
    * The '<em><b>EQUAL</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #EQUAL_VALUE
    * @generated
    * @ordered
    */
   EQUAL(4, "EQUAL", "EQUAL"),

   /**
    * The '<em><b>REGULAR EXPRESSION</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #REGULAR_EXPRESSION_VALUE
    * @generated
    * @ordered
    */
   REGULAR_EXPRESSION(5, "REGULAR_EXPRESSION", "REGULAR_EXPRESSION"),

   /**
    * The '<em><b>UNEQUAL</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #UNEQUAL_VALUE
    * @generated
    * @ordered
    */
   UNEQUAL(6, "UNEQUAL", "UNEQUAL");

   /**
    * The '<em><b>LESS</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>LESS</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #LESS
    * @model
    * @generated
    * @ordered
    */
   public static final int LESS_VALUE = 0;

   /**
    * The '<em><b>LESS OR EQUAL</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>LESS OR EQUAL</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #LESS_OR_EQUAL
    * @model
    * @generated
    * @ordered
    */
   public static final int LESS_OR_EQUAL_VALUE = 1;

   /**
    * The '<em><b>GREATER</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>GREATER</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #GREATER
    * @model
    * @generated
    * @ordered
    */
   public static final int GREATER_VALUE = 2;

   /**
    * The '<em><b>GREATER OR EQUAL</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>GREATER OR EQUAL</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #GREATER_OR_EQUAL
    * @model
    * @generated
    * @ordered
    */
   public static final int GREATER_OR_EQUAL_VALUE = 3;

   /**
    * The '<em><b>EQUAL</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>EQUAL</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #EQUAL
    * @model
    * @generated
    * @ordered
    */
   public static final int EQUAL_VALUE = 4;

   /**
    * The '<em><b>REGULAR EXPRESSION</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>REGULAR EXPRESSION</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #REGULAR_EXPRESSION
    * @model
    * @generated
    * @ordered
    */
   public static final int REGULAR_EXPRESSION_VALUE = 5;

   /**
    * The '<em><b>UNEQUAL</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>UNEQUAL</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #UNEQUAL
    * @model
    * @generated
    * @ordered
    */
   public static final int UNEQUAL_VALUE = 6;

   /**
    * An array of all the '<em><b>Operator Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final OperatorType[] VALUES_ARRAY =
      new OperatorType[]
      {
         LESS,
         LESS_OR_EQUAL,
         GREATER,
         GREATER_OR_EQUAL,
         EQUAL,
         REGULAR_EXPRESSION,
         UNEQUAL,
      };

   /**
    * A public read-only list of all the '<em><b>Operator Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<OperatorType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Operator Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static OperatorType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         OperatorType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Operator Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static OperatorType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         OperatorType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Operator Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static OperatorType get(int value)
   {
      switch (value)
      {
         case LESS_VALUE: return LESS;
         case LESS_OR_EQUAL_VALUE: return LESS_OR_EQUAL;
         case GREATER_VALUE: return GREATER;
         case GREATER_OR_EQUAL_VALUE: return GREATER_OR_EQUAL;
         case EQUAL_VALUE: return EQUAL;
         case REGULAR_EXPRESSION_VALUE: return REGULAR_EXPRESSION;
         case UNEQUAL_VALUE: return UNEQUAL;
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
   private OperatorType(int value, String name, String literal)
   {
      this.value = value;
      this.name = name;
      this.literal = literal;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getValue()
   {
     return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
     return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getLiteral()
   {
     return literal;
   }

   /**
    * Returns the literal value of the enumerator, which is its string representation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      return literal;
   }
   
} //OperatorType
