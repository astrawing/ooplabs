import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hotel {
    private String name;
    private String location;
    private int stars;
    private int floors;
    private List<Room> rooms;

    public Hotel(String name, String location, int stars, int floors, List<Room> rooms) {
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
        for (Room r : rooms) {
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
        this.rooms = new ArrayList<>(rooms);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }

    public int getFloors() { return floors; }
    public void setFloors(int floors) { this.floors = floors; }

    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }

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
        System.out.println("=== " + name + " ===");
        System.out.println("Локация: " + location + ", звёзд: " + stars
                + ", этажей: " + floors);
        System.out.println("Номеров: " + rooms.size()
                + ", общая вместимость: " + getTotalCapacity() + " чел."
                + ", свободно мест: " + getAvailableCapacity());
        System.out.println("Средняя площадь: " + String.format("%.1f", getAverageArea()) + " м²");
        rooms.forEach(r -> System.out.println("  • " + r.describe()));
    }
}