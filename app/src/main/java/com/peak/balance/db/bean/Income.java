package com.peak.balance.db.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "tb_income")
public class Income {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "date")
    private Date date;//时间

    @ColumnInfo(name = "number")
    private float number;//金额

    @ColumnInfo(name = "category")
    private String category;//类别

    @ColumnInfo(name = "extras")
    private String extras;//备注
}
