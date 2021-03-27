package com.example.room;

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

    /**
     * Fragment間のArgumentにいれるため、Parcelable処理を投入
     * @param in
     */
    private Word(Parcel in) {
        mId = in.readInt();
        mWord = in.readString();
        mCompleteFlag = in.readByte() != 0;
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
        dest.writeInt(mId);
        dest.writeString(mWord);
        dest.writeByte((byte) (mCompleteFlag ? 1 : 0));
    }
}
