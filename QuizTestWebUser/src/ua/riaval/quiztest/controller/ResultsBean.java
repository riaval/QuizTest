package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.QuizResult;
import ua.riaval.quiztest.entity.User;

@ManagedBean
@ViewScoped
public class ResultsBean implements Serializable {

	@EJB
	private UserDAO userDAO;
	@EJB
	private QuizResultDAO quizResultDAO;

	private List<QuizResult> results;
	private int firstIndex;
	private int count;

	private User user;
	private static final int AMOUNT_OF_QUIZZES = 15;

	@PostConstruct
	private void postConstruct() {
		String email = FacesContext.getCurrentInstance().getExternalContext()
				.getRemoteUser();
		user = userDAO.findByEmail(email);
		count = quizResultDAO.countForUser(user);
		findResults();
	}

	public void next() {
		firstIndex += AMOUNT_OF_QUIZZES;
		findResults();
	}

	public void previous() {
		firstIndex -= AMOUNT_OF_QUIZZES;
		findResults();
	}

	private void findResults() {
		results = quizResultDAO
				.findForUser(user, firstIndex, AMOUNT_OF_QUIZZES);
	}

	public List<QuizResult> getResults() {
		return results;
	}
	

	public void setResults(List<QuizResult> results) {
		this.results = results;
	}
	

	public int getFirstIndex() {
		return firstIndex;
	}
	

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private static final long serialVersionUID = 1L;

}
