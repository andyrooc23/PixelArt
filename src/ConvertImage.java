package src;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ConvertImage {
    public ConvertImage() {
    }

    public static void main(String[] args) {
        File folder = new File("/Users/andyr/IdeaProjects/PixelArt/src/InputImages");
        File[] listOfFiles = folder.listFiles();

        for(File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    BufferedImage img = ImageIO.read(file);
                    Color[][] arr = PopulateArr(img);
                    drawImage(arr, file.getName());
                } catch (Exception e) {
                    System.out.println("Failed lol");
                    e.printStackTrace();
                }
            }
        }

    }

    public static Color[][] PopulateArr(BufferedImage img) {
        int width = img.getWidth() / 10 - 1;
        int height = img.getHeight() / 10 - 1;
        Color[][] arr = new Color[width][height];

        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                arr[i][j] = new Color(img.getRGB(i * 10 + 5, j * 10 + 5));
            }
        }

        return arr;
    }

    public static void drawImage(Color[][] arr, String name) {
        int height = arr[0].length;
        int width = arr.length;
        BufferedImage bufferImage2 = new BufferedImage(width, height, 1);

        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                bufferImage2.setRGB(x, y, arr[x][y].getRGB());
            }
        }

        File outputfile = new File("/Users/andyr/IdeaProjects/PixelArt/src/OutputImages/" + name);

        try {
            ImageIO.write(bufferImage2, "jpg", outputfile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}