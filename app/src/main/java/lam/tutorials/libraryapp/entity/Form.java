package lam.tutorials.libraryapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "form")
public class Form {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_book;
    private int id_user;
    private int quality;
    private String status;
    private String regis_date;
    private String receive_date;
    private String return_date;
    private String type;
    private long total;

    public Form(int id_book, int id_user, int quality, String status, String regis_date, String receive_date, String return_date, String type, long total) {
        this.id_book = id_book;
        this.id_user = id_user;
        this.quality = quality;
        this.status = status;
        this.regis_date = regis_date;
        this.receive_date = receive_date;
        this.return_date = return_date;
        this.type = type;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getRegis_date() {
        return regis_date;
    }

    public void setRegis_date(String regis_date) {
        this.regis_date = regis_date;
    }

    public String getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(String receive_date) {
        this.receive_date = receive_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
