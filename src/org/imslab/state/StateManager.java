package org.imslab.state;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.imslab.scene.SceneManager;

/**
 * Control the states and swtich between different scenes.
 */
public class StateManager {
	
	private Map<String, State> stateMap;
	private SceneManager sceneManager;
	
	public StateManager() {
		stateMap = new HashMap<>();
	}
	
	public StateManager(SceneManager sceneManager) {
		this();
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Create transition between two states.
	 * @param start  Start state
	 * @param end    End state
	 * @param trans  The filter used to check whether switch state or not
	 */
	public void addTransition(State start, State end, Function<Context, Boolean> trans) {
		start.registerTransition(trans, end);
	}
	
	public void addState(String key, State state) {
		stateMap.put(key, state);
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
	
}
