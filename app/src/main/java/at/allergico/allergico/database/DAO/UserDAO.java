package at.allergico.allergico.database.DAO;

import android.os.AsyncTask;
import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.UserHasAllergenPOJO;
import at.allergico.allergico.database.POJO.UserPOJO;
import at.allergico.allergico.helper.EncryptionHelper;


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
    /******** SINGLETON END *********/
    private EncryptionHelper encryptionHelper = EncryptionHelper.get_instance();

    private List<UserPOJO> _allUsersList = new ArrayList<>();
    public List<UserPOJO> getAllUsersList() {
        return _allUsersList;
    }

    private UserDAO() {
        getAllUsersFromDB();
        System.out.print("UserDAO Instance");
    }

    private Boolean getAllUsersFromDB(){
     UserHasAllergenDAO userHasAllergenDAO = UserHasAllergenDAO.getInstance();
     this.getAllUsersList().clear();
      String jsonString = dbManager.getObject("User");
        if(jsonString == null){return false;}
        //System.out.print(jsonString);
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            for(JSONObject item : jsonObjects){
                UserPOJO user = new UserPOJO(
                        item.getInt("UserID"),
                        item.getString("Username"),
                        item.getString("Password"),
                        item.getString("Mailaddress"),
                        item.getString("Firstname"),
                        item.getString("Lastname"),
                        null,
                        true
                );
                if(item.getString("[Active]").equals("0")){user.setActive(false);}
                String[] dobStringArr = item.getString("DoB").split("-");
                user.setDob(new Date(Integer.parseInt(dobStringArr[0]),Integer.parseInt(dobStringArr[1]),Integer.parseInt(dobStringArr[2])));

                user.setAllergene(userHasAllergenDAO.getAllergeneOfUser(user.getUserID()));

                this.getAllUsersList().add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean reloadDataFromDB(){
        return getAllUsersFromDB();
    }



    public UserPOJO getUserByID(int userID) {
        throw new UnsupportedOperationException();
    }

    public UserPOJO getUserByUsernameOrEmail(String usernameOrEmail) {
        if(!userExists(usernameOrEmail)){return null;}
        ListIterator<UserPOJO> iter = this.getAllUsersList().listIterator();

        UserPOJO user = null;
        while (iter.hasNext()){
            user = iter.next();
            if(user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail)){
                break;
            }
        }
        return user;
    }
    public UserPOJO getUserByEmail(String mailaddress){
        if(!userExists(mailaddress)){return null;}
        ListIterator<UserPOJO> iter = this.getAllUsersList().listIterator();

        UserPOJO user = null;
        while (iter.hasNext()){
            user = iter.next();
            if(user.getEmail().equals(mailaddress)){
                break;
            }
        }
        return user;
    }

    public boolean addUser(UserPOJO newUser)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = formatter.format(newUser.getDob());
        JSONObject addingUser = new JSONObject();
        try
        {
            addingUser.put("UserID", null);
            addingUser.put("Username", newUser.getUsername());
            addingUser.put("Password", encryptionHelper.encryptStringWithDES(newUser.getPassword()));
            addingUser.put("Mailaddress" ,newUser.getEmail());
            addingUser.put("Firstname", newUser.getFirstname());
            addingUser.put("Lastname", newUser.getLastname());
            addingUser.put("DoB", dateInString);
            addingUser.put("Active", "1");
            System.out.println(addingUser.toString());
            boolean result = dbManager.addUser(addingUser.toString());
            if(result){
                reloadDataFromDB();

            }
            return result;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the User where the UserID equals the  UserID in the given POJO with all the Userdata in the given POJO.
     * The Update happens in the DB and in the AllUsersList
     * @param updatedUser
     * @return true if the Update was successfull or false if not
     */
    public boolean updateUser(UserPOJO updatedUser) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = formatter.format(updatedUser.getDob());
        JSONObject addingUser = new JSONObject();
        String encryptedPassword = encryptionHelper.encryptStringWithDES(updatedUser.getPassword());
        updatedUser.setPassword(encryptedPassword);
        try
        {
            addingUser.put("UserID", updatedUser.getUserID());
            addingUser.put("Username", updatedUser.getUsername());
            addingUser.put("Password", encryptedPassword);
            addingUser.put("Mailaddress" ,updatedUser.getEmail());
            addingUser.put("Firstname", updatedUser.getFirstname());
            addingUser.put("Lastname", updatedUser.getLastname());
            addingUser.put("DoB", dateInString);
            addingUser.put("Active", "1");

            boolean result = dbManager.addUser(addingUser.toString());
            if(result){
//                removeUserFromList(updatedUser.getUserID());
//                this.getAllUsersList().add(updatedUser);
                reloadDataFromDB();
            }
            return result;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    private void removeUserFromList(int userID) {
        int i = 0;
        for(; i < this.getAllUsersList().size(); i++){
            if(this.getAllUsersList().get(i).getUserID() == userID){
                break;
            }
        }
        this.getAllUsersList().remove(i);
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

        return this.getAllUsersList().contains(user);
    }

    public boolean userExists(int userID) {
        throw new UnsupportedOperationException();
    }
    public boolean userExists(String mailaddressOrUsername) {
        ListIterator<UserPOJO> iter = this.getAllUsersList().listIterator();
        Boolean ret = false;
        UserPOJO user;
        while (iter.hasNext()){
            user = iter.next();
            if(user.getEmail().equals(mailaddressOrUsername) || user.getUsername().equals(mailaddressOrUsername)){
                ret = true;
                break;
            }
        }
        return ret;
    }
    public boolean userExists(String mailaddressOrUsername,String password) {
        if(!userExists(mailaddressOrUsername)){return false;}
        String encryptedPW = encryptionHelper.encryptStringWithDES(password);
        ListIterator<UserPOJO> iter = this.getAllUsersList().listIterator();
        Boolean ret = false;
        UserPOJO user;
        while (iter.hasNext()){
            user = iter.next();
            if(user.getEmail().equals(mailaddressOrUsername) || user.getUsername().equals(mailaddressOrUsername) && user.getPassword().equals(encryptedPW)){
                ret = true;
                break;
            }
        }
        return ret;
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
