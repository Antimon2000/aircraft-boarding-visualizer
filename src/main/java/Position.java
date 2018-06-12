public class Position {

    private int row;
    private int seat;

    public Position(int row, int seat) {
        this.row = row;
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return seat == position.seat;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + seat;
        return result;
    }

    @Override
    public String toString() {
        return Integer.toString(getRow()) + Character.toString((char) (getSeat()+64));
    }
}
