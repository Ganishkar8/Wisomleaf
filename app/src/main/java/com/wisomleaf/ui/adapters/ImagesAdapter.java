package com.wisomleaf.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wisomleaf.databinding.ImageRecyclerBinding;
import com.wisomleaf.ui.model.ImageModel;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewholder>{

    private Context context;
    private ArrayList<ImageModel> imageList;
    private View.OnClickListener listener;

    public ImagesAdapter(Context context,ArrayList<ImageModel> imageList, View.OnClickListener listener) {
        this.context = context;
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImagesAdapter.ImageViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImagesAdapter.ImageViewholder(ImageRecyclerBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ImageViewholder holder, int position) {
        holder.binding.tvTitle.setText(imageList.get(position).getAuthor());
        holder.binding.tvDesc.setText("This book was written by "+imageList.get(position).getAuthor());
        if (imageList.get(position).getDownloadUrl() != null) {
            String imageUrl = imageList.get(position).getDownloadUrl()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.binding.imgLogo);
        }
        holder.binding.lnImages.setTag(imageList.get(position));
        holder.binding.lnImages.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setResults(ArrayList<ImageModel> results) {
        this.imageList.addAll(results);
        notifyDataSetChanged();
    }

    public void deleteResults() {
        this.imageList.clear();
        notifyDataSetChanged();
    }

    public static class ImageViewholder extends RecyclerView.ViewHolder {
        ImageRecyclerBinding binding;

        public ImageViewholder(ImageRecyclerBinding b) {
            super(b.getRoot());
            binding = b;
        }

    }
    }
