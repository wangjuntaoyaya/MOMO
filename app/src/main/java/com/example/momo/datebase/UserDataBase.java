package com.example.momo.datebase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.momo.dao.entity.User;
import com.example.momo.local.UserDao;

import static com.example.momo.datebase.UserDataBase.DATABASE_VERSION;

@Database(entities = User.class ,version = DATABASE_VERSION,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {


    public static final String DATABASE_NAME="MOMO-Database-Room";
    public static final int DATABASE_VERSION =1 ;

    public abstract UserDao userDao();

    private  static UserDataBase mInstance;

    public static UserDataBase getInstance(Context context) {
        if (mInstance == null) {

            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDataBase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
//        如果从旧版本到新版本的迁移规则无法找到，就会触发错误，如果要避免该错误，
//        可以使用fallbackToDestructiveMigration，这样就可以告诉Room，在找不到迁移规则时，
//        可以破坏性重建数据库，注意这会删除所有数据库表数据。

     return mInstance;
    }




}
