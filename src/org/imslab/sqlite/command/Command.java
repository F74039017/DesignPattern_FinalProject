package org.imslab.sqlite.command;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
	
	protected String name = null;
	protected List<String> args = null;
	
	private Command() {
		args = new ArrayList<>();
	}
	
	private Command(String name) {
		this();
		this.name = name;
	}
	
	/**
	 * Construct instance with name and args
	 * @param name
	 * @param args
	 */
	public Command(String name, String... args) {
		this(name);
		for (String string : args) {
			this.args.add(string);
		}
	}
	
	/* Accessors */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
