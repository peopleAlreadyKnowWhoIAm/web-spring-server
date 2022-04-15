package ua.lpnu.students.labs.laba1.hotel;

public record Hotel(
    String name,
    long numOfVisitorsPerYear,
    int numOfRooms,
    HotelStatus status,
    boolean hasDinner,
    String adress
){
    private static final String[] dinnerMessages = {
        "There is dinner in the hotel",           //if there is dinner
        "There is not dinner in the hotel"        //if there is not dinner
    };      
    private static final String noneInfMessage = "none information";

    private static final String MESSAGE = 
"""
%s 
    Visitors per year: %s
    There are that number of rooms: %s
    %s
    The status of the hotel: %s
    Address of the hotel: %s
""";
/*
    1 %s -- name
    2 %s -- number of visitors (converted to string)
    3 %s -- number of rooms (converted to string)
    4 %s -- whether has the hotel dinner (string message)
    5 %s -- hotel status(string)
    6 %s -- adress (string)
 */
    


    public Hotel{
        Hotel.numOfHotels++;
    }

    public Hotel(final String name, final int numOfRooms, final String adress){
        this(name, -1, numOfRooms, HotelStatus.MIDDLE_PRICE, false, adress);
    }

    public Hotel(){
        this(noneInfMessage, -1, noneInfMessage);
    }

    @Override
    public String toString(){
        return String.format(MESSAGE, 
            name,
            ((numOfVisitorsPerYear == -1)? noneInfMessage: numOfVisitorsPerYear),
            ((numOfRooms == -1)? noneInfMessage: numOfRooms),
            dinnerMessages[((hasDinner == true)?0:1)],
            status.toString(),
            adress 
        );
    }

    protected void finalize(){
        Hotel.numOfHotels--;
    }

    private static int numOfHotels = 0;
    public static int getNumOfHotels() {
        return numOfHotels;
    }

    public int numOfRoomsMultipl(){
        return numOfRooms*3;
    }
}
