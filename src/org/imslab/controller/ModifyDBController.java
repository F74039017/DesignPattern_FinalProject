package org.imslab.controller;

import org.imslab.ChineseData;
import org.imslab.EnglishData;
import org.imslab.MathData;
import org.imslab.Model;
import org.imslab.SubjectData;
import org.imslab.scene.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TabPane;

public class ModifyDBController extends Controller
{
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
	
	public ModifyDBController(String name) {
		super(name);
	}

	@FXML 
	public void processAdd() {
		//sql insert
		SceneManager.getInstance().switchScene("AddProblem");
	}

	@FXML 
	public void processEdit() {
		//sql update
		SceneManager.getInstance().switchScene("EditProblem");
	}
	
	@FXML 
	public void processDel() {
		//sql delete
		SceneManager.getInstance().switchScene("DeleteProblem");
	}
	
	@FXML 
	public void processLogout() {
		SceneManager.getInstance().switchScene("Login");
	}
	
	@FXML 
	public void processBack() {
		SceneManager.getInstance().switchScene("Generator");
	}

	@FXML public void selectEnglishTab() {
		model.setCurrentData(model.getEnglishData());
	}

	@FXML public void selectChineseTab() {
		model.setCurrentData(model.getChineseData());
	}

	@FXML public void selectMathTab() {
		model.setCurrentData(model.getMathData());
	}

	@FXML public void choiceBoxPressed() {
		System.out.println("Check");
	}

	
}