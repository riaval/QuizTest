package ua.riaval.quiztest.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ua.riaval.quiztest.dao.implementation.QuestionTypeDAOImpl;

@FacesConverter("TypeConverter")
public class TypeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String typeName = null;
		switch (Integer.parseInt(arg2)) {
		case 1:
			typeName = "single";
			break;
		case 2:
			typeName = "multiply";
			break;
		case 3:
			typeName = "open";
			break;
		}
		QuestionTypeDAOImpl typeDAO = new QuestionTypeDAOImpl();
		typeDAO.findByTypeName(typeName);
		
		return typeDAO.findByTypeName(typeName);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2.toString();
	}

}
