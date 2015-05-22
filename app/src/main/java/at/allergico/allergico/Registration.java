package at.allergico.allergico;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Registration extends ActionBarActivity {
    private Button birthday;
    private TextView dateDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        birthday = (Button) findViewById(R.id.datePicker);
        dateDisplay = (TextView) findViewById(R.id.datDisplay);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
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
