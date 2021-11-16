package Menu;

import Lists.ListOfPlanes;

import java.util.Scanner;

public class PlaneMenu {
    ListOfPlanes planes = new ListOfPlanes();
    Scanner scannerNumber = new Scanner(System.in);

    private void options(){
        System.out.println("1.Show Planes");
        System.out.println("2.Add Plane");
        System.out.println("3.Update Plane");
        System.out.println("4.Delete Plane");
        System.out.println("5.Search by plane name");
        System.out.println("0.Exit");
        System.out.print("Choose option :");
    }

    public void menu(){
        byte menuOption;
        do {
            options();
            menuOption = scannerNumber.nextByte();
            Action(menuOption);
        }while (menuOption != 0);
    }
    private void Action(byte menuOption) {
        switch (menuOption){
            case 1 -> planes.showPlanes();
            case 2 -> planes.addPlane();
            case 3 -> planes.updatePlane();
            case 4 -> planes.deletePlane();
            case 5 -> planes.searchByPlaneName();
            case 0 -> System.out.println();
            default -> System.out.println("Invalid option");
        }
    }
}
