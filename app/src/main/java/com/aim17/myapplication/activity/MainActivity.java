package com.aim17.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import com.aim17.myapplication.model.Task;
import com.aim17.myapplication.adapter.TaskAdapter;

import com.aim17.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    TaskAdapter adapter;
    List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.taskListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        createTaskList();

        adapter = new TaskAdapter(taskList, R.layout.list_item);
        recyclerView.setAdapter(adapter);
    }

    private void createTaskList()
    {
        Task task1 = new Task();
        task1.setName("Do Homework");
        task1.setDetails("Do homework for Software Engineering");
        Task task2 = new Task();
        task2.setName("Do Nothing");
        task2.setDetails("Do nothing at all");
        Task task3 = new Task();
        task3.setName("Do Something");
        task3.setDetails("Do something in this time");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    public void addTask(View view)
    {
        final Task task = new Task();
        //task4.setName("Add Task Button Test");
        //task4.setDetails("Description");
        //taskList.add(task4);
        //adapter.notifyDataSetChanged();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Task");

    // Set up the input
        final EditText input = new EditText(this);
        final EditText input1 = new EditText(this);

        LinearLayout alertLayout = new LinearLayout(this);
        alertLayout.setOrientation(LinearLayout.VERTICAL);
        alertLayout.addView(input);
        alertLayout.addView(input1);
        builder.setView(alertLayout);

    // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskTitle = input.getText().toString();
                String taskDesc = input1.getText().toString();
                task.setName(taskTitle);
                task.setDetails(taskDesc);
                taskList.add(task);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }
}
