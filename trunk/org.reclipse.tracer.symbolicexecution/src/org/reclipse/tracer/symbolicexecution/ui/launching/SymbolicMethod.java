package org.reclipse.tracer.symbolicexecution.ui.launching;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants;


public class SymbolicMethod
{
   private String symbolicMethodString = null;

   private List<SymbolicMethodParam> params = null;

   private String name = null;


   /**
    * Usually this constructor is used, when loading a method from a saved configuration.
    * 
    * @param symbolicMethodString
    * @param methodSignature
    */
   public SymbolicMethod(String symbolicMethodString, String methodSignature)
   {
      this.symbolicMethodString = symbolicMethodString;
      this.params = new ArrayList<SymbolicMethodParam>();
      createAttributesFromSymbolicMethodString(methodSignature);
   }


   /**
    * Usually this constructor is used, when adding a method per button.
    * 
    * @param methodString
    */
   public SymbolicMethod(String methodSignature)
   {
      this.params = new ArrayList<SymbolicMethodParam>();
      createAttributesFromMethodSignature(methodSignature);
      this.symbolicMethodString = createSymbolicMethodStringFromAttributes();
   }


   private void createAttributesFromMethodSignature(String methodSignature)
   {
      // create name
      this.name = methodSignature.split("\\(")[0];

      // create params
      int paramSectionBegin = methodSignature.indexOf("(") + 1;
      int paramSectionEnd = methodSignature.indexOf(")");
      String params = methodSignature.substring(paramSectionBegin,
            paramSectionEnd);
      if (params.length() > 0)
      {
         String[] names = params.split(",");
         for (int i = 0; i < names.length; i++)
         {
            // initialize all params as symbolic
            this.addParam(new SymbolicMethodParam(names[i],
                  SymbolicExecutionTracerConstants.PARAM_SYM_SHORT));
         }
      }
   }


   private String createSymbolicMethodStringFromAttributes()
   {
      StringBuffer stringBuffer = new StringBuffer();

      // create name section
      stringBuffer.append(this.name);

      // create param section
      stringBuffer.append("(");
      Iterator<SymbolicMethodParam> paramsIter = this.params.iterator();
      while (paramsIter.hasNext())
      {
         SymbolicMethodParam param = (SymbolicMethodParam) paramsIter.next();
         stringBuffer.append(param.getValue());
         if (paramsIter.hasNext())
         {
            stringBuffer.append("#");
         }
      }
      stringBuffer.append(")");

      return stringBuffer.toString();
   }


   private void createAttributesFromSymbolicMethodString(String methodSignature)
   {
      // create name
      this.name = methodSignature.split("\\(")[0];

      // create params
      int symbolicMethodStringParamSectionBegin = this.symbolicMethodString
            .indexOf("(") + 1;
      int symbolicMethodStringParamSectionEnd = this.symbolicMethodString
            .indexOf(")");
      int methodSignatureParamSectionBegin = methodSignature.indexOf("(") + 1;
      int methodSignatureParamSectionEnd = methodSignature.indexOf(")");
      String paramValues = this.symbolicMethodString.substring(
            symbolicMethodStringParamSectionBegin,
            symbolicMethodStringParamSectionEnd);
      String paramNames = methodSignature.substring(
            methodSignatureParamSectionBegin, methodSignatureParamSectionEnd);
      if (paramValues.length() > 0 && paramNames.length() > 0)
      {
         String[] values = paramValues.split("#");
         String[] names = paramNames.split(",");
         for (int i = 0; i < names.length; i++)
         {
            this.addParam(new SymbolicMethodParam(names[i], values[i]));
         }
      }
   }


   public String getSymbolicMethodString()
   {
      return this.symbolicMethodString;
   }


   public void setSymbolicMethodString(String symbolicMethodString)
   {
      this.symbolicMethodString = symbolicMethodString;
   }


   public List<SymbolicMethodParam> getParams()
   {
      return this.params;
   }


   public void setParams(List<SymbolicMethodParam> params)
   {
      this.params = params;
   }


   public void addParam(SymbolicMethodParam symbolicMethodParam)
   {
      if (!paramIsAlreadyContained(symbolicMethodParam.getName()))
      {
         this.params.add(symbolicMethodParam);
         this.refreshString();
      }

   }


   /**
    * Returns true, if a param with the given name is already contained in the method's param list.
    * 
    * @param paramName
    * @return
    */
   public boolean paramIsAlreadyContained(String paramName)
   {
      for (SymbolicMethodParam param : this.params)
      {
         if (param.getName().equals(paramName))
         {
            return true;
         }
      }
      return false;
   }


   /**
    * Returns true, if the param with the given name is stored as "sym". Returns true, if it is
    * stored as "con".
    * 
    * @param paramName
    * @return
    */
   public boolean paramIsSym(String paramName)
   {
      for (SymbolicMethodParam param : this.params)
      {
         if (param.getName().equals(paramName)
               && param.getValue().equals(
                     SymbolicExecutionTracerConstants.PARAM_SYM_SHORT))
         {
            return true;
         }
      }
      return false;
   }


   public String[] getParameterNames()
   {
      String[] paramNames = new String[this.params.size()];
      for (int i = 0; i < paramNames.length; i++)
      {
         paramNames[i] = this.params.get(i).getName();

      }
      return paramNames;
   }


   @Override
   public String toString()
   {
      return this.symbolicMethodString;
   }


   public void refreshString()
   {
      this.symbolicMethodString = createSymbolicMethodStringFromAttributes();
   }


}
