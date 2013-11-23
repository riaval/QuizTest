package ua.riaval.quiztest.dao;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Quiz;

@Local
public interface QuizDAO extends DAO<Quiz> {
	
}
