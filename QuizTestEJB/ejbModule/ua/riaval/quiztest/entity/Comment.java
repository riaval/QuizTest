package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.LinkedHashSet;
import java.util.Set;

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
@Table(name = "Comment", catalog = "QuizTest")
public class Comment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Comment comment;
	private Quiz quiz;
	private String content;
	private Set<Comment> comments = new LinkedHashSet<Comment>();

	public Comment() {
	}

	public Comment(Quiz quiz) {
		this.quiz = quiz;
	}

	public Comment(Comment comment, Quiz quiz, String content,
			Set<Comment> comments) {
		this.comment = comment;
		this.quiz = quiz;
		this.content = content;
		this.comments = comments;
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
	@JoinColumn(name = "comment_id")
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_id", nullable = false)
	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Column(name = "content", length = 45)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
