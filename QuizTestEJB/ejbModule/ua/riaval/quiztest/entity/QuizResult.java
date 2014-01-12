package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
		@NamedQuery(name = "QuizResult.findForUser", query = "SELECT qr FROM QuizResult AS qr WHERE qr.user = :user ORDER BY qr.id DESC"),
		@NamedQuery(name = "QuizResult.countForUser", query = "SELECT COUNT(qr) FROM QuizResult AS qr WHERE qr.user = :user") })
@Entity
@Table(name = "QuizResult", catalog = "QuizTest")
public class QuizResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	private Date date;

	@Column(name = "grade", nullable = false, precision = 22, scale = 0)
	private double grade;

	@Column(name = "showResults", nullable = false)
	private boolean showResults;

	@OrderBy
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quizResult", cascade = CascadeType.ALL)
	private Set<QuestionResult> questionResults = new LinkedHashSet<QuestionResult>(
			0);

	public QuizResult() {
	}

	public QuizResult(User user, String name, Date date) {
		this.user = user;
		this.name = name;
		this.date = date;
	}

	public QuizResult(User user, String name, Date date,
			Set<QuestionResult> questionResults) {
		this.user = user;
		this.name = name;
		this.date = date;
		this.questionResults = questionResults;
	}

	public QuizResult(Quiz quiz) {
		this.name = quiz.getName();
		this.date = Calendar.getInstance().getTime();
		this.showResults = quiz.getShowResults();

		List<QuestionResult> questions = new ArrayList<>(quiz.getQuestions()
				.size());
		for (Question question : quiz.getQuestions()) {
			QuestionResult qr = new QuestionResult(question);
			qr.setQuizResult(this);
			questions.add(qr);
		}
		if (quiz.isRandomOrder()) {
			Collections.shuffle(questions);
		}
		int count = (questions.size() < quiz.getAmount()) ? questions.size()
				: quiz.getAmount();
		for (int i = 0; i < count; i++) {
			questionResults.add(questions.get(i));
		}
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getGrade() {
		return this.grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public boolean isShowResults() {
		return this.showResults;
	}

	public void setShowResults(boolean showResults) {
		this.showResults = showResults;
	}

	public Set<QuestionResult> getQuestionResults() {
		return this.questionResults;
	}

	public void setQuestionResults(Set<QuestionResult> questionResults) {
		this.questionResults = questionResults;
	}

}
