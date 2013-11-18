package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;

import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.entity.QuizResult;

@Stateless
public class QuizResultDAOImpl extends DAOImpl<QuizResult> implements QuizResultDAO {

	public QuizResultDAOImpl() {
		super(QuizResult.class);
	}

}
