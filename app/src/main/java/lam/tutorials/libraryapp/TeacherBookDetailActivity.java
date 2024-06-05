package lam.tutorials.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.ActivityTeacherBookDetailBinding;
import lam.tutorials.libraryapp.databinding.ActivityTeacherMainBinding;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.fragment.ManageBookFragment;

public class TeacherBookDetailActivity extends AppCompatActivity {
    ActivityTeacherBookDetailBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        binding = ActivityTeacherBookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int id_book = intent.getIntExtra("id_book", 0);
        Book cBook = LibAppDatabase.getInstance(this).bookDAO().getBookById(id_book);
        binding.editBookName.setText(cBook.getName());
        binding.editBookYear.setText(cBook.getPublish_date());
        binding.editBookAuthor.setText(cBook.getAuthor());
        binding.editPublishComp.setText(cBook.getPublish_comp());
        binding.editBookType.setText(cBook.getType());
        binding.editBookCategory.setText(cBook.getCategory());
        binding.editBookFaculty.setText(cBook.getFaculty());
        binding.editBookQualityStock.setText(String.valueOf(cBook.getQuality_stock()));
        binding.editBookQualityBorrow.setText(String.valueOf(cBook.getQuality_borrow()));
        binding.editBookPrice.setText(String.valueOf(cBook.getPrice()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherBookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int id_book = intent.getIntExtra("id_book", 0);
        Book cBook = LibAppDatabase.getInstance(this).bookDAO().getBookById(id_book);
        binding.editBookName.setText(cBook.getName());
        binding.editBookYear.setText(cBook.getPublish_date());
        binding.editBookAuthor.setText(cBook.getAuthor());
        binding.editPublishComp.setText(cBook.getPublish_comp());
        binding.editBookType.setText(cBook.getType());
        binding.editBookCategory.setText(cBook.getCategory());
        binding.editBookFaculty.setText(cBook.getFaculty());
        binding.editBookQualityStock.setText(String.valueOf(cBook.getQuality_stock()));
        binding.editBookQualityBorrow.setText(String.valueOf(cBook.getQuality_borrow()));
        binding.editBookPrice.setText(String.valueOf(cBook.getPrice()));
        binding.btnDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book dBook = LibAppDatabase.getInstance(getApplicationContext()).bookDAO().getBookById(id_book);
                LibAppDatabase.getInstance(getApplicationContext()).bookDAO().deleteBook(dBook);
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ManageBookFragment.class);
                startActivity(intent);
            }
        });

        binding.btnUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book oBook = LibAppDatabase.getInstance(TeacherBookDetailActivity.this).bookDAO().getBookById(id_book);
                String book_name = binding.editBookName.getText().toString();
                String book_date = binding.editBookYear.getText().toString();
                String book_author = binding.editBookAuthor.getText().toString();
                String book_comp = binding.editPublishComp.getText().toString();
                String book_type = binding.editBookType.getText().toString();
                String book_category = binding.editBookCategory.getText().toString();
                String book_faculty = binding.editBookFaculty.getText().toString();
                String quality_borrow = binding.editBookQualityBorrow.getText().toString();
                String quality_stock = binding.editBookQualityStock.getText().toString();
                String price = binding.editBookPrice.getText().toString();
                if(book_name.equals("")||book_date.equals("")||book_author.equals("")||price.equals("")||
                   book_comp.equals("")||book_type.equals("")||book_category.equals("")||
                   book_faculty.equals("")||quality_borrow.equals("")||quality_stock.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    int stock = Integer.parseInt(quality_stock);
                    int borrow = Integer.parseInt(quality_borrow);
                    long price_book = Long.parseLong(price);
                    oBook.setName(book_name);
                    oBook.setAuthor(book_author);
                    oBook.setPublish_comp(book_comp);
                    oBook.setPublish_date(book_date);
                    oBook.setType(book_type);
                    oBook.setCategory(book_category);
                    oBook.setFaculty(book_faculty);
                    oBook.setQuality_borrow(borrow);
                    oBook.setQuality_stock(stock);
                    oBook.setPrice(price_book);
                    LibAppDatabase.getInstance(getApplicationContext()).bookDAO().updateBook(oBook);
                    Toast.makeText(getApplicationContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnBackTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
                startActivity(intent);
            }
        });


    }
}