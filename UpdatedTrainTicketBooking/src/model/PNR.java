package model;

import java.util.ArrayList;
import java.util.List;

public class PNR {
    private int trainNumber;
    private int pnrNumber;
    private int count;
    private String status;
    private double price;
    private List<Passenger> passengerList = new ArrayList<>();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(Passenger passenger) {
        this.passengerList.add(passenger);
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(int pnrNumber) {
        this.pnrNumber = pnrNumber;
    }
}
