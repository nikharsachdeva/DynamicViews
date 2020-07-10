package com.nikharsachdeva.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    List<String> teamList = new ArrayList<>();
    ArrayList<CricketerModel> cricketerModelArrayList = new ArrayList<>();
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmit = findViewById(R.id.button_submit_list);


        buttonAdd.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        teamList.add("Teams");
        teamList.add("India");
        teamList.add("Australia");
        teamList.add("England");


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_add:
                addView();

                break;

            case R.id.button_submit_list:
                if (checkIfValidAndRead()) {
                    Intent intent = new Intent(MainActivity.this,Cricketers.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",cricketerModelArrayList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                break;
        }
    }

    private boolean checkIfValidAndRead() {
        cricketerModelArrayList.clear();

        boolean result = true;

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View cricketerView = layoutList.getChildAt(i);
            EditText editTextName = cricketerView.findViewById(R.id.edit_cricketer_name);
            AppCompatSpinner spinnerTeams = cricketerView.findViewById(R.id.spinner_team);

            CricketerModel cricketerModel = new CricketerModel();

            if (!editTextName.getText().toString().equals("")) {
                cricketerModel.setCricketerName(editTextName.getText().toString());
            } else {
                result = false;
                break;
            }

            if (spinnerTeams.getSelectedItemPosition() != 0) {
                cricketerModel.setTeamName(teamList.get(spinnerTeams.getSelectedItemPosition()));
            } else {
                result = false;
                break;
            }

            cricketerModelArrayList.add(cricketerModel);


        }

        if (cricketerModelArrayList.size() == 0) {
            result = false;
            Toast.makeText(this, "Add Cricketers First", Toast.LENGTH_SHORT).show();
        } else if (!result) {
            Toast.makeText(this, "Enter all details correctly", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private void addView() {

        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_cricketer, null, false);

        EditText editText = cricketerView.findViewById(R.id.edit_cricketer_name);
        ImageView imageClose = cricketerView.findViewById(R.id.image_remove);
        AppCompatSpinner spinnerTeams = cricketerView.findViewById(R.id.spinner_team);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, teamList);
        spinnerTeams.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);

    }

    private void removeView(View view) {
        layoutList.removeView(view);
    }
}