package ticketmanagement;

import datalayer.DataLayer;
import model.Passenger;
import model.Train;

import java.util.Scanner;

public class TicketView {
    private TicketModel ticketModel;
    public  TicketView(){
        ticketModel = new TicketModel(this);
    }
    static Scanner in = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);
    public void menu(){
        System.out.println("\n Welcome to IRCTC \n");
        System.out.println(" 1 --> Booking");
        System.out.println(" 2 --> Get PNR");
        System.out.println(" 3 --> View Booked tickets");
        System.out.println(" 4 --> Cancel Tickets ");
        System.out.println(" 5 --> Search passenger ");
        System.out.println(" 6 --> Change ticket status ");
        int input = in.nextInt();
        if(input==1){
            booking();
        }
    }
    int i=0;
    private void booking() {
        System.out.println("Enter from : ");
        String from = sc.nextLine();
        System.out.println("Enter to : ");
        String to = sc.nextLine();
        ticketModel.getAvailableTrains(from,to);
        bookTicketProcess();
    }

    private void bookTicketProcess() {
        System.out.println("Enter train number ");
        int trainNumber = in.nextInt();
       ticketModel.validateTrain(trainNumber);
    }

    public void addPassenger(Train train) {
        System.out.println("Enter number of passenger : ");
        int count = in.nextInt();
        for(int j=1;j<=count;j++){
            Passenger passenger = new Passenger();
            System.out.println("Enter passenger "+j+" details");
            System.out.println();
            System.out.println("Name : ");
            passenger.setName(sc.nextLine());
            System.out.println("Age : ");
            passenger.setAge(in.nextInt());
            System.out.println("Gender : ");
            passenger.setGender(sc.nextLine());
            System.out.println("Id : ");
            passenger.setId(in.nextInt());
            train.setPassengersList(passenger);
        }
        System.out.println("Total ticket fare : "+(count*train.getPrice()));
        menu();
    }

    public void displayPay() {
       System.out.println("1--> Pay");
       System.out.println("2 --cancel");
       int input = in.nextInt();
       if(input==1){
           System.out.println("Booked sucessfully");
           menu();
       } else if (input==2) {
           System.out.println("cancelled sucessfully");
           menu();
       }else {
           System.out.println("invalid input");
           menu();
       }
    }

    public void displayTrain(Train train) {
        System.out.println("Train "+(i++));
        System.out.println();
        System.out.println("Name : "+train.getName());
        System.out.println("Number : "+train.getNumber());
        System.out.println("Departure : "+train.getDeparture());
        System.out.println("Arrival : "+train.getArrival());
        System.out.println("Routes : "+train.getRoutes());
        System.out.println("Seat count : "+train.getSeatCount());
        System.out.println("Fare : "+train.getPrice());
    }

    public void showAlert() {
        System.out.println("Not a valid train ");
        menu();
    }
}
