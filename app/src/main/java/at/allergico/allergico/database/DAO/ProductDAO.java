package at.allergico.allergico.database.DAO;

import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.ProductPOJO;
import at.allergico.allergico.database.POJO.UserPOJO;

/**
 * Created by Michael on 23/05/2015.
 */
public class ProductDAO {
    /******** SINGLETON START ********/
    private static ProductDAO _instance;

    public static ProductDAO getInstance() {
        if (_instance == null) {
            _instance = new ProductDAO();
        }

        return _instance;
    }

    private ProductDAO() {
        getAllProducts();
    }
    /******** SINGLETON END *********/
    private DBManager dbManager = DBManager.getInstance();

    private List<ProductPOJO> _productList = new ArrayList<>();
    public List<ProductPOJO> getProductList() {
        return _productList;
    }


    public List<ProductPOJO> getAllProducts() {
        this.getProductList().clear();
        String jsonString = dbManager.getObject("Product");
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            for(JSONObject item : jsonObjects){
                ProductPOJO product = new ProductPOJO(
                        item.getInt("ProductID"),
                        item.getString("Productname"),
                        item.getString("Description"),
                        (Image)item.get("Image"),
                        item.getString("EANCode")
                );

                this.getProductList().add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.getProductList();
    }

    public ProductPOJO getProductByID(int productID) {
        for(ProductPOJO p : this._productList) {
            if(p.getProductID() == productID) {
                return p;
            }
        }
        return null;
    }

    public List<ProductPOJO> getProductByName(int productName) {
        throw new UnsupportedOperationException();
    }

    public boolean addProduct(ProductPOJO newProduct) {
        throw new UnsupportedOperationException();
    }

    public boolean updateProduct(ProductPOJO newProduct) {
        throw new UnsupportedOperationException();
    }

    public boolean updateProductName(String productName) {
        throw new UnsupportedOperationException();
    }

    public boolean updateProductDescription(String productDescription) {
        throw new UnsupportedOperationException();
    }

    public boolean updateProductImage(Image productImage) {
        throw new UnsupportedOperationException();
    }

    public boolean updateProductEAN(String productEANCode) {
        throw new UnsupportedOperationException();
    }


    public List<AllergenPOJO> getProductsAllergenes(int productID) {
        List<AllergenPOJO> productsAllergenes = new ArrayList<AllergenPOJO>();
        String jsonString = this.dbManager.getObject("ProductHasAllergen");

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            for(JSONObject item : jsonObjects){
                AllergenPOJO ap = new AllergenPOJO(item.getInt("AllergenID"), item.getString("Description"), item.getString("Abbreviation").charAt(0));


                productsAllergenes.add(ap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productsAllergenes;
    }
}
