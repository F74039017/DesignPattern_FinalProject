package org.imslab.sqlite.command;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.Statement;

import org.imslab.sqlite.command.insert.InsertCmd;
import org.imslab.sqlite.command.select.SelectCmd;

public class RegisterCmd extends ModifyCommand {
	
	private static String tableName = "Account";
	private static String nameField = "NAME";
	private static String passwordField = "PASSWORD";
	
	/**
	 * Register account
	 * @param args    [Name] [Password]
	 */
	public RegisterCmd(String... args) {
		super("RegisterCmd", args);
		addDependentCmd(new InsertCmd(tableName, nameField, args[0], passwordField, md5(args[1])));
	}

	@Override
	public boolean exec(Statement statement) throws Exception {
		if (args.size() != 2) {
			throw new Exception("Register command need exactly two args");
		}
		
		ResultSet rs = (new SelectCmd("Account", "ID", "--", "NAME", args.get(0))).exec(statement);
		if (rs.next()) {
			System.err.println(args.get(0) + " has been registered!!");
			throw new AlreadyRegisteredException(args.get(0) + " has been registered!!");
		}
		// use dependent command
		return true;
	}
	
	/**
	 * Digest key string and encode with md5 algorithm.
	 * @param keyStr
	 * @return  encode keyStr
	 */
	public static String md5(String keyStr) {
		String md5=null;
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] barr=md.digest(keyStr.getBytes());
			StringBuffer sb=new StringBuffer();  
			for (int i=0; i < barr.length; i++) {
				sb.append(byte2Hex(barr[i]));
			}
			String hex=sb.toString();
			md5=hex.toUpperCase();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return md5;
	}
	
	public static String byte2Hex(byte b) {
		String[] h={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	    int i=b;
	    if (i < 0) {
	    		i += 256;
	    	}
	    return h[i/16] + h[i%16];
	}
	
	public static class AlreadyRegisteredException extends Exception {
		public AlreadyRegisteredException(String string) {
			super(string);
		}
	}
}
