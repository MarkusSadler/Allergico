package at.allergico.allergico.database.Manager;

/**
 * Created by Michael on 23/05/2015.
 */
public class DBManager {

    /******** SINGLETON START ********/
    private static DBManager _instance;

    public static DBManager getInstance() {
        if (_instance == null) {
            _instance = new DBManager();
        }

        return _instance;
    }

    private DBManager() {

    }
    /******** SINGLETON END *********/

}
