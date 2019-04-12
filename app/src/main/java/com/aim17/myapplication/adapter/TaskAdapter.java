package com.aim17.myapplication.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aim17.myapplication.R;
import com.aim17.myapplication.model.Task;
import java.util.List;



public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private int rowLayout;

    public TaskAdapter(List<Task> tasks, int rowLayout) {
        this.tasks = tasks;
        this.rowLayout = rowLayout;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, final int position) {
        holder.name.setText(tasks.get(position).getName());
        holder.details.setText(tasks.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        // original simply returns tasks size. Keep getting crash on launch because tasks is null
        if (tasks != null){
            return tasks.size();
        } else {
            return 0;
        }
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView details;

        public TaskViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            details = (TextView) v.findViewById(R.id.details);
        }
    }
}
