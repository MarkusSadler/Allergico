package at.allergico.allergico.POJO;

/**
 * Created by Michael on 22/05/2015.
 */
public class ProductCategoryPOJO {

    private int productCatID;
    private String productCatName;

    /**
     * Default constructor
     */
    public ProductCategoryPOJO() {
        this.productCatID = -1;
        this.productCatName = "???";
    }

    /**
     * Constructor that creates an object with given values
     * @param productCatID
     * @param productCatName
     */
    public ProductCategoryPOJO(int productCatID, String productCatName) {
        this.productCatID = productCatID;
        this.productCatName = productCatName;
    }

    public int getProductCatID() {
        return productCatID;
    }

    public void setProductCatID(int productCatID) {
        this.productCatID = productCatID;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }
}
