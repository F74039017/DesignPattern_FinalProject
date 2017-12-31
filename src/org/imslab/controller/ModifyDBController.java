package org.imslab.controller;

import org.imslab.Model;
import org.imslab.question.Question;
import org.imslab.scene.SceneManager;
import org.imslab.sqlite.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	// 5 level
	private ObservableList<String> chineseLvItemList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	private ObservableList<String> englishLvItemList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	private ObservableList<String> mathLvItemList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	
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
		chineseLevelOption.setItems(chineseLvItemList);
		chineseLevelOption.setValue("1");
		englishLevelOption.setItems(englishLvItemList);
		englishLevelOption.setValue("1");
		mathLevelOption.setItems(mathLvItemList);
		mathLevelOption.setValue("1");
		
		chineseNoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		chineseContentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
		chineseProblemTable.setItems(Model.getInstance().getChineseQuestionList());
		englishNoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		englishContentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
		englishProblemTable.setItems(Model.getInstance().getEnglishQuestionList());
		mathNoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		mathContentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
		mathProblemTable.setItems(Model.getInstance().getMathQuestionList());	
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
		model.setCurrentModQuestionTable(DB.ENGLISH_TABLENAME);
	}

	@FXML public void selectChineseTab() {
		model.setCurrentModQuestionTable(DB.CHINESE_TABLENAME);
	}

	@FXML public void selectMathTab() {
		model.setCurrentModQuestionTable(DB.MATH_TABLENAME);
	}

	
}