package com.sixtytwentypeaks.tudu.data;

import android.database.Cursor;

/**
 * Created by narko on 02/09/17.
 */

public class Task {
    public final static int INVALID_ID = -1;

    private long id;
    private String description;
    private int isPriority;

    public Task() {
        id = INVALID_ID;
    }

    public Task(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex(TaskContract._ID));
        description = cursor.getString(cursor.getColumnIndex(TaskContract.DESCRIPTION));
        isPriority = cursor.getInt(cursor.getColumnIndex(TaskContract.IS_PRIORITY));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsPriority() {
        return isPriority;
    }

    public void setIsPriority(int isPriority) {
        this.isPriority = isPriority;
    }

}
