//import java.io.*;
import java.io.Serializable;
import java.time.LocalDate;

public class Rent implements Serializable {

    private int rentId;
    private int bikeNo;
    private LocalDate rentStart;
    private int duration;
    private int custId;
    private boolean isPaid;
    static int nextID = 100;

    public Rent () {
        rentId = nextID++;
    }

    public Rent (int custId, int bikeNo, LocalDate rentStart, int duration) {
        this.custId = custId;
        this.bikeNo = bikeNo;
        this.rentStart = rentStart;
        this.duration = duration;
        rentId = nextID++;
    }

    //setters and getters

    public int getRentId () {
        return rentId;
    }

    public int getBikeNo () {
        return bikeNo;
    }

    public LocalDate getRentStart () {
        return rentStart;
    }

    public int getDuration () {
        return duration;
    }

    public int getCustId () {
        return custId;
    }

    public boolean getIsPaid () {
        return isPaid;
    }

    public void setBikeNo (int bikeNo) {
        this.bikeNo = bikeNo;
    }

    public void setRentStart (LocalDate rentStart) {
        this.rentStart = rentStart;
    }

    public void setDate (String dateStr) {
        rentStart = LocalDate.parse(dateStr);
    }

    public void setDuration (int duration) {
        this.duration = duration;
    }


    public void setIsPaid(boolean isPaid){
        this.isPaid = isPaid;
    }

    //to string

    public String toString () {
        return "Rent ID: " + rentId+ " Customer ID: " + custId + " Bike No: " + bikeNo + " Start date: " + rentStart + " Duration: " + duration;
    }


}//end
