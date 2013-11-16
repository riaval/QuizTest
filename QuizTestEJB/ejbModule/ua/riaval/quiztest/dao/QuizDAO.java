package ua.riaval.quiztest.dao;

import java.util.List;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Quiz;

@Local
public interface QuizDAO extends DAO<Quiz> {

	public List<Quiz> findLastQuizzes(int amount);
	
}
