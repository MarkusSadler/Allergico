package at.allergico.allergico.database.DAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.ProductCategoryPOJO;
import at.allergico.allergico.database.POJO.UserHasAllergenPOJO;

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
        this.getAllAllergene();
        System.out.print("AllergeneDAO Instance");
    }
    /******** SINGLETON END *********/
    private DBManager dbManager = DBManager.getInstance();


    private List<AllergenPOJO> _allergeneList = new ArrayList<>();
    public List<AllergenPOJO> getAllergeneList() {
        return _allergeneList;
    }

    private List<AllergenPOJO> getAllAllergene() {
       //msa UserHasAllergenDAO userHasAllergenDAO = UserHasAllergenDAO.getInstance();
        this.getAllergeneList().clear();
        String jsonString = dbManager.getObject("Allergen");
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            for(JSONObject item : jsonObjects){
                AllergenPOJO allergen = new AllergenPOJO(
                        item.getInt("AllergenID"),
                        item.getString("Description"),
                        item.getString("Abbreviation").charAt(0)

                );

               // allergen.setUsers(userHasAllergenDAO.getUserOfAllergen(allergen.getAllergenID()));
                this.getAllergeneList().add(allergen);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.getAllergeneList();

    }

    public AllergenPOJO getAllergenByID(int allergenID) {
        Iterator<AllergenPOJO> iter = this.getAllergeneList().listIterator();
        while (iter.hasNext()){
            AllergenPOJO item = iter.next();
            if(item.getAllergenID() == allergenID){
                return item;
            }
        }
        return null;
    }

    public AllergenDAO getAllergenByAbbreviation(char abbreviation) {
        throw new UnsupportedOperationException();
    }

    public List<AllergenDAO> getAllergenByDescription(String description) {
        throw new UnsupportedOperationException();
    }
}
