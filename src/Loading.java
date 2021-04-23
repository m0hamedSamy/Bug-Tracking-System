import javax.swing.*;

public class Loading  extends Window{
    private JPanel panel1;
    private JLabel ic;
    private JProgressBar progressBar1;
    private JFrame k;
    private long startTime;


    public Loading(){
        super("",500,600,JFrame.EXIT_ON_CLOSE);
        ContentPanel.add(panel1);
//        setVisible(false);
//        if i want to make bar hidden
//        k=new JFrame();
//        k.add(panel1);
//        k.setSize(500,600);
//        k.setUndecorated(true);
//        k.setVisible(true);
//        k.setLocation(250,200);
//        setVisible(false);
        StyleComponents(panel1);
        startTime=(System.currentTimeMillis()/1000);
        progressBar1.setMaximum(3);

        time();

    }

    public void time(){
        long endTime=startTime;
        while(endTime-startTime<=3){
            endTime=(System.currentTimeMillis()/1000);
            progressBar1.setValue((int)(System.currentTimeMillis()/1000-startTime));
        }
        dispose();
    }
}
