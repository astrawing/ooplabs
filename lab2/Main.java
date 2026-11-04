public class Main {
    public static void main(String[] args) {
        Hotel luxury = new HotelBuilder()
                .name("Grand Palace")
                .location("Москва")
                .stars(5)
                .floors(5)
                .addLuxRoom(1, 1).addLuxRoom(2, 1)
                .addStandardRoom(3, 1)
                .addLuxRoom(4, 1).addLuxRoom(5, 1)
                .addStandardRoom(6, 1)
                .build();
        luxury.printInfo();

        System.out.println("\nПоиск комнаты 3:");
        Room r = luxury.findRoomByNumber(3).orElseThrow();
        System.out.println(r.describe());

        System.out.println();

        Hotel budget = new HotelBuilder()
                .name("Уют")
                .location("Казань")
                .stars(2)
                .floors(3)
                .addStandardRoom(101, 1).addStandardRoom(102, 1).addStandardRoom(103, 1)
                .addStandardRoom(201, 2).addStandardRoom(202, 2).addStandardRoom(203, 2)
                .addStandardRoom(301, 3).addStandardRoom(302, 3).addStandardRoom(303, 3)
                .build();
        budget.printInfo();

        System.out.println("");

        System.out.println("=== Заселение в номера 101, 202, 301 ===");
        budget.findRoomByNumber(101).orElseThrow().checkIn();
        budget.findRoomByNumber(202).orElseThrow().checkIn();
        budget.findRoomByNumber(301).orElseThrow().checkIn();
        budget.printInfo();

        System.out.println("");
        budget.findRoomByNumber(202).orElseThrow().checkIn();
        
        try {
            new HotelBuilder()
                    .name("X").location("Y").stars(2).floors(2)
                    .addStandardRoom(101, 1)
                    .addStandardRoom(101, 1)  
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[Дубликат номера] " + e.getMessage());
        }

        try {
            new HotelBuilder()
                    .name("X").location("Y").stars(2).floors(2)
                    .addStandardRoom(501, 5)   
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("[Ошибка этажа] " + e.getMessage());
        }
    }
}