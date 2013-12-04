package ua.riaval.quiztest.controller.datamodel;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Quiz;

public class QuizDataModel extends LazyDataModel<Quiz> {

	private static final long serialVersionUID = 1L;

	private QuizDAO quizDAO;
	private CategoryDAO categoryDAO;
	private List<Quiz> datasource;
	private String selectedCategory;

	public QuizDataModel() {
		try {
			InitialContext context = new InitialContext();
			quizDAO = (QuizDAO) context
					.lookup("java:global/QuizTest/QuizTestEJB/QuizDAOImpl!ua.riaval.quiztest.dao.QuizDAO");
			categoryDAO = (CategoryDAO) context
					.lookup("java:global/QuizTest/QuizTestEJB/CategoryDAOImpl!ua.riaval.quiztest.dao.CategoryDAO");
		} catch (NamingException e) {
			Logger log = Logger.getLogger(this.getClass().getName());
			log.info("[WORNING] " + e.getMessage());
		}
	}

	@Override
	public Quiz getRowData(String rowKey) {
		int id = Integer.parseInt(rowKey);

		return quizDAO.findByID(id);
	}

	@Override
	public Object getRowKey(Quiz car) {
		return car.getId();
	}

	@Override
	public List<Quiz> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (selectedCategory != null && !selectedCategory.isEmpty()) {
			Category category = categoryDAO.findByName(selectedCategory);
			datasource = quizDAO.findInCategory(category, first, pageSize);
			this.setRowCount(10);
		} else {
			datasource = quizDAO.loadPart(first, pageSize, OrderBy.DESC);
			this.setRowCount(quizDAO.count());
		}

		// datasource = quizDAO.loadPart(first, pageSize, OrderBy.DESC);
		// this.setRowCount(10);
		// this.setRowCount(quizDAO.count());
		return datasource;
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
