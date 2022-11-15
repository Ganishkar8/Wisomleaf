package com.wisomleaf.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wisomleaf.R;
import com.wisomleaf.databinding.ActivityMainBinding;
import com.wisomleaf.ui.adapters.ImagesAdapter;
import com.wisomleaf.ui.model.ImageModel;
import com.wisomleaf.ui.viewmodel.ImagesViewModel;
import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ImagesViewModel imagesViewModel;
    private ActivityMainBinding binding;
    private ImagesAdapter imagesAdapter;
    ArrayList<ImageModel> model = new ArrayList<>();
    int page=0,limit=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        imagesViewModel = new ViewModelProvider(this).get(ImagesViewModel.class);
        imagesViewModel.init();
        imagesViewModel.getImages(String.valueOf(page),String.valueOf(limit));
        binding.idPBLoading.setVisibility(View.VISIBLE);
        imagesAdapter = new ImagesAdapter(getApplicationContext(),model,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.imgRecycler.setLayoutManager(layoutManager);
        binding.imgRecycler.setAdapter(imagesAdapter);
        imagesViewModel.getImagesResponseLiveData().observe(this, new Observer<ArrayList<ImageModel>>() {
            @Override
            public void onChanged(ArrayList<ImageModel> volumesResponse) {
                if (volumesResponse != null) {
                    binding.idPBLoading.setVisibility(View.GONE);
                    imagesAdapter.setResults(volumesResponse);
                    binding.swipeToRefresh.setRefreshing(false);
                    Log.d("Response",volumesResponse.get(0).getDownloadUrl());
                }
            }
        });

        binding.idNestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page=++page;
                    imagesViewModel.getImages(String.valueOf(page),String.valueOf(limit));
                    binding.idPBLoading.setVisibility(View.VISIBLE);
                    binding.swipeToRefresh.setRefreshing(false);
                }
            }
        });

        binding.swipeToRefresh.setOnRefreshListener(this);
        binding.swipeToRefresh.setColorSchemeResources(android.R.color.holo_red_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ln_images:
                ImageModel model = (ImageModel) v.getTag();
                descAlert("This book was written by "+model.getAuthor());
                break;

            default:
                break;
        }

    }

    @Override
    public void onRefresh() {
        imagesAdapter.deleteResults();
        page = 0;
        imagesViewModel.getImages(String.valueOf(page),String.valueOf(limit));
        binding.idPBLoading.setVisibility(View.VISIBLE);

    }

    private void descAlert(String desc){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(desc);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}