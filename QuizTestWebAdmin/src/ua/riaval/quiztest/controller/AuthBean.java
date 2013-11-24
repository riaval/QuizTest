package ua.riaval.quiztest.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class AuthBean {

	private HttpServletRequest request;
	
	private String email;
	private String password;

	@PostConstruct
	private void postConstruct() {
		FacesContext context = FacesContext.getCurrentInstance();
	    request = (HttpServletRequest) context.getExternalContext().getRequest();
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
	    
	    return "index?faces-redirect=true";
	}
	
	public String logout() throws ServletException {
		request.logout();
		
		return "signin?faces-redirect=true";
	}

}
