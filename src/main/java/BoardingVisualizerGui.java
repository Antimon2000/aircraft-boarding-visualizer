import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardingVisualizerGui extends JFrame implements Gui {

    private static final Color COLOR_FREE_SEAT = Color.darkGray.brighter().brighter();
    private static final Color COLOR_OCCUPIED = new Color(51, 151, 255);
    private static final Color COLOR_AISLE = Color.lightGray;

    private static final int WIDTH  = 600;
    private static final int HEIGHT = 850;

    private ColorGridComponent colorGrid;
    private Random random;

    public BoardingVisualizerGui() {
        random = new Random();
        setTitle("Aircraft Boarding Visualizer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        colorGrid = new ColorGridComponent(30, 7);
        add(colorGrid, BorderLayout.CENTER);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    @Override
    public void show(AircraftCrowd aircraftCrowd) {
        List<Cell> crowdMap = aircraftCrowd.getCrowdMap();
        List<ColorCell> colorCells = new ArrayList<>();
        for (Cell cell : crowdMap) {
            Position passengerPosition = cell.getPosition();
            Position pos;
            if (passengerPosition.getSeat() == 0) {
                pos = new Position(passengerPosition.getRow(), 3);
            } else if (passengerPosition.getSeat() <= 3) {
                pos = new Position(passengerPosition.getRow(), passengerPosition.getSeat() - 1);
            } else {
                pos = passengerPosition;
            }

            if (cell.isOccupied()) {
                colorCells.add(new ColorCell(pos.getRow(), pos.getSeat(), COLOR_OCCUPIED, cell.getPassenger().getSeat().toString()));
            } else {

                if (cell.isAisleCell()) {
                    colorCells.add(new ColorCell(pos.getRow(), pos.getSeat(), COLOR_AISLE, ""));
                } else {
                    colorCells.add(new ColorCell(pos.getRow(), pos.getSeat(), COLOR_FREE_SEAT, ""));
                }

            }
        }
        colorGrid.updateColorCells(colorCells);
    }

    private Color generateColor() {
        double greyValue = 0;

        while (greyValue < 20 || greyValue > 230) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            greyValue = 0.2126 * r + 0.7152 * g + 0.0722 * b;
            return new Color(r,g,b);
        }

        return null;
    }
}
