package com.sixtytwentypeaks.tudu.ui;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sixtytwentypeaks.tudu.R;
import com.sixtytwentypeaks.tudu.data.Task;

/**
 * Created by narko on 01/09/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private Cursor mCursor;

    public TaskAdapter(Cursor cursor, OnItemClickListener listener) {
        mCursor = cursor;
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(Task task);
    }

    private OnItemClickListener mOnItemClickListener;


    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        if (mCursor == null) return;
        mCursor.moveToPosition(position);
        holder.bind(new Task(mCursor), mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        private TextView description;

        public TaskHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.text_description);
        }

        public void bind(final Task task, final OnItemClickListener listener) {
            description.setText(task.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(task);
                }
            });

            // set tag for later use while deleting
            itemView.setTag(task.getId());
        }
    }

    public void swapCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }
}
