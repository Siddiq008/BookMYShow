package BookMyShow;

public class Screen {
    private static int iterator = 1;
    private final int screenID;
    private int CinemaID;
    private float amountEarned;
    private final int screenAmount;
    private final boolean [] seats;

    private Screen(int totalSeats, int screenAmount) {
        this.screenID = iterator++;
        this.screenAmount = screenAmount;
        this.seats = new boolean[totalSeats];
    }

    public static Screen getInstance(int totalSeats, int screenAmount) {
        return new Screen(totalSeats, screenAmount) ;
    }

    public int getScreenID() {
        return screenID;
    }

    public int getCinemaID() {
        return CinemaID;
    }

    public void setCinemaID(int cinemaID) {
        CinemaID = cinemaID;
    }
    public void printSeats(){
        System.out.println("seat no is linear from 0 to " + getNoOfSeats());

        for (int i = 0; i < getNoOfSeats(); i++) {
            if (seats[i]) {
                System.out.print((i)+"# ");
            } else {
                System.out.print((i)+"_ ");
            }
            if((i) % 4 == 0){
                System.out.println();
            }
        }


        System.out.println("\nWhere '#' is booked and '_' is available ");
    }
    public int getNoOfSeats(){
        return seats.length;
    }
    public boolean isAvailable(int [] bookingSeats){
        for (int i = 0; i < bookingSeats.length; i++) {
            if(seats[bookingSeats[i]]){
                reverseBooked(bookingSeats, i);
                return false;
            }
            seats[bookingSeats[i]] = true;
        }
        return true;
    }

    public int getScreenAmount() {
        return this.screenAmount;
    }

    public void addAmountEarned(int ticketPrice) {
        this.amountEarned += ticketPrice;
    }

    public void reverseBooked(int [] bookingSeats, int end){
        for (int i = 0; i < end; i++) {
            seats[bookingSeats[i]] = false;
        }
    }
    public void cancellationRedundant(float refundAmount){
        this.amountEarned = this.amountEarned - refundAmount;
    }

}
