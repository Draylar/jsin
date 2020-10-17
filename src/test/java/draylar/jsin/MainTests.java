package draylar.jsin;

import draylar.jsin.api.DirectoryCreatingTest;
import draylar.jsin.api.JColor;
import draylar.jsin.api.JSINImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTests extends DirectoryCreatingTest {

    public MainTests() {
        super("Main");
    }

    @Test
    @DisplayName("Main#to() should only run if the correct number of arguments (4) are supplied.")
    public void toArgumentCount() throws IOException {
        // Create test image file for fourth assertion to pass
        JSINImage jImage = new JSINImage(1, 1);
        jImage.setPixel(0, 0, JColor.from(Color.BLUE));
        jsin.save(jImage, OUTPUT_DIRECTORY, "to_argument");

        // Test arguments
        assertFalse(Main.to(new String[] { "jsin", "to", "png" }));
        assertFalse(Main.to(new String[] { "jsin", "to" }));
        assertFalse(Main.to(new String[] { "jsin", "to", "png", "to_argument", "hello" }));
        assertTrue(Main.to(new String[] { "jsin", "to", "png", "to_argument" }));
    }
}
