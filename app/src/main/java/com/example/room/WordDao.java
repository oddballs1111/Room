package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Update
    void update(Word word);

    @Query("SELECT * FROM word_table where complete_flag = :completeFlag ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords(boolean completeFlag);
}
