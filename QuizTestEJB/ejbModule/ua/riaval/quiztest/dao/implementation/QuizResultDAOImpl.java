package ua.riaval.quiztest.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.entity.QuizResult;
import ua.riaval.quiztest.entity.User;

@Stateless
public class QuizResultDAOImpl extends DAOImpl<QuizResult> implements
		QuizResultDAO {

	public QuizResultDAOImpl() {
		super(QuizResult.class);
	}

	@Override
	public List<QuizResult> findForUser(User user, int firstIndex, int amount) {
		TypedQuery<QuizResult> query = em.createNamedQuery(
				"QuizResult.findForUser", QuizResult.class);
		query.setParameter("user", user);

		return findPart(query, firstIndex, amount);
	}

	@Override
	public int countForUser(User user) {
		Query query = em.createNamedQuery("QuizResult.countForUser");
		query.setParameter("user", user);

		return ((Long) query.getSingleResult()).intValue();
	}

}
