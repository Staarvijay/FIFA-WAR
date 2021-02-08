package com.example.roomwordsample;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "word_table_new")

public class Word {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer mid;

    public void setMid(Integer mid) {
        this.mid = mid;
    }
    public Integer getMid() { return mid; }

    @NonNull
    @ColumnInfo(name = "ankur")
    private Integer mWord_ankur;

    @NonNull
    @ColumnInfo(name = "ankit")
    private Integer mWord_ankit;

    public Word(@NonNull Integer mWord_ankur, @NonNull Integer mWord_ankit) {
        this.mWord_ankur = mWord_ankur;
        this.mWord_ankit = mWord_ankit;
    }


    @NonNull
    public Integer getMWord_ankur() {
        return mWord_ankur;
    }

    @NonNull
    public Integer getMWord_ankit() {
        return mWord_ankit;
    }

    public String getWord(){return "Ankur " + getMWord_ankur() + " - " + getMWord_ankit() + " Ankit";}
}
