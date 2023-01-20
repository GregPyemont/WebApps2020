/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grwp20.WebCoursework.ejb;

import com.grwp20.WebCoursework.entity.SystemUser;
import com.grwp20.WebCoursework.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author greg_
 * 
 * set up database with admin1 inserted
 */
@Startup
@Singleton
public class DbInit {

    @PersistenceContext(unitName = "SecurityExercise2PU")
    EntityManager em;

    @PostConstruct
    public void DbInit() {
        try{
            Query q = em.createQuery("SELECT me FROM SystemUser me WHERE me.email = :name");
            q.setParameter("name", "admin1");
            if(q.getResultList().size() ==0 ){
        System.out.println("At startup: Initialising Datbase with admin");
       
            SystemUser e;
            SystemUserGroup s;
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = "admin1";
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);
           
            e = new SystemUser("admin1", paswdToStoreInDB, "admin1", "admin1");
            s = new SystemUserGroup("admin1", "admin");
            
            em.persist(e);
            em.persist(s);
            em.flush();
            }
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
