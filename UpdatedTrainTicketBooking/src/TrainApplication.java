import train.TrainView;

public class TrainApplication {
    public static void main(String[] args) {
        TrainView trainView = new TrainView();
        trainView.setUp();
        trainView.menu();
    }
}