package com.mary.kiragu.frames;

import com.mary.kiragu.dataaccess.LoginDataManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class LoginFrame extends JFrame implements ActionListener {

    private JPanel bioDataPanel;
    private JPanel otherDetail;
    private JPanel submit;
    private JLabel usernameLabel;
    private JTextField usernameTextField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;

    private LoginDataManager loginDataManager;

    private MainFrame mainFrame;

    public LoginFrame(MainFrame mainFrameObject) {

        //called JFrame (parent) constructor
        super();

        this.mainFrame = mainFrameObject;

        loginDataManager = new LoginDataManager();

        initializeFrame();

        //set all components on the frame 
        setComponents();

    }

    private void initializeFrame() {

        this.setTitle("Login - AMS");

        this.setLayout(null);

        this.setSize(400, 250);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        loginButton = new JButton("Login");
        loginButton.setBounds(165, 150, 100, 30);
        this.add(loginButton);

        //set action listener on button and text fields 
        this.loginButton.addActionListener(this);
        this.usernameTextField.addActionListener(this);
        this.passwordField.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameTextField.getText();

        String password = passwordField.getText();
        boolean loginSuccess = LoginDataManager.authenticate(username, password);
        if (loginSuccess) {
            this.dispose();
            mainFrame.getSystemInfoPanel().setLogin(username);
            mainFrame.setLoggedInUser(username); 
            mainFrame.reLoadTenants();
            mainFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "The usename or password you entered"
                    + "is not correct please try again");
            /*  boolean valid = loginDataManager.authenticate(username, password);

       // if (valid) {
            //redirect to the main frame
            //System.out.println("username: " + username + " and passowrd:" + password + ", valid - disposing login and setting main frame visible");

            //hide login - set visible to false 
            this.dispose();

            //set the logged in user and set main fram visible 
            mainFrame.getSystemInfoPanel().setLogin(username);
            mainFrame.setVisible(true);

       // } else {

            // TO DO - SHOW A DIALOG - your work... 
            //show joption pane dialog with the error
            System.out.println("username: " + username + " and passowrd:" + password + ", invalid");
        }*/

        }

    }
}
