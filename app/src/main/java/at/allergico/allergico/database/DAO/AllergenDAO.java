package at.allergico.allergico.database.DAO;

import java.util.List;

/**
 * Created by Michael on 23/05/2015.
 */
public class AllergenDAO {

    /******** SINGLETON START ********/
    private static AllergenDAO _instance;

    public static AllergenDAO getInstance() {
        if (_instance == null) {
            _instance = new AllergenDAO();
        }

        return _instance;
    }

    private AllergenDAO() {

    }
    /******** SINGLETON END *********/

    public List<AllergenDAO> getAllAllergenes() {
        throw new UnsupportedOperationException();
    }

    public AllergenDAO getAllergenByID(int allergenID) {
        throw new UnsupportedOperationException();
    }

    public AllergenDAO getAllergenByAbbreviation(char abbreviation) {
        throw new UnsupportedOperationException();
    }

    public List<AllergenDAO> getAllergenByDescription(String description) {
        throw new UnsupportedOperationException();
    }
}
