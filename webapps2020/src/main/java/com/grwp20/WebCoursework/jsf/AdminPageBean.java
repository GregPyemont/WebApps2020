/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grwp20.WebCoursework.jsf;

import com.grwp20.WebCoursework.ejb.AdminService;
import com.grwp20.WebCoursework.ejb.DisplayUser;
import com.grwp20.WebCoursework.entity.SystemUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 *
 * @author greg_
 */
@Named(value = "AdminBean")
@ConversationScoped
public class AdminPageBean implements Serializable {
    
    @EJB
    AdminService admSrv;
    
    List id;
    List name;
    List surname;
    List balance;
    private ArrayList<DisplayUser> disUsers;
    
            
    public AdminPageBean() {
        
    }
     
    public void getAllUsers(){
        admSrv.getAllUsers();
        id = admSrv.getIdd();
        name = admSrv.getNamee();
        surname = admSrv.getSurnamee();
       balance = admSrv.getBalancee();
        disUsers = admSrv.getDisUsers();
    }

    public List getId() {
        return id;
    }

    public ArrayList<DisplayUser> getDisUsers() {
        return disUsers;
    }

    public void setDisUsers(ArrayList<DisplayUser> disUsers) {
        this.disUsers = disUsers;
    }

    public void setId(List id) {
        this.id = id;
    }

    public List getName() {
        return name;
    }

    public void setName(List name) {
        this.name = name;
    }

    public List getSurname() {
        return surname;
    }

    public void setSurname(List surname) {
        this.surname = surname;
    }

    public List getBalance() {
        return balance;
    }

    public void setBalance(List balance) {
        this.balance = balance;
    }

 
}

   
    
    




