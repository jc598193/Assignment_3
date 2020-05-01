package com.example.assignment_3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FirstLandingFragment extends Fragment {

    private SharedPreferences preferences;
    private EditText name;
    private String name_st;
    private UserDatabase db;
    private TextView status;
    private List<String> list_names = new ArrayList<>();
    private boolean newuser;
    private Button login;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_landing_first, container, false);

        status = v.findViewById(R.id.status);
        login = v.findViewById(R.id.login);


        // remove score data in sharedpreferences.
        preferences = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        preferences.edit().remove("score").apply();

        // Access UserDatabase
        db = new UserDatabase(getActivity());
//        db.deleteScore("2");
        List<UserScores> data = db.getAllTable();

        // Testing database
        for (UserScores u : data){
            String log = "ID: " + u.getId() + ", Name: " + u.getName() + ", Score: " + u.getScore() + "\n";
            System.out.println(log);
            list_names.add(u.getName());
            System.out.println(list_names);
        }


        // Working with EditText name
        name = v.findViewById(R.id.name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name_st = String.valueOf(name.getText());
                if (name_st.equals("")){      // check if name is blank
                    status.setText("Name can not be blank");
                }else{
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            preferences.edit()
                                    .putString("name", name_st)
                                    .putBoolean("new_user", newuser)
                                    .apply();
                            NavHostFragment.findNavController(FirstLandingFragment.this)
                                    .navigate(R.id.action_First4Fragment_to_Second4Fragment);
                        }
                    });
                    if (!list_names.contains(name_st)){
                        status.setText("Name is available");
                        newuser = true;
                    }else{
                        status.setText("Name is taken. Press confirm if it is your.");
                        newuser = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });



        return v;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // check blank when start
        if (name_st == null) {
            status.setText("Name can not be blank");

        }
    }

    @Override
    public void onPause() {
        super.onPause();

        db = new UserDatabase(getActivity());
        // Save new user
        if (newuser){
            db.addUserScores(new UserScores(name_st, 0));
        }

    }
}
