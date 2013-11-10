package ua.riaval.quiztest.controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ua.riaval.quiztest.dao.DAOFactory;
import ua.riaval.quiztest.entity.Question;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@SessionScoped
public class QuizRunBean {

	private Quiz quiz;
	private Question currentQuestion;
	private long timeLeft;
	Timer timer = new Timer();

	public void push(int requestId) {
		if (quiz != null) {
			return;
		}
		
		quiz = DAOFactory.getQuizDAO().findByID(requestId);
		timeLeft = quiz.getTimeLimit() * 60;
		currentQuestion = quiz.getQuestions().iterator().next();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				timeLeft--;
			}
		}, 5, 1000);
	}
	
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public String getTimeLeft() {
		String min = String.valueOf(timeLeft/60);
		String sec = String.valueOf(timeLeft%60);
		return min + " : " + sec;
	}

}
