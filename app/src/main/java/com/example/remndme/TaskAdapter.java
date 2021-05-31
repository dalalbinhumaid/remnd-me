package com.example.remndme;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final List<Task> tasks;
    private final LayoutInflater layoutInflater;
    private ItemClickListener mClickListener;

    public TaskAdapter(Context context, List<Task> task) {
        this.layoutInflater = LayoutInflater.from(context); // Inflate the adapter
        this.tasks = task;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.task, parent, false); // Inflate the ViewHolder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String task = tasks.get(position).getTitle();
        boolean checked = tasks.get(position).isChecked();

        // The stars appear based on the importance level
        if (tasks.get(position).getImportance() == 0)
            holder.starImportance.setImageResource(R.drawable.highimp);
        if (tasks.get(position).getImportance() == 1)
            holder.starImportance.setImageResource(R.drawable.mediumimp);
        if (tasks.get(position).getImportance() == 2)
            holder.starImportance.setImageResource(R.drawable.lowimp);

        holder.taskText.setText(task);
        holder.checkBox.setChecked(checked);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView taskText;
        CheckBox checkBox;
        ImageView starImportance;
        LinearLayout layout;

        ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            taskText = itemView.findViewById(R.id.task);
            starImportance = itemView.findViewById(R.id.importance);
            layout = itemView.findViewById(R.id.viewLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public String getItem(int id) {
        return tasks.get(id).getTime();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}