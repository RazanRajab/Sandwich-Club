package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    private Sandwich sandwich;

    private ImageView ImageIv;
    private TextView also_known_tv;
    private TextView description_tv;
    private TextView origin_tv;
    private TextView ingredients_tv;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }


        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        initViews();

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(ImageIv);

        setTitle(sandwich.getMainName());
    }

    private void initViews() {

        ImageIv = findViewById(R.id.image_iv);
        also_known_tv = findViewById(R.id.also_known_tv);
        description_tv = findViewById(R.id.description_tv);
        origin_tv = findViewById(R.id.origin_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    private void populateUI() {
        if (sandwich.getAlsoKnownAs().size() != 0) {
            also_known_tv.setText(sandwich.getAlsoKnownAs().toString());
        }
        else {
            also_known_tv.setText(R.string.NO_DATA_MEESAGE);
        }
        if (!sandwich.getDescription().trim().equals("") && sandwich.getDescription() != null) {
            description_tv.setText(sandwich.getDescription());
        } else {
            description_tv.setText(R.string.NO_DATA_MEESAGE);
        }
        if (!sandwich.getPlaceOfOrigin().trim().equals("") && sandwich.getPlaceOfOrigin() != null) {
            origin_tv.setText(sandwich.getPlaceOfOrigin());
        } else {
            origin_tv.setText(R.string.NO_DATA_MEESAGE);
        }
        if (sandwich.getIngredients().size() != 0) {
            ingredients_tv.setText(sandwich.getIngredients().toString());
        } else {
            ingredients_tv.setText(R.string.NO_DATA_MEESAGE);
        }
    }
}
