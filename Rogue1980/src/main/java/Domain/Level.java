package Domain;

import java.util.*;

public class Level {
    private final List<Room> rooms;
    private final List<Corridor> corridors;
    private final boolean[][] connections;
    private Coordinate startPoint;
    private MapPoint endPoint;
    private int startRoom;
    private int endRoom;

    public Level(int n) {
        rooms = new ArrayList<>();
        corridors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rooms.add(new Room(i, j));
            }
        }
        connections = new boolean[Final.NUM_ROOMS][Final.NUM_ROOMS];
        generateConnections();
        createCorridors();
        createStartEndPosition();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i * 3 + j != startRoom) rooms.get(i * 3 + j).addEnemy(n);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rooms.get(i * 3 + j).addItem(n);
            }
        }
    }

    private void generateConnections() { // генерация тунелей
        generateMST();
        addRandomConnections();
        if (!isConnected()) {
            generateConnections();
        }
    }

    private void generateMST() {
        boolean[] visited = new boolean[Final.NUM_ROOMS];
        Random random = new Random();

        // Начинаем с первой комнаты
        visited[0] = true;

        // Пока не соединим все комнаты
        while (true) {
            boolean added = false;
            for (int i = 0; i < Final.NUM_ROOMS; i++) {
                if (visited[i]) {
                    for (int j = 0; j < Final.NUM_ROOMS; j++) {
                        // Проверяем, что комнаты находятся рядом в сетке
                        if (!visited[j] && isAdjacent(i, j) && random.nextBoolean()) {
                            connections[i][j] = true;
                            connections[j][i] = true;
                            visited[j] = true;
                            added = true;
                        }
                    }
                }
            }
            if (!added) break; // Если ничего не добавили, выходим
        }
    }

    private void addRandomConnections() { // добавление случайных тунелей
        Random random = new Random();
        int extraTunnels = random.nextInt(5);
        for (int i = 0; i < extraTunnels; i++) {
            int room1 = random.nextInt(Final.NUM_ROOMS);
            int room2 = random.nextInt(Final.NUM_ROOMS);
            if (room1 != room2 && !connections[room1][room2] && isAdjacent(room1, room2)) {
                connections[room1][room2] = true;
                connections[room2][room1] = true;
            }
        }
    }

    private boolean isConnected() { // проверка связности графа
        boolean[] visited = new boolean[Final.NUM_ROOMS];
        Queue<Integer> queue = new LinkedList<>();

        // Начинаем с первой комнаты
        queue.add(0);
        visited[0] = true;

        // Поиск в ширину (BFS)
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < Final.NUM_ROOMS; i++) {
                if (connections[current][i] && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        // Проверяем, все ли комнаты посещены
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    private boolean isAdjacent(int room1, int room2) {
        int row1 = room1 / Final.ROOMS_PER_SIDE;
        int col1 = room1 % Final.ROOMS_PER_SIDE;
        int row2 = room2 / Final.ROOMS_PER_SIDE;
        int col2 = room2 % Final.ROOMS_PER_SIDE;
        return Math.abs(row1 - row2) + Math.abs(col1 - col2) == 1;
    }

    private Coordinate generateBeginEndCorridor(int direction, Room room) {
        Random random = new Random();
        int x = 0;
        int y = 0;

        if (direction == Final.UP) {
            y = room.getUpLeft().getY(); // Верхняя граница комнаты
            x = random.nextInt(room.getWidth() - 2) + 1 + room.getUpLeft().getX(); // Случайная координата x внутри комнаты
        } else if (direction == Final.RIGHT) {
            x = room.getBotRight().getX(); // Правая граница комнаты
            y = random.nextInt(room.getHeight() - 2) + 1 + room.getUpLeft().getY(); // Случайная координата y внутри комнаты
        } else if (direction == Final.DOWN) {
            y = room.getBotRight().getY(); // Нижняя граница комнаты
            x = random.nextInt(room.getWidth() - 2) + 1 + room.getUpLeft().getX(); // Случайная координата x внутри комнаты
        } else if (direction == Final.LEFT) {
            x = room.getUpLeft().getX(); // Левая граница комнаты
            y = random.nextInt(room.getHeight() - 2) + 1 + room.getUpLeft().getY(); // Случайная координата y внутри комнаты
        }

        return new Coordinate(x, y);
    }

    private int direction(int room1, int room2) {
        int row1 = room1 / Final.ROOMS_PER_SIDE;
        int col1 = room1 % Final.ROOMS_PER_SIDE;
        int row2 = room2 / Final.ROOMS_PER_SIDE;
        int col2 = room2 % Final.ROOMS_PER_SIDE;

        if (row1 < row2) return Final.DOWN;
        if (row1 > row2) return Final.UP;
        if (col1 < col2) return Final.RIGHT;
        if (col1 > col2) return Final.LEFT;
        return -1;
    }

    private void createCorridors() {
        for (int i = 0; i < Final.NUM_ROOMS; i++) {
            for (int j = 0; j < Final.NUM_ROOMS; j++) {
                if (connections[i][j]) {
                    if (j < i) continue;
                    Coordinate begin = generateBeginEndCorridor(direction(i, j), rooms.get(i));
                    Coordinate end = generateBeginEndCorridor(direction(j, i), rooms.get(j));
                    corridors.add(new Corridor(begin, end, direction(i, j)));
                }
            }
        }
    }

    private void createStartEndPosition() {
        Random rand = new Random();
        this.startRoom = rand.nextInt(9);
        this.endRoom = startRoom;
        while (endRoom == startRoom) {
            this.endRoom = rand.nextInt(9);
        }
        int x = rand.nextInt(rooms.get(startRoom).getWidth() - 2) + 1 + rooms.get(startRoom).getUpLeft().getX();
        int y = rand.nextInt(rooms.get(startRoom).getHeight() - 2) + 1 + rooms.get(startRoom).getUpLeft().getY();
        this.startPoint = new Coordinate(x, y);
        x = rand.nextInt(rooms.get(endRoom).getWidth() - 2) + 1 + rooms.get(endRoom).getUpLeft().getX();
        y = rand.nextInt(rooms.get(endRoom).getHeight() - 2) + 1 + rooms.get(endRoom).getUpLeft().getY();
        this.endPoint = new MapPoint(new Coordinate(x, y), false);
        this.rooms.get(startRoom).addUsed(this.startPoint);
        this.rooms.get(endRoom).addUsed(this.endPoint.getCoordinate());
    }

    public void changeVisibility(Coordinate coordinate) {
        for (Room room : rooms) {
            if (room.checkInRoom(coordinate) || room.checkOnWall(coordinate)) {
                room.makeVisibleWallPoints();
                room.makeVisibleRoomPoint();
            } else {
                room.makeInvisibleRoomPoint();
                endPoint.setVisibility(false);
            }
        }
        for (Room room : rooms) {
            if (room.checkInRoom(coordinate) || room.checkOnWall(coordinate)) {
                if (room.checkInRoom(endPoint.getCoordinate())) endPoint.setVisibility(true);
            }
        }
        for (Corridor corridor : corridors) {
            for (MapPoint point : corridor.getPoints()) {
                if ((point.getCoordinate().getX() + 1 == coordinate.getX() && point.getCoordinate().getY() == coordinate.getY()) ||
                        (point.getCoordinate().getX() - 1 == coordinate.getX() && point.getCoordinate().getY() == coordinate.getY()) ||
                        (point.getCoordinate().getY() + 1 == coordinate.getY() && point.getCoordinate().getX() == coordinate.getX()) ||
                        (point.getCoordinate().getY() - 1 == coordinate.getY() && point.getCoordinate().getX() == coordinate.getX()))
                    point.setVisibility(true);
            }
        }
        for (Room room : rooms) {
            if (room.isWallsVisible()) {
                for (Corridor corridor : corridors) {
                    for (MapPoint point : corridor.getPoints()) {
                        if (room.checkOnWall(point.getCoordinate())) point.setVisibility(true);
                    }
                }
            }
        }
    }

    public void moveEnemies(Coordinate coordinate) {
        for (Room room : rooms) {
            if (room.checkInRoom(coordinate)) room.moveEnemies(coordinate);
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public Coordinate getStartPoint() {
        return startPoint;
    }

    public MapPoint getEndPoint() {
        return endPoint;
    }
}
