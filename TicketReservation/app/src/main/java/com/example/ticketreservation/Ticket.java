/*
Group 23

Hardik Thakkar
Sindhuja vemulapalli
Ruoshan Qin
 */

package com.example.ticketreservation;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Sindhuja on 2/4/2016.
 */
public class Ticket implements Parcelable{
    private String name;
    private String source;
    private String destination;
    private String trip;
    private String departureDate;
    private String departureTime;
    private String returningDate;
    private String returningTime;

    public Ticket(String name, String source, String destination, String trip, String departureDate, String departureTime, String returningDate, String returningTime) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.trip = trip;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.returningDate = returningDate;
        this.returningTime = returningTime;
    }

    public Ticket(Parcel in) {
        this.name = in.readString();
        this.source = in.readString();
        this.destination = in.readString();
        this.trip = in.readString();
        this.departureDate = (String)in.readValue(ClassLoader.getSystemClassLoader());
        this.departureTime = in.readString();
        this.returningDate = (String)in.readValue(ClassLoader.getSystemClassLoader());
        this.returningTime = in.readString();;
    }

    public Ticket(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(String returningDate) {
        this.returningDate = returningDate;
    }

    public String getReturningTime() {
        return returningTime;
    }

    public void setReturningTime(String returningTime) {
        this.returningTime = returningTime;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(source);
        dest.writeString(destination);
        dest.writeString(trip);
        dest.writeValue(departureDate);
        dest.writeString(departureTime);
        dest.writeValue(returningDate);
        dest.writeString(returningTime);


    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", trip='" + trip + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime='" + departureTime + '\'' +
                ", returningDate=" + returningDate +
                ", returningTime='" + returningTime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Ticket> CREATOR=new Parcelable.Creator<Ticket>(){
        public Ticket createFromParcel(Parcel in){
            return new Ticket(in);
        }
        public Ticket[] newArray(int size){
            return new Ticket[size];
        }

    };
}
