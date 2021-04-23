import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inbox {

    public Inbox() {

    }
    private static int checkNotification(String tableName, String coulmnName, String name, int code) {
        Statement st = null;
        int x=0;
        ResultSet r;
        try {
            st=DataBase.database.c.createStatement();
            r = st.executeQuery("select count(*) from " + tableName + " where " + coulmnName + "='"+ name +"' and notif="+code);

            while (r.next()){
                x = r.getInt(1);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return x;

    }
    public static void checkInbox(JButton btn, String userName, int code) {
        int x = checkNotification("bug",check(user.currentcode) , userName, code);
        if (x > 0) {

            btn.setIcon(new ImageIcon("src/notification.png"));
        } else {
            btn.setIcon(new ImageIcon("src/bell.png"));

        }
    }
    private static String check(int code){
        if(code==1){
            return "developer";
        }
        else{
            return "tester";
        }
    }
    private static int whoSender(){
        return (user.currentcode==1)?0:1;
}
    public static void viewMails(DefaultTableModel dtm) {
        String msg;
        String sender;
        dtm.addColumn("sender");

        dtm.addColumn("mail");
        dtm.addColumn("bug name");
        dtm.addColumn("receiver");

        if (user.currentcode == 1) {
            msg = "assigned new bug ";

        } else {
            msg = "completed the bug";
        }
        try {

            ResultSet r = DataBase.database.retriveDataBase("select * from bug where " + check(user.currentcode) + " ='" + user.curentuser + "' and notif ='" + user.currentcode + "'");
            while (r.next()) {
                dtm.addRow(new Object[]{r.getString(check(whoSender())),
                        msg,r.getString("bugName"),
                        user.curentuser
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void sendMail( String username, String bName) {


        DataBase.database.updateDataBase("update bug set notif =" + whoSender() + " where " +check(user.currentcode)+ "='" + username + "' and bugName ='" + bName + "'");
    }
    public static void delete(DefaultTableModel dtm){
        for(int i=0;i<dtm.getRowCount();i++){
            DataBase.database.updateDataBase("update bug set notif =null where  bugName ='" + dtm.getValueAt(i,2) + "'");

        }
        dtm.setRowCount(0);

    }
}

