package my;

import java.awt.image.*;

public class GrayFilter extends RGBImageFilter {
    public GrayFilter() {
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        double red = (double) ( (rgb & 0x00ff0000) >> 16 );
        double green = (double) ( (rgb & 0x0000ff00) >> 8 );
        double blue = (double) ( (rgb & 0x000000ff) );
        int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        return (rgb & 0xff000000) | (gray << 16) | (gray << 8) | gray;
    }
}
