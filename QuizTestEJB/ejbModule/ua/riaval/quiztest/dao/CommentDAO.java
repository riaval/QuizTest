package ua.riaval.quiztest.dao;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Comment;

@Local
public interface CommentDAO extends DAO<Comment> {

}
