package lam.tutorials.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.ActivityBorrowBookBinding;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.entity.User;

public class BorrowBookActivity extends AppCompatActivity {

    ActivityBorrowBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBorrowBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int id_student = intent.getIntExtra("id_student", 0);
        int id_book = intent.getIntExtra("id_book",0);
        User student = LibAppDatabase.getInstance(getApplicationContext()).userDAO().getUserById(id_student);
        Book book = LibAppDatabase.getInstance(getApplicationContext()).bookDAO().getBookById(id_book);
    }
}