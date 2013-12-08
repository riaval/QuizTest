package ua.riaval.quiztest.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.jboss.security.auth.spi.Util;

import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.dao.UserRoleDAO;
import ua.riaval.quiztest.entity.User;
import ua.riaval.quiztest.entity.UserRole;

@ManagedBean
@ViewScoped
public class AuthBean {

	@EJB
	private UserDAO userDAO;
	@EJB
	private UserRoleDAO userRoleDAO;

	private HttpServletRequest request;
	private String from;
	private String id;

	@Email
	@Size(min = 6, max = 40)
	private String email;
	
	@Size(min = 6, max = 40)
	private String password;
	
	@Size(min = 6, max = 40)
	private String passwordAgain;
	
	@Size(min = 1, max = 40)
	private String firstName;
	
	@Size(min = 1, max = 40)
	private String lastName;

	@PostConstruct
	private void postConstruct() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		from = params.get("from");
		id = params.get("id");

		FacesContext context = FacesContext.getCurrentInstance();
		request = (HttpServletRequest) context.getExternalContext()
				.getRequest();
	}

	public String signin() {
		try {
			request.login(email, getHash(password));
			return returnURI();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "res");
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							bundle.getString("auth_error"), bundle.getString("wrong_login_pass")));
		}
		return null;
	}

	public String signup() throws ServletException {
		User createdUser = userDAO.findByEmail(email);
		if (createdUser != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "res");
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							bundle.getString("error"), bundle.getString("email_exist")));
			password = passwordAgain = null;
			return null;
		}

		if (!password.equals(passwordAgain)) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "res");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							bundle.getString("error"), bundle.getString("pass_dont_match")));
			password = passwordAgain = null;
			return null;
		}

		UserRole userRole = userRoleDAO.findByRole("user");
		Date registered = Calendar.getInstance().getTime();
		User user = new User(userRole, email, getHash(password), firstName,
				lastName, registered);
		userDAO.save(user);

		return signin();
	}

	public String logout() throws ServletException {
		request.logout();

		return "index?faces-redirect=true";
	}

	public String returnURI() {
		if (from != null) {
			StringBuilder toUri = new StringBuilder();
			toUri.append(from).append("?faces-redirect=true");
			if (id != null) {
				toUri.append("id=").append(id);
			}
			return toUri.toString();
		}
		return "index?faces-redirect=true";
	}

	public String getHash(String item) {
		return Util.createPasswordHash("SHA", Util.BASE64_ENCODING, null, null,
				item);
	}

	public User getUser() {
		String email = FacesContext.getCurrentInstance().getExternalContext()
				.getRemoteUser();
		return userDAO.findByEmail(email);
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

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
