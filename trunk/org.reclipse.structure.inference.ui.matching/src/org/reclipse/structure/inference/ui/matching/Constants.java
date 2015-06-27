package org.reclipse.structure.inference.ui.matching;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;


public abstract interface Constants
{
   public static final String OBJECT_DIAGRAM_LABEL_PREFIX = "od";

   public static final String SATISFIED_TEXT = "satisfied";

   public static final String UNSATISFIED_TEXT = "unsatisfied";

   public static final String SATISFACTION_PREFIX = SATISFIED_TEXT + "=";


   public static final Color DISSATISFIED_COLOR_FG = ColorConstants.gray;

   public static final Color DISSATISFIED_COLOR_BG = ColorConstants.white;

   public static final String NAME_ATTRIBUTE = "matched.object.name";

   public static final Color COLOR_SELECTED = new Color(PlatformUI
         .getWorkbench().getDisplay(), new RGB(255, 223, 148));
   

   public static final String KEY_SOURCE = "http://ns.reclipse.org/structure/inference/ui/matching/objects"; //$NON-NLS-1$

   public static final String KEY_SHOW = "show_set"; //$NON-NLS-1$

   public static final String KEY_BOUND = "bounded_index"; //$NON-NLS-1$
}
