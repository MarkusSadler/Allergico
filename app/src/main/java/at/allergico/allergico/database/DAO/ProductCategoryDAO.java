package at.allergico.allergico.database.DAO;

import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.ProductCategoryPOJO;
import at.allergico.allergico.database.POJO.ProductPOJO;

/**
 * Created by Michael on 23/05/2015.
 */
public class ProductCategoryDAO {

    /******** SINGLETON START ********/
    private static ProductCategoryDAO _instance;

    public static ProductCategoryDAO getInstance() {
        if (_instance == null) {
            _instance = new ProductCategoryDAO();
        }

        return _instance;
    }

    private ProductCategoryDAO() {
        getAllProductCategories();
    }
    /******** SINGLETON END *********/
    DBManager dbManager = DBManager.getInstance();

    private List<ProductCategoryPOJO> _productCatList = new ArrayList<>();
    public List<ProductCategoryPOJO> getProductCatList() {
        return _productCatList;
    }

    public List<ProductCategoryPOJO> getAllProductCategories() {
        this.getProductCatList().clear();
        String jsonString = dbManager.getObject("Product");
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            for(JSONObject item : jsonObjects){
                ProductCategoryPOJO productCat = new ProductCategoryPOJO(
                        item.getInt("ProductCatID"),
                        item.getString("ProductCatName")

                );

                this.getProductCatList().add(productCat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.getProductCatList();
    }

    public List<ProductCategoryPOJO> getProductCategoryByName(String categoryName) {
        throw new UnsupportedOperationException();
    }

    public ProductCategoryPOJO getProductCategoryByID(int categoryID) {
        throw new UnsupportedOperationException();
    }


}
