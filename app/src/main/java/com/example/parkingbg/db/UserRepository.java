package com.example.parkingbg.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.parkingbg.model.User;

import java.util.List;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 14-11-2019
 */
public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDB db = UserDB.getINSTANCE(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDao;

        insertAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insert(users[0]);
            return null;
        }
    }

    public void deleteUserByEmail(String email){
        new deleteUserByEmailAsyncTask(userDao).execute(email);
    }

    private static class deleteUserByEmailAsyncTask extends AsyncTask<String, Void, Void>{

        private UserDao asyncTaskDao;

        deleteUserByEmailAsyncTask(UserDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncTaskDao.deleteUserByEmail(strings[0]);
            return null;
        }
    }
}
