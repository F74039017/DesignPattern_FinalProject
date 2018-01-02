package org.imslab.controller;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddController extends Controller
{
	private Model model;

	@FXML TextArea problemContent;

	@FXML Label addProbleDesc;

	@FXML TextArea optionAContent;

	@FXML TextArea optionBContent;

	@FXML TextArea optionCContent;

	@FXML TextArea optionDContent;

	public AddController() {
		super();
		model = Model.getInstance();
		System.out.println("Create AddController");
	}

	@FXML 
	public void processAdd() {
		if (problemContent.getText().isEmpty() || optionAContent.getText().isEmpty() || 
				optionBContent.getText().isEmpty() || optionCContent.getText().isEmpty() || optionDContent.getText().isEmpty()) {
			model.alert("Oops", "Field can't be empty.");
			return;
		}
		
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
		model.getCurrentData().getQuestionList().add(question);
		
		clear();
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	@FXML 
	public void processCancel() {
		clear();
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	public void prepareUI() {
		addProbleDesc.setText(model.getCurrentData().getSubjectTableName() + " Lv." + model.getCurrentData().getNextAutoIncrementId());
	}
	
	private void clear() {
		problemContent.setText("");
		optionAContent.setText("");
		optionBContent.setText("");
		optionCContent.setText("");
		optionDContent.setText("");
		addProbleDesc.setText("");
	}
	
}
