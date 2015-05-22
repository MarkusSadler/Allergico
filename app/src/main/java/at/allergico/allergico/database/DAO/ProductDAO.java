package at.allergico.allergico.database.DAO;

import android.media.Image;

import java.util.List;

import at.allergico.allergico.database.POJO.ProductPOJO;

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

    }
    /******** SINGLETON END *********/

    public List<ProductPOJO> getAllProducts() {
        throw new UnsupportedOperationException();
    }

    public ProductPOJO getProductByID(int productID) {
        throw new UnsupportedOperationException();
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
}
