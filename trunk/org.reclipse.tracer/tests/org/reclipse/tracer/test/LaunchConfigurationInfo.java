package org.reclipse.tracer.test;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * The information associated with a launch configuration handle.
 * 
 * @author lowende
 * @author Last Editor: $Author: lowende $
 * @version $Revision: 3728 $ $Date: 2007-09-03 17:34:11 +0200 (Mo, 03 Sep 2007) $
 */
public class LaunchConfigurationInfo
{

   /**
    * This configurations attribute table. Keys are <code>String</code>s and values are one of
    * <code>String</code>, <code>Integer</code>, or <code>Boolean</code>.
    */
   private HashMap fAttributes;

   /**
    * This launch configuration's type
    */
   private String fType;


   /**
    * Constructs a new empty info
    */
   public LaunchConfigurationInfo()
   {
      this.fAttributes = new HashMap(10);
   }


   /**
    * Returns this configuration's attribute table.
    * 
    * @return attribute table
    */
   private HashMap getAttributeTable()
   {
      return this.fAttributes;
   }


   /**
    * Returns the <code>String</code> attribute with the given key or the given default value if
    * undefined.
    * 
    * @return attribute specified by given key or the defaultValue if undefined
    * @throws ClassCastException if the attribute with the given key exists but is not a
    *            <code>String</code>
    */
   public String getStringAttribute(String key, String defaultValue)
   {
      Object attr = getAttributeTable().get(key);
      if (attr != null)
      {
         return (String) attr;
      }
      return defaultValue;
   }


   /**
    * Returns the <code>int</code> attribute with the given key or the given default value if
    * undefined.
    * 
    * @return attribute specified by given key or the defaultValue if undefined
    * @throws ClassCastException if the attribute with the given key exists but is not an
    *            <code>int</code>
    */
   public int getIntAttribute(String key, int defaultValue)
   {
      Object attr = getAttributeTable().get(key);
      if (attr != null)
      {
         return ((Integer) attr).intValue();
      }
      return defaultValue;
   }


   /**
    * Returns the <code>boolean</code> attribute with the given key or the given default value if
    * undefined.
    * 
    * @return attribute specified by given key or the defaultValue if undefined
    * @throws ClassCastException if the attribute with the given key exists but is not a
    *            <code>boolean</code>
    */
   public boolean getBooleanAttribute(String key, boolean defaultValue)
   {
      Object attr = getAttributeTable().get(key);
      if (attr != null)
      {
         return ((Boolean) attr).booleanValue();
      }
      return defaultValue;
   }


   /**
    * Returns the <code>java.util.List</code> attribute with the given key or the given default
    * value if undefined.
    * 
    * @return attribute specified by given key or the defaultValue if undefined
    * @throws ClassCastException if the attribute with the given key exists but is not a
    *            <code>java.util.List</code>
    */
   public List getListAttribute(String key, List defaultValue)
   {
      Object attr = getAttributeTable().get(key);
      if (attr != null)
      {
         return (List) attr;
      }
      return defaultValue;
   }


   /**
    * Returns the <code>java.util.Set</code> attribute with the given key or the given default
    * value if undefined.
    * 
    * @return attribute specified by given key or the defaultValue if undefined
    * @throws CoreException if the attribute with the given key exists but is not a
    *            <code>java.util.Set</code>
    * 
    * @since 3.3
    */
   protected Set getSetAttribute(String key, Set defaultValue)
         throws CoreException
   {
      Object attr = getAttributeTable().get(key);
      if (attr != null)
      {
         if (attr instanceof Set)
         {
            return (Set) attr;
         }
         throw new CoreException(new Status(IStatus.ERROR,
               "org.reclipse.tracer.test",
               "Error getting attribute of type java.util.Set."));
      }
      return defaultValue;
   }


   /**
    * Returns the <code>java.util.Map</code> attribute with the given key or the given default
    * value if undefined.
    * 
    * @return attribute specified by given key or the defaultValue if undefined
    * @throws ClassCastException if the attribute with the given key exists but is not a
    *            <code>java.util.Map</code>
    */
   public Map getMapAttribute(String key, Map defaultValue)
   {
      Object attr = getAttributeTable().get(key);
      if (attr != null)
      {
         return (Map) attr;
      }
      return defaultValue;
   }


