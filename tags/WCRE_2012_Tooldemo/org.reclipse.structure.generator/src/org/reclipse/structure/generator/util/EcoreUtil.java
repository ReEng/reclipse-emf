package org.reclipse.structure.generator.util;


import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;


/**
 * This class provides methods related to the {@link EcorePackage ecore package}.
 * 
 * @author Aljoscha Hark <aljoschability@googlemail.com>
 */
public final class EcoreUtil
{
	private EcoreUtil()
	{
		// hide constructor
	}


	public static EParameter getEParameter(EOperation operation, String name)
	{
		for (EParameter parameter : operation.getEParameters())
		{
			if (parameter.getName().equals(name))
			{
				return parameter;
			}
		}

		return null;
	}


	public static EOperation addEOperation(EClass container, String name)
	{
		EOperation element = EcoreFactory.eINSTANCE.createEOperation();

		element.setName(name);

		container.getEOperations().add(element);

		return element;
	}


	public static EParameter addEParameter(EOperation container, String name, EClassifier type)
	{
		EParameter element = EcoreFactory.eINSTANCE.createEParameter();

		element.setName(name);
		element.setEType(type);

		container.getEParameters().add(element);

		return element;
	}


	public static EClass addEClass(EPackage container, String name)
	{
		EClass element = EcoreFactory.eINSTANCE.createEClass();

		element.setName(name);

		container.getEClassifiers().add(element);

		return element;
	}


	public static EPackage addEPackage(Collection<EObject> container, String name, String uri)
	{
		EPackage element = EcoreFactory.eINSTANCE.createEPackage();

		element.setName(name);
		element.setNsPrefix(name);
		element.setNsURI(uri);

		container.add(element);

		return element;
	}
}
