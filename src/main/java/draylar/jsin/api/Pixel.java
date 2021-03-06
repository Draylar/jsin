package draylar.jsin.api;

import java.util.Objects;

/**
 * Represents a pixel in any image with a position and color.
 */
public class Pixel {

    private final int x;
    private final int y;
    private final JColor color;

    /**
     * Primary constructor of {@link Pixel}.
     *
     * @param x      y-coordinate of this pixel
     * @param y      x-coordinate of this pixel
     * @param color  color of this pixel
     */
    public Pixel(int x, int y, JColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public JColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x &&
                y == pixel.y &&
                Objects.equals(color, pixel.color);
    }
}
