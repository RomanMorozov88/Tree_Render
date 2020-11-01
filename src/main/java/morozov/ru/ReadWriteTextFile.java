package morozov.ru;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWriteTextFile {

    public static void writeText(String outputFile, String text) {
        try (FileWriter writer = new FileWriter(outputFile, false)) {
            File f = new File(outputFile);
            if (!f.exists()) {
                f.createNewFile();
            }
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String readText(String inputFile) {
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(inputFile)) {
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

}
