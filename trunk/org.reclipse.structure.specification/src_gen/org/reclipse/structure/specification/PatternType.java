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
 * A representation of the literals of the enumeration '<em><b>Pattern Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.specification.SpecificationPackage#getPatternType()
 * @model
 * @generated
 */
public enum PatternType implements Enumerator
{
   /**
    * The '<em><b>DESIGN PATTERN</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #DESIGN_PATTERN_VALUE
    * @generated
    * @ordered
    */
   DESIGN_PATTERN(0, "DESIGN_PATTERN", "DESIGN_PATTERN"),

   /**
    * The '<em><b>ANTI PATTERN</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ANTI_PATTERN_VALUE
    * @generated
    * @ordered
    */
   ANTI_PATTERN(1, "ANTI_PATTERN", "ANTI_PATTERN");

   /**
    * The '<em><b>DESIGN PATTERN</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>DESIGN PATTERN</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #DESIGN_PATTERN
    * @model
    * @generated
    * @ordered
    */
   public static final int DESIGN_PATTERN_VALUE = 0;

   /**
    * The '<em><b>ANTI PATTERN</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>ANTI PATTERN</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ANTI_PATTERN
    * @model
    * @generated
    * @ordered
    */
   public static final int ANTI_PATTERN_VALUE = 1;

   /**
    * An array of all the '<em><b>Pattern Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final PatternType[] VALUES_ARRAY =
      new PatternType[]
      {
         DESIGN_PATTERN,
         ANTI_PATTERN,
      };

   /**
    * A public read-only list of all the '<em><b>Pattern Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<PatternType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Pattern Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static PatternType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         PatternType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Pattern Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static PatternType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         PatternType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Pattern Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static PatternType get(int value)
   {
      switch (value)
      {
         case DESIGN_PATTERN_VALUE: return DESIGN_PATTERN;
         case ANTI_PATTERN_VALUE: return ANTI_PATTERN;
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
   private PatternType(int value, String name, String literal)
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
   
} //PatternType
