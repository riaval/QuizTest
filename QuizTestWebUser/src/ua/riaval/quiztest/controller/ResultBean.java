package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.entity.AnswerResult;
import ua.riaval.quiztest.entity.QuestionResult;
import ua.riaval.quiztest.entity.QuizResult;

@ManagedBean
@RequestScoped
public class ResultBean implements Serializable {

	@PostConstruct
	private void postConstruct() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		int id = Integer.parseInt(params.get("id"));

		quizResult = quizResultDAO.findByID(id);
	}

	public QuizResult getQuizResult() {
		return quizResult;
	}

	public void setQuizResult(QuizResult quizResul) {
		this.quizResult = quizResul;
	}
	
	public boolean isCorrect(QuestionResult questionResult) {
		for (AnswerResult each : questionResult.getAnswerResults()) {
			if (each.getChecked() && each.getCorrect()) {
				return true;
			}
		}
		return false;
	}
	
	public int getCost() {
		int cost = 0;
		for (QuestionResult each : quizResult.getQuestionResults()) {
			cost += each.getCost();
		}
		
		return cost;
	}
	
	public double getMarks() {
		double marks = 0;
		for (QuestionResult each : quizResult.getQuestionResults()) {
			marks += each.getResult();
		}
		
		return marks;
	}

	@EJB
	private QuizResultDAO quizResultDAO;

	private QuizResult quizResult;

	private static final long serialVersionUID = 1L;

}
