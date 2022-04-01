package ua.lpnu.students.labs.laba2.ChristmassDecoration.tests;


import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.Manager;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

class Main {
    public static void main(String ...args){
        Manager manager = new Manager();
        TextMenu menu = new TextMenu(manager);
        menu.mainMenu();
    }
    
}
