package com.raju.androidrestapiexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raju.androidrestapiexample.adapter.FlagsAdapter;
import com.raju.androidrestapiexample.interfaces.FlagAPI;
import com.raju.androidrestapiexample.model.Flag;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFlagsActivity extends AppCompatActivity {

    private FlagAPI flagAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_flags);

        init();
    }

    private void init() {
        flagAPI = RetrofitHelper.instance().create(FlagAPI.class);

        Call<List<Flag>> flagsCall = flagAPI.getAllFlags();
        flagsCall.enqueue(new Callback<List<Flag>>() {
            @Override
            public void onResponse(Call<List<Flag>> call, Response<List<Flag>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AllFlagsActivity.this, response.code() + ": " + response.errorBody(), Toast.LENGTH_LONG).show();
                    return;
                }

                for (Flag flag : response.body()) {
                    Log.i("FLAG", flag.getCountry());
                }

                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new FlagsAdapter(AllFlagsActivity.this, response.body()));
                recyclerView.setLayoutManager(new LinearLayoutManager(AllFlagsActivity.this));

            }

            @Override
            public void onFailure(Call<List<Flag>> call, Throwable t) {
                Toast.makeText(AllFlagsActivity.this, "ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
