package com.mary.kiragu.frames;

import com.mary.kiragu.panels.RoomsPanel;
import com.mary.kiragu.panels.SystemInfoPanel;
import com.mary.kiragu.panels.TenantsPanel;
import com.mary.kiragu.panels.UserPanel;
import com.mary.kiragu.panels.AccountPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Lenovo
 */
public class MainFrame extends JFrame implements ActionListener {

    //declare the menu bar and menus 
    private JMenuBar menuBar;
    private JMenu tenantsMenu;
    private JMenuItem tenantsMenuItem;
    private JMenuItem newTenantMenuItem;
    private JMenu roomsMenu;
    private JMenuItem roomsMenuItem;
    private JMenuItem newRoomMenuItem;
    private JMenu UsersMenu;
    private JMenuItem usersMenuItem;
    private JMenuItem newUserMenuItem;
    private JMenu accountMenu;
    private JMenuItem accountMenuItem;

    //declare the panels 
    private SystemInfoPanel systemInfoPanel;
    private TenantsPanel tenantsPanel;
    private RoomsPanel roomsPanel;
    private UserPanel userPanel;
    private AccountPanel accountPanel;

    //add the tenants main panel 
    private AddTenantFrame addTenantFrame;
    private AddRoomFrame addRoomFrame;
    private AddUserFrame addUserFrame;
    private TenantsFrame tenantsFrame;

    private String loggedInUser = "unknown";
    private int balanceAfter;

    //add the tenants rooms panel 
    public MainFrame() throws HeadlessException {

        super();

        //set the title
        super.setTitle("Apartments Management System - AMS");

        this.systemInfoPanel = new SystemInfoPanel();
        this.tenantsPanel = new TenantsPanel();
        this.roomsPanel = new RoomsPanel();
        this.userPanel = new UserPanel();
        this.accountPanel = new AccountPanel(this);

        //initialize the other frames
        this.addTenantFrame = new AddTenantFrame(this);
        this.addRoomFrame = new AddRoomFrame(this);
        this.addUserFrame = new AddUserFrame(this);
        this.tenantsFrame = new TenantsFrame(this);
        //set the jmenu bar 
        initializeJMenuBar();

        //set the size of frame 
        setFrameSize();

        setComponents();

    }

    private void initializeJMenuBar() {

        //create menu bar and add it to frame
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //create menus and add to menu bar
        tenantsMenu = new JMenu("Tenants");
        roomsMenu = new JMenu("Rooms");
        UsersMenu = new JMenu("Users");
        accountMenu = new JMenu("Accounts");

        menuBar.add(tenantsMenu);
        menuBar.add(roomsMenu);
        menuBar.add(UsersMenu);
        menuBar.add(accountMenu);
        //create rooms menu items and add to rooms menu 
        roomsMenuItem = new JMenuItem("Rooms");
        newRoomMenuItem = new JMenuItem("New Room");
        roomsMenu.add(roomsMenuItem);
        roomsMenu.add(newRoomMenuItem);

        //create tenants menu and add to tenants menu 
        tenantsMenuItem = new JMenuItem("Tenants");
        newTenantMenuItem = new JMenuItem("New Tenant");
        tenantsMenu.add(tenantsMenuItem);
        tenantsMenu.add(newTenantMenuItem);
//Create users menu and add Menu Items
        usersMenuItem = new JMenuItem("Users");
        newUserMenuItem = new JMenuItem("New User");
        UsersMenu.add(usersMenuItem);
        UsersMenu.add(newUserMenuItem);
        //add accounts menu items
        accountMenuItem = new JMenuItem("Account");
        accountMenu.add(accountMenuItem);
        //register action listeners 
        roomsMenuItem.addActionListener(this);
        newRoomMenuItem.addActionListener(this);
        tenantsMenuItem.addActionListener(this);
        newTenantMenuItem.addActionListener(this);
        usersMenuItem.addActionListener(this);
        newUserMenuItem.addActionListener(this);
        accountMenuItem.addActionListener(this);

    }

