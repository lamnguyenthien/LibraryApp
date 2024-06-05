package lam.tutorials.libraryapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lam.tutorials.libraryapp.R;
import lam.tutorials.libraryapp.adapter.FormAdapter;
import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.FragmentManageFormBinding;
import lam.tutorials.libraryapp.entity.Form;
import lam.tutorials.libraryapp.entity.User;


public class ManageFormFragment extends Fragment {

    FragmentManageFormBinding binding;
    RecyclerView recyclerView;
    List<Form> formList;
    FormAdapter adapter;
    private int id_user;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            id_user = getArguments().getInt("id_user",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        User u = LibAppDatabase.getInstance(getContext()).userDAO().getUserById(id_user);
        String role = u.getRole();
        formList = new ArrayList<>();
        formList = LibAppDatabase.getInstance(getContext()).formDAO().getListForm();
        Collections.reverse(formList);
        adapter = new FormAdapter(getContext(),formList,id_user,role);
        binding.recyclerView.setAdapter(adapter);
    }
}