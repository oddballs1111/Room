package com.example.room;

import android.app.Notification;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.databinding.RecyclerviewItemBinding;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;
    private final CheckBox mCompleteCheckBox;

    private WordViewHolder(RecyclerviewItemBinding binding) {
        super(binding.getRoot());
        wordItemView = binding.textView;
        mCompleteCheckBox = binding.completeCheckBox;
    }

    public void bind(String text, boolean completeFlag) {
        wordItemView.setText(text);
        mCompleteCheckBox.setChecked(completeFlag);
    }

    static WordViewHolder create(ViewGroup parent) {
        RecyclerviewItemBinding binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WordViewHolder(binding);
    }

    public CheckBox getCompleteCheckBox() {
        return mCompleteCheckBox;
    }

    public TextView getTodoTextView() {
        return wordItemView;
    }
}
