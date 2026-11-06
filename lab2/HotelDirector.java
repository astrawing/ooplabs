public class HotelDirector {
    public Hotel buildBudgetHotel(String name, String location, int floors, int roomsPerFloor) {
        validateSize(floors, roomsPerFloor);
        Hotel.Builder builder = new Hotel.Builder()
                .name(name).location(location).stars(2).floors(floors);
        for (int floor = 1; floor <= floors; floor++) {
            for (int i = 1; i <= roomsPerFloor; i++) {
                builder.addStandardRoom(roomNumber(floor, i), floor);
            }
        }
        return builder.build();
    }

    public Hotel buildLuxuryHotel(String name, String location, int floors, int roomsPerFloor) {
        validateSize(floors, roomsPerFloor);
        Hotel.Builder builder = new Hotel.Builder()
                .name(name).location(location).stars(5).floors(floors);
        for (int floor = 1; floor <= floors; floor++) {
            for (int i = 1; i <= roomsPerFloor; i++) {
                builder.addLuxRoom(roomNumber(floor, i), floor);
            }
        }
        return builder.build();
    }

    public Hotel buildBoutiqueHotel(String name, String location, int floors, int roomsPerFloor) {
        validateSize(floors, roomsPerFloor);
        Hotel.Builder builder = new Hotel.Builder()
                .name(name).location(location).stars(4).floors(floors);
        for (int floor = 1; floor <= floors; floor++) {
            RoomType type = (floor == floors) ? RoomType.LUX : RoomType.STANDARD;
            for (int i = 1; i <= roomsPerFloor; i++) {
                builder.addRoom(roomNumber(floor, i), floor, type);
            }
        }
        return builder.build();
    }

    private static int roomNumber(int floor, int indexOnFloor) {
        return floor * 100 + indexOnFloor;
    }

    private static void validateSize(int floors, int roomsPerFloor) {
        if (floors < 1) {
            throw new IllegalArgumentException("Этажей должно быть >= 1, получено: " + floors);
        }
        if (roomsPerFloor < 1) {
            throw new IllegalArgumentException("Номеров на этаже должно быть 1.. , получено: " + roomsPerFloor);
        }
    }
}