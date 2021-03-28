package com.example.room.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.room.R;
import com.example.room.repository.db.Word;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.viewmodel.factory.WordViewModelFactory;
import com.example.room.databinding.FragmentNewWordBinding;

import java.util.concurrent.atomic.AtomicReference;

public class NewWordFragment extends Fragment {
    private static final String TAG = "NewWordFragment";
    private EditText mEditWordView;
    private FragmentNewWordBinding binding;
    private WordViewModel mWordViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNewWordBinding.inflate(inflater, container, false);
        View layoutView = binding.getRoot();

        WordViewModelFactory factory = new WordViewModelFactory(requireActivity().getApplication(), false);
        mWordViewModel = new ViewModelProvider(requireActivity(), factory).get(WordViewModel.class);
        binding.buttonSave.setOnClickListener(this::onClick);

        return layoutView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditWordView = binding.editWord;
        Word todoWord = NewWordFragmentArgs.fromBundle(getArguments()).getTodoWord();
        if(todoWord != null) {
            Log.d("TAG", "todoName = " + todoWord.word);
            mEditWordView.setText(todoWord.word);
        }

    }
    @Override
    public void onStart() {
        super.onStart();

        //IMEを表示
        binding.editWord.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(binding.editWord, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClick(View buttonView) {
        Intent replyIntent = new Intent();
        String data = mEditWordView.getText().toString();
        Log.d(TAG, data);
        if (TextUtils.isEmpty(data)) {
            //失敗トースト表示
            Toast.makeText(
                    requireContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            AtomicReference<Word> todoWord = new AtomicReference<>(NewWordFragmentArgs.fromBundle(getArguments()).getTodoWord());
            if (todoWord.get() == null) {
                //WordがArgumentに入ってない場合はinsert処理
                todoWord.set(new Word(data, false));
                mWordViewModel.insert(todoWord.get());
            } else {
                //WordがArgumentに入っている場合はUpdate処理
                todoWord.get().word = data;
                mWordViewModel.update(todoWord.get());
            }

            //IME閉じる
            InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(binding.editWord.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

            //画面遷移
            Navigation.findNavController(buttonView).navigate(R.id.action_newWordFragment_to_mainFragment);

        }
    }
}
