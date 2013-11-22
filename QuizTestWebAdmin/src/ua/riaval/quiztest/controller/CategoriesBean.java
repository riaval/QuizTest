package ua.riaval.quiztest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ua.riaval.quiztest.controller.datamodel.CategoryDataModel;
import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.Category;

@ManagedBean
@ViewScoped
public class CategoriesBean {

	@EJB
	private CategoryDAO categoryDAO;

	private List<Category> categories;
	private String newCategoryName;
	private List<Category> selectedCategories;
	private CategoryDataModel categoriesModel;

	@PostConstruct
	private void postConstract() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		categories = categoryDAO.findAll(OrderBy.DESC);
		categoriesModel = new CategoryDataModel(categories);
	}

	public CategoryDataModel getCategoriesModel() {
		return categoriesModel;
	}

	public void setCategoriesModel(CategoryDataModel categoriesModel) {
		this.categoriesModel = categoriesModel;
	}

	public List<Category> getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(List<Category> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	public String getCategoryName() {
		return newCategoryName;
	}

	public void setCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}

	public void addCategory() {
		Category category = new Category(newCategoryName);
		categoryDAO.save(category);
		categories.add(0, category);
		categoriesModel.setWrappedData(categories);
		newCategoryName = null;
	}
	
	public void removeCategories() {
		for (Category each : selectedCategories) {
			categoryDAO.delete(each);
			categories.remove(each);
		}
		categoriesModel.setWrappedData(categories);
	}
}
