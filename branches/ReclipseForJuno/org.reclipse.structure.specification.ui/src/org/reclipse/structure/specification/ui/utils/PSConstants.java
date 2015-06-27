package org.reclipse.structure.specification.ui.utils;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;


/**
 * This class holds constants for the pattern specification editor.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public interface PSConstants
{
   public final static Color COLOR__CREATE = ColorConstants.darkGreen;

   public final static String ANNOTATED_ELEMENT = "annotatedElement";

   public final static String CONSTRAINT_DEFAULT_EXPRESSION = "\u00abexpression\u00bb";

   public final static String CONSTRAINT_TEXT_FUZZY = "fuzzy";

   public final static String CONSTRAINT_TEXT_SETRATINGFUNCTION = "set rating function";

   public final static String CONSTRAINT_TEXT_SIZE = "Size";

   public final static String CONSTRAINT_TEXT_SIZE_ATTR = "SIZE";

   public final static String DEFAULT__MODEL_NAME_PREFIX__ANNOTATION = "anno";

   public final static String DEFAULT__MODEL_NAME_PREFIX__CONSTRAINT = "constr";

   public final static String DEFAULT__MODEL_NAME_PREFIX__FRAGMENT = "frag";

   public final static String DEFAULT__MODEL_NAME_PREFIX__LINK = "link";

   public final static String DEFAULT__MODEL_NAME_PREFIX__OBJECT = "obj";

   public final static String DEFAULT__MODEL_NAME_PREFIX__PATH = "path";

   public final static double DEFAULT_WEIGHT = 1.0;

   public final static String DIAGRAM_PREFIX_CATALOG = "pc";

   public final static String DIAGRAM_PREFIX_PATTERNSPECIFICATION = "sp";

   public final static String LABEL_ADDITIONAL = "additional";

   public final static String LABEL_NEGATIVE = "negative";

   public final static String LABEL_NONE = "none";

   public final static String LABEL_SET = "set";

   public final static String LABEL_CREATE = "\u00abcreate\u00bb";

   public final static String LABEL_NO_RANGE = "\u00abno qualifier\u00bb";

   public final static String LABEL_PATTERN = "Pattern";

   public final static String LABEL_TABOO_CLASSES = "w/o";

   public final static String REG_EXPRESSION_STRING = "RegEx";

   public final static String META_MODEL_REFERENCE_KEY = "org.reclipse.structure.specification.metamodel";

   public final static String METRICS_CONFIG_REFERENCE_KEY = "org.reclipse.structure.specification.metricsconfiguration";

}
