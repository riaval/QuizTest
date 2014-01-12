package ua.riaval.quiztest.controller.datamodel;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.model.ListDataModel;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.SelectableDataModel;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.entity.Category;

public class CategoryDataModel extends ListDataModel<Category> implements
		SelectableDataModel<Category> {

	public CategoryDataModel(List<Category> categories) {
		super(categories);
	}

	@Override
	public Category getRowData(String name) {
		try {
			InitialContext context = new InitialContext();
			CategoryDAO categoryDAO = (CategoryDAO) context
					.lookup("java:global/QuizTest/QuizTestEJB/CategoryDAOImpl!ua.riaval.quiztest.dao.CategoryDAO");

			return categoryDAO.findByName(name);
		} catch (NamingException e) {
			Logger log = Logger.getLogger(this.getClass().getName());
			log.info("[WORNING] " + e.getMessage());

			return null;
		}
	}

	@Override
	public Object getRowKey(Category category) {
		return category.getName();
	}

}
