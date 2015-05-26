package at.allergico.allergico.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.AllergenDAO;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.UserPOJO;

public class AdministrateProductActivity extends ListActivity {
    //region Variables
    /** Variables to working with the layout elements. */
    private EditText    firstname;          private EditText    lastname;
    private EditText    eMail;              private TextView    dateDisplay;
    private EditText    username;           private EditText    password;
    private EditText    passwordRepeat;     private Button      addAllergen;
    private Spinner     sItems;             private ListView    allergenListView;
    private Button      submit;             private Button      cancel;

    /** Help variable for input */
    private AllergenDAO allergenDAO = AllergenDAO.getInstance();
    private List<String> spinnerArray;
    private ArrayAdapter<String> spinnerAdapter;
    private String selected;
    private boolean[] checkInput = new boolean[4];
    private ArrayAdapter<String> listViewAdapter;
    private List<String> listViewArray;
    /** For developing instead of helper.CurrentUser */
    private UserPOJO currentUser = new UserPOJO(10, "MikeTike", "michael", "wi13b020@technikum-wien.at", "Michael", "Mosgoeller", new Date(), true);
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrate_product);
        //region Variablebindings
        /** Bind the variables with the layout items on the view */
        firstname       = (EditText) findViewById(R.id.firstname);
        lastname        = (EditText) findViewById(R.id.lastname);
        eMail           = (EditText) findViewById(R.id.mailAdress);
        dateDisplay     = (TextView) findViewById(R.id.datDisplay);
        username        = (EditText) findViewById(R.id.username);
        password        = (EditText) findViewById(R.id.password);
        passwordRepeat  = (EditText) findViewById(R.id.passwordRepeat);
        submit          = (Button)   findViewById(R.id.submit);
        cancel          = (Button)   findViewById(R.id.cancel);
        sItems          = (Spinner)  findViewById(R.id.allergenSpinner);
        addAllergen     = (Button)   findViewById(R.id.addAllergen);

        //endregion

        firstname.setText(currentUser.getFirstname());
        lastname.setText(currentUser.getLastname());
        eMail.setText(currentUser.getEmail());
        dateDisplay.setText(currentUser.getDob().toString());
        username.setText(currentUser.getUsername());
        listViewArray = new ArrayList<String>();
        spinnerArray =  new ArrayList<String>();

        /** Put all allergen from the database in the spinnerArray and with the adapter into the drop down menu*/
        for(AllergenPOJO ag : allergenDAO.getAllergeneList())
        {
            spinnerArray.add(String.valueOf(ag.getAbbreviation()) + " - " + ag.getDescription());
        }
        spinnerAdapter = new ArrayAdapter<String>(AdministrateProductActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(spinnerAdapter);


        //create an ArrayAdaptar from the String Array
        listViewAdapter = new ArrayAdapter<String>(this, R.layout.allergen_list, listViewArray);
        allergenListView= (ListView)findViewById(android.R.id.list);
        // Assign adapter to ListView
        allergenListView.setAdapter(listViewAdapter);

        submit.setEnabled(false);

        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                if(editable.length() > 0) { checkInput[0] = true; }
                else { checkInput[0] = false;}
                checkAllInputs();
            }
        });

        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                if(editable.length() > 0) { checkInput[1] = true; }
                else { checkInput[1] = false;}
                checkAllInputs();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                if(editable.length() == 0 || editable.length() > 6) { checkInput[2] = true; }
                else { checkInput[2] = false;}
                checkAllInputs();
            }
        });

        passwordRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                if(editable.length() == 0 || editable.length() > 6) { checkInput[3] = true; }
                else { checkInput[3] = false;}
                checkAllInputs();
            }
        });

        addAllergen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = sItems.getSelectedItem().toString();
                int index = spinnerArray.indexOf(selected);
                listViewArray.add(selected);
                listViewAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_administrate_product, menu);
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

    /** If each input is valid, the submit button will be enabled, otherwise disabled. */
    private void checkAllInputs()
    {
        if(checkInput[0] && checkInput[1] && checkInput[2] && checkInput[3])
        {   submit.setEnabled(true);    }
        else
        {   submit.setEnabled(false);   }
    }
}
