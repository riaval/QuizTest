package ua.riaval.quiztest.dao;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.Question;

@Local
public interface QuestionDAO extends DAO<Question> {

}
