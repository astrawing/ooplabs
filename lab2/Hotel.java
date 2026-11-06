import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class Hotel {
    private final String name;
    private final String location;
    private final int stars;
    private final int floors;
    private final List<Room> rooms;

    private Hotel(String name, String location, int stars, int floors, List<Room> rooms) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название отеля обязательно");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Локация обязательна");
        }
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Звёзд должно быть 1..5, получено: " + stars);
        }
        if (floors < 1) {
            throw new IllegalArgumentException("Этажей должно быть >= 1, получено: " + floors);
        }
        Objects.requireNonNull(rooms, "Список номеров не может быть null");

        Set<Integer> usedNumbers = new HashSet<>();
        for (Room r : rooms) {
            if (r == null) {
                throw new IllegalArgumentException("Список номеров содержит null");
            }
            if (!usedNumbers.add(r.getRoomNumber())) {
                throw new IllegalArgumentException(
                        "Номер комнаты " + r.getRoomNumber() + " уже используется");
            }
            if (r.getFloor() > floors) {
                throw new IllegalArgumentException(String.format(
                        "Комната №%d на этаже %d выше высоты отеля (%d)",
                        r.getRoomNumber(), r.getFloor(), floors));
            }
        }

        this.name = name;
        this.location = location;
        this.stars = stars;
        this.floors = floors;
        this.rooms = List.copyOf(rooms);
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getStars() { return stars; }
    public int getFloors() { return floors; }

    public List<Room> getRooms() { return rooms; }

    public int getTotalCapacity() {
        return rooms.stream().mapToInt(Room::getCapacity).sum();
    }

    public int getAvailableCapacity() {
        return rooms.stream().filter(r -> !r.isOccupied())
                .mapToInt(Room::getCapacity).sum();
    }

    public List<Room> getAvailableRooms() {
        return rooms.stream().filter(r -> !r.isOccupied()).collect(Collectors.toList());
    }

    public Optional<Room> findRoomByNumber(int roomNumber) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst();
    }

    public List<Room> findRoomsForGuests(int guests) {
        return rooms.stream().filter(r -> r.isSuitableFor(guests)).collect(Collectors.toList());
    }

    public List<Room> findLuxRooms() {
        return rooms.stream().filter(Room::isLux).collect(Collectors.toList());
    }

    public double getAverageArea() {
        return rooms.stream().mapToDouble(Room::getArea).average().orElse(0.0);
    }

    public void printInfo() {
        System.out.println("=-=-=-= " + name + " =-=-=-=");
        System.out.println("Локация: " + location + ", звёзд: " + stars
                + ", этажей: " + floors);
        System.out.println("Номеров: " + rooms.size()
                + ", общая вместимость: " + getTotalCapacity() + " чел."
                + ", свободно мест: " + getAvailableCapacity());
        System.out.println("Средняя площадь: " + String.format("%.1f", getAverageArea()) + " м²");
        rooms.forEach(r -> System.out.println("  • " + r.describe()));
    }

    public static final class Builder {
        private String name;
        private String location;
        private Integer stars;
        private Integer floors;
        private final List<Room> rooms = new ArrayList<>();

        public Builder name(String name) { this.name = name; return this; }
        public Builder location(String location) { this.location = location; return this; }
        public Builder stars(int stars) { this.stars = stars; return this; }
        public Builder floors(int floors) { this.floors = floors; return this; }

        public Builder addRoom(Room room) {
            Objects.requireNonNull(room, "Комната не может быть null");
            rooms.add(room.copy()); 
            return this;
        }

        public Builder addRoom(int roomNumber, int floor, RoomType type) {
            Objects.requireNonNull(type, "Тип комнаты не может быть null");
            return addRoom(type.createRoom(roomNumber, floor));
        }

        public Builder addStandardRoom(int roomNumber, int floor) {
            return addRoom(roomNumber, floor, RoomType.STANDARD);
        }

        public Builder addLuxRoom(int roomNumber, int floor) {
            return addRoom(roomNumber, floor, RoomType.LUX);
        }

        public Hotel build() {
            if (name == null)     throw new IllegalStateException("Не задан атрибут name");
            if (location == null) throw new IllegalStateException("Не задан атрибут location");
            if (stars == null)    throw new IllegalStateException("Не задан атрибут stars");
            if (floors == null)   throw new IllegalStateException("Не задан атрибут floors");

            List<Room> rooms_c = new ArrayList<>(rooms.size());
            for (Room r : rooms) {
                rooms_c.add(r.copy());
            }
            return new Hotel(name, location, stars, floors, rooms_c);
        }
    }
}