package com.laudextest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "user_id")
    private Long userId;
	
	@Id
    @Column(name = "role_id")
    private Long roleId;
	
	public UserRole() {
		super();
	}
	
	public UserRole( Long userId, Long roleId ) {
		super();
        this.userId = userId;
        this.roleId = roleId;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}