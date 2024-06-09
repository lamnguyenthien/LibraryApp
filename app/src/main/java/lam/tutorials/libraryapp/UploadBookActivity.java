package lam.tutorials.libraryapp;

import static lam.tutorials.libraryapp.service.BookService.checkBookIsExist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.ActivityUploadBookBinding;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.fragment.ManageBookFragment;

public class UploadBookActivity extends AppCompatActivity {

    ActivityUploadBookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityUploadBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUploadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.nbookName.getText().toString();
                String author = binding.nbookAuthor.getText().toString();
                String publish_comp = binding.nbookPubishComp.getText().toString();
                String publish_date = binding.nbookPublishYear.getText().toString();
                String type = binding.nbookType.getText().toString();
                String category = binding.nbookCategory.getText().toString();
                String faculty = binding.nbookFaculty.getText().toString();
                String stock = binding.nbookQualityStock.getText().toString();
                String borrow = binding.nbookQualityBorrow.getText().toString();
                String price_string = binding.nbookPrice.getText().toString();
                long price = Long.parseLong(price_string);
                int quality_stock = Integer.parseInt(stock);
                int quality_borrow = Integer.parseInt(borrow);

                if(name.equals("")||author.equals("")||publish_comp.equals("")||publish_date.equals("")||price_string.equals("")
                    ||type.equals("")||category.equals("")||faculty.equals("")||stock.equals("")||borrow.equals("")) {

                    Toast.makeText(UploadBookActivity.this,"Nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }else{
                    if(checkBookIsExist(name,publish_date,author,publish_comp,UploadBookActivity.this)) {
                        Toast.makeText(UploadBookActivity.this,"Sách đã tồn tại",Toast.LENGTH_LONG).show();
                    }else{
                        Book nBook = new Book(name, author, publish_date, publish_comp, quality_stock, quality_borrow, category, type, faculty,price,1);
                        LibAppDatabase.getInstance(UploadBookActivity.this).bookDAO().insertBook(nBook);
                        Toast.makeText(UploadBookActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
                startActivity(intent);
            }
        });
    }
}