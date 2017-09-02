package com.sixtytwentypeaks.tudu.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by narko on 01/09/17.
 */
@ContentProvider(
        authority = TaskProvider.AUTHORITY,
        database = TaskDatabase.class,
        packageName = "com.sixtytwentypeaks.tudu.provider"
)
public class TaskProvider {
    public static final String AUTHORITY = "com.sixtytwentypeaks.tudu.TaskProvider";

    @TableEndpoint(table = TaskDatabase.TASKS) public static class Tasks {

        @ContentUri(
                path = "tasks",
                type = "vnd.android.cursor.dir/task",
                defaultSort = TaskContract.DESCRIPTION + " ASC")
        public static final Uri TASKS = Uri.parse("content://" + AUTHORITY + "/tasks");

        @InexactContentUri(
                path = "tasks" + "/#",
                name = "TASK_ID",
                type = "vnd.android.cursor.item/task",
                whereColumn = TaskContract._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return Uri.parse("content://" + AUTHORITY + "/tasks/" + id);
        }
    }
}
