package draylar.jsin;

import draylar.jsin.api.DirectoryCreatingTest;
import draylar.jsin.api.JColor;
import draylar.jsin.api.JSINImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest extends DirectoryCreatingTest {

    public MainTest() {
        super("Main");
    }

    @Test
    @DisplayName("Main#toOther() should fail if the incorrect number of arguments are supplied.")
    public void toOtherArgumentCount() throws IOException {
        String fileLocation = String.format("%s\\%s", OUTPUT_DIRECTORY, "other_argument");

        // Create test .jsin image file for true assertion to pass
        JSINImage jImage = new JSINImage(1, 1);
        jImage.setPixel(0, 0, JColor.from(Color.BLUE));
        jsin.save(jImage, OUTPUT_DIRECTORY, "other_argument");

        // Test arguments
        Assertions.assertFalse(Main.toOther(new String[] { "to", "png" }, false));
        assertFalse(Main.toOther(new String[] { "to" }, false));
        assertFalse(Main.toOther(new String[] { "to", "png", fileLocation, "hello" }, false));
        assertTrue(Main.toOther(new String[] { "to", "png", fileLocation }, false));
    }

    @Test
    @DisplayName("Main#toOther() should fail if an invalid file was passed in.")
    public void toOtherFileValidity() throws IOException {
        String fileLocation = String.format("%s\\%s", OUTPUT_DIRECTORY, "other_file");

        // Create test .jsin image file for true assertion to pass
        JSINImage jImage = new JSINImage(1, 1);
        jImage.setPixel(0, 0, JColor.from(Color.BLUE));
        jsin.save(jImage, OUTPUT_DIRECTORY, "other_file");

        // Test arguments
        assertTrue(Main.toOther(new String[] { "to", "png", fileLocation }, false));
        assertFalse(Main.toOther(new String[] { "to", "png", fileLocation + ".yeet" }, false));
        assertFalse(Main.toOther(new String[] { "to", "png", fileLocation + "\\test.jsin" }, false));
        assertFalse(Main.toOther(new String[] { "to", "png", fileLocation + "\\" }, false));
    }

    @Test
    @DisplayName("Main#toJsin() should fail if the incorrect number of arguments are supplied.")
    public void toJsinArgumentCount() throws IOException {
        String fileLocation = String.format("%s\\%s", OUTPUT_DIRECTORY, "jsin_argument.png");

        // Create test .png image file for true assertion to pass
        BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        ImageIO.write(bufferedImage, "png", new File(fileLocation));

        // Test arguments
        assertFalse(Main.toJsin(new String[] { "from" }, false));
        assertFalse(Main.toJsin(new String[] { }, false));
        assertFalse(Main.toJsin(new String[] { "from", "png", fileLocation, "hello" }, false));
        assertTrue(Main.toJsin(new String[] { "from", fileLocation }, false));
    }

    @Test
    @DisplayName("Main#toJsin() should fail if an invalid file was passed in.")
    public void toJsinFileValidity() throws IOException {
        String fileLocation = String.format("%s\\%s", OUTPUT_DIRECTORY, "jsin_file.png");

        // Create test .png image file for true assertion to pass
        BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        ImageIO.write(bufferedImage, "png", new File(fileLocation));

        // Test arguments
        assertTrue(Main.toJsin(new String[] { "from", fileLocation }, false));
        assertFalse(Main.toJsin(new String[] { "from", fileLocation + ".yeet" }, false));
        assertFalse(Main.toJsin(new String[] { "from", fileLocation + "\\test.jsin" }, false));
    }
}
