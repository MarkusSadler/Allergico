package at.allergico.allergico.helper;

import at.allergico.allergico.database.POJO.UserPOJO;

/**
 * Created by AGebhard on 26.05.2015.
 */
public class CurrentUser {
    /******** SINGLETON START ********/

    private static CurrentUser _instance;

    public static CurrentUser getInstance() {
        if (_instance == null) {
            _instance = new CurrentUser();
        }

        return _instance;
    }
    /******** SINGLETON END *********/
    private static UserPOJO _logedInUser;

    public static UserPOJO getLogedInUser() {
        return _logedInUser;
    }

    public static void setLogedInUser(UserPOJO _logedInUser) {
        _logedInUser = _logedInUser;
    }
}
