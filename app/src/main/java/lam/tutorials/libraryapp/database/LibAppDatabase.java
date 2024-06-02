package lam.tutorials.libraryapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.tutorials.libraryapp.entity.*;

@Database(entities = {User.class,Book.class, Form.class},version = 1)
public abstract class LibAppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "libapp.dp";
    private static LibAppDatabase instance;

    public static synchronized LibAppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),LibAppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();
    public abstract BookDAO bookDAO();
    public abstract FormDAO formDAO();
}
