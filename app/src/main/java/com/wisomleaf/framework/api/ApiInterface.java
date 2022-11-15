package com.wisomleaf.framework.api;

import com.wisomleaf.ui.model.ImageModel;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET("/v2/list?")
    Call<ArrayList<ImageModel>> getImages(
            @Query("page") String page,
            @Query("limit") String author
    );
}
