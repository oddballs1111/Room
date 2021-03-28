package com.example.room.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.room.R;
import com.example.room.databinding.FragmentEditWordBinding;
import com.example.room.repository.db.Word;

import java.util.concurrent.atomic.AtomicReference;

public class ExistEditWordFragment extends BaseEditFragment {
    @Override
    void setEditWord() {
        Word todoWord = ExistEditWordFragmentArgs.fromBundle(getArguments()).getTodoWord();
        if(todoWord != null) {
            Log.d("TAG", "todoName = " + todoWord.word);
            mEditWordView.setText(todoWord.word);
        }
    }

    @Override
    void insertOrUpdateWord(String data) {
        AtomicReference<Word> todoWord = new AtomicReference<>(ExistEditWordFragmentArgs.fromBundle(getArguments()).getTodoWord());
        //WordがArgumentに入っている場合はUpdate処理
        todoWord.get().word = data;
        mWordViewModel.update(todoWord.get());
    }

    @Override
    void navigationAction(View view) {
        Navigation.findNavController(view).navigate(R.id.action_existEditWordFragment_to_mainFragment);
    }
}
