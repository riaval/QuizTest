package ua.riaval.quiztest.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Quiz;

@Stateless
public class QuizDAOImpl extends DAOImpl<Quiz> implements QuizDAO {

	public QuizDAOImpl() {
		super(Quiz.class);
	}

	@Override
	public List<Quiz> findInCategory(Category category, int firstIndex,
			int amount) {
		if (category == null) {
			return new ArrayList<>();
		}

		TypedQuery<Quiz> query = em.createNamedQuery("Quiz.findInCategory",
				Quiz.class);
		query.setParameter("category", category);

		return findPart(query, firstIndex, amount);
	}

	@Override
	public int countFromCategory(Category category) {
		Query query = em.createNamedQuery("Quiz.countFromCategory");
		query.setParameter("category", category);

		return ((Long) query.getSingleResult()).intValue();
	}

}
