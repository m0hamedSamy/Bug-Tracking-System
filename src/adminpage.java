import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class adminpage extends Window {


    private JButton viewallbugButton;
    private JButton insertButton;
    private JButton updateButton;
    private JPanel admin;
    private JButton backButton;


    public adminpage (String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        admin.setPreferredSize(new Dimension(380,460));
        ContentPanel.add(admin);
        StyleComponents(admin);



        viewallbugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                viewallbug defpage= null;
                try {
                    defpage = new viewallbug( "view all bug",1000,500, JFrame.EXIT_ON_CLOSE);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });



        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertpage defPage=new insertpage( "insert page",500,500, JFrame.EXIT_ON_CLOSE);
                defPage.setSize(500,500);
                dispose();



            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                viewallusers defpage= null;
                try {
                    defpage = new viewallusers( "update page",550,580, JFrame.EXIT_ON_CLOSE);
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              checklogin defpage=new checklogin( " Bug Tracking System ",400,260, JFrame.EXIT_ON_CLOSE);
              dispose();

            }
        });
    }


}
