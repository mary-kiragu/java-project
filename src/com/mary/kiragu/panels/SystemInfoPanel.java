/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mary.kiragu.panels;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class SystemInfoPanel extends JPanel {

    private JLabel systemVersionLabel;
    private JLabel loggedInUserLabel;

    public SystemInfoPanel() {

        super();

        this.setLayout(new BorderLayout(0, 0));

        this.systemVersionLabel = new JLabel("Apartment Management System (AMS) version 1.0 by Mary Kiragu - C026-01-1330/2018");
        this.loggedInUserLabel = new JLabel("Login: ");

        setLabels();

    }

    private void setLabels() {

        this.add(this.systemVersionLabel, BorderLayout.LINE_START);
        this.add(this.loggedInUserLabel, BorderLayout.LINE_END);
    }

    public void setLogin(String username) {
        this.loggedInUserLabel.setText("Login: " + username);
    }

}
