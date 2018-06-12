import java.util.List;

class BoardingVisualizer {

    private AircraftCrowd aircraftCrowd;
    private Gui gui;

    public BoardingVisualizer() {
        PassengerGenerator passengerGenerator = new PassengerGeneratorRandom();
        List<Passenger> passengers = passengerGenerator.generate(30, 6);
        aircraftCrowd = new SingleAisleCrowd(passengers);
        gui = new BoardingVisualizerGui();
    }

    public void start() {
        gui.show(aircraftCrowd);

        while (aircraftCrowd.isBoardingInProgress()) {
            aircraftCrowd.step();
            gui.show(aircraftCrowd);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BoardingVisualizer bv = new BoardingVisualizer();
        bv.start();
    }

}