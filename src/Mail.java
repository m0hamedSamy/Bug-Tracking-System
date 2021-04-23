import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mail extends Window{
    private JPanel root;
    private JTable table1;
    private JButton deleteAllButton;
    DefaultTableModel dtm;

    public Mail(String userName, int code) {

        super("Inbox",850,500,JFrame.DISPOSE_ON_CLOSE);
        ContentPanel.add(root);
        StyleComponents(root);
        dtm=new DefaultTableModel();
        table1.setModel(dtm);
        Inbox.viewMails(dtm);
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inbox.delete(dtm);

            }
        });
    }


public JPanel getRoot(){
        return root;
}


}
