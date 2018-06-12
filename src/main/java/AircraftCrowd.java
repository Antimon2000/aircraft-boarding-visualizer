import java.util.List;

interface AircraftCrowd {

    public boolean isBoardingInProgress();

    public void step();

    public List<Cell> getCrowdMap();

}
