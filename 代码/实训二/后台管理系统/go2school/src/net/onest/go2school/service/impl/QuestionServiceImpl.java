package net.onest.go2school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.onest.go2school.dao.QuestionMapper;
import net.onest.go2school.entity.Question;
import net.onest.go2school.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	public QuestionMapper questionMapper;

	@Override
	public List<Question> findAllQuestions() {
		// TODO Auto-generated method stub
		return questionMapper.findAllQuestions();
	}

	@Override
	public List<Question> findQuestionByLike(String str) {
		// TODO Auto-generated method stub
		return questionMapper.findQuestionByLike(str);
	}

	@Override
	public int deleteQuestion(int questionId) {
		// TODO Auto-generated method stub
		return questionMapper.deleteQuestion(questionId);
	}
	
	
}
