import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignBug extends JFrame {
    private JPanel root;

    private JButton assignBugButton;
    private JComboBox bugs;
    private JComboBox developerEmails;
    ResultSet rs,rs2 ;



    public AssignBug() {

        root.setVisible(false);
        add(root);
        root.setPreferredSize(new Dimension(400,400));

         update();

        assignBugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            DataBase.database.accessDataBase("update bug set developer='"+developerEmails.getSelectedItem()+"' where bugName='"+bugs.getSelectedItem()+"'");
            //DataBase.database.accessDataBase("update bug set developer='"+developerEmails.getSelectedItem()+"' where bugName='"+bugs.getSelectedItem()+"'");
                Inbox.sendMail(user.curentuser, (String) bugs.getSelectedItem());

                bugs.removeItemAt(bugs.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "bug assinged successfully", null, JOptionPane.INFORMATION_MESSAGE);
            if (bugs.getSelectedItem().equals(null))
                JOptionPane.showMessageDialog(null, "there are no bugs to assign", null, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    public  void update(){
         rs= DataBase.database.retriveUserDataBase("users",1);
         rs2= DataBase.database.retriveDataBase("select * from bug where developer IS NULL  and tester='"+user.curentuser+"'");
         developerEmails.removeAllItems();
        bugs.removeAllItems();
        while(true){
            try {
                if (!rs.next()) break;
                developerEmails.addItem(rs.getString("email"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        while(true){
            try {
                if(!rs2.next())break;
                bugs.addItem(rs2.getString("bugName"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public JPanel GetRoot() { return root; }


}

