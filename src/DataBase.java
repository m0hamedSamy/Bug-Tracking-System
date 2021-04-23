import javax.swing.*;
import java.sql.*;

public class DataBase {

    Connection c;

    DataBase() {
        c = connectDataBase();
    }

    public static Connection connectDataBase() {
        String connectionUrl =
                "jdbc:sqlserver://DESKTOP-K4HVRV8\\mohamed.database.windows.net:1433;"
                        + "database=BugSystem;"
                        + "user=mohamed;"
                        + "password=Mohamed123;"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("connected");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DataBase database = new DataBase();


    public int insertBug(String tableName, Bug b) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate("insert into " + tableName + "(bugName,bugType,bugPriority,bugLevel,projectName,bugDate,bugStatus,screenShot,tester) values ('" + b.getBugName() + "','" + b.getBugType() + "','" + b.getPriority() + "','" + b.getBugLevel() + "','" + b.getProjectName() + "','" + b.getBugDate() + "','" + b.getBugStatus() + "','" + b.getScreenshot() + "','"+b.getTester()+"') ");
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }


    public ResultSet retriveDataBase(String query) {

        Statement st = null;
        try {
            st = c.createStatement();
            return st.executeQuery(query);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void accessDataBase(String query) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public ResultSet retriveUserDataBase(String tableName, int code) {

        Statement st = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("select * from " + tableName + " where code='" + code + "' ");
            return r;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //  mohamed ashraf section ---------------------------------------------------------------------------------------------------------
    public void updateDataBase(String query) {

        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //----------------------------------------------------------
    //  mohamed khaled mostafa section ---------------------------------------------------------------------------------------------------------
    public void insertUsers(String tableName, user u) {
        Statement st = null;
        try {
              st = c.createStatement();
             int result ;
             if ((u.getUsertype()==0||u.getUsertype()==1)&&u.getEmail()!=null){
             result = st.executeUpdate("insert into " + tableName + "(username,email,userpassword ,code,rate) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getUsertype() + "','"+u.getRate()+"') ");}
             else{ result = st.executeUpdate("insert into " + tableName + "(username,email,userpassword ,code) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getUsertype() + "') ");}

             if (result == 1) {
                JOptionPane.showMessageDialog(null, " successfully process", "success", JOptionPane.INFORMATION_MESSAGE); }

             } catch (SQLException throwables) {
                  throwables.printStackTrace();

              JOptionPane.showMessageDialog(null, "please enter a valid email adress ", "Error", JOptionPane.ERROR_MESSAGE);

             }

    }


    public void updateDataBase(String tableName, user u) {

        Statement st = null;
        try {
            st = c.createStatement();
            int result ;
            if (u.getUsertype()==0||u.getUsertype()==1){
                result = st.executeUpdate("update " + tableName +" set username='"+u.getUsername()+"', userpassword='"+u.getPassword()+"', code='"+u.getUsertype()+"',rate='"+u.getRate()+"' where email='" + u.getEmail() + "'");}
            else{ result = st.executeUpdate("update " + tableName +" set username='"+u.getUsername()+"', userpassword='"+u.getPassword()+"', code='"+u.getUsertype()+"'  where email='" + u.getEmail() + "'");}
            if (result == 1) {
                JOptionPane.showMessageDialog(null, " record has been update successfully ", "success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }


    public ResultSet check(String tableName, user u) {

        Statement st = null;
        try {

            st = c.createStatement();
            String sql = "select code from " + tableName + " where email ='" + u.getEmail() + "' and userpassword='" + u.getPassword() + "'";
            ResultSet r = st.executeQuery(sql);

            return r;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public void DeleteFromDB(String tableName, user u) {
        Statement st = null;
        try {
            st = c.createStatement();

            int result = st.executeUpdate("delete from " + tableName + " where email='" + u.getEmail() + "'");
            if (result == 1) {
                JOptionPane.showMessageDialog(null, " record has been deleted successfully ", "success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "error ", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }


    public ResultSet retriveUser(String tableName) {

        Statement st = null;
        try {
            st = c.createStatement();

            ResultSet r = st.executeQuery("select * from " + tableName + "");
            return r;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public ResultSet retriveDataBasewhere(String tableName, String email) {

        Statement st = null;
        try {
            st = c.createStatement();

            ResultSet rt = st.executeQuery("select * from " + tableName + " where email='" + email + "'");
            return rt;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }



}
