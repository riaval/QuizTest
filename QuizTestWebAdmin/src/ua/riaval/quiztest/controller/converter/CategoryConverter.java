package ua.riaval.quiztest.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ua.riaval.quiztest.dao.CategoryDAO;

public class CategoryConverter implements Converter {


    private CategoryDAO categoryDAO;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		InitialContext context;
		try {
			context = new InitialContext();
			CategoryDAO categoryDAO = (CategoryDAO) context.lookup("java:global/QuizTest/QuizTestEJB/CategoryDAOImpl!ua.riaval.quiztest.dao.CategoryDAO");
//			categoryDAO.findByName(name)
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
