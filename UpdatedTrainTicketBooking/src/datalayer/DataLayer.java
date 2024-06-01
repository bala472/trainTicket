package datalayer;

import model.PNR;
import model.Train;

import java.util.ArrayList;

public class DataLayer {

    private static DataLayer dataLayer;

    public static DataLayer getInstance() {
        if (dataLayer == null) {
            dataLayer = new DataLayer();
        }
        return dataLayer;
    }

    ArrayList<Train> trainList = new ArrayList<>();
    ArrayList<PNR> pnrList = new ArrayList<>();

    public ArrayList<PNR> getPnrList() {
        return pnrList;
    }

    public void setPnrList(PNR pnr) {
        this.pnrList.add(pnr);
    }

    public ArrayList<Train> getTrainList() {
        return trainList;
    }

    public void setTrainList(Train train) {
        trainList.add(train);
    }
}
