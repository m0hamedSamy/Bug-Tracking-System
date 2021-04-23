import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefineBugPage extends JFrame{

    private JPanel root;

    private JButton defineBugButton;
    private JComboBox priority;
    private JComboBox buglevel;
    private JFormattedTextField bugname;
    private JFormattedTextField bugtype;
    private JFormattedTextField projectname;
    private JRadioButton bugStatus;
    private JTextField attachScreenshotText;
    private JButton attachScreenshotButton;

    JFileChooser fileChooser = new JFileChooser();


    Bug b=new Bug();

    DefineBugPage() {
        root.setPreferredSize(new Dimension(400, 400));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg","jpeg", "png", "gif", "bmp");

        fileChooser.setFileFilter(filter);
        fileChooser. setAcceptAllFileFilterUsed(false);

        defineBugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check;
                b.setBugName(bugname.getText());
                b.setBugType(bugtype.getText());
                b.setBugStatus("opened");
                b.setProjectName(projectname.getText());
                b.setPriority(Integer.parseInt(priority.getSelectedItem().toString()));
                b.setBugLevel(Integer.parseInt(buglevel.getSelectedItem().toString()));
                b.setTester(user.curentuser);
                if (!bugname.getText().equals("")&&!projectname.getText().equals("")&&!bugtype.getText().equals("")) {
                    check = DataBase.database.insertBug("bug", b);
                    if (check == 1) {
                        //samy section
                            String query = "select rate from users where email='" + user.curentuser + "'";
                            try {
                                ResultSet tester = DataBase.database.retriveDataBase(query);
                                tester.next();
                                int rate = Integer.parseInt(tester.getString("rate"));

                                 DataBase.database.updateDataBase("update users set rate='"+(rate+1)+"' where email='"+user.curentuser+"'");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        //
                        JOptionPane.showMessageDialog(null, "bug inserted successfully", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "bug couldn't be inserted make sure that the bug name is not duplicated!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"make sure you didn't forgot anything empty!","Erorr",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        attachScreenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION){
                    File sourceFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    File destinationFolder = new File(System.getProperty("user.dir") + "\\Screenshots\\" + sourceFile.getName());

                    attachScreenshotText.setText(sourceFile.toString());
                    try {
                        copyFileUsingChannel(sourceFile, destinationFolder);
                        b.setScreenshot(sourceFile.getName());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }


    public JPanel GetRoot() { return root; }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }

}
