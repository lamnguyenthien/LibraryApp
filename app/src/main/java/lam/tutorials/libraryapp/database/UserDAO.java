package lam.tutorials.libraryapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lam.tutorials.libraryapp.entity.User;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user WHERE email = :email")
    List<User> getListUserByEmail(String email);

    @Query("SELECT * FROM user WHERE code = :code")
    List<User> getListUserByCode(String code);

    @Query("SELECT * FROM user WHERE code = :code and password = :password")
    List<User> getListUserByAccount(String code, String password);

    @Query("SELECT id FROM user WHERE code = :code and password = :password")
    int getIdUser(String code, String password);

    @Query("SELECT fullname FROM user WHERE id = :id")
    String getNameById(int id);

    @Query("SELECT * FROM user WHERE id = :id")
    User getUserById(int id);

}
