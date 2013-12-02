package ua.riaval.quiztest.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.security.auth.spi.Util;

import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.User;

@ManagedBean
@ViewScoped
public class AuthAdminBean {

	@EJB
	private UserDAO userDAO;

	private HttpServletRequest request;

	private String email;
	private String password;

	@PostConstruct
	private void postConstruct() {
		FacesContext context = FacesContext.getCurrentInstance();
		request = (HttpServletRequest) context.getExternalContext()
				.getRequest();
	}

	public String signin() throws ServletException {
		try {
			request.login(email, getHash(password));
			return "quiz?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Login failed", "wrong login or password"));
		}
		return null;
	}

	public String logout() throws ServletException {
		request.logout();

		return "signin?faces-redirect=true";
	}

	public User getUser() {
		String email = FacesContext.getCurrentInstance().getExternalContext()
				.getRemoteUser();
		return userDAO.findByEmail(email);
	}

	private String getHash(String item) {
		return Util.createPasswordHash("SHA", Util.BASE64_ENCODING, null, null,
				item);
	}
	
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

}