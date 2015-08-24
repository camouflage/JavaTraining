package my;

import imagereader.*;
import java.awt.image.*;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;


public class ImplementImageProcessor implements IImageProcessor {
    public Image showChanelR(Image sourceImage) {
        Image img = Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(sourceImage.getSource(), new RedFilter()));
        return img;
    }

    public Image showChanelG(Image sourceImage) {
        Image img = Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(sourceImage.getSource(), new GreenFilter()));
        return img;
    }

    public Image showChanelB(Image sourceImage) {
        Image img = Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(sourceImage.getSource(), new BlueFilter()));
        return img;
    }

    public Image showGray(Image sourceImage) {
        Image img = Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(sourceImage.getSource(), new GrayFilter()));
        return img;
    }
} 
