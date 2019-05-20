package com.raju.androidrestapiexample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.raju.androidrestapiexample.interfaces.FlagAPI;
import com.raju.androidrestapiexample.model.Flag;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView flag;
    private EditText etCountryId;
    private TextView tvCountry;
    private FlagAPI flagAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        flagAPI = RetrofitHelper.instance().create(FlagAPI.class);

        etCountryId = findViewById(R.id.et_country_id);
        flag = findViewById(R.id.flag);
        tvCountry = findViewById(R.id.tvCountry);
    }

    public void fetchFlag(View view) {
        String id = etCountryId.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            etCountryId.setError("You must provide an ID");
            return;
        } else if (Integer.parseInt(id) < 1 || Integer.parseInt(id) > 35) {
            etCountryId.setError("ID must be between 1-35");
            return;
        }
        int countryId = Integer.parseInt(id);

        Call<Flag> flagCall = flagAPI.getFlag(countryId);
        flagCall.enqueue(new Callback<Flag>() {
            @Override
            public void onResponse(Call<Flag> call, Response<Flag> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code() + ": " + response.errorBody(), Toast.LENGTH_LONG).show();
                    return;
                }
                Flag responseFlag = response.body();
                tvCountry.setText(responseFlag.getCountry());
                String imageUri = "http://sujitg.com.np/wc/teams/" + responseFlag.getFile();
                Picasso.get().load(imageUri).into(flag);

                tvCountry.setVisibility(View.VISIBLE);
                flag.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchAllFlags(View view) {

        startActivity(new Intent(this, AllFlagsActivity.class));
    }
}
