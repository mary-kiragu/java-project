package com.mary.kiragu.panels;

import com.mary.kiragu.dataaccess.TenantsDataManager;
import com.mary.kiragu.domain.Tenant;
import com.mary.kiragu.domain.AccountStatement;
import com.mary.kiragu.dataaccess.AccountStatementDataManager;
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
public class TenantsPanel extends JPanel implements ActionListener {

    private JPanel searchPanel, tablePanel;

    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton searchButton;
    private JTable searchResultsTable;
    private AccountStatement accountStatement;
    private TenantsDataManager tenantsDataManager;
    private AccountStatementDataManager accountStatementDataManager;
    public TenantsPanel() {

        super();

        this.tenantsDataManager = new TenantsDataManager();
        this.accountStatement=new AccountStatement();
        this.accountStatementDataManager=new AccountStatementDataManager();
        initializePanel();

        setComponents();

    }

    private void initializePanel() {

        this.setLayout(new BorderLayout());

        this.searchPanel = new JPanel();
        this.searchPanel.setBorder(BorderFactory.createTitledBorder("Search Panel "));

        this.tablePanel = new JPanel();
        this.tablePanel.setBorder(BorderFactory.createTitledBorder("Tenants Results "));

        this.add(searchPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);

    }

    private void setComponents() {
        //search panel components 
        this.searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.nameLabel = new JLabel("Enter Tenant Name: ");
        this.nameTextField = new JTextField(15);
        this.searchButton = new JButton("Search");

        this.searchPanel.add(this.nameLabel);
        this.searchPanel.add(this.nameTextField);
        this.searchPanel.add(this.searchButton);

        this.nameTextField.addActionListener(this);
        this.searchButton.addActionListener(this);

        //table panel componets 
        this.tablePanel.setLayout(new BorderLayout());
        this.searchResultsTable = new JTable();
        this.searchResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // select only one row 
        JScrollPane tableScrollPane = new JScrollPane(searchResultsTable);
        this.tablePanel.add(tableScrollPane);

    }

    public void loadTenants(String searchValue) {

        //search from the data manager 
        List<Tenant> searchResults = this.tenantsDataManager.searchTenants(searchValue);

        //set new data table model 
        DefaultTableModel tableModel = new DefaultTableModel();
        //set the column headers
        String[] col = {"ID", "Name", "ID No.", "Phone", "Email", "Balance","Room"};
        tableModel.setColumnIdentifiers(col);

        //loading the results into table model 
        for (Tenant tenant : searchResults) {

            String[] data = new String[7];

            data[0] = tenant.getId() + "";
            data[1] = tenant.getFirstName() + " " + tenant.getSecondName() + " " + tenant.getSurname();
            data[2] = tenant.getIdNumber();
            data[3] = tenant.getPhoneNumber();
            data[4] = tenant.getEmailAddress();
            data[5] = tenant.getBalance() + "";
           // data[6]=tenant.getRoomId()+"";
            tableModel.addRow(data);

        }

        //set the new table model 
        searchResultsTable.setModel(tableModel);

       
//        searchResultsTable.revalidate(); // refresh the table
        System.out.println("List: " + searchResults);

      
        
    }
    public void updateBalance(){
    int balance=accountStatement.getBalanceAfter();
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String nameSearchValue = this.nameTextField.getText();

        System.out.println("Action reported: " + e.getActionCommand() + ", search value: " + nameSearchValue);
        
        loadTenants(nameSearchValue);

    }

}
