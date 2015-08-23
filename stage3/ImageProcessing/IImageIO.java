import java.io.*;


public class IImageIO {
    static void myRead(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();
            String content = new String(buffer);
            System.out.println(content);
        } catch ( IOException e ){
            e.printStackTrace();
        }
    }

    void myWrite() {

    }

    public static void main(String[] args) {
        myRead("./bmptest/1.bmp");
    }

}
