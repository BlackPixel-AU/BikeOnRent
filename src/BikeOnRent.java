import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

//#######################################################################
//
//			  BlackPixel-AU
//			  https://github.com/BlackPixel-AU/BikeOnRent
//			  Originally created for an RMIT University project. 
//			  Bugs are free :)
//
//#######################################################################

public class BikeOnRent extends JFrame implements ActionListener {
    String version = "1.0"; //program version
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private ArrayList < Bike > bikes;
    private ArrayList < Customer > customers;
    private ArrayList < Rent > rents;

    //for bikesTab
    JButton btnRList, btnRListAv;
    JTextArea jtaR;

    //for addbikeTab
    String[] motorOptions = {"Full power", "Power assist"};
    JComboBox < String > jComboBox = new JComboBox < > (motorOptions);
    JLabel lblBType, lblBPrice, lblBTypeE, lblBPriceE, lblBMotor, lblBDist;
    JTextField txtBType, txtBPrice, txtBTypeE, txtBPriceE, txtBMotor, txtBDist;
    JButton btnBAdd, btnBAddE, btnBSave, btnBRead;
    JComboBox < String > cbbBMotor;
    JTextArea jtaB;

    // for customersTab
    JButton btnCAdd, btnCList, btnCRead, btnCWrite;
    JTextArea jtaC;
    JLabel lblName, lblCustNo;
    JTextField txtName, txtCustNo;

    // for rents tab
    JLabel lblFRentID, lblFDate;
    JTextField txtFRentID, txtFDate;
    JButton btnFList, btnFListOverDue, btnFListOverDueDay, btnFRemove;
    JTextArea jtaF;

    //for addrentTab
    JLabel lblQCustomerNo, lblQDuration, lblQDate, lblQBikeNo, lblQStartDate;
    JTextField txtQCustomerNo, txtQDuration, txtQDate, txtQBikeNo, txtQStartDate;
    JButton btnQAdd, btnQWrite, btnQRead, btnQListAv;
    JTextArea jtaQ;

    //for miscTab
    JLabel lblOTop, lblOMiddle, lblOBottom;
    JButton btnORead, btnOWrite, btnONuke;


