package ua.riaval.quiztest.dao;

import java.util.List;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Comment;
import ua.riaval.quiztest.entity.Quiz;

@Local
public interface CommentDAO extends DAO<Comment> {

	public List<Comment> findInQuiz(Quiz quiz, int firstIndex, int amount);

	public int countFromQuiz(Quiz quiz);

}
