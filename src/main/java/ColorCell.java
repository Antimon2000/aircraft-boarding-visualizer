import java.awt.*;

public class ColorCell {

    private int row;
    private int column;
    private Color color;
    private String text;


    public ColorCell(int row, int column, Color color, String text) {
        this.row = row;
        this.column = column;
        this.color = color;
        this.text = text;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
