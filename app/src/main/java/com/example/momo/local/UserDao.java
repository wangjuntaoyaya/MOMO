package com.example.momo.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.momo.dao.entity.User;

import java.util.List;

import io.reactivex.Flowable;
@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE id=:useid")
    Flowable<User> getUserById(int useid);

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Delete
    void deleteUser(User... users);

    @Update
    void updateUser(User... users);

    @Query("DELETE FROM user")
    void deleteAllUser();
}
