package draylar.jsin.api;

import java.awt.*;

/**
 * Represents a color value with channels ranging from 0.0 - 1.0.
 */
public class JColor {

    private final float r;
    private final float g;
    private final float b;
    private final float a;

    public JColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public static JColor from(int rgba) {
        return from(new Color(rgba));
    }

    public static JColor from(Color color) {
        return new JColor(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }

    public Color toAWT() {
        return new Color(r, g, b, a);
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JColor pixel = (JColor) o;
        return Float.compare(pixel.r, r) == 0 &&
                Float.compare(pixel.g, g) == 0 &&
                Float.compare(pixel.b, b) == 0 &&
                Float.compare(pixel.a, a) == 0;
    }
}
