package my;

import java.awt.image.*;

public class BlueFilter extends RGBImageFilter {
    public BlueFilter() {
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        return rgb & 0xff0000ff;
    }
}
