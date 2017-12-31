package org.imslab.question;

import java.util.List;

import org.imslab.sqlite.command.createTable.*;
import org.imslab.sqlite.command.delete.*;
import org.imslab.sqlite.command.insert.*;
import org.imslab.sqlite.command.select.*;
import org.imslab.sqlite.command.update.*;

public class EnglishQuestionCmdFactory implements QuestionCmdFactory {
	
	@Override
	public CreateQuestionTableCmd getCreateQuestionTableCmd() {
		return new CreateEnglishQuestionTableCmd();
	}

	@Override
	public InsertQuestionCmd getInsertQuestionCmd(Question question) {
		return new InsertEnglishQuestionCmd(question);
	}

	@Override
	public SelectQuestionCmd getSelectQuestionCmd(List<String> level) {
		return new SelectEnglishQuestionCmd(level);
	}

	@Override
	public UpdateQuestionCmd getUpdateQuestionCmd(Question question) {
		return new UpdateEnglishQuestionCmd(question);
	}

	@Override
	public DeleteQuestionCmd getDeleteQuestionCmd(Question question) {
		return new DeleteEnglishQuestionCmd(question);
	}

}
