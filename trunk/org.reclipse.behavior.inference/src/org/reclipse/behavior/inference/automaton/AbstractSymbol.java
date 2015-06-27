package org.reclipse.behavior.inference.automaton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.reclipse.tracer.model.tracegraph.TGMethodCall;

/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                 1      symbol      1..n 
 * AbstractSymbol ------------------------- Transition
 *                 symbol      transitions
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2007-02-14 17:13:38 +0100 (Mi, 14 Feb 2007)
 *          $
 */
public abstract class AbstractSymbol {

	/**
	 * <pre>
	 *                 1      symbol      1..n 
	 * AbstractSymbol ------------------------- Transition
	 *                 symbol      transitions
	 * </pre>
	 */
	private HashSet<Transition> transitions;

	public boolean addToTransitions(Transition value) {
		boolean changed = false;
		if (value != null) {
			if (this.transitions == null) {
				this.transitions = new HashSet<Transition>();
			}
			changed = this.transitions.add(value);
			if (changed) {
				value.setSymbol(this);
			}
		}
		return changed;
	}

	public boolean hasInTransitions(Transition value) {
		return ((this.transitions != null) && (value != null) && this.transitions
				.contains(value));
	}

	public Iterator<Transition> iteratorOfTransitions() {
		return ((this.transitions == null) ? Collections.<Transition>emptyList().iterator()
				: this.transitions.iterator());
	}

	public int sizeOfTransitions() {
		return ((this.transitions == null) ? 0 : this.transitions.size());
	}

	public boolean removeFromTransitions(Transition value) {
		boolean changed = false;
		if ((this.transitions != null) && (value != null)) {
			changed = this.transitions.remove(value);
			if (changed) {
				value.setSymbol(null);
			}
		}
		return changed;
	}

	public void removeAllFromTransitions() {
		Transition tmpValue;
		Iterator<Transition> iter = iteratorOfTransitions();
		while (iter.hasNext()) {
			tmpValue = (Transition) iter.next();
			removeFromTransitions(tmpValue);
		}
	}

	private Assignment assignment;

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	/**
	 * Checks if the methodCall is represented by the symbol.
	 * 
	 * @param methodCall
	 *            the current method call
	 * @param token
	 *            the token, representing the current bindings
	 * @return true, if the symbol represents the given method call
	 */
	public abstract boolean accept(TGMethodCall methodCall, Token token);

	public abstract String getSymbolText();

	public void removeYou() {
		removeAllFromTransitions();
	}

}
