package draylar.jsin;

import draylar.jsin.api.JColor;
import draylar.jsin.api.Pixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Tests for {@link Pixel}.
 */
public class PixelTest {

    @Test
    @DisplayName("A pixel's x and y values retrieved through getters should be the same as the values passed into its constructor.")
    public void testConstructorCoordinates() {
        Pixel pixel = new Pixel(5, 10, JColor.from(Color.CYAN));
        assertEquals(pixel.getX(), 5);
        assertEquals(pixel.getY(), 10);
    }

    @Test
    @DisplayName("Two pixels with the same color and coordinates should be equal.")
    public void testEquality() {
        Pixel pixelOne = new Pixel(1, 1, JColor.from(Color.CYAN));
        Pixel pixelTwo = new Pixel(1, 1, JColor.from(Color.CYAN));
        assertEquals(pixelOne, pixelTwo);
    }
}
