package org.imslab.controller;

import org.imslab.Model;
import org.imslab.scene.SceneManager;
import org.imslab.sqlite.command.RegisterCmd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterController extends Controller
{

	private Model model;
	
	@FXML Button registerButton;
	@FXML TextField userName;
	@FXML PasswordField password;

	public RegisterController() {
		super();
		model = Model.getInstance();
		System.out.println("Create RegisterController");
	}
	
	public RegisterController(String name) {
		super(name);
	}

	@FXML 
	public void processRegister() {
		String username = userName.getText();
		String pwd = password.getText();
		String errorTitle = "Error!";
		if (username.isEmpty()) {
			model.alert(errorTitle, "Id can't be empty.");
			return;
		}
		if (pwd.isEmpty()) {
			model.alert(errorTitle, "Password can't be empty.");
			return;
		}
		
		try {
			Model.getInstance().register(username, pwd);
			model.info("Success", "Register " + username + " Successfully~");
			SceneManager.getInstance().switchScene("Generator");
			clear();
		} catch (RegisterCmd.AlreadyRegisteredException e) {
			model.alert("Oops", username + " has been registered!");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
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