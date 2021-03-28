package com.example.room.ui.fragment;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.example.room.OnItemClickListener;
import com.example.room.repository.db.Word;
import com.example.room.ui.adapter.WordListAdapter;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.viewmodel.factory.WordViewModelFactory;

public class CompleteListFragment extends BaseListFragment implements OnItemClickListener {
    @Override
    void setOnItemClickListener(WordListAdapter adapter) {
        adapter.setOnItemClickListener(this);
    }

    @Override
    void setOnTodoNameClickListener(WordListAdapter adapter) {
        adapter.setOnTodoNameClickListener(null);
    }

    @Override
    boolean getCompleteFlag() {
        return true;
    }

    @Override
    void setFabVisibility() {
        binding.fab.setVisibility(View.GONE);
    }

    @Override
    void setFabOnClickListener() {
        binding.fab.setOnClickListener(null);
    }

    @Override
    public void onCompleteFlagChange(Word word) {
        mWordViewModel.update(word);
    }
}
