package lam.tutorials.libraryapp.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.entity.Book;

public class BookService {

    public static boolean checkBookIsExist(String name, String date, String author, String comp, Context context) {
        Book book = LibAppDatabase.getInstance(context).bookDAO().getBook(name,date,comp,author);
        if(book != null) {
            return true;
        }else{
            return false;
        }
    }
}
