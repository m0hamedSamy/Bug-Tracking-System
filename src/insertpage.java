import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insertpage extends Window {

    private JButton insertusers;
    private JComboBox usertype;
    private JFormattedTextField emailfield;
    private JPanel rootPanel;
    private JPasswordField passwordField1;
    private JTextField usernamefield;
    private JButton backButton;


    public insertpage(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        rootPanel.setPreferredSize(new Dimension(480,480));
        ContentPanel.add(rootPanel);
        StyleComponents(rootPanel);
        insertusers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user u=new user(usernamefield.getText(),emailfield.getText(),passwordField1.getText(),Integer.parseInt(String.valueOf(usertype.getSelectedIndex())));

                if (!emailfield.getText().trim().isEmpty() && !usernamefield.getText().trim().isEmpty()&& !passwordField1.getText().trim().isEmpty() ){
                    DataBase.database.insertUsers("users",u);}
                else { JOptionPane.showMessageDialog(null,"please enter all required information" ,"Error",JOptionPane.ERROR_MESSAGE ); }



                }

        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminpage defpage=new adminpage( "admin page",450,500, JFrame.EXIT_ON_CLOSE);

               /* defpage.setVisible(true);**/
                dispose();



            }
        });
    }


}
