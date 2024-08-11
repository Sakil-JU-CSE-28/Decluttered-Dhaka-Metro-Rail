package com.example.decluttered_dhaka_metro_rail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MetroApiService {
    @GET("trains/location")
    Call<List<Train>> getRealTimeLocations();
}
