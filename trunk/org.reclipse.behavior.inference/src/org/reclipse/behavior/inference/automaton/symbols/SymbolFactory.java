package org.reclipse.behavior.inference.automaton.symbols;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4102 $ $Date: 2006-06-21 17:05:55 +0200 (Mi, 21 Jun 2006)
 *          $
 */
public class SymbolFactory {

	public SymbolFactory() {
		this.permittedMethodCallSymbols = new HashMap<String, PermittedMethodCallSymbol>();
		this.prohibitedMethodCallSymbols = new HashMap<String, ProhibitedMethodCallSymbol>();
		this.prohibitedCallerSymbols = new HashMap<String, ProhibitedCallerSymbol>();
		this.methodCallObjects = new HashMap<String, MethodCallObject>();
	}

	/**
	 * The empty symbol.
	 */
	public static final Epsilon EPSILON = new Epsilon();

	private final HashMap<String, PermittedMethodCallSymbol> permittedMethodCallSymbols;

	/**
	 * This methods returns a flyweight symbol object. If a symbol for the given
	 * arguments already exists it will be returned, otherwise a new one will be
	 * returned.
	 */
	public PermittedMethodCallSymbol providePermittedMethodCallSymbol(
			MethodCallObject caller, MethodCallObject callee, String methodName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(caller);
		buffer.append("->");
		buffer.append(callee);
		buffer.append(".");
		buffer.append(methodName);

		String key = buffer.toString();
		PermittedMethodCallSymbol symbol = this.permittedMethodCallSymbols
				.get(key);
		if (symbol == null) {
			symbol = createPermittedMethodCallSymbol(caller, callee, methodName);
			this.permittedMethodCallSymbols.put(key, symbol);
		}

		return symbol;
	}

	/**
	 * This method always returns a new symbol object for the given arguments.
	 */
	public PermittedMethodCallSymbol createPermittedMethodCallSymbol(
			MethodCallObject caller, MethodCallObject callee, String methodName) {
		PermittedMethodCallSymbol symbol = new PermittedMethodCallSymbol();
		symbol.setMethodName(methodName);
		symbol.setCaller(caller);
		symbol.setCallee(callee);
		return symbol;
	}

	private final HashMap<String, ProhibitedMethodCallSymbol> prohibitedMethodCallSymbols;

	/**
	 * This methods returns a flyweight symbol object. If a symbol for the given
	 * arguments already exists it will be returned, otherwise a new one will be
	 * returned.
	 */
	public ProhibitedMethodCallSymbol provideProhibitedMethodCallSymbol(
			MethodCallObject callee, String methodName) {
		if(methodName.contains("=")){  //no assignments as MethodCallSymbols
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(callee);
		buffer.append(".");
		buffer.append(methodName);

		String key = buffer.toString();
		ProhibitedMethodCallSymbol symbol = this.prohibitedMethodCallSymbols
				.get(key);
		if (symbol == null) {
			symbol = createProhibitedMethodCallSymbol(callee, methodName);
			this.prohibitedMethodCallSymbols.put(key, symbol);
		}

		return symbol;
	}

	/**
	 * This method always returns a new symbol object for the given arguments.
	 */
	public ProhibitedMethodCallSymbol createProhibitedMethodCallSymbol(
			MethodCallObject callee, String methodName) {
		ProhibitedMethodCallSymbol symbol = new ProhibitedMethodCallSymbol();
		symbol.setMethodName(methodName);
		symbol.setCallee(callee);

		return symbol;
	}

	private final HashMap<String, ProhibitedCallerSymbol> prohibitedCallerSymbols;

	/**
	 * This methods returns a flyweight symbol object. If a symbol for the given
	 * arguments already exists it will be returned, otherwise a new one will be
	 * returned.
	 */
	public ProhibitedCallerSymbol provideProhibitedCallerSymbol(
			List<MethodCallObject> permittedCallers, MethodCallObject callee,
			String methodName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		Iterator<MethodCallObject> iter = permittedCallers.iterator();
		while (iter.hasNext()) {
			MethodCallObject permittedCaller = iter.next();

			buffer.append(permittedCaller);

			if (iter.hasNext()) {
				buffer.append(", ");
			}
		}

		buffer.append("}->");
		buffer.append(callee);
		buffer.append(".");
		buffer.append(methodName);

		String key = buffer.toString();
		ProhibitedCallerSymbol symbol = this.prohibitedCallerSymbols.get(key);
		if (symbol == null) {
			symbol = createProhibitedCallerSymbol(permittedCallers, callee,
					methodName);
			this.prohibitedCallerSymbols.put(key, symbol);
		}

		return symbol;
	}

	/**
	 * This method always returns a new symbol object for the given arguments.
	 */
	public ProhibitedCallerSymbol createProhibitedCallerSymbol(
			List<MethodCallObject> permittedCallers, MethodCallObject callee,
			String methodName) {
		ProhibitedCallerSymbol symbol = new ProhibitedCallerSymbol();
		symbol.setMethodName(methodName);
		symbol.setCallee(callee);

		Iterator<MethodCallObject> iter = permittedCallers.iterator();
		while (iter.hasNext()) {
			symbol.addToPermittedCallers(iter.next());
		}

		return symbol;
	}

	private final HashMap<String, MethodCallObject> methodCallObjects;

	public MethodCallObject provideMethodCallObject(String name, String type) {
		return provideMethodCallObject(name, type, false, false, false);
	}

	public MethodCallObject provideMethodCallObject(String name, String type,
			boolean isSet) {
		return provideMethodCallObject(name, type, isSet, false, false);
	}

	public MethodCallObject provideMethodCallObject(String name, String type,
			boolean isSet, boolean foreach) {
		return provideMethodCallObject(name, type, isSet, foreach, false);
	}

	/**
	 * This methods returns a flyweight method call object. If a method call
	 * object for the given arguments already exists it will be returned,
	 * otherwise a new one will be returned.
	 */
	public MethodCallObject provideMethodCallObject(String name, String type,
			boolean isSet, boolean foreach, boolean other) {
		MethodCallObject methodCallObject = null;
		String set = "";
		StringBuffer buffer = new StringBuffer();
		if (isSet) {
			if (other) {
				name = "other";
			}
			buffer.append(name);
			set = "Set_" + type;
			buffer.append("in " + set);

		} else {
			buffer.append(name);
			buffer.append(":");
			buffer.append(type);
		}
		if(foreach){
			buffer.append(" forEach");
		}

		String key = buffer.toString();
		methodCallObject = this.methodCallObjects.get(key);
		if (methodCallObject == null) {
			methodCallObject = createMethodCallObject(name, type, set, foreach);
			this.methodCallObjects.put(key, methodCallObject);
		}

		return methodCallObject;
	}

	/**
	 * This method always returns a new method call object for the given
	 * arguments.
	 */
	public MethodCallObject createMethodCallObject(String name, String type,
			String set, boolean foreach) {
		if (set.length() > 0) {
			return new MethodCallSetObject(name, type, set, foreach);
		} else {
			return new MethodCallObject(name, type);
		}
	}

}
