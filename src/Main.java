import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
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

        String pMode = args[2];
        if(pMode.equals("S")){
            ImageProcessor processor = new ImageProcessor(image, square, panel, 0, image.getHeight());
            processor.run();
            frame.dispose();
            ImageIO.write(image, "jpg", new File("src/result.jpg"));
        }
        else if(pMode.equals("M")){
            int maxThreads = Runtime.getRuntime().availableProcessors();
            int numThreads = Math.min((int) Math.ceil(image.getHeight() / square), maxThreads);
            ExecutorService executor = null;
            try {
                executor = Executors.newFixedThreadPool(numThreads);
                int sectionHeight = image.getHeight() / numThreads;
                for (int i = 0; i < numThreads; i++) {
                    int yStart = i * sectionHeight;
                    int yEnd = (i == numThreads - 1) ? image.getHeight() : (i + 1) * sectionHeight;
                    ImageProcessor processor = new ImageProcessor(image, square, panel, yStart, yEnd);
                    executor.submit(processor);
                }
                executor.shutdown();
                try {
                    if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                        ImageIO.write(image, "jpg", new File("src/result.jpg"));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                finally{
                    frame.dispose();
                }
            }
            finally{
                if (executor != null && !executor.isTerminated()) {
                    executor.shutdownNow();
                }
            }
        }
        else{
            System.out.printf("Invalid argument: %s\n", args[2]);
        }
    }
}
