package org.imslab.controller;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetailController extends Controller
{
	private Model model;
	// Set before switch to this scene
	private String backSceneName;
	@FXML Label problemContent;
	@FXML Label optionAContent;
	@FXML Label optionBContent;
	@FXML Label optionCContent;
	@FXML Label optionDContent;
	
	public DetailController() {
		super();
		model = Model.getInstance();
		System.out.println("Create DetailController");
	}
	
	@FXML 
	public void processBack() {
		clear();
		SceneManager.getInstance().switchScene(backSceneName);
		backSceneName = null;
	}
	
	private void clear() {
		problemContent.setText("");
		optionAContent.setText("");
		optionBContent.setText("");
		optionCContent.setText("");
		optionDContent.setText("");
	}
	
	/**
	 * Set the sceneName before switch to this scene.
	 * Fill question information to the content fields.
	 * @param sceneName   caller's scene name
	 * @param question   
	 */
	public void prepareUI(String sceneName, Question question) {
		// Safety check
		if (!SceneManager.getInstance().getAllScene().keySet().contains(sceneName)) {
			new Exception("Can't find " + sceneName + "in SceneManager").printStackTrace();
			System.exit(1);
		}
		this.backSceneName = sceneName;
		
		problemContent.setText(question.getContent());
		optionAContent.setText(question.getSa());
		optionBContent.setText(question.getSb());
		optionCContent.setText(question.getSc());
		optionDContent.setText(question.getSd());
	}
}
