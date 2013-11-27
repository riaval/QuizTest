package ua.riaval.quiztest.dao;

import java.util.List;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.QuizResult;
import ua.riaval.quiztest.entity.User;

@Local
public interface QuizResultDAO extends DAO<QuizResult> {

	public List<QuizResult> findForUser(User user, int firstIndex, int amount);
	
	public int countForUser(User user);
	
}
