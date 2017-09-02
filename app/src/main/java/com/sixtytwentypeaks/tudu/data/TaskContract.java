package com.sixtytwentypeaks.tudu.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by narko on 01/09/17.
 */

public interface TaskContract {
    @DataType(INTEGER) @PrimaryKey @AutoIncrement String _ID = "_id";
    @DataType(TEXT) @NotNull String DESCRIPTION = "description";
    @DataType(INTEGER) String IS_COMPLETE = "is_complete";
    @DataType(INTEGER) String IS_PRIORITY = "is_priority";
}
