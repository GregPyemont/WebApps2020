/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grwp20.WebCoursework.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author greg_
 */
@Entity
public class TransactionRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private String sender;
    
    private String receiver;
    
    private String amount;
   // @NotNull
   // private Date time;
    
    private boolean accepted;
   
    private boolean completed;

    
     public TransactionRecords(){
             }

    public TransactionRecords( String sender, String receiver, String amount, boolean accepted, boolean completed) {
       
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        
        this.accepted = accepted;
        this.completed = completed;
    }

    
    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    /*public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.sender);
        hash = 41 * hash + Objects.hashCode(this.receiver);
       // hash = 41 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
       // hash = 41 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionRecords other = (TransactionRecords) obj;
      //  if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
      //      return false;
     //   }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
      //  if (!Objects.equals(this.time, other.time)) {
     //       return false;
     //   }
        return true;
    }



    @Override
    public String toString() {
        return "com.gp225.securityexercise2.entity.TransactionRecords[ id=" + id + " ]";
    }
    
}
