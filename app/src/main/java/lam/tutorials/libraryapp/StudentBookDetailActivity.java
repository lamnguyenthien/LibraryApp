package lam.tutorials.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.ActivityStudentBookDetailBinding;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.entity.User;
import lam.tutorials.libraryapp.fragment.FindBookFragment;

public class StudentBookDetailActivity extends AppCompatActivity {

    ActivityStudentBookDetailBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int id_book = intent.getIntExtra("id_book", 0);
        int id_user = intent.getIntExtra("id_student",0);
        Book book = LibAppDatabase.getInstance(this).bookDAO().getBookById(id_book);
        User user = LibAppDatabase.getInstance(this).userDAO().getUserById(id_user);
        binding.tvtBookStock.setText("Còn: " + book.getQuality_stock());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentBookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int id_book = intent.getIntExtra("id_book", 0);
        int id_user = intent.getIntExtra("id_student",0);
        Book book = LibAppDatabase.getInstance(this).bookDAO().getBookById(id_book);
        User user = LibAppDatabase.getInstance(this).userDAO().getUserById(id_user);
        binding.tvtBName.setText("Tên sách: " + book.getName());
        binding.tvtBAuthor.setText("Tác giả: " + book.getAuthor());
        binding.tvtBPublishComp.setText("NXB: " + book.getPublish_comp());
        binding.tvtBPublishYear.setText("Năm: " + book.getPublish_date());
        binding.tvtBType.setText("Loại: " + book.getType());
        binding.tvtBCategory.setText("Thể loại: " + book.getCategory());
        binding.tvtBFaculty.setText("Ngành: " + book.getFaculty());
        binding.tvtBookStock.setText("Còn: " + book.getQuality_stock());
        binding.tvtBookPrice.setText("Giá: " + book.getPrice() + " VND");

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnBuyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BuyBookActivity.class);
                intent.putExtra("id_student", id_user);
                intent.putExtra("id_book", id_book);
                startActivity(intent);
            }
        });

        binding.btnBorrowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BorrowBookActivity.class);
                intent.putExtra("id_student", id_user);
                intent.putExtra("id_book", id_book);
                startActivity(intent);
            }
        });
    }
}