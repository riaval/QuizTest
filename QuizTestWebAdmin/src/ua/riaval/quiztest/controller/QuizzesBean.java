package ua.riaval.quiztest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.controller.datamodel.QuizDataModel;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class QuizzesBean {

	@EJB
	private QuizDAO quizDAO;

	private QuizDataModel quizDataModel;
	private Quiz selectedQuiz;

	@PostConstruct
	private void postConstract() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		quizDataModel = new QuizDataModel();
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

}
