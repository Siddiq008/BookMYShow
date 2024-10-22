package BookMyShow;

import java.util.HashMap;


class CreateData {
    private static CreateData createData;
    private static final HashMap<Integer, Theater> theaterHashMap = new HashMap<>();
    private static final HashMap<Integer, Cinema> cinemaHashMap = new HashMap<>();
    private static final HashMap<Integer, Screen> screenHashMap = new HashMap<>();

    public static CreateData getInstance(){
        if(createData == null){
            createData = new CreateData();
        }
        return createData;
    }
    public void createTheaters(){
        Theater theater1 = Theater.getInstance("LA Cinema", 3);
        theaterHashMap.put(theater1.getTheaterID(), theater1);
        createScreens(theater1.getTheaterID(), 3);
        Theater theater2 = Theater.getInstance("Sona mina", 3);
        theaterHashMap.put(theater2.getTheaterID(), theater2);
        createScreens(theater2.getTheaterID(), 3);
        Theater theater3 = Theater.getInstance("INOX", 3);
        theaterHashMap.put(theater3.getTheaterID(), theater3);
        createScreens(theater3.getTheaterID(), 3);
        Theater theater4 = Theater.getInstance("IMAX", 2);
        theaterHashMap.put(theater4.getTheaterID(), theater4);
        createScreens(theater4.getTheaterID(), 2);
    }
    private void createScreens(int theaterID, int screenCount){
        Theater theater = theaterHashMap.get(theaterID);
        int seatsPerScreen = 10;
        int screenAmount = 5;
        for (int i = 0; i < screenCount; i++) {
            Screen screen = Screen.getInstance(seatsPerScreen, screenAmount);
            theater.setScreens(screen.getScreenID());
            screenHashMap.put(screen.getScreenID(), screen);
            seatsPerScreen = 15;
            screenAmount += 5;
            if(screenAmount > 25) screenAmount = 5;
        }

    }

    public void createMovies(){
        Cinema cinema;
        Theater theater;
        int [] screens;
        cinema = Cinema.getInstance("Ironman", 200);
        cinemaHashMap.put(cinema.getCinemaID(), cinema);
        cinema = Cinema.getInstance("Captain", 150);
        cinemaHashMap.put(cinema.getCinemaID(), cinema);
        cinema = Cinema.getInstance("Thor", 100);
        cinemaHashMap.put(cinema.getCinemaID(), cinema);
        cinema = Cinema.getInstance("Strange", 180);
        cinemaHashMap.put(cinema.getCinemaID(), cinema);
        cinema = Cinema.getInstance("Spider", 160);
        cinemaHashMap.put(cinema.getCinemaID(), cinema);
        cinema = Cinema.getInstance("Panther", 150);
        cinemaHashMap.put(cinema.getCinemaID(), cinema);

        theater = theaterHashMap.get(1);
        screens = theater.getScreens();
        screenHashMap.get(screens[0]).setCinemaID(1);
        theater = theaterHashMap.get(2);
        screens = theater.getScreens();
        screenHashMap.get(screens[1]).setCinemaID(1);
        theater = theaterHashMap.get(3);
        screens = theater.getScreens();
        screenHashMap.get(screens[2]).setCinemaID(1);
        theater = theaterHashMap.get(4);
        screens = theater.getScreens();
        screenHashMap.get(screens[0]).setCinemaID(1);

        theater = theaterHashMap.get(1);
        screens = theater.getScreens();
        screenHashMap.get(screens[1]).setCinemaID(2);
        theater = theaterHashMap.get(2);
        screens = theater.getScreens();
        screenHashMap.get(screens[0]).setCinemaID(2);
        theater = theaterHashMap.get(3);
        screens = theater.getScreens();
        screenHashMap.get(screens[1]).setCinemaID(2);

        theater = theaterHashMap.get(1);
        screens = theater.getScreens();
        screenHashMap.get(screens[2]).setCinemaID(3);
        theater = theaterHashMap.get(3);
        screens = theater.getScreens();
        screenHashMap.get(screens[1]).setCinemaID(3);

        theater = theaterHashMap.get(2);
        screens = theater.getScreens();
        screenHashMap.get(screens[2]).setCinemaID(4);
        theater = theaterHashMap.get(4);
        screens = theater.getScreens();
        screenHashMap.get(screens[1]).setCinemaID(4);


    }

    public HashMap<Integer, Theater> getTheaterHashMap() {
        return theaterHashMap;
    }

    public HashMap<Integer, Cinema> getCinemaHashMap() {
        return cinemaHashMap;
    }

    public HashMap<Integer, Screen> getScreenHashMap() {
        return screenHashMap;
    }
}
