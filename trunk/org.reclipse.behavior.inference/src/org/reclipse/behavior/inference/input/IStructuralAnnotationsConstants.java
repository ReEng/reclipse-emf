package org.reclipse.behavior.inference.input;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4347 $ $Date: 2010-05-18 17:24:58 +0200 (Di, 18 Mai 2010) $
 */
public interface IStructuralAnnotationsConstants
{

   public final static String SYSTEM_ID = "http://wwwcs.uni-paderborn.de/cs/fujaba/DTDs/StructuralAnnotations.dtd";


   public static final String XML_FILE_SUFFIX = ".xannotations";

   public static final String ZIP_FILE_SUFFIX = ".zannotations";
   
   public static final String[] NEW_FILE_EXTENSIONS = new String [] {
      "*.psa"};

   public static final String[] FILE_EXTENSIONS = new String[] {
         "*" + ZIP_FILE_SUFFIX + "; *" + XML_FILE_SUFFIX };

   public static final String[] NEW_FILE_DESCRIPTIONS = new String[] {
         "Annotations"};
   
   public static final String[] FILE_DESCRIPTIONS = new String[] {
         "(Compressed) Structural Pattern Annotations"};

   public static final String FILE_NAME = "StructuralAnnotations.xannotations";


   public final static String STRUCTURAL_ANNOTATIONS_TAG = "StructuralAnnotations";

   public final static String STRUCTURAL_ANNOTATION_TAG = "StructuralAnnotation";

   public final static String BOUND_OBJECT_TAG = "BoundObject";

   public final static String SIZE_ATTRIBUTE = "size";

   public final static String KEY_ATTRIBUTE = "key";

   public final static String NAME_ATTRIBUTE = "name";

   public final static String FUZZY_BELIEF_ATTRIBUTE = "fuzzyBelief";

}
