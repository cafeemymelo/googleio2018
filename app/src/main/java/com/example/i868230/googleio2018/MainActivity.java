package com.example.i868230.googleio2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText taskDescription;
    ListView taskList;

    List<Task> tasks;
    ArrayAdapter<Task> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDescription = findViewById(R.id.taskDescription);
        taskList = findViewById(R.id.taskList);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(adapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = tasks.get(position);
                task.setDone(!task.isDone());
                adapter.notifyDataSetChanged();
            }
        });

        registerForContextMenu(taskList);
    }

    public void addTask(View view) {
        String description = taskDescription.getText().toString();
        Task task = new Task(description);
        tasks.add(task);
        adapter.notifyDataSetChanged();
        taskDescription.setText("");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.task_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                int position = ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;
                tasks.remove(position);
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
