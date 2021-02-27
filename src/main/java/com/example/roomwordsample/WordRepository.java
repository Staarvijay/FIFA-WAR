package com.example.roomwordsample;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Word>> nAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWordsInNormalOrder();

        nAllWords = mWordDao.isEntityEmptyOrNot();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }

    public void undoLastEntry() {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.undoLastEntry();
        });
    }

//    LiveData<List<Word>> listofword = nAllWords;
    public boolean isEntityEmptyOrNot(LiveData<List<Word>> listofword){
        if(listofword.getValue() == null){
            Log.i("info","this is wordrepo jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            return true;
        }
        else{
            return false;
        }
    }

}
