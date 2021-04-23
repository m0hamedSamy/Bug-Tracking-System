
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckDevPerformance extends Window {
    private JTextField performance;
    private JButton checkButton;
    private JButton backButton;
    private JPanel root;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JComboBox devEmail;


    CheckDevPerformance(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        ContentPanel.add(root);
        StyleComponents(root);
        StyleComponents(leftPanel);
        StyleComponents(bottomPanel);
        StyleComponents(rightPanel);

        ArrayList<user> developers = developersList(); // method for returning an array of all developers
//        Boolean found = false; //for checking if this email exists
        for (int i=0 ; i<developers.size() ; i++){

            devEmail.addItem(developers.get(i).getEmail()); // adding each email to the comboBox

        }

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = (String)devEmail.getSelectedItem();


                String query = "select rate from users where email='" + email + "'";
                try {

                    ResultSet developer = DataBase.database.retriveDataBase(query);
                    developer.next();
                    int rate = Integer.parseInt(developer.getString("rate"));

                    performance.setText(rate + "");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProjectManagerPage("Buggy (Project Manager view)", 500, 500, EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    public ArrayList<user> developersList(){
        ArrayList<user> users = new ArrayList<>();
        try{

            String query = "select * from users";
            ResultSet rs = DataBase.database.retriveDataBase(query);
            user newUser;
            while(rs.next()){
                // sending the data from database row into the User class
                newUser = new user(rs.getString("username"), rs.getString("email"),
                        rs.getString("userpassword"), rs.getInt("code"), rs.getInt("rate"));
                // discarding all users except developers
                if(newUser.getCode() == 1) // the code 1 means that this user is a developer
                    users.add(newUser);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
