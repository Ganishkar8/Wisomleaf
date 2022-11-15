package com.wisomleaf.ui.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wisomleaf.framework.api.Api;
import com.wisomleaf.framework.api.ApiInterface;
import com.wisomleaf.ui.model.ImageModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesRepository {

    private MutableLiveData<ArrayList<ImageModel>> imageResponseLiveData;

    public ImagesRepository() {
        imageResponseLiveData = new MutableLiveData<>();
    }

    public void getImages(String page, String limit) {

        ApiInterface apiInterface ;
        apiInterface  = Api.getClient();

        Call<ArrayList<ImageModel>> call = apiInterface.getImages(page,limit);
        call.enqueue(new Callback<ArrayList<ImageModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageModel>> call, Response<ArrayList<ImageModel>> response) {
                Log.d("TAG","response.code()"+"");

                if (response.body() != null)
                imageResponseLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<ImageModel>> call, Throwable t) {
                imageResponseLiveData.postValue(null);
                Log.d("TAG",t.getMessage()+"");
            }
        });

    }

    public LiveData<ArrayList<ImageModel>> getImagesResponseLiveData() {
        return imageResponseLiveData;
    }

}
