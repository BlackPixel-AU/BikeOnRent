import java.io.Serializable;

public class Bike implements Serializable{

    protected int bikeNo;
    protected String type;
    protected boolean available = true;
    protected double pricePerDay;
    static int nextId = 1;

    public Bike() {}

    public Bike (String type, double pricePerDay) {
        this.type = type;
        this.pricePerDay = pricePerDay;
        bikeNo = nextId++;
    }

    //setters and getters

    public int getBikeNo() {
        return bikeNo;
    }

    public String getType() {
        return type;
    }

    public boolean getAvalible() {
        return available;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }


    public void setType (String type) {
        this.type = type;
    }

    public void setAvalible (boolean avalible) {
        this.available = avalible;
    }

    public void setPricePerDay (double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    //to string

    public String toString () {
        return "Bike No: " + bikeNo + ", Type: " + type + ", Price per day: " + pricePerDay + ", Available?  " + available;
    }

}//end
