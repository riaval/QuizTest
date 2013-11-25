package ua.riaval.quiztest.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.QuizResult;
import ua.riaval.quiztest.entity.User;

@ManagedBean
@RequestScoped
public class ResultsBean implements Serializable {

	@EJB
	private UserDAO userDAO;
	@EJB
	private QuizResultDAO quizResultDAO;

//	private ResultsDataModel ResultsDataModel;
//	private QuizResult selectedQuiz;
	private List<QuizResult> results;

	@PostConstruct
	private void postConstruct() {
		String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		User user = userDAO.findByEmail(email);
		results = quizResultDAO.findForUser(user);
	}
	
//	@PostConstruct
//	private void postConstruct() {
//		ResultsDataModel = new ResultsDataModel(quizResultDAO);
//	}
//
//	public ResultsDataModel getResultsDataModel() {
//		return ResultsDataModel;
//	}
//
//	public void setResultsDataModel(ResultsDataModel resultsDataModel) {
//		ResultsDataModel = resultsDataModel;
//	}
//
//	public QuizResult getSelectedQuiz() {
//		return selectedQuiz;
//	}
//
//	public void setSelectedQuiz(QuizResult selectedQuiz) {
//		this.selectedQuiz = selectedQuiz;
//	}

	public List<QuizResult> getResults() {
		return results;
	}

	public void setResults(List<QuizResult> results) {
		this.results = results;
	}
	
	private static final long serialVersionUID = 1L;

}
