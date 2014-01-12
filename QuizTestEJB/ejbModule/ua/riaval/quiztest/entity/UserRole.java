package ua.riaval.quiztest.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "UserRole.findByRole", query = "SELECT ur FROM UserRole AS ur WHERE ur.name = :role")
})
@Entity
@Table(name = "UserRole", catalog = "QuizTest")
public class UserRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 45)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
	private Set<User> users = new LinkedHashSet<User>();

	public UserRole() {
	}

	public UserRole(String name, Set<User> users) {
		this.name = name;
		this.users = users;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
