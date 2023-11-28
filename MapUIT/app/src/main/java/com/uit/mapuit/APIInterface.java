package com.uit.mapuit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("api/master/map")
    Call<MapModel> getMap();
}
