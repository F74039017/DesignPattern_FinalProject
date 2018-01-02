package org.imslab.controller;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class EditController extends Controller
{
	private Model model;

	@FXML TextArea problemContent;

	@FXML Label editProblemDesc;

	@FXML TextArea optionAContent;

	@FXML TextArea optionBContent;

	@FXML TextArea optionCContent;

	@FXML TextArea optionDContent;
	
	
	public EditController() {
		super();
		model = Model.getInstance();
		System.out.println("Create EditController");
	}
	
	@FXML 
	public void processEdit() {
		
		try {
			Question question = model.getCurrentData().getSelectQuestion();
			question.setContent(problemContent.getText());
			question.setSa(optionAContent.getText());
			question.setSb(optionBContent.getText());
			question.setSc(optionCContent.getText());
			question.setSd(optionDContent.getText());
			int index = model.getCurrentData().getSelectIndex();
			
			// Return item is only a snapshot of the question; therefore replace it directly.
			model.getCurrentData().getQuestionList().set(index, question);
			model.updateQuestion(question);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		clear();
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	@FXML 
	public void processCancel() {
		clear();
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	private void clear() {
		problemContent.setText("");
		optionAContent.setText("");
		optionBContent.setText("");
		optionCContent.setText("");
		optionDContent.setText("");
		editProblemDesc.setText("");
	}
	
	/**
	 * Called by ModifyController.
	 * Fill question information to the content fields.
	 */
	public void prepareUI() {
		Question question = model.getCurrentData().getSelectQuestion();
		problemContent.setText(question.getContent());
		optionAContent.setText(question.getSa());
		optionBContent.setText(question.getSb());
		optionCContent.setText(question.getSc());
		optionDContent.setText(question.getSd());
		editProblemDesc.setText(question.getSubjectTable() + " Lv." + question.getLv());
	}
}
