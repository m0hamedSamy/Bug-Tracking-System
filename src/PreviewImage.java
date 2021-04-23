import javax.swing.*;
import java.awt.image.BufferedImage;

public class PreviewImage extends Window{
    PreviewImage(String windowTitle, int width, int height, int defaultCloseOperation, BufferedImage imageName) {
        super(windowTitle, width, height, defaultCloseOperation);

        ImageIcon imageIcon = new ImageIcon(imageName);
        JLabel imageLabel = new JLabel();

        imageLabel.setIcon(imageIcon);

        ContentPanel.add(imageLabel);

        StyleComponents(ContentPanel);
    }
}
