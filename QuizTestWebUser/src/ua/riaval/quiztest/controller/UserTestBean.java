package ua.riaval.quiztest.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class UserTestBean {

	private Quiz quiz;

//	public UserTestBean() {
//		System.out
//				.println("========================UserTestBean=================");
//		Map<String, String> params = FacesContext.getCurrentInstance()
//				.getExternalContext().getRequestParameterMap();
//
//		int id = Integer.parseInt(params.get("id"));
//		quiz = DAOFactory.getQuizDAO().findByID(id);
//	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
