package ua.riaval.quiztest.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.security.auth.spi.Util;

import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.User;


@ManagedBean
@RequestScoped
public class AuthBean {

	@EJB
	private UserDAO userDAO;
	
	private HttpServletRequest request;

	private String email = "ls2294@gmail.com";
	private String password;

	@PostConstruct
	private void postConstruct() {
		FacesContext context = FacesContext.getCurrentInstance();
		request = (HttpServletRequest) context.getExternalContext()
				.getRequest();
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

	public String signin() throws ServletException {
		request.login(email, password);

//		return "index";
		return "index?faces-redirect=true";
	}

	public String logout() throws ServletException {
		request.logout();

		return "index?faces-redirect=true";
	}
	
	public User getUser() {
		String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
//		Util.createPasswordHash("MD5","BASE64",null, null, plainPassword);
		return userDAO.findByEmail(email);
	}

}
