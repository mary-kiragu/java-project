package com.mary.kiragu;

import com.mary.kiragu.frames.LoginFrame;
import com.mary.kiragu.frames.MainFrame;

/**
 *
 * @author Lenovo
 */
public class AMS {

    private MainFrame mainFrame;
    private LoginFrame loginFrame;

    public AMS() {
        this.mainFrame = new MainFrame();
        this.loginFrame = new LoginFrame(mainFrame);
    }

    public void start() {
        //show the login 
        this.loginFrame.setVisible(true);

        // comment or remove before gi live
//        this.mainFrame.reLoadTenants();
//        this.mainFrame.setVisible(true);
    }

    public static void main(String[] args) {

        AMS application = new AMS();

        application.start();

    }

}
