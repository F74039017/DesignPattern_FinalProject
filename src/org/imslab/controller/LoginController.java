package org.imslab.controller;

import org.imslab.Model;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.PasswordField;

public class LoginController extends Controller {
	
	@FXML Label idText;
	@FXML Label pwdText;
	@FXML Button loginButton;
	@FXML TextField userName;
	@FXML PasswordField password;

	public LoginController() {
		super();
		System.out.println("Create LoginController");
	}
	
	public LoginController(String name) {
		super(name);
	}

	/**
	 * Press login button
	 */
	@FXML 
	public void processLogin() {
		try {
			if (Model.getInstance().checkPassword(userName.getText(), password.getText())) {
				SceneManager.getInstance().switchScene("Generator");
				clear();
			} else {
				// TODO: We should show some error message in GUI.
				System.err.println("Wrong pasword!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Press 'Enter' and trigger processLogin(). 
	 */
	@FXML
	public void processKeyEvent(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
	          processLogin();
	     }
	}

	private void clear() {
		userName.setText("");
		password.setText("");
	}
	
}
