/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.panels;

import com.mary.kiragu.dataaccess.LoginDataManager;
import com.mary.kiragu.domain.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class UserPanel extends JPanel implements ActionListener {

    private LoginDataManager loginDataManager;
    private JPanel searchPanel, tablePanel;

    private JLabel userNameLabel;
    private JTextField userNameTextField;
    private JButton searchButton;
    private JTable searchResultsTable;

    public UserPanel() {
        super();
        this.loginDataManager = new LoginDataManager();

        initializePanel();

        setComponents();

    }

    private void initializePanel() {

        this.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        this.loginDataManager = new LoginDataManager();
        this.searchPanel = new JPanel();
        this.searchPanel.setBorder(BorderFactory.createTitledBorder("Search Panel "));

        this.tablePanel = new JPanel();
        this.tablePanel.setBorder(BorderFactory.createTitledBorder("Users "));

        this.add(searchPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);

    }

    private void setComponents() {

        //search panel components 
        this.searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.userNameLabel = new JLabel("Enter username: ");
        this.userNameTextField = new JTextField(15);
        this.searchButton = new JButton("Search");

        this.searchPanel.add(this.userNameLabel);
        this.searchPanel.add(this.userNameTextField);
        this.searchPanel.add(this.searchButton);

        this.userNameTextField.addActionListener(this);
        this.searchButton.addActionListener(this);

        //table panel componets 
        this.tablePanel.setLayout(new BorderLayout());
        this.searchResultsTable = new JTable();
        this.searchResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // select only one row 
        JScrollPane tableScrollPane = new JScrollPane(searchResultsTable);
        this.tablePanel.add(tableScrollPane);

    }

    public void loadUsers(String searchValue) {

        //search from the data manager 
        List<User> searchResults = this.loginDataManager.searchUser(searchValue);

        //set new data table model 
        DefaultTableModel tableModel = new DefaultTableModel();
        //set the column headers
        String[] col = {"User Id", "Username", "Password"};
        tableModel.setColumnIdentifiers(col);

        //loading the results into table model 
        for (User user : searchResults) {

            String[] data = new String[6];

            data[0] = user.getUserId() + "";
            data[1] = user.getUserName();
            data[2] = user.getPassword() + "";

            tableModel.addRow(data);

        }

        //set the new table model 
        searchResultsTable.setModel(tableModel);

        //updates the table 
//        searchResultsTable.revalidate(); // refresh the table
        System.out.println("List: " + searchResults);

        // we are supposed to load the data into the table 
        //logic for search
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String userNameSearchValue = this.userNameTextField.getText();

        System.out.println("Action reported: " + e.getActionCommand() + ", search value: " + userNameSearchValue);
        loadUsers(userNameSearchValue);
    }

}
