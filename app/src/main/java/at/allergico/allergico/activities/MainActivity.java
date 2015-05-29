package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import at.allergico.allergico.R;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button eanReader;
    private Button administrate;
    private Button _productOverview;
    private Button _addProductButton;
    private Button _showAllergenButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eanReader = (Button) findViewById(R.id.EANButton);
        administrate = (Button) findViewById(R.id.AdministrateUser);
        this._productOverview = (Button) findViewById(R.id.productOverview);
        this._addProductButton = (Button) findViewById(R.id.addProductButton);

        eanReader.setOnClickListener(this);
        administrate.setOnClickListener(this);
        this._productOverview.setOnClickListener(this);
        this._addProductButton.setOnClickListener(this);

        _showAllergenButton = (Button) findViewById(R.id.showAllergenebutton);
        this._showAllergenButton.setOnClickListener(this);
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
        if(view.getId() == R.id.AdministrateUser){
            i = new Intent(this, AdministrateProductActivity.class);
        }else if(view.getId() == R.id.eanButton){
           i = new Intent(this, EANReaderActivity.class);
        } else if(view.getId() == R.id.productOverview) {
            i = new Intent(this, ProductOverviewActivity.class);
        } else if(view.getId() == R.id.addProductButton) {
            i = new Intent(this, AddProductActivity.class);
        }else if(view.getId() == R.id.showAllergenebutton){
            i = new Intent(this,ShowAllergenActivity.class);
        }

        if(i != null) {
            this.startActivity(i);
        }

    }
}
