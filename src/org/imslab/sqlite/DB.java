package org.imslab.sqlite;

/**
 * Database configuration class
 */
public class DB {
	
	public static final String CONN_NAME = "jdbc:sqlite:question.db";
	public static final String PRIMARY_FIELD = "ID";
	
	// sqlite sequence
	public static final String SQLITE_SEQUENCE_TABLENAME = "sqlite_sequence";
	public static final String SQLITE_SEQUENCE_SEQUENCE = "seq";
	public static final String SQLITE_SEQUENCE_NAME = "name";
	
	// Account
	public static final String ACCOUNT_TABLENAME = "Account";
	public static final String ACCOUNT_USERNAME = "NAME";
	public static final String ACCOUNT_PASSWORD = "PASSWORD";
	
	public static final String ACCOUNT_DEFAULT_USERNAME = "admin";
	public static final String ACCOUNT_DEFAULT_PASSWORD = "admin";
	
	// Question Common fields
	public static final String QUESION_CONTENT = "CONTENT";
	public static final String QUESTION_LV = "LV";
	public static final int QUESION_LV_MIN = 1;
	public static final int QUESION_LV_MAX = 5;
	public static final String QUESTION_SELECTA = "SA";
	public static final String QUESTION_SELECTB = "SB";
	public static final String QUESTION_SELECTC = "SC";
	public static final String QUESTION_SELECTD = "SD";
	
	// Chinese Question
	public static final String CHINESE_TABLENAME = "Chinese";

	// English Question
	public static final String ENGLISH_TABLENAME = "English";
	
	// Math Question
	public static final String MATH_TABLENAME = "Math";
	
}
