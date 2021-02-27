package com.example.roomwordsample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    Button undoButton;

    int ankurRedoData = 0;
    int ankitRedoData = 0;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ankurRedoData = data.getIntExtra(NewWordActivity.EXTRA_REPLY_ANKUR, 0);
            ankitRedoData = data.getIntExtra(NewWordActivity.EXTRA_REPLY_ANKIT, 0);
            Word word = new Word(data.getIntExtra(NewWordActivity.EXTRA_REPLY_ANKUR, 0), data.getIntExtra(NewWordActivity.EXTRA_REPLY_ANKIT, 0));
            mWordViewModel.insert(word);
            undoButton.setVisibility(View.VISIBLE);


        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button redoButton = findViewById(R.id.redoButton);
        redoButton.setVisibility(View.INVISIBLE);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        undoButton = findViewById(R.id.undoButton);
        undoButton.setVisibility(View.INVISIBLE);
        undoButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(MainActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Are you sure wanna UNDO?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        mWordViewModel.undoLastEntry();

                                        redoButton.setVisibility(View.VISIBLE);
                                        redoButton.postDelayed(new Runnable() {
                                            public void run() {
                                                redoButton.setVisibility(View.INVISIBLE);
                                            }
                                        }, 7000);

                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();
                    }}
        );


        redoButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Word word = new Word(ankurRedoData, ankitRedoData);
                        mWordViewModel.insert(word);
                        redoButton.setVisibility(View.INVISIBLE);

                    }}
        );

    }
}