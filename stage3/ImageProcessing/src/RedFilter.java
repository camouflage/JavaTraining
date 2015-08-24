package my;

import java.awt.image.*;

public class RedFilter extends RGBImageFilter {
    public RedFilter() {
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        return rgb & 0xffff0000;
    }
}
