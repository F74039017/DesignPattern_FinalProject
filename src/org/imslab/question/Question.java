package org.imslab.question;

import org.imslab.sqlite.command.Broker;

public abstract class Question {
	
	protected int id;
	
	public Question() {
		id = -1;
	}
	
	/**
	 * Return the string which will be printed to the txt file.
	 * @return   formated content
	 */
	abstract String format();
	
	public void add2DB() throws Exception {
		try {
			Broker broker = new Broker();
			
			broker.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() throws Exception {
	}
	
	
	public void delete() throws Exception {
		
	}
	
}
