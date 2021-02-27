package com.example.roomwordsample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WordDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table_new")
    void deleteAll();

    @Query("DELETE FROM word_table_new WHERE id = (SELECT MAX(id) FROM word_table_new)")
    void undoLastEntry();

    @Query("SELECT * FROM word_table_new ORDER BY ankur ASC")
    LiveData<List<Word>> getAlphabetizedWords();

    @Query("SELECT * FROM word_table_new ORDER BY id DESC")
    LiveData<List<Word>> getAllWordsInNormalOrder();
}