    public BikeOnRent() {

        bikes = new ArrayList < Bike > ();
        customers = new ArrayList < Customer > ();
        rents = new ArrayList < Rent > ();

        //components for tabbed panel
        JTabbedPane tabs = new JTabbedPane();
        JPanel bikesTab = new JPanel();
        JPanel addBikesTab = new JPanel();
        JPanel customersTab = new JPanel();
        JPanel rentsTab = new JPanel();
        JPanel addRentsTab = new JPanel();
        JPanel miscTab = new JPanel();

        //components for bikesTab
        //__________________________________________
        JPanel displayRPanel = new JPanel();
        JPanel buttonRPanel = new JPanel();
        //for bikesTab display
        jtaR = new JTextArea(30, 50);
        JScrollPane scrollR = new JScrollPane(jtaR, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        btnRList = new JButton("List All Bikes");
        btnRListAv = new JButton("List Available Bikes");

        //components for bikesAdd panel
        //__________________________________________
        JPanel addBikesBPanel = new JPanel();
        JPanel inputBPanel = new JPanel();
        JPanel buttonBPanel = new JPanel();

        JPanel addEBikesBPanel = new JPanel();
        JPanel inputeBPanel = new JPanel();
        JPanel buttoneBPanel = new JPanel();

        JPanel readwriteBPanel = new JPanel();

        lblBType = new JLabel("Enter bike type");
        txtBType = new JTextField(10);
        lblBPrice = new JLabel("Enter bike price per day");
        txtBPrice = new JTextField(10);
        btnBAdd = new JButton("Add Bike");

        lblBTypeE = new JLabel("Enter bike type");
        txtBTypeE = new JTextField(10);
        lblBPriceE = new JLabel("Enter bike price per day");
        txtBPriceE = new JTextField(10);
        lblBMotor = new JLabel("Enter bike Motor");
        cbbBMotor = new JComboBox < > (motorOptions);
        //txtBMotor = new JTextField(10);
        lblBDist = new JLabel("Enter bike distance");
        txtBDist = new JTextField(10);
        btnBAddE = new JButton("Add EBike");

        btnBSave = new JButton("Save to file");
        btnBRead = new JButton("Read from file");

        //_________________________________________
        //components for customerTab
        JPanel inputCPanel = new JPanel();
        JPanel addCustCPanel = new JPanel();
        JPanel readwriteCPanel = new JPanel();
        JPanel disButtonsCPanel = new JPanel();
        JPanel disListCPanel = new JPanel();
        JPanel displayCPanel = new JPanel();
        JPanel buttonCPanel = new JPanel();

        inputCPanel.setLayout(new GridLayout(2, 1));
        inputCPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        displayCPanel.setPreferredSize(new Dimension(200, 200));
        disListCPanel.setPreferredSize(new Dimension(200, 200));

        lblName = new JLabel("Enter Customer name");
        txtName = new JTextField(10);
        lblCustNo = new JLabel("Enter Customer phone");
        txtCustNo = new JTextField(10);
        btnCAdd = new JButton("Add customer");
        btnCList = new JButton("List all customers");
        btnCWrite = new JButton("Save to file");
        btnCRead = new JButton("Read from file");
        jtaC = new JTextArea(15, 50);
        jtaC.setBorder(new TitledBorder("Customers"));

        //______________________________________________
        //components for rentsTab
        JPanel displayFPanel = new JPanel();
        JPanel buttonFPanel = new JPanel();
        JPanel rInputFPanel = new JPanel();
        JPanel rButtonFPanel = new JPanel();
        JPanel removeFPanel = new JPanel();
        JPanel buttTopFPanel = new JPanel();
        JPanel buttBotFPanel = new JPanel();

        btnFList = new JButton("List All Rents");
        btnFListOverDue = new JButton("Overdue Rents (today)");
        btnFListOverDueDay = new JButton("Overdue Rents (date)");
        btnFRemove = new JButton("Remove Rent");

        txtFRentID = new JTextField(10);
        lblFRentID = new JLabel("Enter Rent ID");

        txtFDate = new JTextField(10);
        lblFDate = new JLabel("Enter Date YYYY-MM-DD");

        jtaF = new JTextArea(30, 50);
        JScrollPane scrollF = new JScrollPane(jtaF, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //components for rentsAdd panel
        //__________________________________________
        JPanel addRentPanel = new JPanel();
        JPanel inputQPanel = new JPanel();
        JPanel buttonQPanel = new JPanel();
        JPanel readwriteQPanel = new JPanel();
        JPanel bikesQPanel = new JPanel();
        JPanel buttonBikesQPanel = new JPanel();
        JPanel displayQPanel = new JPanel();
        jtaQ = new JTextArea(6, 50);
        JScrollPane scrollQ = new JScrollPane(jtaQ, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        lblQCustomerNo = new JLabel("Enter Customer No");
        txtQCustomerNo = new JTextField(10);
        lblQStartDate = new JLabel("Enter Start Date YYYY-MM-DD");
        txtQStartDate = new JTextField(10);
        lblQDuration = new JLabel("Enter Duration");
        txtQDuration = new JTextField(10);
        lblQBikeNo = new JLabel("Enter Bike No");
        txtQBikeNo = new JTextField(10);
        btnQAdd = new JButton("Add Rent");
        btnQListAv = new JButton("List Available Bikes");

        btnQWrite = new JButton("Save to file");
        btnQRead = new JButton("Read from file");

        jtaB = new JTextArea(10, 50);

        //components for misc panel
        //__________________________________________
        JPanel aboutPanel = new JPanel();
        JPanel dataPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel picturePanel = new JPanel();
        BufferedImage picture = null;
        try {
            picture = ImageIO.read(new File("images/bike.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel logo = new JLabel(new ImageIcon(picture));

        lblOTop = new JLabel("BikeOnRent");
        lblOMiddle = new JLabel("Version "+version);
        lblOBottom = new JLabel("Taner Kurtulus - s3822299");

        btnORead = new JButton("Read from all files");
        btnOWrite = new JButton("Save to all files");
        btnONuke = new JButton("Erase all data");


        //_______________________________________
        //build tabs

        add(tabs, BorderLayout.CENTER);
        tabs.addTab("Bikes", bikesTab);
        tabs.addTab("Modify Bikes", addBikesTab);
        tabs.addTab("Customers", customersTab);
        tabs.addTab("Rents", rentsTab);
        tabs.addTab("Add Rent", addRentsTab);
        tabs.addTab("Misc", miscTab);

        //_______________________________________
        //build bikesTab
        bikesTab.setLayout(new BorderLayout());
        bikesTab.add(displayRPanel, BorderLayout.CENTER);
        bikesTab.add(buttonRPanel, BorderLayout.SOUTH);
        jtaR.setBorder(new TitledBorder("Bikes"));
        btnRList.addActionListener(this);
        btnRListAv.addActionListener(this);
        buttonRPanel.add(btnRList);
        buttonRPanel.add(btnRListAv);
        displayRPanel.add(scrollR);

        //______________________________________________
        //build bikeAddTab
        //JPanel displayBPanel = new JPanel();

        addBikesTab.setLayout(new BorderLayout());
        addBikesTab.add(addBikesBPanel, BorderLayout.NORTH);
        addBikesTab.add(addEBikesBPanel, BorderLayout.CENTER);
        addBikesTab.add(readwriteBPanel, BorderLayout.SOUTH);

        addBikesBPanel.add(inputBPanel, BorderLayout.NORTH);
        addBikesBPanel.add(buttonBPanel, BorderLayout.CENTER);

        addEBikesBPanel.add(inputeBPanel, BorderLayout.NORTH);
        addEBikesBPanel.add(buttoneBPanel, BorderLayout.CENTER);

        addBikesBPanel.setBorder(new TitledBorder("Add Bike"));
        addEBikesBPanel.setBorder(new TitledBorder("Add EBike"));
        readwriteBPanel.setBorder(new TitledBorder("Read/Write Bikes to File"));

        inputBPanel.setBorder(new EmptyBorder(30, 50, 50, 30));
        inputBPanel.setLayout(new GridLayout(2, 2, 20, 10));

        inputeBPanel.setBorder(new EmptyBorder(30, 50, 50, 30));
        inputeBPanel.setLayout(new GridLayout(5, 5, 20, 10));

        inputBPanel.add(lblBType);
        inputBPanel.add(txtBType);
        inputBPanel.add(lblBPrice);
        inputBPanel.add(txtBPrice);
        buttonBPanel.add(btnBAdd);

        inputeBPanel.add(lblBTypeE);
        inputeBPanel.add(txtBTypeE);
        inputeBPanel.add(lblBPriceE);
        inputeBPanel.add(txtBPriceE);
        inputeBPanel.add(lblBMotor);
        inputeBPanel.add(cbbBMotor);
        inputeBPanel.add(lblBDist);
        inputeBPanel.add(txtBDist);
        buttoneBPanel.add(btnBAddE);

        readwriteBPanel.add(btnBSave);
        readwriteBPanel.add(btnBRead);

        btnBAdd.addActionListener(this);
        btnBAddE.addActionListener(this);
        btnBSave.addActionListener(this);
        btnBRead.addActionListener(this);
        cbbBMotor.addActionListener(this);

        //_________________________________________________
        //build customersTab
        customersTab.setLayout(new BorderLayout());
        customersTab.add(addCustCPanel, BorderLayout.NORTH);
        customersTab.add(displayCPanel, BorderLayout.CENTER);
        customersTab.add(readwriteCPanel, BorderLayout.SOUTH);

        displayCPanel.add(disListCPanel, BorderLayout.NORTH);

        displayCPanel.setLayout(new GridLayout(1, 1));
        displayCPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        addCustCPanel.add(inputCPanel, BorderLayout.NORTH);
        addCustCPanel.add(buttonCPanel, BorderLayout.SOUTH);

        inputCPanel.setLayout(new GridLayout(2, 2, 20, 10));
        inputCPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputCPanel.add(lblName);
        inputCPanel.add(txtName);
        inputCPanel.add(lblCustNo);
        inputCPanel.add(txtCustNo);

        readwriteCPanel.add(btnCWrite);
        readwriteCPanel.add(btnCRead);

        displayCPanel.setBorder(new TitledBorder("Customers"));
        addCustCPanel.setBorder(new TitledBorder("Add Customer"));
        readwriteCPanel.setBorder(new TitledBorder("Read/Write Customers to File"));

        buttonCPanel.add(btnCAdd);
        disListCPanel.add(jtaC);
        disListCPanel.add(btnCList);


        //build button panel
        btnCAdd.addActionListener(this);
        btnCList.addActionListener(this);
        btnCRead.addActionListener(this);
        btnCWrite.addActionListener(this);


        //_______________________________________
        //build rentsTab
        rentsTab.setLayout(new BorderLayout());
        rentsTab.add(removeFPanel, BorderLayout.NORTH);
        rentsTab.add(displayFPanel, BorderLayout.CENTER);
        rentsTab.add(buttonFPanel, BorderLayout.SOUTH);

        removeFPanel.add(rInputFPanel, BorderLayout.WEST);
        removeFPanel.add(rButtonFPanel, BorderLayout.EAST);

        buttonFPanel.add(buttTopFPanel, BorderLayout.NORTH);
        buttonFPanel.add(buttBotFPanel, BorderLayout.SOUTH);

        jtaF.setBorder(new TitledBorder("Rents"));

        buttonFPanel.setLayout(new GridLayout(2, 2, 20, 10));
        rInputFPanel.add(lblFRentID);
        rInputFPanel.add(txtFRentID);

        rButtonFPanel.add(btnFRemove);

        removeFPanel.setBorder(new TitledBorder("Remove a rent"));

        buttTopFPanel.add(btnFList);
        buttTopFPanel.add(btnFListOverDue);

        buttBotFPanel.add(lblFDate);
        buttBotFPanel.add(txtFDate);
        buttBotFPanel.add(btnFListOverDueDay);

        displayFPanel.add(scrollF);

        btnFRemove.addActionListener(this);
        btnFList.addActionListener(this);
        btnFListOverDue.addActionListener(this);
        btnFListOverDueDay.addActionListener(this);


        //______________________________________________
        //build rentsAddTab
        //JPanel displayBPanel = new JPanel();

        addRentsTab.setLayout(new BorderLayout());
        addRentsTab.add(addRentPanel, BorderLayout.NORTH);
        addRentsTab.add(bikesQPanel, BorderLayout.CENTER);
        addRentsTab.add(readwriteQPanel, BorderLayout.SOUTH);
        displayQPanel.add(scrollQ);

        addRentPanel.add(inputQPanel, BorderLayout.NORTH);
        addRentPanel.add(buttonQPanel, BorderLayout.SOUTH);

        bikesQPanel.add(displayQPanel, BorderLayout.NORTH);
        displayQPanel.add(buttonBikesQPanel, BorderLayout.SOUTH);
        displayQPanel.add(btnQListAv);

        bikesQPanel.setLayout(new GridLayout(1, 1));
        bikesQPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        addRentPanel.setBorder(new TitledBorder("Add Rent"));
        readwriteQPanel.setBorder(new TitledBorder("Read/Write Rents to File"));
        bikesQPanel.setBorder(new TitledBorder("Available bikes"));

        inputQPanel.setBorder(new EmptyBorder(30, 50, 50, 30));
        inputQPanel.setLayout(new GridLayout(4, 2, 20, 10));

        inputQPanel.add(lblQCustomerNo);
        inputQPanel.add(txtQCustomerNo);
        inputQPanel.add(lblQStartDate);
        inputQPanel.add(txtQStartDate);
        inputQPanel.add(lblQDuration);
        inputQPanel.add(txtQDuration);
        inputQPanel.add(lblQBikeNo);
        inputQPanel.add(txtQBikeNo);
        buttonQPanel.add(btnQAdd);

        readwriteQPanel.add(btnQWrite);
        readwriteQPanel.add(btnQRead);

        btnQAdd.addActionListener(this);
        btnQWrite.addActionListener(this);
        btnQRead.addActionListener(this);

        jtaQ.setBorder(new TitledBorder("Available Bikes"));
        btnQListAv.addActionListener(this);


        //build miscTab
        miscTab.setLayout(new BorderLayout());
        miscTab.add(aboutPanel, BorderLayout.NORTH);
        miscTab.add(dataPanel, BorderLayout.SOUTH);

        aboutPanel.add(textPanel, BorderLayout.EAST);
        aboutPanel.add(picturePanel, BorderLayout.WEST);

        aboutPanel.setBorder(new TitledBorder("About"));
        dataPanel.setBorder(new TitledBorder("Manage Program Data"));

        aboutPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        picturePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        textPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        aboutPanel.setLayout(new GridLayout(1, 1, 20, 10));
        textPanel.setLayout(new GridLayout(10, 1, 20, 10));
        picturePanel.add(logo);
        textPanel.add(lblOTop);
        textPanel.add(lblOMiddle);
        textPanel.add(lblOBottom);

        dataPanel.add(btnORead);
        dataPanel.add(btnOWrite);
        dataPanel.add(btnONuke);

        btnORead.addActionListener(this);
        btnOWrite.addActionListener(this);
        btnONuke.addActionListener(this);
    }


    public void populateLists() {
        Bike[] arrOfBikes = {

                //Bikes
                new Bike("Road", 100),
                new Bike("Mountain", 80),
                new Bike("BMX", 120),
                new Bike("City", 260),
                new Bike("BMX", 120),
                new Bike("City", 260),
                new Bike("City", 260),

                //EBikes
                new EBike("Road", 230, "Full power", 100),
                new EBike("Mountain", 210, "Full power", 100),
                new EBike("BMX", 190, "Power assist", 90),
                new EBike("Road", 200, "Power assist", 100),
                new EBike("City", 240, "Full power", 110)
        };


        Customer[] arrOfCustomers = {
                new Customer("Taner", "04176298463"),
                new Customer("Jim", "0417284622"),
                new Customer("Jill", "0481372753")
        };
        Rent[] arrOfRents = {
                new Rent(100, 1, LocalDate.of(2022, 10, 15), 1),
                new Rent(101, 2, LocalDate.of(2023, 2, 27), 1)
        };

        for (int i = 0; i < arrOfBikes.length; i++)
            bikes.add(arrOfBikes[i]);
        for (int i = 0; i < arrOfCustomers.length; i++)
            customers.add(arrOfCustomers[i]);
        for (int i = 0; i < arrOfRents.length; i++)
            rents.add(arrOfRents[i]);

    }

    public static void main(String[] args) {
        BikeOnRent br = new BikeOnRent();
        br.populateLists();
        br.setSize(650, 600); //default 600, 550
        br.setLocationRelativeTo(null);
        br.setVisible(true);

    }

    public void checkAvailable() {
        for (Bike b: bikes) {
            for (Rent r: rents) {
                if (r.getBikeNo() == b.getBikeNo())
                    b.setAvalible(false);
            }
        }
    }

    public void addCustomer() {

        System.out.print("\n * Add Customer Starting");
        String name = txtName.getText();
        String phoneNo = txtCustNo.getText();
        Customer c = new Customer(name, phoneNo);
        customers.add(c);
        System.out.print("\nCustomer " + name + " added!");
        String titleBar = "Customer Added ✅";
        JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void addBike() {
        System.out.print("\n * Add Bike Starting");
        String type = txtBType.getText();
        String sprice = txtBPrice.getText();
        double price = Double.parseDouble(sprice);
        Bike b = new Bike(type, price);
        bikes.add(b);
        System.out.print("\nBike " + type + " added!");
        String titleBar = "Bike Added ✅";
        JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void addEBike() {
        System.out.print("\n * Add EBike Starting");
        String type = txtBTypeE.getText();
        String sprice = txtBPriceE.getText();
        double price = Double.parseDouble(sprice);
        String motor = (String) cbbBMotor.getSelectedItem();
        String sdist = txtBDist.getText();
        int dist = Integer.parseInt(sdist);
        Bike b = new EBike(type, price, motor, dist);
        bikes.add(b);
        System.out.print("\nEBike " + type + " added!");
        String titleBar = "EBike Added ✅";
        JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void addRent() {

        System.out.print("\n * Add Rent Starting");
        String custIdS = txtQCustomerNo.getText();
        int custId = Integer.parseInt(custIdS);
        int custRenting = 0; // for reasons unknown to science, if this is a bool instead of an int it stops working 
        for (Rent b: rents) {
            if (b.getCustId() == custId) {
                System.out.print("\nCust ID Fail");
                custRenting = 1;
                String titleBar = "Customer " + custId + " already renting! ⛔";
                JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (custRenting == 0) {
            System.out.print("\nCust ID Pass");
            addRentDurCheck();
        }
    }


    public void addRentDurCheck() {

        System.out.print("\n * Add Rent Dur Check Starting");
        String durS = txtQDuration.getText();
        int dur = Integer.parseInt(durS);
        if (dur > 30) {
            String titleBar = "Duration must be 30 days or less! ⛔";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
            System.out.print("\nDuration Fail");
        } else {
            System.out.print("\nDuration Pass");
            addRentFinal();
        }

    }

    public void addRentFinal() {

        System.out.print("\n * Add Rent Final Starting");
        String custIdS = txtQCustomerNo.getText();
        int custId = Integer.parseInt(custIdS);

        String dateStr = txtQStartDate.getText();
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateStr);
        } catch (Exception e) {}

        String bikeNoS = txtQBikeNo.getText();
        int bikeNo = Integer.parseInt(bikeNoS);
        for (Bike r: bikes) {
            r.setAvalible(false);
        }
        String durS = txtQDuration.getText();
        int dur = Integer.parseInt(durS);
        Rent rent = new Rent(custId, bikeNo, date, dur);
        rents.add(rent);
        System.out.print("\nRent for " + custId + " added!");
        String titleBar = "Rent Added ✅";
        JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);

    }

    public void removeRentById() {
        System.out.print("\n * Remove Rent Starting");
        String idS = txtFRentID.getText();
        int id = Integer.parseInt(idS);
        for (int i = 0; i < rents.size(); i++) {
            Rent b = rents.get(i);
            int idFound = 0;
            if (b.getRentId() == id) {
                idFound = 1;
                rents.remove(b);
                Bike r = bikes.get(i);
                System.out.print("\nRent " + id + " removed!\n");
                String titleBar = "Rent Removed ✅";
                JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    public Bike searchBikesByBikeNo(int bikeNo) {
        System.out.print("\n * Search Bike ID Starting");
        for (Bike r: bikes) {
            if (r.getBikeNo() == bikeNo) {
                System.out.println(r);
            }

        }
        return null;
    }


    public void saveBikes() {
        System.out.print("\n * Save Bikes Starting");
        try {
            fos = new FileOutputStream("bike.dat");
            oos = new ObjectOutputStream(fos);
            for (Bike b: bikes) {
                oos.writeObject(b);
            }
            fos.close();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nBikes saved to file!");
    }

    public void saveCustomers() {
        System.out.print("\n * Save Customers Starting");
        try {
            fos = new FileOutputStream("customer.dat");
            oos = new ObjectOutputStream(fos);
            for (Customer c: customers) {
                oos.writeObject(c);
            }
            fos.close();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nCustomers saved to file!");
    }

    public void saveRents() {
        System.out.print("\n * Save Rents Starting");
        try {
            fos = new FileOutputStream("rent.dat");
            oos = new ObjectOutputStream(fos);
            for (Rent r: rents) {
                oos.writeObject(r);
            }
            fos.close();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nRents saved to file!");
    }


    public void readBikes() {
        System.out.print("\n * Read Bikes Starting");
        bikes.clear();
        try {
            fis = new FileInputStream("bike.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Object object = ois.readObject();
                    Bike b = (Bike) object;

                    //update bike status
                    int bikeNo = b.getBikeNo();
                    Bike r = searchBikesByBikeNo(bikeNo);

                    //add to array list
                    bikes.add(b);
                    System.out.println(b);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nBikes read from file!");
    }

    public void readCustomers() {
        System.out.print("\n * Read Customers Starting\n");
        customers.clear();
        try {
            fis = new FileInputStream("customer.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Object object = ois.readObject();
                    Customer c = (Customer) object;


                    //add to array list
                    customers.add(c);
                    System.out.println(c);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nCustomers read from file!");
    }

    public void readRents() {
        System.out.print("\n * Read Rents Starting\n");
        rents.clear();
        try {
            fis = new FileInputStream("rent.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Object object = ois.readObject();
                    Rent r = (Rent) object;


                    //add to array list
                    rents.add(r);
                    System.out.println(r);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nRents read from file!");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // Bikes tab
        if (e.getSource() == btnRList) {
            checkAvailable();
            jtaR.setText("");
            for (Bike r: bikes) {
                jtaR.append("\n" + r.toString() + "\n");
            }
        }

        if (e.getSource() == btnRListAv) {
            checkAvailable();
            jtaR.setText("");
            for (Bike c: bikes) {
                if (c.getAvalible() == true) {
                    jtaR.append("\n" + c.toString() + "\n");
                }
            }
        }

        // bike modify tab
        if (e.getSource() == btnBAdd) {
            addBike();
        }

        if (e.getSource() == btnBAddE) {
            addEBike();
        }

        if (e.getSource() == btnBSave) {
            saveBikes();
            String titleBar = "Bikes saved to file 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnBRead) {
            readBikes();
            String titleBar = "Bikes restored from file 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        // customers tab
        if (e.getSource() == btnCWrite) {
            saveCustomers();
            String titleBar = "Customers saved to file 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnCRead) {
            readCustomers();
            String titleBar = "Customers restored from file 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnCAdd) {
            addCustomer();
        }

        if (e.getSource() == btnCList) {
            jtaC.setText("");
            for (Customer c: customers) {
                jtaC.append("\n" + c.toString() + "\n");
            }
        }

        // rents tab
        if (e.getSource() == btnFList) {
            jtaF.setText("");
            for (Rent f: rents) {
                jtaF.append("\n" + f.toString() + "\n");
            }
        }

        if (e.getSource() == btnFListOverDue) {
            //displayOverdue();
            jtaF.setText("");
            for (Rent f: rents) {
                LocalDate today = LocalDate.now();
                int duration = f.getDuration();
                LocalDate due = (f.getRentStart().plusDays(duration));

                if (today.isAfter(due)) {
                    String output = ("Rent ID: " + f.getRentId() + " Customer ID: " + f.getCustId() + " Bike No: " + f.getBikeNo() + " Due Date: " + due);
                    jtaF.append("\n" + output.toString() + "\n");
                }
            }
        }

        if (e.getSource() == btnFListOverDueDay) {
            //displayOverdue();
            jtaF.setText("");
            for (Rent f: rents) {

                String dateStr = txtFDate.getText();
                //String dateStr = "2016-08-16";
                LocalDate date = null;
                try {
                    date = LocalDate.parse(dateStr);
                } catch (Exception e1) {}

                int duration = f.getDuration();
                LocalDate due = (f.getRentStart().plusDays(duration));

                if (date.isAfter(due)) {
                    String output = ("Rent ID: " + f.getRentId() + " Customer ID: " + f.getCustId() + " Bike No: " + f.getBikeNo() + " Due Date: " + due);
                    jtaF.append("\n" + output.toString() + "\n");
                }
            }
        }

        if (e.getSource() == btnFRemove) {
            removeRentById();
        }


        // rent modify tab
        if (e.getSource() == btnQAdd) {
            addRent();
        }

        if (e.getSource() == btnQWrite) {
            saveRents();
            String titleBar = "Rents saved to file 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnQRead) {
            readRents();
            String titleBar = "Rents restored from file 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnQListAv) {
            checkAvailable();
            jtaQ.setText("");
            for (Bike c: bikes) {
                if (c.getAvalible() == true) {
                    jtaQ.append("\n" + c.toString() + "\n");
                }
            }
        }

        // rent misc tab
        if (e.getSource() == btnORead) {
            readBikes();
            readRents();
            readCustomers();
            String titleBar = "Restored from all files 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnOWrite) {
            saveBikes();
            saveRents();
            saveCustomers();
            String titleBar = "Saved to all files 💾";
            JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnONuke) {
            String prompt = "Erase all data?";
            int reply = JOptionPane.showConfirmDialog(null, prompt, prompt, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                bikes.clear();
                rents.clear();
                customers.clear();
                System.out.println("\nArrays wiped!");
                String titleBar = "All data deleted 💣";
                JOptionPane.showMessageDialog(null, "" + titleBar, titleBar, JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("\nCanceled by user");
            }

        }

    }

}