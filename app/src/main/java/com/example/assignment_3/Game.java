package com.example.assignment_3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    private QuestionDatabase data;
    SharedPreferences preferences;
    private TextView score, question;
    private Button answer1, answer2, answer3, answer4;
    private ImageView image;
    private int mScore;
    private int  question_number;
    private String correct_answer;
    UserDatabase userDatabase;
    MediaPlayer correct_sound, wrong_sound;
    boolean open_sound, correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        score = findViewById(R.id.scores);
        question = findViewById(R.id.question);
        image = findViewById(R.id.image_q);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        updateQuestion();


        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        int saved_score = preferences.getInt("score", 0);
        score.setText(String.valueOf(saved_score));






        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (answer1.getText() == correct_answer){
                correct = true;
                controlSound();
                Toast.makeText(Game.this, "correct", Toast.LENGTH_SHORT).show();
                mScore = mScore + 1;
                updateScore(mScore);
                updateQuestion();

            }else{
                Toast.makeText(Game.this, "incorrect", Toast.LENGTH_SHORT).show();
                correct = false;
                controlSound();
                updateQuestion();
            }
            }
        });


        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (answer2.getText() == correct_answer){
                Toast.makeText(Game.this, "correct", Toast.LENGTH_SHORT).show();
                correct =true;
                controlSound();
                mScore = mScore + 1;
                updateScore(mScore);
                updateQuestion();

            }else{
                Toast.makeText(Game.this, "incorrect", Toast.LENGTH_SHORT).show();
                correct = false;
                controlSound();
                updateQuestion();
            }
            }
        });


        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (answer3.getText() == correct_answer){
                Toast.makeText(Game.this, "correct", Toast.LENGTH_SHORT).show();
                correct = true;
                controlSound();
                mScore = mScore + 1;
                updateScore(mScore);
                updateQuestion();

            }else{
                Toast.makeText(Game.this, "incorrect", Toast.LENGTH_SHORT).show();
                correct = false;
                controlSound();
                updateQuestion();
            }
            }
        });


        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (answer4.getText() == correct_answer){
                Toast.makeText(Game.this, "correct", Toast.LENGTH_SHORT).show();
                correct = true;
                controlSound();
                mScore = mScore + 1;
                updateScore(mScore);
                updateQuestion();

            }else{
                Toast.makeText(Game.this, "incorrect", Toast.LENGTH_SHORT).show();
                correct = false;
                controlSound();
                updateQuestion();
            }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Menu controller
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_game){
            return true;
        }else if(id == R.id.action_scores) {
            updateUserScore();
            Intent intent = new Intent(this, HighScores.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    // Question Controller
    private void updateQuestion(){
        data = new QuestionDatabase();
        if (question_number < 10){
            question.setText(data.getQuestion(question_number));
            image.setImageResource(data.getImage(question_number));
            answer1.setText(data.getAnswer1(question_number));
            answer2.setText(data.getAnswer2(question_number));
            answer3.setText(data.getAnswer3(question_number));
            answer4.setText(data.getAnswer4(question_number));
            correct_answer = data.getCorrect(question_number);
            question_number ++;
        }else{
            finishedAlert();
        }

    }

    // Score update
    private void updateScore(int point){
        score.setText(String.valueOf(point));
    }


    // Update Score in Database
    void updateUserScore() {
        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        String name = preferences.getString("name", "");
        System.out.println(name);
        userDatabase = new UserDatabase(this);
        UserScores user_data = userDatabase.getScore(name);
        user_data.setScore(mScore);
        userDatabase.updateScore(user_data);
    }


    // Dialog when question is running out
    void finishedAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Game.this);
        builder1.setMessage("You are completed the game. Do you want to play again?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                        startActivity(getIntent());
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(Game.this, LandingPage.class);
                        startActivity(intent);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    // Play correct or wrong sound
    void controlSound(){
        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        open_sound = preferences.getBoolean("play_sound", true);

        if (open_sound) {
            correct_sound = MediaPlayer.create(getApplicationContext(), R.raw.correct_effect);
            wrong_sound = MediaPlayer.create(getApplicationContext(), R.raw.wrong_effect);
            if (correct){
                correct_sound.start();
            }else{
                wrong_sound.start();
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        preferences.edit().putInt("score", mScore).apply();
    }
}
