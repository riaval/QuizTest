package ua.riaval.quiztest.entity;

// Generated 04.11.2013 10:49:58 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * QuizResult generated by hbm2java
 */
@Entity
@Table(name = "QuizResult", catalog = "QuizTest")
public class QuizResult implements java.io.Serializable {

	private Integer id;
	private User user;
	private String name;
	private Date date;
	private Set<QuestionResult> questionResults = new HashSet<QuestionResult>(0);

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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quizResult")
	public Set<QuestionResult> getQuestionResults() {
		return this.questionResults;
	}

	public void setQuestionResults(Set<QuestionResult> questionResults) {
		this.questionResults = questionResults;
	}

}
