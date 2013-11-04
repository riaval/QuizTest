package ua.riaval.quiztest.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import ua.riaval.quiztest.dao.DAOFactory;
import ua.riaval.quiztest.entity.Category;

@ManagedBean
@RequestScoped
public class UserCategoryBean {

	public UserCategoryBean() {
		categories = DAOFactory.getCategoryDAO().findAll();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < categories.size(); i++) {
			sb.append(i);
			sb.append(",");
		}
		activeIndexStr = sb.toString();
	}

	private List<Category> categories;
	private String activeIndexStr;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getActiveIndexStr() {
		return activeIndexStr;
	}

	public void setActiveIndexStr(String activeIndexStr) {
		this.activeIndexStr = activeIndexStr;
	}

}
