import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private final BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(image.getWidth() > screenSize.getWidth() || image.getHeight() > screenSize.getHeight()) {
            g.drawImage(ImageProcessor.resizeImage(image, (int)screenSize.getWidth(), (int)screenSize.getHeight()), 0, 0, null);
        }
        else{
            g.drawImage(image,0, 0, null);
        }
    }
}