package com.peak.balance.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.peak.balance.db.bean.Expend;

import java.util.List;

@Dao
public interface ExpendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(Expend... expend);

    @Delete
    void deleteRecord(Expend expend);

    @Update
    void update(Expend expend);

    @Query("select * from tb_expend")
    List<Expend> getAll();


}
