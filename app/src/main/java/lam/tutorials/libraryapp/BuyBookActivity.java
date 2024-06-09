package lam.tutorials.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.ActivityBuyBookBinding;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.entity.Form;
import lam.tutorials.libraryapp.entity.User;
import lam.tutorials.libraryapp.fragment.FindBookFragment;
import lam.tutorials.libraryapp.fragment.RegisFormFragment;

public class BuyBookActivity extends AppCompatActivity {

    ActivityBuyBookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int id_student = intent.getIntExtra("id_student", 0);
        int id_book = intent.getIntExtra("id_book",0);
        User student = LibAppDatabase.getInstance(getApplicationContext()).userDAO().getUserById(id_student);
        Book book = LibAppDatabase.getInstance(getApplicationContext()).bookDAO().getBookById(id_book);

        binding.tvtStuName.setText("Họ tên: " + student.getFullname());
        binding.tvtStuCode.setText("MSV: " + student.getCode());
        binding.tvtBName.setText("Tên sách: " + book.getName());
        binding.tvtBAuthor.setText("Tác giả: " + book.getAuthor());
        binding.tvtBPublishComp.setText("NXB: " + book.getPublish_comp());
        binding.tvtBPublishYear.setText("Năm: " + book.getPublish_date());
        //binding.tvtBCategory.setText("Thể loại: " + book.getCategory());
        //binding.tvtBType.setText("Loại: " + book.getType());
        //binding.tvtBFaculty.setText("Ngành: " + book.getFaculty());
        binding.tvtBookPrice.setText("Giá: " + book.getPrice() + " VND");

        binding.editQuality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int quality = Integer.parseInt(binding.editQuality.getText().toString());
                    if(quality > book.getQuality_stock()) {
                        Toast.makeText(getApplicationContext(), "Thư viện không đủ số lượng sách", Toast.LENGTH_SHORT).show();
                    }else{
                        long total = quality * book.getPrice();
                        binding.editTotal.setText(String.valueOf(total));
                    }
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số lượng hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnBuyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.editQuality.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số lượng hợp lệ", Toast.LENGTH_SHORT).show();
                }else{
                    int quality = Integer.parseInt(binding.editQuality.getText().toString());
                    Book cbook = LibAppDatabase.getInstance(getApplicationContext()).bookDAO().getBookById(id_book);
                    if(quality > cbook.getQuality_stock()) {
                        Toast.makeText(getApplicationContext(), "Thư viện không đủ số lượng sách", Toast.LENGTH_SHORT).show();
                    }else{
                        long total = quality * cbook.getPrice();
                        //Calendar calendar = Calendar.getInstance();

                        Date date = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String currentDate = dateFormat.format(date);

                        Form buyform = new Form(id_book, id_student, quality, "Đã bán", currentDate, null,null, "BuyForm", total);
                        int stock = cbook.getQuality_stock() - quality;
                        cbook.setQuality_stock(stock);
                        LibAppDatabase.getInstance(getApplicationContext()).bookDAO().updateBook(cbook);
                        LibAppDatabase.getInstance(getApplicationContext()).formDAO().insertForm(buyform);
                        Toast.makeText(getApplicationContext(), "Mua thành công", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), StudentBookDetailActivity.class);
                        //startActivity(intent);
                        finish();
                    }
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}