package at.allergico.allergico.database.POJO;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 22/05/2015.
 */
public class ProductPOJO {
    private int productID;
    private String productName;
    private String description;
    private Bitmap image;
    private String eanCode;
    private List<AllergenPOJO> allergene;

    /**
     * Default constructor for ProductPOJO
     */
    public ProductPOJO() {
        this.productID = -1;
        this.productName = "???";
        this.description = "???";
        this.image = null;
        this.eanCode = "???";
        this.allergene = new ArrayList<>();

    }

    /**
     * Constructor that creates an object with given values
     * @param productID
     * @param productName
     * @param description
     * @param image
     * @param eanCode
     */
    public ProductPOJO(int productID, String productName, String description, Bitmap image, String eanCode, List<AllergenPOJO> allergene) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.eanCode = eanCode;
        if(allergene == null){
            this.allergene = new ArrayList<>();
        }else{
            this.allergene = new ArrayList<>(allergene);
        }


    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public List<AllergenPOJO> getAllergene() {
        return allergene;
    }

    public void setAllergene(List<AllergenPOJO> allergene) {
        this.allergene = allergene;
    }
}
