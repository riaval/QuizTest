package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {

	@PostConstruct
	private void postConstruct() {
		quizzes = quizDAO.findLastQuizzes(10);
		categories = catDAO.findAll();
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
	
	@EJB
	private CategoryDAO catDAO;
	@EJB
	private QuizDAO quizDAO;

	private List<Category> categories;
	private Category currentCategory;
	private List<Quiz> quizzes;
	
	private static final long serialVersionUID = 1L;

}
