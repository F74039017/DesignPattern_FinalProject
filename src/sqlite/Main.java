package sqlite;


import sqlite.command.Broker;
import sqlite.command.CreateTableCmd;

public class Main {

	public static void main( String args[] ) {
		Broker broker = new Broker();
//		broker.addCommand(new ConnDBCmd());
		broker.addCommand(new CreateTableCmd("IMSLAB", "FIELD1", "FIELD2")); // Facade
		try {
			broker.execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
