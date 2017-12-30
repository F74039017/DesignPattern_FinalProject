package org.imslab.question;

public class Question {
	
	protected int id;
	protected String content="";
	protected String lv="";
	protected String sa="", sb="", sc="", sd="";
	
	public Question() {
		id = -1;
	}
	
	/**
	 * Return the string which will be printed to the txt file.
	 * @return   formated content
	 */
	public String format() {
		return "";
	}
	
	/* Accessors */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLv() {
		return lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	public String getSa() {
		return sa;
	}

	public void setSa(String sa) {
		this.sa = sa;
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = sb;
	}

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}
	
	
	/**
	 * Question builder
	 */
	public static class Builder {
		
		Question question = null;
		
		public Builder() {
			question = new Question();
		}
		
		public Builder id(int id) {
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
		
		public Question build() {
			return question;
		}
		
	}
	
}
