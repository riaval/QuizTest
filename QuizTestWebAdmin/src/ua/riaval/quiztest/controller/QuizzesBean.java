package ua.riaval.quiztest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@RequestScoped
public class QuizzesBean {

	@EJB
	private QuizDAO quizDAO;
	
	private List<Quiz> quizzes;

	@PostConstruct
	private void postConstract() {
		quizzes = quizDAO.findAll();
	}
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

}
