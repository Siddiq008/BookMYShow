package BookMyShow;

public class Cinema {
    static int iterator = 1;
    private final int cinemaID;
    private final String cinemaName;
    private final int Amount;

    private Cinema(String cinemaName, int Amount){
        this.cinemaID = iterator++;
        this.cinemaName = cinemaName;
        this.Amount = Amount;
    }

    public static Cinema getInstance(String cinemaName, int Amount) {
        return new Cinema(cinemaName, Amount);
    }
    public int getCinemaID() {
        return cinemaID;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public int getAmount() {
        return Amount;
    }
}
