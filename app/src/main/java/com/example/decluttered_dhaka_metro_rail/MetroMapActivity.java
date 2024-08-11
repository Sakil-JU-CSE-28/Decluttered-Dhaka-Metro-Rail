package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MetroMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MetroApiService metroApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_map);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backend.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        metroApiService = retrofit.create(MetroApiService.class);

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MetroMapActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fetchTrainLocations();
    }

    private void fetchTrainLocations() {
        metroApiService.getRealTimeLocations().enqueue(new Callback<List<Train>>() {
            @Override
            public void onResponse(Call<List<Train>> call, Response<List<Train>> response) {
                if (response.isSuccessful()) {
                    List<Train> trains = response.body();
                    updateMapWithTrainLocations(trains);
                }
            }

            @Override
            public void onFailure(Call<List<Train>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateMapWithTrainLocations(List<Train> trains) {
        mMap.clear(); // Clear existing markers

        for (Train train : trains) {
            LatLng trainLocation = new LatLng(train.getLatitude(), train.getLongitude());
            mMap.addMarker(new MarkerOptions().position(trainLocation).title("Train " + train.getTrainId()));
        }
    }
}