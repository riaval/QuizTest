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
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "type_id", nullable = false)
	private QuestionType questionType;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "quiz_id", nullable = false)
	private Quiz quiz;
	
	@Column(name = "text", length = 500)
	private String text;
	
	@Column(name = "image")
	private byte[] image;
	
	@Column(name = "latex", nullable = false)
	private boolean latex;
	
	@Column(name = "cost", nullable = false)
	private int cost = 1;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
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

	
	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
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

	
	public boolean isLatex() {
		return this.latex;
	}

	public void setLatex(boolean latex) {
		this.latex = latex;
	}

	
	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	
	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

}
