package com.raju.androidrestapiexample.interfaces;

import com.raju.androidrestapiexample.model.Flag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FlagAPI {

    @GET("singleflag/{id}")
    Call<Flag> getFlag(@Path("id") int id);

    @GET("flags")
    Call<List<Flag>> getAllFlags();

}
