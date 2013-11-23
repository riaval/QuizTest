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
import javax.persistence.Table;

@Entity
@Table(name = "Question", catalog = "QuizTest")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private QuestionType questionType;
	private Quiz quiz;
	private String text;
	private byte[] image;
	private boolean latex;
	private int cost = 1;
	private Set<Answer> answers = new LinkedHashSet<Answer>();

	public Question() {
	}

	public Question(QuestionType questionType, Quiz quiz, boolean latex) {
		this.questionType = questionType;
		this.quiz = quiz;
		this.latex = latex;
	}

	public Question(QuestionType questionType, Quiz quiz, String text,
			byte[] image, boolean latex, int cost, Set<Answer> answers) {
		this.questionType = questionType;
		this.quiz = quiz;
		this.text = text;
		this.image = image;
		this.latex = latex;
		this.cost = cost;
		this.answers = answers;
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "type_id", nullable = false)
	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "quiz_id", nullable = false)
	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Column(name = "text", length = 500)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "latex", nullable = false)
	public boolean isLatex() {
		return this.latex;
	}

	public void setLatex(boolean latex) {
		this.latex = latex;
	}

	@Column(name = "cost", nullable = false)
	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

}
