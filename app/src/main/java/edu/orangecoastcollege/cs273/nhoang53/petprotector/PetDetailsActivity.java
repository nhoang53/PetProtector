package edu.orangecoastcollege.cs273.nhoang53.petprotector;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.MessageFormat;

public class PetDetailsActivity extends AppCompatActivity {

    private ImageView petImageView;
    private Uri imageUri;
    private TextView nameTextView;
    private TextView detailsTextView;
    private TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        petImageView = (ImageView) findViewById(R.id.petImageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        detailsTextView = (TextView) findViewById(R.id.detailsTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);

        Intent intent = getIntent();
        nameTextView.setText(intent.getStringExtra("Name").toString());
        detailsTextView.setText(intent.getStringExtra("Details").toString());

        // convert phone number
        String phone = intent.getStringExtra("Phone").toString();
        try {
            MessageFormat phoneNumberFormat = new MessageFormat("({0})-{1}-{2}");
            String[] phoneArray = {phone.substring(0, 3),
                    phone.substring(3, 6),
                    phone.substring(6)};
            phoneTextView.setText(phoneNumberFormat.format(phoneArray));
        }catch (Exception ex)
        {
            Log.e("Pet Details.", "Error formatting phone number.", ex);
            phoneTextView.setText(phone);
        }

        // get image uri
        imageUri = Uri.parse(intent.getStringExtra("ImageUri"));
        petImageView.setImageURI(imageUri);
    }
}
