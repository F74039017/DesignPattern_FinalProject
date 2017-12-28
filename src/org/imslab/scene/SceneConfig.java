package org.imslab.scene;

public class SceneConfig {
	
	// Not allow programmer to instantialize this class.
	private SceneConfig() {
		
	}
	
	public static final String[] FXML_LIST = {
			"ui.fxml",
			"ui2.fxml"
	};
	
	public static final String DEFAULT_SCENE = "ui";
}
