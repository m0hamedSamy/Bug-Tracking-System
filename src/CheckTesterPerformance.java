
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckTesterPerformance extends Window {
    private JPanel root;
    private JTextField performance;
    private JButton checkButton;
    private JButton backButton;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JComboBox testerEmail;

    CheckTesterPerformance(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        ContentPanel.add(root);
        StyleComponents(root);
        StyleComponents(leftPanel);
        StyleComponents(bottomPanel);
        StyleComponents(rightPanel);

        ArrayList<user> testers = testersList(); // method for returning an array of all testers
//        Boolean found = false; //for checking if this email exists
        for (int i=0 ; i<testers.size() ; i++){
//            if(email.equals(testers.get(i).getEmail())){
//                performance.setText(String.valueOf(testers.get(i).getRate()));
//                found = true;
//                break;
//            }

            testerEmail.addItem(testers.get(i).getEmail()); // adding each email to the comboBox

        }

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = (String) testerEmail.getSelectedItem();
//                if(email.equals("")){ // to make sure the user type an email before clicking the check button
//                    JOptionPane.showMessageDialog(null,
//                            "Please enter an email.", "Error", JOptionPane.ERROR_MESSAGE);
//                    performance.setText(""); // setting back the performance to a blank text field
//                    return;
//                }


//                if(!found){ // if not found you will get an error message
//                    JOptionPane.showMessageDialog(null,
//                            "Invalid email! Maybe this email is not a tester email or it doesn't exist at all.",
//                            "Error", JOptionPane.ERROR_MESSAGE);
//                    performance.setText(""); // setting back the performance to a blank text field
//                }

                String query = "select rate from users where email='" + email + "'";
                try {
                    ResultSet tester = DataBase.database.retriveDataBase(query);
                    tester.next();
                    int rate = Integer.parseInt(tester.getString("rate"));
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

    public ArrayList<user> testersList(){
        ArrayList<user> users = new ArrayList<>();
        try{

            String query = "select * from users";
            ResultSet rs = DataBase.database.retriveDataBase(query);
            user newUser;
            while(rs.next()){
                // sending the data from database row into the User class
                newUser = new user(rs.getString("username"), rs.getString("email"),
                        rs.getString("userpassword"), rs.getInt("code"), rs.getInt("rate"));
                // discarding all users except testers
                if(newUser.getCode() == 0) { // the code 0 means that this user is a tester
                    users.add(newUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
