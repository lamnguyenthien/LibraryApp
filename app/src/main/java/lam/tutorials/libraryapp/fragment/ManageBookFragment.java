package lam.tutorials.libraryapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import lam.tutorials.libraryapp.UploadBookActivity;
import lam.tutorials.libraryapp.adapter.BookAdapter;
import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.databinding.FragmentManageBookBinding;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.entity.User;


public class ManageBookFragment extends Fragment {

    FragmentManageBookBinding binding;
    RecyclerView recyclerView;
    List<Book> bookList;

    SearchView searchView;
    private int id_user;
    BookAdapter adapter;

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
        // Inflate the layout for this fragment
        binding = FragmentManageBookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.search.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        User u = LibAppDatabase.getInstance(getContext()).userDAO().getUserById(id_user);
        String role = u.getRole();
        bookList = new ArrayList<>();
        bookList = LibAppDatabase.getInstance(getContext()).bookDAO().getListBook();
        adapter = new BookAdapter(getContext(),bookList,id_user, role);
        binding.recyclerView.setAdapter(adapter);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(), UploadBookActivity.class);
                startActivity(intent);
            }
        });

        binding.search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }

    public void searchList(String text) {
        ArrayList<Book> searchList = new ArrayList<>();
        for(Book book:bookList) {
            if(book.getName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(book);
            }
        }
        adapter.serachDataList(searchList);
    }
}