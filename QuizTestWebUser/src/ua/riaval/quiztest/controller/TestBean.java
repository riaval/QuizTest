package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class TestBean implements Serializable {

	@PostConstruct
	private void postConstruct() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		int id = Integer.parseInt(params.get("id"));
		quiz = quizDAO.findByID(id);
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@EJB
	QuizDAO quizDAO;

	private Quiz quiz;

	private static final long serialVersionUID = 1L;

}
