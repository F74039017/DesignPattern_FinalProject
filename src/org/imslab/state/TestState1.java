package org.imslab.state;

import org.imslab.scene.SceneManager;

import javafx.event.ActionEvent;

public class TestState1 extends State {
	
	public TestState1(String name) {
		super(name);
	}

	@Override
	public void doAction(ActionEvent actionEvent, Context context) {
		context.setNextStateName("test2");
		SceneManager.getInstance().switchScene("ui2");
	}

}
