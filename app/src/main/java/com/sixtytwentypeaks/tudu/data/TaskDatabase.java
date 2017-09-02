package com.sixtytwentypeaks.tudu.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by narko on 01/09/17.
 */

@Database(version = TaskDatabase.VERSION, packageName = "com.sixtytwentypeaks.tudu.provider")
public class TaskDatabase {
    public static final int VERSION = 1;
    @Table(TaskContract.class) public static final String TASKS = "tasks";
}
