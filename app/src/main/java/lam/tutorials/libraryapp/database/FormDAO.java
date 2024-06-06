package lam.tutorials.libraryapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lam.tutorials.libraryapp.entity.Form;

@Dao
public interface FormDAO {
    @Insert
    void insertForm(Form form);

    @Update
    void updateForm(Form form);

    @Query("SELECT * FROM form")
    List<Form> getListForm();

    @Query("SELECT * FROM form WHERE id = :id_form")
    Form getFormById(int id_form);

    @Query("SELECT * FROM form WHERE type = :type")
    List<Form> getListFormByType(String type);

    @Query("SELECT * FROM form WHERE type = :type AND status = :status")
    List<Form> getListFormByStatus(String type, String status);

    @Query("SELECT * FROM form WHERE id_user = :id_user")
    List<Form> getListFormByIdUser(int id_user);

    @Query("DELETE FROM form")
    void deleteAllForm();
}
