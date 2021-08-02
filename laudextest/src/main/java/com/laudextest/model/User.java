package com.laudextest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;
	
	@Column(name = "name")
	private String	name;
	
	@Column(name = "lastnamef")
	private String	lastnamef;
	
	@Column(name = "lastnamem")
	private String	lastnamem;
	
	@Column(name = "birthday")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	birthday;
	
	@Column(name = "gender")
	private String	gender;
	
	@Column(name = "studygrade")
	private String	studygrade;
	
	@Column(name = "email")
	private String	email;
	
	@Column(name = "phone")
	private String	phone;
	
	@Column(name = "password")
	private String	password;
	
	@ManyToMany
    @JoinTable(	name = "user_role",
    			joinColumns = @JoinColumn(name = "user_id"),
    			inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<Role>();
	
	public User() {
		super();
	}
	
	public User(	String	name,	String	lastnamef,	String	lastnamem,	Date birthday,	String gender,
					String	studygrade,	String	email,	String	phone,	String	password ) {
		super();
		
		this.name = name;
		this.lastnamef = lastnamef;
		this.lastnamem = lastnamem;
		this.birthday = birthday;
		this.gender = gender;
		this.studygrade = studygrade;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastnamef() {
		return lastnamef;
	}

	public void setLastnamef(String lastnamef) {
		this.lastnamef = lastnamef;
	}

	public String getLastnamem() {
		return lastnamem;
	}

	public void setLastnamem(String lastnamem) {
		this.lastnamem = lastnamem;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudygrade() {
		return studygrade;
	}

	public void setStudygrade(String studygrade) {
		this.studygrade = studygrade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
