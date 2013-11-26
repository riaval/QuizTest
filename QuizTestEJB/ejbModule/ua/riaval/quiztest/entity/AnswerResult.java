package ua.riaval.quiztest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AnswerResult", catalog = "QuizTest")
public class AnswerResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private QuestionResult questionResult;
	private String text;
	private Boolean correct;
	private boolean checked;

	public AnswerResult() {
	}

	public AnswerResult(String text, boolean checked) {
		this.text = text;
		this.checked = checked;
	}

	public AnswerResult(QuestionResult questionResult, String text,
			Boolean correct, boolean checked) {
		this.questionResult = questionResult;
		this.text = text;
		this.correct = correct;
		this.checked = checked;
	}

	public AnswerResult(Answer answer) {
		this.text = answer.getText();
		this.correct = answer.getCorrect();
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
	@JoinColumn(name = "questionResult_id", nullable = false)
	public QuestionResult getQuestionResult() {
		return this.questionResult;
	}

	public void setQuestionResult(QuestionResult questionResult) {
		this.questionResult = questionResult;
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

	@Column(name = "checked", nullable = false)
	public boolean getChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerResult other = (AnswerResult) obj;
		if (checked != other.checked)
			return false;
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
