package com.example.android.logindemo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.logindemo.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private final static int TEZ_REQUEST_CODE = 123;
    private static final int REQUEST_CALL=1;
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    TextView name, description, date, contact, price, tezId;
    ImageView teacherDetailImageView;
    ImageView whatsApp, call;
    Random rand = new Random();
    String itemPrice, itemTezId;
    String itemContactDetails;


    private void initializeWidgets() {
        name = findViewById(R.id.nameDetailTextView);
        description = findViewById(R.id.descriptionDetailTextView);
        date = findViewById(R.id.dateDetailTextView);
        contact = findViewById(R.id.contactDetailTextView);
        tezId = findViewById(R.id.tezEditText);
        price = findViewById(R.id.priceDetailTextView);

        teacherDetailImageView = findViewById(R.id.teacherDetailImageView);
        whatsApp = (ImageView) findViewById(R.id.whatsAppImageView);
        call = (ImageView) findViewById(R.id.callImageView);
    }

    private String getDateToday() {
        Date date = new Date();
        String today = dateFormat.format(date);
        return today;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Book Details");
        initializeWidgets();

        Intent intent = this.getIntent();
        final String itemName = intent.getExtras().getString("NAME_KEY");
        itemPrice = intent.getExtras().getString("PRICE_KEY");
        itemContactDetails = intent.getExtras().getString("CONTACT_DETAILS");
        String itemDescription = intent.getExtras().getString("DESCRIPTION_KEY");
        String imageURL = intent.getExtras().getString("IMAGE_KEY");
        itemTezId = intent.getExtras().getString("TEZ_KEY");


        name.setText("Book Name: " + itemName);
        price.setText("Price: " + itemPrice);
        contact.setText("Phone: " + itemContactDetails);
        description.setText("Details: " + itemDescription);


        date.setText("Date: " + getDateToday());
        whatsApp.setOnClickListener(getWhatsAppOnClickListener(itemName, itemContactDetails));


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

        Picasso.with(this)
                .load(imageURL)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(teacherDetailImageView);
    }

    private void makePhoneCall() {
        if(itemContactDetails.trim().length() > 0){
            if(ContextCompat.checkSelfPermission(DetailsActivity.this,
                    Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(DetailsActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }
            else {
                String dial = "tel:" +itemContactDetails;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private View.OnClickListener getWhatsAppOnClickListener(final String itemName, final String itemContactDetails) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                "https://api.whatsapp.com/send?phone=+88" + itemContactDetails +
                                        "&text=I'm%20interested%20in%20your%20book%20" +
                                        itemName + "%20for%20sale"
                        )));
            }
        };
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEZ_REQUEST_CODE) {
            Log.d("result", data.getStringExtra("Status"));
        }
    }

}
