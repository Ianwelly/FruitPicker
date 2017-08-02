package wizard.ian.android.fruitpicker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import wizard.ian.android.fruitpicker.Data.FruitContract;
import wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry;
import wizard.ian.android.fruitpicker.Data.FruitDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private FruitDbHelper mDbHelper;




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

        mDbHelper = new FruitDbHelper(this);



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
        FruitDbHelper mDbHelper = new FruitDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + FruitContract.FruitEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_fruit);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertFruit() {

       SQLiteDatabase db =  mDbHelper.getWritableDatabase();
        ContentValues values = new  ContentValues();
        values.put(FruitEntry.COLUMN_FRUIT, "Apple" );
        values.put(FruitEntry.COLUMN_QUANTITY, "200");
        values.put(FruitEntry.COLUMN_PRICE, 100);

        db.insert(FruitEntry.TABLE_NAME, null, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflates an option menu
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //User clicked on a menu option
        switch(item.getItemId()){
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
