package org.imslab.state;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class State {
		
	protected Context context;
	
	protected Map<Function<Context, Boolean>, State> transition;
	
	public State() {
		transition = new HashMap<>();
	}

	/**
	 * Check all registered transition filter. 
	 * If the filter returns true, then the current state will switch to the next state.
	 */
	public boolean checkAndChangeState() {
		for (Function<Context, Boolean> f : transition.keySet()) {
			if(f.apply(this.context)) {
				System.out.println(transition.get(f).getClass().getName());
				changeToState(transition.get(f));
				break; // only change once.
			}
		}
		return false;
	}
	
	/**
	 * Register the filter. If the filter is true, then the state will switch to the next state.
	 * 
	 * @param f				filter
	 * @param nextState		nextState
	 */
	public void registerTransition(Function<Context, Boolean> f, State s) {
		transition.put(f, s);
	}

	/**
	 * Copy context to next state and go to next state
	 * 
	 * @param state 		next state
	 */
	public void changeToState(State state) {
		state.setContext(this.context);
		context.setCurrentState(state);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
}
