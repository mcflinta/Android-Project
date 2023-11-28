package com.uit.mapuit;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIManager {
    private static final APIClient apiClient = new APIClient();
    private static final APIInterface getMap = apiClient.getClient().create(APIInterface.class);
    public static void getMap() {
        Call<MapModel> call = getMap.getMap();
        call.enqueue(new Callback<MapModel>() {
            @Override
            public void onResponse(Call<MapModel> call, Response<MapModel> response) {
                if (response.isSuccessful())
                {
                    MapModel.setMapObj(response.body());
                    Log.d("API CALL", "Call Map Success ");
                }
                else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API CALL", "Error response: " + errorBody);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MapModel> call, Throwable t) {
                Log.e("Retrofit", "Network error: " + t.getMessage());
            }
        });
    }
}
