package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;

import ua.riaval.quiztest.dao.QuestionDAO;
import ua.riaval.quiztest.entity.Question;

@Stateless
public class QuestionDAOImpl extends DAOImpl<Question> implements QuestionDAO {

	public QuestionDAOImpl() {
		super(Question.class);
	}

}
