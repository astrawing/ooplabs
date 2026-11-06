public class Main {
    public static void main(String[] args) {
        HotelDirector director = new HotelDirector();

        Hotel luxury = director.buildLuxuryHotel("Grand Palace", "Москва", 2, 3);
        luxury.printInfo();

        System.out.println();
        Hotel boutique = director.buildBoutiqueHotel("Лофт", "Санкт-Петербург", 3, 2);
        boutique.printInfo();

        System.out.println();
        Hotel budget = director.buildBudgetHotel("Уют", "Казань", 3, 3);
        budget.printInfo();

        System.out.println("\nДемонстрация функционала заселения:");
        budget.findRoomByNumber(101).orElseThrow().checkIn();
        budget.findRoomByNumber(202).orElseThrow().checkIn();
        budget.findRoomByNumber(301).orElseThrow().checkIn();
        System.out.println("Свободно мест: " + budget.getAvailableCapacity());

        System.out.println("\nПопытка заселиться в уже занятый номер 202:");
        budget.findRoomByNumber(202).orElseThrow().checkIn();

        System.out.println();

        Hotel custom = new Hotel.Builder()
                .name("LilBlue").location("Сочи").stars(3).floors(2)
                .addStandardRoom(101, 1)
                .addStandardRoom(102, 1)
                .addRoom(201, 2, RoomType.LUX)
                .build();
        custom.printInfo();

        try {
            new Hotel.Builder()
                    .name("X").location("Y").stars(2).floors(2)
                    .addStandardRoom(101, 1)
                    .addStandardRoom(101, 1)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        }

        try {
            new Hotel.Builder()
                    .name("X").location("Y").stars(2).floors(2)
                    .addStandardRoom(501, 5)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Hotel.Builder()
                    .name("X").location("Y").stars(2)
                    .addStandardRoom(501, 5)
                    .floors(2)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Hotel.Builder().name("X").location("Y").stars(2).build();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            director.buildBudgetHotel("X", "Y", 0, 5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}