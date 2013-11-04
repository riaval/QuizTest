package ua.riaval.quiztest.entity;

// Generated 04.11.2013 10:49:58 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Answer", catalog = "QuizTest")
public class Answer implements java.io.Serializable {

	private Integer id;
	private String text;
	private Boolean correct = false;
	private Question question;
//	private Set<AnswerAnswer> answerAnswersForChildAnswerId = new HashSet<AnswerAnswer>();
//	private Set<AnswerAnswer> answerAnswersForParentAnswerId = new HashSet<AnswerAnswer>();

	public Answer() {
	}

	public Answer(String text, Boolean correct) {
		this.text = text;
		this.correct = correct;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id", nullable = false)
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Column(name = "text", nullable = false, length = 200)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "correct")
	public Boolean getCorrect() {
		return this.correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "answerByChildAnswerId")
//	public Set<AnswerAnswer> getAnswerAnswersForChildAnswerId() {
//		return this.answerAnswersForChildAnswerId;
//	}
//
//	public void setAnswerAnswersForChildAnswerId(
//			Set<AnswerAnswer> answerAnswersForChildAnswerId) {
//		this.answerAnswersForChildAnswerId = answerAnswersForChildAnswerId;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "answerByParentAnswerId")
//	public Set<AnswerAnswer> getAnswerAnswersForParentAnswerId() {
//		return this.answerAnswersForParentAnswerId;
//	}
//
//	public void setAnswerAnswersForParentAnswerId(
//			Set<AnswerAnswer> answerAnswersForParentAnswerId) {
//		this.answerAnswersForParentAnswerId = answerAnswersForParentAnswerId;
//	}

}
