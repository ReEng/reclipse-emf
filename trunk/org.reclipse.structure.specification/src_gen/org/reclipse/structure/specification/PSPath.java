/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSPath#getTabooClasses <em>Taboo Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSPath()
 * @model
 * @generated
 */
public interface PSPath extends PSConnection
{
   /**
    * Returns the value of the '<em><b>Taboo Classes</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Taboo Classes</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Taboo Classes</em>' reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPath_TabooClasses()
    * @model keys="name" ordered="false"
    * @generated
    */
   EList<EClass> getTabooClasses();

} // PSPath
