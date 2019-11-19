package com.example.momo.datebase;

import com.example.momo.dao.entity.User;

import java.util.List;

import io.reactivex.Flowable;

public interface IUserDateSource {

    Flowable<User> getUserById(int useid);


    Flowable<List<User>> getAllUsers();

    void insertUser(User... users);

    void deleteUser(User... users);

    void updateUser(User... users);

    void deleteAllUser();
}
