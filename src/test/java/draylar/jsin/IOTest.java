package draylar.jsin;

import draylar.jsin.api.JColor;
import draylar.jsin.api.JSIN;
import draylar.jsin.api.JSINImage;
import draylar.jsin.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class IOTest extends DirectoryCreatingTest {

    private static final String TEST_IMAGE_URL = "valiphotos_fall_photograph.png";
    private static final Random RANDOM = new Random();

    public IOTest() {
        super("Generic");
    }

    @Test
    @DisplayName("BufferedImage and a JSINImage constructed from it should have the same content.")
    public void testConversion() {
        BufferedImage pngImage = FileUtils.readImage(TEST_IMAGE_URL);
        JSINImage jsinImage = new JSINImage(pngImage);

        for(int i = 0; i < 10; i++) {
            int randX = RANDOM.nextInt(pngImage.getWidth() - 1);
            int randY = RANDOM.nextInt(pngImage.getHeight() - 1);

            // Retrieve random pixel from png image and check if it matches up with the new JSIN object
            Color rgb = new Color(pngImage.getRGB(randX, randY));
            JColor jRgb = new JColor(rgb.getRed() / 255f, rgb.getGreen() / 255f, rgb.getBlue() / 255f, rgb.getAlpha() / 255f);
            assertEquals(jRgb, jsinImage.getPixel(randX, randY).getColor());
        }
    }

    @Test
    @DisplayName("A saved and re-read JSINImage should have the same contents as the original image.")
    public void testSavingJSIN() throws IOException {
        JSIN jsin = new JSIN();
        BufferedImage pngImage = FileUtils.readImage(TEST_IMAGE_URL);
        JSINImage image = new JSINImage(pngImage);

        // Save image
        jsin.save(image, OUTPUT_DIRECTORY, "conversion_save");

        // Make sure the file exists
        File file = new File(OUTPUT_DIRECTORY + "\\" + "conversion_save.jsin");
        assertTrue(file.exists());

        // Read the file and ensure the contents are the same
        JSINImage readImage = jsin.from(Files.readString(file.toPath()));
        assertEquals(image, readImage);
    }

    @Test
    @DisplayName("A .png created from a JSINImage should have the same contents when re-read.")
    public void testSavingPNG() throws IOException {
        // Create simple JSIN image and save it
        JSIN jsin = new JSIN();
        JSINImage image = new JSINImage(1, 1);
        image.setPixel(0, 0, JColor.from(Color.BLUE));
        jsin.save(image, OUTPUT_DIRECTORY, "topng");

        // Re-load image
        JSINImage readImage = jsin.from(Files.readString(Paths.get(OUTPUT_DIRECTORY + "\\" + "topng.jsin")));
        BufferedImage bufferedImage = readImage.toBuffered();

        // Save as PNG
        File pngFile = Paths.get(OUTPUT_DIRECTORY + "\\" + "topng.png").toFile();
        ImageIO.write(bufferedImage, "png", pngFile);

        // Make sure the new png file exists
        assertTrue(pngFile.exists());

        // Read the file and ensure the contents are the same
        BufferedImage readPng = ImageIO.read(pngFile);
        assertEquals(image, new JSINImage(readPng));
    }

    @Test
    @DisplayName("A saved and re-read JSINImage should be identical to the original.")
    public void testSaving() throws IOException {
        JSIN jsin = new JSIN();
        JSINImage image = new JSINImage(1, 1);
        image.setPixel(0, 0, JColor.from(Color.BLUE));

        // Save image
        jsin.save(image, OUTPUT_DIRECTORY, "save");

        // Make sure the file exists
        File file = new File(OUTPUT_DIRECTORY + "\\" + "save.jsin");
        assertTrue(file.exists());

        // Read the file and ensure the contents are the same
        JSINImage readImage = jsin.from(Files.readString(file.toPath()));
        assertEquals(image, readImage);
    }
}
