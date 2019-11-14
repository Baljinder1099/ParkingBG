package com.example.parkingbg;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * ParkingBG created by gursharansandhu
 * Student ID : 991544576
 * on 2019-11-14
 */
public class Utils {

    public static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) &&
                Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
