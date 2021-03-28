package com.example.room;

import android.view.View;

import com.example.room.repository.db.Word;

public interface OnTodoNameClickListener {
    public void onClick(View view, Word word);
}
