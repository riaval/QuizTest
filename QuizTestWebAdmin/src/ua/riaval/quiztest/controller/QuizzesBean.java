package ua.riaval.quiztest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class QuizzesBean {

	@EJB
	private QuizDAO quizDAO;
	
	private List<Quiz> quizzes;

	@PostConstruct
	private void postConstract() {
		quizzes = quizDAO.findAll(OrderBy.DESC);
	}
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

}
