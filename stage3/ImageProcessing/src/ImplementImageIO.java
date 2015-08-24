package my;

import java.io.*;
import java.awt.image.*;
import java.awt.Toolkit;

import imagereader.Runner;


public class ImplementImageIO extends ImageIO{
    public Image myRead(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

            // skip the first 18bytes.
            fis.skip(18);

            byte[] buffer4 = new byte[4];
            byte[] buffer2 = new byte[2];

            // read width #18-21 
            fis.read(buffer4);
            int width = toInt(buffer4, 4);

            // read height #22-25 
            fis.read(buffer4);
            int height = toInt(buffer4, 4);

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

            // 
            int blank = ((size / height) - width * 3) / (width * 3);
            byte rgb[] = new byte[(width + blank) * height * 3];
            fis.read(rgb, 54, (width + blank) * height * 3);

            fis.close();

            int rgbInt[] = new int[width * height];
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

            Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, rgbInt, 0, width));

            //System.out.println(size);

            //return img;
        } catch ( IOException e ){
            e.printStackTrace();
        }
    }

    public Image myWrite(Image img, String filePath) {
        return null;
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
        }
        return value; 
    }

}
