package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.AnswerResult;
import ua.riaval.quiztest.entity.QuestionResult;
import ua.riaval.quiztest.entity.Quiz;
import ua.riaval.quiztest.entity.QuizResult;
import ua.riaval.quiztest.entity.User;

@ManagedBean
@SessionScoped
public class QuizRunBean implements Serializable {

	public String start(int requestId) {
		if (quiz == null) {
			Quiz defaultQuiz = quizDAO.findByID(requestId);
			this.quiz = new QuizResult(defaultQuiz);

			timeLeft = defaultQuiz.getTimeLimit() * 60;
			currentQuestion = this.quiz.getQuestionResults().iterator().next();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					timeLeft--;
				}
			}, 5, 1000);
		}
		return "testRun?faces-redirect=true";
	}

	public String getTimeLeft() {
		String min = String.valueOf(timeLeft / 60);
		String sec = String.valueOf(timeLeft % 60);
		return min + " : " + sec;
	}

	public String getSingleAnswer() {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			if (answerResult.getChecked()) {
				return answerResult.getText();
			}
		}
		return null;
	}

	public List<String> getMultiAnswers() {
		List<String> answers = new ArrayList<String>();
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			if (answerResult.getChecked()) {
				answers.add(answerResult.getText());
			}
		}
		return answers;
	}

	public String getOpenAnswer() {
		return getSingleAnswer();
	}

	public void setSingleAnswer(String answer) {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			// System.out.println(answerResult.getText().equals(answer) + " : "
			// + answerResult.getText() + " : " + answer);
			if (answerResult.getText().equals(answer)) {
				answerResult.setChecked(true);
				return;
			}
			answerResult.setChecked(false);
		}
	}

	public void setMultiAnswers(List<String> answers) {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			for (String answer : answers) {
				if (answerResult.getText().equals(answer)) {
					answerResult.setChecked(true);
				}
			}
		}
	}

	public void setOpenAnswer(String answer) {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			if (answerResult.getChecked()) {
				answerResult.setText(answer);
				return;
			}
		}

		AnswerResult ar = new AnswerResult(answer, true);
		ar.setCorrect(false);
		ar.setQuestionResult(currentQuestion);
		currentQuestion.getAnswerResults().add(ar);
	}

	public String finish() {
		User user = userDAO.findByID(1);
		quiz.setUser(user);
		quizResultDAO.save(quiz);
		
		int id = quiz.getId();
		quiz = null;
		return "result?faces-redirect=true&id=" + id;
	}

	public QuizResult getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizResult quiz) {
		this.quiz = quiz;
	}

	public QuestionResult getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(QuestionResult currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	@EJB
	private UserDAO userDAO;
	@EJB
	private QuizDAO quizDAO;
	@EJB
	private QuizResultDAO quizResultDAO;

	private QuizResult quiz;
	private QuestionResult currentQuestion;
	private long timeLeft;
	private Timer timer = new Timer();

	private static final long serialVersionUID = 1L;

}
