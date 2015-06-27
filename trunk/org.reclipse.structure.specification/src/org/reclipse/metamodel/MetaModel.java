package org.reclipse.metamodel;


import java.util.Collection;
import java.util.HashSet;


/**
 * This class represents all data for a meta model. It is used to show the registered meta model
 * extensions and to return the package URIs to a newly created pattern catalog. Maybe it will also
 * be useful in the future for other things.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class MetaModel
{

   /**
    * The identifier for the meta model.
    */
   private String id;

   /**
    * The name for the meta model.
    */
   private String name;

   /**
    * The version information for the meta model.
    */
   private String version;

   /**
    * The description of the meta model.
    */
   private String description;

   /**
    * The packages that has been registered for the meta model.
    */
   private Collection<String> packages;

   /**
    * The trigger chooser (maybe null).
    */
   private ITriggerChooser chooser;

   /**
    * The element labeler (maybe null).
    */
   private AbstractElementLabeler labeler;

   /**
    * The AST preparer (maybe null).
    */
   private IASTPreparer preparer;


   /**
    * Default constructor. Initializes the object with all values.
    * 
    * @param id The identifier for the meta model.
    * @param name The name for the meta model.
    * @param version The version information for the meta model.
    * @param description An optional description of the meta model.
    */
   public MetaModel(String id, String name, String version, String description)
   {
      this.id = id;
      this.name = name;
      this.version = version;
      this.description = description;

      packages = new HashSet<String>();
   }


   /**
    * Getter of the meta model identifier.
    * 
    * @return Returns the id.
    * @see #id
    */
   public String getId()
   {
      return this.id;
   }


   /**
    * Getter of the meta model name.
    * 
    * @return Returns the (trimmed) name.
    * @see #name
    */
   public String getName()
   {
      if (name != null && !name.trim().isEmpty())
      {
         return name.trim();
      }

      return "No valid name provided.";
   }


   /**
    * Getter of the meta model version information.
    * 
    * @return Returns the (trimmed) version information.
    * @see #version
    */
   public String getVersion()
   {
      if (version != null && !version.trim().isEmpty())
      {
         return version.trim();
      }

      return "?";
   }


   /**
    * Getter of the meta model description.
    * 
    * @return meta model's description
    * @see #description
    */
   public String getDescription()
   {
      if (description != null && !description.trim().isEmpty())
      {
         return description.trim();
      }

      return "No description provided.";
   }


   public Collection<String> getPackages()
   {
      return packages;
   }


   public boolean addPackage(String uri)
   {
      return packages.add(uri);
   }


   public ITriggerChooser getTriggerChooser()
   {
      return chooser;
   }


   public IASTPreparer getASTPreparer()
   {
      return preparer;
   }


   public AbstractElementLabeler getLabeler()
   {
      return labeler;
   }


   public void setTriggerChooser(ITriggerChooser chooser)
   {
      this.chooser = chooser;
   }


   public void setASTPreparer(IASTPreparer preparer)
   {
      this.preparer = preparer;
   }


   public void setLabeler(AbstractElementLabeler labeler)
   {
      this.labeler = labeler;
   }
}
