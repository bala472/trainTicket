package train;

import datalayer.DataLayer;
import model.Train;

import java.util.Scanner;

public class TrainModel {
    TrainView trainView;
    public TrainModel(TrainView trainView){
        this.trainView = trainView;
    }
    Scanner in = new Scanner(System.in);
    public void trainSetup(){
        Train train = new Train();
        System.out.println("Enter name : ");
        train.setName(in.nextLine());
        System.out.println("Enter from :");
        train.setFrom(in.nextLine());
        System.out.println("Enter to : ");
        train.setTo(in.nextLine());
        System.out.println("Enter departure");
        train.setDeparture(in.nextLine());
        System.out.println("Enter arrival :");
        train.setArrival(in.nextLine());
        setupRoute(train);
        System.out.println("Enter number : ");
        train.setNumber(in.nextInt());
        System.out.println("Enter PNR : ");
        train.setPnr(in.nextInt());
        System.out.println("Enter number of seats");
        train.setSeatCount(in.nextInt());
        System.out.println("Enter ticket price");
        train.setPrice(in.nextInt());
        DataLayer.getInstance().setTrainList(train);
        trainView.showMessage("Process Completed Successfully");
    }
    private void setupRoute(Train train) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of stops : ");
        int nos = in.nextInt();
        for(int i=1;i<=nos;i++){
            System.out.println("Enter stop "+i+" name : ");
            train.setRoutes(sc.nextLine());
        }
    }
}
