package com.peak.balance.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.peak.balance.db.bean.Expend;
import com.peak.balance.db.bean.Income;

import java.util.List;

@Dao
public interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(Income... incomes);

    @Delete
    void deleteRecord(Income income);

    @Update
    void update(Income income);

    @Query("select * from tb_income")
    List<Income> getAll();


}
