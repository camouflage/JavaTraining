import my.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.awt.Image;


public class ImageReaderTest {
    @Test
    public void testGray1() {
        testTemplate("bmptest/1.bmp", "bmptest/1_gray", "bmptest/goal/1_gray_goal.bmp", "gray");
    }

    @Test
    public void testRed1() {
        testTemplate("bmptest/1.bmp", "bmptest/1_red", "bmptest/goal/1_red_goal.bmp", "red");
    }

    @Test
    public void testGreen1() {
        testTemplate("bmptest/1.bmp", "bmptest/1_green", "bmptest/goal/1_green_goal.bmp", "green");
    }

    @Test
    public void testBlue1() {
        testTemplate("bmptest/1.bmp", "bmptest/1_blue", "bmptest/goal/1_blue_goal.bmp", "blue");
    }

    @Test
    public void testGray2() {
        testTemplate("bmptest/2.bmp", "bmptest/2_gray", "bmptest/goal/2_gray_goal.bmp", "gray");
    }

    @Test
    public void testRed2() {
        testTemplate("bmptest/2.bmp", "bmptest/2_red", "bmptest/goal/2_red_goal.bmp", "red");
    }

    @Test
    public void testGreen2() {
        testTemplate("bmptest/2.bmp", "bmptest/2_green", "bmptest/goal/2_green_goal.bmp", "green");
    }

    @Test
    public void testBlue2() {
        testTemplate("bmptest/2.bmp", "bmptest/2_blue", "bmptest/goal/2_blue_goal.bmp", "blue");
    }

    public void testTemplate(String in, String out, String goal, String type) {
        ImplementImageIO imageioer1 = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        ImplementImageIO imageioer2 = new ImplementImageIO();
        ImplementImageIO imageioer3 = new ImplementImageIO();

        Image img1 = imageioer1.myRead(in);
        Image test = null;
        if ( type == "gray" ) {
            test = processor.showGray(img1);
        } else if ( type == "red" ) {
            test = processor.showChanelR(img1);
        } else if ( type == "green" ) {
            test = processor.showChanelG(img1);
        } else if ( type == "blue" ) {
            test = processor.showChanelB(img1);
        }

        imageioer1.myWrite(test, out);

        imageioer2.myRead(goal);
        imageioer3.myRead(out + ".bmp");

        // width
        assertEquals(imageioer1.getWidth(), imageioer2.getWidth());
        // heigth
        assertEquals(imageioer1.getHeight(), imageioer2.getHeight());
        // RGB
        int[] rgb1 = imageioer2.getRgb();
        int[] rgb2 = imageioer3.getRgb();
        for ( int i = 0; i < rgb1.length; i++ ) {
            assertEquals(rgb1[i], rgb2[i]);
        }
    }
}
