package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.AllergenDAO;
import at.allergico.allergico.database.DAO.UserHasAllergenDAO;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.helper.CurrentUser;

/**
 * Created by AGebhard on 28.05.2015.
 */
public class AllergenListActivity extends Activity {
    private ListView aList;
    private List<String> listArray;
    private AllergenDAO allergenDAO = AllergenDAO.getInstance();
    private UserHasAllergenDAO uhaDAO = UserHasAllergenDAO.getInstance();
    private List<Integer> userAllergen;
    private CurrentUser cu = CurrentUser.getInstance();
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergen_list);

        listArray = new ArrayList<>();
        /** Put all allergen from the database in the spinnerArray and with the adapter into the drop down menu*/
        for(AllergenPOJO ag : allergenDAO.getAllergeneList())
        {
            listArray.add(String.valueOf(ag.getAbbreviation()) + " - " + ag.getDescription());
        }
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);
        aList = (ListView) findViewById(R.id.allergenList);
        aList.setAdapter(listAdapter);
        aList.setClickable(true);

        userAllergen = new ArrayList<>();
        for(AllergenPOJO i : uhaDAO.getAllergeneOfUser(cu.getLogedInUser().getUserID()))
        {
            userAllergen.add(i.getAllergenID());
        }

        aList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = i+1;
                if(userAllergen.contains(id) == false)
                {
                    adapterView.getChildAt(i).setBackgroundColor(Color.BLUE);
                    userAllergen.add(id);
                }
                else
                {
                    adapterView.getChildAt(i).setBackgroundColor(Color.WHITE);
                    userAllergen.remove(id);
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_allergen_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
