import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage output =  new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        output.getGraphics().drawImage(scaledImage, 0, 0, null);
        return output;
    }
}
