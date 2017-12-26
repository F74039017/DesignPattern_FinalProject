package sqlite;


import sqlite.command.Broker;
import sqlite.command.CreateTableCmd;
import sqlite.command.InsertCmd;

public class Main {

	public static void main( String args[] ) {
		Broker broker = new Broker();
		broker.addCommand(new CreateTableCmd("IMSLAB", "FIELD1", "FIELD2")); // Facade
		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "HAPPY", "FIELD2", "DAY"));
		try {
			broker.execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
