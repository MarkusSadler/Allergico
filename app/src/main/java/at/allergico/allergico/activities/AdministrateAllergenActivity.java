package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import at.allergico.allergico.R;
import at.allergico.allergico.adapters.AdministrateAllergenListViewAdapter;
import at.allergico.allergico.database.DAO.AllergenDAO;

public class AdministrateAllergenActivity extends Activity {

    private ListView _lv;
    private Button _naviButton;

    private String _naviIntent = null;

    private AdministrateAllergenActivityListener _listener;
    private AdministrateAllergenListViewAdapter _listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrate_allergen);
        this._listener = new AdministrateAllergenActivityListener();
        this._listAdapter = new AdministrateAllergenListViewAdapter(getApplicationContext(), AllergenDAO.getInstance().getAllergeneList());

        if(savedInstanceState == null) {
            Bundle extras = this.getIntent().getExtras();
            if(extras != null) {
                if(extras.getString("sourceActivity") != null) {
                    this._naviIntent = extras.getString("sourceActivity");
                } else if(extras.getString("mainActivity") != null) {
                    this._naviIntent = extras.getString("mainActivity");
                }
            }
        } else {
            if(savedInstanceState.getSerializable("sourceActivity") != null) {
                this._naviIntent = (String) savedInstanceState.getSerializable("sourceActivity");
            } else if(savedInstanceState.getSerializable("mainActivity") != null) {
                this._naviIntent = (String) savedInstanceState.getSerializable("mainActivity");
            }
        }

        this._lv = (ListView) this.findViewById(R.id.allergenListView);
        this._lv.setOnItemClickListener(this._listener);
        this._lv.setOnItemLongClickListener(this._listener);
        this._lv.setAdapter(this._listAdapter);

        this._naviButton = (Button) this.findViewById(R.id.administrateAllergenNavigationButton);
        this._naviButton.setOnClickListener(this._listener);

        switch(this._naviIntent) {
            case "adminstrateActivity":
                this._naviButton.setText("Zurück zur Profilverwaltung");
                break;
            case "mainActivity":
                this._naviButton.setText("Zurück zum Hauptmenü");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_administrate_allergen, menu);
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

    private class AdministrateAllergenActivityListener implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.administrateAllergenNavigationButton) {
                Intent i = null;

                switch (AdministrateAllergenActivity.this._naviIntent) {
                    case "adminstrateActivity":
                        //TODO DB sochn
                        i = new Intent(AdministrateAllergenActivity.this.getApplicationContext(), AdministrateProductActivity.class);
                        break;
                    case "mainActivity":
                        i = new Intent(AdministrateAllergenActivity.this.getApplicationContext(), MainActivity.class);
                }

                AdministrateAllergenActivity.this.startActivity(i);
            }
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
