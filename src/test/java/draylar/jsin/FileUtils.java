package draylar.jsin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    /**
     * Reads the image with the given name under the resources directory.
     *
     * @param name  name of image to read
     * @return      read image as a {@link BufferedImage}
     * @throws      NullPointerException if the file is not valid
     */
    public static BufferedImage readImage(String name) {
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(name);
        BufferedImage img = null;

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private FileUtils() {

    }
}
