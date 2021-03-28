package com.example.room.repository.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "todo_children_table",
        foreignKeys = @ForeignKey(
                entity=Word.class,
        parentColumns = "id",
        childColumns = "word_id",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE))
public class ChildrenWord extends Word {

    @ColumnInfo(name = "word_id")
    public int wordId;

    public ChildrenWord(@NonNull String word, boolean completeFlag) {
        super(word, completeFlag);
    }
}
