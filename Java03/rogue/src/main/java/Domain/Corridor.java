package Domain;

import java.util.ArrayList;
import java.util.List;

public class Corridor {
    private final List<MapPoint> corridorPoints;

    public Corridor(Coordinate begin, Coordinate end, int rotation) {
        corridorPoints = new ArrayList<>();

        int x1 = begin.getX();
        int y1 = begin.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (rotation == Final.LEFT || rotation == Final.RIGHT) {
            int halfX = (x1 + x2) / 2;
            if (x1 < x2) {
                for (int x = x1; x <= halfX; x++) {
                    corridorPoints.add(new MapPoint(new Coordinate(x, y1), false));
                }
            } else {
                for (int x = x1; x >= halfX; x--) {
                    corridorPoints.add(new MapPoint(new Coordinate(x, y1), false));
                }
            }

            if (y1 < y2) {
                for (int y = y1; y <= y2; y++) {
                    corridorPoints.add(new MapPoint(new Coordinate(halfX, y), false));
                }
            } else {
                for (int y = y1; y >= y2; y--) {
                    corridorPoints.add(new MapPoint(new Coordinate(halfX, y), false));
                }
            }

            if (x1 < x2) {
                for (int x = halfX + 1; x <= x2; x++) {
                    corridorPoints.add(new MapPoint(new Coordinate(x, y2), false));
                }
            } else {
                for (int x = halfX - 1; x >= x2; x--) {
                    corridorPoints.add(new MapPoint(new Coordinate(x, y2), false));
                }
            }
        } else if (rotation == Final.UP || rotation == Final.DOWN) {
            int halfY = (y1 + y2) / 2;
            if (y1 < y2) {
                for (int y = y1; y <= halfY; y++) {
                    corridorPoints.add(new MapPoint(new Coordinate(x1, y), false));
                }
            } else {
                for (int y = y1; y >= halfY; y--) {
                    corridorPoints.add(new MapPoint(new Coordinate(x1, y), false));
                }
            }
            if (x1 < x2) {
                for (int x = x1; x <= x2; x++) {
                    corridorPoints.add(new MapPoint(new Coordinate(x, halfY), false));
                }
            } else {
                for (int x = x1; x >= x2; x--) {
                    corridorPoints.add(new MapPoint(new Coordinate(x, halfY), false));
                }
            }

            if (y1 < y2) {
                for (int y = halfY + 1; y <= y2; y++) {
                    corridorPoints.add(new MapPoint(new Coordinate(x2, y), false));
                }
            } else {
                for (int y = halfY - 1; y >= y2; y--) {
                    corridorPoints.add(new MapPoint(new Coordinate(x2, y), false));
                }
            }
        }
    }

    public List<MapPoint> getPoints() {
        return corridorPoints;
    }
}