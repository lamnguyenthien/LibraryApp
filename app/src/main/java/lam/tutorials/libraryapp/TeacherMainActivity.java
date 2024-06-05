package lam.tutorials.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.bottomappbar.BottomAppBar;

import lam.tutorials.libraryapp.databinding.ActivityTeacherMainBinding;
import lam.tutorials.libraryapp.fragment.AccountFragment;
import lam.tutorials.libraryapp.fragment.ManageBookFragment;
import lam.tutorials.libraryapp.fragment.ManageFormFragment;
import lam.tutorials.libraryapp.R;
import lam.tutorials.libraryapp.fragment.RegisFormFragment;

public class TeacherMainActivity extends AppCompatActivity {

    ActivityTeacherMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        int id_user = extras.getInt("id_user", 0); // Đặt giá trị mặc định nếu không có
        String name_user = extras.getString("name_user");
        binding.toolbarGreet.setTitle("Xin chào, " + name_user);


        Bundle bundle_create = new Bundle();
        bundle_create.putInt("id_user", id_user);
        ManageFormFragment manageFormFragment_create = new ManageFormFragment();
        manageFormFragment_create.setArguments(bundle_create);
        replaceFragment(manageFormFragment_create);
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id_user", id_user);
            if (item.getItemId() == R.id.formregis) {
                ManageFormFragment manageFormFragment = new ManageFormFragment();
                manageFormFragment.setArguments(bundle);
                replaceFragment(manageFormFragment);
            } else if (item.getItemId() == R.id.managebook) {
                ManageBookFragment manageBookFragment = new ManageBookFragment();
                manageBookFragment.setArguments(bundle);
                replaceFragment(manageBookFragment);
            } else if (item.getItemId() == R.id.account) {
                AccountFragment accountFragment = new AccountFragment();
                accountFragment.setArguments(bundle);
                replaceFragment(accountFragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }



}