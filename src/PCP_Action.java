import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;


public enum PCP_Action implements Action {
	Hospital,Home,Start;

	private static final Set<PCP_Action> _actions = new LinkedHashSet<PCP_Action>();
	static {
		_actions.add(Hospital);
		_actions.add(Home);
	}

	/**
	 * 
	 * @return a set of the actual actions.
	 */
	public static final Set<PCP_Action> actions() {
		return _actions;
	}
	
	//
	// START-Action
	@Override
	public boolean isNoOp() {
		if (Start == this) {
			return true;
		}
		return false;
	}
}
