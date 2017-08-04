package wizard.ian.android.fruitpicker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry;

public class CatalogActivity extends AppCompatActivity {

    //private FruitDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

       // mDbHelper = new FruitDbHelper(this);


    }

    //When the app returns to this activity from the editor activity the onCreate isn't called
    //so the old database information would still be displayed. The onStart() method refreshes what
    //ever is needed, in this case the database.

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
//       FruitDbHelper mDbHelper = new FruitDbHelper(this);
////
////        // Create and/or open a database to read from it
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Create the Strings needed for entry into the Cursor object

        String[] projection = {
                FruitEntry._ID,
                FruitEntry.COLUMN_FRUIT,
                FruitEntry.COLUMN_QUANTITY,
                FruitEntry.COLUMN_PRICE,
                FruitEntry.COLUMN_IMAGE
        };

////Inflate the Cursor object
//        Cursor cursor = db.query(
//                FruitEntry.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//        );

        Cursor cursor = getContentResolver().query(
                FruitEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);


        TextView displayView = (TextView) findViewById(R.id.text_view_fruit);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(FruitEntry._ID + " - " +
                    FruitEntry.COLUMN_FRUIT + " - " +
                    FruitEntry.COLUMN_QUANTITY + " - " +
                    FruitEntry.COLUMN_PRICE + " - " +
                    FruitEntry.COLUMN_IMAGE + "\n");


            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(FruitEntry._ID);
            int fruitColumnIndex = cursor.getColumnIndex(FruitEntry.COLUMN_FRUIT);
            int quantityColumnIndex = cursor.getColumnIndex(FruitEntry.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(FruitEntry.COLUMN_PRICE);
            int imageColumnIndex = cursor.getColumnIndex(FruitEntry.COLUMN_IMAGE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(fruitColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                String currentImage = cursor.getString(imageColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentQuantity + " - " +
                        currentPrice + " - " +
                        currentImage));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertFruit() {

        //SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FruitEntry.COLUMN_FRUIT, "Apple");
        values.put(FruitEntry.COLUMN_QUANTITY, "200");
        values.put(FruitEntry.COLUMN_PRICE, 100);

        Uri newUri = getContentResolver().insert(FruitEntry.CONTENT_URI, values);

        //db.insert(FruitEntry.TABLE_NAME, null, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates an option menu
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //User clicked on a menu option
        switch (item.getItemId()) {
            //Respond to a click on 'Insert dummy data'
            case R.id.action_insert_dummy_data:
                //do nothing for now
                insertFruit();
                displayDatabaseInfo();
                return true;
            //Respond to a click on 'Delete all entries'
            case R.id.action_delete_all_entries:
                //Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}