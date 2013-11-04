package ua.riaval.quiztest.controller;

import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import ua.riaval.quiztest.dao.implementation.UserDAOImpl;
import ua.riaval.quiztest.entity.User;
import ua.riaval.quiztest.service.ServiceUtil;

@ManagedBean
@RequestScoped
public class AdminLoginBean {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login(ActionEvent actionEvent)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UserDAOImpl userDAO = new UserDAOImpl();
		User user = userDAO.findByEmail(email);

		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email not found", null));
			return null;
		}

		String passwordHash = ServiceUtil.getSoldHash(password);
		if (user.getPassword().equals(passwordHash)) {
			return "admin-panel?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong password", null));
			return null;
		}

	}

}
