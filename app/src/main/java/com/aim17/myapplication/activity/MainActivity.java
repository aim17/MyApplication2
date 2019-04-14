package com.aim17.myapplication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    // Function solely for creating test list and debugging code
    private void createTaskList()
    {
        Log.d("todoapp", "CreateTaskList");
        List<Task> testList = new ArrayList<>();

        Task task1 = new Task();
        task1.setName("Do Homework");
        task1.setDetails("Do homework for Software Engineering");
        Task task2 = new Task();
        task2.setName("Do Nothing");
        task2.setDetails("Do nothing at all");
        Task task3 = new Task();
        task3.setName("Do Something");
        task3.setDetails("Do something in this time");

        testList.add(task1);
        testList.add(task2);
        testList.add(task3);


        // The rest here is for load and save task debugging
        Log.d("createTaskList", "Test print of task elements");
        for (Task t : testList){
            Log.d("createTaskList", t.getName());
        }
        saveTasks(testList);
        taskList = loadTasks();
        for (Task t : taskList){
            Log.d("createTaskList", t.getName());
        }

    }

    // Called when task popup "ok" button is pressed
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

    public void editTask(View view)
    {

    }

    // Retreives an arraylist of tasks from file storage
    ArrayList<Task> loadTasks(){
        ArrayList<Task> retlist;
        FileInputStream finp;
        ObjectInputStream oinp;

        try {
            //finp = new FileInputStream("taskslist.ser");
            finp = openFileInput("taskslist.ser");
            oinp = new ObjectInputStream(finp);

            retlist = (ArrayList) oinp.readObject();

            oinp.close();
            finp.close();
            return retlist;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("loadTasks", "Failed to load tasks, returning null");
        return null;
    }


    void saveTasks(List<Task> tasks){
        FileOutputStream fout;
        ObjectOutputStream oout;

        /*
        * Still need to add:
        * - Check if file already exists, and handle accordingly
        *  - If file doesnt exist, create file
        *  - If file does exist, replace contents (deleting old contents)
        * */

        try {
            //fout = new FileOutputStream("taskslist.ser");
            fout = openFileOutput("taskslist.ser", Context.MODE_PRIVATE);
            oout = new ObjectOutputStream(fout);

            oout.writeObject(tasks);

            oout.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
