package org.imslab;

import org.imslab.question.Question;

public class Model {
	
	// Common data
	private String userName = "Unknown";
	
	// Singleton pattern
	private static Model model = null;
		
	private Model() {
		
	}
	
	public static Model getInstance() {
		if (model == null) {
			model = new Model();
		}
		return model;
	}
	
	/* Model function */
	
	public void addQuestion(Question question) throws Exception {
		if (question.getId()!=-1) {
			throw new Exception("Question with id != -1 can't be added to database");
		}
		
		
	}
	
	public void updateQuestion(Question question) {
		
	}
	
	public void deleteQuestion(Question question) {
		
	}
	
	
	/* Accessors */
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
