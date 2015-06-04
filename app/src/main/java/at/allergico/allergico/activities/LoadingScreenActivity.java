package at.allergico.allergico.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.zxing.client.android.Intents;

import at.allergico.allergico.LoginActivity;
import at.allergico.allergico.R;
import at.allergico.allergico.database.DAO.AllergenDAO;
import at.allergico.allergico.database.DAO.ProductCategoryDAO;
import at.allergico.allergico.database.DAO.ProductDAO;
import at.allergico.allergico.database.DAO.UserDAO;
import at.allergico.allergico.database.DAO.UserHasAllergenDAO;
import at.allergico.allergico.database.POJO.ProductPOJO;
import at.allergico.allergico.helper.EncryptionHelper;

public class LoadingScreenActivity extends Activity implements OnLoadingDAOsCompleted{
    private ProgressBar progressBar;
    private AllergenDAO allergenDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        progressBar = (ProgressBar) findViewById(R.id.LoadingScreenProgressBar);
        progressBar.setMax(100);
        DAOLoader daoLoader = new DAOLoader();
        daoLoader.doInBackground(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loading_screen, menu);
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
    public void onLoadingDAOsTaskCompleted() {
        allergenDAO = AllergenDAO.getInstance();

        if(allergenDAO.getAllergeneList().size() == 0){
            progressBar.setProgress(100);
            DisplayToast("No Internetconnection!");

        }else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    /** Display the entered Message in a toast */
    public void DisplayToast(String tmp)
    {
        Toast.makeText(LoadingScreenActivity.this, tmp, Toast.LENGTH_LONG).show();
    }

}

class DAOLoader  extends AsyncTask<OnLoadingDAOsCompleted, Integer, Boolean>{
    private AllergenDAO allergenDAO;
    private ProductDAO productDAO;
    private ProductCategoryDAO productCategoryDAO;
    private UserDAO userDAO;
    private UserHasAllergenDAO userHasAllergenDAO;
    private EncryptionHelper encryptionHelper;

    private OnLoadingDAOsCompleted listener;


    @Override
    protected Boolean doInBackground(OnLoadingDAOsCompleted... params) {
        this.listener = params[0];
        encryptionHelper = EncryptionHelper.get_instance();
        allergenDAO = AllergenDAO.getInstance();
        productCategoryDAO = ProductCategoryDAO.getInstance();
        productDAO = ProductDAO.getInstance();
        userHasAllergenDAO = UserHasAllergenDAO.getInstance();
        userDAO = UserDAO.getInstance();
        listener.onLoadingDAOsTaskCompleted();
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }

    @Override
    protected void onPostExecute(Boolean b){

    }
}

interface OnLoadingDAOsCompleted{
    void onLoadingDAOsTaskCompleted();
}