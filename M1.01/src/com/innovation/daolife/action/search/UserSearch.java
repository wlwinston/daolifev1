package com.innovation.daolife.action.search;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.innovation.common.search.ICommonSearch;
import com.innovation.daolife.model.User;

public class UserSearch implements ICommonSearch,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String username;
    private String password;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public DetachedCriteria getDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		if(username!=null && username.trim().length()>0)
			detachedCriteria.add(Restrictions.eq("username", username));
		if(password!=null && password.trim().length()>0)
			detachedCriteria.add(Restrictions.eq("password", password));
		if(email!=null && email.trim().length()>0)
			detachedCriteria.add(Restrictions.eq("email", email));
		return detachedCriteria;
	}
}
