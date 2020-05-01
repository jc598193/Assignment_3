package com.example.assignment_3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighScores extends AppCompatActivity {

    private GridView score_table;
    private UserDatabase db;
    ArrayList<String> scores_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores_data);
        score_table.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
            Intent intent = new Intent(this, Game.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_scores) {
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<String> getArray(){
        score_table = findViewById(R.id.scores_table);
        db = new UserDatabase(this);

        List<UserScores> data = db.getAllTable();

        scores_data.add("ID");
        scores_data.add("NAME");
        scores_data.add("SCORE");

        for (UserScores u : data) {
            String log = "ID: " + u.getId() + ", Name: " + u.getName() + ", Score: " + u.getScore() + "\n";
            System.out.println(log);
//            String[] stringarray = {String.valueOf(u.getId()), u.getName(), String.valueOf(u.getScore())};
            scores_data.add(String.valueOf(u.getId()));
            scores_data.add(u.getName());
            scores_data.add(String.valueOf(u.getScore()));
        }
        System.out.println(scores_data);
        return scores_data;
    }
}
