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
	private Map<String, Controller> controllerMap;
	private Scene currentScene;
	
	// Singleton pattern
	private static SceneManager sceneManager = null;
		
	private SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		sceneMap = new HashMap<>();
		controllerMap = new HashMap<>();
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
		
		String sceneName = url2SceneName(url);
		controllerMap.put(sceneName, loader.getController());
		
		return new Scene(root);
	}
	
	/**
	 * Create scene corresponding to the .fxml file, and add it to the sceneList.
	 * @param url  URL of .fxml file
	 * @throws IOException 
	 */
	public void addScene(String url) throws IOException {
		Scene scene = createScene(url);
		
		String sceneName = url2SceneName(url);
		sceneMap.put(sceneName, scene);

		// debug
		System.out.println("Create scene "+sceneName);
	}
	
	private String url2SceneName(String url) {
		// prepare scene name
		int lastSlash = url.lastIndexOf('/');
		int lastDot = url.lastIndexOf('.');
		String sceneName = url.substring(lastSlash+1, lastDot);
		return sceneName;
	}
	
	public Map<String,Scene> getAllScene() {
		Map<String, Scene> clone = new HashMap<>(sceneMap);
		return clone;
	}
	
	public Scene getScene(String sceneName) {
		try {
			if (!sceneMap.keySet().contains(sceneName)) {
				throw new Exception("Can't found " + sceneName + " in SceneManager");
			}
			return sceneMap.get(sceneName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// this won't be excuted.
			return null;
		}
	}
	
	public Map<String, Controller> getAllController() {
		Map<String, Controller> clone = new HashMap<>(controllerMap);
		return clone;
	}
	
	public Controller getController(String sceneName) {
		try {
			if (!controllerMap.keySet().contains(sceneName)) {
				throw new Exception("Can't found " + sceneName + " in SceneManager");
			}
			return controllerMap.get(sceneName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// this won't be excuted.
			return null;
		}
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
