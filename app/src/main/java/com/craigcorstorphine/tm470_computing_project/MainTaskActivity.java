package com.craigcorstorphine.tm470_computing_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.craigcorstorphine.tm470_computing_project.Adapter.TaskAdapter;
import com.craigcorstorphine.tm470_computing_project.Model.TaskModel;
import com.craigcorstorphine.tm470_computing_project.Utils.DatabaseHelperTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainTaskActivity extends AppCompatActivity implements DialogCloseListner{

    private DatabaseHelperTask db;

    private RecyclerView tasksRecyclerView;
    private TaskAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<TaskModel> taskList;

    private int kudos;
    private Button kudoIncrement, kudoDecrement;
    TextView tV;
    private int count;

    private SharedPreferences kudoCounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Objects.requireNonNull(getSupportActionBar()).hide();
        kudoCounter = getApplicationContext().getSharedPreferences("com.craigcorstorphine.tm470_computing_project", Context.MODE_PRIVATE);

        //start the value of greed from what it was earlier.
        count = kudoCounter.getInt("count",0);




        db = new DatabaseHelperTask(this);
        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TaskAdapter(db, MainTaskActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });

        kudoIncrement = findViewById(R.id.increment); kudoDecrement = findViewById(R.id.decrement);
        tV = findViewById(R.id.textView2);

        kudoIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //update and print the value of greed everytime you increment it:
                kudoCounter.edit().putInt("count", count).apply();
                Log.i("Test: ", Integer.toString(kudoCounter.getInt("count", count)));
                count++;
                tV.setText(count +" Kudos!");

            }
        });
        kudoDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                //update and print the value of greed everytime you increment it:
                kudoCounter.edit().putInt("count", count).apply();
                Log.i("Test: ", Integer.toString(kudoCounter.getInt("count", count)));
                count--;
                tV.setText(count + "Kudos!");

            }
        });


    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }






}
