package at.allergico.allergico.database.DAO;

import android.graphics.BitmapFactory;
import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.ProductHasAllergenPOJO;
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
    private AllergenDAO allergenDAO = AllergenDAO.getInstance();

    private List<ProductPOJO> _productList = new ArrayList<>();
    public List<ProductPOJO> getProductList() {
        return _productList;
    }


    private Boolean getAllProducts() {
        this.getProductList().clear();
        String jsonString = dbManager.getObject("Product");
        if(jsonString == null){return false;}
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
                            null,
                            item.getString("EANCode"),
                            null
                );
//                byte[] imagebyteArray =  item.getString("Image").getBytes();
//                product.setImage(BitmapFactory.decodeByteArray(imagebyteArray, 0, imagebyteArray.length));


                this.getProductList().add(product);
            }
           boolean res =  addAllergensToProductList();
            if(res == false){return false;}
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean addAllergensToProductList() {
        String productHasAllergenJsonString = dbManager.getObject("ProductHasAllergen");
        if(productHasAllergenJsonString == null) return false;
        try{
            JSONArray jsonArray = new JSONArray(productHasAllergenJsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            List<ProductHasAllergenPOJO> productHasAllergenList = new ArrayList<>();
            for(JSONObject item : jsonObjects){
                this.getProductByID(item.getInt("Product_ProductID")).getAllergene().add(allergenDAO.getAllergenByID(item.getInt("AllergenID")));
            }

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean reloadDataFromDB(){
        return getAllProducts();
    }

    public ProductPOJO getProductByID(int productID) {
        for(ProductPOJO p : this._productList) {
            if(p.getProductID() == productID) {
                return p;
            }
        }
        return null;
    }

    public List<ProductPOJO> getProductByName(String productName) {
        List<ProductPOJO> ret = new ArrayList<>();
        for(ProductPOJO p : this._productList) {
            if(p.getProductName() == productName) {
                ret.add(p);
            }
        }
        return ret;
    }

    public boolean addProduct(ProductPOJO newProduct) {
        this.getProductList().add(newProduct);
        JSONObject addingProduct = new JSONObject();
        JSONObject addingAllergene;
        try
        {
            addingProduct.put("ProductID", null);
            addingProduct.put("Productname", newProduct.getProductName());
            addingProduct.put("Description", newProduct.getDescription());
            addingProduct.put("Image" ,null);
            addingProduct.put("EANCode", newProduct.getEanCode());
            boolean result = dbManager.addProduct(addingProduct.toString());
            reloadDataFromDB();

            boolean allergenRes;
            for(AllergenPOJO item : newProduct.getAllergene()){
                addingAllergene = new JSONObject();
                addingAllergene.put("AllergenID", item.getAllergenID());
                addingAllergene.put("ProductID", this.getProductByEANCode(newProduct.getEanCode()).getProductID());
               allergenRes =  dbManager.addProductHasAllergen(addingAllergene.toString());
                if(!allergenRes){
                    result = false;
                    break;
                }
            }

            if(result){
                this.getProductList().add(newProduct);
            }
            return result;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }

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

    /**
     * Method to check if an Product with an EANCode allready exists in the Database
     * @param eanCode
     * @return Returns true if and Product with the given eanCode allready exists in the ProductList
     */
    public boolean productExists(String eanCode){
        for(ProductPOJO item : this.getProductList()){
            if(item.getEanCode().equals(eanCode)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given eanCode is a valid EANCode according to the GS1 Standard
     * Reference: https://de.wikipedia.org/wiki/European_Article_Number
     * @param eanCode
     * @return true when the EANCode is valid and false if not
     */
    public boolean validateEANCode(String eanCode){
        int[] numbers = new int[eanCode.length()];
        int i = 0;
        for(byte b : eanCode.getBytes()){
            numbers[i] = (int) b;
        }

        int checksum = 0;
        for(int j = 0; j < numbers.length; j++){
            if(j%2 == 0){
                checksum += numbers[j]*3;
            }else{
                checksum += numbers[j];
            }
        }

        if(checksum%10 == 0){
            return true;
        }else{
            return false;
        }

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
                if(item.getInt("Product_ProductID") == productID){
                    productsAllergenes.add(ap);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productsAllergenes;
    }

    public ProductPOJO getProductByEANCode(String eanCode) {
        for(ProductPOJO p : this._productList) {
            if(p.getEanCode().compareTo(eanCode) == 0) {
                return p;
            }
        }
        return null;
    }
}
