package com.craigcorstorphine.tm470_computing_project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KudoScore extends AppCompatActivity {

    int kudos;
    TextView numberKudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kudos_layout);

        kudos = 0;

        TextView numberKudo = this.findViewById(R.id.editTextNumber);
        numberKudo.setText(kudos);
    }

}
