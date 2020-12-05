package com.mary.kiragu.frames;

import com.mary.kiragu.dataaccess.TenantsDataManager;
import com.mary.kiragu.panels.TenantsPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTable;

public class TenantsFrame extends JFrame {

    private JTable table;

    private TenantsPanel tenantsPanel;
    private MainFrame mainFrame;
    private TenantsDataManager tenantsDataManager;

    public TenantsFrame(MainFrame mainFrameObject) throws HeadlessException {
        super();
        this.tenantsPanel = new TenantsPanel();
        this.mainFrame = mainFrameObject;
        this.tenantsDataManager = new TenantsDataManager();

        setFrameSize();
        setComponents();
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

    private void addComponents() {

        super.dispose();
        table = new JTable();
    }

    private void setComponents() {

        this.setLayout(new BorderLayout());

        //set the tenants as the default panel 
        this.add(tenantsPanel, BorderLayout.CENTER);

    }
}
