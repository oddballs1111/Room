package com.example.room;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {
    private final String TAG = "WordListAdapter";
    private OnItemClickListener mOnItemClickLisetner;

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    public void setmOnItemClickLisetner(OnItemClickListener onItemClickLisetner) {
        mOnItemClickLisetner = onItemClickLisetner;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord(), current.getCompleteFlag());

        CheckBox completeCheckBox = holder.getCompleteCheckBox();
        completeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "buttonView = " + buttonView + "isChecked = " + isChecked);
                completeCheckBox.setOnCheckedChangeListener(null);
                current.setCompleteFlag(isChecked);
                //押下された事をもってDBの更新処理を行う
                mOnItemClickLisetner.onCompleteFlagChange(current);
            }
        });
    }

    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }
}
