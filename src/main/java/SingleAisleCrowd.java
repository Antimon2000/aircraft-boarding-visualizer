import java.util.ArrayList;
import java.util.List;

public class SingleAisleCrowd implements AircraftCrowd {

    private static final int NUMBER_ROWS = 30;
    private static final int SEATS_PER_ROW = 6;

    private Cell startCell;
    private List<Cell> aisleCells;
    private List<Cell> cells;
    private List<Passenger> passengers;

    public SingleAisleCrowd(List<Passenger> passengers) {
        this.passengers = passengers;
        cells = new ArrayList<>();
        aisleCells = new ArrayList<>();
        startCell = createSeatMap();
        startCell.setPassenger(passengers.remove(0));
    }

    @Override
    public boolean isBoardingInProgress() {
        return true;
    }

    @Override
    public void step() {
        startCell.step(new Cell(new Position(100,100), null));
        if (!startCell.isOccupied() && !passengers.isEmpty()) {
            startCell.setPassenger(passengers.remove(0));
        }
    }

    @Override
    public List<Cell> getCrowdMap() {
        return cells;
    }


    private Cell createSeatMap() {
        Cell lastAisleCell = null;
        for (int row = NUMBER_ROWS; row >= 1; row--) {
            Cell aisleCell = new Cell(new Position(row, 0));
            cells.add(aisleCell);
            aisleCell.addNeighbor(lastAisleCell);
            lastAisleCell = aisleCell;

            Cell lowerSeatGroup = createLowerSeatGroup(row);
            Cell higherSeatGroup = createHigherSeatGroup(row);
            aisleCell.addNeighbor(lowerSeatGroup);
            aisleCell.addNeighbor(higherSeatGroup);
            aisleCells.add(aisleCell);
        }

        return lastAisleCell;
    }

    private Cell createLowerSeatGroup(int row) {
        Cell aisleNeighbor1 = new Cell(new Position(row,1));
        Cell aisleNeighbor2 = new Cell(new Position(row,2));
        Cell aisleNeighbor3 = new Cell(new Position(row,3));
        cells.add(aisleNeighbor1);
        cells.add(aisleNeighbor2);
        cells.add(aisleNeighbor3);
        aisleNeighbor3.addNeighbor(aisleNeighbor2);
        aisleNeighbor2.addNeighbor(aisleNeighbor1);
        return aisleNeighbor3;
    }

    private Cell createHigherSeatGroup(int row) {
        Cell aisleNeighbor6 = new Cell(new Position(row,6));
        Cell aisleNeighbor5 = new Cell(new Position(row,5));
        Cell aisleNeighbor4 = new Cell(new Position(row,4));
        cells.add(aisleNeighbor6);
        cells.add(aisleNeighbor5);
        cells.add(aisleNeighbor4);
        aisleNeighbor4.addNeighbor(aisleNeighbor5);
        aisleNeighbor5.addNeighbor(aisleNeighbor6);
        return aisleNeighbor4;
    }

}
