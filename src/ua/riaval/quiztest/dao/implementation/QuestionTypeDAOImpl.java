package ua.riaval.quiztest.dao.implementation;

import javax.persistence.TypedQuery;

import ua.riaval.quiztest.entity.QuestionType;

public class QuestionTypeDAOImpl extends DAOImpl<QuestionType> {

	public QuestionTypeDAOImpl() {
		super(QuestionType.class);
	}

	public QuestionType findByTypeName(String typeName) {
		TypedQuery<QuestionType> query = getEntityManager().createNamedQuery(
				"QuestionType.findByType", QuestionType.class);
		query.setParameter("typeName", typeName);

		return findOne(query);
		
	}
	
}
