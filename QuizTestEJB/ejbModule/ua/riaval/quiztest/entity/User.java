package ua.riaval.quiztest.entity;

// Generated 04.11.2013 10:49:58 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@NamedQueries({
	@NamedQuery(name = "User.findByEmail", 
				query = "SELECT u FROM User AS u WHERE u.email = :email")
})
@Entity
@Table(name = "User", catalog = "QuizTest", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements java.io.Serializable {

	private Integer id;
	private UserRole userRole;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date registered;
	private Set<QuizResult> quizResults = new LinkedHashSet<QuizResult>();

	public User() {
	}

	public User(UserRole userRole, String email, String password,
			String firstName, String lastName, Date registered) {
		this.userRole = userRole;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registered = registered;
	}

	public User(UserRole userRole, String email, String password,
			String firstName, String lastName, Date registered,
			Set<QuizResult> quizResults) {
		this.userRole = userRole;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registered = registered;
		this.quizResults = quizResults;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userRole_id", nullable = false)
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Column(name = "email", unique = true, nullable = false, length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 120)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "firstName", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registered", nullable = false, length = 19)
	public Date getRegistered() {
		return this.registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<QuizResult> getQuizResults() {
		return this.quizResults;
	}

	public void setQuizResults(Set<QuizResult> quizResults) {
		this.quizResults = quizResults;
	}

}
