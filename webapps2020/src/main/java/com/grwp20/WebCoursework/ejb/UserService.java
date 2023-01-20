package com.grwp20.WebCoursework.ejb;

import com.grwp20.WebCoursework.entity.SystemUser;
import com.grwp20.WebCoursework.entity.SystemUserGroup;
import com.grwp20.WebCoursework.entity.TransactionRecords;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * 
 */
@Stateless 
@RolesAllowed("user")
public class UserService {

    @PersistenceContext
    EntityManager em;

    private String username;
    private Object request;
    
    public UserService() {
    }
    //register user
    public void registerUser(String email, String userpassword, String name, String surname) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);

            // apart from the default constructor which is required by JPA
            // you need to also implement a constructor that will make the following code succeed
            sys_user = new SystemUser(email, paswdToStoreInDB, name, surname);
            sys_user_group = new SystemUserGroup(email, "users");

            em.persist(sys_user);
            em.persist(sys_user_group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //transastion to update both senders and reciver balnace in database
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBalance( String sendEmail,String amount){
        
         Query q = em.createQuery("SELECT me.balance FROM SystemUser me WHERE me.email = :name");
         q.setParameter("name", sendEmail);
         Query m = em.createQuery("SELECT me.id FROM SystemUser me WHERE me.email = :name");
         m.setParameter("name", sendEmail);
         
         Query t = em.createQuery("SELECT me.balance FROM SystemUser me WHERE me.email = :name");
         t.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
         Query l = em.createQuery("SELECT me.id FROM SystemUser me WHERE me.email = :name");
         l.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
         
         String tid = m.getResultList().get(0).toString(); //their string id
         Long tidn = Long.parseLong(tid); //long id
         String mid = l.getResultList().get(0).toString(); //my string id
         Long midn = Long.parseLong(mid); //long id
         
         double y=Double.parseDouble(amount);
         double tu=Double.parseDouble(q.getResultList().get(0)+"");        
         double newTheirBal = y+tu; 
         
         double mu=Double.parseDouble(t.getResultList().get(0)+"");        
         double newMyBal = mu - y; 

         SystemUser j = em.find(SystemUser.class, midn);
         j.setBalance(newMyBal);
         em.persist(j);
         em.flush();
         
         SystemUser e = em.find(SystemUser.class, tidn);
         e.setBalance(newTheirBal);
         em.persist(e);
         em.flush();
         
    }
    
    //registers a sending transaction in transaction database
     public int registerSendTransaction( String sendEmail, String amount) {
         int error = 5;
         String tsem = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
         
         Query q = em.createQuery("SELECT me.email FROM SystemUser me WHERE me.email = :name");
         q.setParameter("name", sendEmail);         
         Query t = em.createQuery("SELECT me.balance FROM SystemUser me WHERE me.email = :name");
         t.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
         if (!q.getResultList().isEmpty()) {            
            
         double y=Double.parseDouble(amount);
         double u=Double.parseDouble(t.getResultList().get(0)+"");
        
         y = Math.abs(y);
       
         if ( y>u ){
             error = 2;
         }
        
         else if (y == 0){
             error = 4;
         }
         else{
             TransactionRecords tr;
             tr = new TransactionRecords(tsem,sendEmail,amount,true,true);
            em.persist(tr);
            error = 0;
         }}
         else{
               error = 1;  
                 }
        return error; 
     
         
}
     //registers a recieving transaction in transaction database
     public int registerRecTransaction( String sendEmail, String amount) {
          int error = 5;
         String tsem = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
         
         Query q = em.createQuery("SELECT me.email FROM SystemUser me WHERE me.email = :name");
         q.setParameter("name", sendEmail);         
         Query p = em.createQuery("SELECT me.balance FROM SystemUser me WHERE me.email = :name");
         p.setParameter("name", sendEmail);
         Query t = em.createQuery("SELECT me.balance FROM SystemUser me WHERE me.email = :name");
         t.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
         if (!q.getResultList().isEmpty()) {            
          
         double i=Double.parseDouble(p.getResultList().get(0)+"");  //their balnce
         double y=Double.parseDouble(amount);                           //the amount
         y = Math.abs(y);
       
         if (y > i){
             error = 3;             
     }
         else if (y == 0){
             error = 4;
         }
         else{
             TransactionRecords tr;
             tr = new TransactionRecords(tsem,sendEmail,amount,false,false);
            em.persist(tr);
            error = 0;
         }}
         else{
               error = 1;  
                 }
        return error; 
     
     }
    
    public List<SystemUser> getAll(){
       
            List<SystemUser> comments = em.createQuery("SELECT t FROM SystemUser T" ).getResultList();
                   return comments;
         
}
    //get users full name
     public String find(String name){
         SystemUser entity = em.find(SystemUser.class, name);
         return entity.getName() + entity.getSurname();
     }
     //get users full name
      public  String findFLName(){
   
                 Query q = em.createQuery("SELECT me.name FROM SystemUser me WHERE me.email = :name");
                 Query l = em.createQuery("SELECT  me.surname FROM SystemUser me WHERE me.email = :name");
                q.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
                l.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

                List<String> results1 = (List<String>) q.getResultList();
                List<String>  results2 = (List<String> ) l.getResultList();
                
                return results1.get(0) +" "+ results2.get(0); 
     }
      public  String getEmail(){
      
                return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
     }
      
      public String getBal(){
           Query q = em.createQuery("SELECT me.balance FROM SystemUser me WHERE me.email = :name");
           q.setParameter("name", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
           List<String> results = (List<String>) q.getResultList();          
           return String.valueOf(results.get(0));
          
      }

   
}
