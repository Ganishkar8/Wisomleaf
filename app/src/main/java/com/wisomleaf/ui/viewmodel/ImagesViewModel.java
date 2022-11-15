package com.wisomleaf.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wisomleaf.ui.model.ImageModel;
import com.wisomleaf.ui.repository.ImagesRepository;

import java.util.ArrayList;

public class ImagesViewModel extends AndroidViewModel {
    private ImagesRepository bookRepository;
    private LiveData<ArrayList<ImageModel>> volumesResponseLiveData;

    public ImagesViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new ImagesRepository();
        volumesResponseLiveData = bookRepository.getImagesResponseLiveData();
    }

    public void getImages(String page, String limit) {
        bookRepository.getImages(page, limit);
    }

    public LiveData<ArrayList<ImageModel>> getImagesResponseLiveData() {
        return volumesResponseLiveData;
    }
}

