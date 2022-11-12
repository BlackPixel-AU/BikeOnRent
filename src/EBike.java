
public class EBike extends Bike {

    private String motorStrength;
    private int maxDistance;

    public EBike (String type, double pricePerDay, String motorStrength, int maxDistance){
        super(type, pricePerDay);
        this.motorStrength = motorStrength;
        this.maxDistance = maxDistance;
    }

    //setters and getters

    public String getMotorStrength() {
        return motorStrength;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMotorStrength (String motorStrength) {
        this.motorStrength = motorStrength;
    }

    public void setMaxDistance (int maxDistance) {
        this.maxDistance = maxDistance;
    }

    //to string

    public String toString () {
        return "Bike No: " + bikeNo + ", Type: " + type + " Motor: " + motorStrength + " Distance: " + maxDistance + ", Price per day: " + pricePerDay + ", Available?  " + available;
    }


}//end
