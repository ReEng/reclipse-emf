package org.reclipse.structure.inference.notification;


import org.fujaba.commons.console.IProcessConsoleListener;
import org.reclipse.structure.inference.annotations.ASGAnnotation;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public interface InferenceProgressListener extends IProcessConsoleListener
{
   void init();


   void newValues(int current, int maximum);


   void newAnnotation(ASGAnnotation annotation);
}
