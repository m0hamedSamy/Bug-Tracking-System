
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ShowOpenBugs extends Window{
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table;

    private JScrollPane scrollPane;
    private JButton backButton;
    private JPanel bottomPanel;
    private JPanel root;
    private JPanel topPanel;


    ShowOpenBugs(String windowTitle, int width, int height, int defaultCloseOperation) {
        super(windowTitle, width, height, defaultCloseOperation);

        tableModel.addColumn("Bug name");
        tableModel.addColumn("Bug type");
        tableModel.addColumn("Bug priority");
        tableModel.addColumn("Bug level");
        tableModel.addColumn("Project name");
        tableModel.addColumn("Bug date");
        tableModel.addColumn("Developer");
        tableModel.addColumn("Tester");

        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 250));


        /** styles */
//        ContentPanel.add(root);
//        StyleComponents(root);
//        StyleComponents(bottomPanel);

        ContentPanel.add(root);
        StyleComponents(root);
        StyleComponents(topPanel);
        StyleComponents(bottomPanel);
        /***************************/


        showBugs();


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProjectManagerPage("Buggy (Project Manager view)", 500, 500, EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    public ArrayList<Bug> bugList(){
        ArrayList<Bug> bugs = new ArrayList<Bug>();
        try{

            String query = "select * from bug";
            ResultSet rs = DataBase.database.retriveDataBase(query);
            Bug bug;
            while(rs.next()){
                // sending the data from database row into the Bug class
                bug = new Bug(rs.getString("bugStatus"), rs.getString("bugName"),
                        rs.getString("bugType"), rs.getInt("bugPriority"),
                        rs.getInt("bugLevel"), rs.getString("projectName"),
                        rs.getString("bugDate"), rs.getString("developer"),
                        rs.getString("tester"));
                // discarding the closed bugs
                if(bug.getBugStatus().equals("closed"))
                    continue;
                bugs.add(bug);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bugs;
    }


    public void showBugs(){
        ArrayList<Bug> bugs = bugList();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        Object[] row = new Object[8];
        for (int i=0 ; i<bugs.size() ; i++){
            row[0] = bugs.get(i).getBugName();
            row[1] = bugs.get(i).getBugType();
            row[2] = bugs.get(i).getPriority();
            row[3] = bugs.get(i).getBugLevel();
            row[4] = bugs.get(i).getProjectName();
            row[5] = bugs.get(i).getDisplayableBugDate();
            row[6] = bugs.get(i).getDeveloper();
            row[7] = bugs.get(i).getTester();
            model.addRow(row);
        }
        if (bugs.size() == 0){ // checking if there is no open bugs
            JOptionPane.showMessageDialog(null, "There is no open bugs.");
        }
    }


}
