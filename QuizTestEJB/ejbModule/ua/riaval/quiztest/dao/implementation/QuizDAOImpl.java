package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Quiz;

@Stateless
public class QuizDAOImpl extends DAOImpl<Quiz> implements QuizDAO {

	public QuizDAOImpl() {
		super(Quiz.class);
	}

}
