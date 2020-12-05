package com.mary.kiragu.frames;

import com.mary.kiragu.dataaccess.TenantsDataManager;
import com.mary.kiragu.dataaccess.RoomsDataManager;
import com.mary.kiragu.domain.Room;
import com.mary.kiragu.domain.Tenant;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class AddTenantFrame extends JFrame implements ActionListener {

    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel secondNameLabel;
    private JTextField secondNameTextField;
    private JLabel surNameLabel;
    private JTextField surNameTextField;
    private JLabel idNumberLabel;
    private JTextField idNumberTextField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel roomLabel;
    private JTextField roomTextfield;
    private JLabel amountPaidLabel;
    private JTextField amountPaidTextField;
    private JLabel balanceLabel;
    private JTextField balanceTextField;
    private JButton addTenantButton;
    private JComboBox rooms;
    private MainFrame mainFrame;
    private TenantsDataManager tenantsDataManager;
    private RoomsDataManager roomsDataManager;

    public AddTenantFrame(MainFrame mainFrameObject) {
        super();
        this.mainFrame = mainFrameObject;
        this.tenantsDataManager = new TenantsDataManager();
        this.roomsDataManager=new RoomsDataManager();
        initializeFrame();
        addComponents();

    }

    private void initializeFrame() {

        this.setTitle("AddTenant- AMS");

        this.setLayout(null);

        this.setSize(400, 650);
        this.setTitle("Add Tenant");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(true);

        //set at the center of screen 
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenDimension.getWidth() - this.getWidth()) / 2;
        int y = (int) (screenDimension.getHeight() - 100 - this.getHeight()) / 2; // substract 50 because of the computer tool bar 
        this.setBounds(x, y, this.getWidth(), this.getHeight());

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String firstName = firstNameTextField.getText();
        String secondName = secondNameTextField.getText();
        String surname = surNameTextField.getText();
        String idNumber = idNumberTextField.getText();
        String balance = balanceTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber=phoneNumberTextField.getText();
        
         String amountPaid = amountPaidTextField.getText();
        System.out.println(firstName);
        System.out.println(secondName);
        System.out.println(surname);

        //extract all data 
        //do validation - mobile number cannot be null 
        //create tenant object 
        Tenant newTenant = new Tenant();
        newTenant.setFirstName(firstName);
        newTenant.setSecondName(secondName);
        newTenant.setSurname(surname);
        newTenant.setIdNumber(idNumber);
        newTenant.setBalance(Integer.parseInt(balance));
        
        newTenant.setEmailAddress(email);
        newTenant.setPhoneNumber(phoneNumber);
       
        newTenant.setAmountPaid(Integer.parseInt(amountPaid));
        
        System.out.println("tenant: "+ newTenant);

        //call data manager to save 
        boolean added = this.tenantsDataManager.addTenant(newTenant);

        if (added) {
            //the tenant has been added successfully - we need to dispose the frame

            //reset input fields 
            this.firstNameTextField.setText("");
            this.surNameTextField.setText("");
            this.secondNameTextField.setText("");
            this.idNumberTextField.setText("");
            this.phoneNumberTextField.setText("");
            this.roomTextfield.setText("");
            this.amountPaidTextField.setText("");
            this.balanceTextField.setText("");
            this.emailTextField.setText("");
            this.setVisible(false);
            
            this.mainFrame.reLoadTenants();

            //show dialog showing success and on confirm, you dispose - TO DO LATER
        } else {
            //show error dialog message 
        }

    }

    private void addComponents() {
        super.dispose();
        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(30, 30, 100, 30);
        this.add(firstNameLabel);
        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(165, 30, 100, 30);
        this.add(firstNameTextField);

        secondNameLabel = new JLabel("Second Name");
        secondNameLabel.setBounds(30, 90, 100, 30);
        this.add(secondNameLabel);

        secondNameTextField = new JTextField();
        secondNameTextField.setBounds(160, 90, 100, 30);
        this.add(secondNameTextField);

        surNameLabel = new JLabel("Surname");
        surNameLabel.setBounds(30, 150, 100, 30);
        this.add(surNameLabel);

        surNameTextField = new JTextField();
        surNameTextField.setBounds(160, 150, 100, 30);
        this.add(surNameTextField);
        idNumberLabel = new JLabel("ID Number ");
        idNumberLabel.setBounds(30, 210, 100, 30);
        this.add(idNumberLabel);
        idNumberTextField = new JTextField();
        idNumberTextField.setBounds(160, 210, 100, 30);
        this.add(idNumberTextField);
        phoneNumberLabel = new JLabel("Phone Number ");
        phoneNumberLabel.setBounds(30, 270, 100, 30);
        this.add(phoneNumberLabel);
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(160, 270, 100, 30);
        this.add(phoneNumberTextField);

        emailLabel = new JLabel("Email ");
        emailLabel.setBounds(30, 330, 100, 30);
        this.add(emailLabel);
        emailTextField = new JTextField();
        emailTextField.setBounds(160, 330, 100, 30);
        this.add(emailTextField);
        roomLabel = new JLabel("Room occupied");
        roomLabel.setBounds(30, 390, 100, 30);
        this.add(roomLabel);
        rooms=new JComboBox();
        rooms.setBounds(160, 390, 100, 30);
        this.add(rooms);
        
        List<Room> roomsList = this.roomsDataManager.searchRooms("");
       
        System.out.println("rooms: "+roomsList);
        
        for(Room room: roomsList){
            rooms.addItem(room.getRoomName());
        }
       
        /*roomTextfield = new JTextField();
        roomTextfield.setBounds(160, 390, 100, 30);
        rooms=new JComboBox();
        roomTextfield.add(rooms);
        this.add(roomTextfield);*/
        
        amountPaidLabel = new JLabel("Amount Paid");
        amountPaidLabel.setBounds(30, 450, 100, 30);
        this.add(amountPaidLabel);
        amountPaidTextField = new JTextField();
        amountPaidTextField.setBounds(160, 450, 100, 30);
        this.add(amountPaidTextField);
        balanceLabel = new JLabel("Balance");
        balanceLabel.setBounds(30, 510, 100, 30);
        this.add(balanceLabel);
        balanceTextField = new JTextField();
        balanceTextField.setBounds(160, 510, 100, 30);
        this.add(balanceTextField);

        addTenantButton = new JButton("ADD");
        addTenantButton.setBounds(165, 570, 100, 30);
        this.add(addTenantButton);
        this.addTenantButton.addActionListener(this);
        this.firstNameTextField.addActionListener(this);
        this.idNumberTextField.addActionListener(this);
        this.secondNameTextField.addActionListener(this);
        this.surNameTextField.addActionListener(this);
        this.phoneNumberTextField.addActionListener(this);
        this.rooms.addActionListener(this);
        this.amountPaidTextField.addActionListener(this);
        System.out.println("" + firstNameTextField);
    }

}
