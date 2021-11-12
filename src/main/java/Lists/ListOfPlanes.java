package Lists;

import MainClasses.Plane;
import MainClasses.ReadFiles;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ListOfPlanes {
    private Map<String, Plane> planes;
    private final AtomicInteger index = new AtomicInteger();
    Scanner scannerText = new Scanner(System.in);
    Scanner scannerNumber = new Scanner(System.in);

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

        index.set(1);
        planes.forEach((key, plane) -> System.out.println(index.getAndIncrement() + "." + plane ));

    }
    public void addPlane(){
        Plane plane = new Plane();
        System.out.println("Enter plane name : ");
        plane.setPlaneName(scannerText.nextLine());
        System.out.println("Enter plane average speed : ");
        plane.setAverageSpeed(scannerNumber.nextInt());
        System.out.println("Enter number of seats : ");
        plane.setNumebrOfSeats(scannerNumber.nextInt());
        planes.put(plane.getPlaneName(),plane);
        ReadFiles.updatePlanes(planes);
    }
}
