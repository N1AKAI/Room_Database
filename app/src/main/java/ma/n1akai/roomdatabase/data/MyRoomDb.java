package ma.n1akai.roomdatabase.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class MyRoomDb extends RoomDatabase {
    public abstract PersonDao getPersonDao();

}
