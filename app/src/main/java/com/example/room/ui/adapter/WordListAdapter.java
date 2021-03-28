package com.example.room.ui.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.room.OnItemClickListener;
import com.example.room.OnTodoNameClickListener;
import com.example.room.repository.db.Word;
import com.example.room.viewmodel.viewholder.WordViewHolder;

public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {
    private final String TAG = "WordListAdapter";
    private OnItemClickListener mOnItemClickListener = null;
    private OnTodoNameClickListener mTodoNameClickListener = null;

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnTodoNameClickListener(OnTodoNameClickListener onTodoNameClickListener) {
        mTodoNameClickListener = onTodoNameClickListener;
    }
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.word, current.completeFlag);

        CheckBox completeCheckBox = holder.getCompleteCheckBox();
        completeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "buttonView = " + buttonView + "isChecked = " + isChecked);
                completeCheckBox.setOnCheckedChangeListener(null);
                current.completeFlag = isChecked;

                //押下された事をもってDBの更新処理を行う
                mOnItemClickListener.onCompleteFlagChange(current);
            }
        });

        TextView todoName = holder.getTodoTextView();
        todoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextViewのクリックによりTodo名称の変更処理を行う
                if(mTodoNameClickListener != null) {
                    mTodoNameClickListener.onClick(v, current);
                }
            }
        });
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.word.equals(newItem.word);
        }
    }
}
