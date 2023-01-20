/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grwp20.WebCoursework.ejb;


import com.grwp20.WebCoursework.entity.SystemUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author greg_
 */

@Stateless
@RolesAllowed("admin")
public class AdminService {
    
    @PersistenceContext
    EntityManager em;
    
    private static ArrayList<SystemUser> users;
    
    ArrayList<String> idd = new ArrayList<String>();
    ArrayList<String> namee = new ArrayList<String>();
    ArrayList<String> surnamee = new ArrayList<String>();
    ArrayList<Double> balancee = new ArrayList<Double>();
   private ArrayList<DisplayUser> disUsers = new ArrayList<DisplayUser>();

    public ArrayList<DisplayUser> getDisUsers() {
        return disUsers;
    }

    public void setDisUsers(ArrayList<DisplayUser> disUsers) {
        this.disUsers = disUsers;
    }
    

    public AdminService() {
    }
   
    
    //querys database for all ids, names and balances
    public void getAllUsers( ){

        List<SystemUser> res =  em.createQuery("SELECT me FROM SystemUser me", SystemUser.class).getResultList();
        for (int i =0;i<res.size();i++){
            idd.add(res.get(i).getId().toString());
           namee.add(res.get(i).getName().toString());
            surnamee.add(res.get(i).getSurname().toString());
            balancee.add(res.get(i).getBalance());
           disUsers.add(new DisplayUser(res.get(i).getId().toString(),res.get(i).getName().toString(),res.get(i).getSurname().toString(),res.get(i).getBalance()));
        }
   
    }


    public ArrayList getIdd() {
        return idd;
    }

    public void setIdd(ArrayList idd) {
        this.idd = idd;
    }

    public ArrayList getNamee() {
        return namee;
    }

    public void setNamee(ArrayList namee) {
        this.namee = namee;
    }

    public ArrayList getSurnamee() {
        return surnamee;
    }

    public void setSurnamee(ArrayList surnamee) {
        this.surnamee = surnamee;
    }

    public ArrayList getBalancee() {
        return balancee;
    }

    public void setBalancee(ArrayList balancee) {
        this.balancee = balancee;
    }

   
    }
    

