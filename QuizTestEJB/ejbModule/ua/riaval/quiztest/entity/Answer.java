package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Answer", catalog = "QuizTest")
public class Answer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String text;
	private Boolean correct = false;
	private Question question;

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

}
