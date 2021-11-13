package Lists;

import Exceptions.WrongIndexException;
import MainClasses.Plane;
import MainClasses.ReadFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ListOfPlanes {
    private Map<String, Plane> planes;
    private final AtomicInteger index = new AtomicInteger();
    private Scanner scannerText = new Scanner(System.in);
    private Scanner scannerNumber = new Scanner(System.in);
    private List<String> listPlanes = new ArrayList<>();

    public ListOfPlanes() {
        Runnable readPlanes = () -> planes = ReadFiles.readPlanes();
        Thread readFilePlanes = new Thread(readPlanes);
        readFilePlanes.start();
        try {
            readFilePlanes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showPlanes() {
        listPlanes = new ArrayList<>();
        index.set(1);
        planes.forEach((key, plane) -> {
            System.out.println(index.getAndIncrement() + "." + plane);
            listPlanes.add(key);
        });
    }

    public void addPlane() {
        Plane plane = new Plane();
        enterPlaneDetails(plane);
        planes.put(plane.getPlaneName(), plane);
        ReadFiles.updatePlanes(planes);
    }

    private void enterPlaneDetails(Plane plane) {
        System.out.println("Enter plane name : ");
        plane.setPlaneName(scannerText.nextLine());
        System.out.println("Enter plane average speed : ");
        plane.setAverageSpeed(scannerNumber.nextInt());
        System.out.println("Enter number of seats : ");
        plane.setNumebrOfSeats(scannerNumber.nextInt());
    }

    public void updatePlane() {
        showPlanes();
        System.out.println("Choose plane to update : ");
        int option = scannerNumber.nextInt();
        try {
            checkPlaneIndex(option);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        String planeKey = listPlanes.get(option - 1);
        Plane plane = new Plane();
        enterPlaneDetails(plane);
        planes.replace(planeKey, plane);
        ReadFiles.updatePlanes(planes);
    }

    public void deletePlane() {
        showPlanes();
        System.out.println("Choose plane to delete : ");
        int option = scannerNumber.nextInt();
        try {
            checkPlaneIndex(option);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        String planeKey = listPlanes.get(option - 1);
        planes.remove(planeKey);
        ReadFiles.updatePlanes(planes);
    }

    public void searchByPlaneName() {
        System.out.println("Enter plane name ");
        String planeName = scannerText.nextLine();
        Plane plane = planes.get(planeName);
        if (plane == null) {
            System.out.println("The plane was not found");
        } else {
            System.out.println(plane);
        }
    }

    private void checkPlaneIndex(int option) throws WrongIndexException {
        if (option > planes.size() || option < 1) {
            System.out.println("Invalid plane selected");
            throw new WrongIndexException();
        }
    }

}
