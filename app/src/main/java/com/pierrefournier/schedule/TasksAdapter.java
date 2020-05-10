package com.pierrefournier.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pierrefournier.schedule.model.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder>{

    private List<Task> tasks;

    public TasksAdapter(List<Task> tasks){
        this.tasks = tasks;

    }
    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_cell, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.display(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TasksViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateView;
        private final TextView hourView;
        private final TextView titleView;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            dateView = itemView.findViewById(R.id.DateChildrenCellTextView);
            hourView = itemView.findViewById(R.id.HourChildrenCellTextView);
            titleView = itemView.findViewById(R.id.TitleChildrenCellTextView);
        }

        public void display(Task task){
            dateView.setText(task.getDate());
            hourView.setText(task.getStartHour());
            titleView.setText(task.getTitle());
        }
    }
}
