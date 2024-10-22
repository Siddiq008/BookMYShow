package BookMyShow;

public class BookMyShow {
    // LA Cinema
    // Sona mina
    // INOX
    // IMAX

//    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        CreateData createData = new CreateData();
        createData.createTheaters();
        createData.createMovies();

        BookingManager bookingManager = BookingManager.getInstance();
        bookingManager.bookMyTicket();
        System.out.println("Exiting the system ... ");
    }



//    public static void createTheater(){
//       String theaterName = scanner.next();
//       int screenCount = scanner.nextInt();
//       Theater theater = new Theater(theaterName, screenCount);
//       int theaterID = theater.getTheaterID();
//       theaterHashMap.put(theaterID, theater);
//       createScreens(theaterID, screenCount);
//    }
//    public static void createScreens(int theaterID, int screenCount){
//        Theater theater = theaterHashMap.get(theaterID);
//        int seatsPerScreen;
//        for (int i = 0; i < screenCount; i++) {
//            System.out.println("Enter total no of seats in screen " + (i+1) + " : ");
//            seatsPerScreen = scanner.nextInt();
//            Screen screen = new Screen(theaterID, seatsPerScreen);
//            theater.setScreens(screen.getScreenID());
//        }
//    }
//    public static void createCinema(){
//        System.out.println("Enter a movie name : ");
//        String movieName = scanner.next();
//        Cinema cinema = new Cinema(movieName);
//        cinemaHashMap.put(cinema.getCinemaID(), cinema);
//    }
}
