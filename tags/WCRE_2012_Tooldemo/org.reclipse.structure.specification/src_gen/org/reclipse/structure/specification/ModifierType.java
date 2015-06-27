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
 * A representation of the literals of the enumeration '<em><b>Modifier Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.specification.SpecificationPackage#getModifierType()
 * @model
 * @generated
 */
public enum ModifierType implements Enumerator
{
   /**
    * The '<em><b>NONE</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #NONE_VALUE
    * @generated
    * @ordered
    */
   NONE(0, "NONE", "NONE"),

   /**
    * The '<em><b>ADDITIONAL</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ADDITIONAL_VALUE
    * @generated
    * @ordered
    */
   ADDITIONAL(1, "ADDITIONAL", "ADDITIONAL"),

   /**
    * The '<em><b>NEGATIVE</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #NEGATIVE_VALUE
    * @generated
    * @ordered
    */
   NEGATIVE(2, "NEGATIVE", "NEGATIVE"),

   /**
    * The '<em><b>SET</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SET_VALUE
    * @generated
    * @ordered
    */
   SET(3, "SET", "SET");

   /**
    * The '<em><b>NONE</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #NONE
    * @model
    * @generated
    * @ordered
    */
   public static final int NONE_VALUE = 0;

   /**
    * The '<em><b>ADDITIONAL</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>ADDITIONAL</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ADDITIONAL
    * @model
    * @generated
    * @ordered
    */
   public static final int ADDITIONAL_VALUE = 1;

   /**
    * The '<em><b>NEGATIVE</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>NEGATIVE</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #NEGATIVE
    * @model
    * @generated
    * @ordered
    */
   public static final int NEGATIVE_VALUE = 2;

   /**
    * The '<em><b>SET</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>SET</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SET
    * @model
    * @generated
    * @ordered
    */
   public static final int SET_VALUE = 3;

   /**
    * An array of all the '<em><b>Modifier Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final ModifierType[] VALUES_ARRAY =
      new ModifierType[]
      {
         NONE,
         ADDITIONAL,
         NEGATIVE,
         SET,
      };

   /**
    * A public read-only list of all the '<em><b>Modifier Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<ModifierType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Modifier Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ModifierType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         ModifierType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Modifier Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ModifierType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         ModifierType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Modifier Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ModifierType get(int value)
   {
      switch (value)
      {
         case NONE_VALUE: return NONE;
         case ADDITIONAL_VALUE: return ADDITIONAL;
         case NEGATIVE_VALUE: return NEGATIVE;
         case SET_VALUE: return SET;
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
   private ModifierType(int value, String name, String literal)
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
   
} //ModifierType
