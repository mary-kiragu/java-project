package com.mary.kiragu.panels;

import com.mary.kiragu.dataaccess.RoomsDataManager;
import com.mary.kiragu.domain.Room;
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
public class RoomsPanel extends JPanel implements ActionListener {

    private JPanel searchPanel, tablePanel;

    private JLabel roomNumberLabel;
    private JTextField roomNumberTextField;
    private JButton searchButton;
    private JTable searchResultsTable;
    private RoomsDataManager roomsDataManager;

    //rooms data manager to be created 
    public RoomsPanel() {

        super();

        //initialize rooms data manager 
        initializePanel();

        setComponents();

    }

    private void initializePanel() {

        this.setLayout(new BorderLayout());
        this.roomsDataManager = new RoomsDataManager();
        this.searchPanel = new JPanel();
        this.searchPanel.setBorder(BorderFactory.createTitledBorder("Search Panel "));

        this.tablePanel = new JPanel();
        this.tablePanel.setBorder(BorderFactory.createTitledBorder("Rooms "));

        this.add(searchPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);

    }

    private void setComponents() {
        //search panel components 
        this.searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.roomNumberLabel = new JLabel("Enter Room Name: ");
        this.roomNumberTextField = new JTextField(15);
        this.searchButton = new JButton("Search");

        this.searchPanel.add(this.roomNumberLabel);
        this.searchPanel.add(this.roomNumberTextField);
        this.searchPanel.add(this.searchButton);

        this.roomNumberTextField.addActionListener(this);
        this.searchButton.addActionListener(this);

        //table panel componets 
        this.tablePanel.setLayout(new BorderLayout());
        this.searchResultsTable = new JTable();
        this.searchResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // select only one row 
        JScrollPane tableScrollPane = new JScrollPane(searchResultsTable);
        this.tablePanel.add(tableScrollPane);

    }

    public void loadRooms(String searchValue) {

        //search from the data manager 
        List<Room> searchResults = this.roomsDataManager.searchRooms(searchValue);

        //set new data table model 
        DefaultTableModel tableModel = new DefaultTableModel();
        //set the column headers
        String[] col = {"RoomID", "RoomName", "Rent", };
        tableModel.setColumnIdentifiers(col);

        //loading the results into table model 
        for (Room room : searchResults) {

            String[] data = new String[6];

            data[0] = room.getRoomId() + "";
            data[1] = room.getRoomName() ;
            data[2] = room.getRent() + "";
            

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

        String roomNumberSearchValue = this.roomNumberTextField.getText();

        System.out.println("Action reported: " + e.getActionCommand() + ", search value: " + roomNumberSearchValue);
        loadRooms(roomNumberSearchValue);
    }

}
