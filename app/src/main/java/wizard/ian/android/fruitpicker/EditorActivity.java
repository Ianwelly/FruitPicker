package wizard.ian.android.fruitpicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static java.lang.Integer.parseInt;

public class EditorActivity extends AppCompatActivity {

    //    private String fruitText;
    // private String mQuantityText;
    private static final int SELECTED_PICTURE = 1;
    ImageView mImageView;

    private EditText mQuantityEditText;
    private EditText mPriceEditText;
    private EditText mFruitEditText;
    private Button  mLoadImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //Inflate EditTexts

        //Inflate Buttons
        Button increaseButton = (Button) findViewById(R.id.button_increase);
        Button decreaseButton = (Button) findViewById(R.id.button_decrease);
        Button deleteButton = (Button) findViewById(R.id.btn_delete);
        Button orderButton = (Button) findViewById(R.id.btn_order);
        mLoadImageButton = (Button) findViewById(R.id.btn_load_image);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFruitEditText = (EditText) findViewById(R.id.edit_text_fruit);
                mQuantityEditText = (EditText) findViewById(R.id.edit_text_quantity);
                mPriceEditText = (EditText) findViewById(R.id.edit_text_price);
                mImageView = (ImageView) findViewById(R.id.image_view_thumbnail);

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
    }
}





