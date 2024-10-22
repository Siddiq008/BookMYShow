package BookMyShow;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookingManager {
    private static BookingManager instance;
    private HashMap<Integer, Theater> theaterHashMap;
    private HashMap<Integer, Cinema> cinemaHashMap;
    private HashMap<Integer, Screen> screenHashMap;
    private static final HashMap<Integer, Ticket> ticketHashMap = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    private BookingManager() {}
    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }
    public void bookMyTicket(){
        setInstances();
        while (true){
            System.out.println("\nEnter your option : \n");
            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Show Ticket \n 4. Exit \n");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    getDetails();
                    break;
                case 2:
                    cancelTicket();
                    break;
                case 3:
                    showTicket();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }
    private void getDetails(){
        while (true) {
            System.out.println("\n 1. I know my theater id , and movie id. Book Ticket \n" +
                    "2. I know my theater id , list movies \n" +
                    "3. I know my movie id , list theaters \n" +
                    "4. list all theaters and movies \n" +
                    "5. exit \n" +
                    "Enter your choice :");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    getTicket();
                    return;
                case 2:
                    getTheaterID();
                    break;
                case 3:
                    listAllTheatersAndMovie(true);
                    break;
                case 4:
                    listAllTheatersAndMovie(false);
                    break;
                case 5:
                    System.out.println("Exiting ... ");
                    return;
                default:
                    System.out.println("Enter a valid input ");
            }
        }
    }
    private void setInstances(){
        CreateData createData = CreateData.getInstance();
        theaterHashMap = createData.getTheaterHashMap();
        screenHashMap = createData.getScreenHashMap();
        cinemaHashMap = createData.getCinemaHashMap();
    }

    private void listAllTheatersAndMovie(boolean flag){
        int movieID = 0;
        if(flag){
            movieID = scanner.nextInt();
        }

        for (Map.Entry<Integer, Theater> map : theaterHashMap.entrySet()){
            System.out.println("Theater ID : " +map.getKey() + "     Theater Name : " + map.getValue().getTheaterName() +
                    "\n Screens");
            Theater theater = map.getValue();
            if(flag){
                listTheatersByMovieID(theater, movieID);
            }else {
                listMoviesByTheaterID(theater);
            }
            System.out.println();
        }
    }

    private void getTheaterID(){
        Theater theater = getTheaterIDCheck();
        listMoviesByTheaterID(theater);
    }

    private void listMoviesByTheaterID(Theater theater){
        int [] screens = theater.getScreens();
        for (int i = 0; i < screens.length; i++) {
            int cinemaId = screenHashMap.get(screens[i]).getCinemaID();
            if(cinemaId != 0){
                String movieName = cinemaHashMap.get(cinemaId).getCinemaName();
                System.out.println("Screen no " + i + " Movie : " + movieName + " Movie ID : " + cinemaId);
            }
        }
    }
    private void listTheatersByMovieID(Theater theater, int movieID){
        int [] screens = theater.getScreens();
        for (int i = 0; i < screens.length; i++) {
            int cinemaId = screenHashMap.get(screens[i]).getCinemaID();
            if(cinemaId == movieID){
                System.out.println("Theater ID : " +theater.getTheaterID() + "      Theater Name : " +theater.getTheaterName() + "      Screen " + i);
                return;
            }
            System.out.println();
        }
    }
    private void getTicket(){
        Theater theater = getTheaterIDCheck();
        Cinema cinema = getCinemaIdCheck();
        Screen screen = getScreenId(theater, cinema);
        if(screen == null){
            System.out.println("The cinema " + cinema.getCinemaName() + " is not take placed in "+ theater.getTheaterName());
            return;
        }
        screen.printSeats();
        System.out.println("Enter number of seats you need : ");
        int noOfTickets;
        int totalSeats = screen.getNoOfSeats();
        while (true){
            noOfTickets = scanner.nextInt();
            if(noOfTickets > 0){
                break;
            }
            System.out.println("Enter a number between 1 - " + (totalSeats-1));
        }
        System.out.println("Enter a seat numbers : ");
        int [] bookingSeats = new int[noOfTickets];
        int current;
        for (int i = 0; i < noOfTickets; i++) {
            while (true){
                current = scanner.nextInt();
                if(current > 0 && current < totalSeats){
                    break;
                }
                System.out.println("Enter a number between 1 - " + (totalSeats-1));
            }
            bookingSeats[i] = current;
        }
        if(!screen.isAvailable(bookingSeats)){
            System.out.println("You entered a booked seat .. Try again");
            return;
        }
        bookTicket(theater, cinema, screen, noOfTickets, bookingSeats);
    }

    private Theater getTheaterIDCheck(){
        System.out.println("Enter theater id : ");
        int theaterId;
        Theater theater;
        while (true){
            theaterId = scanner.nextInt();
            theater = theaterHashMap.get(theaterId);
            if(theater != null){
                break;
            }
            System.out.println("Enter a valid theater id : \n" +
                    "If you don't know enter 1 or press any : ");
            if(scanner.nextInt() == 1){
                listAllTheatersAndMovie(false);
            }
        }
        return theater;
    }

    private Cinema getCinemaIdCheck(){
        System.out.println("Enter movie id : ");
        int cinemaId;
        Cinema cinema;
        while (true){
            cinemaId = scanner.nextInt();
            cinema = cinemaHashMap.get(cinemaId);
            if(cinema != null){
                break;
            }
            System.out.println("Enter a valid cinema id : \n" +
                    "If you don't know enter 1 or press any : ");
            if(scanner.nextInt() == 1){
                listAllTheatersAndMovie(false);
            }
        }
        return cinema;
    }
    private Screen getScreenId(Theater theater, Cinema cinema){
        int [] screens = theater.getScreens();
        for (int j : screens) {
            Screen screen = screenHashMap.get(j);
            if (screen.getCinemaID() == cinema.getCinemaID()) {
                return screen;
            }
        }
        return null;
    }

    private void bookTicket(Theater theater, Cinema cinema, Screen screen, int ticketCount, int [] bookedTickets){
        String movieName = cinema.getCinemaName();
        String theaterName = theater.getTheaterName();
        int screenNo = 0;
        int [] screens = theater.getScreens();
        for (int i = 0; i < screens.length; i++) {
            if(screen.getScreenID() == screens[i]){
                screenNo = i+1;
                break;
            }
        }
        int oneTicketPrice = calculateTicketPrice(cinema.getAmount() , screen.getScreenAmount());
        int ticketPrice = oneTicketPrice * ticketCount;
        screen.addAmountEarned(ticketPrice);
        Ticket ticket = new Ticket(cinema, theater, ticketCount, screenNo, bookedTickets, ticketPrice);
        ticketHashMap.put(ticket.getTicketNo(), ticket);
        System.out.println("Your ticket has booked in "+ theaterName +" at screen no: "+ screenNo +
                " for movie "+ movieName + " and Ticket price is : "+ ticketPrice);
        System.out.println("Please note your ticket id for entry proof and cancellation if need " +
                "\n Ticket no : " + ticket.getTicketNo());
    }
    private void cancelTicket(){

        Ticket ticket = getTicketObject();
        if(ticket == null){
            System.out.println("You cancelled a cancellation ");
            return;
        }
        removeFromDB(ticket);
        System.out.println("Your tickets has cancelled");
    }
    private void removeFromDB(Ticket ticket){
        int screenNo = ticket.getScreenNo();
        int [] screens = ticket.getTheater().getScreens();
        Screen screen = screenHashMap.get(screens[screenNo-1]);
        int [] seats = ticket.getSeats();
        screen.reverseBooked(seats, seats.length);
        refund(ticket.getAmount(), screen);
        ticketHashMap.remove(ticket.getTicketNo());
    }
    private int calculateTicketPrice(int cinemaPrice, int screenPrice){
        screenPrice = (screenPrice * cinemaPrice / 100) ;
        return cinemaPrice + screenPrice;
    }
    private void refund(int ticketPrice, Screen screen){
        float redundantAmount = (float) ticketPrice / 10;
        float refundAmount = ticketPrice - redundantAmount;
        System.out.println("You will be refund 90 % of amount : " + refundAmount +
                "\n10 % is a cancellation fee which is : " + redundantAmount);
        screen.cancellationRedundant(refundAmount);
    }
    private void showTicket(){
        if(ticketHashMap.isEmpty()){
            System.out.println("There is no ticket take place in DB currently ");
            return;
        }
        Ticket ticket = getTicketObject();
        if(ticket != null){
            System.out.println("Ticket Details : " +
                    "\n Ticket no : " + ticket.getTicketNo()+
                    "\n Theater name : " + ticket.getTheater().getTheaterName() +
                    "\n Screen no : " + ticket.getScreenNo() +
                    "\n Movie name : " + ticket.getCinema().getCinemaName() +
                    "\n Ticket price : " + ticket.getAmount() +
                    "\n No of Seats : " + ticket.getNoOfTickets());
            int [] seats = ticket.getSeats();
            System.out.println("Your seats : ");
            for (int seat : seats) {
                System.out.print(seat + " ");
            }

        }

    }
    private Ticket getTicketObject(){
        int ticketID;
        Ticket ticket;
        while (true){
            System.out.println("Enter your ticket ID : ");
            ticketID = scanner.nextInt();
            if(ticketID < 0){
                continue;
            }
            ticket = ticketHashMap.get(ticketID);
            if(ticket != null) break;
            System.out.println("Your ticket number is not exist in the Database : ");
            System.out.println("Enter 'y' if you don't want to continue or press any character : ");
            char y = scanner.next().charAt(0);
            if(y == 'y'){
                return null;
            }
        }
        return ticket;
    }
}
