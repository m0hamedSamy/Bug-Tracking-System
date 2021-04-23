import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class checklogin extends Window{
    private JPanel page;
    private JFormattedTextField emailfield;
    private JPasswordField passwordfield;
    private JButton loginbutton;
    private JButton exitButton;

    public checklogin(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);
        page.setPreferredSize(new Dimension(330,240));
        user u=new user();
         ContentPanel.add(page);
         StyleComponents(page);

        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                u.setEmail(emailfield.getText());
                u.setPassword(passwordfield.getText());
                user.setCurentuser(emailfield.getText());
                int code=-1;

                ResultSet found = DataBase.database.check("users",u);

               while (true) {
                   try {
                       if (!found.next()) break;
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
                   try {
                       code = found.getInt("code");
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               }
                user.setCurentCode(code);
                if ( code== 0) { JOptionPane.showMessageDialog(null,"login successfully" ,"success",JOptionPane.INFORMATION_MESSAGE );
                                        /*tester page **/

                    TesterWindow testerModule=new TesterWindow(user.curentuser,500,500,JFrame.EXIT_ON_CLOSE);
                    dispose();

                }
                else if ( code==1 ) { JOptionPane.showMessageDialog(null,"login successfully" ,"success",JOptionPane.INFORMATION_MESSAGE );
                                       /* developer page**/
                        Developer developer=new Developer();
                        dispose();



                }
                else  if ( code==2 ){ JOptionPane.showMessageDialog(null,"login successfully" ,"success",JOptionPane.INFORMATION_MESSAGE );

                                       /* manager page**/

                    new ProjectManagerPage(user.curentuser, 500, 500, EXIT_ON_CLOSE);
                    dispose();

                }
                 else  if ( code==3)  { JOptionPane.showMessageDialog(null,"login successfully" ,"success",JOptionPane.INFORMATION_MESSAGE );
                                       /* admin page**/

                    adminpage adminpage=new adminpage( user.curentuser,450,500, JFrame.EXIT_ON_CLOSE);
                    dispose();

                 }

                 else { JOptionPane.showMessageDialog(null,"invaild email or password" ,"Error",JOptionPane.ERROR_MESSAGE ); } }

                });


                 exitButton.addActionListener(new ActionListener() {
                 @Override
                  public void actionPerformed(ActionEvent e) {
                          System.exit(0);

                  }
                 });     }

}


