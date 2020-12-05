/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.frames;

import com.mary.kiragu.dataaccess.AccountStatementDataManager;
import com.mary.kiragu.domain.AccountStatement;
import com.mary.kiragu.domain.Tenant;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class RecordInvoiceOrPaymentFrame extends JFrame implements ActionListener {

    private MainFrame mainFrame;
    private AccountStatementDataManager accountStatementDataManager;
    private JLabel amountAddedLabel;
    private JTextField amountAddedTextField;
    private JLabel detailsLabel;
    private JTextField detailsTextField;
    private JButton saveButton;

    private Tenant tenant;
    private String transactionType;

    /**
     *
     * @param mainFrame
     */
    public RecordInvoiceOrPaymentFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.accountStatementDataManager = new AccountStatementDataManager();

        initializeFrame();
        setComponents();
    }

    private void initializeFrame() {

        this.setTitle("PaymentRecords AMS");

        this.setLayout(null);

        this.setSize(400, 350);
        this.setTitle("Add Payment/Invoice");
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

    public void setComponents() {
        amountAddedLabel = new JLabel("Amount");
        amountAddedLabel.setBounds(30, 30, 100, 30);
        this.add(amountAddedLabel);

        amountAddedTextField = new JTextField();
        amountAddedTextField.setBounds(165, 30, 200, 30);
        this.add(amountAddedTextField);

        detailsLabel = new JLabel("Details");
        detailsLabel.setBounds(30, 90, 100, 30);
        this.add(detailsLabel);
        detailsTextField = new JTextField();
        detailsTextField.setBounds(165, 90, 200, 30);
        this.add(detailsTextField);

        saveButton = new JButton("Save");
        saveButton.setBounds(165, 150, 100, 30);
        this.add(saveButton);

        //set action listener on button and text fields 
        this.saveButton.addActionListener(this);
        this.amountAddedTextField.addActionListener(this);
        this.detailsTextField.addActionListener(this);

    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;

        this.setTitle("Record " + transactionType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String amountAdded = amountAddedTextField.getText();
        String details = detailsTextField.getText();

        int amount = Integer.parseInt(amountAdded);

        int balanceBefore = tenant.getBalance();
        int balanceAfter = 0;

        //change the balance 
        if (this.transactionType.equalsIgnoreCase("PAYMENT")) {
            balanceAfter = balanceBefore - amount; //reduce the balance
            tenant.setBalance(balanceAfter);
        } else {
            balanceAfter = balanceBefore + amount; //reduce the balance
            tenant.setBalance(balanceAfter);
        }

        //save the tenant 
        AccountStatement newRecord = new AccountStatement();
        newRecord.setAmount(Integer.parseInt(amountAdded));
        newRecord.setDetails(details);
        newRecord.setTransactionType(transactionType);
        newRecord.setTenantId(this.tenant.getId());
        newRecord.setUser(mainFrame.getLoggedInUser());
        newRecord.setBalanceBefore(balanceBefore);
        newRecord.setBalanceAfter(balanceAfter);

        System.out.println("about to create a statement: " + newRecord);

        boolean added = this.accountStatementDataManager.accountStatement(newRecord);

        if (added) {
            //the tenant has been added successfully - we need to dispose the frame

            //reset input fields 
            this.amountAddedTextField.setText("");
            this.detailsTextField.setText("");

            this.mainFrame.reLoadAccountStatement();

            this.setVisible(false);
        }

    }
}
