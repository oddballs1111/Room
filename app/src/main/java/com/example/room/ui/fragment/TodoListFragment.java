package com.example.room.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.room.OnItemClickListener;
import com.example.room.OnTodoNameClickListener;
import com.example.room.R;
import com.example.room.repository.db.Word;
import com.example.room.ui.adapter.WordListAdapter;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.viewmodel.factory.WordViewModelFactory;

public class TodoListFragment extends BaseListFragment implements OnItemClickListener, OnTodoNameClickListener {
    private final static String TAG = "TodoListFragment";

    @Override
    void setOnItemClickListener(WordListAdapter adapter) {
        adapter.setOnItemClickListener(this);
    }

    @Override
    void setOnTodoNameClickListener(WordListAdapter adapter) {
        adapter.setOnTodoNameClickListener(this);
    }

    @Override
    boolean getCompleteFlag() {
        return false;
    }

    @Override
    void setFabVisibility() {
        binding.fab.setVisibility(View.VISIBLE);
    }

    @Override
    void setFabOnClickListener() {
        binding.fab.setOnClickListener( fabView -> {
            Navigation.findNavController(fabView).navigate(R.id.action_mainFragment_to_newWordFragment);
        });

    }

    @Override
    public void onCompleteFlagChange(Word word) {
        mWordViewModel.update(word);
    }

    @Override
    public void onClick(View view, Word word) {
        Log.d(TAG, "view = " + view);
        //更新対象のwordを渡す
        TodoListFragmentDirections.ActionMainFragmentToExistEditWordFragment action =
                TodoListFragmentDirections.actionMainFragmentToExistEditWordFragment();
        action.setTodoWord(word);
        Navigation.findNavController(view).navigate(action);
    }
}
