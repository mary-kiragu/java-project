/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.frames;

import com.mary.kiragu.dataaccess.LoginDataManager;
import com.mary.kiragu.domain.User;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class AddUserFrame extends JFrame implements ActionListener {

    private JLabel usernameLabel;
    private JTextField usernameTextField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton Register;

    private LoginDataManager loginDataManager;

    private MainFrame mainFrame;

    public AddUserFrame(MainFrame mainFrameObject) throws HeadlessException {
        super();

        this.mainFrame = mainFrameObject;

        loginDataManager = new LoginDataManager();

        initializeFrame();

        //set all components on the frame 
        setComponents();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void setComponents() {

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 30, 100, 30);
        this.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(165, 30, 200, 30);
        this.add(usernameTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 90, 100, 30);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(165, 90, 200, 30);
        this.add(passwordField);

        Register = new JButton("Register");
        Register.setBounds(165, 150, 100, 30);
        this.add(Register);

        //set action listener on button and text fields 
        this.Register.addActionListener(this);
        this.usernameTextField.addActionListener(this);
        this.passwordField.addActionListener(this);

    }

    private void initializeFrame() {

        this.setTitle("Add a user - AMS");

        this.setLayout(null);

        this.setSize(400, 250);
        this.setTitle("Register");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false);

        //set at the center of screen 
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenDimension.getWidth() - this.getWidth()) / 2;
        int y = (int) (screenDimension.getHeight() - 100 - this.getHeight()) / 2; // substract 50 because of the computer tool bar 
        this.setBounds(x, y, this.getWidth(), this.getHeight());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // User newUSer = new User();

        String username = usernameTextField.getText();
        String password = passwordField.getText();
        User newLogin = new User();
        newLogin.setUserName(username);
        newLogin.setPassword(password);
        boolean added = this.loginDataManager.addUser(newLogin);

        if (added) {
            //the tenant has been added successfully - we need to dispose the frame

            //reset input fields 
            this.usernameTextField.setText("");
            this.passwordField.setText("");

            this.setVisible(false);
        } 
    }

}
