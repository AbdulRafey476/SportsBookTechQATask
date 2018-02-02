package SectionB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DataGenerator {
    public static void main(String[] args) {
        InputStream input = null;
        OutputStream output = null;
        String urlGenerator = "https://api.mockaroo.com/api/43e3fd70?count=1000&key=4515e0e0";
        String filePath = "src/main/resources/randomizedPeopleDataset.json";

        try {
            input = new URL(urlGenerator).openStream();
            output = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0; ) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}