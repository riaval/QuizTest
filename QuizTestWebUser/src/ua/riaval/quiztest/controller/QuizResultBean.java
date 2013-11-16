package ua.riaval.quiztest.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ua.riaval.quiztest.entity.QuizResult;

@ManagedBean
@ViewScoped
public class QuizResultBean {
	
	private QuizResult quizResult;

	public void inject(QuizResult quizResult) {
		this.quizResult = quizResult;
	}
	
	public QuizResult getQuizResult() {
		return quizResult;
	}

	public void setQuizResult(QuizResult quizResult) {
		this.quizResult = quizResult;
	}
	
}
