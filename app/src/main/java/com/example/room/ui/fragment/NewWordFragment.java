package com.example.room.ui.fragment;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.room.R;
import com.example.room.repository.db.Word;

public class NewWordFragment extends BaseEditFragment {

    @Override
    void setEditWord() {
        /* Do Nothing. */
    }

    @Override
    void insertOrUpdateWord(String data) {
        Word newWord = new Word(data, false);
        mWordViewModel.insert(newWord);
    }

    @Override
    void navigationAction(View view) {
        Navigation.findNavController(view).navigate(R.id.action_newWordFragment_to_mainFragment);
    }
}
