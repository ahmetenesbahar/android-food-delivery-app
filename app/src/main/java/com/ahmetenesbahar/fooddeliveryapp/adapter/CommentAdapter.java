package com.ahmetenesbahar.fooddeliveryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetenesbahar.fooddeliveryapp.R;
import com.ahmetenesbahar.fooddeliveryapp.databinding.ItemContainerCommentBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Comment;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> items;

    public CommentAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerCommentBinding binding = ItemContainerCommentBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comment comment = (Comment) items.get(position).getObject();
        ((CommentViewHolder) holder).setCommentData(comment);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerCommentBinding binding;

        public CommentViewHolder(ItemContainerCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }


        void setCommentData(Comment comment) {
            Picasso.get().load(comment.getCommentImage()).into(binding.imageComment);
            binding.textViewUsername.setText(comment.getUserName());
            binding.textViewComment.setText(comment.getCommentText());
            binding.ratingBar.setRating(comment.getCommentRating());
        }
    }


}
