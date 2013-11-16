package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.entity.Category;

@Stateless
public class CategoryDAOImpl extends DAOImpl<Category> implements CategoryDAO {

	public CategoryDAOImpl() {
		super(Category.class);
	}

}
