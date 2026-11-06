public enum RoomType {
    STANDARD(2, 18.0, 3000.0),
    LUX(4, 60.0, 12000.0);

    private final int capacity;
    private final double area;
    private final double basePrice;

    RoomType(int capacity, double area, double basePrice) {
        this.capacity = capacity;
        this.area = area;
        this.basePrice = basePrice;
    }

    public Room createRoom(int roomNumber, int floor) {
        return new Room(roomNumber, floor, capacity, area, basePrice);
    }
}