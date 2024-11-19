import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    private final BufferedImage image;

    public ImageProcessor(BufferedImage image, int square, ImagePanel panel, int yStart, int yEnd) {
        this.image = image;
    }


    public int getAverageColor(int xStart, int xEnd, int yStart, int yEnd) {
        double red = 0;
        double green = 0;
        double blue = 0;
        int count = (xEnd - xStart) * (yEnd - yStart);
        for (int i = xStart; i < xEnd; i++) {
            for (int j = yStart; j < yEnd; j++) {
                Color color = new Color(image.getRGB(i, j));
                red += Math.pow(color.getRed(), 2);
                green += Math.pow(color.getGreen(), 2);
                blue += Math.pow(color.getBlue(), 2);
            }
        }
        return new Color(
                (float) Math.sqrt(red / count) / 255,
                (float) Math.sqrt(green / count) / 255,
                (float) Math.sqrt(blue / count) / 255
        ).getRGB();
    }

    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage output =  new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        output.getGraphics().drawImage(scaledImage, 0, 0, null);
        return output;
    }
}
