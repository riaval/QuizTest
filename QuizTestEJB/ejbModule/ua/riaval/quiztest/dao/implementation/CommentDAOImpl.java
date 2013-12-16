package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;

import ua.riaval.quiztest.dao.CommentDAO;
import ua.riaval.quiztest.entity.Comment;

@Stateless
public class CommentDAOImpl extends DAOImpl<Comment> implements CommentDAO {

	public CommentDAOImpl() {
		super(Comment.class);
	}

}
