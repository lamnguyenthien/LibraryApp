package lam.tutorials.libraryapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lam.tutorials.libraryapp.R;
import lam.tutorials.libraryapp.database.LibAppDatabase;
import lam.tutorials.libraryapp.entity.Book;
import lam.tutorials.libraryapp.entity.Form;
import lam.tutorials.libraryapp.entity.User;

public class FormAdapter extends RecyclerView.Adapter<MyFormViewHolder>
{

    private Context context;
    private List<Form> formlist;
    //private List<User> userList;
    //private List<Book> booklist;

    private int id_user;
    private String role;

    public FormAdapter(Context context, List<Form> formlist, int id_user, String role) {
        this.context = context;
        this.formlist = formlist;
        this.id_user = id_user;
        this.role = role;
    }

    @NonNull
    @Override
    public MyFormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_form_item,parent,false);
        return new MyFormViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyFormViewHolder holder, int position) {

        holder.tvBookName.setText(LibAppDatabase.getInstance(context.getApplicationContext()).bookDAO().getNameBookById(formlist.get(position).getId_book()));
        holder.tvUserName.setText(LibAppDatabase.getInstance(context.getApplicationContext()).userDAO().getNameById(formlist.get(position).getId_user()));
        holder.tvStatus.setText("Trạng thái: " + formlist.get(position).getStatus());
        holder.tvQuality.setText("Số lượng: " + formlist.get(position).getQuality());
        holder.tvTotal.setText("Trạng thái: " + formlist.get(position).getStatus());
        if(formlist.get(position).getType().equals("BuyForm")) {
            holder.tvRegisDate.setText("Ngày mua: " + formlist.get(position).getRegis_date());
            //holder.tvReceiveDate.setVisibility(View.GONE);
            //holder.tvReturnDate.setVisibility(View.GONE);
            holder.tableRow.setVisibility(View.GONE);
        }else if (formlist.get(position).getType().equals("BorrowForm")){
            holder.tvRegisDate.setText("Ngày đăng ký: " + formlist.get(position).getRegis_date());
            holder.tvReceiveDate.setText("Ngày nhận: " + formlist.get(position).getReceive_date());
            holder.tvReturnDate.setText("Ngày trả: " + formlist.get(position).getReturn_date());
        }
    }

    @Override
    public int getItemCount() {
        return formlist.size();
    }

    public void changDataList(ArrayList<Form> CurrentFormList) {
        formlist = CurrentFormList;
        notifyDataSetChanged();
    }
}

class MyFormViewHolder extends RecyclerView.ViewHolder {

    TextView tvBookName, tvUserName, tvStatus, tvQuality, tvTotal, tvRegisDate, tvReceiveDate, tvReturnDate;
    LinearLayout formCard;
    TableRow tableRow;

    public MyFormViewHolder(@NonNull View itemView) {
        super(itemView);

        tvBookName = itemView.findViewById(R.id.tv_book_name);
        tvUserName = itemView.findViewById(R.id.tv_user_name);
        tvStatus = itemView.findViewById(R.id.tv_form_status);
        tvQuality = itemView.findViewById(R.id.tv_quality);
        tvTotal = itemView.findViewById(R.id.tv_total);
        tvRegisDate = itemView.findViewById(R.id.tv_regis_date);
        tvReceiveDate = itemView.findViewById(R.id.tv_receive_date);
        tvReturnDate = itemView.findViewById(R.id.tv_return_date);
        tableRow = itemView.findViewById(R.id.tableRow);
    }
}
