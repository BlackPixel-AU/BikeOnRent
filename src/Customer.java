import java.io.Serializable;

public class Customer implements Serializable{

    private int custId;
    private String name;
    private String phoneNo;
    static int nextID = 100;

    public Customer () {}

    public Customer (String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
        custId = nextID++;
    }

    //setters and getters

    public int getCustId () {
        return custId;
    }
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    //to string

    public String toString () {
        return "Customer ID: " + custId + ", Name: " + name + ", Phone: " + phoneNo;
    }

}//end