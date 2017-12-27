package org.imslab.state;

public class Context {
	
	private State currentState = null;
	
	public Context() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static class ContextBuilder {
		
		private Context context;
	    
	    public Context build() {
	    		return context;
	    }
	}


	/* Accessors */
	
	public State getCurrentState() {
		return currentState;
	}


	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
}
