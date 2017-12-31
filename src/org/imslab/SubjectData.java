package org.imslab;

import java.util.HashMap;
import java.util.List;

import org.imslab.question.Question;
import org.imslab.question.QuestionCmdFactory;
import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.Broker;
import org.imslab.sqlite.command.select.SelectSequenceCmd;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Offer a builder to create UI Operand object and pass it to the SubjectData constructor.
 * The constructor will initialize all UI operands and try to create the subject's table. 
 */
public class SubjectData {
	
	// RW attributes
	protected ObservableList<Question> questionList;
	protected ObservableList<String> lvItemList;
	protected String currentLv;
	
	// read only
	protected QuestionCmdFactory factory;
	protected String subjectTableName;

	protected Operand operand;
	protected Broker broker;
	
	private SubjectData() {
		questionList = FXCollections.observableArrayList();
		lvItemList = FXCollections.observableArrayList();
		broker = new Broker();
		int minLv = DB.QUESION_LV_MIN;
		int maxLv = DB.QUESION_LV_MAX;
		currentLv = String.valueOf(minLv);
		for(int i=minLv; i<=maxLv; i++) {
			lvItemList.add(String.valueOf(i));
		}
	}
	
	/**
	 * Subclass has to determine the factory and tableName in constructor.
	 * And then take operand parameter from the user. 
	 * 
	 * This constructor will create the question table if not exists.
	 * @param factory
	 * @param tableName
	 * @param operand
	 */
	public SubjectData(QuestionCmdFactory factory, String tableName, Operand operand) {
		this();
		this.factory = factory;
		this.subjectTableName = tableName;
		this.operand = operand;
		
		if (operand.hasNullFields()) {
			new Exception("Operand parameter has null fields");
			System.exit(1);
		}
		
		// Try to create the question table
		Broker broker =  new Broker();
		try {
			broker.addCommand(factory.getCreateQuestionTableCmd()).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// UI
		initRelativeUI();
	}
	
	/**
	 * Called after the constructor get the operands.
	 */
	private void initRelativeUI() {
		operand.getLevelOption().setItems(lvItemList);
		operand.getLevelOption().setValue(currentLv);
		
		operand.getNoColumn().setCellValueFactory(new PropertyValueFactory<>("id"));
		operand.getContentColumn().setCellValueFactory(new PropertyValueFactory<>("content"));
		operand.getProblemTable().setItems(questionList);	
		
		// set currentLv when select item in choice box
		operand.getLevelOption().getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				currentLv = lvItemList.get(newValue.intValue());
			}
		});
	}
	
	/**
	 * Reset UI relative data
	 */
	public void resetUI() {
		questionList.clear();
		currentLv = String.valueOf(DB.QUESION_LV_MIN);
		operand.getLevelOption().setValue(lvItemList.get(0));
	}
	
	/**
	 * Get the next insert id of the question subject table.
	 * @param cmd
	 * @return  next insert id of the table.
	 */
	public String getNextAutoIncrementId() {
		try {
			List<HashMap<String, String>> rs;
			rs = broker.execQuery(new SelectSequenceCmd(subjectTableName));
			if (rs.size()==0) {
				return "1"; // first element
			}
			return String.valueOf(Integer.parseInt(rs.get(0).get(DB.SQLITE_SEQUENCE_SEQUENCE))+1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// this won't be returned.
			return null;
		}
	}
	
	/**
	 * Return the selected item in the table view.
	 * @return
	 */
	public Question getSelectQuestion() {
		return (Question)operand.getProblemTable().getSelectionModel().getSelectedItem();
	}
	
	/**
	 * Return the selected item index in the table view.
	 * @return
	 */
	public int getSelectIndex() {
		return operand.getProblemTable().getSelectionModel().getSelectedIndex();
	}
	
	/* Accessors */

	public QuestionCmdFactory getFactory() {
		return factory;
	}

	public String getSubjectTableName() {
		return subjectTableName;
	}

	public ObservableList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ObservableList<Question> questionList) {
		this.questionList = questionList;
	}

	public ObservableList<String> getLvItemList() {
		return lvItemList;
	}

	public void setLvItemList(ObservableList<String> lvItemList) {
		this.lvItemList = lvItemList;
	}

	public String getCurrentLv() {
		return currentLv;
	}

	public void setCurrentLv(String currentLv) {
		this.currentLv = currentLv;
	}
	
	public static class Operand {
		
		private ChoiceBox levelOption = null;
		private TableColumn noColumn = null;
		private TableColumn contentColumn = null;
		private TableView problemTable = null;
		
		public ChoiceBox getLevelOption() {
			return levelOption;
		}
		
		public void setLevelOption(ChoiceBox levelOption) {
			this.levelOption = levelOption;
		}
		
		public TableColumn getNoColumn() {
			return noColumn;
		}
		
		public void setNoColumn(TableColumn noColumn) {
			this.noColumn = noColumn;
		}
		
		public TableColumn getContentColumn() {
			return contentColumn;
		}
		
		public void setContentColumn(TableColumn contentColumn) {
			this.contentColumn = contentColumn;
		}

		public TableView getProblemTable() {
			return problemTable;
		}

		public void setProblemTable(TableView problemTable) {
			this.problemTable = problemTable;
		}
		
		public boolean hasNullFields() {
			if (levelOption==null || noColumn==null || contentColumn==null || problemTable==null) {
				return true;
			}
			return false;
		}

	}
	
	public static class OperandBuilder {
		
		private Operand operand;
		
		public OperandBuilder() {
			operand = new Operand();
		}
		
		public OperandBuilder levelOption(ChoiceBox levelOption) {
			operand.setLevelOption(levelOption);
			return this;
		}
		
		public OperandBuilder noColumn(TableColumn noColumn) {
			operand.setNoColumn(noColumn);
			return this;
		}
		
		public OperandBuilder contentColumn(TableColumn contentColumn) {
			operand.setContentColumn(contentColumn);
			return this;
		} 
		
		public OperandBuilder problemTable(TableView problemTable) {
			operand.setProblemTable(problemTable);
			return this;
		}
		
		/**
		 * Make sure [levelOption], [noColumn], [contentColumn], [problemTable] have been set.
		 * @return
		 */
		public Operand build() {
			try {
				if (operand.hasNullFields()) {
					throw new Exception("Operand has null fields");
				}
				return operand;
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
				return null;
			}
		}
		
	}	
	
}
