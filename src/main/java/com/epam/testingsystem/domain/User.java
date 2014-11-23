package com.epam.testingsystem.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.epam.testingsystem.domain.enums.RoleNum;


@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	private Integer id;
	
	@Column(name = "user_nickname")
	private String nickName;
	
	@Column(name = "user_passwd")
	private String password;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_role")
	@Enumerated(EnumType.ORDINAL)
	private RoleNum role;
	
	@ManyToMany(targetEntity = Option.class, fetch = FetchType.LAZY)
	@JoinTable(name = "UserOption", 
		joinColumns = { @JoinColumn(name = "idUser") },
		inverseJoinColumns = { @JoinColumn(name = "idOption") })
	private Set<Option> options;

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleNum getRole() {
		return role;
	}

	public void setRole(RoleNum role) {
		this.role = role;
	}
}
