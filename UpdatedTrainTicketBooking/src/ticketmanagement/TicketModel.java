package ticketmanagement;

import datalayer.DataLayer;
import model.PNR;
import model.Passenger;
import model.Train;

public class TicketModel {
    private TicketView ticketView;

    public TicketModel(TicketView ticketView) {
        this.ticketView = ticketView;
    }

    public void getAvailableTrains(String from, String to) {
        for (Train train : DataLayer.getInstance().getTrainList()) {
            if (train.getFrom().equalsIgnoreCase(from) || train.getTo().equalsIgnoreCase(from) ||
                    train.getRoutes().contains(from)) {
                if (train.getFrom().equalsIgnoreCase(to) || train.getTo().equalsIgnoreCase(to) ||
                        train.getRoutes().contains(to)) {
                    ticketView.displayTrain(train);
                }
            }
        }
    }

    public void validateTrain(int trainNumber) {
        for (Train train : DataLayer.getInstance().getTrainList()) {
            if (train.getNumber() == trainNumber) {
                ticketView.addPassenger(train);
                return;
            }
            ticketView.showAlert();
        }
    }

    public void validatePNR(int pnrNumber) {
        for (PNR pnr : DataLayer.getInstance().getPnrList()) {
            if (pnr.getPnrNumber() == pnrNumber) {
                ticketView.displayTrainByPNR(pnr);
                ticketView.displayPassenger(pnr);
                return;
            }
        }
        ticketView.showMessage("PNR not found");
    }

    public void cancelTicketByPNR(int pnrNumber) {
        for (PNR pnr : DataLayer.getInstance().getPnrList()) {
            if (pnr.getPnrNumber() == pnrNumber) {
                setAvailableCount(pnr);
                return;
            }
        }
    }

    public void setAvailableCount(PNR pnr) {
        for (Train train : DataLayer.getInstance().getTrainList()) {
            if (pnr.getTrainNumber() == train.getNumber()) {
                switch (pnr.getStatus()) {
                    case "CNF" -> {
                        train.setAvailableCount(train.getAvailableCount() + pnr.getCount());
                        pnr.setStatus("CANCELLED");
                        ticketView.showMessage("Ticket cancelled successfully !!! Amount " + pnr.getPrice() + " will be refunded ");
                        return;
                    }
                    case "WL" -> {
                        pnr.setStatus("CANCELLED");
                        ticketView.showMessage("Ticket cancelled successfully !!! Amount " + pnr.getPrice() + " will be refunded ");
                        return;
                    }
                    case "CANCELLED" -> {
                        ticketView.showMessage("Ticket already cancelled !!!");
                        return;
                    }
                }
            }
        }
    }

    public void validateTrainToViewBooked(int trainNumber) {
        boolean trainNotFound = true;
        for (PNR pnr : DataLayer.getInstance().getPnrList()) {
            if (pnr.getTrainNumber() == trainNumber) {
                trainNotFound = false;
                for (Passenger passenger : pnr.getPassengerList()) {
                    System.out.println("Name : " + passenger.getName() +
                            " || Age : " + passenger.getAge() + " || Gender : " + passenger.getGender() + " || ID : " + passenger.getId() +
                            " || PNR: " + pnr.getPnrNumber() + " || Status : " + pnr.getStatus());
                }
            }
        }
        if (trainNotFound) {
            ticketView.showMessage("Train Not Found !!!");
        }
    }

    public void searchPassenger(int id) {
        for (PNR pnr : DataLayer.getInstance().getPnrList()) {
            for (Passenger passenger : pnr.getPassengerList()) {
                if (passenger.getId() == id) {
                    System.out.println("Name : " + passenger.getName() + " || AGE : " + passenger.getAge() + " || Gender : " + passenger.getAge() + " || ID : " + passenger.getId());
                    return;
                }
            }
        }
        ticketView.showMessage("Please enter valid ID");
    }

    public void validateTicketStatus(int pnrNumber) {
        for (PNR pnr : DataLayer.getInstance().getPnrList()) {
            if (pnr.getPnrNumber() == pnrNumber) {
                if (pnr.getStatus().equals("CANCELLED")) {
                    ticketView.showMessage("CANCELLED ticket not eligible for change status !!! ");
                    return;
                } else if (pnr.getStatus().equals("CNF")) {
                    ticketView.showMessage("Ticket already in confirmed status !!!");
                    return;
                } else {
                    for (Train train : DataLayer.getInstance().getTrainList()) {
                        if (train.getNumber() == pnr.getTrainNumber()) {
                            changeTicketStatus(train, pnr);
                            return;
                        }
                    }
                }
            }
        }
        ticketView.showMessage("PNR not found !!!");
    }

    public void changeTicketStatus(Train train, PNR pnr) {
        ticketView.displayTrain(train);
        if (train.getAvailableCount() >= pnr.getCount()) {
            ticketView.showMessage("Ticket eligible to change status from WL to CNF");
            int confirmORCancel = ticketView.askConfirmOrCancel();
            if (confirmORCancel == 1) {
                train.setAvailableCount(train.getAvailableCount() - pnr.getCount());
                pnr.setStatus("CNF");
                ticketView.showMessage("Status changed successfully");
            } else if (confirmORCancel == 2) {
                ticketView.showMessage("Status change process terminated !!!");
            } else {
                ticketView.showMessage("Please enter valid input ");
                changeTicketStatus(train, pnr);
            }
        } else {
            ticketView.showMessage("Number of available tickets is less than Number of passenger in the given PNR !!!");
        }
    }


}
