/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grwp20.WebCoursework.jsf;

import com.grwp20.WebCoursework.ejb.UserService;
import com.grwp20.WebCoursework.entity.SystemUser;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "Bean")
@ConversationScoped
public class UserPageBean implements Serializable {

    @EJB
    UserService usrSrv;
    long a = 1;

    private String amount;
    private String thisEmail;
    private String sendEmail;
    private String message = "";

    public UserPageBean() {
    }
    //welcome message including full name of user on user page
    public String getFLName() {
        String a = usrSrv.findFLName();
        thisEmail = usrSrv.getEmail();
        return "Welcome " + a + "!";
    }

    public String getBalance() {
        String bal = usrSrv.getBal();
        return "£" + bal;
    }

    //sends money to other user
    public void reqSendTransaction() {
        if (sendEmail != "" && amount != "" && amount.matches("[0-9]+")) {
            int result = usrSrv.registerSendTransaction(sendEmail, amount);      
            if (result == 0) {
                setMessage("You're have Sent " + amount + " to " + sendEmail);
                usrSrv.updateBalance( sendEmail,amount);
            } else if (result == 1) {
                setMessage("The account you ahave entered not exist!");

            } else if (result == 2) {
                setMessage("You do not have enougth money in your account!");
            } else if (result == 4) {
                setMessage("You must send an amount!");
            } else {
                setMessage("Random Error Sorry!");
            }
        } else {
            setMessage("Please fill both boxes");
        }

        amount = "";
        sendEmail = "";

    }

    //requested money from pther user
    public void reqRecTransaction() {       
        if (sendEmail != "" && amount != "" && amount.matches("[0-9]+") ) {
             setMessage("You're asking to Recieve " + amount + " from " + sendEmail);
            amount = "-" + amount;
            int result = usrSrv.registerRecTransaction(sendEmail, amount);

            if (result == 0) {
               usrSrv.updateBalance( sendEmail,amount);

            } else if (result == 1) {
                setMessage("The account you ahave entered not exist!");

            } else if (result == 3) {
                setMessage("The user you are requesting does not have enough money in their account");
            } else if (result == 4) {
                setMessage("You must send an amount!");
            } else {
                setMessage("Random Error Sorry!");
            }

        } else {
            setMessage("Please fill both boxes");
        }
        amount = "";
        sendEmail = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getThisEmail() {
        return thisEmail;
    }

    public void setThisEmail(String thisEmail) {
        this.thisEmail = thisEmail;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

}
