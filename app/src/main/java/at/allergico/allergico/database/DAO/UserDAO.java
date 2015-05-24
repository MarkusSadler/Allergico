package at.allergico.allergico.database.DAO;

import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.UserPOJO;


/**
 * Created by Markus on 22.05.2015.
 * Adapted by Michael on 22.05.2015.
 */
public class UserDAO {
    /******** SINGLETON START ********/
    DBManager dbManager = DBManager.getInstance();
    private static UserDAO _instance;

    public static UserDAO getInstance() {
        if (_instance == null) {
            _instance = new UserDAO();
        }

        return _instance;
    }

    private UserDAO() {

    }
    /******** SINGLETON END *********/
    public List<UserPOJO> getAllUsersFromDB(){
       throw new UnsupportedOperationException();
   }

    public UserDAO getUserByID(int userID) {
        throw new UnsupportedOperationException();
    }

    public UserDAO getUserByUsername(String username) {
        throw new UnsupportedOperationException();
    }

    public boolean addUser(UserPOJO newUser)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = formatter.format(newUser.getDob());
        JSONObject addingUser = new JSONObject();
        try
        {
            addingUser.put("UserID", null);
            addingUser.put("Username", newUser.getUsername());
            addingUser.put("Password", newUser.getPassword());
            addingUser.put("Mailaddress" ,newUser.getEmail());
            addingUser.put("Firstname", newUser.getFirstname());
            addingUser.put("Lastname", newUser.getLastname());
            addingUser.put("DoB", dateInString);
            addingUser.put("Active", true);
            System.out.println(addingUser.toString());
            return dbManager.addUser(addingUser.toString());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(UserPOJO updatedUser) {
        throw new UnsupportedOperationException();
    }

    public boolean updateUserUsername(int userID, String newUsername) {
        throw new UnsupportedOperationException();
    }

    public boolean updatePassword(int userID, String newPassword) {
        throw new UnsupportedOperationException();
    }

    public boolean updateEmail(int userID, String newEmail) {
        throw new UnsupportedOperationException();
    }

    public boolean updateFirstname(int userID, String newFirstaeme) {
        throw new UnsupportedOperationException();
    }

    public boolean updateLastname(int userID, String newLastnaem) {
        throw new UnsupportedOperationException();
    }

    public boolean updateDateOfBirth(int userID, Date newDateOfBirth) {
        throw new UnsupportedOperationException();
    }

    public boolean userExists(UserPOJO user) {

        throw new UnsupportedOperationException();
    }

    public boolean userExists(int userID) {

        throw new UnsupportedOperationException();
    }

    public UserPOJO deleteUser(int userID) {

        throw new UnsupportedOperationException();
    }

    public UserPOJO deleteUser(String username) {

        throw new UnsupportedOperationException();
    }

    public UserPOJO deleteUser(UserPOJO deleteUser) {
        throw new UnsupportedOperationException();
    }

    public List<UserPOJO> deleteUsers(List<UserPOJO> deleteUsers) {

        throw new UnsupportedOperationException();
    }
}
