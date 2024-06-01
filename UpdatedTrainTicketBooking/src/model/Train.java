package model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private int number;
    private String name;
    private String departure;
    private String arrival;
    private String from;
    private String to;
    private double price;
    private int seatCount;
    private int availableCount;
    private List<Passenger> passengersList = new ArrayList<>();
    private List<String> routes = new ArrayList<>();

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(Passenger passengers) {
        this.passengersList.add(passengers);
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        setAvailableCount(seatCount);
        this.seatCount = seatCount;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes.add(routes);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
