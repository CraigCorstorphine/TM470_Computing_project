package com.craigcorstorphine.tm470_computing_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button taskButton = findViewById(R.id.TaskList);
        Button mathButton = findViewById(R.id.button2);
        Button otherButton = findViewById(R.id.button3);

        taskButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent n = new Intent(MenuActivity.this,
                        MainTaskActivity.class);
                startActivity(n);
            }
        });

        mathButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent n = new Intent(MenuActivity.this, MathQuiz.class);
                startActivity(n);
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent n = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(n);
            }
        });
    }


}
