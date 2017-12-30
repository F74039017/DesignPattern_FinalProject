package org.imslab.question;

import java.util.List;

import org.imslab.sqlite.command.createTable.CreateQuestionTableCmd;
import org.imslab.sqlite.command.delete.DeleteQuestionCmd;
import org.imslab.sqlite.command.insert.InsertQuestionCmd;
import org.imslab.sqlite.command.select.SelectQuestionCmd;
import org.imslab.sqlite.command.update.UpdateQuestionCmd;

/**
 * Abstract factory pattern
 */
public interface QuestionCmdFactory {
	
	abstract CreateQuestionTableCmd getQuestionTableCmd();
	
	abstract InsertQuestionCmd getInsertQuestionCmd(Question question);
	
	abstract SelectQuestionCmd getSelectQuestionCmd(List<String> level);
	
	abstract UpdateQuestionCmd getUpdateQuestionCmd(Question question);
	
	abstract DeleteQuestionCmd getDeleteQuestionCmd(Question question);
	
}
