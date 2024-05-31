package datalayer;

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

    public ArrayList<Train> getTrainList() {
        return trainList;
    }
    public void setTrainList(Train train) {
        trainList.add(train);
    }
}
