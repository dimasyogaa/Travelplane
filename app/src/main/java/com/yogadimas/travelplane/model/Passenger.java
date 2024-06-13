package com.yogadimas.travelplane.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Passenger implements Parcelable {
    public static final Creator<Passenger> CREATOR = new Creator<Passenger>() {
        @Override
        public Passenger createFromParcel(Parcel in) {
            return new Passenger(in);
        }

        @Override
        public Passenger[] newArray(int size) {
            return new Passenger[size];
        }
    };
    private String namePassenger;
    private String airportDeparture;
    private String airportReturn;
    private String airlineName;
    private String cabinClass;
    private String dateDeparture;
    private String timeDeparture;
    private String timeTravel;
    private String dateReturn;
    private String timeReturn;
    private String paymentMethod;
    private String accountNumber;
    private String discount;
    private String airlinePrice;
    private String total;

    public Passenger() {
    }

    protected Passenger(Parcel in) {
        namePassenger = in.readString();
        airportDeparture = in.readString();
        airportReturn = in.readString();
        airlineName = in.readString();
        cabinClass = in.readString();
        dateDeparture = in.readString();
        timeDeparture = in.readString();
        timeTravel = in.readString();
        dateReturn = in.readString();
        timeReturn = in.readString();
        paymentMethod = in.readString();
        accountNumber = in.readString();
        discount = in.readString();
        airlinePrice = in.readString();
        total = in.readString();
    }

    public String getNamePassenger() {
        return namePassenger;
    }

    public void setNamePassenger(String namePassenger) {
        this.namePassenger = namePassenger;
    }

    public String getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(String airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public String getAirportReturn() {
        return airportReturn;
    }

    public void setAirportReturn(String airportReturn) {
        this.airportReturn = airportReturn;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getTimeTravel() {
        return timeTravel;
    }

    public void setTimeTravel(String timeTravel) {
        this.timeTravel = timeTravel;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getTimeReturn() {
        return timeReturn;
    }

    public void setTimeReturn(String timeReturn) {
        this.timeReturn = timeReturn;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getAirlinePrice() {
        return airlinePrice;
    }

    public void setAirlinePrice(String airlinePrice) {
        this.airlinePrice = airlinePrice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namePassenger);
        dest.writeString(airportDeparture);
        dest.writeString(airportReturn);
        dest.writeString(airlineName);
        dest.writeString(cabinClass);
        dest.writeString(dateDeparture);
        dest.writeString(timeDeparture);
        dest.writeString(timeTravel);
        dest.writeString(dateReturn);
        dest.writeString(timeReturn);
        dest.writeString(paymentMethod);
        dest.writeString(accountNumber);
        dest.writeString(discount);
        dest.writeString(airlinePrice);
        dest.writeString(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
