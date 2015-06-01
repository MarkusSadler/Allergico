package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.AllergenDAO;
import at.allergico.allergico.database.DAO.ProductDAO;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.ProductPOJO;
import at.allergico.allergico.helper.AllergenHelper;

public class ShowProductActivity extends Activity {

    private TextView _productName;
    private TextView _productDescription;
    //private TextView _allergenesOfProduct;
    private AllergenHelper ah = new AllergenHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        this._productName = (TextView) this.findViewById(R.id.productName);
        this._productDescription = (TextView) this.findViewById(R.id.productDescription);
        //this._allergenesOfProduct = (TextView) this.findViewById(R.id.AllergenText);
        ProductDAO pDAO = ProductDAO.getInstance();
        ProductPOJO desiredProduct = null;

        if(savedInstanceState == null) {
            Bundle extras = this.getIntent().getExtras();
            System.out.println("got bundle");
            if(extras != null) {
                System.out.println("got extras");
                if(extras.getInt("productID") > 0) {
                    desiredProduct = pDAO.getProductByID(extras.getInt("productID"));
                } else if(extras.getString("eanCode") != null) {
                    System.out.println("ean code :)");
                    desiredProduct = pDAO.getProductByEANCode(extras.getString("eanCode"));
                } else {
                    setDefaultViewValues();
                }
            } else {
                setDefaultViewValues();
            }
        } else {
            if(savedInstanceState.getSerializable("productID") != null) {
                desiredProduct = pDAO.getProductByID(new Integer((int) savedInstanceState.getSerializable("productID")));
            } else if(savedInstanceState.getSerializable("eanCode") != null) {
                desiredProduct = pDAO.getProductByEANCode((String) savedInstanceState.getSerializable("eanCode"));
            } else {
                setDefaultViewValues();
            }
        }

        if(desiredProduct != null) {
            this._productName.setText(desiredProduct.getProductName());
            this._productDescription.setText(desiredProduct.getDescription());

            List<AllergenPOJO> productsAllergenes = pDAO.getProductsAllergenes(desiredProduct.getProductID());
            if(productsAllergenes.size() == 0) {
                //this._allergenesOfProduct.setText("Allergenes: None");
            } else {

                //this._allergenesOfProduct.setText("Allergenes:");
                LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
                for(AllergenPOJO all : productsAllergenes) {
                    //this._allergenesOfProduct.setText(this._allergenesOfProduct.getText() + " " + all.getAbbreviation());

                    ImageView image = new ImageView(ShowProductActivity.this);
                    image.setBackgroundResource(ah.getAllergenImage(all.getAbbreviation()));
                    linearLayout1.addView(image);
                }
            }
        } else {
            setDefaultViewValues();
        }
    }

    private void setDefaultViewValues() {
        String errorMessage = "Product load failed";
        this._productName.setText(errorMessage);
        this._productDescription.setText(errorMessage);
        //this._allergenesOfProduct.setText(errorMessage);
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

    public void onClickBackToProductOverviewButton(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        this.startActivity(i);
    }
}
