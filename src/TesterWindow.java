import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TesterWindow extends Window {
    private JButton defineBug = new JButton("Define a Bug");
    private JButton assignBug = new JButton("Assign a Bug");
    private JButton monitorBugs = new JButton("Monitor all Bugs");
    private JButton logout = new JButton("Logout");
    private JButton notification=new JButton();

    TesterWindow(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        AssignBug assignBugMenu = new AssignBug();
        DefineBugPage defineBugMenu = new DefineBugPage();
        MonitorBugs monitorBugsMenu = new MonitorBugs();


        ContentPanel.add(defineBug);
        ContentPanel.add(assignBug);
        ContentPanel.add(monitorBugs);
        ContentPanel.add(logout);
        ContentPanel.add(notification);

        ContentPanel.add(defineBugMenu.GetRoot());
        ContentPanel.add(assignBugMenu.GetRoot());
        ContentPanel.add(monitorBugsMenu.GetRoot());
        StyleComponents(ContentPanel);
        StyleComponents(defineBugMenu.GetRoot());
        StyleComponents(assignBugMenu.GetRoot());
        StyleComponents(monitorBugsMenu.GetRoot());

        Inbox.checkInbox(notification,user.curentuser,user.currentcode);
        logout.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                checklogin loginPage = new checklogin("Bug Tracking System ", 400, 260, JFrame.EXIT_ON_CLOSE);
            }
        });
        notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mail not=new Mail(user.curentuser,user.currentcode);
                Inbox.checkInbox(notification,user.curentuser,user.currentcode);
            }
        });

        defineBug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineBugMenu.GetRoot().setVisible(true);
                assignBugMenu.GetRoot().setVisible(false);
                monitorBugsMenu.GetRoot().setVisible(false);

                setSize(new Dimension(width, height));
            }
        });

        assignBug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineBugMenu.GetRoot().setVisible(false);
                assignBugMenu.GetRoot().setVisible(true);
                monitorBugsMenu.GetRoot().setVisible(false);
                setSize(new Dimension(width, height));

                assignBugMenu.update();
            }
        });

        monitorBugs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitorBugsMenu.UpdateTable();

                defineBugMenu.GetRoot().setVisible(false);
                assignBugMenu.GetRoot().setVisible(false);
                monitorBugsMenu.GetRoot().setVisible(true);

                setSize(new Dimension(900, height));
            }
        });

    }
}
