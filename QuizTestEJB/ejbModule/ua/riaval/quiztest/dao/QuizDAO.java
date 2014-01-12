package ua.riaval.quiztest.dao;

import java.util.List;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Quiz;

@Local
public interface QuizDAO extends DAO<Quiz> {

	public List<Quiz> findInCategory(Category category, int firstIndex,
			int amount);

	public int countFromCategory(Category category);

}
