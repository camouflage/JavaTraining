import my.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.awt.Image;


public class ImageReaderTest {
    @Test
    public void testGray() {
        ImplementImageIO imageioer1 = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        ImplementImageIO imageioer2 = new ImplementImageIO();
        ImplementImageIO imageioer3 = new ImplementImageIO();

        Image img1 = imageioer1.myRead("bmptest/1.bmp");
        Image test = processor.showGray(img1);
        imageioer1.myWrite(test, "bmptest/1_gray");

        imageioer2.myRead("bmptest/goal/1_gray_goal.bmp");
        imageioer3.myRead("bmptest/1_gray.bmp");

        assertEquals(imageioer1.getWidth(), imageioer2.getWidth());
        assertEquals(imageioer1.getHeight(), imageioer2.getHeight());
        int[] rgb1 = imageioer2.getRgb();
        int[] rgb2 = imageioer3.getRgb();
        for ( int i = 0; i < rgb1.length; i++ ) {
            assertEquals(rgb1[i], rgb2[i]);
        }
    }

    @Test
    public void testRed() {
        ImplementImageIO imageioer1 = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        ImplementImageIO imageioer2 = new ImplementImageIO();
        ImplementImageIO imageioer3 = new ImplementImageIO();

        Image img1 = imageioer1.myRead("bmptest/1.bmp");
        Image test = processor.showChanelR(img1);
        imageioer1.myWrite(test, "bmptest/1_red");

        imageioer2.myRead("bmptest/goal/1_red_goal.bmp");
        imageioer3.myRead("bmptest/1_red.bmp");

        assertEquals(imageioer1.getWidth(), imageioer2.getWidth());
        assertEquals(imageioer1.getHeight(), imageioer2.getHeight());
        int[] rgb1 = imageioer2.getRgb();
        int[] rgb2 = imageioer3.getRgb();
        for ( int i = 0; i < rgb1.length; i++ ) {
            assertEquals(rgb1[i], rgb2[i]);
        }
    }

    @Test
    public void testGreen() {
        ImplementImageIO imageioer1 = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        ImplementImageIO imageioer2 = new ImplementImageIO();
        ImplementImageIO imageioer3 = new ImplementImageIO();

        Image img1 = imageioer1.myRead("bmptest/1.bmp");
        Image test = processor.showChanelG(img1);
        imageioer1.myWrite(test, "bmptest/1_green");

        imageioer2.myRead("bmptest/goal/1_green_goal.bmp");
        imageioer3.myRead("bmptest/1_green.bmp");

        assertEquals(imageioer1.getWidth(), imageioer2.getWidth());
        assertEquals(imageioer1.getHeight(), imageioer2.getHeight());
        int[] rgb1 = imageioer2.getRgb();
        int[] rgb2 = imageioer3.getRgb();
        for ( int i = 0; i < rgb1.length; i++ ) {
            assertEquals(rgb1[i], rgb2[i]);
        }
    }

    @Test
    public void testBlue() {
        ImplementImageIO imageioer1 = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        ImplementImageIO imageioer2 = new ImplementImageIO();
        ImplementImageIO imageioer3 = new ImplementImageIO();

        Image img1 = imageioer1.myRead("bmptest/1.bmp");
        Image test = processor.showChanelB(img1);
        imageioer1.myWrite(test, "bmptest/1_blue");

        imageioer2.myRead("bmptest/goal/1_blue_goal.bmp");
        imageioer3.myRead("bmptest/1_blue.bmp");

        assertEquals(imageioer1.getWidth(), imageioer2.getWidth());
        assertEquals(imageioer1.getHeight(), imageioer2.getHeight());
        int[] rgb1 = imageioer2.getRgb();
        int[] rgb2 = imageioer3.getRgb();
        for ( int i = 0; i < rgb1.length; i++ ) {
            assertEquals(rgb1[i], rgb2[i]);
        }
    }
}