package ua.lpnu.students.labs.laba2;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

class Main {
    public static void main(String ...args){
        Manager manager = new Manager();
        TextMenu menu = new TextMenu(manager);
        menu.mainMenu();
    }
    
}
