package at.allergico.allergico.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.ProductDAO;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.ProductPOJO;

public class ProductOverviewActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    private ListView productListView;
    private ProductDAO productDAO =  ProductDAO.getInstance();
    private  List<String> productStringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_overview);
        productListView = (ListView) findViewById(R.id.ProductListView);
        productListView.setOnItemClickListener(this);
        CreateProductListView();
    }

    private void CreateProductListView() {
        List<ProductPOJO> productList = productDAO.getProductList();

        for(ProductPOJO item : productList) {
            productStringList.add(item.getProductName());
        }
        

        productListView.setAdapter(new ArrayAdapter<>(this,R.layout.product_list_item,productStringList));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_overview, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(getApplicationContext(),ShowProductActivity.class);
            System.out.print("clicked: " + productStringList.get(position));
            i.putExtra("productID",productStringList.get(position));
            startActivity(i);
    }
}