    private void setFrameSize() {

        //set resizable 
        this.setResizable(true);

        //get the screen dimension of the computer
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screenDimension.getWidth();

        int height = (int) screenDimension.getHeight() - 50; // substract 50 because of the computer tool bar 

        this.setSize(width, height);

    }

    private void setComponents() {

        this.setLayout(new BorderLayout());

        //add system info panel 
        this.add(this.systemInfoPanel, BorderLayout.SOUTH);

        //set the tenants as the default panel 
        this.add(tenantsPanel, BorderLayout.CENTER);

    }

    public SystemInfoPanel getSystemInfoPanel() {
        return systemInfoPanel;
    }

    public void reLoadTenants() {
        this.tenantsPanel.loadTenants("");
    }
    
    public void reLoadAccountStatement() {
        this.accountPanel.reLoadAccountStatements();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Clicked menu item: " + e.getActionCommand());

        if (e.getSource() == this.tenantsMenuItem) {

            this.remove(roomsPanel);
            this.remove(userPanel);
            this.remove(accountPanel);

            this.tenantsPanel.loadTenants(""); //load

//            this.removeAll();
            //  System.out.println("User clicked on tenants menu item - set the center of jframe to have tenants panel ");
            this.add(tenantsPanel, BorderLayout.CENTER);
//            this.tenantsFrame.setVisible(true);
//            this.tenantsFrame.setAlwaysOnTop(true);

        } else if (e.getSource() == this.newTenantMenuItem) {
            System.out.println("User clicked on New tenant menu item - set the center of jframe to have tenants panel, show the new tenant frame infront of main");

            this.remove(roomsPanel);
            this.remove(userPanel);
            this.remove(accountPanel);

            this.add(tenantsPanel, BorderLayout.CENTER);

            this.addTenantFrame.setVisible(true);
            this.addTenantFrame.setAlwaysOnTop(true);

            //add new tenant frame to be displayed 
        } else if (e.getSource() == this.roomsMenuItem) {

            this.remove(tenantsPanel);
            this.remove(userPanel);
            this.remove(accountPanel);

            this.roomsPanel.loadRooms("");

            System.out.println("User clicked on rooms menu item - set the center of jframe to have rooms panel");
            this.add(roomsPanel, BorderLayout.CENTER);

        } else if (e.getSource() == this.newRoomMenuItem) {

            this.remove(tenantsPanel);
            this.remove(userPanel);
            this.remove(accountPanel);

            System.out.println("User clicked on rooms menu item - set the center of jframe to have rooms panel, show new room frame");
            this.add(roomsPanel, BorderLayout.CENTER);

            this.addRoomFrame.setVisible(true);
            this.addRoomFrame.setAlwaysOnTop(true);

            //add new tenant frame to be displayed 
        } else if (e.getSource() == this.usersMenuItem) {
            this.remove(roomsPanel);
            this.remove(tenantsPanel);
            this.remove(accountPanel);
            this.userPanel.loadUsers("");

            this.add(userPanel, BorderLayout.CENTER);

        } else if (e.getSource() == this.newUserMenuItem) {
            this.remove(roomsPanel);
            this.remove(tenantsPanel);
            this.remove(accountPanel);

            this.add(userPanel, BorderLayout.CENTER);

            this.addUserFrame.setVisible(true);
            this.addUserFrame.setAlwaysOnTop(true);

        } else if (e.getSource() == this.accountMenuItem) {

            this.remove(roomsPanel);
            this.remove(tenantsPanel);
            this.remove(userPanel);

            this.add(accountPanel, BorderLayout.CENTER);
            this.accountPanel.loadAccountStatements("");
        }
        this.invalidate();
        this.validate();
        this.repaint();

    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
     public void setBalanceAfter(int balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

}
