package org.imslab.controller;

import org.imslab.Model;
import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterController extends Controller
{

	@FXML Button registerButton;
	@FXML TextField userName;
	@FXML PasswordField password;

	public RegisterController() {
		super();
		System.out.println("Create RegisterController");
	}
	
	public RegisterController(String name) {
		super(name);
	}

	@FXML 
	public void processRegister() {
		String username = userName.getText();
		String pwd = password.getText();
		try {
			Model.getInstance().register(username, pwd);
			clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SceneManager.getInstance().switchScene("Generator");
	}
	
	@FXML 
	public void processBack() {
		SceneManager.getInstance().switchScene("Generator");
	}
	
	private void clear() {
		userName.setText("");
		password.setText("");
	}
}