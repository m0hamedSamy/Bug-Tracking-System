import javax.swing.*;
import java.awt.event.*;

public class Developer extends Window {
    private JPanel panel1;
    private JButton viewAllBugsButton;
    private JButton notification;
    private JButton logoutButton;
    private JLabel welcomeLabel;
    Mail n;
    public Developer(){

        super(user.curentuser,500,500,JFrame.EXIT_ON_CLOSE);
        ContentPanel.add(panel1);
        StyleComponents(panel1);
        String usermail=user.curentuser;

        welcomeLabel.setText("welcome back "+user.curentuser);
        Inbox.checkInbox(notification,usermail,user.currentcode);
        viewAllBugsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBugs vb=new ViewBugs(usermail);

                dispose();

            }
        });

        notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 n=new Mail(usermail,1);
                Inbox.checkInbox(notification,usermail,user.currentcode);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checklogin c=new checklogin("Bug Tracking System ", 400, 260, JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });



    }


    }

