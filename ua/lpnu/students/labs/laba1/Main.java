package ua.lpnu.students.labs.laba1;

import ua.lpnu.students.labs.laba1.hotel.*;

public class Main {
    public static void main(String[] args){
        Hotel[] hotelsInLviv = {
            new Hotel(),
            new Hotel("Superiority", 123, "vul. Stepana Bandery, 23"),
            new Hotel("Average choice", 43000, 329, HotelStatus.CHEAP, true, "vul. Stryjska, 234")
        };
        System.out.println(String.format("There are %d hotels in Lviv", Hotel.getNumOfHotels()));
        for (Hotel hotel : hotelsInLviv) {
            System.out.println(hotel);
        }
    }
}
