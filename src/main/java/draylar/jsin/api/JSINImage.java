package draylar.jsin.api;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class JSINImage {

    private final int width;
    private final int height;
    private final Pixel[][] pixels;

    /**
     * Primary constructor for {@link JSINImage}. Takes in width and height parameters that bound the image size.
     *
     * <p>Width and height values are 0-indexed, and represent the total number of pixels on one axis.
     * An image with 25 pixels, 5 long on the X axis, and 5 tall on the Y axis, would have a width and height of 4.
     *
     * @param width   width of this {@link JSINImage}
     * @param height  height of this {@link JSINImage}
     */
    public JSINImage(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Pixel[width][height];
    }

    public JSINImage(BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new Pixel[image.getWidth()][image.getHeight()];

        // Populate pixel array with BufferedImage data
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pixels[x][y] = new Pixel(x, y, JColor.from(image.getRGB(x, y)));
            }
        }
    }

    public BufferedImage toBuffered() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Populate BufferedImage with pixel array data
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, pixels[x][y].getColor().toAWT().getRGB());
            }
        }

        return image;
    }

    /**
     * Sets the pixel at the given coordinate in this {@link JSINImage}.
     *
     * <p>The coordinates in this method are 0-indexed. (0, 0) represents the top-left pixel.
     *
     * @param x      x value of position
     * @param y      y value of position
     * @param color  color to set position to
     */
    public void setPixel(int x, int y, JColor color) {
        pixels[x][y] = new Pixel(x, y, color);
    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSINImage image = (JSINImage) o;
        return Arrays.deepEquals(pixels, image.pixels);
    }
}
