package com.example.momo.datebase;

import com.example.momo.dao.entity.User;

import java.util.List;

import io.reactivex.Flowable;

public class UserRepository  implements IUserDateSource{

    private UserDateSoureImp userDateSoureImp;
    private static UserRepository mLocalDataSource;
    public UserRepository(UserDateSoureImp userDateSoureImp){
        this.userDateSoureImp=userDateSoureImp;

    }

     public static UserRepository getInstance(UserDateSoureImp userDateSoureImp){
        if(mLocalDataSource==null){

            mLocalDataSource=new UserRepository(userDateSoureImp);
        }

        return  mLocalDataSource;
     }
    @Override
    public Flowable<User> getUserById(int useid) {
        return userDateSoureImp.getUserById(useid);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return userDateSoureImp.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        userDateSoureImp.insertUser(users);
    }

    @Override
    public void deleteUser(User... users) {
        userDateSoureImp.deleteUser(users);
    }

    @Override
    public void updateUser(User... users) {
        userDateSoureImp.updateUser(users);
    }

    @Override
    public void deleteAllUser() {
        userDateSoureImp.deleteUser();
    }
}
