package ua.riaval.quiztest.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ua.riaval.quiztest.entity.Answer;

@FacesConverter("AnswerConverter")
public class AnswerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return new Answer(arg2, true);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Answer) {
			return ((Answer) arg2).getText();
		}
		return null;
	}

}
