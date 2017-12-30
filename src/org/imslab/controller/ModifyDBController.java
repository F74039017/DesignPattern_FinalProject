package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

public class ModifyDBController extends Controller
{
	// FXML
	@FXML ChoiceBox chineseLevelOption;
	@FXML ChoiceBox englishLevelOption;
	@FXML ChoiceBox mathLevelOption;
	// 5 level
	ObservableList<String> chineseLvItemList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	ObservableList<String> englishLvItemList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	ObservableList<String> mathLvItemList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	
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

	public ModifyDBController() {
		super();
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

	
}