package com.sixtytwentypeaks.tudu;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.sixtytwentypeaks.tudu.data.Task;
import com.sixtytwentypeaks.tudu.data.TaskContract;
import com.sixtytwentypeaks.tudu.data.TaskProvider;

public class AddTaskActivity extends AppCompatActivity {
    private static final String TAG = AddTaskActivity.class.getSimpleName();
    private Task mTask;
    private Uri mUri;
    private EditText mTaskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
         mTaskDescription = (EditText) findViewById(R.id.task_description);

        mUri = getIntent().getData();
        if (mUri == null) {
            // new Task
            mTask = new Task();
        } else {
            // edit task
            Log.d(TAG, mUri.toString());
            mTask = retrieveTask(mUri);
            mTaskDescription.setText(mTask.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            saveTask();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(TaskContract.DESCRIPTION, mTaskDescription.getText().toString());
                values.put(TaskContract.IS_PRIORITY, mTask.getIsPriority());
                if (mTask.getId() == Task.INVALID_ID) {
                    Uri uri = getContentResolver().insert(TaskProvider.Tasks.TASKS, values);
                    Log.d(TAG, "Task inserted: " + uri.toString());
                } else {
                    Log.d(TAG, "Task updated: " + mUri.toString());
                    getContentResolver().update(mUri, values, null, null);
                }
            }
        }).start();
        finish();
    }

    private Task retrieveTask(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
        cursor.moveToFirst();
        Task task = new Task(cursor);
        cursor.close();

        return task;
    }
}
