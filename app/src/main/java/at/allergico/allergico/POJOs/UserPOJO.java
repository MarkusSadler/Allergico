package at.allergico.allergico.POJOs;

import java.util.Date;

/**
 * Created by Markus on 22.05.2015.
 */
public class UserPOJO {
    private String username;

    public String getUsername() {
        return username;
    }

    private String mailaddress;
        public String getMailaddress() {
            return mailaddress;
        }

        public void setMailaddress(String mailaddress) {
            this.mailaddress = mailaddress;
        }

    public void setUsername(String username) {
        username = username;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        firstname = firstname;
    }

    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public UserPOJO(String username, String password, String firstname, String lastname, Date dob) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
    }


}
