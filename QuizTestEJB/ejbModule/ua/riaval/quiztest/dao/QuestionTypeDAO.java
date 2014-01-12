package ua.riaval.quiztest.dao;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.QuestionType;

@Local
public interface QuestionTypeDAO extends DAO<QuestionType> {

	public QuestionType findByTypeName(String typeName);

}
