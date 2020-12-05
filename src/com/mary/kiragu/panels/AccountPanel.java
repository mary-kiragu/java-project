/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.panels;

import com.mary.kiragu.dataaccess.AccountStatementDataManager;
import com.mary.kiragu.dataaccess.TenantsDataManager;
import com.mary.kiragu.domain.AccountStatement;
import com.mary.kiragu.domain.Tenant;
import com.mary.kiragu.frames.MainFrame;
import com.mary.kiragu.frames.RecordInvoiceOrPaymentFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class AccountPanel extends JPanel implements ActionListener {

    private JPanel topPanel, centerPanel;
    private JButton searchButton, addInvoiceButton, addPaymentButton;
    private JTextField searchTenantTextField;
    private JLabel searchTenantLabel;
    private JTextField nameTextField;
    private JLabel idNumberLabel;
    private JTextField idNumberTextField;
    private JLabel balanceLabel;
    private JTextField balanceTextField;
    private AccountStatementDataManager accountStatementDataManager;
    private TenantsDataManager tenantsDataManager;
    private JTable searchAccountStatementTable;

    private MainFrame parentMainFrame;

    private RecordInvoiceOrPaymentFrame recordInvoiceOrPaymentFrame;

    private Tenant tenant;

    //all buttons here and even the table to be declared 
    public AccountPanel(MainFrame mainFrameObject) {
        super();

        this.parentMainFrame = mainFrameObject;
        this.recordInvoiceOrPaymentFrame = new RecordInvoiceOrPaymentFrame(mainFrameObject);

        this.tenantsDataManager = new TenantsDataManager();
        this.accountStatementDataManager = new AccountStatementDataManager();

        initializePanel();
        setComponents();
    }

    private void initializePanel() {

        this.setLayout(new BorderLayout());

        this.topPanel = new JPanel();
        this.topPanel.setBorder(BorderFactory.createTitledBorder("Account Search"));

        this.centerPanel = new JPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

    }

    private void setComponents() {

        //first panel
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //topLeftPanel.add(new JLabel("Tenant "));
        this.searchTenantLabel = new JLabel("Enter Tenant ID or ID Number: ");
        this.searchTenantTextField = new JTextField(15);
        this.searchTenantTextField.addActionListener(this);
        this.searchButton = new JButton("Search");
        topLeftPanel.add(searchTenantLabel);
        topLeftPanel.add(searchTenantTextField);
        topLeftPanel.add(searchButton);
        this.searchButton.addActionListener(this);
        //second top right panel panel
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // topRightPanel.add(new JLabel("Record Invoice"));

        this.addInvoiceButton = new JButton("Record invoice");
        this.addPaymentButton = new JButton("Record Payment");
        topRightPanel.add(addPaymentButton);
        topRightPanel.add(addInvoiceButton);
        this.addPaymentButton.addActionListener(this);
        this.addInvoiceButton.addActionListener(this);

        //Adding two JPanel to a panel  
        this.topPanel.setLayout(new BorderLayout());
        this.topPanel.add(topLeftPanel, BorderLayout.LINE_START);
        this.topPanel.add(topRightPanel, BorderLayout.LINE_END);

        //center panel 
        JPanel centerTopPanel = new JPanel();
        centerTopPanel.setBorder(BorderFactory.createTitledBorder("Tenant Information"));
        centerTopPanel.setLayout(new BoxLayout(centerTopPanel, BoxLayout.LINE_AXIS));
        centerTopPanel.add(Box.createHorizontalGlue());
        centerTopPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        searchTenantLabel = new JLabel("Name:");
        nameTextField = new JTextField();

        centerTopPanel.add(searchTenantLabel);
        centerTopPanel.add(nameTextField);
        nameTextField.setAlignmentX(LEFT_ALIGNMENT);
        nameTextField.setEditable(false);

        idNumberLabel = new JLabel("ID Number");
        idNumberTextField = new JTextField();
        centerTopPanel.add(idNumberLabel);
        centerTopPanel.add(idNumberTextField);
        idNumberTextField.setAlignmentX(LEFT_ALIGNMENT);
        idNumberTextField.setEditable(false);

        balanceLabel = new JLabel("Balance");
        balanceTextField = new JTextField();
        centerTopPanel.add(balanceLabel);
        centerTopPanel.add(balanceTextField);
        balanceTextField.setAlignmentX(LEFT_ALIGNMENT);
        balanceTextField.setEditable(false);

        Dimension minSize = new Dimension(5, 30);
        Dimension prefSize = new Dimension(5, 30);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 50);
        centerTopPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        // 
        JPanel centerBottomPanel = new JPanel();
        centerBottomPanel.setLayout(new BorderLayout());

        centerBottomPanel.setBorder(BorderFactory.createTitledBorder("Account Statement"));
        centerBottomPanel.add(new JLabel("center bottom - table"));
        searchAccountStatementTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(searchAccountStatementTable);
        centerBottomPanel.add(tableScrollPane);
        centerBottomPanel.add(searchAccountStatementTable);

        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(centerTopPanel, BorderLayout.NORTH);

        this.centerPanel.add(centerBottomPanel, BorderLayout.CENTER);

    }

    public void reLoadAccountStatements() {
        loadAccountStatements(tenant.getId() + "");
    }

    public void loadAccountStatements(String searchValue) {

        //set balance 
        if (tenant != null) {
            this.balanceTextField.setText(tenant.getBalance() + "");
            
        }

        //search from the data manager 
        List<AccountStatement> searchResults = this.accountStatementDataManager.searchByTenantId(searchValue);

        //set new data table model 
        DefaultTableModel tableModel = new DefaultTableModel();
        //set the column headers
        String[] col = {"StatementId", "TenantId", "TransactionDate", "TransactionType",
            "Details", "Amount", "BalanceAfter", "User"};
        tableModel.setColumnIdentifiers(col);

        //loading the results into table model 
        for (AccountStatement accountStatement : searchResults) {

            String[] data = new String[8];

            data[0] = accountStatement.getStatementId() + "";
            data[1] = accountStatement.getTenantId() + " ";
            data[2] = accountStatement.getTransactionDate();
            data[3] = accountStatement.getTransactionType();
            data[4] = accountStatement.getDetails();
            data[5] = accountStatement.getAmount() + "";
            data[6] = accountStatement.getBalanceAfter() + "";
            data[7] = accountStatement.getUser() + "";
            tableModel.addRow(data);

        }

        //set the new table model 
        searchAccountStatementTable.setModel(tableModel);

        searchAccountStatementTable.revalidate(); // refresh the table
        System.out.println("List: " + searchResults);

        // we are supposed to load the data into the table 
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.addInvoiceButton) {

            this.recordInvoiceOrPaymentFrame.setVisible(true);
            this.recordInvoiceOrPaymentFrame.setAlwaysOnTop(true);

            //pass the tenant id and transaction type to the record invoice frame
            this.recordInvoiceOrPaymentFrame.setTransactionType("INVOICE");
            this.recordInvoiceOrPaymentFrame.setTenant(tenant);

        } else if (e.getSource() == this.addPaymentButton) {

            this.recordInvoiceOrPaymentFrame.setVisible(true);
            this.recordInvoiceOrPaymentFrame.setAlwaysOnTop(true);

            //pass the tenant id and transaction type to the record invoice frame
            this.recordInvoiceOrPaymentFrame.setTransactionType("PAYMENT");
            this.recordInvoiceOrPaymentFrame.setTenant(tenant);

        } else if (e.getSource() == this.searchButton || e.getSource() == this.searchTenantTextField) {

            //clear the fields and the table 
            this.nameTextField.setText("");
            this.idNumberTextField.setText("");
            this.balanceTextField.setText("");

            String searchValue = this.searchTenantTextField.getText();

            Tenant tenant = this.tenantsDataManager.searchTenantsByIdOrIDNumber(searchValue);

            System.out.println("searching for tenant: " + searchValue);

            System.out.println("tenant: " + tenant);

            if (tenant != null) {
                this.nameTextField.setText(tenant.getFirstName() + " " + tenant.getSecondName() + " " + tenant.getSurname() + "(Room " + tenant.getRoomId() + ")");
                this.idNumberTextField.setText(tenant.getIdNumber());
                this.balanceTextField.setText("KES " + tenant.getBalance());

                this.tenant = tenant;

                //load the data to the table
                System.out.println("Action reported: " + e.getActionCommand() + ", search value: " + searchValue);
                loadAccountStatements(this.tenant.getId() + "");

            }

        }

    }

}
