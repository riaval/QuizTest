package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {

	@PostConstruct
	private void postConstruct() {
		categories = categoryDAO.findAll(OrderBy.DESC);
		for (Category category : categories) {
			int count = quizDAO.countFromCategory(category);
			catCount.put(category, count);
		}
		loadDef();
	}

	public void firstLoadQuizzes(Category category) {
		firstIndex = 0;
		loadQuizzes(category);
	}

	public void next() {
		firstIndex += AMOUNT_OF_QUIZZES;
		loadQuizzes(currentCategory);
	}

	public void previous() {
		firstIndex -= AMOUNT_OF_QUIZZES;
		loadQuizzes(currentCategory);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	private void loadQuizzes(Category category) {
		if (category == null) {
			loadDef();
		} else {
			loadCategory(category);
		}
	}

	private void loadDef() {
		count = quizDAO.count();
		quizzes = quizDAO.loadPart(firstIndex, AMOUNT_OF_QUIZZES, OrderBy.DESC);
		currentCategory = null;
	}

	private void loadCategory(Category category) {
		currentCategory = category;
		quizzes = quizDAO.findInCategory(category, firstIndex,
				AMOUNT_OF_QUIZZES);
		count = quizDAO.countFromCategory(category);
	}
	
	public int countQuizzes(Category category) {
//		System.out.println(category.getName() + " : " + category.getQuizzes().size());
//		return category.getQuizzes().size();
//		return quizDAO.countFromCategory(category);
		return catCount.get(category);
	}

	@EJB
	private CategoryDAO categoryDAO;
	@EJB
	private QuizDAO quizDAO;

	private int count;
	private int firstIndex;
	private List<Category> categories;
	private Category currentCategory;
	private List<Quiz> quizzes;
	private Map<Category, Integer> catCount = new HashMap<>();

	private static final int AMOUNT_OF_QUIZZES = 5;
	private static final long serialVersionUID = 1L;

}
