package ua.riaval.quiztest.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Quiz;

@Stateless
public class QuizDAOImpl extends DAOImpl<Quiz> implements QuizDAO {

	public QuizDAOImpl() {
		super(Quiz.class);
	}

	@Override
	public List<Quiz> findLastQuizzes(int amount) {
		TypedQuery<Quiz> query = em.createNamedQuery("Quiz.findAllDesc",
				Quiz.class);
		query.setMaxResults(amount);

		return findMany(query);
	}

}
