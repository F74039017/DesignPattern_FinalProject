package org.imslab.controller;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DelController extends Controller
{
	private Model model;
	@FXML Label problemContent;
	@FXML Label optionAContent;
	@FXML Label optionBContent;
	@FXML Label optionCContent;
	@FXML Label optionDContent;
	@FXML Label delProbelmDesc;
	
	public DelController() {
		super();
		model = Model.getInstance();
		System.out.println("Create DelController");
	}
	
	@FXML 
	public void processDel() {
		Question question = model.getCurrentData().getSelectQuestion();
		
		try {
			model.deleteQuestion(question);
			model.getCurrentData().getQuestionList().remove(question);
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
		delProbelmDesc.setText("");
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
		delProbelmDesc.setText(question.getSubjectTable() + " Lv." + question.getLv());
	}
}
