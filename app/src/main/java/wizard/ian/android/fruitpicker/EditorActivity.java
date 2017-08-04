package wizard.ian.android.fruitpicker;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry;

import static java.lang.Integer.parseInt;

public class EditorActivity extends AppCompatActivity {

    //    private String fruitText;
    // private String mQuantityText;
    private static final int PICK_IMAGE = 100;


    Uri mImageUri;
    ImageView mImageView;

    private EditText mQuantityEditText;
    private EditText mPriceEditText;
    private EditText mFruitEditText;
    private Button  mLoadImageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mFruitEditText = (EditText) findViewById(R.id.edit_text_fruit);
        mQuantityEditText = (EditText) findViewById(R.id.edit_text_quantity);
        mPriceEditText = (EditText) findViewById(R.id.edit_text_price);
        //Image


        //Inflate Buttons
        Button increaseButton = (Button) findViewById(R.id.button_increase);
        Button decreaseButton = (Button) findViewById(R.id.button_decrease);
        Button deleteButton = (Button) findViewById(R.id.btn_delete);
        Button orderButton = (Button) findViewById(R.id.btn_order);
        mLoadImageButton = (Button) findViewById(R.id.btn_load_image);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Create strings from EditTexts for use as input into order Email
                String fruitText = mFruitEditText.getText().toString();
                String quantityText = mQuantityEditText.getText().toString();
                int quantity = parseInt(quantityText);
                String priceText = mPriceEditText.getText().toString();
                int price = parseInt(priceText);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, " ");
                intent.putExtra(Intent.EXTRA_SUBJECT, fruitText);
                intent.putExtra(Intent.EXTRA_TEXT, "Please send me " + quantity + " " + fruitText
                        + " @ " + price + " pounds");
                startActivity(intent);
            }
        });


        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantityEditText = (EditText) findViewById(R.id.edit_text_quantity);
                String quantityText = mQuantityEditText.getText().toString();
                int quantity = parseInt(quantityText);
                quantity++;
                String newQuantity = Integer.toString(quantity);
                mQuantityEditText.setText(newQuantity);

            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantityEditText = (EditText) findViewById(R.id.edit_text_quantity);
                String quantityText = mQuantityEditText.getText().toString();
                int quantity = parseInt(quantityText);
                quantity--;
                String newQuantity = Integer.toString(quantity);
                mQuantityEditText.setText(newQuantity);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFruitEditText = (EditText) findViewById(R.id.edit_text_fruit);
                mQuantityEditText = (EditText) findViewById(R.id.edit_text_quantity);
                mPriceEditText = (EditText) findViewById(R.id.edit_text_price);

                mFruitEditText.setText(" ");
                mQuantityEditText.setText(" ");
                mPriceEditText.setText(" ");

            }
        });

        mLoadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView = (ImageView) findViewById(R.id.image_view_thumbnail);
                openGallery();
            }
        });


    }
    public void openGallery() {

        //Invoke image gallery
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            mImageUri = data.getData();
            mImageView.setImageURI(mImageUri);

//            //Added code for camera
//            FruitDbHelper mDbHelper = new FruitDbHelper(this);
//            SQLiteDatabase db = mDbHelper.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(FruitEntry.COLUMN_IMAGE, mImageView);
        }
    }

    //Inserts the details entered by the user
    public void insertFruit() {
        //find the input fields, get the text that was entered and then convert into a string
        //and trim any whitespace. Then assign it to a String.
        String nameString = mFruitEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String priceString = mPriceEditText.getText().toString();
        int price = Integer.parseInt(priceString);
        //Need to add image here

//        FruitDbHelper mDbHelper = new FruitDbHelper(this);
//
//        //Get db into write mode
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FruitEntry.COLUMN_FRUIT, nameString);
        values.put(FruitEntry.COLUMN_QUANTITY, quantity);
        values.put(FruitEntry.COLUMN_PRICE, price);
        values.put(FruitEntry.COLUMN_IMAGE, mImageUri.toString() );
        //Need to add image here

        Uri newUri = getContentResolver().insert(FruitEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, getString(R.string.editor_insert_pet_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.editor_insert_pet_successful),
                    Toast.LENGTH_SHORT).show();
        }

        //Insert the ContentValues object into the db.
        // Get the returned id value. If it is -1 we know something went wrong
//        long newRowId = db.insert(FruitEntry.TABLE_NAME,null,values);
//        if (newRowId == -1){
//            Toast.makeText(EditorActivity.this, "Oops! Something went wrong!",Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Fruit was saved!",Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertFruit();
                // Exit activity and return to the previous one
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




