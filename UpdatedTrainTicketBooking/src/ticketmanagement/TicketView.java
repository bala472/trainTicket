package ticketmanagement;

import datalayer.DataLayer;
import model.PNR;
import model.Passenger;
import model.Train;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketView {
    private TicketModel ticketModel;

    public TicketView() {
        ticketModel = new TicketModel(this);
    }

    static Scanner sc = new Scanner(System.in);

    public void menu() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("\n Welcome to IRCTC \n");
            System.out.println(" 1 --> Booking");
            System.out.println(" 2 --> Get PNR");
            System.out.println(" 3 --> View Booked tickets");
            System.out.println(" 4 --> Cancel Tickets ");
            System.out.println(" 5 --> Search passenger ");
            System.out.println(" 6 --> Change ticket status ");
            int input = in.nextInt();
            if (input == 1) {
                booking();
            } else if (input == 2) {
                getPNR();
                menu();
            } else if (input == 3) {
                viewBookedTicket();
                menu();
            } else if (input == 4) {
                cancelTicket();
                menu();
            } else if (input == 5) {
                searchPassenger();
                menu();
            } else if (input == 6) {
                changeTicketStatus();
                menu();
            } else {
                System.out.println("Please enter valid input");
                menu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be number ");
            menu();
        }
    }

    private void changeTicketStatus() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter PNR : ");
            int pnr = in.nextInt();
            ticketModel.validateTicketStatus(pnr);
        } catch (InputMismatchException e) {
            System.out.println("PNR must be number ");
            changeTicketStatus();
        }
    }

    private void searchPassenger() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter passenger ID : ");
            int id = in.nextInt();
            ticketModel.searchPassenger(id);
        } catch (InputMismatchException e) {
            System.out.println("Passenger ID must be number ");
            searchPassenger();
        }
    }

    private void viewBookedTicket() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter train number ");
            int trainNumber = in.nextInt();
            ticketModel.validateTrainToViewBooked(trainNumber);
        } catch (InputMismatchException e) {
            System.out.println("Train Number must be number !!!");
            viewBookedTicket();
        }
    }

    private void cancelTicket() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter PNR number : ");
            int pnrNumber = in.nextInt();
            ticketModel.cancelTicketByPNR(pnrNumber);
        } catch (InputMismatchException e) {
            System.out.println("PNR must be number");
            cancelTicket();
        }

    }

    public void getPNR() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter PNR number : ");
            int pnr = in.nextInt();
            ticketModel.validatePNR(pnr);
        } catch (InputMismatchException e) {
            System.out.println("PNR must be number ");
            getPNR();
        }
    }

    private void booking() {
        System.out.println("Enter from : ");
        String from = sc.nextLine();
        System.out.println("Enter to : ");
        String to = sc.nextLine();
        ticketModel.getAvailableTrains(from, to);
        bookTicketProcess();
    }

    private void bookTicketProcess() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter train number ");
            int trainNumber = in.nextInt();
            ticketModel.validateTrain(trainNumber);
        } catch (InputMismatchException e) {
            System.out.println("Train number must be number");
            bookTicketProcess();
        }
    }

    public void addPassenger(Train train) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Available seat : " + train.getAvailableCount());
            System.out.println("Enter number of passenger : ");
            int count = in.nextInt();
            PNR pnr = new PNR();
            pnr.setTrainNumber(train.getNumber());
            pnr.setPnrNumber((int) (Math.random() * 1000000));
            for (int j = 1; j <= count; j++) {
                Passenger passenger = new Passenger();
                System.out.println("Enter passenger " + j + " details");
                System.out.println();
                System.out.println("Name : ");
                passenger.setName(sc.nextLine());
                System.out.println("Age : ");
                passenger.setAge(in.nextInt());
                System.out.println("Gender : ");
                passenger.setGender(sc.nextLine());
                System.out.println("Id : ");
                int id = in.nextInt();
                passenger.setId(id);
                if (train.getAvailableCount() - count >= 0) {
                    pnr.setStatus("CNF");
                } else {
                    pnr.setStatus("WL");
                }
                pnr.setPassengerList(passenger);
                train.setPassengersList(passenger);
            }
            double price = count * train.getPrice();
            pnr.setPrice(price);
            pnr.setCount(count);
            System.out.println("Total ticket fare : " + price);
            displayPay(train, count, pnr);
        } catch (InputMismatchException e) {
            System.out.println("Input must be number ");
            addPassenger(train);
        }
    }

    public void displayPay(Train train, int count, PNR pnr) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("1 --> Pay");
            System.out.println("2 --> cancel");
            int input = in.nextInt();
            if (input == 1) {
                if (pnr.getStatus().equals("CNF")) {
                    train.setAvailableCount(train.getAvailableCount() - count);
                }
                DataLayer.getInstance().setPnrList(pnr);
                System.out.println("Booked successfully !!!! your PNR is " + pnr.getPnrNumber());
                menu();
            } else if (input == 2) {
                System.out.println("cancelled successfully");
                menu();
            } else {
                System.out.println("invalid input");
                menu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be number ");
            displayPay(train, count, pnr);
        }

    }

    public void displayTrain(Train train) {
        System.out.println();
        System.out.println("Name : " + train.getName());
        System.out.println("Number : " + train.getNumber());
        System.out.println("Departure : " + train.getDeparture());
        System.out.println("Arrival : " + train.getArrival());
        System.out.println("From : " + train.getFrom());
        System.out.println("To : " + train.getTo());
        System.out.println("Routes : " + train.getRoutes());
        System.out.println("Seat count : " + train.getSeatCount());
        System.out.println("Available count : " + train.getAvailableCount());
        System.out.println("Fare : " + train.getPrice());
        System.out.println();
    }

    public void showAlert() {
        System.out.println("Not a valid train ");
        menu();
    }

    public void displayTrainByPNR(PNR pnr) {
        for (Train train : DataLayer.getInstance().getTrainList()) {
            if (train.getNumber() == pnr.getTrainNumber()) {
                displayTrain(train);
                return;
            }
        }
        System.out.println("Train Not found");
    }

    public void displayPassenger(PNR pnr) {
        System.out.println();
        for (Passenger passenger : pnr.getPassengerList()) {
            System.out.println("Name : " + passenger.getName() + " || AGE : " + passenger.getAge() + " || ID : " + passenger.getId() + " || Gender : " + passenger.getGender() + " || PNR : " + pnr.getPnrNumber() + " || Status : " + pnr.getStatus());
        }
        System.out.println();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int askConfirmOrCancel() {
        int input = 0;
        try {
            Scanner in = new Scanner(System.in);
            System.out.println(" 1 --> confirm");
            System.out.println(" 2 --> cancel");
            input = in.nextInt();
            return input;
        } catch (Exception e) {
            System.out.println("Input must be number ");
            askConfirmOrCancel();
        }
        return input;
    }
}
