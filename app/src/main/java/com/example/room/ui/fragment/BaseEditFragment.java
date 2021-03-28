package com.example.room.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.room.R;
import com.example.room.databinding.FragmentEditWordBinding;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.viewmodel.factory.WordViewModelFactory;

abstract class BaseEditFragment extends Fragment {
    private static final String TAG = "NewWordFragment";
    protected EditText mEditWordView;
    protected FragmentEditWordBinding binding;
    protected WordViewModel mWordViewModel;

    abstract void setEditWord();
    abstract void insertOrUpdateWord(String string);
    abstract void navigationAction(View view);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentEditWordBinding.inflate(inflater, container, false);
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
        setEditWord();

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
        String data = mEditWordView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            //失敗トースト表示
            Toast.makeText(
                    requireContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            //IME閉じる
            InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(binding.editWord.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

            insertOrUpdateWord(data);

            //画面遷移
            navigationAction(buttonView);

        }
    }

}
