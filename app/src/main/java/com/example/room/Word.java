package com.example.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId = 0;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @ColumnInfo(name = "complete_flag")
    private boolean mCompleteFlag;

    public Word(@NonNull String word, boolean completeFlag) {
        this.mWord = word;
        mCompleteFlag = completeFlag;
    }

    public int getId(){
        return this.mId;
    }

    public String getWord(){
        return this.mWord;
    }

    public boolean getCompleteFlag(){
        return this.mCompleteFlag;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public void setCompleteFlag(boolean completeFlag) {
        mCompleteFlag = completeFlag;
    }
}
