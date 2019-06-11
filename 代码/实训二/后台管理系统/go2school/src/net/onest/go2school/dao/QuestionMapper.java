package net.onest.go2school.dao;

import java.util.List;

import net.onest.go2school.entity.Question;

public interface QuestionMapper {
	public List<Question> findAllQuestions();//查询所有问题
	
	public List<Question> findQuestionByLike(String str);//模糊查询
	
	public int deleteQuestion(int questionId);//删除问题

}
