package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "Quiz.findInCategory", query = "SELECT q FROM Quiz AS q WHERE :category MEMBER OF q.categories ORDER BY q.id DESC"),
	@NamedQuery(name = "Quiz.countFromCategory", query = "SELECT COUNT(q) FROM Quiz AS q WHERE :category MEMBER OF q.categories")
})
@Entity
@Table(name = "Quiz", catalog = "QuizTest")
public class Quiz implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String comment;
	private int timeLimit = 1;
	private int amount = 1;
	private boolean randomOrder;
	private boolean showResults;
	private boolean binaryGrade;
	private Set<Comment> comments = new LinkedHashSet<Comment>();
	private Set<Category> categories = new LinkedHashSet<Category>();
	private Set<Question> questions = new LinkedHashSet<Question>();

	public Quiz() {
	}

	public Quiz(String name, String comment, int timeLimit, int amount) {
		this.name = name;
		this.comment = comment;
		this.timeLimit = timeLimit;
		this.amount = amount;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "comment", nullable = false, length = 500)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "timeLimit", nullable = false)
	public int getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "amount", nullable = false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "randomOrder", nullable = false)
	public boolean isRandomOrder() {
		return this.randomOrder;
	}

	public void setRandomOrder(boolean randomOrder) {
		this.randomOrder = randomOrder;
	}

	@Column(name = "showResults", nullable = false)
	public boolean getShowResults() {
		return this.showResults;
	}

	public void setShowResults(boolean showResults) {
		this.showResults = showResults;
	}

	@Column(name = "binaryGrade", nullable = false)
	public boolean isBinaryGrade() {
		return this.binaryGrade;
	}

	public void setBinaryGrade(boolean binaryGrade) {
		this.binaryGrade = binaryGrade;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz", cascade = CascadeType.ALL)
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	// @ManyToMany(mappedBy = "quizzes", cascade = CascadeType.ALL)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "Quiz_Category", joinColumns = { @JoinColumn(name = "quiz_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OrderBy
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz", cascade = CascadeType.ALL)
	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
