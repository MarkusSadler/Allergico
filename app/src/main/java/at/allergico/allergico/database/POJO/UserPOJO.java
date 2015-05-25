package at.allergico.allergico.database.POJO;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Markus on 22.05.2015.
 * Adapted by Michael on 22.05.2015
 */
public class UserPOJO {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Date dob;
    private boolean active;
    private List<AllergenPOJO> allergene;

    /**
     * Default constructor - sets all values of the object to default
     */
    public UserPOJO() {
        this.userID = -1;
        this.username = "???";
        this.password = "???";
        this.email = "???";
        this.firstname = "???";
        this.lastname = "???";
        this.dob = GregorianCalendar.getInstance().getTime();
        this.active = false;
        this.allergene = new ArrayList<>();

    }

    /**
     * Creates a new object and assigns given values
     * @param userID
     * @param username
     * @param password
     * @param email
     * @param firstname
     * @param lastname
     * @param dob
     * @param active
     */

    public UserPOJO(int userID, String username, String password, String email, String firstname, String lastname, Date dob, boolean active) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.active = active;
        this.allergene = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString(){
        return String.format("/s /s",this.getFirstname(),this.getLastname());
    }

    public List<AllergenPOJO> getAllergene() {
        return allergene;
    }

    public void setAllergene(List<AllergenPOJO> allergene) {
        this.allergene = allergene;
    }
}
