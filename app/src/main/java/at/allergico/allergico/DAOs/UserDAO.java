package at.allergico.allergico.DAOs;

import java.util.LinkedList;
import java.util.List;

import at.allergico.allergico.POJOs.UserPOJO;

/**
 * Created by Markus on 22.05.2015.
 */
public class UserDAO {
    private static UserDAO _instance;

    public static UserDAO get_instance() {
        if(_instance == null) {
            _instance = new UserDAO();
        }
        return _instance;
    }

   public List<UserPOJO> getAllUsersFromDB(){
       List<UserPOJO> ret = new LinkedList<UserPOJO>();
       //TODO:Add functionality of getting Users from DB via JSON
       return ret;
   }
}
