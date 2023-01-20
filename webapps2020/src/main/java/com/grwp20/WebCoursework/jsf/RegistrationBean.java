package com.grwp20.WebCoursework.jsf;

import com.grwp20.WebCoursework.ejb.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 *
 */
@Named
@RequestScoped
public class RegistrationBean {

    @EJB
    UserService usrSrv;
    
    String email;
    String userpassword;
    String name;
    String surname;

    public RegistrationBean() {

    }

    //call the injected EJB
    public String register() {
        usrSrv.registerUser(email, userpassword, name, surname);
        return "index";
    }
    
    public UserService getUsrSrv() {
        return usrSrv;
    }

    public void setUsrSrv(UserService usrSrv) {
        this.usrSrv = usrSrv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    
}
