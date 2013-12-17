package ua.riaval.quiztest.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.CommentDAO;
import ua.riaval.quiztest.entity.Comment;
import ua.riaval.quiztest.entity.Quiz;

@Stateless
public class CommentDAOImpl extends DAOImpl<Comment> implements CommentDAO {

	public CommentDAOImpl() {
		super(Comment.class);
	}

	@Override
	public List<Comment> findInQuiz(Quiz quiz, int firstIndex,
			int amount) {

		TypedQuery<Comment> query = em.createNamedQuery("Comment.findInQuiz",
				Comment.class);
		query.setParameter("quiz", quiz);

		return findPart(query, firstIndex, amount);
	}

	@Override
	public int countFromQuiz(Quiz quiz) {
		Query query = em.createNamedQuery("Comment.countFromQuiz");
		query.setParameter("quiz", quiz);

		return ((Long) query.getSingleResult()).intValue();
	}
}
