package com.example.room.repository.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id = 0;

    @NonNull
    @ColumnInfo(name = "word")
    public String word;

    @ColumnInfo(name = "complete_flag")
    public boolean completeFlag;

    public Word(@NonNull String word, boolean completeFlag) {
        this.word = word;
        this.completeFlag = completeFlag;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    /**
     * Fragment間のArgumentにいれるため、Parcelable処理を投入
     * @param in
     */
    private Word(Parcel in) {
        id = in.readInt();
        word = in.readString();
        completeFlag = in.readByte() != 0;
    }

    /**
     * Fragment間のArgumentにいれるため、Parcelable処理を投入
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Fragment間のArgumentにいれるため、Parcelable処理を投入
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeByte((byte) (completeFlag ? 1 : 0));
    }
}
