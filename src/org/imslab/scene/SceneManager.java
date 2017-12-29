package org.imslab.scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.imslab.controller.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage primaryStage;
	private Map<String, Scene> sceneMap;
	private Scene currentScene;
	
	// Singleton pattern
	private static SceneManager sceneManager = null;
		
	private SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		sceneMap = new HashMap<>();
		initAllScene();
	}
	
	public static SceneManager setPrimaryStage(Stage primaryStage) {
		if (sceneManager == null) {
			sceneManager = new SceneManager(primaryStage);
		}
		return sceneManager;
	}
	
	public static SceneManager getInstance() {
		if (sceneManager == null) {
			System.err.println("Primary stage not set yet.");
			System.exit(1);
		}
		return sceneManager;
	}
	
	private void initAllScene() {
		for (String string : SceneConfig.FXML_LIST) {
			try {
				addScene(SceneConfig.relativeBasePath+string);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (existScene(SceneConfig.DEFAULT_SCENE)) {
			currentScene = sceneMap.get(SceneConfig.DEFAULT_SCENE);
			primaryStage.setScene(currentScene);
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
		currentScene = sceneMap.get(sceneName);
		primaryStage.setScene(sceneMap.get(sceneName));
	}
}
