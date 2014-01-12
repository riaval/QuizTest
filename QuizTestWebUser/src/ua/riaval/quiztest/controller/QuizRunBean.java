package ua.riaval.quiztest.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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

	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	@EJB
	private QuizDAO quizDAO;
	@EJB
	private QuizResultDAO quizResultDAO;

	private QuizResult quiz;
	private QuestionResult currentQuestion;
	private long timeLeft;
	private Timer timer;
	private boolean binary;
	private int storedId;

	public String start(int requestId) {
		if (quiz == null) {
			Quiz defaultQuiz = quizDAO.findByID(requestId);
			binary = defaultQuiz.isBinaryGrade();
			this.quiz = new QuizResult(defaultQuiz);

			timeLeft = defaultQuiz.getTimeLimit() * 60;
			currentQuestion = this.quiz.getQuestionResults().iterator().next();

			timer = new Timer();
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
		long min = timeLeft / 60;
		long sec = timeLeft % 60;
		return String.format("%02d", min) + " : " + String.format("%02d", sec);
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

		newAnswerResult(answer, currentQuestion);
	}

	private void newAnswerResult(String text, QuestionResult question) {
		AnswerResult ar = new AnswerResult(text, true);
		ar.setCorrect(false);
		ar.setQuestionResult(question);
		question.getAnswerResults().add(ar);
	}

	public String finish() {
		timer.cancel();
		String email = FacesContext.getCurrentInstance().getExternalContext()
				.getRemoteUser();
		User user = userDAO.findByEmail(email);
		quiz.setUser(user);
		quizAnalyzing(quiz);

		quizResultDAO.save(quiz);

		int id = storedId = quiz.getId();
		quiz = null;

		return "result?faces-redirect=true&id=" + id;
	}

	public void checkfinish() {
		if (timeLeft < 1) {
			finish();
			ExternalContext ec = FacesContext.getCurrentInstance()
					.getExternalContext();
			try {
				ec.redirect("result.xhtml?id=" + storedId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private QuizResult quizAnalyzing(QuizResult quiz) {
		double mark = 0;
		double marks = 0;
		int cost = 0;
		double grade;
		for (QuestionResult qr : quiz.getQuestionResults()) {
			cost += qr.getCost();
			String questionType = qr.getQuestionType().getName();
			if (questionType.equals("multiple")) {
				int correct = 0;
				int pointed = 0;
				double result = 0;
				for (AnswerResult ar : qr.getAnswerResults()) {
					if (ar.getCorrect()) {
						pointed++;
					}
					if (ar.getCorrect() && ar.getChecked()) {
						correct++;
					} else if (!ar.getCorrect() && ar.getChecked()) {
						correct--;
					}
				}
				result = (double) correct / pointed;
				if (binary && result != 1) {
					continue;
				} else {
					double score = qr.getCost() * result;
					mark = (score < 0) ? 0 : score;
					marks += mark;
				}
			} else if (questionType.equals("single")) {
				for (AnswerResult ar : qr.getAnswerResults()) {
					if (ar.getCorrect() && ar.getChecked()) {
						mark = qr.getCost();
						marks += mark;
					}
				}
			} else if (questionType.equals("open")) {
				AnswerResult checkedAnswer = null;
				for (AnswerResult ar : qr.getAnswerResults()) {
					if (ar.getChecked()) {
						checkedAnswer = ar;
						break;
					}
				}
				if (checkedAnswer == null) {
					newAnswerResult("", qr);
				}
				for (AnswerResult ar : qr.getAnswerResults()) {
					if (ar.getCorrect()
							&& ar.getText().equals(checkedAnswer.getText())) {
						checkedAnswer.setCorrect(true);
						mark = qr.getCost();
						marks += mark;
						break;
					}
				}
			}
			qr.setResult(mark);
			mark = 0;
		}
		grade = (double) marks / cost;
		quiz.setGrade(grade);
		return quiz;
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

}
