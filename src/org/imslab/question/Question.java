package org.imslab.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.imslab.sqlite.DB;

import javafx.beans.property.SimpleStringProperty;

public class Question {
	
	protected SimpleStringProperty id;
	protected SimpleStringProperty content;
	protected SimpleStringProperty lv;
	protected SimpleStringProperty sa, sb, sc, sd;
	protected String subjectTable = null;
	
	public Question() {
		id = new SimpleStringProperty("-1");
		content = new SimpleStringProperty("");
		lv = new SimpleStringProperty("");
		sa = new SimpleStringProperty("");
		sb = new SimpleStringProperty("");
		sc = new SimpleStringProperty("");
		sd = new SimpleStringProperty("");
	}
	
	// Clone constructor
	public Question(Question question) {
		copy(question);
	}
	
	public void copy(Question question) {
		this.id = question.id;
		this.content = question.content;
		this.lv = question.lv;
		this.sa = question.sa;
		this.sb = question.sb;
		this.sc = question.sc;
		this.sd = question.sd;
		this.subjectTable = question.subjectTable;
	}
	
	/**
	 * TODO:
	 * Return the string which will be printed to the txt file.
	 * @return   formated content
	 */
	public String format() {
		return "";
	}
	
	/* Accessors */

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getContent() {
		return content.get();
	}

	public void setContent(String content) {
		this.content.set(content);
	}

	public String getLv() {
		return lv.get();
	}

	public void setLv(String lv) {
		this.lv.set(lv);
	}

	public String getSa() {
		return sa.get();
	}

	public void setSa(String sa) {
		this.sa.set(sa);
	}

	public String getSb() {
		return sb.get();
	}

	public void setSb(String sb) {
		this.sb.set(sb);
	}

	public String getSc() {
		return sc.get();
	}

	public void setSc(String sc) {
		this.sc.set(sc);
	}

	public String getSd() {
		return sd.get();
	}

	public void setSd(String sd) {
		this.sd.set(sd);
	}

	public String getSubjectTable() {
		return subjectTable;
	}

	public void setSubjectTable(String subjectTable) {
		this.subjectTable = subjectTable;
	}

	
	/* Helper function */
	
	/**
	 * Digest the sql results and create questions list.
	 * @param rs
	 * @param subjectTable   Table name of subject
	 * @return
	 */
	public static List<Question> digestSqlResult(List<HashMap<String, String>> rs, String subjectTable) {
		List<Question> qList = new ArrayList<>();
		for (HashMap<String, String> map : rs) {
			Question question = new Question.Builder().id(map.get(DB.PRIMARY_FIELD))
													 .content(map.get(DB.QUESION_CONTENT))
													 .lv(map.get(DB.QUESTION_LV))
													 .sa(map.get(DB.QUESTION_SELECTA))
													 .sb(map.get(DB.QUESTION_SELECTB))
													 .sc(map.get(DB.QUESTION_SELECTC))
													 .sd(map.get(DB.QUESTION_SELECTD))
													 .subjectTable(subjectTable)
													 .build();
			qList.add(question);
		}
		return qList;
	}
	
	
	/**
	 * Question builder
	 */
	public static class Builder {
		
		Question question = null;
		
		public Builder() {
			question = new Question();
		}
		
		public Builder id(String id) {
			question.setId(id);
			return this;
		}

		public Builder content(String content) {
			question.setContent(content);
			return this;
		}

		public Builder lv(String lv) {
			question.setLv(lv);
			return this;
		}

		public Builder sa(String sa) {
			question.setSa(sa);
			return this;
		}

		public Builder sb(String sb) {
			question.setSb(sb);
			return this;
		}

		public Builder sc(String sc) {
			question.setSc(sc);
			return this;
		}

		public Builder sd(String sd) {
			question.setSd(sd);
			return this;
		}
		
		public Builder subjectTable(String subjectTable) {
			question.setSubjectTable(subjectTable);
			return this;
		}
		
		public Question build() {
			return question;
		}
		
	}

}
