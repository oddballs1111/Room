package com.example.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.room.databinding.ActivityNewWordBinding;
import com.example.room.databinding.RecyclerviewItemBinding;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private WordViewHolder(RecyclerviewItemBinding binding) {
        super(binding.getRoot());
        wordItemView = binding.textView;
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static WordViewHolder create(ViewGroup parent) {
        RecyclerviewItemBinding binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WordViewHolder(binding);
    }
}
