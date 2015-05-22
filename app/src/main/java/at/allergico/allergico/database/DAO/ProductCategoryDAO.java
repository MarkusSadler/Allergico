package at.allergico.allergico.database.DAO;

import java.util.List;

import at.allergico.allergico.POJO.ProductCategoryPOJO;

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

    }
    /******** SINGLETON END *********/

    public List<ProductCategoryPOJO> getAllProductCategories() {
        throw new UnsupportedOperationException();
    }

    public List<ProductCategoryPOJO> getProductCategoryByName(String categoryName) {
        throw new UnsupportedOperationException();
    }

    public ProductCategoryPOJO getProductCategoryByID(int categoryID) {
        throw new UnsupportedOperationException();
    }
}
