package at.allergico.allergico.database.POJO;

/**
 * Created by Markus on 03.06.2015.
 */
public class ProductHasAllergenPOJO {
    private int ProductID;
    private int AllergenID;

    public ProductHasAllergenPOJO(){
        setProductID(-1);
        setAllergenID(-1);
    }

    public ProductHasAllergenPOJO(int productID, int allergenID){
        setProductID(productID);
        setAllergenID(allergenID);
    }

    public int getProductID() {
        return ProductID;
    }

    public int getAllergenID() {
        return AllergenID;
    }

    public void setAllergenID(int allergenID) {
        AllergenID = allergenID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }
}
