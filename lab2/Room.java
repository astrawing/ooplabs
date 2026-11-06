public class Room {
    private final int roomNumber;
    private final int floor;
    private final int capacity;
    private final double area;
    private final double basePrice;
    private boolean occupied;

    public Room(int roomNumber, int floor, int capacity, double area, double basePrice) {
        if (roomNumber < 1) {
            throw new IllegalArgumentException("Номер комнаты должен быть >= 1");
        }
        if (floor < 1) {
            throw new IllegalArgumentException("Этаж должен быть >= 1");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Вместимость должна быть >= 1");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Площадь должна быть > 0");
        }
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть > 0");
        }
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
        this.area = area;
        this.basePrice = basePrice;
        this.occupied = false;
    }

    public Room copy() {
        return new Room(roomNumber, floor, capacity, area, basePrice);
    }

    public int getRoomNumber() { return roomNumber; }
    public int getFloor() { return floor; }
    public int getCapacity() { return capacity; }
    public double getArea() { return area; }
    public double getBasePrice() { return basePrice; }

    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    public String describe() {
        return String.format("№%d | Этаж %d | %d чел. | %.1f м² | %.0f руб./ночь | %s",
                roomNumber, floor, capacity, area, basePrice,
                occupied ? "занят" : "свободен");
    }

    public boolean isSuitableFor(int guests) { return capacity >= guests; }

    public double calculatePrice(int nights) { return basePrice * nights; }

    public boolean isLux() { return basePrice >= 10000.0 && area >= 50.0; }

    public boolean checkIn() {
        if (occupied) {
            System.out.println("Номер " + this.roomNumber + " уже занят.");
            return false;
        }
        occupied = true;
        System.out.println("Успешное заселение в номер " + this.roomNumber + ".");
        return true;
    }

    public boolean checkOut() {
        if (!occupied) {
            System.out.println("Номер " + this.roomNumber + " уже свободен.");
            return false;
        }
        occupied = false;
        System.out.println("Успешное выселение из номера " + this.roomNumber + ".");
        return true;
    }
}