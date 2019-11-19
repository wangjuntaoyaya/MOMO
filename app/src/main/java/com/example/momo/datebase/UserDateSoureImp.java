package com.example.momo.datebase;

import com.example.momo.dao.entity.User;
import com.example.momo.local.UserDao;

import java.util.List;

import io.reactivex.Flowable;

public class UserDateSoureImp implements IUserDateSource{
    private UserDao userDao;
    public UserDateSoureImp(UserDao u){
        userDao=u;
    }
    @Override
    public Flowable<User> getUserById(int useid) {
        return userDao.getUserById(useid);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        userDao.insertUser(users);
    }

    @Override
    public void deleteUser(User... users) {
        userDao.deleteUser(users);
    }

    @Override
    public void updateUser(User... users) {
        userDao.updateUser(users);
    }

    @Override
    public void deleteAllUser() {
        userDao.deleteAllUser();
    }
}
