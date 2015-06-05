package at.allergico.allergico.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import at.allergico.allergico.R;
import at.allergico.allergico.helper.ProductHelper;

public class AddProductActivity extends Activity {

    private EditText _productName;
    private EditText _productDescription;

    private Button _nextActivityButton;

    private RadioGroup _buttonGroup;
    private RadioButton _photoTakenRadioButton;
    private RadioButton _noPhotoCRadioButton;

    private ImageView _madePhoto;
    private String _productNameString;
    private String _productDescriptionString;


    private AddProductActivityEventListener _listener;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        this._listener = new AddProductActivityEventListener();

        this._madePhoto = (ImageView) this.findViewById(R.id.addProductImage);

        this._productName = (EditText) this.findViewById(R.id.addProductName);
        this._productName.addTextChangedListener(this._listener);

        this._productDescription = (EditText) this.findViewById(R.id.addProductDescription);
        this._productDescription.addTextChangedListener(this._listener);

        this._buttonGroup = (RadioGroup) this.findViewById(R.id.addProductButtonGroup);

        this._photoTakenRadioButton = (RadioButton) this.findViewById(R.id.takePhotoRadio);
        this._photoTakenRadioButton.setOnClickListener(this._listener);
        this._photoTakenRadioButton.setOnCheckedChangeListener(this._listener);

        this._noPhotoCRadioButton = (RadioButton) this.findViewById(R.id.noTakePhotoRadio);;
        this._noPhotoCRadioButton.setOnClickListener(this._listener);
        this._noPhotoCRadioButton.setOnCheckedChangeListener(this._listener);

        this._nextActivityButton = (Button) this.findViewById(R.id.nextButton);
        this._nextActivityButton.setOnClickListener(this._listener);
        this._nextActivityButton.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = intent.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            AddProductActivity.this._madePhoto.setImageBitmap(imageBitmap);
        } else if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                Intent i = new Intent(getApplicationContext(), AdministrateAllergenActivity.class);
                i.putExtra("sourceActivity", "addProductActivity");

                ProductHelper.EANCode = contents;


                startActivity(i);
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }

    private class AddProductActivityEventListener implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.nextButton:
                    AddProductActivity.this._productNameString = AddProductActivity.this._productName.getText().toString();
                    AddProductActivity.this._productDescriptionString = AddProductActivity.this._productDescription.getText().toString();

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                    startActivityForResult(intent, 0);

                    break;
                case R.id.takePhotoRadio:
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                        this.checkAllInputs();
                        break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            checkAllInputs();
        }

        private void checkAllInputs() {
            if(AddProductActivity.this._productName.length() > 0 && AddProductActivity.this._productDescription.length() > 0) {
                if(AddProductActivity.this._photoTakenRadioButton.isChecked() && AddProductActivity.this._madePhoto.getDrawable() != null || AddProductActivity.this._noPhotoCRadioButton.isChecked()) {
                    AddProductActivity.this._nextActivityButton.setEnabled(true);
                    ProductHelper.productdescription = AddProductActivity.this._productDescription.getText().toString();
                    ProductHelper.productname = AddProductActivity.this._productName.getText().toString();
                } else {
                    AddProductActivity.this._nextActivityButton.setEnabled(false);
                }
            } else {
                AddProductActivity.this._nextActivityButton.setEnabled(false);
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            checkAllInputs();
        }
    }
}