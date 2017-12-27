package org.imslab;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage primaryStage;
	private Map<String, Scene> sceneMap;
	private static final String[] fxmlList = {
			"ui.fxml",
			"ui2.fxml"
	};
	private static final String defaultSecene = "ui";
		
	public SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		sceneMap = new HashMap<>();
		initAllScene();
	}
	
	private void initAllScene() {
		for (String string : fxmlList) {
			try {
				addScene(string);
			} catch (Exception e) {
				System.err.println("IOExcpetion when create scene "+string);
			}
		}
		
		if (existScene(defaultSecene)) {
			primaryStage.setScene(sceneMap.get(defaultSecene));
		}
		else {
			System.err.println("Can not found default scene.");
			System.exit(1);
		}
	}
	
	/**
	 * Create and return the instance of scene corresponding to the .fxml file
	 * @param url  URL of .fxml file
	 * @return 
	 * @throws IOException
	 */
	private Scene createScene(String url) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		loader.setControllerFactory(t -> new Controller(this));
		Parent root = loader.load();
		return new Scene(root);
	}
	
	/**
	 * Create scene corresponding to the .fxml file, and add it to the sceneList.
	 * @param url  URL of .fxml file
	 * @throws IOException 
	 */
	public void addScene(String url) throws IOException {
		Scene scene = createScene(url);
		
		// prepare scene name
		int lastSlash = url.lastIndexOf('/');
		int lastDot = url.lastIndexOf('.');
		String sceneName = url.substring(lastSlash+1, lastDot);
		
		sceneMap.put(sceneName, scene);

		// debug
		System.out.println("Create scene "+sceneName);
	}
	
	public Map<String,Scene> getAllScene() {
		Map<String, Scene> clone = new HashMap<>(sceneMap);
		return clone;
	}
	
	/**
	 * Check whether the scene name exist in the sceneList
	 * @param sceneName
	 * @return
	 */
	public boolean existScene(String sceneName) {
		return sceneMap.containsKey(sceneName);
	}
	
	/**
	 * Switch to the specific scene accroding to the sceneName.
	 * @param sceneName
	 */
	public void switchScene(String sceneName) {
		primaryStage.setScene(sceneMap.get(sceneName));
	}
}
