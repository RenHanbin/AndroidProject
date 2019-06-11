package net.onest.go2school.service;

import java.util.List;

import net.onest.go2school.entity.Question;

public interface QuestionService {
	public List<Question> findAllQuestions();
	public List<Question> findQuestionByLike(String str);//模糊查询
	public int deleteQuestion(int questionId);//删除问题
}
