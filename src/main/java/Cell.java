import java.util.ArrayList;
import java.util.List;

public class Cell {

    private Position position;
    private Passenger passenger;
    private List<Cell> neighbors;

    public Cell(Position position) {
        this(position, null);
    }

    public Cell(Position position, Passenger passenger) {
        this.position = position;
        this.passenger = passenger;
        neighbors = new ArrayList<>();
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAisleCell() {
        return neighbors.size() >= 2;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void removePassenger() {
        setPassenger(null);
    }

    public boolean hasPathTo(Position key) {
        if (position.equals(key)) {
            return true;
        } else {
            for (Cell neighbor : neighbors) {
                if (neighbor.hasPathTo(key))
                    return true;
            }
        }
        return false;
    }

    public boolean hasFinalPassenger() {
        return (getPassenger() != null) && getPosition().equals(getPassenger().getSeat());
    }

    public boolean isOccupied() {
        return getPassenger() != null;
    }

    public void addNeighbor(Cell neighbor) {
        if (neighbor != null)
            neighbors.add(neighbor);
    }

    public void step(Cell predecessor) {
        for (Cell neighbor : neighbors) {
            neighbor.step(this);
        }

        Passenger potentialSeatOwner = predecessor.getPassenger();
        if (potentialSeatOwner == null || !hasPathTo(potentialSeatOwner.getSeat()))
            return;

        if (hasFinalPassenger()) {
            predecessor.setPassenger(getPassenger());
            setPassenger(potentialSeatOwner);
        }

        if (!isOccupied()) {
            setPassenger(potentialSeatOwner);
            predecessor.removePassenger();
        }
    }

}
