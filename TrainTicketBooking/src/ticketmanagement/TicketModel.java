package ticketmanagement;

import datalayer.DataLayer;
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
                    if(train.getFrom().equalsIgnoreCase(to) || train.getTo().equalsIgnoreCase(to) ||
                        train.getRoutes().contains(to)){
                        ticketView.displayTrain(train);
                    }
            }
        }
    }

    public void validateTrain(int trainNumber) {
        for(Train train : DataLayer.getInstance().getTrainList()){
            if(train.getNumber()==trainNumber){
                ticketView.addPassenger(train);
                return;
            }
            ticketView.showAlert();
        }
    }
}