   /**
    * Sets this configuration's type.
    * 
    * @param type launch configuration type
    */
   public void setType(String type)
   {
      this.fType = type;
   }


   /**
    * Returns this configuration's type.
    * 
    * @return launch configuration type
    */
   public String getType()
   {
      return this.fType;
   }


   /**
    * Sets the given attribute to the given value. Only working copy's should use this API.
    * 
    * @param key attribute key
    * @param value attribute value
    */
   public void setAttribute(String key, Object value)
   {
      if (value == null)
      {
         getAttributeTable().remove(key);
      }
      else
      {
         getAttributeTable().put(key, value);
      }
   }


   /**
    * Returns a copy of this info's attribute map.
    * 
    * @return a copy of this info's attribute map
    */
   protected HashMap getAttributes()
   {
      return (HashMap) getAttributeTable().clone();
   }


   /**
    * Returns the content of this info as XML
    * 
    * @return the content of this info as XML
    * @throws NullPointerException if a attribute has been set with a null key
    * @throws UnsupportedEncodingException if an exception occurs creating the XML
    * @throws ParserConfigurationException if an exception occurs creating the XML
    * @throws TransformerException if an exception occurs creating the XML
    * @throws UnsupportedEncodingException
    */
   protected String getAsXML()
         throws ParserConfigurationException,
            TransformerException,
            UnsupportedEncodingException
   {
      DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();

      Element configRootElement = doc.createElement("launchConfiguration");
      doc.appendChild(configRootElement);

      configRootElement.setAttribute("type", getType());

      Iterator keys = getAttributeTable().keySet().iterator();
      while (keys.hasNext())
      {
         String key = (String) keys.next();
         if (key == null)
         {
            throw new NullPointerException("Key is null!");
         }
         Object value = getAttributeTable().get(key);
         if (value == null)
         {
            continue;
         }
         Element element = null;
         String valueString = null;
         if (value instanceof String)
         {
            valueString = (String) value;
            element = createKeyValueElement(doc, "stringAttribute", key,
                  valueString);
         }
         else if (value instanceof Integer)
         {
            valueString = ((Integer) value).toString();
            element = createKeyValueElement(doc, "intAttribute", key,
                  valueString);
         }
         else if (value instanceof Boolean)
         {
            valueString = ((Boolean) value).toString();
            element = createKeyValueElement(doc, "booleanAttribute", key,
                  valueString);
         }
         else if (value instanceof List)
         {
            element = createListElement(doc, "listAttribute", key, (List) value);
         }
         else if (value instanceof Map)
         {
            element = createMapElement(doc, "mapAttribute", key, (Map) value);
         }
         configRootElement.appendChild(element);
      }

      return serializeDocument(doc);
   }


   private String serializeDocument(Document doc)
         throws TransformerException,
            UnsupportedEncodingException
   {
      ByteArrayOutputStream s = new ByteArrayOutputStream();

      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);
      StreamResult outputTarget = new StreamResult(s);
      transformer.transform(source, outputTarget);

