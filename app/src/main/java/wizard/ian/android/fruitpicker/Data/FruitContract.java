package wizard.ian.android.fruitpicker.Data;

import android.provider.BaseColumns;

/**
 * Created by Kezia on 01/08/2017.
 */

public final class FruitContract  {

    private FruitContract(){}

    //inner class defining the table constants, this is all we do. The actual building of the
    //database is done in the db helper
    public static abstract class FruitEntry implements BaseColumns {

        //The strings relate to the constant names not the data type

        public static final String TABLE_NAME = "fruits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FRUIT = "fruit";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image";
        //image to go in here
        // public static final String String COLUMN_IMAGE =" mImageUri"
    }


}
