package at.allergico.allergico.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.allergico.allergico.R;

public class ShowAllergenDetail extends ActionBarActivity {

    private TextView _allergenName;
    private TextView _allergenDescription;
    private Button _backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allergen_detail);

        this._backButton = (Button) this.findViewById(R.id.backButton);

        this._allergenName = (TextView) this.findViewById(R.id.allergenDescription);
        this._allergenDescription = (TextView) this.findViewById(R.id.allergenDescription);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_allergen_detail, menu);
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

    private class ShowAllergenDetailListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.backButton) {
                Toast.makeText(getApplicationContext(), "Back button pressed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
