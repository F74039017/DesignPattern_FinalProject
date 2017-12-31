package org.imslab.controller;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddController extends Controller
{
	private Model model;

	@FXML TextArea problemContent;
	@FXML TextField optionAContent;
	@FXML TextField optionBContent;
	@FXML TextField optionCContent;
	@FXML TextField optionDContent;

	public AddController() {
		super();
		model = Model.getInstance();
		System.out.println("Create AddController");
	}
	
	public AddController(String name) {
		super(name);
	}

	@FXML 
	public void processAdd() {
		//prepare
		Question question = new Question.Builder().id(model.getCurrentData().getNextAutoIncrementId())
												 .lv(model.getCurrentData().getCurrentLv())
				 								 .content(problemContent.getText())
				 								 .sa(optionAContent.getText())
				 								 .sb(optionBContent.getText())
				 								 .sc(optionAContent.getText())
				 								 .sd(optionDContent.getText())
				 								 .subjectTable(model.getCurrentData().getSubjectTableName())
				 								 .build();
		// sql insert
		model.addQuestion(question);
		
		clear();
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	@FXML 
	public void processCancel() {
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	private void clear() {
		problemContent.setText("");
		optionAContent.setText("");
		optionBContent.setText("");
		optionCContent.setText("");
		optionDContent.setText("");
	}
	
}
