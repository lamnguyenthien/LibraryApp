package lam.tutorials.libraryapp;

import static lam.tutorials.libraryapp.service.UserService.checkAccount;
import static lam.tutorials.libraryapp.service.UserService.isStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = binding.loginCode.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if(code.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(checkAccount(code,password, LoginActivity.this)) {
                        if(isStudent(code,password, LoginActivity.this)) {
                            int id = LibAppDatabase.getInstance(LoginActivity.this).userDAO().getIdUser(code, password);
                            String name = LibAppDatabase.getInstance(LoginActivity.this).userDAO().getNameById(id);
                            Intent intent  = new Intent(getApplicationContext(), StudentMainActivity.class);
                            intent.putExtra("id_user", id);
                            intent.putExtra("name_user", name);
                            startActivity(intent);
                        }else{
                            int id = LibAppDatabase.getInstance(LoginActivity.this).userDAO().getIdUser(code, password);
                            String name = LibAppDatabase.getInstance(LoginActivity.this).userDAO().getNameById(id);
                            Intent intent  = new Intent(getApplicationContext(), TeacherMainActivity.class);
                            intent.putExtra("id_user", id);
                            intent.putExtra("name_user", name);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}