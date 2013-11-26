package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;

import ua.riaval.quiztest.dao.AnswerDAO;
import ua.riaval.quiztest.entity.Answer;

@Stateless
public class AnswerDAOImpl extends DAOImpl<Answer> implements AnswerDAO {

	public AnswerDAOImpl() {
		super(Answer.class);
	}

}
