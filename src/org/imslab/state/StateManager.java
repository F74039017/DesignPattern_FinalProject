package org.imslab.state;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Control the states and swtich between different scenes.
 */
public class StateManager {
	
	private Map<String, State> stateMap;
	private State currentState = null;
	private Context context = null;
	
	// Singleton pattern
	private static StateManager stateManager = null;
		
	private StateManager() {
		stateMap = new HashMap<>();
		
		// init all states
		initAllState();
		initTransition();
		
		// init state
		try {
			setInitState("test1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static StateManager getInstance() {
		if (stateManager == null) {
			stateManager = new StateManager();
		}
		return stateManager;
	}
	
	
	private void setInitState(String stateName) throws Exception {
		if (!checkState(stateName)) {
			throw new Exception("Initial state name doesn't exist.");
		}
		currentState = stateMap.get(stateName);
		
		// TODO: use builder pattern to bulid it
		context = new Context();
		context.setCurrentState(currentState);
		context.setValidNextState(currentState.getValidNextState());
		context.setNextStateName(currentState.getName());
		
		currentState.setContext(context);
	}
	
	private void initAllState() {
		addState(new TestState1("test1"));
		addState(new TestState2("test2"));
	}
	
	private void initTransition() {
		
		Function<Context, Boolean> defaultFilter = (c) -> {
			System.out.println("Check in default filter");
			if (c.checkValidNextState(c.getNextStateName())) {
				return true;
			}
			return false;
		};
		
		try {
			// add some transitions.
			addTransition("test1", "test2", defaultFilter);;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Create transition between two states.
	 * @param start  Start state name
	 * @param end    End state name
	 * @param trans  The filter used to check whether switch state or not
	 * @throws Exception    start or end state not exist.
	 */
	public void addTransition(String start, String end, Function<Context, Boolean> trans) throws Exception {
		if (!checkState(start)) {
			throw new Exception("Empty start state " + start.toString());
		}
		if (!checkState(end)) {
			throw new Exception("Empty end start " + end.toString());
		}
		
		stateMap.get(start).registerTransition(trans, stateMap.get(end));
	}
	
	public void addState(State state) {
		stateMap.put(state.getName(), state);
	}
	
	public void removeState(String key) {
		stateMap.remove(key);
	}
	
	/**
	 * Check if the state was added by state key name.
	 * @param key 
	 * @return true if the key was added.
	 */
	public boolean checkState(String key) {
		if(stateMap.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the state was added by state instance.
	 * @param state
	 * @return true if the state was added.
	 */
	public boolean checkState(State state) {
		if(stateMap.containsValue(state)) {
			return true;
		}
		return false;
	}

	/* Accessors */
	
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
}
