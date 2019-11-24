package com.example.parkingbg.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * ParkingBG created by Baljinder
 * Student ID : 991540911
 * on 21-11-2019
 */
@Entity(tableName = "parking_table")
public class Parking implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "building")
    private int buildingCode;

    @ColumnInfo(name = "hours")
    private float hours;

    @ColumnInfo(name = "carPlate")
    private String carPlate;

    @ColumnInfo(name = "suitNo")
    private String suitNo;

    @ColumnInfo(name = "date")
    private String  dateTime;

//    @ColumnInfo(name = "time")
//    private Time time;

    @ColumnInfo(name = "parkingCharges")
    private int parkingCharges;

    public Parking(int buildingCode, float hours, String carPlate, String suitNo, String dateTime, int parkingCharges) {
        this.buildingCode = buildingCode;
        this.hours = hours;
        this.carPlate = carPlate;
        this.suitNo = suitNo;
        this.dateTime = dateTime;
        this.parkingCharges = parkingCharges;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(int buildingCode) {
        this.buildingCode = buildingCode;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getSuitNo() {
        return suitNo;
    }

    public void setSuitNo(String suitNo) {
        suitNo = suitNo;
    }

    public String getDate() {
        return dateTime;
    }

    public void setDate(String date) {
        this.dateTime = date;
    }



    public int getParkingCharges() {
        return parkingCharges;
    }

    public void setParkingCharges(int parkingCharges) {
        this.parkingCharges = parkingCharges;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "buildingCode=" + buildingCode +
                ", hours=" + hours +
                ", carPlate='" + carPlate + '\'' +
                ", SuitNo='" + suitNo + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", parkingCharges=" + parkingCharges +
                '}';
    }
}
