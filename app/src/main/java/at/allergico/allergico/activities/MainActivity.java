package at.allergico.allergico.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import at.allergico.allergico.R;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private Button eanReader;
    private Button administrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eanReader = (Button) findViewById(R.id.EANButton);
        administrate = (Button) findViewById(R.id.AdministrateUser);
        eanReader.setOnClickListener(this);
        administrate.setOnClickListener(this);
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
        if(view.getId() == administrate.getId()){
            MainActivity.this.startActivity(new Intent(MainActivity.this, AdministrateProductActivity.class));
        }else if(view.getId() == eanReader.getId()){
            MainActivity.this.startActivity(new Intent(MainActivity.this, EANReaderActivity.class));
        }
    }
}
