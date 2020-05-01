package com.example.assignment_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class SecondLandingFragment extends Fragment {

    private Button play;
    private TextView welcome;

    SharedPreferences preferences;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_landing_second, container, false);
        // Inflate the layout for this fragment

        preferences = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        String name = preferences.getString("name", "");

        welcome = v.findViewById(R.id.welcome);
        boolean new_user = preferences.getBoolean("new_user", true);

        if (new_user){
            welcome.setText("Welcome new player");
        }else{
            welcome.setText("Welcome back, "+ name);
        }


        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        play = view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Game.class);
                startActivity(intent1);

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Clear new_user data
        preferences = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        preferences.edit().remove("new_user").apply();
    }
}
