package lam.tutorials.libraryapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lam.tutorials.libraryapp.entity.Book;

@Dao
public interface BookDAO {

    @Insert
    void insertBook(Book book);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("SELECT * FROM book")
    List<Book> getListBook();

    @Query("SELECT * FROM book WHERE enable = 1")
    List<Book> getListBookEnable();

    @Query("SELECT * FROM book WHERE id = :id")
    Book getBookById(int id);

    @Query("SELECT name FROM book WHERE id = :id")
    String getNameBookById(int id);


    @Query("SELECT * FROM book WHERE name = :name AND publish_date = :publish_date " +
            "AND publish_comp = :publish_comp AND author = :author")
    Book getBook(String name, String publish_date, String publish_comp, String author);
}
