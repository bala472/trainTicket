package train;

import datalayer.DataLayer;
import model.Train;
import ticketmanagement.TicketView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrainView {

    private TrainModel trainModel;

    public TrainView() {
        trainModel = new TrainModel(this);
    }

    public void menu() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println(" 1 --> Add Train");
            System.out.println(" 2 --> View Trains");
            System.out.println(" 3 --> Ticket Management");
            int input = in.nextInt();
            if (input == 1) {
                trainModel.trainSetup();
            } else if (input == 2) {
                displayTrains();
            } else if (input == 3) {
                new TicketView().menu();
            } else {
                System.out.println("please enter valid input");
                menu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be number ");
            menu();
        }

    }

    private void displayTrains() {
        int i = 1;
        for (Train train : DataLayer.getInstance().getTrainList()) {
            System.out.println("Schedule " + (i++));
            System.out.println("Name : " + train.getName());
            System.out.println("Number : " + train.getNumber());
            System.out.println("Departure : " + train.getDeparture());
            System.out.println("Arrival : " + train.getArrival());
            System.out.println("From : " + train.getFrom());
            System.out.println("To : " + train.getTo());
            System.out.println("Routes : " + train.getRoutes());
            System.out.println("Seat count : " + train.getSeatCount());
            System.out.println("Fare : " + train.getPrice());
        }
        menu();
    }

    public void showMessage(String message) {
        System.out.println(message);
        menu();
    }

    public void setUp() {
        Train train = new Train();
        train.setName("nellai express");
        train.setSeatCount(3);
        train.setPrice(100);
        train.setDeparture("13:00");
        train.setArrival("11:00");
        train.setFrom("nellai");
        train.setTo("chennai");
        train.setRoutes("tenkasi");
        train.setRoutes("sivakasi");
        train.setNumber(1234);
        DataLayer.getInstance().setTrainList(train);
    }
}
