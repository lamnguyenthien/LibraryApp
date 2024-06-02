package lam.tutorials.libraryapp.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.entity.User;

public class UserService {

    public static boolean checkEmail(String email, Context context) {
        List<User> listUser = new ArrayList<>();
        listUser = LibAppDatabase.getInstance(context).userDAO().getListUserByEmail(email);
        if(listUser.size() == 0) {
            return false;
        }else{
            return true;
        }
    }

    public static boolean checkCode(String code, Context context) {
        List<User> listUser = new ArrayList<>();
        listUser = LibAppDatabase.getInstance(context).userDAO().getListUserByCode(code);
        if(listUser.size() == 0) {
            return false;
        }else{
            return true;
        }
    }

    public static boolean checkAccount(String code, String pass, Context context) {
        List<User> listUser = new ArrayList<>();
        listUser = LibAppDatabase.getInstance(context).userDAO().getListUserByAccount(code,pass);
        if(listUser.size() == 0) {
            return false;
        }else{
            return true;
        }
    }

    public static boolean isStudent(String code, String pass, Context context) {
        List<User> listUser = new ArrayList<>();
        listUser = LibAppDatabase.getInstance(context).userDAO().getListUserByAccount(code,pass);
        if(listUser.get(0).getRole().equals("Student")) {
            return true;
        }else{
            return false;
        }
    }


}
