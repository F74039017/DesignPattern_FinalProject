package org.imslab.state;

import java.util.List;

public class Context {
	
	private State currentState = null;
	
	// Used by transition. If it same as currentState, then the state won't be changed.
	private String nextStateName = "";
	
	private List<State> validNextState;
	
	public Context() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static class ContextBuilder {
		
		private Context context;
	    
	    public Context build() {
	    		return context;
	    }
	}
	
	public boolean checkValidNextState(String stateName) {
		for (State validState : validNextState) {
			if (stateName == validState.getName()) {
				return true;
			}
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


	public void setValidNextState(List<State> validNextState) {
		this.validNextState = validNextState;
	}


	public String getNextStateName() {
		return nextStateName;
	}


	public void setNextStateName(String nextStateName) {
		this.nextStateName = nextStateName;
	}
}
