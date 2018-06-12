import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class ColorGridComponent extends JComponent implements ComponentListener {

    private int rows;
    private int columns;
    private int offsetX;
    private int offsetY;
    private int seatSize;
    private int seatSpace;
    private List<ColorCell> colorCells;

    public ColorGridComponent(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.colorCells = new ArrayList<>();
        addComponentListener(this);
        adjustGridSize();
    }

    public void updateColorCells(List<ColorCell> colorCells) {
        this.colorCells = colorCells;
        repaint();
    }

    private void adjustGridSize() {
        offsetY = getHeight() / 20;
        seatSize = 3 * (getHeight() - 2 * offsetY) / (4 * rows - 1);
        seatSpace = seatSize / 3;
        offsetX = getWidth() / 2 - (columns * seatSize + (columns - 1) * seatSpace) / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ColorCell cell : colorCells) {
            paintCell(cell.getRow(), cell.getColumn(), cell.getColor(), cell.getText(), g);
        }
    }

    private void paintCell(int row, int column, Color color, String text, Graphics g) {
        g.setColor(color);
        int x = offsetX +  column          * (seatSize + seatSpace);
        int y = offsetY + (rows + 1 - row) * (seatSize + seatSpace);
        g.fillRect(x, y, seatSize, seatSize);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 9));
        g.drawString(text, x+1, y+13);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        adjustGridSize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
