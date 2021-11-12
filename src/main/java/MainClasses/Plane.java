package MainClasses;

public class Plane {

    private String planeName;
    private int averageSpeed;
    private int numebrOfSeats;

    public Plane(String planeName, int averageSpeed, int numebrOfSeats) {
        this.planeName = planeName;
        this.averageSpeed = averageSpeed;
        this.numebrOfSeats = numebrOfSeats;
    }

    public Plane() {
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public int getNumebrOfSeats() {
        return numebrOfSeats;
    }

    public void setNumebrOfSeats(int numebrOfSeats) {
        this.numebrOfSeats = numebrOfSeats;
    }

    @Override
    public String toString() {
        return "Plane : " +
                "planeName='" + planeName + '\'' +
                ", averageSpeed=" + averageSpeed +
                ", numebrOfSeats=" + numebrOfSeats;
    }
}
