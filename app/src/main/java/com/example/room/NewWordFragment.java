package com.example.room;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.room.databinding.FragmentNewWordBinding;

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
        mEditWordView = binding.editWord;

        WordViewModelFactory factory = new WordViewModelFactory(requireActivity().getApplication(), false);
        mWordViewModel = new ViewModelProvider(this, factory).get(WordViewModel.class);
        binding.buttonSave.setOnClickListener(buttonView -> {
            Intent replyIntent = new Intent();
            String data = mEditWordView.getText().toString();
            Log.d(TAG, data);
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                //失敗トースト表示
                Toast.makeText(
                        requireContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            } else {
                //保存処理
                Word word = new Word(data, false);
                mWordViewModel.insert(word);

                //IME閉じる
                InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(binding.editWord.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

                //画面遷移
                Navigation.findNavController(buttonView).navigate(R.id.action_newWordFragment_to_mainFragment);
//                FragmentManager fragmentManager = requireFragmentManager();
//                fragmentManager.popBackStack();

            }
        });

        return layoutView;
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
}
