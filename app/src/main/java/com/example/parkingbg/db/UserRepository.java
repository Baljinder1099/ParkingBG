package com.example.parkingbg.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.parkingbg.model.Parking;
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
    private LiveData<List<Parking>> allParking;

    public UserRepository(Application application) {
        UserDB db = UserDB.getINSTANCE(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
        allParking = userDao.getAllParking();
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<Parking>> getAllParking() {
        return allParking;
    }

    public void insert(User user){ new insertUserAsyncTask(userDao).execute(user); }

    public void insert(Parking parking){ new insertParkingAsyncTask(userDao).execute(parking); }

    public void update(User user){ new updateUserAsyncTask(userDao).execute(user); }



    private static class insertUserAsyncTask extends AsyncTask<User,  Void, Void> {

        private UserDao asyncTaskDao;

        insertUserAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insert(users[0]);
            return null;
        }


    }





    private static class updateUserAsyncTask extends AsyncTask<User,  Void, Void> {

        private UserDao asyncTaskDao;

        updateUserAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.update(users[0]);
            asyncTaskDao.insert(users[0]);
            return null;
        }


    }

    private static class insertParkingAsyncTask extends AsyncTask<Parking, Void, Void>{

        private UserDao asyncTaskDao;

        insertParkingAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(Parking... parkings) {
            asyncTaskDao.insert(parkings[0]);
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
