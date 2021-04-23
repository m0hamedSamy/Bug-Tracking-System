
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagerPage extends Window {
    private JPanel root;
    private JButton checkDev;
    private JButton checkTester;
    private JButton showOpen;
    private JButton showClosed;
    private JLabel welcomeText;
    private JButton logoutButton;
    private user projectManager;


    ProjectManagerPage(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        ContentPanel.add(root);
        StyleComponents(root);

        checkDev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckDevPerformance("Buggy (Project Manager view)", 400, 300, EXIT_ON_CLOSE);
                dispose();
            }
        });
        checkTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckTesterPerformance("Buggy (Project Manager view)", 400, 300, EXIT_ON_CLOSE);
                dispose();
            }
        });
        showOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowOpenBugs("Buggy (Project Manager view)", 900, 400, EXIT_ON_CLOSE);
                dispose();
            }
        });
        showClosed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowClosedBugs("Buggy (Project Manager view)", 900, 400, EXIT_ON_CLOSE);
                dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new checklogin("Bug Tracking System ", 400, 260, JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }
}
