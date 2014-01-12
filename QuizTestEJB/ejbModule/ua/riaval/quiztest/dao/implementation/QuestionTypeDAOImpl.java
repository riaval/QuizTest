package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.QuestionTypeDAO;
import ua.riaval.quiztest.entity.QuestionType;

@Stateless
public class QuestionTypeDAOImpl extends DAOImpl<QuestionType> implements
		QuestionTypeDAO {

	public QuestionTypeDAOImpl() {
		super(QuestionType.class);
	}

	@Override
	public QuestionType findByTypeName(String typeName) {
		TypedQuery<QuestionType> query = em.createNamedQuery(
				"QuestionType.findByType", QuestionType.class);
		query.setParameter("typeName", typeName);

		return findOne(query);

	}

}
