package ua.riaval.quiztest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.controller.datamodel.QuizDataModel;
import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class QuizzesBean {

	@EJB
	private QuizDAO quizDAO;
	@EJB
	private CategoryDAO categoryDAO;

	private QuizDataModel quizDataModel;
	private List<Category> categories;
	private Quiz selectedQuiz;

	@PostConstruct
	private void postConstract() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		categories = categoryDAO.findAll(OrderBy.ASC);
		quizDataModel = new QuizDataModel();
	}
	
	public String editQuiz() {
		return "quiz?faces-redirect=true&id=" + selectedQuiz.getId();
	}

	public void removeQuiz() {
		quizDAO.delete(selectedQuiz);
	}

	public QuizDataModel getQuizDataModel() {
		return quizDataModel;
	}

	public void setQuizDataModel(QuizDataModel quizDataModel) {
		this.quizDataModel = quizDataModel;
	}

	public Quiz getSelectedQuiz() {
		return selectedQuiz;
	}

	public void setSelectedQuiz(Quiz selectedQuiz) {
		this.selectedQuiz = selectedQuiz;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
