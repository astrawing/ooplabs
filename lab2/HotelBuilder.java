import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelBuilder {
    private String name;
    private String location;
    private Integer stars;
    private Integer floors;
    private final List<Room> rooms = new ArrayList<>();
    private final Set<Integer> usedNumbers = new HashSet<>();

    public HotelBuilder name(String name) { this.name = name; return this; }
    public HotelBuilder location(String location) { this.location = location; return this; }
    public HotelBuilder stars(int stars) { this.stars = stars; return this; }
    public HotelBuilder floors(int floors) { this.floors = floors; return this; }

    public HotelBuilder addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Комната не может быть null");
        }
        if (!usedNumbers.add(room.getRoomNumber())) {
            throw new IllegalArgumentException(
                    "Номер комнаты " + room.getRoomNumber() + " уже используется");
        }
        if (floors != null && room.getFloor() > floors) {
            throw new IllegalArgumentException(String.format(
                    "Комната №%d на этаже %d выше высоты отеля (%d)",
                    room.getRoomNumber(), room.getFloor(), floors));
        }
        rooms.add(room);
        return this;
    }

    public HotelBuilder addStandardRoom(int roomNumber, int floor) {
        return addRoom(new Room(roomNumber, floor, 2, 18.0, 3000.0));
    }

    public HotelBuilder addLuxRoom(int roomNumber, int floor) {
        return addRoom(new Room(roomNumber, floor, 4, 60.0, 12000.0));
    }

    public Hotel build() {
        if (name == null)     throw new IllegalStateException("Не задано name");
        if (location == null) throw new IllegalStateException("Не задано location");
        if (stars == null)    throw new IllegalStateException("Не задано stars");
        if (floors == null)   throw new IllegalStateException("Не задано floors");
        return new Hotel(name, location, stars, floors, rooms);
    }
}