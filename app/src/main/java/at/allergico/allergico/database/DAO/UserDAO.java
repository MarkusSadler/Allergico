package at.allergico.allergico.database.DAO;

import java.util.Date;
import java.util.List;

import at.allergico.allergico.database.POJO.UserPOJO;


/**
 * Created by Markus on 22.05.2015.
 * Adapted by Michael on 22.05.2015.
 */
public class UserDAO {
    /******** SINGLETON START ********/
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

    public boolean addUser(UserPOJO newUser) {
        throw new UnsupportedOperationException();
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
