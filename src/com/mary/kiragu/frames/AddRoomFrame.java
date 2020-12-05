/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.frames;

import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 *
 */
import com.mary.kiragu.dataaccess.RoomsDataManager;

import com.mary.kiragu.domain.Room;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddRoomFrame extends JFrame implements ActionListener {

    private JLabel roomIdLabel;
    private JTextField roomIdTextField;
    private JLabel roomNameLabel;
    private JTextField roomNameTextField;
   
    private JLabel rentLabel;
    private JTextField rentTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JButton addRoomButton;
    private MainFrame mainFrame;
    private RoomsDataManager roomsDataManager;

    public AddRoomFrame(MainFrame mainFrameObject) {
        dispose();
        this.mainFrame = mainFrameObject;
        this.roomsDataManager = new RoomsDataManager();
        initializeFrame();
        addComponents();
    }

    private void initializeFrame() {

        this.setTitle("Add Room- AMS");

        this.setLayout(null);

        this.setSize(400, 350);
        this.setTitle("Add Room");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false);

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

    private void addComponents() {
        super.dispose();
        
        roomNameLabel = new JLabel("Room Name");
        roomNameLabel.setBounds(30, 30, 100, 30);
        roomNameTextField = new JTextField();
        roomNameTextField.setBounds(160, 30, 100, 30);
        
        rentLabel = new JLabel("Rent");
        rentLabel.setBounds(30, 150, 100, 30);
        rentTextField = new JTextField();
        rentTextField.setBounds(160, 150, 100, 30);

        addRoomButton = new JButton("ADD");
        addRoomButton.setBounds(160, 210, 100, 30);
        
        this.add(roomNameLabel);
        this.add(roomNameTextField);
        
        this.add(rentLabel);
        this.add(rentTextField);
        this.add(addRoomButton);
        this.addRoomButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Room newRoom = new Room();
        
        
        String roomName = roomNameTextField.getText();
       
        String rent = rentTextField.getText();
        newRoom.setRoomName(roomName);
        newRoom.setRent(Integer.parseInt(rent));
        
         boolean added = this.roomsDataManager.addRoom(newRoom);

        if (added) {
            //the tenant has been added successfully - we need to dispose the frame

            //reset input fields 
            
            this.roomNameTextField.setText("");
            
            this.rentTextField.setText("");
            
            this.setVisible(false);

            //show dialog showing success and on confirm, you dispose 
        } else {
            //show error dialog message 
        }

    }


    }


