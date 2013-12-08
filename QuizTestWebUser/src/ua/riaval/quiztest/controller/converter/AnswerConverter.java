package ua.riaval.quiztest.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ua.riaval.quiztest.dao.AnswerDAO;
import ua.riaval.quiztest.entity.Answer;

@FacesConverter("answerConverter")
public class AnswerConverter implements Converter {

	AnswerDAO answerDAO;
	
	public AnswerConverter() {
		InitialContext context;
		try {
			context = new InitialContext();
			answerDAO = (AnswerDAO) context.lookup("java:global/QuizTest/QuizTestEJB/AnswerDAOImpl!ua.riaval.quiztest.dao.AnswerDAO");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		int id = Integer.parseInt(arg2);
		Answer answer = answerDAO.findByID(id);
		return answer;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Answer) {
			String answer = ((Answer) arg2).getId().toString();
			return answer;
		}
		return null;
	}

}
