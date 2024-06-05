package lam.tutorials.libraryapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lam.tutorials.libraryapp.entity.Form;

@Dao
public interface FormDAO {
    @Insert
    void insertForm(Form form);

    @Query("SELECT * FROM Form")
    List<Form> getListForm();

    @Query("SELECT * FROM Form WHERE id_user = :id_user")
    List<Form> getListFormByIdUser(int id_user);

    @Query("DELETE FROM Form")
    void deleteAllForm();
}
