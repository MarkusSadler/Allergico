package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import at.allergico.allergico.LoginActivity;
import at.allergico.allergico.R;

public class MainActivity extends Activity implements View.OnClickListener{
    private ImageButton _eanReader;
    private ImageButton _administrate;
    private ImageButton _productOverview;
    private ImageButton _addProductButton;
    private ImageButton _showAllergenButton;
    private ImageButton _logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this._eanReader = (ImageButton) findViewById(R.id.EANButton);
        this._administrate = (ImageButton) findViewById(R.id.AdministrateUserButton);
        this._productOverview = (ImageButton) findViewById(R.id.ProductOverviewButton);
        this._addProductButton = (ImageButton) findViewById(R.id.AddProductButton);
        this._showAllergenButton = (ImageButton) findViewById(R.id.ShowAllergeneButton);
        this._logoutButton = (ImageButton) findViewById(R.id.LogoutButton);

        this._eanReader.setOnClickListener(this);
        this._administrate.setOnClickListener(this);
        this._productOverview.setOnClickListener(this);
        this._addProductButton.setOnClickListener(this);
        this._showAllergenButton.setOnClickListener(this);
        this._logoutButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View view) {
        Intent i = null;
        if(view.getId() == R.id.AdministrateUserButton){
            i = new Intent(this, AdministrateProductActivity.class);
        }else if(view.getId() == R.id.EANButton){
            i = new Intent("com.google.zxing.client.android.SCAN");
            i.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(i, 0);
        } else if(view.getId() == R.id.ProductOverviewButton) {
            i = new Intent(this, ProductOverviewActivity.class);
        } else if(view.getId() == R.id.AddProductButton) {
            i = new Intent(this, AddProductActivity.class);
        }else if(view.getId() == R.id.ShowAllergeneButton){
            i = new Intent(this, AdministrateAllergenActivity.class);
            i.putExtra("sourceActivity", "mainActivity");
        }else if(view.getId() == R.id.LogoutButton){
            i = new Intent(this, LoginActivity.class);
        }

        if(i != null) {
            this.startActivity(i);
        }

    }
}