      return s.toString("UTF8");
   }


   /**
    * Helper method that creates a 'key value' element of the specified type with the specified
    * attribute values.
    */
   private Element createKeyValueElement(Document doc, String elementType,
         String key, String value)
   {
      Element element = doc.createElement(elementType);
      element.setAttribute("key", key);
      element.setAttribute("value", value);
      return element;
   }


   private Element createListElement(Document doc, String elementType,
         String listKey, List list)
   {
      Element listElement = doc.createElement(elementType);
      listElement.setAttribute("key", listKey);
      Iterator iterator = list.iterator();
      while (iterator.hasNext())
      {
         String value = (String) iterator.next();
         Element element = doc.createElement("listEntry");
         element.setAttribute("value", value);
         listElement.appendChild(element);
      }
      return listElement;
   }


   private Element createMapElement(Document doc, String elementType,
         String mapKey, Map map)
   {
      Element mapElement = doc.createElement(elementType);
      mapElement.setAttribute("key", mapKey);
      Iterator iterator = map.keySet().iterator();
      while (iterator.hasNext())
      {
         String key = (String) iterator.next();
         String value = (String) map.get(key);
         Element element = doc.createElement("mapEntry");
         element.setAttribute("key", key);
         element.setAttribute("value", value);
         mapElement.appendChild(element);
      }
      return mapElement;
   }


   protected void initializeFromXML(Element root)
   {
      // read type
      setType(root.getAttribute("type"));

      NodeList list = root.getChildNodes();
      int length = list.getLength();
      for (int i = 0; i < length; ++i)
      {
         Node node = list.item(i);
         short nodeType = node.getNodeType();
         if (nodeType == Node.ELEMENT_NODE)
         {
            Element element = (Element) node;
            String nodeName = element.getNodeName();

            if (nodeName.equalsIgnoreCase("stringAttribute"))
            {
               setStringAttribute(element);
            }
            else if (nodeName.equalsIgnoreCase("intAttribute"))
            {
               setIntegerAttribute(element);
            }
            else if (nodeName.equalsIgnoreCase("booleanAttribute"))
            {
               setBooleanAttribute(element);
            }
            else if (nodeName.equalsIgnoreCase("listAttribute"))
            {
               setListAttribute(element);
            }
            else if (nodeName.equalsIgnoreCase("mapAttribute"))
            {
               setMapAttribute(element);
            }
         }
      }
   }


   private void setStringAttribute(Element element)
   {
      String key = getKeyAttribute(element);
      String value = getValueAttribute(element);
      setAttribute(key, value);
   }


   private void setIntegerAttribute(Element element)
   {
      String key = getKeyAttribute(element);
      String value = getValueAttribute(element);
      setAttribute(key, new Integer(value));
   }


   private void setBooleanAttribute(Element element)
   {
      String key = getKeyAttribute(element);
      String value = getValueAttribute(element);
      setAttribute(key, Boolean.valueOf(value));
   }


   private void setListAttribute(Element element)
   {
      String listKey = element.getAttribute("key");
      NodeList nodeList = element.getChildNodes();
      int entryCount = nodeList.getLength();
      List list = new ArrayList(entryCount);
      for (int i = 0; i < entryCount; i++)
      {
         Node node = nodeList.item(i);
         short type = node.getNodeType();
         if (type == Node.ELEMENT_NODE)
         {
            Element subElement = (Element) node;
            String nodeName = subElement.getNodeName();
            if (!nodeName.equalsIgnoreCase("listEntry"))
            {
               throw new IllegalStateException(
                     "Element's type is not of the expected type 'listEntry'.");
            }
            String value = getValueAttribute(subElement);
            list.add(value);
         }
      }
      setAttribute(listKey, list);
   }


   private void setMapAttribute(Element element)
   {
      String mapKey = element.getAttribute("key");
      NodeList nodeList = element.getChildNodes();
      int entryCount = nodeList.getLength();
      Map map = new HashMap(entryCount);
      for (int i = 0; i < entryCount; i++)
      {
         Node node = nodeList.item(i);
         short type = node.getNodeType();
         if (type == Node.ELEMENT_NODE)
         {
            Element subElement = (Element) node;
            String nodeName = subElement.getNodeName();
            if (!nodeName.equalsIgnoreCase("mapEntry"))
            {
               throw new IllegalStateException(
                     "Element's type is not of the expected type 'mapEntry'.");
            }
            String key = getKeyAttribute(subElement);
            String value = getValueAttribute(subElement);
            map.put(key, value);
         }
      }
      setAttribute(mapKey, map);
   }


   private String getKeyAttribute(Element element)
   {
      String key = element.getAttribute("key");
      if (key == null)
      {
         throw new NullPointerException("Key is null!");
      }
      return key;
   }


   private String getValueAttribute(Element element)
   {
      String value = element.getAttribute("value");
      if (value == null)
      {
         throw new NullPointerException("Key is null!");
      }
      return value;
   }
}
