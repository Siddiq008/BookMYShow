package BookMyShow;

public class Theater {
    private static int iterator = 1;
    private final int theaterID;
    private final String theaterName;
    private final int [] screens;
    private int screenCount;

    private Theater(String theaterName, int SCREENS) {
        this.theaterID = iterator++;
        this.theaterName = theaterName;
        this.screenCount = 0;
        this.screens = new int[SCREENS];
    }

    public static Theater getInstance(String theaterName, int SCREENS) {
        return new Theater(theaterName, SCREENS);
    }
    public int getTheaterID() {
        return theaterID;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setScreens(int screenID){
        screens[screenCount++] = screenID;
    }

    public int[] getScreens() {
        return screens;
    }

}
