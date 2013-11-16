package ua.riaval.quiztest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.entity.Category;

@ManagedBean
@RequestScoped
public class UserCategoryBean {

	@EJB
	private CategoryDAO catDAO;

	private List<Category> categories;
	private String activeIndexStr;

	@PostConstruct
	public void postConstruct() {
		categories = catDAO.findAll();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < categories.size(); i++) {
			sb.append(i);
			sb.append(",");
		}
		activeIndexStr = sb.toString();
	}

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
