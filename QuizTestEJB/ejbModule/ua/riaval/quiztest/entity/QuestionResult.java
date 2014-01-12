package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionResult", catalog = "QuizTest")
public class QuestionResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionType_id", nullable = false)
	private QuestionType questionType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizResult_id", nullable = false)
	private QuizResult quizResult;

	@Column(name = "text", length = 500)
	private String text;

	@Column(name = "image")
	private byte[] image;

	@Column(name = "isLatex", nullable = false)
	private boolean isLatex;

	@Column(name = "cost", nullable = false)
	private int cost;

	@Column(name = "result", nullable = false)
	private double result;

	@OrderBy
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionResult", cascade = CascadeType.ALL)
	private Set<AnswerResult> answerResults = new LinkedHashSet<AnswerResult>();

	public QuestionResult() {
	}

	public QuestionResult(QuestionType questionType, QuizResult quizResult,
			boolean isLatex) {
		this.questionType = questionType;
		this.quizResult = quizResult;
		this.isLatex = isLatex;
	}

	public QuestionResult(QuestionType questionType, QuizResult quizResult,
			String text, byte[] image, boolean isLatex,
			Set<AnswerResult> answerResults) {
		this.questionType = questionType;
		this.quizResult = quizResult;
		this.text = text;
		this.image = image;
		this.isLatex = isLatex;
		this.answerResults = answerResults;
	}

	public QuestionResult(Question question) {
		this.questionType = question.getQuestionType();
		this.text = question.getText();
		this.cost = question.getCost();
		for (Answer answer : question.getAnswers()) {
			AnswerResult ar = new AnswerResult(answer);
			ar.setQuestionResult(this);
			answerResults.add(ar);
		}
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public QuizResult getQuizResult() {
		return this.quizResult;
	}

	public void setQuizResult(QuizResult quizResult) {
		this.quizResult = quizResult;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isIsLatex() {
		return this.isLatex;
	}

	public void setIsLatex(boolean isLatex) {
		this.isLatex = isLatex;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public double getResult() {
		return this.result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public Set<AnswerResult> getAnswerResults() {
		return this.answerResults;
	}

	public void setAnswerResults(Set<AnswerResult> answerResults) {
		this.answerResults = answerResults;
	}

}
