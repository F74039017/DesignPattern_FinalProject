package org.imslab.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GenController extends Controller {
	
	protected ObservableList<Question> questionList;
	protected Random random;
	
	private Model model;
	@FXML Button chineseButton;
	@FXML Button englishButton;
	@FXML Button mathButton;
	@FXML CheckBox lv1Ckbox;
	@FXML CheckBox lv2Ckbox;
	@FXML CheckBox lv3Ckbox;
	@FXML CheckBox lv4Ckbox;
	@FXML CheckBox lv5Ckbox;
	@FXML TableColumn noCol;
	@FXML TableColumn contentCol;
	@FXML TableView sheetTable;
	
	public GenController() {
		super();
		questionList = FXCollections.observableArrayList();
		random = new Random();
		model = Model.getInstance();
		System.out.println("Create GenController");
	}
	
	public GenController(String name) {
		super(name);
	}
	
	@FXML
	private void initialize() {
		// bind table and question list
		noCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
		sheetTable.setItems(questionList);	
	}

	// TODO:
	@FXML 
	public void processPreview() {
		SceneManager.getInstance().switchScene("SheetPreview");
	}
	
	@FXML 
	public void processModifyDB() {
		model.setCurrentData(model.getChineseData());
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	@FXML 
	public void processRegister() {
		SceneManager.getInstance().switchScene("Register");
	}
	
	@FXML 
	public void processLogout() {
		SceneManager.getInstance().switchScene("Login");
	}
	
	public void prepareUI() {
		chineseButton.setDisable(true);
		englishButton.setDisable(false);
		mathButton.setDisable(false);
		questionList.clear();
	}

	private void processSubjectButton(ActionEvent event) {
		chineseButton.setDisable(false);
		englishButton.setDisable(false);
		mathButton.setDisable(false);
		((Button)event.getSource()).setDisable(true);
	}

	@FXML public void processChineseButton(ActionEvent event) {
		processSubjectButton(event);
		model.setCurrentData(model.getChineseData());
	}

	@FXML public void processEnglishButton(ActionEvent event) {
		processSubjectButton(event);
		model.setCurrentData(model.getEnglishData());
	}

	@FXML public void processMathButton(ActionEvent event) {
		processSubjectButton(event);
		model.setCurrentData(model.getMathData());
	}
	
	private void selectORcancelAllLv(boolean flag) {
		lv1Ckbox.setSelected(flag);
		lv2Ckbox.setSelected(flag);
		lv3Ckbox.setSelected(flag);
		lv4Ckbox.setSelected(flag);
		lv5Ckbox.setSelected(flag);
	}

	@FXML public void processSelectAllButton() {
		selectORcancelAllLv(true);
	}

	@FXML public void processCancelButton() {
		selectORcancelAllLv(false);
	}
	
	private void addLvIfSelected(CheckBox cb, List<String> list) {
		if (cb.isSelected()) {
			String text = cb.getText();
			int lastSpace = text.lastIndexOf(' ');
			list.add(cb.getText().substring(lastSpace+1, text.length()));
		}
	}
	
	private List<String> getLvList() {
		List<String> list = new ArrayList<>();
		addLvIfSelected(lv1Ckbox, list);
		addLvIfSelected(lv2Ckbox, list);
		addLvIfSelected(lv3Ckbox, list);
		addLvIfSelected(lv4Ckbox, list);
		addLvIfSelected(lv5Ckbox, list);
		return list;
	}

	// TODO: verify
	@FXML public void processDelButton() {
		// Delete directly. Not switch to DeleteProblem scene
		Question question = (Question)sheetTable.getSelectionModel().getSelectedItem();
		questionList.remove(question);
	}

	// XXX: Only add question randomly
	// TODO: add subject columns?
	@FXML public void processAddButton() {
		try {
			// Check Empty lvList
			List<String> lvList = getLvList();
			if (lvList.size()==0) {
				model.alert("Oops", "Must select at least one level!!");
				return;
			}
			
			// Check empty result
			List<Question> rs = model.selectQuestion(lvList);
			if (rs.size()==0) {
				model.alert("Oops", "No matched question.\nPlease add some questions to the database.");
				return;
			}
			
			// add randomly
			int randNum;
			Question toAdd;
			boolean duplicated;
			do {
				randNum = random.nextInt(rs.size());
				toAdd = rs.get(randNum);
				rs.remove(toAdd); // prevent infinity loop
				duplicated = questionList.contains(toAdd);
			} while (duplicated && !rs.isEmpty());
			if (duplicated) {
				model.alert("Oops", "All matched questions are added to the list.");
				return;
			}
			questionList.add(toAdd);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
