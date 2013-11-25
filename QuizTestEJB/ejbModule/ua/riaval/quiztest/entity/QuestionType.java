package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "QuestionType.findByType", query = "SELECT qt FROM QuestionType AS qt WHERE qt.name = :typeName") })
@Entity
@Table(name = "QuestionType", catalog = "QuizTest")
public class QuestionType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SINGLE = "single";
	public static final String MULTIPLE = "multiple";
	public static final String OPEN = "open";

	private Integer id;
	private String name;
	private Set<QuestionResult> questionResults = new LinkedHashSet<QuestionResult>();
	private Set<Question> questions = new LinkedHashSet<Question>();

	public QuestionType() {
	}

	public QuestionType(String name) {
		this.name = name;
	}

	public QuestionType(String name, Set<QuestionResult> questionResults,
			Set<Question> questions) {
		this.name = name;
		this.questionResults = questionResults;
		this.questions = questions;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionType")
	public Set<QuestionResult> getQuestionResults() {
		return this.questionResults;
	}

	public void setQuestionResults(Set<QuestionResult> questionResults) {
		this.questionResults = questionResults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionType")
	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
