package ua.riaval.quiztest.dao;

import ua.riaval.quiztest.dao.implementation.CategoryDAOImpl;
import ua.riaval.quiztest.dao.implementation.QuestionTypeDAOImpl;

public class DAOFactory {

	private DAOFactory() {
	}

	private static QuestionTypeDAOImpl questionTypeDAO;
	private static CategoryDAOImpl categoryDAO;

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

}
