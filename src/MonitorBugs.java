import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonitorBugs extends JFrame {
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table;

    private JPanel root = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(table);



    MonitorBugs(){
        root.setVisible(false);

        tableModel.addColumn("Name");
        tableModel.addColumn("Type");
        tableModel.addColumn("Statues");
        tableModel.addColumn("Priority");
        tableModel.addColumn("Level");
        tableModel.addColumn("Project name");
        tableModel.addColumn("Date");
        tableModel.addColumn("Screenshot");
        tableModel.addColumn("Developer");
        tableModel.addColumn("Tester");

        table.setModel(tableModel);
        table.getTableHeader().setBackground(new Color(0x2C2F33));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        //table.setPreferredScrollableViewportSize(new Dimension(750, 370));

        table.setDefaultEditor(Object.class, null);

        UpdateTable();

        root.add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();

                if (col == 7){
                    try {
                        String imageName = tableModel.getValueAt(row, col).toString();

                        BufferedImage bufferedImage = null;
                        try {
                            bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\" + imageName));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        int width = bufferedImage.getWidth();
                        int height = bufferedImage.getHeight();

                        PreviewImage previewImage = new PreviewImage(imageName, width, height + 30, JFrame.DO_NOTHING_ON_CLOSE, bufferedImage);
                    }
                    catch (NullPointerException ex) { }
                }
            }
        });
    }

    public JPanel GetRoot(){
        return root;
    }

    public void UpdateTable(){
        ResultSet rs = DataBase.database.retriveDataBase("select * from bug");

        tableModel.setRowCount(0);

        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                tableModel.addRow(new Object[] {
                        rs.getString("bugName"),
                        rs.getString("bugType"),
                        rs.getString("bugStatus"),
                        rs.getString("bugPriority"),
                        rs.getString("bugLevel"),
                        rs.getString("projectName"),
                        rs.getString("bugDate"),
                        rs.getString("screenShot"),
                        rs.getString("developer"),
                        rs.getString("tester")});
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
