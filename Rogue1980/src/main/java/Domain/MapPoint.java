package Domain;

public class MapPoint {
    private boolean visibility;
    private final Coordinate coordinate;

    public MapPoint(Coordinate coordinate, boolean visibility) {
        this.coordinate = coordinate;
        setVisibility(visibility);
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
