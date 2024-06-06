package lam.tutorials.libraryapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
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

    ArrayList<String> listStatus = new ArrayList<String>();
    ArrayAdapter arrayAdapter = null;


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

        listStatus.add("Tất cả");
        listStatus.add("Chờ nhận");
        listStatus.add("Đã nhận");
        listStatus.add("Đã trả");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        User u = LibAppDatabase.getInstance(getContext()).userDAO().getUserById(id_user);
        String role = u.getRole();
        formList = new ArrayList<>();
        formList = LibAppDatabase.getInstance(getContext()).formDAO().getListForm();
        List<Form> buyFormList = LibAppDatabase.getInstance(getContext()).formDAO().getListFormByType("BuyForm");
        List<Form> borrowFormList = LibAppDatabase.getInstance(getContext()).formDAO().getListFormByType("BorrowForm");
        Collections.reverse(formList);
        adapter = new FormAdapter(getContext(),formList,id_user,role);
        binding.recyclerView.setAdapter(adapter);
        binding.tablerowStatus.setVisibility(View.GONE);
        arrayAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listStatus);
        arrayAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        binding.spinStatus.setAdapter(arrayAdapter);

        binding.spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String status = listStatus.get(position);

                if(status.equals("Tất cả")) {
                    adapter.changDataList(borrowFormList);
                }else{
                    filterListStatus(status);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changDataList(buyFormList);
                binding.tablerowStatus.setVisibility(View.GONE);
            }
        });

        binding.btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changDataList(borrowFormList);
                binding.tablerowStatus.setVisibility(View.VISIBLE);
            }
        });

        binding.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changDataList(formList);
                binding.tablerowStatus.setVisibility(View.GONE);
            }
        });
    }

    public void filterListStatus(String status) {
        ArrayList<Form> filterListStatus = new ArrayList<>();
        for(Form form:formList) {
            if(form.getStatus().toLowerCase().equals(status.toLowerCase())) {
                filterListStatus.add(form);
            }
        }
        adapter.changDataList(filterListStatus);
    }


}