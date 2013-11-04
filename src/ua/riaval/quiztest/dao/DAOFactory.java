package ua.riaval.quiztest.dao;

import ua.riaval.quiztest.dao.implementation.CategoryDAOImpl;
import ua.riaval.quiztest.dao.implementation.QuestionTypeDAOImpl;
import ua.riaval.quiztest.dao.implementation.QuizDAOImpl;

public class DAOFactory {

	private DAOFactory() {
	}

	private static QuestionTypeDAOImpl questionTypeDAO;
	private static CategoryDAOImpl categoryDAO;
	private static QuizDAOImpl quizDAO;

	public static QuestionTypeDAOImpl getQuestionTypeDAO() {
		if (questionTypeDAO == null) {
			questionTypeDAO = new QuestionTypeDAOImpl();
		}
		return questionTypeDAO;
	}
	
	public static CategoryDAOImpl getCategoryDAO() {
		if (categoryDAO == null) {
			categoryDAO = new CategoryDAOImpl();
		}
		return categoryDAO;
	}
	
	public static QuizDAOImpl getQuizDAO() {
		if (quizDAO == null) {
			quizDAO = new QuizDAOImpl();
		}
		return quizDAO;
	}

}
