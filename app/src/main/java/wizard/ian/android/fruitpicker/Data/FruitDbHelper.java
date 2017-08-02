package wizard.ian.android.fruitpicker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry.COLUMN_FRUIT;
import static wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry.COLUMN_IMAGE;
import static wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry.COLUMN_PRICE;
import static wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry.COLUMN_QUANTITY;
import static wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry.TABLE_NAME;
import static wizard.ian.android.fruitpicker.Data.FruitContract.FruitEntry._ID;

/**
 * Created by Kezia on 01/08/2017.
 */

public class FruitDbHelper extends SQLiteOpenHelper {

    //This is the name of the database NOT the tabl
    private static final String DATABASE_NAME = "fruit.db";

    //This will change when we change the schema. For e.g. adding a new colum
    private static final int DATABASE_VERSION = 1;



    public FruitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //This method instantiates the database the first time around
    @Override
    public void onCreate(SQLiteDatabase db) {
        //First we build the table using the format of the SQLite db in the form of a String
        //CREATE TABLE fruit (_id INTEGER PRIMARY KEY, fruit TEXT, quantity INTEGER, price INTEGER,
        //image BITMAP
        String SQL_CREATE_FRUIT_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FRUIT
                 + " TEXT NOT NULL, " + COLUMN_QUANTITY + " TEXT NOT NULL, " + COLUMN_PRICE +
                 " TEXT NOT NULL, " + COLUMN_IMAGE + " BLOB);";
        // + COLUMN BITMAP NEED TO ENTER THIS LATER

        //The we use the execute SQL method to execute the build. We execute it on our instance of
        // the
        //database that we created in as db in the onCreate method, passing in the table name as an
        //argument
        db.execSQL(SQL_CREATE_FRUIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
