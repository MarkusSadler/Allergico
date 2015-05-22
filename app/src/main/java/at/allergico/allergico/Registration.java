package at.allergico.allergico;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import at.allergico.allergico.database.POJO.UserPOJO;


public class Registration extends ActionBarActivity {
    //region daclarationsOfLayoutVariables
    private EditText    firstname;
    private EditText    lastname;
    private EditText    eMail;
    private TextView    dateDisplay;
    private EditText    username;
    private EditText    password;
    private EditText    passwordRepeat;
    private Button      dateOfBirth;
    private Button      submit;
    private Button      reset;
    //endregion

    boolean[] checkInput = new boolean[7];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //region connectionToLayoutItems
        firstname       = (EditText) findViewById(R.id.firstname);
        lastname        = (EditText) findViewById(R.id.lastname);
        eMail           = (EditText) findViewById(R.id.mailAdress);
        dateDisplay     = (TextView) findViewById(R.id.datDisplay);
        username        = (EditText) findViewById(R.id.username);
        password        = (EditText) findViewById(R.id.password);
        passwordRepeat  = (EditText) findViewById(R.id.passwordRepeat);
        dateOfBirth     = (Button)   findViewById(R.id.datePicker);
        submit          = (Button)   findViewById(R.id.submit);
        reset           = (Button)   findViewById(R.id.reset);
        //endregion

        submit.setEnabled(false);

        //region TextChangeListener for editable Textfields
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

        eMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                String tmp = editable.toString();
                if(editable.length() > 0 && tmp.contains("@")) { checkInput[2] = true; }
                else { checkInput[2] = false;}
                checkAllInputs();
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                if(editable.length() > 0) { checkInput[3] = true; }
                else { checkInput[3] = false;}
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
                if(editable.length() > 6) { checkInput[4] = true; }
                else { checkInput[4] = false;}
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
                if(editable.length() > 6) { checkInput[5] = true; }
                else { checkInput[5] = false;}
                checkAllInputs();
            }
        });

        dateDisplay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable)
            {
                String tmp = editable.toString();
                if(tmp.equals("Bitte auswählen") == false) { checkInput[6] = true; }
                else { checkInput[6] = false;}
                checkAllInputs();
            }
        });
        //endregion

        //region Test
        dateOfBirth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDatePicker();
            }
        });

        reset.setOnClickListener(new View.OnClickListener()
        {
             @Override
             public void onClick(View view)
             {
                 firstname.setText("");         lastname.setText("");
                 eMail.setText("");             username.setText("");
                 password.setText("");          passwordRepeat.setText("");
                 dateDisplay.setText("Bitte auswählen");
             }
         }
        );

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(password.getText().toString().equals(passwordRepeat.getText().toString()))
                {
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = "";
                    for(int i=0; i < dateDisplay.getText().toString().split("\\.").length; i++)
                    {
                        dateString += dateDisplay.getText().toString().split("\\.")[i];
                    }
                    Date date = GregorianCalendar.getInstance().getTime();
                    try {
                        date = ft.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    UserPOJO insertUser = new UserPOJO( -1, username.getText().toString(),  password.getText().toString(),
                                                            eMail.getText().toString(),     firstname.getText().toString(),
                                                            lastname.getText().toString(),  date, true);
                    DisplayToast(insertUser.getFirstname() + "," + insertUser.getLastname() + "," + insertUser.getUsername());
                }
                else {DisplayToast("Passwörter stimmen nicht überein"); }
            }
        });
        //endregion
    }

    private void checkAllInputs()
    {
        if(checkInput[0] && checkInput[1] && checkInput[2] && checkInput[3] && checkInput[4] && checkInput[5] && checkInput[6])
        {   submit.setEnabled(true);    }
        else
        {   submit.setEnabled(false);   }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("year", calender.get(Calendar.YEAR));
        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");
    }

        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            if(getCurrentDate()[0] < year)
            {
                DisplayToast("Fehler - Jahr liegt in der Zukunft");
            }
            else if(getCurrentDate()[0] >= year && getCurrentDate()[1] < monthOfYear)
            {
                DisplayToast("Fehler - Monat liegt in der Zukunft");
            }
            else if(getCurrentDate()[0] >= year && getCurrentDate()[1] >= monthOfYear && getCurrentDate()[2] < dayOfMonth)
            {
                DisplayToast("Fehler - Tag liegt in der Zukunft");
            }
            else
            {
                dateDisplay.setText(String.valueOf(dayOfMonth) + "." + String.valueOf(monthOfYear)
                        + "." + String.valueOf(year));
            }
        }
    };

    public int[] getCurrentDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        String dateString = dateFormat.format(date).split("-")[0];
        int[] curDate = new int[3];
        curDate[0] = Integer.parseInt(dateString.split("\\.")[0]);
        curDate[1] = Integer.parseInt(dateString.split("\\.")[1]);
        curDate[2] = Integer.parseInt(dateString.split("\\.")[2]);
        return curDate;
    }

    public void DisplayToast(String tmp)
    {
        Toast.makeText(Registration.this,tmp,Toast.LENGTH_LONG).show();
    }
}
