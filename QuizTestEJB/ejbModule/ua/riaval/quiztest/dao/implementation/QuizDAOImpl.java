package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Quiz;

@Local
public class QuizDAOImpl extends DAOImpl<Quiz> {

	public QuizDAOImpl() {
		super(Quiz.class);
	}

}
