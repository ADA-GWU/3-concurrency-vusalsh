import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 3){
            System.out.println("Usage: <filename.jpg> <square_size> <S|M>");
            return;
        }
        File file = new File(Objects.requireNonNull(args[0]));
        BufferedImage image = ImageIO.read(file);
        int square = Integer.parseInt(args[1]);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImagePanel panel = new ImagePanel(image);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
