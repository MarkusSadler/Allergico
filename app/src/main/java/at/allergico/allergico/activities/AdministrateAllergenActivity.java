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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.allergico.allergico.R;
import at.allergico.allergico.adapters.AdministrateAllergenListViewAdapter;
import at.allergico.allergico.database.DAO.AllergenDAO;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.helper.ColourHelper;
import at.allergico.allergico.helper.CurrentUser;

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
        this._listener = new AdministrateAllergenActivityListener(CurrentUser.getLogedInUser().getAllergene());
        this._listAdapter = new AdministrateAllergenListViewAdapter(getApplicationContext(), AllergenDAO.getInstance().getAllergeneList());

        if(savedInstanceState == null) {
            Bundle extras = this.getIntent().getExtras();
            if(extras != null) {
                if(extras.getString("sourceActivity") != null) {
                    this._naviIntent = extras.getString("sourceActivity");
                }
            }
        } else {
            if(savedInstanceState.getSerializable("sourceActivity") != null) {
                this._naviIntent = (String) savedInstanceState.getSerializable("sourceActivity");
            }
        }

        this._lv = (ListView) this.findViewById(R.id.allergenListView);
        if(this._naviIntent.compareTo("addProductActivity") == 0 || this._naviIntent.compareTo("adminstrateActivity") == 0) {
            this._lv.setOnItemLongClickListener(this._listener);
        }
        this._lv.setAdapter(this._listAdapter);


        this._naviButton = (Button) this.findViewById(R.id.administrateAllergenNavigationButton);
        this._naviButton.setOnClickListener(this._listener);
        switch(this._naviIntent) {
            case "adminstrateActivity":
                this._naviButton.setText("Zurueck zur Profilverwaltung");
                break;
            case "mainActivity":
                this._naviButton.setText("Zurueck zum HauptmenueMike");
                break;
            case "addProductActivity":
                this._naviButton.setText("Produkt hinzufuegen");
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

        private Set<Integer> _selectedAllergenes;

        public AdministrateAllergenActivityListener(List<AllergenPOJO> selectedAllergenes) {
            this._selectedAllergenes = new HashSet<Integer>();

            for(AllergenPOJO item : selectedAllergenes) {
                this._selectedAllergenes.add(item.getAllergenID());
            }
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(AdministrateAllergenActivity.this.getApplicationContext(), "in onclick", Toast.LENGTH_LONG);
            if(v.getId() == R.id.administrateAllergenNavigationButton) {
                Intent i = null;

                Set<Integer> newUserAllergenes = getSelectedItemsFromListView();
                Toast.makeText(AdministrateAllergenActivity.this.getApplicationContext(), "Size: " + newUserAllergenes.size(), Toast.LENGTH_LONG);

                switch (AdministrateAllergenActivity.this._naviIntent) {
                    case "adminstrateActivity":
                        //TODO update user allergenes

                        i = new Intent(AdministrateAllergenActivity.this.getApplicationContext(), AdministrateProductActivity.class);
                        break;
                    case "addProductActivity":
                        //TODO DB write product into db
                    case "mainActivity":
                        i = new Intent(AdministrateAllergenActivity.this.getApplicationContext(), MainActivity.class);
                        break;
                }

                AdministrateAllergenActivity.this.startActivity(i);
            }
        }

        private Set<Integer> getSelectedItemsFromListView() {
            return this._selectedAllergenes;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            boolean result = this._selectedAllergenes.add(position);

            if(result == true) {
                view.setBackgroundColor(ColourHelper.SELECTED_LIST_ELEMENT);
            } else {
                this._selectedAllergenes.remove(position);
                view.setBackgroundColor(R.color.Transparent);
            }

            return result;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
