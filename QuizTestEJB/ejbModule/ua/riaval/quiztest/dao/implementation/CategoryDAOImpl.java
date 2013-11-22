package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.entity.Category;

@Stateless
public class CategoryDAOImpl extends DAOImpl<Category> implements CategoryDAO {

	public CategoryDAOImpl() {
		super(Category.class);
	}

	@Override
	public Category findByName(String name) {
		TypedQuery<Category> query = em.createNamedQuery(
				"Category.findByName", Category.class);
		query.setParameter("name", name);

		return findOne(query);
		
	}
	
}
