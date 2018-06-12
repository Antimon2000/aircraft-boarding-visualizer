import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassengerGeneratorRandom implements PassengerGenerator {

    @Override
    public List<Passenger> generate(int rows, int seatsPerRow) {
        List<Passenger> passengers = new ArrayList<>(rows * seatsPerRow);

        for (int row = 1; row <= rows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                Passenger passenger = new Passenger(new Position(row, seat));
                passengers.add(passenger);
            }
        }

        Collections.shuffle(passengers);
        return passengers;
    }

}
