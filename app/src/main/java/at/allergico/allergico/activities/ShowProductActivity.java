package at.allergico.allergico.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.AllergenDAO;
import at.allergico.allergico.database.DAO.ProductDAO;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.ProductPOJO;

public class ShowProductActivity extends ActionBarActivity {

    private TextView _productName;
    private TextView _productDescription;
    private TextView _allergenesOfProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        this._productName = (TextView) this.findViewById(R.id.productName);
        this._productDescription = (TextView) this.findViewById(R.id.productDescription);
        this._allergenesOfProduct = (TextView) this.findViewById(R.id.AllergenText);
        int productID = 3;

        ProductDAO pDAO = ProductDAO.getInstance();
        ProductPOJO desiredProduct = pDAO.getProductByID(productID);

        if(desiredProduct != null) {
            this._productName.setText(desiredProduct.getProductName());
            this._productDescription.setText(desiredProduct.getDescription());

            List<AllergenPOJO> productsAllergenes = pDAO.getProductsAllergenes(desiredProduct.getProductID());
            if(productsAllergenes.size() == 0) {
                this._allergenesOfProduct.setText("Allergenes: None");
            } else {
                this._allergenesOfProduct.setText("Allergenes:");
                for(AllergenPOJO all : productsAllergenes) {
                    this._allergenesOfProduct.setText(this._allergenesOfProduct.getText() + " " + all.getAbbreviation());
                }
            }

        } else {
            this._productName.setText("Product Load Failed");
            this._productDescription.setText("Product Load Failed");
            //Toast.makeText(this.getApplicationContext(), "No product fetched", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
