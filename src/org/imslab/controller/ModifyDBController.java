package org.imslab.controller;

import java.util.ArrayList;
import java.util.List;

import org.imslab.ChineseData;
import org.imslab.EnglishData;
import org.imslab.MathData;
import org.imslab.Model;
import org.imslab.SubjectData;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TabPane;

public class ModifyDBController extends Controller {
	private Model model;
	
	// FXML
	@FXML ChoiceBox chineseLevelOption;
	@FXML ChoiceBox englishLevelOption;
	@FXML ChoiceBox mathLevelOption;

	@FXML Tab chineseTab;
	@FXML TableView chineseProblemTable;
	@FXML Button chineseAddButton;
	@FXML Button chineseSearchButton;
	@FXML Button chineseEditButton;
	@FXML Button chineseDelButton;
	@FXML Tab englishTab;
	@FXML TableView englishProblemTable;
	@FXML Button englishSearchButton;
	@FXML Button englishAddButton;
	@FXML Button englishDelButton;
	@FXML Button englishEditButton;
	@FXML Tab mathTab;
	@FXML TableView mathProblemTable;
	@FXML Button mathSearchButton;
	@FXML Button mathAddButton;
	@FXML Button mathDelButton;
	@FXML Button mathEditButton;
	@FXML Button backButton;
	@FXML Button logoutButton;
	@FXML TableColumn chineseNoCol;
	@FXML TableColumn chineseContentCol;
	@FXML TableColumn englishNoCol;
	@FXML TableColumn englishContentCol;
	@FXML TableColumn mathNoCol;
	@FXML TableColumn mathContentCol;
	@FXML TabPane tabPane;
	

	public ModifyDBController() {
		super();
		model = Model.getInstance();
		System.out.println("Create ModifyDBController");
	}
	
	@FXML
	private void initialize() {
		ChineseData chineseData = new ChineseData(new SubjectData.OperandBuilder().levelOption(chineseLevelOption)
				.noColumn(chineseNoCol).contentColumn(chineseContentCol).problemTable(chineseProblemTable).build());
		model.setChineseData(chineseData);
		EnglishData englishData = new EnglishData(new SubjectData.OperandBuilder().levelOption(englishLevelOption)
				.noColumn(englishNoCol).contentColumn(englishContentCol).problemTable(englishProblemTable).build());
		model.setEnglishData(englishData);
		MathData mathData = new MathData(new SubjectData.OperandBuilder().levelOption(mathLevelOption)
				.noColumn(mathNoCol).contentColumn(mathContentCol).problemTable(mathProblemTable).build());
		model.setMathData(mathData);
		
		model.setCurrentData(chineseData);
	}
	
	private void reset() {
		// prevent initialize problem
		if (model.getCurrentData() == null) {
			return;
		}
		model.getChineseData().resetUI();
		model.getEnglishData().resetUI();
		model.getMathData().resetUI();
		model.setCurrentData(model.getChineseData());
	}
	
	@FXML public void selectEnglishTab() {
		reset();
		model.setCurrentData(model.getEnglishData());
	}

	@FXML public void selectChineseTab() {
		reset();
		model.setCurrentData(model.getChineseData());
	}

	@FXML public void selectMathTab() {
		reset();
		model.setCurrentData(model.getMathData());
	}
	
	@FXML 
	public void processLogout() {
		reset();
		SceneManager.getInstance().switchScene("Login");
	}
	
	@FXML 
	public void processBack() {
		reset();
		SceneManager.getInstance().switchScene("Generator");
	}
	
	@FXML public void processSearch() {
		// clear old data
		model.getCurrentData().getQuestionList().clear();
		
		List<String> lvList = new ArrayList<>();
		lvList.add(model.getCurrentData().getCurrentLv());
		try {
			List<Question> rs = model.selectQuestion(lvList);
			// append all result
			model.getCurrentData().getQuestionList().addAll(rs);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML 
	public void processAdd() {
		AddController controller = (AddController)SceneManager.getInstance().getController("AddProblem");
		controller.prepareUI();
		SceneManager.getInstance().switchScene("AddProblem");
	}

	@FXML 
	public void processEdit() {
		// If not selected any question
		if (model.getCurrentData().getSelectQuestion() == null) {
			return;
		}
		
		EditController controller = (EditController)SceneManager.getInstance().getController("EditProblem");
		controller.prepareUI();
		SceneManager.getInstance().switchScene("EditProblem");
	}
	
	@FXML 
	public void processDel() {
		// If not selected any question
		if (model.getCurrentData().getSelectQuestion() == null) {
			return;
		}
		
		DelController controller = (DelController)SceneManager.getInstance().getController("DeleteProblem");
		controller.prepareUI();
		SceneManager.getInstance().switchScene("DeleteProblem");
	}

	@FXML public void processDetail() {
		DetailController controller = (DetailController)SceneManager.getInstance().getController("Detail");
		Question question = (Question)model.getCurrentData().getSelectQuestion();
		
		// TODO: enable the button after user selecting an question?
		if (question == null) {
			model.alert("Oops", "Select an quesion first");
			return;
		}
		controller.prepareUI("ModifyDB", question);
		SceneManager.getInstance().switchScene("Detail");
	}
	
}