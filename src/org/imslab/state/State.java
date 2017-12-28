package org.imslab.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javafx.event.ActionEvent;

public abstract class State implements Processable {
		
	protected Context context;
	protected String name;
	protected Map<Function<Context, Boolean>, State> transition;
	protected List<State> validNextState = null;
	
	private State() {
		transition = new HashMap<>();
		validNextState = new ArrayList<>();
	}
	
	public State(String name) {
		this();
		this.name = name;
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
		validNextState.add(s);
	}

	/**
	 * Copy context to next state and go to next state
	 * 
	 * @param state 		next state
	 */
	public void changeToState(State state) {
		state.setContext(this.context);
		context.setValidNextState(state.getValidNextState());
		context.setCurrentState(state);
		System.out.println("Change to state: " + state.toString());
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<State> getValidNextState() {
		return validNextState;
	}

	private void setValidNextState(List<State> validNextState) {
		this.validNextState = validNextState;
	}
	
	/**
	 * Process the action event and check whether it need to change state.
	 * @param actionEvent
	 */
	public void process(ActionEvent actionEvent) {
		doAction(actionEvent, context);
		checkAndChangeState();
	}
	
	/**
	 * State subclass should override this class to deal with the ui action event.
	 */
	abstract public void doAction(ActionEvent actionEvent, Context context);
	
}
