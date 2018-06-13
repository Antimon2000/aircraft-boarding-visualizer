public class Passenger {

    private Position seat;
    private int baggageDelay;
    private boolean storesBaggage;

    public Passenger(Position seat, int baggageDelay) {
        this.seat = seat;
        this.baggageDelay = baggageDelay;
        storesBaggage = false;
    }

    public Position getSeat() {
        return seat;
    }

    public void setSeat(Position seat) {
        this.seat = seat;
    }

    public void storeBaggage() {
        storesBaggage = true;
        baggageDelay--;
        if (baggageDelay <= 0) {
            storesBaggage = false;
        }
    }

    public boolean isStoringBaggage() {
        return storesBaggage && baggageDelay >= 0;
    }

    public boolean hasStoredBaggage() {
        return baggageDelay <= 0;
    }

}
