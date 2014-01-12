package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import ua.riaval.quiztest.dao.CommentDAO;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.Comment;
import ua.riaval.quiztest.entity.Quiz;
import ua.riaval.quiztest.entity.User;

@ManagedBean
@ViewScoped
public class TestBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int AMOUNT_OF_COMMENTS = 10;

	@EJB
	private QuizDAO quizDAO;
	@EJB
	private UserDAO userDAO;
	@EJB
	private CommentDAO commentDAO;

	private Quiz quiz;
	private String newComment;
	private List<Comment> comments;
	private int firstIndex;
	private int count;

	@PostConstruct
	private void postConstruct() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		int id = Integer.parseInt(params.get("id"));
		quiz = quizDAO.findByID(id);
		findResults();
	}

	public void addComment() {
		String email = FacesContext.getCurrentInstance().getExternalContext()
				.getRemoteUser();
		User user = userDAO.findByEmail(email);
		Date date = Calendar.getInstance().getTime();
		Comment comment = new Comment(quiz, newComment, date, user);
		quiz.getComments().add(comment);
		quizDAO.merge(quiz);
		newComment = null;

		findResults();
		RequestContext.getCurrentInstance().update("form");
	}

	public void next() {
		firstIndex += AMOUNT_OF_COMMENTS;
		findResults();
	}

	public void previous() {
		firstIndex -= AMOUNT_OF_COMMENTS;
		findResults();
	}

	private void findResults() {
		comments = commentDAO.findInQuiz(quiz, firstIndex, AMOUNT_OF_COMMENTS);
		count = commentDAO.countFromQuiz(quiz);
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getNewComment() {
		return newComment;
	}

	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

}
