package com.example.room.viewmodel.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

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

    public static WordViewHolder create(ViewGroup parent) {
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
