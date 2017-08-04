package wizard.ian.android.fruitpicker.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Kezia on 01/08/2017.
 */

public final class FruitContract  {

    private FruitContract(){}

    //Set up the URI constants
   public static final String CONTENT_AUTHORITY = "wizard.ian.android.fruitpicker";
    public  static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FRUITS = "fruits";



    //inner class defining the table constants, this is all we do. The actual building of the
    //database is done in the db helper
    public static final class FruitEntry implements BaseColumns {

        //Construct the final Uri path
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FRUITS);

        //The strings relate to the constant names not the data type

        public static final String TABLE_NAME = "fruits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FRUIT = "fruit";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image";
        //image to go in here
        // public static final String String COLUMN_IMAGE =" mImageUri"

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRUITS;

        /**
         * The MIME type of the {@link #//CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRUITS;

    }
    }





