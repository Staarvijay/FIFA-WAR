package com.example.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_ANKUR = "com.example.android.wordlistsql.REPLY_ANKUR";
    public static final String EXTRA_REPLY_ANKIT = "com.example.android.wordlistsql.REPLY_ANKIT";

    private EditText mEditWordView_ankur;
    private EditText mEditWordView_ankit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView_ankur = findViewById(R.id.edit_word_ankur);
        mEditWordView_ankit = findViewById(R.id.edit_word_ankit);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView_ankur.getText()) || TextUtils.isEmpty(mEditWordView_ankit.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Integer wordAnkur = Integer.parseInt(mEditWordView_ankur.getText().toString());
                Integer wordAnkit = Integer.parseInt(mEditWordView_ankit.getText().toString());
                replyIntent.putExtra(EXTRA_REPLY_ANKUR, wordAnkur);
                replyIntent.putExtra(EXTRA_REPLY_ANKIT, wordAnkit);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}