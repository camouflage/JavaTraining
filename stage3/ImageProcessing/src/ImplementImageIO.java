package my;

import java.io.*;
import java.awt.image.*;
import java.awt.Image;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import imagereader.*;

public class ImplementImageIO implements IImageIO {
    private int[] rgbInt;
    private int width;
    private int height;

    public Image myRead(String filePath) {
        Image img = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

            // skip the first 18bytes.
            fis.skip(18);

            byte[] buffer4 = new byte[4];
            byte[] buffer2 = new byte[2];

            // read width #18-21 
            fis.read(buffer4);
            width = toInt(buffer4, 4);

            // read height #22-25 
            fis.read(buffer4);
            height = toInt(buffer4, 4);

            fis.skip(2);

            // read #28-29
            fis.read(buffer2);
            int pixelBit = toInt(buffer2, 2);

            // read #30-33
            fis.read(buffer4);
            int compression = toInt(buffer4, 4);

            // read #34-37
            fis.read(buffer4);
            int size = toInt(buffer4, 4);

            // skip next 16 bytes
            fis.skip(16);

            //System.out.println(size);
            //System.out.println(pixelBit);

            if ( pixelBit == 24 ) {
                // Calculate padding.
                int blank = ((size / height) - width * 3);
                if ( blank == 4 ) {
                    blank = 0;
                }

                // Now at byte#54.
                byte[] rgb = new byte[(width + blank) * height * 3];
                fis.read(rgb, 0, (width + blank) * height * 3);

                fis.close();

                // Convert the byte array to int array.
                rgbInt = new int[width * height];
                int idx = 0;
                for ( int y = 0; y < height; y++ ) {
                    for (int x = 0; x < width; x++) {
                        rgbInt[width * (height - y - 1) + x] =
                            (255 & 0xff) << 24
                            | (( (int) rgb[idx + 2] & 0xff ) << 16)
                            | (( (int) rgb[idx + 1] & 0xff ) << 8)
                            | (int) rgb[idx] & 0xff;
                        idx += 3;
                    }
                    idx += blank;
                }

                // Create image.
                img = Toolkit.getDefaultToolkit().createImage(
                    new MemoryImageSource(width, height, rgbInt, 0, width));

            } else if ( pixelBit == 8 ) {
                fis.skip(256 * 4);

                int blank = ((size / height) - width);

                if ( blank == 4 ) {
                    blank = 0;
                }

                byte gray[] = new byte[(width + blank) * height];
                fis.read(gray, 0, (width + blank) * height);

                fis.close();

                // Convert the byte array to int array.
                int grayInt[] = new int[width * height];
                int idx = 0;
                for ( int y = 0; y < height; y++ ) {
                    for (int x = 0; x < width; x++) {
                        grayInt[width * (height - y - 1) + x] = 
                            (255 & 0xff) << 24
                            | ( (int) gray[idx] & 0xff ) << 16
                            | ( (int) gray[idx] & 0xff ) << 8
                            | (int) gray[idx] & 0xff;
                        idx += 1;
                    }
                    idx += blank;
                }

                img = Toolkit.getDefaultToolkit().createImage(
                    new MemoryImageSource(width, height, grayInt, 0, width));

            }
                
        } catch ( IOException e ){
            e.printStackTrace();
        }
        
        return img;
    }

    public Image myWrite(Image img, String filePath) {
        if ( img instanceof BufferedImage ) {
            return (BufferedImage) img;
        }

        try {
            BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            
            Graphics2D g = bimg.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            
            ImageIO.write(bimg, "bmp", new File(filePath + ".bmp"));
        } catch ( IOException e ){
            e.printStackTrace();
        }

        return img;
    }

    public static int toInt( byte[] src, int num ) {
        int value = 0;
        if ( num == 4 ) {
            value = (int) ( ((src[0] & 0xFF))  
                |((src[1] & 0xFF) << 8)  
                |((src[2] & 0xFF) << 16)  
                |(src[3] & 0xFF) << 24 );  
        } else if ( num == 2 ) {
            value = (int) ( ((src[0] & 0xFF))  
                |((src[1] & 0xFF) << 8)
                |((0x00) << 16)
                |((0x00) << 24) );
        } else if ( num == 1 ) {
            value = (int) ( ((src[0] & 0xFF))  
                |((0x00) << 8)
                |((0x00) << 16)
                |((0x00) << 24) );
        }
        return value; 
    }

    public int[] getRgb() {
        return rgbInt;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
