package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.UserDAO;
import at.allergico.allergico.database.POJO.UserPOJO;
import at.allergico.allergico.helper.CurrentUser;

public class AdministrateProductActivity extends Activity implements View.OnClickListener{
    //region Variables
    /** Variables to working with the layout elements. */
    private EditText    firstname;          private EditText    lastname;
    private EditText    eMail;              private TextView    dateDisplay;
    private EditText    username;           private EditText    password;
    private EditText    passwordRepeat;     private ImageButton addAllergenBtn;
    private Button      submitBtn;          private Button      cancelBtn;
    private UserPOJO    updatedUser;

    /** Help variable for input */
    private boolean[] checkInput = new boolean[4];
    /** For developing instead of helper.CurrentUser */
    private CurrentUser cU = CurrentUser.getInstance();
    private UserPOJO currentUser = cU.getLogedInUser();
    private UserDAO ud = UserDAO.getInstance();
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrate_product);
        //region Variablebindings
        /** Bind the variables with the layout items on the view */
        firstname       = (EditText)    findViewById(R.id.firstname);
        lastname        = (EditText)    findViewById(R.id.lastname);
        eMail           = (EditText)    findViewById(R.id.mailAdress);
        dateDisplay     = (TextView)    findViewById(R.id.datDisplay);
        username        = (EditText)    findViewById(R.id.username);
        password        = (EditText)    findViewById(R.id.password);
        passwordRepeat  = (EditText)    findViewById(R.id.passwordRepeat);
        submitBtn       = (Button)      findViewById(R.id.submit);
        cancelBtn       = (Button)      findViewById(R.id.cancel);
        addAllergenBtn  = (ImageButton) findViewById(R.id.addAllergen);

        //endregion
        System.out.println(currentUser.getFirstname());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
        String[] reportDate = df.format(currentUser.getDob()).split("-");
        for(int i=0; i<checkInput.length; i++) { checkInput[i] = true; }
        firstname.setText(currentUser.getFirstname());
        lastname.setText(currentUser.getLastname());
        eMail.setText(currentUser.getEmail());
        dateDisplay.setText(reportDate[0]);
        username.setText(currentUser.getUsername());
        submitBtn.setEnabled(false);
        checkAllInputs();


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
                else {
                    checkInput[2] = false;
                }
                checkAllInputs();
            }
        });

        passwordRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0 || editable.length() > 6) {
                    checkInput[3] = true;
                } else {
                    checkInput[3] = false;
                }
                checkAllInputs();
            }
        });

        addAllergenBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

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

    /** If each input is valid, the submitBtn button will be enabled, otherwise disabled. */
    private void checkAllInputs()
    {
        if(checkInput[0] && checkInput[1] && checkInput[2] && checkInput[3])
        {   submitBtn.setEnabled(true);    }
        else
        {   submitBtn.setEnabled(false);   }
    }

    @Override
    public void onClick(View view) {
        Intent i = null;
        if(view.getId() == R.id.submit)
        {
            if(password.getText().length() == 0 && passwordRepeat.getText().length() == 0){
                updatedUser = new UserPOJO(currentUser.getUserID(), currentUser.getUsername(), currentUser.getPassword(), currentUser.getEmail(), firstname.getText().toString(), lastname.getText().toString(), currentUser.getDob(), true);
            }else if(password.getText().toString().equals(passwordRepeat.getText().toString()) == false){
                password.setError("Passwörter stimmen nicht überein!");
                passwordRepeat.setError("Passwörter stimmen nicht überein!");
            }else{
                updatedUser = new UserPOJO(currentUser.getUserID(), currentUser.getUsername(), password.getText().toString(), currentUser.getEmail(), firstname.getText().toString(), lastname.getText().toString(), currentUser.getDob(), true);
            }
            System.out.println(updatedUser.toString());
            
            ud.updateUser(updatedUser);
            cU.setLogedInUser(ud.getUserByUsernameOrEmail(currentUser.getEmail()));
            i = new Intent(this, MainActivity.class);
        }else if(view.getId() == R.id.cancel){
            i = new Intent(this, MainActivity.class);
        }else if(view.getId() == R.id.addAllergen){
            i = new Intent(this, AdministrateAllergenActivity.class);
            i.putExtra("sourceActivity", "adminstrateActivity");
        }

        if(i != null) {
            this.startActivity(i);
        }
    }
}
