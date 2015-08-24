package my;

import java.awt.image.*;

public class GreenFilter extends RGBImageFilter {
    public GreenFilter() {
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        return rgb & 0xff00ff00;
    }
}
