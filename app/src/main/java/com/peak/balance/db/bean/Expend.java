package com.peak.balance.db.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.Nullable;


@Entity(tableName = "tb_expend")
public class Expend {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private long date;//时间

    @ColumnInfo(name = "number")
    private float number;//金额

    @ColumnInfo(name = "category")
    private String category;//类别

    @ColumnInfo(name = "extras")
    private String extras;//备注

    public Expend(float number, @Nullable String category, @Nullable String extras) {
        this.date = System.currentTimeMillis();
        this.number = number;
        this.category = category;
        this.extras = extras;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "Expend{" +
                "id=" + id +
                ", date=" + date +
                ", number=" + number +
                ", category='" + category + '\'' +
                ", extras='" + extras + '\'' +
                '}';
    }
}
