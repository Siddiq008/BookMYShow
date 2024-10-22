package BookMyShow;

public class Ticket {
    private static int iterator = 1;
    private final int ticketNo;
    private final Cinema cinema;
    private final Theater theater;
    private final int noOfTickets;
    private final int screenNo;
    private final int [] seats;
    int amount;

    public Ticket(Cinema cinema, Theater theater, int noOfTickets, int screenNo, int[] seats, int amount) {
        this.ticketNo = iterator++;
        this.cinema = cinema;
        this.theater = theater;
        this.noOfTickets = noOfTickets;
        this.screenNo = screenNo;
        this.seats = seats;
        this.amount = amount;
    }

    public int getTicketNo() {
        return this.ticketNo;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public Theater getTheater() {
        return this.theater;
    }
    public int[] getSeats(){
        return this.seats;
    }
    public int getAmount(){
        return this.amount;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public int getScreenNo() {
        return screenNo;
    }
}
