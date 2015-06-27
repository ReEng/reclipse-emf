/**
 * Copyright 2011 University of Paderborn. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors: Aljoscha Hark - initial API and implementation.
 */
package org.reclipse.structure.generator;

import org.reclipse.structure.generator.util.Constants;


/**
 * This interface delivers all relevant constants used during the story pattern generation.
 */
public interface GeneratorConstants
{
   /**
    * The namespace URI of the generated package containing the engine classes.
    */
   String PACKAGE_URI = Constants.PACKAGE_URI_ENGINES;

   /**
    * The suffix added to the pattern name for its engine class.
    */
   String CLASS_SUFFIX = Constants.SUFFIX_ENGINE;

   /**
    * The name of the 'annotate' method inside an engine class.
    */
   String METHOD = Constants.METHOD_ANNOTATE;

   /**
    * The name of the 'this' parameter of the 'annotate' method.
    */
   String PARAMETER_THIS = Constants.VAR_THIS;

   /**
    * The name of the 'context' parameter of the 'annotate' method.
    */
   String PARAMETER_CONTEXT = Constants.VAR_CONTEXT;

   /**
    * The name of the 'context' parameter of the 'annotate' method.
    */
   String PARAMETER_SEARCH_ADDITIONALS = Constants.VAR_SEARCH_ADDITIONALS;
}
