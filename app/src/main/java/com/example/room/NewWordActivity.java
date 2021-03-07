package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.room.databinding.ActivityNewWordBinding;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;
    private ActivityNewWordBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewWordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        setContentView(R.layout.activity_new_word);
//        mEditWordView = findViewById(R.id.edit_word);
        mEditWordView = binding.editWord;

//        final Button button = findViewById(R.id.button_save);
        binding.buttonSave.setOnClickListener(buttonView -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditWordView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
