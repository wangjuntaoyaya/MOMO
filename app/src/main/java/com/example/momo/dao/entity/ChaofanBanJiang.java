package com.example.momo.dao.entity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChaofanBanJiang extends ViewModel {
    MutableLiveData mutableLiveData= new MutableLiveData<News>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int  age;

}
